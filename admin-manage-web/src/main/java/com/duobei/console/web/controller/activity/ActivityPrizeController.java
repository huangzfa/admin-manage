package com.duobei.console.web.controller.activity;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.PrizeTypeEnum;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.NumberUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.domain.OssUploadResult;
import com.duobei.core.manage.sys.service.CommonService;
import com.duobei.core.operation.activity.domain.ActivityHongbaoPrize;
import com.duobei.core.operation.activity.domain.ActivityPrize;
import com.duobei.core.operation.activity.domain.ActivityPrizeRel;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;
import com.duobei.core.operation.activity.service.ActivityHongbaoPrizeService;
import com.duobei.core.operation.activity.service.ActivityPrizeRelService;
import com.duobei.core.operation.activity.service.ActivityPrizeService;
import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.operation.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/26
 */
@Controller
@RequestMapping(value = "${authzPath}/activity/prize")
@Slf4j
public class ActivityPrizeController extends BaseController {

    @Autowired
    private ActivityPrizeService activityPrizeService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private ActivityPrizeRelService prizeRelService;
    @Autowired
    private ActivityHongbaoPrizeService hongbaoPrizeService;


    /**
     * 奖品列表
     *
     * @param model
     * @return
     */
    @RequiresPermissions("activity:list:view")
    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "activity/prize/list";
    }

    /**
     * ajax查询
     *
     * @return
     */
    @RequiresPermissions("activity:list:view")
    @RequestMapping(value = "/getPrizeData")
    @ResponseBody
    public String getProductData(ActivityCriteria criteria) {
        try {
            if (criteria == null) {
                criteria = new ActivityCriteria();
            }
            if (criteria.getPagesize() == 0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<ActivityPrize> list = activityPrizeService.getPageList(criteria);
            return successJsonResult("success", "list", list);
        } catch (Exception e) {
            log.warn("ajax查询奖品列表异常", e);
            return failJsonResult("查询失败");
        }
    }

    /**
     * 跳转奖品编辑页
     * @param model
     * @param prizeId
     * @return
     */
    @RequiresPermissions("activity:list:edit")
    @RequestMapping(value = "/form")
    public String form(Model model, Integer prizeId) {
        if (prizeId != null) {
            model.addAttribute("prize", activityPrizeService.getByPrizeId(prizeId));
        }
        return "activity/prize/form";
    }

    /**
     * 奖品保存
     * @param prize
     * @return
     */
    @RequiresPermissions("activity:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(ActivityPrize prize) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            //如果类型是优惠券，先查询优惠券
            if (prize.getPrizeType().equals(PrizeTypeEnum.JKQ.getEnv()) || prize.getPrizeType().equals(PrizeTypeEnum.HKQ.getEnv())) {
                Coupon couponDo = couponService.getCouponById(prize.getCouponId());
                if (couponDo == null) {
                    throw new TqException("优惠券不存在");
                }
                prize.setMoney(couponDo.getAmount());
                prize.setPrizeName(couponDo.getCouponName());
            } else if (prize.getPrizeType().equals(PrizeTypeEnum.JYJP.getEnv())) {
                //跳转链接如果不为空，设置类型为1
                if (!StringUtil.isBlank(prize.getLink())) {
                    prize.setIsJump(BizConstant.INT_ONE);
                }
            }
            if (prize.getPrizeId() == null) {
                prize.setAddOperatorId(credential.getOpId());
                activityPrizeService.save(prize);
            } else {
                prize.setModifyOperatorId(credential.getOpId());
                prize.setModifyTime(new Date());
                activityPrizeService.update(prize);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("save异常", e);
                return failJsonResult("save异常");
            }
        }
    }

    /**
     * 删除奖品
     * @param prizeId
     * @return
     */
    @RequiresPermissions("activity:list:edit")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String save(Integer prizeId) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            ActivityPrize prize = activityPrizeService.getByPrizeId(prizeId);
            if (prize == null) {
                throw new TqException("奖品不存在");
            }
            //删除之前，先查询是否有活动关联了奖品
            HashMap<String, Object> params = new HashMap<>();
            params.put("prizeId", prize.getPrizeId());
            List<ActivityPrizeRel> list = prizeRelService.getByPrizeId(params);
            if (list.size() > BizConstant.INT_ZERO) {
                throw new TqException("有活动关联了此奖品，请先删除活动中的配置");
            }
            List<ActivityHongbaoPrize> hongbaoList = hongbaoPrizeService.getByPrizeId(params);
            if (hongbaoList.size() > BizConstant.INT_ZERO) {
                throw new TqException("红包活动关联了此奖品，请先删除活动中的配置");
            }
            prize.setModifyOperatorId(credential.getOpId());
            prize.setModifyTime(new Date());
            activityPrizeService.update(prize);
            return simpleSuccessJsonResult("success");
        }catch (Exception e){
            return failJsonResult(e.getMessage());
        }
    }

    /**
     * 查询优惠券
     * @param couponType
     * @param response
     * @return
     */
    @RequestMapping(value = "/getCouponList")
    @ResponseBody
    public String getCouponList(String couponType, HttpServletResponse response) {
        return successJsonResult("success", "list", couponService.getCouponList(couponType));
    }

    /**
     * 单张图片上传
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/uploadIcon", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String uploadIcon(@RequestParam("file") MultipartFile file, Integer ImgFileSize, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (!fileType.equalsIgnoreCase("jpg") && !fileType.equalsIgnoreCase("png") && !fileType.equalsIgnoreCase("jpeg")
                    && !fileType.equalsIgnoreCase("bmp") && !fileType.equalsIgnoreCase("gif")) {
                return failJsonResult("上传的图片格式不符合标准，请修改后重试");
            }
            DecimalFormat df = new DecimalFormat("#.00");
            long fileS = file.getSize();
            double fileSize = NumberUtil.strToDoubleWithDefault(df.format((double) fileS / 1024), 0);

            if (fileSize >= (double) ImgFileSize) {
                return failJsonResult("图片大小不符合标准，请修改后重试");
            }
            OssUploadResult our = commonService.uploadImageToOss(file);
            return successJsonResult("success", "our", our);
        } catch (Exception e) {
            return failJsonResult(e.getMessage());
        }
    }
}
