package dep.pdt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.pdt.PdtSize;

public class EPdtSize extends PdtSize {
	public static void main(String[] args) {
		new EPdtSize().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		VFlds[] vflds = new VFlds[] { new VFlds(TB) };
		VFlds[] searchVflds = new VFlds[] { new VFlds(T.NAME,T.PRODUCT_CATEGORY) };
		EMCrt ext = new EMCrtSimple<>(TB, vflds, searchVflds);
		ext.getVfldsList().get(T.NAME).setWidthList(278);
		ext.getVfldsForm().del(T.CREATE_BY, T.CREATE_TIME,T.DELETED);
		ext.newExts().init();

		return ext;
	}
}
