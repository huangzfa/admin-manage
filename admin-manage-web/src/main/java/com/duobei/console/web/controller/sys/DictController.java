package com.duobei.console.web.controller.sys;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duobei.common.vo.ListVo;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.sys.domain.Dict;
import com.duobei.core.manage.sys.service.DictService;
import com.duobei.common.exception.TqException;

@Controller
@RequestMapping(value = "${authzPath}/sys/dict")
public class DictController extends BaseController {
	private final static Logger log = LoggerFactory.getLogger(DictController.class);

	@Autowired
	private DictService dictService;
	
	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "/list")
	public String list(Model model){
		return "sys/dict/dict";
	}
	
	@RequiresPermissions("sys:dict:view")
	@ResponseBody
	@RequestMapping(value = "/dictTypetData")
	public String dictTypetData() {
		try {
			List<Dict> list=dictService.queryAllDictType();
			return successJsonResult("success", "list", new ListVo<Dict>(list.size(), list));
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			}else{
				log.warn("查询数据字典异常",e);
				return failJsonResult("查询数据字典异常，请查看错误日志");
			}
		}
	}
	@RequiresPermissions("sys:dict:view")
	@ResponseBody
	@RequestMapping(value = "/dictItemData")
	public String dictItemData(Integer pId) {
		try {
			List<Dict> list=dictService.queryDictByPId(pId);
			return successJsonResult("success", "list", new ListVo<Dict>(list.size(), list));
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			}else{
				log.warn("查询数据字典异常",e);
				return failJsonResult("查询数据字典异常，请查看错误日志");
			}
		}
	}

}
