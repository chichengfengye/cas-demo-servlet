package com.tdt.demo.caslogin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdt.demo.util.CasServerUtil;
import com.tdt.demo.util.FileUtil;

@WebServlet(name = "casLoginServlet", urlPatterns = "/casLogin")
public class CasLogin extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8765399436384103304L;
	//private static Property p = null;
	private static final String CONFIG_FILE = "cas_url.properties";
	private static String casUrl = null;
	private static String clientUrl = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void init() throws ServletException {
		Properties p = FileUtil.getLocalProperAddr(CONFIG_FILE);
		casUrl = p.getProperty("cas.server.url");
		clientUrl = p.getProperty("cas.client.url");
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("casLoginServlet");
	    String username = "user";
	    String password = "123";
	    username = request.getParameter("username").trim();
	    password = request.getParameter("password").trim();
	    String from = request.getParameter("from").trim();
	    String tdtFrom = null;
	    if("zj".equals(from)) {
	    	tdtFrom = "console";
	    }
	    Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		//String url = "https://sso.tianditu.gov.cn:8443/cas/v1/tickets";
		//String service = "http://demo.tianditu.gov.cn:8081/home";
		String tgt = CasServerUtil.getTGT(casUrl, username, password, tdtFrom);
		if(null == tgt || "".equals(tgt)) {
			response.setContentType("charset=utf-8");
			String rt = "{\"msg\" : \"The user does not exist\"}";
			response.getWriter().println(rt);
			return ;
		}
		System.out.println("TGT: " + tgt );
		String st = CasServerUtil.getST(casUrl, tgt, clientUrl);
		System.out.println("ST：" + st);
		String newUrl = clientUrl + "?ticket=" + st;
		System.out.println(newUrl);
		response.sendRedirect(newUrl);
	}
	
	

}
