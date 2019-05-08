package com.tdt.demo.caslogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@WebServlet(name = "httpLoginServlet", urlPatterns = "/httpLogin")
public class HttpLogin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8765399436384103304L;

	private static final String USERNAME = "casuser";
	private static final String PASSWORD = "Mellon";
	private static final String CAS_LOGIN_URL = "https://sso.tianditu.gov.cn:8443/cas/login?service=http://demo.tianditu.gov.cn:8081/home";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("httpLoginServlet");
		try {
			login();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void login() throws Exception {
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		try {
			// 访问Server登陆页面获得Cookie
			HttpGet initServerGet = new HttpGet(CAS_LOGIN_URL);
			CloseableHttpResponse response1 = httpclient.execute(initServerGet);
			try {
				HttpEntity entity = response1.getEntity();
				System.out.println("Login form get: " + response1.getStatusLine());
				EntityUtils.consume(entity);
				System.out.println("Initial set of cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}
			} finally {
				response1.close();
			}

			// 获取lt及execution参数
			Map<String, String> map = doCasLoginRequest(httpclient, CAS_LOGIN_URL);
			// 封装参数
			HttpUriRequest login = RequestBuilder.post().setUri(new URI(CAS_LOGIN_URL))
					.addParameter("username", USERNAME).addParameter("password", PASSWORD)
					.addParameter("lt", map.get("lt")).addParameter("_eventId", "submit").addParameter("submit", "登录")
					.addParameter("execution", map.get("execution")).build();
			CloseableHttpResponse response2 = httpclient.execute(login);
			String location = response2.getFirstHeader("Location").getValue();
			System.out.println(location.substring(location.indexOf("?ticket=") + 8));
			Header[] tgtHead = response2.getHeaders("Set-Cookie");

			// 无需重新再请求，否则会导致重定向过多
			HttpGet httpGet = new HttpGet(location);
			httpGet.setHeaders(tgtHead);
			CloseableHttpResponse response3 = httpclient.execute(httpGet);
			System.out.println("请求访问地址状态码: " + response3.getStatusLine());
			String body = EntityUtils.toString(response3.getEntity());
			try {
				HttpEntity entity = response2.getEntity();

				System.out.println("Login form get: " + response2.getStatusLine());
				EntityUtils.consume(entity);

				System.out.println("Post logon cookies:");
				List<Cookie> cookies = cookieStore.getCookies();

				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}

	}

	private Map<String, String> doCasLoginRequest(CloseableHttpClient httpclient, String casLoginUrl) throws Exception {
		Map<String, String> result = new HashMap<String, String>(16);
		HttpGet httpget = new HttpGet(casLoginUrl);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
		String tempLine = rd.readLine();
		String lt = "<input type=\"hidden\" name=\"lt\" value=\"";
		String execution = "<input type=\"hidden\" name=\"execution\" value=\"";
		while (tempLine != null) {
			int lt_index = tempLine.indexOf(lt);
			if (lt_index != -1) {
				String s1 = tempLine.substring(lt_index + lt.length());
				int index1 = s1.indexOf("\"");
				if (index1 != -1) {
					result.put("lt", s1.substring(0, index1));
				}
			}
			int execution_index = tempLine.indexOf(execution);
			if (execution_index != -1) {
				String s1 = tempLine.substring(execution_index + execution.length());
				int index1 = s1.indexOf("\"");
				if (index1 != -1) {
					result.put("execution", s1.substring(0, index1));
				}
			}
			tempLine = rd.readLine();
		}
		if (entity != null) {
			entity.consumeContent();
		}
		return result;

	}

}
