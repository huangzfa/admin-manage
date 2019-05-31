package com.duobei.core.manage.sys.service;

import com.duobei.core.manage.sys.domain.OssUploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface CommonService {

	OssUploadResult uploadImageToOss(MultipartFile imageFile);

	public OssUploadResult uploadFileToOssWithPath(MultipartFile file,
												   String path);

	OssUploadResult uploadToOss(InputStream inputStream, String fileName, Long fileSize);

	/**
	 * 支持所有文件上传
	 * @param imageFile
	 * @return
	 */
	OssUploadResult uploadFile(MultipartFile imageFile);
}
