package com.duobei.core.manage.sys.service.impl;

import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.duobei.common.util.ConfigProperties;
import com.duobei.common.util.FileSizeUtil;
import com.duobei.common.util.OSSUtil;
import com.duobei.core.manage.sys.domain.OssUploadResult;
import com.duobei.core.manage.sys.service.CommonService;

import java.io.*;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("commonService")
public class CommonServiceImpl implements CommonService {
	private static Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	private OSSUtil ossUtil;

	private final static String OSS_URL_PREF ="oss.url.pre";

	private final static String OSS_BUCKET ="oss.bucket.name";
	@Override
	public OssUploadResult uploadImageToOss(MultipartFile imageFile) {
		OssUploadResult result = new OssUploadResult();
		String fileName = imageFile.getOriginalFilename();
		String contextType = this.getFileContentType(fileName);
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

	@Override
	public OssUploadResult uploadFileToOssWithPath(MultipartFile file, String path) {
		String fileName=file.getOriginalFilename();
		String contextType = this.getFileContentType(fileName);
		return this.uploadFileToXls(file, contextType, path, fileName);
	}

	@Override
	public OssUploadResult uploadToOss(InputStream inputStream, String fileName, Long fileSize) {
		OssUploadResult result = new OssUploadResult();
		try{
			String path = ConfigProperties.get(OSSUtil.getStorePath()) + "/";
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(fileSize);
			metadata.setContentType(this.getFileContentType(fileName));
			PutObjectResult pubResult = ossUtil.getOSSClient().putObject(ConfigProperties.get(OSS_BUCKET), path + fileName, inputStream,metadata);

			if(pubResult == null || org.apache.commons.lang.StringUtils.isBlank(pubResult.getETag())){
				log.error("upload to oss error, put object result is null or etag is empty,  fileName {}", fileName);
				result.setSuccess(false);
				result.setMsg("upload to oss error, put object result is null or etag is empty");
				return result;
			}
			result.setSuccess(true);
			result.setMsg("upload inputStream to oss succeed");
			result.setFileMd5(pubResult.getETag());
			String url = ConfigProperties.get(OSS_URL_PREF) + path + fileName;
			result.setUrl(url);
		}catch(Exception e){
			log.error("upload inputStream to oss error", e);
			result.setSuccess(false);
			result.setMsg("upload inputStream to oss error, message is " + e.getMessage());
		}
		return result;
	}

	private OssUploadResult uploadFileToXls(MultipartFile file,String contextType, String path,String fileName) {
		OssUploadResult result = new OssUploadResult();
		try{
			InputStream is=file.getInputStream();
			OutputStream os=new FileOutputStream(new File(path+fileName));
			int i=-1;
			while((i=is.read())!=-1){
				os.write(i);
			}
			result.setSuccess(true);
			result.setMsg("upload to oss succeed");
			result.setUrl(path + fileName);
			os.close();
			is.close();
		}catch(Exception e){
			log.error("upload to oss error", e);
			result.setSuccess(false);
			result.setMsg("upload to oss error, message is " + e.getMessage());
		}
		return result;
	}
	private String getFileContentType(String fileName){
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
		}else if( "xls".equals(fileSuffix) || "xlsx".equals(fileSuffix)) {
			return "application/x-xls";
		}else if("mp4".equals(fileSuffix)){
			return "video/mpeg4";
		}else if("mpt".equals(fileSuffix)){
			return "application/vnd.ms-project";
		}else if("txt".equals(fileSuffix)){
			return "text/plain";
		}
		return "";
	}

	/**
	 * OSS通用文件上传，不限制文件类型
	 * @param imageFile
	 * @return
	 */
	@Override
	public  OssUploadResult uploadFile(MultipartFile imageFile) {
		OssUploadResult result = new OssUploadResult();
		String fileName = imageFile.getOriginalFilename();
		String contextType = this.getFileContentType(fileName);
		String path = OSSUtil.getStorePath();
		String fileNameSuffix = UUID.randomUUID().toString();
		if(StringUtils.isNotBlank(contextType)){
			fileNameSuffix = fileNameSuffix + fileName.substring(fileName.lastIndexOf("."));
		}
		try {
			PutObjectResult putObjectResult = OSSUtil.uploadFileToOss(imageFile, contextType, path, fileNameSuffix);
			if(putObjectResult == null || StringUtils.isBlank(putObjectResult.getETag())){
				result.setSuccess(false);
				result.setMsg("upload to oss error, put object result is null or etag is empty");
				return result;
			}
			result.setSuccess(true);
			result.setMsg("upload to oss succeed");
			result.setFileMd5(putObjectResult.getETag());
			result.setUrl(OSSUtil.getUrlPre() + path + fileNameSuffix);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("upload to oss error, message is " + e.getMessage());
		}
		return result;
	}
}
