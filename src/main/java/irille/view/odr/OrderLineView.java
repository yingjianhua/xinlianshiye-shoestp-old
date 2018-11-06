package irille.view.odr;

import irille.pub.formatter.CurrencyFormatter;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.odr.OdrOrderLine;
import irille.shop.plt.PltErate;
import irille.view.pdt.SpecView;

import org.json.JSONException;

public class OrderLineView {

	private Integer qty;
	private String subtotal;
	private SpecView spec;
	
	public static OrderLineView build(OdrOrderLine bean, Language lang, PltErate currency) throws JSONException {
		OrderLineView view = new OrderLineView();
		view.setQty(bean.getQty());
		view.setSubtotal(CurrencyFormatter.format(bean.getSubtotal(), currency, true, false));
		view.setSpec(SpecView.build(bean.gtSpec(), lang, currency));
		return view;
	}
	
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public SpecView getSpec() {
		return spec;
	}
	public void setSpec(SpecView spec) {
		this.spec = spec;
	}
	
}
