# DDNS
#### 基于spring的用于阿里云域名动态绑定ip

因为在外办公偶尔会需要远程桌面到家里面的电脑，向日葵限制带宽，FRP也受限于服务器的带宽，VPS大带宽服务器的延迟有太高了。

正巧家里的宽带是有公网IP的300M宽带所以考虑是否可以使用公网IP做端口转发将对应的请求通过路由器转发对应机器的3389端口达到远程桌面的效果

<u>\~\~(这都是上学的时候玩剩下的😂）\~\~</u>



### 接下来讲讲怎么用（别问为啥不用花生壳等DDNS产品，问了就是要钱。。）

首先要有一个备案过的阿里云的域名(别问为啥是阿里的。。因为我用的阿里的文档😂)

然后打开阿里云的控制台...

拿到ak(最好看着阿里的文档来....这里就不赘述了)

##### 最重要的来了

##### 1、在你的域名新建一个二级域名（要用以及域名也行。。。）解析到随便一个ip（等下项目启动之后会自动更新的。。）

##### 2、通过<a href = 'https://next.api.aliyun.com/api/Alidns/2015-01-09/DescribeDomainRecords'>**阿里接口在线调试工具**</a> 调用 调试 DescribeDomainRecordInfo这个接口获得返回结果根据文档取出红框的RecordId和RR填入application.properties对应的字段中。

![image-20210817230719075](https://www.didi1390.xyz/file/20210817/4617ccc3da47452ea5a582164de3d02e.png)



```.properties
server.port=8088
recordid = #RecordId
accessKeyId = accessKeyId
accessKeySecret = accessKeySecret
rr = #rr
```

填入对应的字段，启动项目。定时器会半小时检查一次当前的公网ip是否与指定的域名绑定的ip一样。如果不一样就会修改。

![image-20210817231530052](https://www.didi1390.xyz/file/20210817/8d51eb8531414ad69be66b1403b14e0c.png)

可以比对一下，用cmd ping一下域名的ip是否和当前设备的公网ip一致。


### =====结束了======
