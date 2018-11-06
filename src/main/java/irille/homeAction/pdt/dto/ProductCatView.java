package irille.homeAction.pdt.dto;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtCatDAO;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

/**
 * 产品分类视图
 * @author yingjianhua
 *
 */
public class ProductCatView {
	private Integer id;
	private String name;
	private List<ProductCatView> subs;
	
	public static ProductCatView build(PdtCat cat, Language lang) throws JSONException {
		ProductCatView view = new ProductCatView();
		view.setId(cat.getPkey());
		view.setName(cat.getName(lang));
		view.setSubs(build(PdtCatDAO.Query.listSub(cat.getPkey()), lang));
		return view;
	}
	public static List<ProductCatView> build(List<PdtCat> list, Language lang) throws JSONException {
		List<ProductCatView> views = new ArrayList<>();
		for(PdtCat cat:list) {
			if(cat.isEnabeld())
				views.add(build(cat, lang));
		}
		return views;
	}
	
	  public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProductCatView> getSubs() {
		return subs;
	}
	public void setSubs(List<ProductCatView> subs) {
		this.subs = subs;
	}
	
}
