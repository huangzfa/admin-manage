package com.duobei.console.web.controller.push;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.SmsChannelCodeEnum;
import com.duobei.common.enums.SmsUserfulCodeEnums;
import com.duobei.common.exception.ManageExceptionCode;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.ImportExcelUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.common.vo.ReturnParamsVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.message.push.domain.PushConfig;
import com.duobei.core.message.push.service.PushConfigService;
import com.duobei.core.message.sms.domain.SmsAppChannelConfig;
import com.duobei.core.message.sms.service.SmsAppChannelConfigService;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.service.ProductService;
import com.duobei.core.operation.push.domain.PushFailUser;
import com.duobei.core.operation.push.domain.PushRecord;
import com.duobei.core.operation.push.domain.criteria.PushRecordCriteria;
import com.duobei.core.operation.push.domain.vo.PushRecordVo;
import com.duobei.core.operation.push.service.PushFailUserService;
import com.duobei.core.operation.push.service.PushRecordService;
import com.duobei.dic.ZD;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/13
 */
@Controller
@RequestMapping(value = "${authzPath}/push")
@Slf4j
public class PushController extends BaseController {

    @Autowired
    private PushRecordService recordService;
    @Autowired
    private PushConfigService configService;
    @Autowired
    private SmsAppChannelConfigService channelConfigService;
    @Autowired
    private PushFailUserService failUserService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AppService appService;

    /**
     * 极光推送列表页
     * @param model
     * @return
     */
    @RequiresPermissions("push:sys:view")
    @RequestMapping(value = "/sys/list")
    public String list(Model model, Integer appId){
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "push/sys/list";
    }

    /**
     * 短信推送列表页
     * @param model
     * @return
     */
    @RequiresPermissions("push:sms:view")
    @RequestMapping(value = "/sms/list")
    public String smsList(Model model, Integer appId){
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "push/sms/list";
    }

    /**
     * ajax查询轮播列表
     * @return
     */
    @RequiresPermissions("push:sys:view")
    @RequestMapping(value = "/sys/getSysData")
    @ResponseBody
    public String getMessageData(PushRecordCriteria criteria ){
        try {
            OperatorCredential credential = getCredential();
            if( credential == null){
                return failJsonResult("登录过期，请重新登录");
            }
            //验证数据权限
            validAuthData(criteria.getProductId());
            if (criteria.getPagesize()== BizConstant.INT_ZERO) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<PushRecordVo> list = recordService.getPageList(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("查询列表异常",e);
                return failJsonResult("查询失败");
            }
        }
    }

    /**
     * 跳转极光编辑页
     * @param model
     * @return
     * @throws TqException
     */
    @RequiresPermissions("push:sys:edit")
    @RequestMapping(value = "/sys/form")
    public String smsForm( Model model,Integer productId) {
        model.addAttribute("productId", productId);
        model.addAttribute("appList", getAppListByProductId(productId));
        return "push/sms/form";
    }

    /**
     * 跳转短信编辑页
     * @param model
     * @return
     * @throws TqException
     */
    @RequiresPermissions("push:sms:edit")
    @RequestMapping(value = "/sms/form")
    public String form( Model model,Integer productId) {
        model.addAttribute("productId", productId);
        model.addAttribute("appList", getAppListByProductId(productId));
        return "push/sms/form";
    }

