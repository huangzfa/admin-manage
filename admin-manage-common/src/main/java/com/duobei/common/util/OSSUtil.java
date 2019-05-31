package com.duobei.common.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 文件上传Util
 *
 * @author xingwu
 */
public class OSSUtil {

    private final static Logger logger = LoggerFactory.getLogger(OSSUtil.class);

    private volatile static OSSClient instance;

    private static OSSUtil ossUtil;

    /**
     * OSS 的地址
     */
    private String ossEndPoint;
    /**
     * OSS 的key值
     */
    private String ossAccessKeyId;
    /**
     * OSS 的secret值
     */
    private String ossAccessKeySecret;
    /**
     * OSS 的bucket名字
     */
    private String ossBucketName;

    /**
     * OSS 图片存放地址
     */
    private String ossUrlPre;

    /**
     * OSS 图片存放路径
     */
    private String ossStorePath;

    public OSSUtil() {
    }

    public OSSUtil(String ossEndPoint, String ossAccessKeyId, String ossAccessKeySecret,
        String ossBucketName, String ossUrlPre, String ossStorePath) {
        this.ossEndPoint = ossEndPoint;
        this.ossAccessKeyId = ossAccessKeyId;
        this.ossAccessKeySecret = ossAccessKeySecret;
        this.ossBucketName = ossBucketName;
        this.ossUrlPre = ossUrlPre;
        this.ossStorePath = ossStorePath;
    }

    public String getOssStorePath() {
        return ossStorePath;
    }

    public void setOssStorePath(String ossStorePath) {
        this.ossStorePath = ossStorePath;
    }

    public String getOssEndPoint() {
        return ossEndPoint;
    }

    public void setOssEndPoint(String ossEndPoint) {
        this.ossEndPoint = ossEndPoint;
    }

    public String getOssAccessKeyId() {
        return ossAccessKeyId;
    }

    public void setOssAccessKeyId(String ossAccessKeyId) {
        this.ossAccessKeyId = ossAccessKeyId;
    }

    public String getOssAccessKeySecret() {
        return ossAccessKeySecret;
    }

    public void setOssAccessKeySecret(String ossAccessKeySecret) {
        this.ossAccessKeySecret = ossAccessKeySecret;
    }

    public String getOssBucketName() {
        return ossBucketName;
    }

    public void setOssBucketName(String ossBucketName) {
        this.ossBucketName = ossBucketName;
    }

    public String getOssUrlPre() {
        return ossUrlPre;
    }

    public void setOssUrlPre(String ossUrlPre) {
        this.ossUrlPre = ossUrlPre;
    }
    /**
     * 单例
     *
     * @return OSS工具类实例
     */
    public OSSClient getOSSClient() {
        if (instance == null) {
            synchronized (OSSUtil.class) {
                if (instance == null) {
                    ossUtil = new OSSUtil(ossEndPoint, ossAccessKeyId, ossAccessKeySecret, ossBucketName,ossUrlPre,ossStorePath);
                    logger.info("OSS_END_POINT :{}", ossEndPoint);
                    instance = new OSSClient(ossEndPoint, ossAccessKeyId, ossAccessKeySecret);
                }
            }
        }
        return instance;
    }


    /**
     * 当Bucket不存在时创建Bucket
     */
    private void createBucket() {
        try {
            // 判断是否存在该Bucket，不存在时再重新创建
            if (!getOSSClient().doesBucketExist(ossBucketName)) {
                getOSSClient().createBucket(ossBucketName);
            }
        } catch (Exception e) {
            logger.error("{}", "创建Bucket失败,请核对Bucket名称(规则：只能包含小写字母、数字和短横线，必须以小写字母和数字开头和结尾，长度在3-63之间)");
            throw new RuntimeException("创建Bucket失败,请核对Bucket名称(规则：只能包含小写字母、数字和短横线，必须以小写字母和数字开头和结尾，长度在3-63之间)");
        }
    }


    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param file 文件
     * @return 文件的访问地址
     */
    public static String uploadFile(MultipartFile file, String folder) {
        String fileName = String.format("%s/%s", folder, file.getOriginalFilename());
        try (InputStream inputStream = file.getInputStream()) {
            //上传文件
            ossUtil.getOSSClient().putObject(ossUtil.getOssBucketName(), fileName, inputStream);
            ossUtil.getOSSClient().shutdown();
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{}", "上传文件失败");
            throw new RuntimeException("上传文件失败");
        }
    }

    public static String getUrlPre() {
        return ossUtil.getOssUrlPre();
    }

    public static String getStorePath() {
        return ossUtil.getOssStorePath();
    }

    public static PutObjectResult uploadFileToOss(MultipartFile file, String contextType, String path, String fileName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(contextType);
        PutObjectResult pubResult = null;
        try{
            pubResult = ossUtil.getOSSClient().putObject(ossUtil.getOssBucketName(), path + fileName, file.getInputStream(),metadata);

        }catch(Exception e){
            return null;
        }
        return pubResult;
    }


    /**
     * 获得流
     *
     * @param fileUrl 文件的URL
     * @return 流
     */
    public static InputStream getFileInPutStream(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            logger.error("{}", "文件地址为空");
            throw new RuntimeException("文件地址为空");
        }
        OSSObject ossObject = ossUtil.getOSSClient().getObject(ossUtil.getOssBucketName(), fileUrl);
        return ossObject.getObjectContent();
    }

}
