package main.java.com.duobei.console.web.controller.app;

import com.duobei.common.exception.TqException;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;
import com.duobei.core.operation.consume.service.ConsumeLoanConfigService;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.startupPage.domain.StartupPage;
import com.duobei.core.operation.startupPage.service.StartupPageService;
import com.duobei.dic.ZD;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author litianxiong
 * @description 启动页配置控制器
 * @date 2019/3/2
 */
@Controller
@RequestMapping(value = "${authzPath}/app/borrowShow")
public class BorrowShowController extends BaseController {
    Logger log = LoggerFactory.getLogger(BorrowShowController.class);
    private final static String PERMISSIONPRE = "app:borrowShow:";
    private final static String ADDRESSPRE = "app/borrowShow/";
    private final static String DESC = "借钱页默认配置";
    @Resource
    ConsumeLoanConfigService consumeLoanConfigService;

    /**
     * 启动页配置获取
     * @param model
     *
     * @throws TqException
     */
    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/form")
    public String startupPageForm( Model model,Integer productId)  {
        OperatorCredential credential = getCredential();
        //获取用户产品权限
        List<Product> productList = credential.getProductList();
        if (productList == null || productList.size() <= 0){
            return failJsonResult("无产品权限");
        }
        //应用列表

        if (productId == null){
            productId = productList.get(0).getId();
        }
        //查询应用
        ConsumeLoanConfig consumeLoanConfig = consumeLoanConfigService.getByProductId(productId);
        if (consumeLoanConfig == null){
            model.addAttribute("borrowShow",new ConsumeLoanConfig());
        }else{
            model.addAttribute("borrowShow",consumeLoanConfig);
        }
        model.addAttribute("productList",productList);
        model.addAttribute("productId",productId);

        return "app/borrowShow/borrowShowForm";
    }
    @RequiresPermissions(PERMISSIONPRE + "edit")
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update (ConsumeLoanConfig consumeLoanConfig){

        //获取用户产品权限
        try {
             OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            //验证数据权限
            if( consumeLoanConfig.getProductId() != null ){
                validAuthData(null,consumeLoanConfig.getProductId());
            }else{
                throw new TqException("数据操作权限失败");
            }

            //修改时间、修改人
            consumeLoanConfig.setModifyOperatorId(credential.getOpId());
            consumeLoanConfig.setModifyTime(new Date());
            //显示最小、最大可借金额 元--->分
            consumeLoanConfig.setShowMinAmount(consumeLoanConfig.getShowMinAmount()*1000);
            consumeLoanConfig.setShowMaxAmount(consumeLoanConfig.getShowMaxAmount()*1000);
            if (consumeLoanConfig.getId() == null){
                //新增
                //添加人、添加时间
                consumeLoanConfig.setAddOperatorId(credential.getOpId());
                consumeLoanConfig.setAddTime(consumeLoanConfig.getModifyTime());
                //
                consumeLoanConfigService.saveBorrowShow(consumeLoanConfig);
            }else {
               /* startupPage.setId(data.getId());*/
                //修改
               //查询数据
                ConsumeLoanConfig oldData = consumeLoanConfigService.getById(consumeLoanConfig.getId());
                consumeLoanConfig.setDataVersion(oldData.getDataVersion());
                consumeLoanConfigService.updateBorrowShowById(consumeLoanConfig);
            }
            return simpleSuccessJsonResult ("success",1);
        } catch (Exception e) {
            log.error("编辑"+DESC+"异常", e);
            if (e instanceof TqException){
                return failJsonResult(e.getMessage());
            }else {
                return failJsonResult("编辑"+DESC+"异常");
            }
        }
    }

}
