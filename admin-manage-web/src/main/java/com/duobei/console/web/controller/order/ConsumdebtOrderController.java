package com.duobei.console.web.controller.order;

import com.alibaba.fastjson.JSON;
import com.duobei.common.enums.ConsumdebtOrderStateEumn;
import com.duobei.common.enums.ResourceResTypeEumn;
import com.duobei.common.enums.ResourceResTypeSecEumn;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.base.SpecialDateEditor;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.domain.Dict;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.biz.domain.BizResource;
import com.duobei.core.operation.biz.service.BizResourceService;
import com.duobei.core.operation.consume.service.ConsumeLoanConfigService;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;
import com.duobei.core.transaction.consumdebt.domain.criteria.ConsumdebtOrderCriteria;
import com.duobei.core.transaction.consumdebt.domain.vo.ConsumdebtOrderListVo;
import com.duobei.core.transaction.consumdebt.service.ConsumdebtOrderService;
import com.duobei.dic.ZD;
import com.duobei.utils.DateUtil;
import com.duobei.utils.ExportUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.util.*;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/8
 */
@Controller
@RequestMapping("${authzPath}/order/consumdebt")
public class ConsumdebtOrderController extends BaseController {
    private final static Logger log = LoggerFactory.getLogger(ConsumdebtOrderController.class);
    private final static String PERMISSIONPRE = "order:consumdebt:";
    private final static String ADDRESSPRE = "order/consumdebt/";
    private final static String DESC = "借贷商品";

    @Resource
    ConsumdebtOrderService consumdebtOrderService;

    @Resource
    BizResourceService bizResourceService;

    @InitBinder
    private void initBinder(WebDataBinder b) {
        b.registerCustomEditor(Date.class, new SpecialDateEditor());
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/list")
    public String list(Integer productId, Model model) {
        List<Product> productList = getCredential().getProductList();
        if (productId == null && productList != null && productList.size() > 0){
            //如果未传productId 则赋予初始值
            productId = productList.get(0).getId();
        }
        //借贷商品订单状态
        List<Dict> stateList = DictUtil.getDictList(ZD.consumdebtOrderState);
        model.addAttribute("productLists", JSON.toJSONString(productList));
        model.addAttribute("productId",productId);
        model.addAttribute("stateList",JSON.toJSONString(stateList));
        return ADDRESSPRE+"consumdebtOrderList";
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/consumdebtOrderList")
    public String getList(ConsumdebtOrderCriteria consumdebtOrderCriteria) {
        OperatorCredential credential = getCredential();
        if( credential == null){
            return failJsonResult("登录过期，请重新登录");
        }
        //验证数据权限
        if( consumdebtOrderCriteria.getProductId() !=null ){
            try {
                validAuthData(consumdebtOrderCriteria.getProductId());
            }catch (Exception e){
                return failJsonResult(e.getMessage());
            }

        }else{
            return failJsonResult("产品数据查询失败");

        }
        if (consumdebtOrderCriteria.getPagesize() == 0) {
            consumdebtOrderCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        try {
            Map<String,Object> dataMap = new HashMap<>();

            //查询列表
            ListVo<ConsumdebtOrderListVo> list = consumdebtOrderService.getListByQuery(consumdebtOrderCriteria);

            dataMap.put("list",list);

            return successJsonResult(dataMap,"success");
        } catch (Exception e) {
            log.warn("查询"+DESC+"列表异常", e);
            return failJsonResult("系统异常");
        }
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/form")
    public String getInfo(ConsumdebtOrder consumdebtOrder) throws TqException {
        Map<String,Object> dataMap = new HashMap();
        //查询借款信息
        consumdebtOrder = consumdebtOrderService.getById(consumdebtOrder.getId());
        dataMap.put("consumdebtOrder",consumdebtOrder);
        return successJsonResult(dataMap);
    }

    /**
     * 修改
     * @param entity
     * @return
     * @throws TqException
     */
    @RequiresPermissions(PERMISSIONPRE+"edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(ConsumdebtOrder entity) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }else{
                validAuthData(entity.getProductId());
            }
            //验证参数
            validParam(entity);
            //修改人、修改时间
            entity.setModifyTime(new Date());
            entity.setModifyOperatorId(credential.getOpId());
            //修改
            consumdebtOrderService.updateDelivery(entity);
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("修改"+DESC+"信息异常", e);
                return failJsonResult("修改"+DESC+"信息异常");
            }

        }

    }

    private void validParam(ConsumdebtOrder entity) throws TqException {
        //收货手机号验证
        if (StringUtil.isEmpty(entity.getConsigneeMobile())){
            throw new TqException("收货手机号不能为空");
        }
        //收货人验证
        if (StringUtil.isEmpty(entity.getConsigneeMobile())){
            throw new TqException("收货人不能为空");
        }
        //收货地址验证
        if (StringUtil.isEmpty(entity.getConsigneeMobile())){
            throw new TqException("收货地址不能为空");
        }
    }


    @RequiresPermissions(PERMISSIONPRE+"export")
    @RequestMapping(value = "/export", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String export(ConsumdebtOrderCriteria consumdebtOrderCriteria) throws Exception {
        try {

            Long count =consumdebtOrderService.queryCount(consumdebtOrderCriteria);
             BizResource resource = bizResourceService.getByResTypeAnResTypeSec(ResourceResTypeEumn.BIZ_CONFIG.getCode(), ResourceResTypeSecEumn.COMSUMDEBTORDER_REPORT_MAX_NUM.getCode());
             //默认最大条数为5W条
            int defaultMax = 50000;
            if (resource != null){
                defaultMax = resource.getIntValue();
            }
             if (count>defaultMax){
                return failJsonResult( "导出数据条数超出限制.请导出更小时间范围的数据 (一共"+count+"条,最大限制"+defaultMax+"条)");
            }
        } catch (Exception e) {
            logger.error("edit ConsumdebtOrder error", e);
            return failJsonResult( "操作失败");
        }
        return simpleSuccessJsonResult("success");
    }
    /**
     * 导出表格数据CSV格式的报表
     */
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
        Long userId = Long.valueOf(request.getParameter("userId"));
        Integer state = Integer.valueOf(request.getParameter("state"));
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
