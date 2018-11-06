package irille.dep.plt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.plt.PltTrantslate;

public class EPltTrantslate extends PltTrantslate {
	public static void main(String[] args) {
		new EPltTrantslate().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		VFlds vflds = new VFlds(TB);
		VFlds searchVflds = new VFlds(T.SOURCE_TEXT);//搜索工具栏设置
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		VFlds vfl = ext.getVfldsForm();
		VFlds vflist = ext.getVfldsList();
		ext.newExts().init();
		return ext;
	}

}
