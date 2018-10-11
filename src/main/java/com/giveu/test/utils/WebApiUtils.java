package com.giveu.test.utils;

import com.giveu.test.enums.WebApi;
import net.sf.json.JSONArray;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @title：rabbitmq web api
 * @author：xuan
 * @date：2018/10/11
 */
public class WebApiUtils {

	private final static String REST_API = "http://172.16.100.132:15672/api/";
	private final static String USER_NAME = "guest";
	private final static String PASSWORD = "guest";

	/**
	 * @title：获取rabbitmq_webapi结果
	 * @author：xuan
	 * @date：2018/10/11
	 */
	public static JSONArray getApiResult(WebApi api) {
		String url = REST_API + api.getValue();

		//设置凭证
		Map<String, Object> headMap = new HashMap<>();
		String up = USER_NAME + ":" + PASSWORD;
		String credentials = null;
		try {
			credentials = new BASE64Encoder().encode(up.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		headMap.put("Authorization", "Basic " + credentials);

		String result = HttpConnectUtil.doGetWithHeader(url, headMap);
		JSONArray json = JSONArray.fromObject(result);
		return json;
	}

	public static void main(String[] args) {
		System.out.println(getApiResult(WebApi.EXCHANGE_LIST));
	}

}
