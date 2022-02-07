// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.sample;

import com.aliyun.tea.*;
import com.aliyun.ecs20140526.models.*;
import com.aliyun.tea.utils.StringUtils;
import com.aliyun.teaopenapi.models.*;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AutoJoinECSWhiteList {

    public static final String GET_IP_URL = "http://icanhazip.com,http://ident.me,http://ifconfig.me,http://ipecho.net/plain,http://whatismyip.akamai.com,http://myip.dnsomatic.com";
    
    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @param endpoint
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.ecs20140526.Client createClient(String accessKeyId, String accessKeySecret,String endpoint) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret)
                // 访问的域名
                .setEndpoint(endpoint);
        return new com.aliyun.ecs20140526.Client(config);
    }

    /**
     * 获取本地IP
     * @param url
     * @return
     */
    public static String getIp(String url) {
        String ip = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("getIp响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("getIp响应内容长度为:" + responseEntity.getContentLength());
                ip = EntityUtils.toString(responseEntity).replace("\n","");
                System.out.println("getIp响应内容为:" + ip);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return ip;
    }
    
    public static void main(String[] args_) throws Exception {
        try {
            //循环遍历获取IP的url，查询本机IP
            String[] getIpUrls = GET_IP_URL.split(",");
            String ip = "";
            for(String getIpUrl : getIpUrls){
                ip = getIp(getIpUrl);
                if(!StringUtils.isEmpty(ip)){
                    break;
                }
            }
            if(StringUtils.isEmpty(ip)){
                com.aliyun.teaconsole.Client.log("未获取到IP");
                System.exit(1);
            }
            java.util.List<String> args = java.util.Arrays.asList(args_);
            com.aliyun.ecs20140526.Client client = AutoJoinECSWhiteList.createClient(args.get(4), args.get(5),args.get(6));
            AuthorizeSecurityGroupRequest authorizeSecurityGroupRequest = new AuthorizeSecurityGroupRequest()
                    .setRegionId(args.get(0))
                    .setSecurityGroupId(args.get(1))
                    .setIpProtocol(args.get(2))
                    .setPortRange(args.get(3))
                    .setDescription("自动IP")
                    .setSourceCidrIp(ip);
            AuthorizeSecurityGroupResponse resp = client.authorizeSecurityGroup(authorizeSecurityGroupRequest);
            com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(resp)));
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
