package dep.plt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.view.VFlds;
import irille.shop.plt.PltFreight;
import irille.shop.plt.PltFreightLine;


public class EPltFreight extends PltFreight {
	public static void main(String[] args) {
		new EPltFreight().crtExt().crtFiles();
	}
	public EMCrt crtExt(){
		PltFreightLine.TB.getCode();
		VFlds[] vflds =new VFlds[]{  new VFlds(TB)};
		VFlds[] mflds = new VFlds[] { new VFlds(T.PKEY, T.COMPANY) };
		VFlds[] searchVflds =new VFlds[]{ new VFlds(T.COMPANY,T.TYPE)};
		EMCrt ext = new EMCrtComp(TB, vflds,mflds ,searchVflds,
				new VFlds[] { new VFlds(PltFreightLine.T.MAIN) });
		VFlds vfl = ext.getVfldsForm();
		vfl.del(T.ENABLED);//删除FORM表单里的字段
		vfl.del(T.CREATED_BY);
		vfl.del(T.CREATED_TIME);
		vfl.del(T.USER_SYS);
		ext.newExts().init();
//		EMCrt extTrig = new EMCrtTrigger(TB, vflds, T.COMPANY, new VFlds(T.COMPANY));
//		extTrig.newExts().init().crtFiles();
		return ext;
	}
	//@formatter:off

	//@formatter:on
}
