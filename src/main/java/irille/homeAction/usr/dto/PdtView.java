package irille.homeAction.usr.dto;

import irille.pub.util.SEOUtils;
import irille.shop.pdt.PdtProduct;

public class PdtView {
	private PdtProduct  pdt;
	private String rewrite;

	public PdtView() {

	}


	public PdtProduct getPdt() {
		return pdt;
	}


	public void setPdt(PdtProduct pdt) {
		this.pdt = pdt;
	}

	public String getRewrite() {
		return rewrite;
	}
	public void setRewrite(int id ,String name) {
		this.rewrite =SEOUtils.getPdtProductTitle(id, name);;
	}

	@Override
	public String toString() {
		return "PdtView{" +
				"pdt=" + pdt +
				", rewrite='" + rewrite + '\'' +
				'}';
	}
}
