package com.zhang.fileshare.utils.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zhang.fileshare.utils.token.JwtHelper;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author zhazhalin
 * @version 1.0
 * @date 2022/8/11 15:39
 */
public class OssUtils {
    public static String upload(HttpServletRequest request,MultipartFile multipartFile) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = AliyunPropertiesUtils.ENDPOINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = AliyunPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = AliyunPropertiesUtils.SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = AliyunPropertiesUtils.BUCKET;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = null;
        String fileName = null;
        try {
            //获得文件信息流
            inputStream = multipartFile.getInputStream();
            //根据文件获取上传文件的原始文件名
            String originalFilename = multipartFile.getOriginalFilename();
            //获取当前用户id+姓名来进行文件夹分类
            String username = JwtHelper.getUserId(request.getHeader("token")) + JwtHelper.getUserName(request.getHeader("token"));

            //获取当前日期
            String dateTime = new DateTime().toString("yyyy-MM-dd");
            //根据当前日期创建文件夹
            fileName = username+"/"+dateTime + "/" + UUID.randomUUID().toString().replaceAll("-", "") + originalFilename;
            // 创建PutObject请求。
            ossClient.putObject(bucketName, fileName, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }
}
