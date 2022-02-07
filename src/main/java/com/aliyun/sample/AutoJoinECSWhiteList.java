// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.sample;

import com.aliyun.ecs20140526.Client;
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
import java.util.List;

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
    public static Client createClient(String accessKeyId, String accessKeySecret, String endpoint) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret)
                // 访问的域名
                .setEndpoint(endpoint);
        return new Client(config);
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

            InParam inParam = new InParam(args_);
            com.aliyun.ecs20140526.Client client = AutoJoinECSWhiteList.createClient(inParam.getAccessKeyId(), inParam.getAccessKeySecret()
                    ,inParam.getEndpoint());
            //1.获取目前存在的入方向规则
            DescribeSecurityGroupAttributeRequest describeSecurityGroupAttributeRequest = new DescribeSecurityGroupAttributeRequest()
                    .setSecurityGroupId(inParam.getSecurityGroupId())
                    .setRegionId(inParam.getRegionId());
            // 复制代码运行请自行打印 API 的返回值
            DescribeSecurityGroupAttributeResponse response = client.describeSecurityGroupAttribute(describeSecurityGroupAttributeRequest);
            DescribeSecurityGroupAttributeResponseBody body = response.getBody();
            DescribeSecurityGroupAttributeResponseBody.DescribeSecurityGroupAttributeResponseBodyPermissions permissions = body.getPermissions();
            List<DescribeSecurityGroupAttributeResponseBody.DescribeSecurityGroupAttributeResponseBodyPermissionsPermission> permissionList = permissions.getPermission();
            for (DescribeSecurityGroupAttributeResponseBody.DescribeSecurityGroupAttributeResponseBodyPermissionsPermission permission : permissionList) {
                //判断条件 当描述和 端口范围都相同时，表示为同一条入方向规则
                if (inParam.getDescription().equals(permission.getDescription())
                && inParam.getPortRange().equals(permission.getPortRange())) {
                    if(ip.equals(permission.getSourceCidrIp())){
                        com.aliyun.teaconsole.Client.log("无需新增");
                        //无需新增规则
                        System.exit(0);
                    }else{
                        //2.删除失效的入方向规则
                        if(!remove( inParam,  permission.getSourceCidrIp(),  client)){
                            //删除失败-比较严重的问题，需要人工手动排查
                            com.aliyun.teaconsole.Client.log("删除失败");
                        }
                    }
                }
            }
            //3.加入当前的IP的入方向规则
            System.exit(join( inParam, ip,client) ? 0 : 1);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 删除入方向规则
     * @param inParam
     * @param delIp
     * @param client
     * @return
     */
    public static Boolean remove(InParam inParam, String delIp, com.aliyun.ecs20140526.Client client) {
        //2.删除失效的入方向规则
        RevokeSecurityGroupRequest revokeSecurityGroupRequest = new RevokeSecurityGroupRequest()
                .setSecurityGroupId(inParam.getSecurityGroupId())
                .setRegionId(inParam.getRegionId())
                .setPortRange(inParam.getPortRange())
                .setIpProtocol(inParam.getIpProtocol())
                .setSourceCidrIp(delIp);
        // 复制代码运行请自行打印 API 的返回值
        try {
            RevokeSecurityGroupResponse response = client.revokeSecurityGroup(revokeSecurityGroupRequest);
            com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(response)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 加入入方向规则
     * @param inParam
     * @param ip
     * @return
     * @throws Exception
     */
    public static Boolean join(InParam inParam,String ip,com.aliyun.ecs20140526.Client client) throws Exception {
        AuthorizeSecurityGroupRequest authorizeSecurityGroupRequest = new AuthorizeSecurityGroupRequest()
                .setRegionId(inParam.getRegionId())
                .setSecurityGroupId(inParam.getSecurityGroupId())
                .setIpProtocol(inParam.getIpProtocol())
                .setPortRange(inParam.getPortRange())
                .setDescription(inParam.getDescription())
                .setSourceCidrIp(ip);
        AuthorizeSecurityGroupResponse resp = client.authorizeSecurityGroup(authorizeSecurityGroupRequest);
        com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(resp)));
        return true;
    }
}
