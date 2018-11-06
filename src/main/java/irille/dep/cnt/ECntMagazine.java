package irille.dep.cnt;

import irille.dep.plt.EPltFreight;
import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.cnt.CntMagazine;
import irille.shop.cnt.CntLink.T;

public class ECntMagazine extends CntMagazine{
	public static void main(String[] args) {
		new ECntMagazine().crtCnt().crtFiles();
	}
	public EMCrt crtCnt() {
		VFlds vflds=new VFlds(TB);
		VFlds searchVflds=new VFlds(T.NAME);
		EMCrt ext =new EMCrtSimple<>(TB, vflds, searchVflds);
		VFlds vfl = ext.getVfldsForm();
		vfl.del(T.CREATED_BY);
		vfl.del(T.CREATED_TIME);
		ext.newExts().init();
		return ext;
	}
	
	//@formatter:off	
	
	//@formatter:on
}
