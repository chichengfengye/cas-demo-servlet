package com.tdt.demo.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CasLoginUtilTest {
	
	
	@Test
	public void testLogin() throws Exception {
		String username = "casuser";
		String password = "Mellon";
		String url = "https://sso.tianditu.gov.cn:8443/cas/v1/tickets";
		boolean b = CasLoginUtil.validateFromCAS(username, password, url);
		System.out.println(b);
	}
	

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	

}
