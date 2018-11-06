package irille.dep.pdt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.view.VFlds;
import irille.shop.pdt.PdtAttr;
import irille.shop.pdt.PdtAttrLine;

public class EPdtAttr extends PdtAttr {
	public static void main(String[] args) {
		new EPdtAttr().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		PdtAttrLine.TB.getCode();
		VFlds[] vflds = new VFlds[] { new VFlds(TB) };
		VFlds[] mflds = new VFlds[] { new VFlds(T.NAME) };
		VFlds[] searchVflds = new VFlds[] { new VFlds(T.NAME,T.CATEGORY) };
		EMCrt ext = new EMCrtComp(TB, vflds, mflds, searchVflds,
				new VFlds[] { new VFlds(PdtAttrLine.T.MAIN) });
		ext.getVfldsList().get(T.NAME).setWidthList(198);
		ext.getVfldsForm().del(T.CREATE_BY, T.CREATE_TIME,T.DELETED);
		ext.newExts().init();
		return ext;
	}
}