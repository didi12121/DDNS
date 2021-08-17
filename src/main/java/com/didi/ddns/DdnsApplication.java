package com.didi.ddns;

import com.didi.ddns.http.Aliyun;
import com.didi.ddns.http.IP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class DdnsApplication {

  @Resource
  Aliyun aliyun;
  @Value("${rr}")
  String rr;
  public static void main(String[] args) {
    SpringApplication.run(DdnsApplication.class, args);
  }

  @Scheduled(cron = "0 0/30 0/1 * * ? ")
  public void checkip() throws Exception {
    log.info("开始查询当前配置");
    String publicIPAddress = IP.getPublicIPAddress();
    String nowip = aliyun.getnowip();
    if (!nowip.equals(publicIPAddress)) {
        log.info("当前公网ip 与绑定的公网ip不同 当前公网ip为{},绑定ip为{}",publicIPAddress,nowip);
        log.info("开始更改ip");
        aliyun.changeip(publicIPAddress,rr);
    }else {
      log.info("当前ip与公网ip相同--无需替换");
    }
  }
}
