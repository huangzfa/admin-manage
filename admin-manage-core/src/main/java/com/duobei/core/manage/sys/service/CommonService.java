package com.duobei.core.manage.sys.service;

import com.duobei.core.manage.sys.domain.OssUploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface CommonService {

	OssUploadResult uploadImageToOss(MultipartFile imageFile);

	public OssUploadResult uploadFileToOssWithPath(MultipartFile file,
												   String path);

	OssUploadResult uploadImageToOss(InputStream is, String failFileName, int length);
}
