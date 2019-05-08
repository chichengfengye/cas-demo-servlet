package com.tdt.demo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	static HttpClient httpClient = HttpClients.createDefault();

	private static HttpResponse invoke(HttpUriRequest httpRqeust) {
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpRqeust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 澶勭悊涓婁紶鏂囦欢
	 * 
	 * @param url
	 * @param params
	 * @param b
	 * @return
	 */
	public static String MethodPost(String url, Map<String, String> params,
			byte[] b) {
		HttpPost httpPost = new HttpPost(url);
		MultipartEntityBuilder meb = MultipartEntityBuilder.create();
		meb.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			meb.addTextBody(key, params.get(key),ContentType.TEXT_PLAIN.withCharset("UTF-8"));
		}
		ContentBody cb = new ByteArrayBody(b,"file");//
		FormBodyPartBuilder fbpb = FormBodyPartBuilder.create();
		fbpb.setBody(cb);
		fbpb.setName("xmlFile");
		meb.addPart(fbpb.build());
		//meb.addBinaryBody("xmlFile", b);  //?? bug?鍐呴儴ByteArrayBody涓璮ilename涓簄ull(瀹為檯涓嶈兘涓簄ull)
		HttpEntity he = meb.build();
		httpPost.setEntity(he);
		HttpResponse response = invoke(httpPost);
		return parse(response);
	}

	/**
	 * 鏅�歱ost璇锋眰
	 * 
	 * @param params
	 */
	public static String MethodPost(String url, Map<String, String> params) {
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		HttpEntity he = null;
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			he = new UrlEncodedFormEntity(nvps, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpPost.setEntity(he);
		HttpResponse response = invoke(httpPost);
		return parse(response);
	}

	public static String MethodGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = invoke(httpGet);
		return parse(response);
	}

	private static String parse(HttpResponse response) {
		HttpEntity entity = response.getEntity();
		String s = null;
		try {
			s = EntityUtils.toString(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	public void close() {

	}
}
