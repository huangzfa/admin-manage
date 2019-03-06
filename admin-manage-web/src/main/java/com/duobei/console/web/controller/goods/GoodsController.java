package com.duobei.console.web.controller.goods;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoods;
import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoodsPic;
import com.duobei.core.operation.consumdebt.domain.criteria.ConsumdebtGoodsCriteria;
import com.duobei.core.operation.consumdebt.domain.vo.ConsumdebtGoodsVo;
import com.duobei.core.operation.consumdebt.service.ConsumdebtGoodsPicService;
import com.duobei.core.operation.consumdebt.service.ConsumdebtGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**借贷商品管理
 * @author huangzhongfa
 * @description
 * @date 2019/3/6
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/goods")
public class GoodsController extends BaseController {

    @Autowired
    private ConsumdebtGoodsService consumdebtGoodsService;
    @Autowired
    private ConsumdebtGoodsPicService consumdebtGoodsPicService;

    /**
     * 商户产品列表页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("goods:list:view")
    @RequestMapping(value = "/list")
    public String mpList(Model model) {
        return "goods/list";
    }

    /**
     * ajax查询商品列表
     *
     * @return
     */
    @RequiresPermissions("goods:list:view")
    @RequestMapping(value = "/getGoodsData")
    @ResponseBody
    public String getProductData(ConsumdebtGoodsCriteria criteria) {
        try {
            if (criteria == null) {
                criteria = new ConsumdebtGoodsCriteria();
            }
            if (criteria.getPagesize() == 0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<ConsumdebtGoodsVo> list = consumdebtGoodsService.getPageList(criteria);
            return successJsonResult("success", "list", list);
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("ajax查询商品列表异常", e);
                return failJsonResult("查询失败");
            }
        }
    }

    /**
     * 跳转商品编辑页面
     * @param model
     * @param goodsNo
     * @return
     */
    @RequiresPermissions("goods:list:view")
    @RequestMapping(value = "/form")
    public String form(Model model,String goodsNo) {
        ConsumdebtGoodsVo vo = null;
        if(!StringUtil.isBlank(goodsNo)){
            vo = consumdebtGoodsService.getByGoodsNo(goodsNo);
            if( vo!=null){
                //查询轮播图和详情图
                List<ConsumdebtGoodsPic> picList = consumdebtGoodsPicService.getByGoodsId(vo.getId());
                StringBuilder detailUrls = new StringBuilder();//详情图
                StringBuilder bannerUrls = new StringBuilder();//轮播图
                for (ConsumdebtGoodsPic pic : picList) {
                    if( pic.getPicType().equals(1)){
                        bannerUrls.append(",");
                        bannerUrls.append(pic.getPicUrl());
                    }else{
                        detailUrls.append(",");
                        detailUrls.append(pic.getPicUrl());
                    }

                }
                if(detailUrls.length()>0){
                    model.addAttribute("detailUrls",detailUrls.substring(1));
                }
                if(bannerUrls.length()>0){
                    model.addAttribute("bannerUrls",bannerUrls.substring(1));
                }
            }
        }else{
            vo = new ConsumdebtGoodsVo();
        }
        model.addAttribute("goods",vo);
        return "goods/form";
    }

}