    @RequiresPermissions("push:sys:edit")
    @RequestMapping(value = "/sys/save")
    @ResponseBody
    public String save(PushRecord record ){
        try {
            OperatorCredential credential = getCredential();
            if( credential == null){
                return failJsonResult("登录过期，请重新登录");
            }
            App app = appService.getAppById(record.getAppId());
            if( app == null ){
                return failJsonResult("应用不存在");
            }
            if( record.getPushType().equals(ZD.pushType_jg)){
                PushConfig config = new PushConfig();
                config.setAppKey(app.getAppKey());
                config.setState(BizConstant.INT_ONE);
                if( configService.countByAppKey(config) <BizConstant.INT_ONE){
                    return failJsonResult("请配送该应用推送账号");
                }
            }else{
                SmsAppChannelConfig config  = new SmsAppChannelConfig();
                config.setState(BizConstant.INT_ONE);
                config.setBusinessCode(SmsUserfulCodeEnums.MARKETING.getCode());
                config.setAppKey(app.getAppKey());
                SmsAppChannelConfig entity = channelConfigService.getByRecord(config);
                if(entity == null){
                    return failJsonResult("请配置该应用营销渠道");
                }
                record.setNoticeType(SmsChannelCodeEnum.findEnvType(entity.getChannelCode()));
            }
            record.setAddOperatorId(credential.getOpId());
            if( record.getPushWay().equals(ZD.pushWay_now)){
                record.setPushTime(new Date());
                recordService.save(record,null);
            }else{
                ReturnParamsVo paramsVo = recordService.saveQuartzInfo(record,null);
                if( !paramsVo.getCode().equals(ManageExceptionCode.SUCCESS.getErrorCode())){
                    return failJsonResult(paramsVo.getMsg());
                }
            }
            return simpleSuccessJsonResult("success");

        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("save异常", e);
                return failJsonResult("save异常");
            }
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequiresPermissions("push:sys:edit")
    @RequestMapping(value = "/sys/delete")
    @ResponseBody
    public String delete(Long id ){
        try {
            OperatorCredential credential = getCredential();
            if( credential == null){
                return failJsonResult("登录过期，请重新登录");
            }
            if( id == null ){
                return failJsonResult("参数错误");
            }
            PushRecord record = recordService.getById(id);
            if( record == null ){
                return failJsonResult("id不存在");
            }
            PushRecord entity = new PushRecord()
                    .setId(record.getId())
                    .setModifyOperatorId(credential.getOpId())
                    .setIsDelete(record.getId())
                    .setModifyTime(new Date());
            recordService.delete(entity);
            return simpleSuccessJsonResult("success");

        }catch (Exception e) {
            log.warn("操作失败", e);
            return failJsonResult(e.getMessage());
        }
    }


    /***
     * 导入Excel
     * @param file
     * @return
     */
    @RequestMapping(value = "/import")
    @RequiresPermissions("push:sys:edit")
    @ResponseBody
    public String sendCoupon(@RequestParam(value="importexcel", required=false) MultipartFile file, PushRecord record, HttpServletResponse response) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( file==null || file.getSize()== BizConstant.INT_ZERO){
                throw new TqException("请上传文件");
            }
            String orgname = file.getOriginalFilename();
            if( !orgname.contains(".xlsx") && !orgname.contains(".xls")){
                throw new TqException("请选择正确模板文件");
            }
            if( file.getSize()>1048576){
                throw new TqException("文件太大,请分批导入");
            }
            App app = appService.getAppById(record.getAppId());
            if( app == null ){
                return failJsonResult("应用不存在");
            }
            if( record.getPushTime() == null ){
                return failJsonResult("请填写推送时间");
            }
            record.setAddOperatorId(credential.getOpId());
            record.setModifyTime(new Date());
            record.setModifyOperatorId(credential.getOpId());
            if( record.getPushType().equals(ZD.pushType_jg)){
                PushConfig config = new PushConfig();
                config.setAppKey(app.getAppKey());
                config.setState(BizConstant.INT_ONE);
                if( configService.countByAppKey(config) <BizConstant.INT_ONE){
                    return failJsonResult("请配送该应用推送账号");
                }
            }else{
                SmsAppChannelConfig config  = new SmsAppChannelConfig();
                config.setState(BizConstant.INT_ONE);
                config.setBusinessCode(SmsUserfulCodeEnums.MARKETING.getCode());
                config.setAppKey(app.getAppKey());
                SmsAppChannelConfig entity = channelConfigService.getByRecord(config);
                if(entity == null){
                    return failJsonResult("请配置该应用营销渠道");
                }
                record.setNoticeType(SmsChannelCodeEnum.findEnvType(entity.getChannelCode()));
            }
            String curProjectPath = "/home/admin/file/push";
            String fileName = curProjectPath + System.currentTimeMillis()+orgname.substring(orgname.lastIndexOf("."),orgname.length());
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fileName));
            InputStream in =file.getInputStream();
            //待推送id名单
            List<List<Object>> listob = ImportExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
            in.close();
            record.setPath(fileName);
            if( record.getPushWay().equals(ZD.pushWay_now)){
                record.setPushTime(new Date());
                recordService.save(record,listob);
            }else{
                ReturnParamsVo paramsVo = recordService.saveQuartzInfo(record,listob);
                if( !paramsVo.getCode().equals(ManageExceptionCode.SUCCESS.getErrorCode())){
                    return failJsonResult(paramsVo.getMsg());
                }
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                // TODO Auto-generated catch block
                log.warn("import解析excel异常",e);
                return failJsonResult("无法正确解析excel内容,请检查文件内容是否符合规范");
            }
        }
    }

    @RequestMapping(value = "/export")
    @RequiresPermissions("market:sendCoupon:edit")
    public void export(HttpServletResponse response,Integer pushId){

        List<PushFailUser> list = failUserService.getListByPushId(pushId);
        String productName = "";
        String appName = "";
        if(list .size() > BizConstant.INT_ZERO){
            Product product = productService.getById(list.get(0).getProductId());
            if(product!=null){
                productName = product.getProductName();
            }
            App app = appService.getAppById(list.get(0).getAppId());
            if( app!=null ){
                appName = app.getAppName();
            }
        }
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 4600);// 设置列宽
        sheet.setColumnWidth((short) 1, (short) 4600);

        Row row = sheet.createRow((int) 0);
        row.setHeightInPoints(20);
        CellStyle style = wb.createCellStyle();
        Font font=wb.createFont();
        font.setColor(HSSFColor.RED.index);//HSSFColor.VIOLET.index //字体颜色
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);//居左
        Cell cell = row.createCell((short) 0);
        cell.setCellValue("platform-id");
        cell = row.createCell((short) 1);
        cell.setCellValue("产品");
        cell = row.createCell((short) 2);
        cell.setCellValue("应用");
        cell = row.createCell((short) 3);
        cell.setCellValue("失败原因");

        style = wb.createCellStyle();
        // 第五步，写入到excel
        for(int i=0;i<list.size();i++){
            row = sheet.createRow((int) i + 1);
            row.setHeightInPoints(20);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(list.get(i).getUserId());
            row.createCell((short) 1).setCellValue(productName);
            row.createCell((short) 2).setCellValue(appName);
            row.createCell((short) 3).setCellValue(list.get(i).getReason());
        }
        try
        {  	//输出Excel文件
            OutputStream output=response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=failUser.xls");
            response.setContentType("application/msexcel");
            wb.write(output);
            output.close();
            wb.close();
        }catch (Exception e)  {
            e.printStackTrace();
        }
    }
}
