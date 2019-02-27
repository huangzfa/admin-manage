package com.duobei.core.manage.sys.service;

import com.duobei.core.manage.sys.domain.OssUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

	OssUploadResult uploadImageToOss(MultipartFile imageFile);
}
