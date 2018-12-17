package dep.pdt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.html.EMCrtTrigger;
import irille.pub.view.VFlds;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;

public class EPdtProduct extends PdtProduct {
	public static void main(String[] args) {
		new EPdtProduct().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		PdtSpec.TB.getCode();
		VFlds[] vflds = new VFlds[] { new VFlds(TB) };
		VFlds[] mflds = new VFlds[] { new VFlds(T.NAME) };
		VFlds[] searchVflds = new VFlds[] { new VFlds(T.NAME) };
		EMCrt ext = new EMCrtComp(TB, vflds, mflds, searchVflds,
				new VFlds[] { new VFlds(PdtSpec.T.PRODUCT) });
		//ext.getVfldsForm().del(T.CREATE_BY, T.CREATE_TIME);
		ext.newExts().init();
		VFlds showFlds = new VFlds(T.PICTURE,T.SUPPLIER,T.NAME,T.CATEGORY,T.CODE,T.SKU);
		EMCrt extTrig = new EMCrtTrigger(TB, showFlds, T.NAME, new VFlds(T.NAME,T.SKU,T.CODE,T.SUPPLIER));
		VFlds trigList = extTrig.getVfldsList();
		extTrig.newExts().init().crtFiles();
		return ext;
	}
}
