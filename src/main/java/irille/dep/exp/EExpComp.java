package irille.dep.exp;

import irille.exp.exp.ExpComp;
import irille.exp.exp.ExpCompLine;
import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.html.EMCrtCompSimple;
import irille.pub.view.VFlds;

public class EExpComp extends ExpComp {
	public static void main(String[] args) {
		new EExpComp().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		ExpCompLine.TB.getCode();
		VFlds[] vflds = new VFlds[] { new VFlds(TB) };
		VFlds[] mflds = new VFlds[] { new VFlds(T.CODE, T.NAME) };
		VFlds[] searchVflds = new VFlds[] { new VFlds(T.CODE, T.NAME) };
		EMCrt ext = new EMCrtComp(TB, vflds, mflds, searchVflds,new VFlds[] { new VFlds(ExpCompLine.T.MAIN) });
		ext.newExts().init();
		return ext;
	}

}