package dep.plt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.plt.PltPay;

public class EPltPay extends PltPay{
	public static void main(String[] args) {
		//new EPltPay().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		VFlds vflds = new VFlds(TB);
		VFlds searchVflds = new VFlds(T.SUPPLIER);//搜索工具栏设置
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		VFlds vfl = ext.getVfldsForm();
		VFlds vflist = ext.getVfldsList();
		vflist.del(T.EXTRA_COSTS);
//		vflist.del(T.MIN_COST);
//		vflist.del(T.MAX_COST);
//		vflist.del(T.DES);
//		vflist.get(T.LOGO).setWidthList(40);
		vfl.getCode();
//		vfl.del(T.CREATED_TIME);
		vfl.del(T.ENABLED);//删除FORM表单里的字段
		ext.newExts().init();
		return ext;
	}

	//@formatter:off

	//@formatter:on
}
