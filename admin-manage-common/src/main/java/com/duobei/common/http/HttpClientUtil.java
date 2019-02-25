package com.duobei.common.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class HttpClientUtil {
	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	static final int getConnectForPoolingTimeout = 1 * 1000;// 1秒
	static final int connectTimeout = 10 * 1000;// 10秒
	static final int socketTimeout = 10 * 1000;// 5秒
	static final int maxTotal = 300;// 连接池最大数
	static final int maxPerRoute = 10;// 没有路由站点的最大连接数
	static final String CHARSET_UTF_8 = "UTF-8";

	private static CloseableHttpClient httpClient = null;
	public static IdleConnectionMonitorThread idleConnectionMonitorThread;
	private final static Object syncLock = new Object();

	/**
	 * 设置请求配置，超时时间，请求头等
	 * 
	 * @param httpRequestBase
	 */
	public static void config(HttpRequestBase httpRequestBase) {
		// 设置Header等
		// httpRequestBase.setHeader("Content-Type",
		// "application/x-www-form-urlencoded; charset=utf-8");
		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(getConnectForPoolingTimeout)// 连接不够用时,从连接池中取连接的等待时间,一定要设置，而且不能太大
				.setConnectTimeout(connectTimeout)// 设置请求超时 根据业务调整
				.setSocketTimeout(socketTimeout)// 设置等待数据超时时间 根据业务调整
				.setStaleConnectionCheckEnabled(true)// 在提交请求之前 测试连接是否可用
				.build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * 获取HttpClient对象
	 * 
	 * @param url
	 * @return
	 */
	// public static CloseableHttpClient getHttpClient(String url) {
	// String hostname = url.split("/")[2];
	// int port = 80;
	// if (hostname.contains(":")) {
	// String[] arr = hostname.split(":");
	// hostname = arr[0];
	// port = Integer.parseInt(arr[1]);
	// }
	// if (httpClient == null) {
	// synchronized (syncLock) {
	// if (httpClient == null) {
	// httpClient = createHttpClient(maxTotal, maxPerRoute, 50, hostname, port);
	// }
	// }
	// }
	// return httpClient;
	// }
	public static CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			synchronized (syncLock) {
				if (httpClient == null) {
					httpClient = createHttpClient(maxTotal, maxPerRoute, 0, null, 0);
				}
			}
		}
		return httpClient;
	}

	/**
	 * 创建HttpClient对象-连接池管理
	 * 
	 * @param maxTotal
	 *            整个连接池最大连接数
	 * @param maxPerRoute
	 *            没有路由站点的最大连接数
	 * @param maxRoute
	 *            设置目标主机的最大连接数
	 * @param hostname
	 *            目标主机域名
	 * @param port
	 *            目标主机端口
	 * @return
	 */
	public static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int maxRoute, String hostname,
			int port) {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
		///////////
		// KeyStore trustStore =
		// KeyStore.getInstance(KeyStore.getDefaultType());
		// SSLContext sslContext = SSLContexts.custom().useTLS()
		// .loadTrustMaterial(trustStore, new TrustStrategy() {
		// @Override
		// public boolean isTrusted(X509Certificate[] chain, String authType)
		// throws CertificateException {
		// return true;
		// }
		// }).build();
		// LayeredConnectionSocketFactory sslSF = new
		// SSLConnectionSocketFactory(sslContext,
		// SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		/////////////
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", plainsf).register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		// 设置整个连接池最大连接数 根据自己的场景决定
		cm.setMaxTotal(maxTotal);
		// 没有路由站点的最大连接数（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。设置过小无法支持大并发(ConnectionPoolTimeoutException:
		// Timeout waiting for connection from pool)，
		// 路由是对maxTotal的细分,如果只有一个路由，那就设置为maxTotal
		cm.setDefaultMaxPerRoute(maxPerRoute);
		/**
		 * 此处解释下MaxtTotal和DefaultMaxPerRoute的区别： 1、MaxtTotal是整个池子的大小；
		 * 2、DefaultMaxPerRoute是根据连接到的主机对MaxTotal的一个细分；比如： MaxtTotal=400
		 * DefaultMaxPerRoute=200
		 * 而我只连接到http://sishuok.com时，到这个主机的并发最多只有200；而不是400；
		 * 而我连接到http://sishuok.com 和
		 * http://qq.com时，到每个主机的并发最多只有200；即加起来是400（但不能超过400）；
		 * 所以起作用的设置是DefaultMaxPerRoute。
		 */
		// 这里是设置目标主机的最大连接数，与上面的没有路由站点的最大连接数
		if (hostname != null) {
			HttpHost httpHost = new HttpHost(hostname, port);
			cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);
		}
		// 设置http client的重试次数，默认是3次（如果项目量不大，这个默认即可）
		HttpRequestRetryHandler httpRequestRetryHandler = null;
		/**
		 * httpRequestRetryHandler = new HttpRequestRetryHandler() { public
		 * boolean retryRequest(IOException exception, int executionCount,
		 * HttpContext context) { if (executionCount >= 2) {// 如果已经重试了2次，就放弃
		 * return false; } if (exception instanceof NoHttpResponseException) {//
		 * 如果服务器丢掉了连接，那么就重试 return true; } if (exception instanceof
		 * SSLHandshakeException) {// 不要重试SSL握手异常 return false; } if (exception
		 * instanceof InterruptedIOException) {// 超时 return false; } if
		 * (exception instanceof UnknownHostException) {// 目标服务器不可达 return
		 * false; } if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
		 * return false; } if (exception instanceof SSLException) {// SSL握手异常
		 * return false; }
		 * 
		 * HttpClientContext clientContext = HttpClientContext .adapt(context);
		 * HttpRequest request = clientContext.getRequest(); // 如果请求是幂等的，就再次尝试
		 * if (!(request instanceof HttpEntityEnclosingRequest)) { return true;
		 * } return false; } };
		 */
		// 此配置是禁用掉
		httpRequestRetryHandler = new DefaultHttpRequestRetryHandler(0, false);

		idleConnectionMonitorThread = new IdleConnectionMonitorThread(cm);
		idleConnectionMonitorThread.start();

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm)
				.setRetryHandler(httpRequestRetryHandler).build();
		return httpClient;
	}

	/**
	 * 设置post参数
	 * 
	 * @param httpost
	 * @param params
	 * @throws UnsupportedEncodingException
	 */
	private static void setPostParams(HttpPost httpost, Map<String, Object> params)
			throws UnsupportedEncodingException {
		if (params == null) {
			return;
		}
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
		}
		httpost.setEntity(new UrlEncodedFormEntity(nvps, CHARSET_UTF_8));
	}

	/**
	 * post 请求-表单类型
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Map<String, Object> params) throws Exception {
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		setPostParams(httppost, params);
		CloseableHttpResponse response = null;
		try {
			response = getHttpClient().execute(httppost, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.warn("http连接池关闭response异常", e);
			}
		}
	}

	public static String post(String url, Map<String, Object> params, HashMap<String, String> headers)
			throws Exception {
		HttpPost httppost = new HttpPost(url);
		config(httppost);
		setPostParams(httppost, params);
		if (headers != null) {
			for (String key : headers.keySet()) {
				httppost.setHeader(key, headers.get(key));
			}
		}
		CloseableHttpResponse response = null;
		try {
			response = getHttpClient().execute(httppost, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.warn("http连接池关闭response异常", e);
			}
		}
	}

	/**
	 * post 请求-数据块
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String postData(String url, String postData) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		config(httpPost);
		StringEntity stringEntity = new StringEntity(postData, CHARSET_UTF_8);
		httpPost.setEntity(stringEntity);

		CloseableHttpResponse response = null;
		try {
			response = getHttpClient().execute(httpPost, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.warn("http连接池关闭response异常", e);
			}
		}
	}

	public static String postData(String url, String postData, HashMap<String, String> headers) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		config(httpPost);
		StringEntity stringEntity = new StringEntity(postData, CHARSET_UTF_8);
		httpPost.setEntity(stringEntity);
		if (headers != null) {
			for (String key : headers.keySet()) {
				httpPost.setHeader(key, headers.get(key));
			}
		}
		CloseableHttpResponse response = null;
		try {
			response = getHttpClient().execute(httpPost, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.warn("http连接池关闭response异常", e);
			}
		}
	}

	public static String putData(String url, String postData, HashMap<String, String> headers) throws Exception {
		HttpPut httpPut = new HttpPut(url);
		config(httpPut);
		StringEntity stringEntity = new StringEntity(postData, CHARSET_UTF_8);
		httpPut.setEntity(stringEntity);
		if (headers != null) {
			for (String key : headers.keySet()) {
				httpPut.setHeader(key, headers.get(key));
			}
		}
		httpPut.setHeader("", "");

		CloseableHttpResponse response = null;
		try {
			response = getHttpClient().execute(httpPut, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.warn("http连接池关闭response异常", e);
			}
		}
	}

	/**
	 * post请求-ssl
	 * 
	 * @param url
	 * @param data
	 * @param pwd
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String postSSL(String url, String data, String pwd, String path) throws Exception {
		boolean isHttp = path.indexOf("http") == 0;
		InputStream instream = null;
		KeyStore keyStore = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		CloseableHttpResponse fileResponse = null;
		try {
			keyStore = KeyStore.getInstance("PKCS12");
			if (isHttp) {
				fileResponse = HttpClientUtil.getForResources(path);
				instream = fileResponse.getEntity().getContent();
			} else {
				instream = new FileInputStream(new File(path));
			}
			keyStore.load(instream, pwd.toCharArray());
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, pwd.toCharArray()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			HttpPost httpPost = new HttpPost(url);
			StringEntity stringEntity = new StringEntity(data, CHARSET_UTF_8);
			httpPost.setEntity(stringEntity);
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (Exception e2) {
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (Exception e2) {
				}
			}
			if (fileResponse != null) {
				try {
					fileResponse.close();
				} catch (Exception e2) {
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	public static String postSSL(String url, String data, String pwd, byte[] cerByteArr) throws Exception {
		InputStream instream = null;
		KeyStore keyStore = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			keyStore = KeyStore.getInstance("PKCS12");
			instream = new ByteArrayInputStream(cerByteArr);
			keyStore.load(instream, pwd.toCharArray());
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, pwd.toCharArray()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			HttpPost httpPost = new HttpPost(url);
			StringEntity stringEntity = new StringEntity(data, CHARSET_UTF_8);
			httpPost.setEntity(stringEntity);
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (Exception e2) {
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (Exception e2) {
				}
			}
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	/**
	 * get请求
	 * 
	 * @throws Exception
	 */
	public static String get(String url) throws Exception {
		HttpGet httpget = new HttpGet(url);
		config(httpget);
		CloseableHttpResponse response = null;
		try {
			response = getHttpClient().execute(httpget, HttpClientContext.create());
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
			return result;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (response != null) {
					response.close();
				}

			} catch (IOException e) {
				log.warn("http连接池关闭response异常", e);
			}
		}
	}

	/**
	 * 获取网络资源-注意用完后关闭资源
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static CloseableHttpResponse getForResources(String url) throws Exception {
		HttpGet httpget = new HttpGet(url);
		config(httpget);
		CloseableHttpResponse response = null;
		try {
			response = getHttpClient().execute(httpget, HttpClientContext.create());
		} catch (IOException e) {
			throw e;
		}
		return response;
	}

	/**
	 * 关闭连接管理器,当一个HttpClient的实例不在使用，或者已经脱离它的作用范围，我们需要关掉它的连接管理器，来关闭掉所有的连接，
	 * 释放掉这些连接占用的系统资源。
	 */
	public static void shutdown() {
		if (httpClient != null) {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.warn("关闭httpclient异常", e);
			}
		}
		if (idleConnectionMonitorThread != null) {
			idleConnectionMonitorThread.shutdown();
		}
	}

	/**
	 * 获取资源-返回字节数组
	 * 
	 * @param path-url或相对路径等
	 * @return
	 */
	public static byte[] getResourcesByPath(String path) throws Exception {
		boolean isHttp = path.indexOf("http") == 0;
		InputStream instream = null;
		ByteArrayOutputStream byteOs = null;
		CloseableHttpResponse fileResponse = null;
		byte[] result = null;
		try {
			if (isHttp) {
				fileResponse = HttpClientUtil.getForResources(path);
				HttpEntity entity = fileResponse.getEntity();
				result = EntityUtils.toByteArray(entity);
				EntityUtils.consume(entity);
			} else {
				instream = new FileInputStream(new File(path));
				byteOs = new ByteArrayOutputStream();
				byte[] bytes = new byte[1024];
				int len;
				while (-1 != (len = instream.read(bytes))) {
					byteOs.write(bytes, 0, len);
				}
				result = byteOs.toByteArray();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (Exception e2) {
				}
			}
			if (byteOs != null) {
				try {
					byteOs.close();
				} catch (Exception e2) {
				}
			}
			if (fileResponse != null) {
				try {
					fileResponse.close();
				} catch (Exception e2) {
				}
			}
		}
		return result;
	}

	public static void getFile(String url, String destFileName) throws ClientProtocolException, IOException {
		// 生成一个httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		InputStream in = entity.getContent();
		File file = new File(destFileName);
		try {
			FileOutputStream fout = new FileOutputStream(file);
			int l = -1;
			byte[] tmp = new byte[1024];
			while ((l = in.read(tmp)) != -1) {
				fout.write(tmp, 0, l);
				// 注意这里如果用OutputStream.write(buff)的话，图片会失真，大家可以试试
			}
			fout.flush();
			fout.close();
		} finally {
			// 关闭低层流。
			in.close();
		}
		httpclient.close();
	}

}
