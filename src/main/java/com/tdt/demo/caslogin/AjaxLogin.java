package com.tdt.demo.caslogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ajaxLoginServlet", urlPatterns = "/ajaxLogin")
public class AjaxLogin extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8765399436384103304L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ajaxLoginServlet");
	   response.sendRedirect("/ajax.jsp");
	}
	
	

}
