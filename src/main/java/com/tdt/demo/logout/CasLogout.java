package com.tdt.demo.logout;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdt.demo.util.FileUtil;

@WebServlet(name = "logoutServlet", urlPatterns = "/casLogout")
public class CasLogout extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -637853622612836130L;
	private static String casLogoutUrl = null;
	private static Properties p = null;
	private static final String CONFIG_FILE = "cas_url.properties";
	@Override
	public void init() throws ServletException {
		p = FileUtil.getLocalProperAddr(CONFIG_FILE);
		casLogoutUrl = p.getProperty("cas.server.logout.url");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(casLogoutUrl);
	}

	
	

}
