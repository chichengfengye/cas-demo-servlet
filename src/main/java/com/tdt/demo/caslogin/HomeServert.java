package com.tdt.demo.caslogin;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.authentication.AttributePrincipal;

@WebServlet(name = "homeServlet", urlPatterns = "/home")
public class HomeServert extends  HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1638006347816892047L;

	@Override
	public void doGet( HttpServletRequest request,  HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("homeServlet");

		AttributePrincipal principal = (AttributePrincipal) request
				.getUserPrincipal();
		
        if(null == principal){
        	System.out.println("用户数据为空");
        } else {
        	System.out.println("用户数据不为空");
        }
        Map<String, Object> attrs = principal.getAttributes();
		Set<String> sets = attrs.keySet();
		if(sets.size() > 0) {
			System.out.println("用户属性数据为");
		} else {
			System.out.println("用户属性数据为空");
		}
		for(String key : sets) {
			System.out.println(key);
		}
		String value = (String)attrs.get("login_name");
		System.out.println(value);
		String name = principal.getName();
		User user = new User();
		user.setUsername(name);
		user.setPassword("Mellon");
		request.getSession().setAttribute("user", user);
		System.out.println("username = " + name);
		response.sendRedirect("/home.jsp");
	}
}
