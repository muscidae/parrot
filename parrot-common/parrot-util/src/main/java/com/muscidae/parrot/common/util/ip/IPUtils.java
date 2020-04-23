package com.muscidae.parrot.common.util.ip;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @author hubin
 * @date 2014-5-8
 * @description IP工具类
 */
public final class IPUtils {

    private IPUtils(){ }

	/**
	 * 获取用户真实IP地址
	 */
    public String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(null == ipAddress || 0 == ipAddress.length() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(null == ipAddress || 0 == ipAddress.length() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(null == ipAddress || 0 == ipAddress.length() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)){
                //根据网卡取本机配置的IP
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= Objects.requireNonNull(inetAddress).getHostAddress();
            }
        }
        //对于通过多个代理的情况, 第一个IP为客户端真实IP,多个IP按照','分割
        if(null != ipAddress && 15 < ipAddress.length()) //"***.***.***.***".length() = 15
            if(0 < ipAddress.indexOf(","))
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        return ipAddress;
    }

    public enum Singleton{
        INSTANCE;
        private IPUtils ipUtils;
        Singleton(){ ipUtils = new IPUtils(); }
        public IPUtils newInstance(){ return ipUtils; }
    }
	
}
