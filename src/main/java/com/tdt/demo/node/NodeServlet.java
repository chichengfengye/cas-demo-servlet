package com.tdt.demo.node;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 这个是用来给省市用户访问用的，内部逻辑如下</br>
 * <li>根据省市提供的用户名进行查询</li>
 * <li>查询到跳转oauth2服务登录</li>
 * <li>没有查询到跳转到注册</li>
 * @author ack
 *
 */

@WebServlet(name = "nodeServlet", urlPatterns = "/nodeLogin")
public class NodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2688979709361786347L;
	private static final String USER_REGISTER_URL = "";
	private static final String CONSOLE_SEVICE = "";
	private static final String OAUTH_URL = "";
	private static final String CAS_URL = "https://cas.example.org:8443/cas/login?username=admin&service=http://demo.tianditu.gov.cn:8081/home";
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").trim();
		String url = "";
		//查询数据库,这里直接判断
		if("admin".equals(username)) {
			// 跳转oauth登录
			url = CAS_URL;
		} else {
			//跳转注册
			url = USER_REGISTER_URL;
		}
		response.sendRedirect(url);
		
	
	}
	

}
