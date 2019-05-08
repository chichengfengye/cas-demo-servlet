package com.tdt.demo.caslogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.tdt.demo.common.Content;


@WebServlet(name = "gitServlet", urlPatterns = "/gitLogin")
public class GitLogin extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = -8765399436384103304L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("gitServlet");

		 String clientName = "tdt";
		 String requestUrl = null;
		 try {
		     OAuthClientRequest accessTokenRequest = OAuthClientRequest
		      .authorizationLocation(Content.GIT_CODE_URL)
		      //.setResponseType(Content.RESPONSE_TYPE)
		      .setState("state")
		      .setClientId(Content.GIT_CLIENT_ID)
		      .setRedirectURI(Content.REDIRECT_URL)
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
