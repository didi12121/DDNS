package com.didi.ddns.config;


import com.aliyun.alidns20150109.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AliyunConfig {
  @Value("${accessKeyId}")
  String accessKeyId;
  @Value("${accessKeySecret}")
  String accessKeySecret;

  public Client getclient() throws Exception {
      return new Client(new Config()
        .setAccessKeyId(accessKeyId)
        .setAccessKeySecret(accessKeySecret));
  }

}
