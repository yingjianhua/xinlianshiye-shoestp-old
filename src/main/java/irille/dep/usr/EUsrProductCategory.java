package irille.dep.usr;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.usr.UsrProductCategory;

public class EUsrProductCategory extends UsrProductCategory {
	public static void main(String[] args) {
		new EUsrProductCategory().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		VFlds vflds = new VFlds(TB);
		VFlds searchVflds = new VFlds(T.NAME, T.SUPPLIER, T.CATEGORY_UP,T.ENABLED,T.SEO_KEYWORD_EN);
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		ext.getVfldsList().get(T.NAME).setWidthList(160);
		ext.getVfldsList().get(T.CATEGORY_UP).setWidthList(160);
		ext.getVfldsList().get(T.SUPPLIER).setWidthList(280);
		ext.newExts().init();
		return ext;
	}
}