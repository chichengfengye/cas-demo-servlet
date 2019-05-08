package com.tdt.demo.console;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "consoleLoginServlet", urlPatterns = "/console")
public class ConsoleServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1199502871591844623L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/console.jsp");
	}
	
	

}
