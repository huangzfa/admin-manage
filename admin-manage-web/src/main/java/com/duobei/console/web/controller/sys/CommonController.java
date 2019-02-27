package com.duobei.console.web.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.duobei.common.util.lang.NumberUtil;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.service.MenuService;
import com.duobei.core.manage.sys.domain.OssUploadResult;
import com.duobei.core.manage.sys.service.CommonService;
import com.duobei.common.exception.TqException;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "${authzPath}/common")
public class CommonController extends BaseController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/map")
	public String map(String lon, String lat, Model model) {
		model.addAttribute("lon", lon);
		model.addAttribute("lat", lat);
		return "common/map";
	}

	@RequestMapping(value = "/encrypt/menu")
	public String encryptMenu(String url, HttpServletRequest request, HttpServletResponse response) {
		String menuUrl = menuService.decryptMenuUrl(url);
		return "redirect:" + menuUrl;
	}

	/**
	 * 单张图片上传
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadIcon", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String bankIconChange(MultipartFile imageFile, Integer ImgFileSize, HttpServletRequest request, HttpServletResponse response) throws  IOException{
		try {

			String fileName = imageFile.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
			if ( !fileType.equalsIgnoreCase("jpg") && !fileType.equalsIgnoreCase("png") && !fileType.equalsIgnoreCase("jpeg")
					&& !fileType.equalsIgnoreCase("bmp") && !fileType.equalsIgnoreCase("gif")) {
				return failJsonResult("上传的图片格式不符合标准，请修改后重试");
			}
			DecimalFormat df = new DecimalFormat("#.00");
			long fileS = imageFile.getSize();
			double fileSize = NumberUtil.strToDoubleWithDefault(df.format((double) fileS / 1024), 0);

			if(fileSize>=(double)ImgFileSize){
				return failJsonResult("图片大小不符合标准，请修改后重试");
			}
			OssUploadResult uploadResult = commonService.uploadImageToOss(imageFile);
			return successJsonResult("success", "url", uploadResult.getUrl());
		}catch (Exception e){
			return failJsonResult("上传异常");
		}
	}

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(MultipartFile file,Integer maxFileSize,String enableFileTypes) {
		OssUploadResult uploadResult = new OssUploadResult();
		try {
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!enableFileTypes.contains(fileType)) {
				uploadResult.setSuccess(false);
				uploadResult.setMsg("上传的图片格式不符合标准，请修改后重试");
				return JSONObject.toJSONString(uploadResult);
			}
			DecimalFormat df = new DecimalFormat("#.00");
			long fileS = file.getSize();
			double fileSize = NumberUtil.strToDoubleWithDefault(df.format((double) fileS / 1024), 0);
			if(fileSize>=(double)maxFileSize){
				uploadResult.setSuccess(false);
				uploadResult.setMsg("图片大小不符合标准，请修改后重试");
				return JSONObject.toJSONString(uploadResult);
			}
			uploadResult = commonService.uploadImageToOss(file);
			return JSONObject.toJSONString(uploadResult);
		} catch (Exception e) {
			if (e instanceof TqException) {
				return failJsonResult(e.getMessage());
			} else {
				uploadResult.setSuccess(false);
				uploadResult.setMsg("图片上传异常，请查看错误日志");
				return JSONObject.toJSONString(uploadResult);
			}
		}
	}
}