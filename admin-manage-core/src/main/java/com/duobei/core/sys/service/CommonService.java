package com.duobei.core.sys.service;

import com.duobei.core.sys.domain.OssUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

	OssUploadResult uploadImageToOss(MultipartFile imageFile);
}
