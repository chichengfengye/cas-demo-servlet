package com.tdt.demo.common;

public class Content {
	
	/**
	 * code
	 */
	public static final String RESPONSE_TYPE = "code";
	/**
	 * redirect url
	 */
	public static final String REDIRECT_URL = "http://demo.tianditu.gov.cn:8081/getToken";
	/**
	 * GIT app name
	 */
	public static final String GIT_APP_NAME = "mygit";
	/**
	 * GIT app name
	 */
	public static final String GIT_STATE = "STATE";
	/**
	 * git id
	 */
	public static final String GIT_CLIENT_ID = "8dd80e47eb9f9f278afd"; 
	/**
	 * git secrit
	 */
	public static final String GIT_CLIENT_SECRET = "e6df75b0cef7e77b18e049ccabcadb2bd479dd65"; 
	/**
	 * our callback
	 */
	public static final String GIT_CALLBACK = REDIRECT_URL; // TODO 修改成自己的 [注意：callback要和注册的回调路径保持一致 否则登录授权之后会报NullPointerException]

	/**
	 * git code
	 */
	public static final String GIT_CODE_URL = "https://github.com/login/oauth/authorize";
	/**
	 * git token
	 */
	public static final String GIT_TOKEN_URL = "https://github.com/login/oauth/access_token";
	/**
	 * git user info 
	 */
	public static final String GIT_USER_INFO_URL = "https://api.github.com/user";
	
	//-------------------------------tdt-------------------------------------//
	public static final String TDT_CLIENT_ID = "merryyou"; 
	public static final String TDT_CLIENT_SECRET = "merryyou";
	public static final String TDT_CALLBACK = REDIRECT_URL; 
	
	//http://auth.tdt.com:8888/oauth/authorize?client_id=CLIENT_ID&state=STATE&redirect_uri=CALLBACK
	//http://auth.tdt.com:8888/oauth/token?client_id=CLIENT_ID&client_secret=CLIENT_SECRET&code=CODE&redirect_uri=CALLBACK
	//http://auth.tdt.com:8888/userJwt?access_token=xx
	// 获取code的url
	public static final String TDT_CODE_URL = "http://auth.tdt.com:8888/oauth/authorize";
	// 获取token的url
	public static final String TDT_TOKEN_URL = "http://auth.tdt.com:8888/oauth/token";
	// 获取用户信息的url
	public static final String TDT_USER_INFO_URL = "http://auth.tdt.com:8888/userJwt";
	
	
	
	

}
