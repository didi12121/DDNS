package com.didi.ddns.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class IP {
  /**
   * 获得公网ip
   * @return
   */
  public static String getPublicIPAddress(){
    String re  = "";
    OkHttpClient client = new OkHttpClient().newBuilder()
      .build();
    Request request = new Request.Builder()
      .url("https://api.ipify.org/")
      .method("GET", null)
      .build();
    try {
      Response response = client.newCall(request).execute();
      re=response.body().string();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return re;
  }
}
