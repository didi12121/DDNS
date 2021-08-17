package com.didi.ddns.http;

import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.DescribeDomainRecordInfoRequest;
import com.aliyun.alidns20150109.models.DescribeDomainRecordInfoResponse;
import com.aliyun.alidns20150109.models.UpdateDomainRecordRequest;
import com.aliyun.alidns20150109.models.UpdateDomainRecordResponse;
import com.didi.ddns.config.AliyunConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Aliyun {

  @Resource
  AliyunConfig aliyunConfig;
  @Value("${recordid}")
  String recordid;

  public String getnowip() throws Exception {
    DescribeDomainRecordInfoRequest describeDomainRecordInfoRequest = new DescribeDomainRecordInfoRequest()
      .setRecordId(recordid);
    DescribeDomainRecordInfoResponse describeDomainRecordInfoResponse = aliyunConfig.getclient().describeDomainRecordInfo(describeDomainRecordInfoRequest);
    String value = describeDomainRecordInfoResponse.getBody().value;
    return value;
  }


  public void changeip(String ip,String rr) throws Exception {
    UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest()
      .setRecordId(recordid)
      .setRR(rr)
      .setType("A")
      .setValue(ip);
    UpdateDomainRecordResponse updateDomainRecordResponse = aliyunConfig.getclient().updateDomainRecord(updateDomainRecordRequest);
  }



}
