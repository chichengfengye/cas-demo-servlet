package com.tdt.demo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class CasLoginUtil {
	

	public boolean validateFromCAS(String username, String password) throws Exception {
		String url = "https://sso.tianditu.gov.cn:8443/cas/v1/tickets";
		return validateFromCAS(username, password, url);
	}
	public static boolean validateFromCAS(String username, String password, String url) throws Exception{
		try {
			HttpURLConnection hsu = (HttpURLConnection) openConn(url);
			String s = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
			s += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

			System.out.println(s);
			OutputStreamWriter out = new OutputStreamWriter(hsu.getOutputStream());
			BufferedWriter bwr = new BufferedWriter(out);
			bwr.write(s);
			bwr.flush();
			bwr.close();
			out.close();

			String tgt = hsu.getHeaderField("location");
			System.out.println(hsu.getResponseCode());
			if (tgt != null && hsu.getResponseCode() == 201) {
				System.out.println(tgt);

				System.out.println("Tgt is : " + tgt.substring(tgt.lastIndexOf("/") + 1));
				tgt = tgt.substring(tgt.lastIndexOf("/") + 1);
				bwr.close();
				closeConn(hsu);

				String serviceURL = "http://demo.tianditu.gov.cn/";
				String encodedServiceURL = URLEncoder.encode("service", "utf-8") + "="
						+ URLEncoder.encode(serviceURL, "utf-8");
				System.out.println("Service url is : " + encodedServiceURL);

				String myURL = url + "/" + tgt;
				System.out.println(myURL);
				hsu = (HttpURLConnection) openConn(myURL);
				out = new OutputStreamWriter(hsu.getOutputStream());
				bwr = new BufferedWriter(out);
				bwr.write(encodedServiceURL);
				bwr.flush();
				bwr.close();
				out.close();

				System.out.println("Response code is:  " + hsu.getResponseCode());

				BufferedReader isr = new BufferedReader(new InputStreamReader(hsu.getInputStream()));
				String line;
				System.out.println(hsu.getResponseCode());
				while ((line = isr.readLine()) != null) {
					System.out.println(line);
				}
				isr.close();
				hsu.disconnect();
				return true;

			} else {
				return false;
			}

		} catch (MalformedURLException mue) {
			mue.printStackTrace();
			throw mue;

		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}

	}
	

	static URLConnection openConn(String urlk) throws MalformedURLException, IOException {

		URL url = new URL(urlk);
		HttpURLConnection hsu = (HttpURLConnection) url.openConnection();
		hsu.setDoInput(true);
		hsu.setDoOutput(true);
		hsu.setRequestMethod("POST");
		return hsu;

	}

	static void closeConn(HttpURLConnection c) {
		c.disconnect();
	}
}
