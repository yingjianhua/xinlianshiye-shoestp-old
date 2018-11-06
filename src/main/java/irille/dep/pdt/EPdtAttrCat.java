package irille.dep.pdt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.pdt.PdtAttrCat;

public class EPdtAttrCat extends PdtAttrCat {
	public static void main(String[] args) {
		new EPdtAttrCat().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		VFlds[] vflds = new VFlds[] { new VFlds(TB) };
		VFlds[] searchVflds = new VFlds[] { new VFlds(T.NAME) };
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		ext.getVfldsForm().del(T.CREATE_BY, T.CREATE_TIME,T.DELETED);
		ext.newExts().init();
		return ext;
	}
}