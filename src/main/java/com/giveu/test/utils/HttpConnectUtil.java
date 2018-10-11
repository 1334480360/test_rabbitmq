package com.giveu.test.utils;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @title：httpclient工具类
 * @author：xuan
 * @date：2018/2/28
 */
public class HttpConnectUtil {

	/**
	 * 请求编码
	 */
	public static String ENCODING_UTF8 = "UTF-8";

	public static final int DEFAULT_TIME_OUT = 8000;

	/**
	 * <pre>
	 * 发送带参数的POST的HTTP请求
	 * </pre>
	 * 
	 * @param reqUrl HTTP请求URL
	 * @param parameters 参数映射表
	 * @return HTTP响应的字符串
	 */
	public static String doPost(String reqUrl, Map parameters, String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
				Entry element = (Entry) iter.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(), HttpConnectUtil.ENCODING_UTF8));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			System.out.println(reqUrl + "?" + params.toString());
			url_con.setConnectTimeout(50000);// （单位：毫秒）jdk
			// 1.5换成这个,连接超时
			url_con.setReadTimeout(50000);// （单位：毫秒）jdk 1.5换成这个,读操作超时
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (Exception e) {
			System.out.println("doPost.url:" + reqUrl + ",error:" + e.getMessage());
//			e.printStackTrace();
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}
	/**
	 * <pre>
	 * 发送带参数的POST的HTTP请求
	 * </pre>
	 * 
	 * @param reqUrl HTTP请求URL
	 * @param parameters 参数映射表
	 * @param sendEncoding 发送编码
	 * @param recvEncoding 接收编码
	 * @return HTTP响应的字符串
	 */
	public static String doPost(String reqUrl, Map parameters, String sendEncoding,String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
				Entry element = (Entry) iter.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(), sendEncoding));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			System.out.println(reqUrl + "?" + params.toString());
			url_con.setConnectTimeout(50000);// （单位：毫秒）jdk
			// 1.5换成这个,连接超时
			url_con.setReadTimeout(50000);// （单位：毫秒）jdk 1.5换成这个,读操作超时
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			System.out.println("doPost.url:" + reqUrl + ",error:" + e.getMessage());
//			e.printStackTrace();
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}
	public static String doHttpPost(String url, String body, int timeout) throws Exception{
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
		client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(body));
		HttpResponse response = client.execute(post);
		if(response != null){
			int code = response.getStatusLine().getStatusCode();
			if(code == HttpStatus.SC_OK){//如果返回成功
				return EntityUtils.toString(response.getEntity());
			}
		}
    	return null;
    }

	/**
	 * post + json
	 * @param url
	 * @param body
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public static String doHttpPostWithJson(String url, String body, int timeout) throws Exception{
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
		client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(body));
		post.addHeader("Content-Type", "application/json");
		HttpResponse response = client.execute(post);
		if(response != null){
//			int code = response.getStatusLine().getStatusCode();
            return EntityUtils.toString(response.getEntity());
		}
        return null;
    }

	public static String doHttpPostWithJson(String url, String body, Map<String, Object> headMap) throws Exception{
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, DEFAULT_TIME_OUT);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, DEFAULT_TIME_OUT);
		client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(body));
		post.addHeader("Content-Type", "application/json");
		if(headMap != null){
			for(Entry<String, Object> entry: headMap.entrySet()){
				post.setHeader(entry.getKey(), entry.getValue().toString());
			}
		}

		HttpResponse response = client.execute(post);
		if(response != null){
//			int code = response.getStatusLine().getStatusCode();
            return EntityUtils.toString(response.getEntity());
		}
        return null;
    }

    public static String doHttpsPostWithCookies(String url,Map<String, Object> map, String cookies) {
        HttpClient client = new DefaultHttpClient();
        String str = "";

        try {
            X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() { return null; }
            };
            SSLContext ctx = SSLContext.getInstance("TLSv1");
            ctx.init(null, new TrustManager[]{xtm}, null);
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

            List<org.apache.http.NameValuePair> params = new ArrayList<>();//构建POST请求的参数
            for (String key : map.keySet()) {
                String value = null;
                Object obj;
                if((obj = map.get(key)) != null){
                    value = obj.toString();
                }
                params.add(new BasicNameValuePair(key, value));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            HttpPost post = new HttpPost(url);//创建HttpPost
            post.setEntity(entity);
            post.setHeader("Cookie", cookies);
            post.setHeader("Adf-Rich-Message", "true");
            post.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");

            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK) {
                System.out.println("Http接口状态出错(" + response.getStatusLine().getStatusCode() + ")");
            }
            str = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
			System.out.println("doHttpsPostWithCookies.url:" + url + ",error:" + e.getMessage());
//            e.printStackTrace();
        }finally {
            client.getConnectionManager().shutdown();
            return str;
        }
    }

    /**
     * @title：获取响应头中的cookies
     * @author：xuan
     * @date：2018/3/2
     */
    public static String getHttpsPostCookies(String url, Map<String, Object> map) {
        HttpClient client = new DefaultHttpClient();
        String str = "";

        try {
            X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() { return null; }
            };
            SSLContext ctx = SSLContext.getInstance("TLSv1");
            ctx.init(null, new TrustManager[]{xtm}, null);
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

            List<org.apache.http.NameValuePair> params = new ArrayList<>();//构建POST请求的参数
            for (String key : map.keySet()) {
                String value = null;
                Object obj;
                if((obj = map.get(key)) != null){
                    value = obj.toString();
                }
                params.add(new BasicNameValuePair(key, value));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            HttpPost post = new HttpPost(url);//创建HttpPost
            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK) {
                System.out.println("Http接口状态出错(" + response.getStatusLine().getStatusCode() + ")");
            }

            Header[] headers = response.getHeaders("Set-Cookie");
            if (headers == null || headers.length <= 0) {
                return null;
            }
            for(Header header : headers){
                str += header.getValue().split(";")[0] + ";";
            }

            str = str.substring(0, str.length() - 1);
        } catch (Exception e) {
			System.out.println("getHttpsPostCookies.url:" + url + ",error:" + e.getMessage());
//            e.printStackTrace();
        }finally {
            client.getConnectionManager().shutdown();
            return str;
        }
    }

	public static String doGet(String urlStr) {
		return doGet(urlStr,DEFAULT_TIME_OUT);
	}

	/**
	 * 发送get请求
	 * @param url       链接地址
	 * @return
	 */
	public static String doHttpsGetWithCookies(String url , Map<String, Object> headMap){
		String charset = "utf-8";
		HttpClient httpClient;
		HttpGet httpGet;
		String result = null;

		try {
			httpClient = new SSLClient();
			httpGet = new HttpGet(url);

			for (String key : headMap.keySet()) {
				httpGet.setHeader(key, headMap.get(key).toString());
			}

			HttpResponse response = httpClient.execute(httpGet);
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public static String doGetWithHeader(String urlStr, Map<String, Object> headMap) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(DEFAULT_TIME_OUT);
			conn.setReadTimeout(DEFAULT_TIME_OUT);
			if(headMap != null){
				for(Entry<String, Object> entry: headMap.entrySet()){
					conn.setRequestProperty(entry.getKey(), entry.getValue().toString());
				}
			}
			conn.connect();
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream in = null;
				try {
					in = conn.getInputStream();
					return IOUtils.toString(in);
				} finally {
					if (in != null)
						in.close();
				}
			}
			return null;
		} catch (Exception e) {
			System.out.println("doGetWithCookie.url:" + urlStr + ",error:" + e.getMessage());
//			throw new RuntimeException(e);
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return null;
	}

    public static String doGetWithCookie(String urlStr, String cookie) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(DEFAULT_TIME_OUT);
            conn.setReadTimeout(DEFAULT_TIME_OUT);
            conn.setRequestProperty("Cookie", cookie);
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = null;
                try {
                    in = conn.getInputStream();
                    return IOUtils.toString(in);
                } finally {
                    if (in != null)
                        in.close();
                }
            }
            return null;
        } catch (Exception e) {
			System.out.println("doGetWithCookie.url:" + urlStr + ",error:" + e.getMessage());
//			throw new RuntimeException(e);
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return null;
    }
	
	public static String doGet(String urlStr, int timeout) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.connect();
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream in = null;
				try {
					in = conn.getInputStream();
					return IOUtils.toString(in);
				} finally {
					if (in != null)
						in.close();
				}
			}
			return null;
		} catch (Exception e) {
			System.out.println("doGet.url:" + urlStr + ",error:" + e.getMessage());
//			throw new RuntimeException(e);
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return null;
	}
	
	/**
	 * 发送get请求，请求参数在方法中未做encode处理
	 * 
	 * @param reqUrl 请求url
	 * @param reEncode 返回编码 默认为GBK
	 * @return
	 */
	public static String get(String reqUrl, String reEncode) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		reEncode = (reEncode == null || reEncode.trim().equals("")) ? ENCODING_UTF8 : reEncode;
		try {
			StringBuffer params = new StringBuffer();
			String queryUrl = reqUrl;
			int paramIndex = reqUrl.indexOf("?");

			if (paramIndex > 0) {
				queryUrl = reqUrl.substring(0, paramIndex);
				String parameters = reqUrl.substring(paramIndex + 1, reqUrl.length());
				String[] paramArray = parameters.split("&");
				for (int i = 0; i < paramArray.length; i++) {
					String string = paramArray[i];
					int index = string.indexOf("=");
					if (index > 0) {
						String parameter = string.substring(0, index);
						String value = string.substring(index + 1, string.length());
						params.append(parameter);
						params.append("=");
						params.append(value);
						params.append("&");
					}
				}

				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(queryUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("GET");
			url_con.setConnectTimeout(50000);// （单位：毫秒）jdk
			// 1.5换成这个,连接超时
			url_con.setReadTimeout(50000);// （单位：毫秒）jdk 1.5换成这个,读操作超时
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, reEncode));
			String tempLine = rd.readLine();
			StringBuffer temp = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				temp.append(tempLine);
				temp.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = temp.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			System.out.println("get.url:" + reqUrl + ",error:" + e.getMessage());
//			throw new RuntimeException(e);
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}

		return responseContent;
	}

	public static class NameValuePair {
		public String name;
		public String value;

		public NameValuePair(String name, String value) {
			this.name = name;
			this.value = value;
		}
	}

	public static String appendParams(String urlStr, NameValuePair... pairs) {
		StringBuilder sBuilder = new StringBuilder();
		for (NameValuePair pair : pairs) {
			sBuilder.append(pair.name);
			sBuilder.append("=");
			sBuilder.append(pair.value);
			sBuilder.append("&");
		}
		if (sBuilder.length() > 0) {
			sBuilder.deleteCharAt(sBuilder.length() - 1);
		}
		if (sBuilder.length() > 0) {
			if (urlStr.indexOf("?") == -1) {
				urlStr += "?";
			} else if (!urlStr.endsWith("?") && !urlStr.endsWith("&")) {
				urlStr += "&";
			}
			urlStr += sBuilder.toString();
			return urlStr;
		} else {
			return urlStr;
		}
	}
}
