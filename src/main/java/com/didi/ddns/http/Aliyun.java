package com.didi.ddns.http;

import com.aliyun.alidns20150109.models.*;
import com.didi.ddns.config.AliyunConfig;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class Aliyun {

  @Resource
  AliyunConfig aliyunConfig;
  @Value("${recordid}")
  String recordid;

  public DescribeDomainRecordInfoResponseBody getnowip() throws Exception {
    DescribeDomainRecordInfoRequest describeDomainRecordInfoRequest = new DescribeDomainRecordInfoRequest()
      .setRecordId(recordid);
    DescribeDomainRecordInfoResponse describeDomainRecordInfoResponse = aliyunConfig.getclient().describeDomainRecordInfo(describeDomainRecordInfoRequest);
    String value = describeDomainRecordInfoResponse.getBody().value;
    return describeDomainRecordInfoResponse.getBody();
  }


  public void changeip(String ip,String rr) throws Exception {
    UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest()
      .setRecordId(recordid)
      .setRR(rr)
      .setType("A")
      .setValue(ip);
    UpdateDomainRecordResponse updateDomainRecordResponse = aliyunConfig.getclient().updateDomainRecord(updateDomainRecordRequest);
    log.info(String.valueOf(updateDomainRecordResponse.toMap()));
  }

  public void updatecache(String domainName) throws Exception {
    UpdateDnsCacheDomainRequest request = new UpdateDnsCacheDomainRequest();
    request.setDomainName(domainName);
    aliyunConfig.getclient().updateDnsCacheDomain(request);
  }


}
