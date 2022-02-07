# 在官方文档上做了一些小修改
- 获取本地连接的IP
- bat脚本（暂时没做shell）
--java -jar autojoinEcsWhiteList.jar regionId securityGroupId ipProtocol portRange accessKeyId accessKeySecret endpoint


<figure><table>
<thead>
<tr><th>名称</th><th>类型</th><th>必填</th><th>描述</th><th>示例值及参考API</th></tr></thead>
<tbody><tr><td>RegionId</td><td>String</td><td>是</td><td>安全组所属地域ID。您可以调用<a href='https://next.api.aliyun.com/document/Ecs/2014-05-26/DescribeRegions'>DescribeRegions</a>查看最新的阿里云地域列表。</td><td>示例值：cn-hangzhou</td></tr><tr><td>SecurityGroupId</td><td>String</td><td>是</td><td>目的端安全组ID。</td><td>&nbsp;</td></tr><tr><td>IpProtocol</td><td>String</td><td>是</td><td>传输层协议。取值大小写敏感。<br>取值范围：<br>tcp<br>udp<br>icmp<br>gre<br>all：支持所有协议<br>此处icmp协议仅支持IPv4地址。</td><td>示例值：tcp</td></tr><tr><td>PortRange</td><td>String</td><td>是</td><td>目的端安全组开放的传输层协议相关的端口范围。<br>取值范围：<br>TCP/UDP协议：取值范围为1~65535。使用斜线（/）隔开起始端口和终止端口。例如：1/200<br>ICMP协议：-1/-1<br>GRE协议：-1/-1<br>IpProtocol取值为all：-1/-1<br>了解端口的应用场景，请参见<a href='https://help.aliyun.com/document_detail/40724.html'>典型应用的常用端口</a>。</td><td>示例值: 9000/9000</td></tr><tr><td>accessKeyId</td><td>String</td><td>是</td><td>AccessKey ID和AccessKey Secret是您访问阿里云API的密钥</td><td>&nbsp;</td></tr><tr><td>accessKeySecret</td><td>String</td><td>是</td><td>AccessKey ID和AccessKey Secret是您访问阿里云API的密钥</td><td>&nbsp;</td></tr><tr><td>endpoint</td><td>String</td><td>是</td><td>访问的域名</td><td>例如：ecs.cn-hangzhou.aliyuncs.com</td></tr></tbody>
</table></figure>
<p>&nbsp;</p>






| 名称            | 类型   | 必填 | 描述                                                         | 示例值及参考API                    |
| --------------- | ------ | ---- | ------------------------------------------------------------ | ---------------------------------- |
| RegionId        | String | 是   | 安全组所属地域ID。您可以调用[DescribeRegions](https://next.api.aliyun.com/document/Ecs/2014-05-26/DescribeRegions)查看最新的阿里云地域列表。 | 示例值：cn-hangzhou                |
| SecurityGroupId | String | 是   | 目的端安全组ID。                                             |                                    |
| IpProtocol      | String | 是   | 传输层协议。取值大小写敏感。<br />取值范围：<br />tcp<br />udp<br />icmp<br />gre<br />all：支持所有协议<br />此处icmp协议仅支持IPv4地址。 | 示例值：tcp                        |
| PortRange       | String | 是   | 目的端安全组开放的传输层协议相关的端口范围。<br />取值范围：<br />TCP/UDP协议：取值范围为1~65535。使用斜线（/）隔开起始端口和终止端口。例如：1/200<br />ICMP协议：-1/-1<br />GRE协议：-1/-1<br />IpProtocol取值为all：-1/-1<br />了解端口的应用场景，请参见[典型应用的常用端口](https://help.aliyun.com/document_detail/40724.html)。 | 示例值: 9000/9000                  |
| accessKeyId     | String | 是   | AccessKey ID和AccessKey Secret是您访问阿里云API的密钥        |                                    |
| accessKeySecret | String | 是   | AccessKey ID和AccessKey Secret是您访问阿里云API的密钥        |                                    |
| endpoint        | String | 是   | 访问的域名                                                   | 例如：ecs.cn-hangzhou.aliyuncs.com |




# 下面是官方文档
# 增加一条入方向安全组规则文档示例

该项目为指定安全组入方向的访问权限，允许或者拒绝其他设备发送入方向流量到安全组里的实例。文档示例，该示例**无法在线调试**，如需调试可下载到本地后替换 [AK](https://usercenter.console.aliyun.com/#/manage/ak) 以及参数后进行调试。

## 运行条件

- 下载并解压需要语言的代码;

- 在阿里云帐户中获取您的 [凭证](https://usercenter.console.aliyun.com/#/manage/ak)并通过它替换下载后代码中的 ACCESS_KEY_ID 以及 ACCESS_KEY_SECRET;

- 执行对应语言的构建及运行语句

## 执行步骤
下载的代码包，在根据自己需要更改代码中的参数和 AK 以后，可以在**解压代码所在目录下**按如下的步骤执行

- Java
*JDK 版本要求 1.8*
```sh
mvn clean package
java -jar target/sample-1.0.0-jar-with-dependencies.jar
```
```

## 使用的 API

-  AuthorizeSecurityGroup 指定安全组入方向的访问权限，允许或者拒绝其他设备发送入方向流量到安全组里的实例。文档示例，可以参考：[文档](https://next.api.aliyun.com/document/Ecs/2014-05-26/AuthorizeSecurityGroup)



## 返回示例

*实际输出结构可能稍有不同，属于正常返回；下列输出值仅作为参考，以实际调用为准*


- JSON 格式 
```js
{
    "RequestId":"CEF72CEB-54B6-4AE8-B225-F876FF7BA984"
}
```
- XML 格式 
```xml
<AuthorizeSecurityGroupResponse>
    <RequestId>CEF72CEB-54B6-4AE8-B225-F876FF7BA984</RequestId>
</AuthorizeSecurityGroupResponse>
```


