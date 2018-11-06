package irille.dep.lg;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.lg.LgAccess;

public class ELgAccess extends LgAccess {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		//new ELgAccess().crtExt().crtFiles();
	}
	public EMCrt<?> crtExt(){
		VFlds vflds = new VFlds(TB);
		VFlds searchVflds = new VFlds(T.LOGINNAME);//搜索工具栏设置
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		ext.getVfldsList().setWidths(0, 100, 120, 100, 500, 500, 100, 100);
		ext.newExts().init();
		return ext;
	}
}
//@formatter:off	
/** Begin onQuery ********
	var win = Ext.create('mvc.view.lg.LgAccess.Win',{
		title : this.title+'>统计'
	});
	win.show();
*** End onQuery *********/
//@formatter:on