package irille.pub.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
	public static void main(String[] args) throws Exception {
		System.out.println(sendGet("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=eyJhbGciOiJSUzI1NiIsImtpZCI6ImI4NjNiNTM0MDY5YmZjMDIwNzE5N2JjZjgzMTMyMGQxY2RjMmNlZTIifQ.eyJhenAiOiIxMDMzODMzMjYxMTkzLWFxazlyaTM5a3BhdnRpYzVpZjNrb3N2OHV1dXNybGM0LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiMTAzMzgzMzI2MTE5My1hcWs5cmkzOWtwYXZ0aWM1aWYza29zdjh1dXVzcmxjNC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExMzc2NDgzOTY1ODIxMjgxNTkzMSIsImVtYWlsIjoic2hvZXN0cEBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6InJiZmJaakNBb3dKam9jUThTelF3cEEiLCJleHAiOjE1MzU1NDEzMzUsImlzcyI6ImFjY291bnRzLmdvb2dsZS5jb20iLCJqdGkiOiI5YzY2Y2MzNzIzMGVmYjdhMzNjZDQ2MDgzMjNkZTYzZDk5M2E1NDY4IiwiaWF0IjoxNTM1NTM3NzM1LCJuYW1lIjoi6Z6L6LS45rivIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS8tbTVGdEQxS3NKYjQvQUFBQUFBQUFBQUkvQUFBQUFBQUFBQUEvQVBVSUZhTmhmUHlTTzdDZnBORTQyUlNlNDlPZldIbnNQdy9zOTYtYy9waG90by5qcGciLCJnaXZlbl9uYW1lIjoi6LS45rivIiwiZmFtaWx5X25hbWUiOiLpnosiLCJsb2NhbGUiOiJ6aC1DTiJ9.nV0_0GsZhIKoKNttkPCmkrjoqgDkDOI_gBX8fmsXqxNCFMe81x-1y49oyBHrZge7w1iX4ZWUVQ2nEYwWYachNAeCKcHbrmUxIzQNa3tb-F4Wlnj8X5EI8K_ICjL0WeWDwxn3WFk_pN-9Pn6ZmxvDmRX9Lhj611SqEygRwyR87qrTHO46Q9F8N6U3Xr6IL6Go-q_E3kbQw2t-hrYwRtk-ON2CPEKlFPOpJs9Td182odmsARcpTDDLmOeXeVVrvm-wXmH_Kv5Zj2GL-QALumPNjA2oz6jpXtnwNHqHgF0PwoTrPS2A27X2U5IUMqqT9spMcOw2TZHidPeWk0_vFdV9-w","127.0.0.1",1080));
	}
}
