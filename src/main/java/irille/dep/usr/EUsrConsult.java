package irille.dep.usr;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.usr.UsrConsult;

public class EUsrConsult extends UsrConsult {
	public static void main(String[] args) {
		new EUsrConsult().crtExt().crtFiles();
	}
	
	public EMCrt crtExt() {
		VFlds vflds = new VFlds(TB);
		VFlds searchVflds = new VFlds(T.NAME,T.TITLE,T.PRODUCT,T.PURCHASE);//搜索工具栏设置
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		VFlds vfl = ext.getVfldsForm();
		VFlds vflist = ext.getVfldsList();
		vflist.get(T.IMAGE).setWidthList(123);
		vflist.get(T.COUNTRY).setWidthList(104);
		vflist.get(T.PRODUCT).setWidthList(380);
		vflist.get(T.EMAIL).setWidthList(198);
		vflist.del(T.HAVE_NEW_MSG);
		ext.newExts().init();
		return ext;
	}

}
