package irille.view.home;

import irille.pub.util.AppConfig;

/**
 * 给前端页面配置基本信息
 * @author yingjianhua
 *
 */
public class EnvConfig {
//	var stpshop_config={
//			"domain":"https://www.shoestp.com",
//			"date":"2018/08/20 10:24:39",
//			"lang":"en",
//			"currency":"USD",
//			"currency_symbols":"$",
//			"currency_rate":"1.0000",
//			"FbAppId":"718848724966585",
//			"FbPixelOpen":"0",
//			"UserId":"208",
//			"TouristsShopping":"0",
//			"PaypalExcheckout":""
//		};
	
	private String domain;
	private String lang;
	private String currency;
	private String currencySymbols;
	private String currencyRate;
	private String userId;
	private Long systemTime;
	private String imageBaseUrl;
	
	public static EnvConfig build(String domain, String lang, String currency, String currencySymbols, String currencyRate, String userId) {
		EnvConfig config = new EnvConfig();
		config.setDomain(domain);
		config.setLang(lang);
		config.setCurrency(currency);
		config.setCurrencySymbols(currencySymbols);
		config.setCurrencyRate(currencyRate);
		config.setUserId(userId);
		config.setSystemTime(System.currentTimeMillis());
		config.setImageBaseUrl(AppConfig.image_base_url);
		return config;
	}

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrencySymbols() {
		return currencySymbols;
	}
	public void setCurrencySymbols(String currencySymbols) {
		this.currencySymbols = currencySymbols;
	}
	public String getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(String currencyRate) {
		this.currencyRate = currencyRate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(Long systemTime) {
		this.systemTime = systemTime;
	}
	public Integer getTimezoneOffset() {
		return AppConfig.timeZone.getRawOffset();
	}
	public String getImageBaseUrl() {
		return imageBaseUrl;
	}
	public void setImageBaseUrl(String imageBaseUrl) {
		this.imageBaseUrl = imageBaseUrl;
	}
}
