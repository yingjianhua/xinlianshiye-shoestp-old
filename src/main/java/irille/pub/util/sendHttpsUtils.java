package irille.pub.util;

import java.io.*;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import static irille.pub.util.createSSLUtils.createSSLClientDefault;

public class sendHttpsUtils {
	
	public static String sendGet(String url,String host,int port)throws Exception {
		 //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //代理对象
        HttpHost proxy = new HttpHost(host, port, "http");
        //127.0.0.1    1080
        //配置对象
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        //创建请求方法的实例， 并指定请求url
        HttpGet httpget=new HttpGet(url);
        //使用配置
        httpget.setConfig(config);
        CloseableHttpResponse response=httpClient.execute(httpget);
        HttpEntity entity=response.getEntity();
        String content=EntityUtils.toString(entity, "utf-8");
        //接收响应头
        //获取一个响应头，first和last两个方法指的是，当里面有两个一样的响应时，就去第一个或最后一个
       /* String server = response.getFirstHeader("Server").toString();
        System.out.println(server);*/
        //获取所有响应头
        Header[] header = response.getAllHeaders();
        //输出数组大小
        Header[] ha = response.getHeaders("Server");
        httpClient.close();
        return content;	
	}
	/*
	 * 处理https GET/POST请求
	 * 请求地址、请求方法、参数
	 * */
	public static String send(String requestUrl,String requestMethod,String outputStr){
		StringBuffer buffer=null;
		try{
		//创建SSLContext
		SSLContext sslContext=SSLContext.getInstance("SSL");
		TrustManager[] tm={new MyX509TrustManager()};
		//初始化
		sslContext.init(null, tm, new java.security.SecureRandom());;
		//获取SSLSocketFactory对象
		SSLSocketFactory ssf=sslContext.getSocketFactory();
		URL url=new URL(requestUrl);
/*//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new );
////		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost","127.0.0.1");
		System.setProperty("http.proxyPort","1080");*/
		HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod(requestMethod);
		//设置当前实例使用的SSLSoctetFactory
		conn.setSSLSocketFactory(ssf);
		conn.connect();
		//往服务器端写内容
		if(null!=outputStr){
			OutputStream os=conn.getOutputStream();
			os.write(outputStr.getBytes("utf-8"));
			os.close();
		}
		
		//读取服务器端返回的内容
		InputStream is=conn.getInputStream();
		InputStreamReader isr=new InputStreamReader(is,"utf-8");
		BufferedReader br=new BufferedReader(isr);
		buffer=new StringBuffer();
		String line=null;
		while((line=br.readLine())!=null){
			buffer.append(line);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public static String get(String url,String host,int port) throws Exception {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			//信任所有
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();
		//ALLOW_ALL_HOSTNAME_VERIFIER:这个主机名验证器基本上是关闭主机名验证的,实现的是一个空操作，并且不会抛出javax.net.ssl.SSLException异常。
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		//创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		//代理对象
		HttpHost proxy = new HttpHost(host, port, "http");
		//127.0.0.1    1080
		//配置对象
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		//创建请求方法的实例， 并指定请求url
		HttpGet httpget=new HttpGet(url);
		//使用配置
		httpget.setConfig(config);
		CloseableHttpResponse response=httpClient.execute(httpget);
		HttpEntity entity=response.getEntity();
		String content=EntityUtils.toString(entity, "utf-8");
		//接收响应头
		//获取一个响应头，first和last两个方法指的是，当里面有两个一样的响应时，就去第一个或最后一个
       /* String server = response.getFirstHeader("Server").toString();
        System.out.println(server);*/
		//获取所有响应头
		Header[] header = response.getAllHeaders();
		//输出数组大小
		Header[] ha = response.getHeaders("Server");
		httpClient.close();
		return content;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(get("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=wenzhou+Museum&inputtype=textquery&fields=formatted_address%2Cname%2Crating%2Cgeometry&key=AIzaSyCPbc3yNYQgVc56qbUuAY_Yap-uDMkDkvc","127.0.0.1",1080));
	}
}
