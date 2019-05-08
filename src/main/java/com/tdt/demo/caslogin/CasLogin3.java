package com.tdt.demo.caslogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;


@WebServlet(name = "tokenServlet", urlPatterns = "/casLogin3")
public class CasLogin3 extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = -8765399436384103304L;

	String clientId = null;
	String clientSecret = null;
	String accessTokenUrl = null;
	String userInfoUrl = null;
	String redirectUrl = null;
	String response_type = null;
	String code = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("tokenServlet");
		 //clientId = "merryyou";
		 //clientSecret = "merryyou";
		 clientId = "8dd80e47eb9f9f278afd";
		 clientSecret = "e6df75b0cef7e77b18e049ccabcadb2bd479dd65";
		 String authUrl = "http://auth.tdt.com:8888/oauth/authorize";
		 accessTokenUrl = "http://auth.tdt.com:8888/oauth/token";
		 redirectUrl = "http://demo.tianditu.gov.cn:8081/getToken";
		 response_type = "code";
		 String clientName = "tdt";
		 String requestUrl = null;
		 try {
		     OAuthClientRequest accessTokenRequest = OAuthClientRequest
		      .authorizationLocation(authUrl)
		      .setResponseType(response_type)
		      .setClientId(clientId)
		      .setRedirectURI(redirectUrl)
		      .buildQueryMessage();
		      requestUrl = accessTokenRequest.getLocationUri();
		 } catch (OAuthSystemException e) {
		  e.printStackTrace();
		 }
		 System.out.println("-------------------");
		 System.out.println(requestUrl + "&client_name="+clientName);
		 System.out.println("-------------------");
		 response.sendRedirect(requestUrl);
		 
	}

}
