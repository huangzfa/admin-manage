package com.duobei.console.web.controller.finance;

import com.alibaba.fastjson.JSON;
import com.duobei.core.manage.sys.domain.Dict;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.report.criteria.FinanceReportCriteria;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import com.duobei.core.transaction.renewal.domain.BorrowCashRenewal;
import com.duobei.core.transaction.renewal.service.RenewalService;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;
import com.duobei.core.transaction.repayment.service.RepaymentService;
import com.duobei.dic.ZD;
import com.duobei.utils.DateUtil;
import com.duobei.utils.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.duobei.console.web.controller.base.BaseController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author litianxiong
 * @description 财务报表
 * @date 2019/4/12
 */
@Controller
@RequestMapping(value = "${authzPath}/finance/report")
public class ExportReportController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(
            ExportReportController.class);

    private final static String PERMISSIONPRE = "finance:report:";
    private final static String ADDRESSPRE = "finance/report/";
    private final static String DESC = "财务报表导出";

    @Resource
    BorrowCashService borrowCashService;

    @Resource
    RepaymentService repaymentService;

    @Resource
    RenewalService renewalService;


    @RequiresPermissions(PERMISSIONPRE + "view")
    @RequestMapping(value = "/form")
    public String list(Integer productId,Model model){
        //获取用户产品列表
        List<Product> productList = getCredential().getProductList();
        if (productId == null && productList != null && productList.size() > 0){
            //如果未传productId 则赋予初始值
            productId = productList.get(0).getId();
        }

        //报表类型
        List<Dict> reportType = DictUtil.getDictList(ZD.reportType);
        //借款状态
        List<Dict> borrowStateList = DictUtil.getDictList(ZD.borrowState);
        //还款状态
        List<Dict> repayStateList = DictUtil.getDictList(ZD.repayState);
        //续借状态
        List<Dict> renwalStateList = DictUtil.getDictList(ZD.renewalState);

        //默认前一天的开始和结束时间
        Date startTime = DateUtil.getYesterday();
        Date endTime = DateUtil.getDayEnd(startTime);

        model.addAttribute("productLists", JSON.toJSONString(productList));
        model.addAttribute("productId",productId);
        model.addAttribute("reportType",JSON.toJSONString(reportType));
        model.addAttribute("borrowStateList", JSON.toJSONString(borrowStateList));
        model.addAttribute("repayStateList",JSON.toJSONString(repayStateList));
        model.addAttribute("renwalStateList",JSON.toJSONString(renwalStateList));
        model.addAttribute("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
        model.addAttribute("endTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime));
        return ADDRESSPRE + "exportReport";
    }

    @RequiresPermissions(PERMISSIONPRE+"export")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    @ResponseBody
    public String export(FinanceReportCriteria criteria,HttpServletRequest request,HttpServletResponse response) throws Exception {
        try {
            if (ZD.reportType_borrow == criteria.getReportType()){
                //生成借款报表
                createBorrowReport(criteria,response);
                //生成还款报表
                createRepayReport(criteria,response);
                //生成续借报表
                criteriaRenwalReport(criteria,response);
            }
        } catch (Exception e) {
            logger.error("生成财务报表失败{}", e);
            return failJsonResult( "操作失败");
        }
        return simpleSuccessJsonResult("success");
    }

    private void criteriaRenwalReport(FinanceReportCriteria criteria, HttpServletResponse response) {
    }

    private void createRepayReport(FinanceReportCriteria criteria, HttpServletResponse response) {
        String fName = "还款信息_";
        criteria.setPagesize(30000);
        Map<String, String> titleMap = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 1;
            }
        });
        titleMap.put("productName","所属产品");
        titleMap.put("upsThirdOrderNo", "第三方流水号");
        titleMap.put("repayNo", "还款流水");
        titleMap.put("borrowNo", "借款编号");
        titleMap.put("couponAmount", "优惠减免");
        titleMap.put("rebateAmount", "账户余额抵扣");
        titleMap.put("repaymentAmount", "待还金额");
        titleMap.put("actualAmount", "现金还款");
        titleMap.put("actualAmount", "减免金额");
        titleMap.put("","实收金额");

        titleMap.put("gmtCreate", "还款时间");
        titleMap.put("statusName", "状态");
        titleMap.put("repayTypeName", "还款类型");
        titleMap.put("alipayAccount", "支付宝账户");
        titleMap.put("repaymentRemark","备注信息");

        try (final OutputStream os = response.getOutputStream()) {
            String gmtStart = new SimpleDateFormat("yyyy-mm-dd").format(criteria.getStartTime());
            String gmtEnd = new SimpleDateFormat("yyyy-mm-dd").format(criteria.getEndTime());
            String fn = "";
            List<BorrowCashRepayment> list = repaymentService.getReportList(criteria);
            if(StringUtils.isNotBlank(gmtStart) && StringUtils.isNotBlank(gmtEnd)){
                fn = fName + gmtStart.split(" ")[0] + "-" + gmtEnd.split(" ")[0];
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                fn = fName + sdf.format(new Date()).toString();
            }
            ExcelUtil.excelExportOn(list, titleMap, fName, fn, response);
        } catch (Exception e) {
            logger.error("导出失败", e);
        }
    }

    private void createBorrowReport(FinanceReportCriteria criteria, HttpServletResponse response) {
       
    }

    /**
     * 导出表格数据CSV格式的报表
     *//*
    @RequestMapping(value = { "/exportConsumdebtOrderList" }, method = RequestMethod.GET)
    public void exportConsumdebtOrderListCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> dataList = new ArrayList<>();
        String sTitle = "订单编号,图片,产品名称,收件人,电话号码,收获地址,订单状态";
        String fName = "借贷商品订单_";
        String mapKey = "orderNo,goodsIcon,goodsName,consignee,consigneeMobile,address,status";
        ConsumdebtOrderCriteria criteria = bulidReport(request);
        List<ConsumdebtOrder> data = consumdebtOrderService.getListByReportQuery(criteria);
        for (ConsumdebtOrder orderDo : data) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderNo", orderDo.getOrderNo());
            map.put("goodsIcon", orderDo.getGoodsIcon());
            map.put("goodsName", addQuote(orderDo.getGoodsName()));
            map.put("consignee", addQuote(orderDo.getConsignee()));
            map.put("consigneeMobile", orderDo.getConsigneeMobile());
            map.put("address", addQuote(orderDo.getAddress()));
            map.put("status", ConsumdebtOrderStateEumn.findDescByCode((orderDo.getState())));
            dataList.add(map);
        }
        try (final OutputStream os = response.getOutputStream()) {
            ExportUtil.responseSetProperties(fName, response);
            ExportUtil.doExport(dataList, sTitle, mapKey, os);
        } catch (Exception e) {
            logger.error("导出CSV失败", e);
        }

    }

    private ConsumdebtOrderCriteria bulidReport(HttpServletRequest request) {
        String orderNo = ObjectUtils.toString(request.getParameter("orderNo"), null);

        Integer productId = Integer.parseInt(request.getParameter("productId"));
        Long userId ="".equals(request.getParameter("userId")) ? null :  Long.parseLong(request.getParameter("userId"));
        Integer state ="".equals(request.getParameter("state"))  ? null :  Integer.parseInt(request.getParameter("state"));
        String gmtStartStr = ObjectUtils.toString(request.getParameter("startTime"), null);
        String gmtEndStr = ObjectUtils.toString(request.getParameter("endTime"), null);
        String logisticsNo = ObjectUtils.toString(request.getParameter("logisticsNo"), null);

        ConsumdebtOrderCriteria criteria = new ConsumdebtOrderCriteria();
        criteria.setProductId(productId);
        criteria.setOrderNo(orderNo);
        criteria.setUserId(userId);
        criteria.setState(state);
        criteria.setLogisticsNo(logisticsNo);
        criteria.setStartTime(StringUtils.isEmpty(gmtStartStr) ? DateUtil.INIT_DATE : DateUtil.parseDate(gmtStartStr, DateUtil.DEFAULT_PATTERN_WITH_HYPHEN));
        criteria.setEndTime(StringUtils.isEmpty(gmtEndStr) ? DateUtil.getFinalDate() : DateUtil.parseDate(gmtEndStr, DateUtil.DEFAULT_PATTERN_WITH_HYPHEN));
        return criteria;
    }
*/

    /**
     * 防止 数据中带有 逗号,导致 csv文件 格式不正确
     */
    private static String addQuote(String str) {
        StringUtils.replace(str,"\"","\"\"");
        return new StringBuffer()
                .append("\"")
                .append(str)
                .append("\"")
                .toString();
    }
}
