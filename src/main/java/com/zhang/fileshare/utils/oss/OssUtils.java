package com.zhang.fileshare.utils.oss;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.zhang.fileshare.utils.token.JwtHelper;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    /**
     * 批量下载oss 文件 并打成zip 包 返回到response中
     *
     * @param fileNames oss上的文件名集合 如：product/image/3448275920.png
     * @param zipFileName 压缩包文件名
     * @param response  HttpServletResponse
     */
    public static void batchDownLoadOssFile(List<String> fileNames, String zipFileName, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + zipFileName + ".zip");
        BufferedInputStream bis = null;
        try {
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            OSSClient ossClient = new OSSClient(AliyunPropertiesUtils.ENDPOINT, AliyunPropertiesUtils.ACCESS_KEY_ID, AliyunPropertiesUtils.SECRET);
            int sortNum = 0;
            for (String fileName : fileNames) {
                Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
                GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(AliyunPropertiesUtils.BUCKET, fileName, HttpMethod.GET);
                // 设置过期时间。
                request.setExpiration(expiration);
                // 生成签名URL（HTTP GET请求）。
                URL signedUrl = ossClient.generatePresignedUrl(request);
                // 使用签名URL发送请求。
                OSSObject ossObject = ossClient.getObject(signedUrl, new HashMap<>());

                if (ossObject != null) {
                    InputStream inputStream = ossObject.getObjectContent();
                    byte[] buffs = new byte[1024 * 10];

                    String zipFile = sortNum + "_" + fileName.substring(fileName.lastIndexOf("/") + 1);
                    ZipEntry zipEntry = new ZipEntry(zipFile);
                    zos.putNextEntry(zipEntry);
                    bis = new BufferedInputStream(inputStream, 1024 * 10);

                    int read;
                    while ((read = bis.read(buffs, 0, 1024 * 10)) != -1) {
                        zos.write(buffs, 0, read);
                    }
                    ossObject.close();
                }
                sortNum++;
            }
            zos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != bis) {
                    bis.close();
                }
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
