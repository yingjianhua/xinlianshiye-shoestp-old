package irille.dep.plt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.plt.PltConfig;

public class EPltConfig extends PltConfig {
	public static void main(String[] args) {
		new EPltConfig().crtExt().crtFiles();
	}
	public EMCrt crtExt(){
		VFlds vflds = new VFlds(TB);
		VFlds searchVflds = new VFlds(T.VARIABLE);//搜索工具栏设置
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		ext.getVfldsForm().get(T.VARIABLE).setReadOnly("true");
		ext.newExts().init();
		return ext;
	}
}
