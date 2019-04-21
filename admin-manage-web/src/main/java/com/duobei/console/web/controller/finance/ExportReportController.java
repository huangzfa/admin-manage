package com.duobei.console.web.controller.finance;

import com.alibaba.fastjson.JSON;
import com.duobei.core.base.SpecialDateEditor;
import com.duobei.core.manage.sys.domain.Dict;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.service.ProductService;
import com.duobei.core.operation.report.criteria.FinanceReportCriteria;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashReportVo;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import com.duobei.core.transaction.renewal.domain.BorrowCashRenewal;
import com.duobei.core.transaction.renewal.domain.vo.BorrowCashRenewalReportVo;
import com.duobei.core.transaction.renewal.service.RenewalService;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentReportVo;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    @Resource
    ProductService productService;

    @InitBinder
    private void initBinder(WebDataBinder b) {
        b.registerCustomEditor(Date.class, new SpecialDateEditor());
    }

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
        List<Dict> renewalStateList = DictUtil.getDictList(ZD.renewalState);

        //默认前一天的开始和结束时间
        Date startTime = DateUtil.getYesterday();
        Date endTime = DateUtil.getDayEnd(startTime);

        model.addAttribute("productLists", JSON.toJSONString(productList));
        model.addAttribute("productId",productId);
        model.addAttribute("reportType",JSON.toJSONString(reportType));
        model.addAttribute("borrowStateList", JSON.toJSONString(borrowStateList));
        model.addAttribute("repayStateList",JSON.toJSONString(repayStateList));
        model.addAttribute("renewalStateList",JSON.toJSONString(renewalStateList));
        model.addAttribute("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
        model.addAttribute("endTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime));
        return ADDRESSPRE + "exportReport";
    }

    @RequiresPermissions(PERMISSIONPRE+"export")
    @RequestMapping(value = "/export")
    public String export(FinanceReportCriteria criteria,HttpServletRequest request,HttpServletResponse response) throws Exception {

        //验证数据权限
        if( criteria.getProductId() !=null ){
            try {
                validAuthData(criteria.getProductId());
            }catch (Exception e){
                return failJsonResult(e.getMessage());
            }
        }else{
            return failJsonResult("产品数据导出失败");
        }
        //获取产品信息
        Product product = productService.getById(criteria.getProductId());
        criteria.setProduct(product);
        try {
            if (ZD.reportType_borrow == criteria.getReportType()){
                //生成借款报表
                createBorrowReport(criteria,response);
            }else if(ZD.reportType_repay == criteria.getReportType()){
                //生成还款报表
                createRepayReport(criteria,response);
            }else if(ZD.reportType_renewal == criteria.getReportType()){
                //生成续借报表
                criteriaRenewalReport(criteria,response);
            }else{
                return failJsonResult("导出类型错误");
            }
        } catch (Exception e) {
            logger.error("生成财务报表失败{}", e);
            return failJsonResult( "操作失败");
        }
        return simpleSuccessJsonResult("success");
    }

    private void criteriaRenewalReport(FinanceReportCriteria criteria, HttpServletResponse response) {
        String fName = "【"+criteria.getProduct().getProductName()+"】续借信息_";
        //最大导出30000条
        criteria.setPagesize(30000);
        criteria.setPage(1);
        Map<String, String> titleMap = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 1;
            }
        });
        titleMap.put("productName","所属产品");
        titleMap.put("renewalNo", "续借流水");
        titleMap.put("borrowNo", "借款编号");
        titleMap.put("capitalAmountDecimal", "还款本金");
        titleMap.put("rebateAmountDecimal", "账户余额抵扣");
        titleMap.put("actualAmountDecimal", "实际支付");
        titleMap.put("addTime", "续期时间");
        titleMap.put("renewalStateName", "状态");

        try (final OutputStream os = response.getOutputStream()) {
            String gmtStart = new SimpleDateFormat("yyyy-MM-dd").format(criteria.getStartTime());
            String gmtEnd = new SimpleDateFormat("yyyy-MM-dd").format(criteria.getEndTime());
            String fn = "";
            List<BorrowCashRenewalReportVo> list = renewalService.getReportList(criteria);
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

    private void createRepayReport(FinanceReportCriteria criteria, HttpServletResponse response) {
        String fName = "【"+criteria.getProduct().getProductName()+"】还款信息_";
        //最大导出30000条
        criteria.setPagesize(30000);
        criteria.setPage(1);
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
        titleMap.put("couponAmountDecimal", "优惠金额");
        titleMap.put("rebateAmountDecimal", "账户余额抵扣");
        titleMap.put("unpaidAmountDecimal", "待还金额");
        titleMap.put("repayAmountDecimal", "现金还款");
        /*titleMap.put("actualAmount", "减免金额");*/
        titleMap.put("repayActualAmountDecimal","实收金额");
        titleMap.put("gmtPlanRepayment", "应还款时间");
        titleMap.put("addTime", "还款时间");
        titleMap.put("repayStateName", "状态");
        titleMap.put("repayTypeName", "还款类型");
        titleMap.put("accountName", "支付宝账户");
        titleMap.put("remark","备注信息");

        try (final OutputStream os = response.getOutputStream()) {
            String gmtStart = new SimpleDateFormat("yyyy-MM-dd").format(criteria.getStartTime());
            String gmtEnd = new SimpleDateFormat("yyyy-MM-dd").format(criteria.getEndTime());
            String fn = "";
            List<BorrowCashRepaymentReportVo> list = repaymentService.getReportList(criteria);
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
        String fName = "【"+criteria.getProduct().getProductName()+"】借款信息_";
        //最大导出30000条
        criteria.setPagesize(30000);
        criteria.setPage(1);
        Map<String, String> titleMap = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 1;
            }
        });
        titleMap.put("productName","所属产品");
        titleMap.put("upsThirdOrderNo", "借款单号");
        titleMap.put("borrowNo", "借款编号");
        titleMap.put("amountDeciaml", "借款金额");
        titleMap.put("borrowDays", "借款期限（天）");
        titleMap.put("poundageDeciaml", "服务费");
        titleMap.put("activityAmountDeciaml", "优惠金额");
        titleMap.put("arrivalAmountDeciaml", "到账金额");
        titleMap.put("borrowStateName", "借款状态");
        titleMap.put("riskStateName","风控状态");
        titleMap.put("addTime", "申请时间");
        titleMap.put("gmtPlanRepayment", "预计还款时间");
        titleMap.put("renewalNum", "续借次数");
        titleMap.put("poundageRate", "借钱手续费率");
        titleMap.put("waitAmountDeciaml","未还金额");
        titleMap.put("sumOverdueAmountDeciaml","已还逾期费");
        titleMap.put("repayAmountDeciaml","已还金额");
        titleMap.put("waitOverdueAmountDeciaml","未还金额");
        try (final OutputStream os = response.getOutputStream()) {
            String gmtStart = new SimpleDateFormat("yyyy-MM-dd").format(criteria.getStartTime());
            String gmtEnd = new SimpleDateFormat("yyyy-MM-dd").format(criteria.getEndTime());
            String fn = "";
            List<BorrowCashReportVo> list = borrowCashService.getReportList(criteria);

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
}
