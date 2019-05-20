package com.tdt.demo.caslogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 4586552868767145513L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("loginServlet");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

//		if("casuser".equals(username) && "Mellon".equals(password)) {
        System.out.println("用户已登录");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/home.jsp");

	/*	} else {
			response.sendRedirect("/login.jsp");
		}*/

    }

}
