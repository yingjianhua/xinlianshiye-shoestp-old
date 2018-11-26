package irille.pub.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.pub.Log;
import irille.pub.bean.query.BeanQuery;
import irille.pub.svr.DbPool;
import junit.framework.Assert;

public class AppConfig {
	private static final Log LOG = new Log(AppConfig.class);
	
	public static final String domain;
	public static final Boolean dev;
	public static final TimeZone timeZone;
	public static final ObjectMapper objectMapper;
	public static final String image_base_url;
	public static final String mail_template;
	public static final String mail_template2;
	public static final String mail_template_file;
	public static final String mail_template_file2;
	public static final String mail_template_logo;
	public static final String mail_template_domain;
	public static final String mail_template_index;
	public static final String mail_template_forget;
	public static final String mail_template_contactus;

	static {
//		String filepath = "/appconfig.properties";
		String filepath = "appconfig.properties";
		BufferedReader in = null;
		try {
			InputStream inStream = AppConfig.class.getClassLoader().getResourceAsStream(filepath);
			Properties properties = new Properties();
			properties.load(inStream);
			
			
			domain = properties.getProperty("domain");
			Assert.assertNotNull(filepath+"中没有配置domain", domain);

			dev = Boolean.valueOf(properties.getProperty("dev", "false"));
			BeanQuery.config(dev);
			timeZone = TimeZone.getTimeZone(properties.getProperty("i18n.timezone", "Asia/Shanghai"));
			image_base_url = properties.getProperty("imageBaseUurl", "http://www.shoesttr.com:8080");
			
			objectMapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
			
			mail_template_file = properties.getProperty("mail.template.file");
			mail_template_file2 = properties.getProperty("mail.template.file2");
			mail_template_logo = properties.getProperty("mail.template.logo");
			mail_template_domain = properties.getProperty("mail.template.domain");
			mail_template_index = properties.getProperty("mail.template.index");
			mail_template_forget = properties.getProperty("mail.template.forget");
			mail_template_contactus = properties.getProperty("mail.template.contactUs");
			
			in = new BufferedReader(new InputStreamReader(AppConfig.class.getResourceAsStream(mail_template_file)));
			StringBuilder b = new StringBuilder();
			String buffer;
			while((buffer = in.readLine())!=null) {
				b.append(buffer).append("\r\n");
			}
			mail_template = b.toString();

			in = new BufferedReader(new InputStreamReader(AppConfig.class.getResourceAsStream(mail_template_file2), Charset.forName("utf-8")));
			 b = new StringBuilder();
			while((buffer = in.readLine())!=null) {
				System.out.println(buffer);
				b.append(buffer).append("\r\n");
			}

			mail_template2 = b.toString();
		} catch (Exception e) {
			LOG.err("properties is not exists", "配置文件【{0}】不存在", filepath);
			throw new AssertionError();
		} finally {
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	public static void db_connection_close() {
		DbPool.getInstance().removeConn();
	}
	public static void dbpool_release() {
		DbPool.getInstance().releaseAll();
	}
	public static void db_connection_commit() throws SQLException {
		DbPool.getInstance().getConn().commit();
	}
	public static void db_connection_rollback() throws SQLException {
		DbPool.getInstance().getConn().rollback();
	}
	
	public static void main(String[] args) {
		System.out.println(AppConfig.timeZone);
	}
}
