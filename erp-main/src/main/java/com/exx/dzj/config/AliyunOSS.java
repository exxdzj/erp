package com.exx.dzj.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Date 2019/1/15 0015 14:58
 * @Description
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun")
public class AliyunOSS {

    //@Value("${endpoint}")
    private String endpoint;
    //@Value("${accessKeyId}")
    private String accessKeyId;
    //@Value("${accessKeySecret}")
    private String accessKeySecret;
    //@Value("${bucketName}")
    private String bucketName;
    //@Value("${subdir}")
    private String subdir;
    //@Value("${active}")
    private String active;

}
