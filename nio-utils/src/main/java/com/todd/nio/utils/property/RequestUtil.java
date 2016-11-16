package com.todd.nio.utils.property;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.todd.nio.utils.message.MsgContext;

public class RequestUtil {
	
	
	public static String invoke(String method, String remoteUrl, String pro, String charSet) {
		pro = StringUtil.isNotEmpty(pro) ? "" : pro;
		try {
			URL url = new URL(remoteUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod(method);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			OutputStream os = connection.getOutputStream();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(pro.toString().getBytes(charSet));
			out.flush();
			String line;
			StringBuilder sb = new StringBuilder();
			String returnData = null;
			if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), charSet));
				while ((line = reader.readLine()) != null) {
					sb.append(line.trim());
				}
				returnData = sb.toString();
			} /*else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getErrorStream(), charSet));
				while ((line = reader.readLine()) != null) {
					sb.append(line.trim());
				}
				returnData = sb.toString();
				}*/
			connection.getHeaderField("Set-Cookie");
			os.close();
			out.close();
			return returnData;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String doGet(String url, String para, String charSet) {
		return invoke("GET", url, para, charSet);
	}
	
	public static String doPost(String url, String para, String charSet) {
		return invoke("POST", url, para, charSet);
	}
	
	public static String getPara(Map<String, Object> map) {
		StringBuffer buf = new StringBuffer("");
		if (map != null) {
			for (Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = String.valueOf(entry.getValue());
				buf.append(key + "=" + value + "&");
			}
		}
		return buf.substring(0, buf.length() - 1);
	}
	
	public static String getRealRemoteIp(MsgContext in) {
		return getRealRemoteIp(in.getRequest());
	}
	
	public static String getRealRemoteIp(HttpServletRequest request) {
		if (null == request) {
			return null;
		}
		try {
			//第一步首先获取代理类传过来的用户真实IP,如果有，直接返回
			//			String ip = request.getHeader("proxy-send-client-ip");
			//			if(ip != null && ip.length() > 0){
			//				logger.info("proxy-send-client-ip=" + ip);
			//				return ip;
			//			}
			//接下来，获取Squid透传过来的ip
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Real-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			if (!(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))) {
				int dotIdx = ip.indexOf(",");
				if (dotIdx == -1) {
					//squid
					//透传过来的IP以一个空格分隔，第二个固定为客户的真实IP
					String[] ipToken = ip.split(" ");
					if (ipToken.length > 1) {
						return ipToken[1];
					}
				} else {
					//squid,nginx
					//透传过来的IP以一个,分隔，第一个固定为客户的真实IP
					String[] ipToken = ip.split(",");
					if (ipToken.length > 1) {
						return ipToken[0];
					}
				}
				return ip;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String DoStaticGet(String remoteUrl, String CharSet) {
		try {
			
			URL u = new URL(remoteUrl);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			String line;
			StringBuilder sb = new StringBuilder();
			String returnData = null;
			if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line.trim());
				}
				returnData = sb.toString();
			}
			return returnData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
