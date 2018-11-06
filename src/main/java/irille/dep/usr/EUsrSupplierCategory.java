package irille.dep.usr;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.usr.UsrSupplierCategory;

public class EUsrSupplierCategory extends UsrSupplierCategory {
	public static void main(String[] args) {
		new EUsrSupplierCategory().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		VFlds vflds = new VFlds(TB);
		VFlds searchVflds = new VFlds(T.NAME,T.CATEGORY_UP);
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		ext.getVfldsList().del(T.SHOW_NAME);
		ext.getVfldsList().get(T.NAME).getWidthDefault();
		ext.getVfldsForm().del(T.CREATE_BY, T.CREATE_TIME);
		ext.newExts().init();
		return ext;
	}
}