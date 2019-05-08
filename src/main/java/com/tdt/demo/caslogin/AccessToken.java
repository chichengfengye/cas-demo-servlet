package com.tdt.demo.caslogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.tdt.demo.common.Content;
import com.tdt.demo.util.HttpUtil;


@WebServlet(name = "casLogin3Servlet", urlPatterns = "/getToken")
public class AccessToken extends HttpServlet {

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

		System.out.println("casLogin3Servlet");


		//tdtToken(request, response);
		gitToken(request, response);
		

	}

	private void gitToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("................githup获得token............................");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		code = httpRequest.getParameter("code");
		System.out.println(code);
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		String accessToken = null;
		try {
			OAuthClientRequest accessTokenRequest = OAuthClientRequest
					.tokenLocation(Content.GIT_TOKEN_URL)
					//.setGrantType(GrantType.AUTHORIZATION_CODE)
					.setClientId(Content.GIT_CLIENT_ID)
					.setClientSecret(Content.GIT_CLIENT_SECRET)
					.setCode(code)
					.setRedirectURI(Content.GIT_CALLBACK)
					//.setScope("all")
					.buildQueryMessage();
			String tokenUrl = accessTokenRequest.getLocationUri();
			System.out.println("token url = " + tokenUrl);
			// 去服务端请求access token，并返回响应
			//OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.GET);
			// 获取服务端返回过来的access token
			//accessToken = oAuthResponse.getAccessToken();
			accessToken = HttpUtil.MethodGet(tokenUrl);
			// 查看access token是否过期
			//Long expiresIn = oAuthResponse.getExpiresIn();
			System.out.println("token = " + accessToken);
			System.out.println(
			"-----------客户端/callbackCode--------------------------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获得用户信息
		String userUrl = Content.GIT_USER_INFO_URL + "?" + accessToken;
		System.out.println("userUrl : " + userUrl);
		response.sendRedirect(userUrl);
		
	}

	public void tdtToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("................tdt获得token............................");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		code = httpRequest.getParameter("code");
		System.out.println(code);
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		String accessToken = null;
		try {
			OAuthClientRequest accessTokenRequest = OAuthClientRequest
					.tokenLocation(Content.TDT_TOKEN_URL)
					.setGrantType(GrantType.AUTHORIZATION_CODE)
					.setClientId(Content.TDT_CLIENT_ID)
					.setClientSecret(Content.GIT_CLIENT_SECRET)
					.setCode(code)
					.setRedirectURI(Content.REDIRECT_URL)
					//.setScope("all")
					.buildQueryMessage();
			System.out.println("token url = " + accessTokenRequest.getLocationUri());
			// 去服务端请求access token，并返回响应
			OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
			// 获取服务端返回过来的access token
			accessToken = oAuthResponse.getAccessToken();
			// 查看access token是否过期
			//Long expiresIn = oAuthResponse.getExpiresIn();
			System.out.println("token = " + accessToken);
			System.out.println(
			"-----------客户端/callbackCode--------------------------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获得用户信息
		String userUrl = Content.TDT_USER_INFO_URL + "?access_token="+accessToken;
		response.sendRedirect(userUrl);
		
	}

}
