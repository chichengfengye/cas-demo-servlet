package com.tdt.demo.util;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CasServerUtil {
	// 登录服务器地址
	private static final String CAS_SERVER_PATH = "https://sso.tianditu.gov.cn";

	// 登录地址的token
	private static final String GET_TOKEN_URL = CAS_SERVER_PATH + "/v1/tickets";

	public static String getTGT(String tokenUrl, String username, String password, String tdtFrom) {
		try {
			HttpPost httpPost = new HttpPost(tokenUrl);
			CloseableHttpClient client = HttpClients.createDefault();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));
			params.add(new BasicNameValuePair("from", tdtFrom));

			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(httpPost);
            if(null != response) {
            	System.out.println(response.toString());
            }
			Header headerLocation = response.getFirstHeader("Location");
			String location = headerLocation == null ? null : headerLocation.getValue();

			System.out.println("Location：" + location);

			if (location != null) {
				return location.substring(location.lastIndexOf("/") + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 默认线上cas地址
	 * 
	 * @param username
	 * @param password
	 * @param tdtFrom
	 * @return
	 */
	public static String getTGT(String username, String password, String tdtFrom) {
		return getTGT(GET_TOKEN_URL, username, password, tdtFrom);
	}

	/**
	 * 获取TGT
	 */
	public static String getTGT(String username, String password) {
		try {
			HttpPost httpPost = new HttpPost(GET_TOKEN_URL);
			CloseableHttpClient client = HttpClients.createDefault();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(httpPost);

			Header headerLocation = response.getFirstHeader("Location");
			String location = headerLocation == null ? null : headerLocation.getValue();

			System.out.println("Location：" + location);

			if (location != null) {
				return location.substring(location.lastIndexOf("/") + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**默认线上
	 * 获取ST
	 */
	public static String getST(String TGT, String service) {
		return getST(GET_TOKEN_URL, TGT, service);
	}
	/**
	 * 获取ST
	 */
	public static String getST(String tokenUrl, String TGT, String service) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			String url = tokenUrl + "/" + TGT;
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("service", service));
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(httpPost);
			String st = readResponse(response);
			return st == null ? null : (st == "" ? null : st);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取 response body 内容为字符串
	 *
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private static String readResponse(HttpResponse response) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String result = new String();
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}
		return result;
	}

	/**
	 * 创建模拟客户端（针对 https 客户端禁用 SSL 验证）
	 *
	 * @param cookieStore
	 *            缓存的 Cookies 信息
	 * @return
	 * @throws Exception
	 */
	public static CloseableHttpClient createHttpClientWithNoSsl(CookieStore cookieStore) throws Exception {
		// Create a trust manager that does not validate certificate chains
		/*
		 * TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		 * 
		 * @Override public X509Certificate[] getAcceptedIssuers() { return null; }
		 * 
		 * @Override public void checkClientTrusted(X509Certificate[] certs, String
		 * authType) { // don't check }
		 * 
		 * @Override public void checkServerTrusted(X509Certificate[] certs, String
		 * authType) { // don't check } } };
		 * 
		 * SSLContext ctx = SSLContext.getInstance("TLS"); ctx.init(null, trustAllCerts,
		 * null); LayeredConnectionSocketFactory sslSocketFactory = new
		 * SSLConnectionSocketFactory(ctx); return
		 * HttpClients.custom().setSSLSocketFactory(sslSocketFactory)
		 * .setDefaultCookieStore(cookieStore == null ? new BasicCookieStore() :
		 * cookieStore).build();
		 */
		return null;
	}

}
