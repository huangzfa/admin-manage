package com.duobei.console.web.controller.market;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.ImportExcelUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.domain.OssUploadResult;
import com.duobei.core.manage.sys.service.CommonService;
import com.duobei.core.operation.coupon.domain.CouponSendRecord;
import com.duobei.core.operation.coupon.domain.criteria.CouponCriteria;
import com.duobei.core.operation.coupon.domain.criteria.CouponSendRecordCriteria;
import com.duobei.core.operation.coupon.domain.vo.CouponSendRecordVo;
import com.duobei.core.operation.coupon.service.CouponSendRecordService;
import com.duobei.utils.BizCacheUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**优惠券发送管理类
 * @author huangzhongfa
 * @date 2018/12/3
 */
@Controller
@RequestMapping(value = "${authzPath}/market/coupon")
public class SendCouponController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(SendCouponController.class);

    @Resource
    private BizCacheUtil bizCacheUtil;
    @Autowired
    private CouponSendRecordService recordService;
    @Autowired
    private CommonService commonService;


    @RequiresPermissions("market:sendCoupon:view")
    @RequestMapping(value = "/send")
    public String index(Model model){
        model.addAttribute("productList",getCredential().getProductList());
        return "market/coupon/sendCoupon";
    }


    @RequiresPermissions("market:coupon:edit")
    @RequestMapping(value = "/senCoupon")
    @ResponseBody
    public String senCoupon(String phone,Long couponId) throws RuntimeException{
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new RuntimeException("登录过期，请重新登录");
            }
            if( StringUtil.isBlank(phone)){
                throw new RuntimeException("请填写手机号");
            }
            if( couponId==null ){
                throw new RuntimeException("请选择优惠券");
            }
            recordService.sendCouponByPhone(phone,couponId,credential);
            return simpleSuccessJsonResult("success");
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("senCoupon优惠券发送异常",e);
                return failJsonResult("发送失败");
            }
        }
    }

    /***
     * 导入Excel
     * @param file
     * @return
     */
    @RequestMapping(value = "/import")
    @RequiresPermissions("market:sendCoupon:edit")
    @ResponseBody
    public String sendCoupon(@RequestParam(value="importexcel", required=false) MultipartFile file, Long couponId, String couponSource, HttpServletResponse response) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( couponId==null ){
                throw new TqException("参数为空");
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
            InputStream in =file.getInputStream();
            List<List<Object>> listob = ImportExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
            in.close();
            //上传文件到oss
            OssUploadResult uploadResult = commonService.uploadFile(file);
            if( !uploadResult.isSuccess()){
                return failJsonResult(uploadResult.getMsg());
            }
            HashMap<String,Object> map = recordService.batchSendCoupon(uploadResult.getUrl(),listob,couponId,credential);
            Object msg = map.get("msg");
            if( msg != null){
                return failJsonResult(msg.toString());
            }else {
                return successJsonResult("success","map",map);
            }
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
    public void export(HttpServletResponse response){
        Object object = bizCacheUtil.getObjectList("sendFailUser");//获取缓存失败用户
        if( object == null ){
            return;
        }
        List<Map> maps = (List<Map>)object;
        bizCacheUtil.delCache("sendFailUser");//清除缓存
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
        cell.setCellValue("失败原因");


        style = wb.createCellStyle();
        // 第五步，写入到excel
        for(int i=0;i<maps.size();i++){
            row = sheet.createRow((int) i + 1);
            row.setHeightInPoints(20);
            Map<String,String> map = maps.get(i);
            Set<String> keys = map.keySet();//此行可省略，直接将map.keySet()写在for-each循环的条件中
            for(String key:keys) {
                // 第四步，创建单元格，并设置值
                row.createCell((short) 0).setCellValue(key);
                row.createCell((short) 1).setCellValue(map.get(key));
            }
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


    /**
     * 发送记录
     * @param model
     * @return
     */
    @RequiresPermissions("market:sendCoupon:view")
    @RequestMapping(value = "/record")
    public String sendRecord(Model model){
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "market/coupon/sendRecord";
    }

    @RequiresPermissions("market:sendCoupon:view")
    @RequestMapping(value = "/getRecordData")
    @ResponseBody
    public String getRecordData(CouponSendRecordCriteria criteria) throws TqException{
        try {
            if( criteria == null ){
                criteria = new CouponSendRecordCriteria();
            }
            if (criteria.getPagesize()==0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<CouponSendRecordVo> list = recordService.getPage(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("senCoupon优惠券发送记录异常",e);
                return failJsonResult("查询失败");
            }
        }
    }
}
