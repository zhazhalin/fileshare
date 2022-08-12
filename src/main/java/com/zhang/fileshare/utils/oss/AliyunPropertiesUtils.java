package com.zhang.fileshare.utils.oss;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhazhalin
 * @version 1.0
 * @date 2022/8/11 15:03
 */
@Component
public class AliyunPropertiesUtils implements InitializingBean {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.secret}")
    private String secret;
    @Value("${aliyun.oss.bucket}")
    private String bucket;
    public static String ENDPOINT;
    public static String ACCESS_KEY_ID;
    public static String SECRET;
    public static String BUCKET;
    @Override
    public void afterPropertiesSet() throws Exception {
        this.ENDPOINT=endpoint;
        this.ACCESS_KEY_ID=accessKeyId;
        this.SECRET=secret;
        this.BUCKET=bucket;
    }
}
