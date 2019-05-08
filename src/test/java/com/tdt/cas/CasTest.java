package com.tdt.cas;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.tdt.demo.util.HttpUtil;

public class CasTest {
	
	String url = "https://sso.tianditu.gov.cn:8443/cas/v1/tickets";

	@Test
	public void testLogin() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "casuser");
		map.put("password", "Mellon");
		String url = "https://sso.tianditu.gov.cn:8443/cas/v1/tickets";
		String test = HttpUtil.MethodPost(url, map);

		System.out.println(test);
	}
	
	public void testConn() {
		
	}

	public void main(String[] args) throws Exception {
		String username = "casuser";
		String password = "Mellon";
		//validateFromCAS(username, password);
	}

}
