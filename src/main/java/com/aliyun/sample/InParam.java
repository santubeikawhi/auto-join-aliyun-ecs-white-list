package com.aliyun.sample;

public class InParam {
    
    private String regionId;
    
    private String securityGroupId;
    
    private String ipProtocol;
    
    private String portRange;

    private String description;
    
    private String accessKeyId;

    private String accessKeySecret;
    
    private String endpoint;

    public InParam(String[] args_) {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        this.regionId = args.get(0);
        this.securityGroupId = args.get(1);
        this.ipProtocol = args.get(2);
        this.portRange = args.get(3);
        this.description = args.get(4);
        this.accessKeyId = args.get(5);
        this.accessKeySecret = args.get(6);
        this.endpoint = args.get(7);
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public void setSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    public String getIpProtocol() {
        return ipProtocol;
    }

    public void setIpProtocol(String ipProtocol) {
        this.ipProtocol = ipProtocol;
    }

    public String getPortRange() {
        return portRange;
    }

    public void setPortRange(String portRange) {
        this.portRange = portRange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
