package com.duobei.core.manage.sys.service.impl;

import com.aliyun.oss.model.PutObjectResult;
import com.duobei.common.util.FileSizeUtil;
import com.duobei.common.util.OSSUtil;
import com.duobei.core.manage.sys.domain.OssUploadResult;
import com.duobei.core.manage.sys.service.CommonService;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Override
	public OssUploadResult uploadImageToOss(MultipartFile imageFile) {
		OssUploadResult result = new OssUploadResult();
		String fileName = imageFile.getOriginalFilename();
		String contextType = this.getImageFileContentType(fileName);
		String path = OSSUtil.getStorePath();
		String fileNameSuffix = UUID.randomUUID().toString();
		if(StringUtils.isNotBlank(contextType)){
			fileNameSuffix = fileNameSuffix + fileName.substring(fileName.lastIndexOf("."));
		}
		try {
			PutObjectResult putObjectResult = OSSUtil.uploadFileToOss(imageFile, contextType, path, fileNameSuffix);
			int[] widHei = FileSizeUtil.getImageWidthHeight(imageFile.getInputStream());
			if(putObjectResult == null || StringUtils.isBlank(putObjectResult.getETag())){
				result.setSuccess(false);
				result.setMsg("upload to oss error, put object result is null or etag is empty");
				return result;
			}
			result.setSuccess(true);
			result.setMsg("upload to oss succeed");
			result.setFileMd5(putObjectResult.getETag());
			result.setUrl(OSSUtil.getUrlPre() + path + fileNameSuffix);
			result.setWidth(widHei[0]);
			result.setHeight(widHei[1]);
		} catch (IOException e) {
			result.setSuccess(false);
			result.setMsg("upload to oss error, message is " + e.getMessage());
		}
		return result;
	}

	private String getImageFileContentType(String fileName){
		if(org.apache.commons.lang.StringUtils.isBlank(fileName) || fileName.indexOf(".") < 0){
			return "";
		}
		String fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1);
		if("jpeg".equals(fileSuffix)||"jpg".equals(fileSuffix)){
			return "image/jpeg";
		}else if("png".equals(fileSuffix)){
			return "image/png";
		}else if("gif".equals(fileSuffix)){
			return "image/gif";
		}else if("apk".equals(fileSuffix)){
			return "application/vnd.android.package-archive";
		}
		return "";
	}
}
