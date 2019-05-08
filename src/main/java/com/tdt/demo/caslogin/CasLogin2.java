package com.tdt.demo.caslogin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdt.demo.util.HttpUtil;


@WebServlet(name = "casLogin2Servlet", urlPatterns = "/casLogin2")
public class CasLogin2 extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8765399436384103304L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("casLogin2Servlet");
	    String username = "casuser";
	   // String password = "Mellon";
		//String url = "https://sso.tianditu.gov.cn:8443/cas/v1/tickets";
	    String url = "https://sso.tianditu.gov.cn:8443/cas/hugeAutoLogin";
	    String service = "http://demo.tianditu.gov.cn:8081/home";
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("service", service);
		map.put("sign", "11");
		System.out.println("url = " + url);
		String s = HttpUtil.MethodPost(url, map);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap m = objectMapper.readValue(s, HashMap.class);
		String newUrl = (String)m.get("url");
		System.out.println(newUrl);
		response.sendRedirect(url);
	}
	
	

}
