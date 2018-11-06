package irille.homeAction.usr.dto;

import irille.homeAction.pdt.dto.ProductCatView;
import irille.view.plt.CurrencyView;
import irille.view.plt.LanguageView;

import java.util.List;


/**
 * 系统环境参数
 * @author yingjianhua
 *
 */
public class EnvView {
	private PurchaseView login;
	private List<LanguageView> languages;
	private List<CurrencyView> currencys;
	private String curLanguage;
	private CurrencyView currency;
	private List<ProductCatView> productCat;
	
	private EnvView(){};
	public static EnvView build(PurchaseView login, List<LanguageView> languages, List<CurrencyView> currencys, String curLanguage, CurrencyView currency, List<ProductCatView> productCat) {
		EnvView view = new EnvView();
		view.login = login;
		view.languages = languages;
		view.currencys = currencys;
		view.curLanguage = curLanguage;
		view.currency = currency;
		view.productCat = productCat;
		return view;
	}
	
	public PurchaseView getLogin() {
		return login;
	}
	public void setLogin(PurchaseView login) {
		this.login = login;
	}
	public List<LanguageView> getLanguages() {
		return languages;
	}
	public List<CurrencyView> getCurrencys() {
		return currencys;
	}
	public void setCurrencys(List<CurrencyView> currencys) {
		this.currencys = currencys;
	}
	public void setLanguages(List<LanguageView> languages) {
		this.languages = languages;
	}
	public String getCurLanguage() {
		return curLanguage;
	}
	public void setCurLanguage(String curLanguage) {
		this.curLanguage = curLanguage;
	}
	public CurrencyView getCurrency() {
		return currency;
	}
	public void setCurrency(CurrencyView currency) {
		this.currency = currency;
	}
	public List<ProductCatView> getProductCat() {
		return productCat;
	}
	public void setProductCat(List<ProductCatView> productCat) {
		this.productCat = productCat;
	}
	
}
