package irille.dep.pdt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.html.EMCrtTrigger;
import irille.pub.view.VFlds;
import irille.shop.pdt.PdtSpec;

public class EPdtSpec extends PdtSpec {
	public static void main(String[] args) {
		new EPdtSpec().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		VFlds[] vflds = new VFlds[] { new VFlds(TB) };
		VFlds[] searchVflds = new VFlds[] { new VFlds(T.KEY_NAME) };
		EMCrt ext = new EMCrtSimple<>(TB, vflds, searchVflds);
		ext.newExts().init();
		
		EMCrt extTrig = new EMCrtTrigger(TB, vflds,PdtSpec.T.KEY_NAME, new VFlds(PdtSpec.T.KEY_NAME));
		extTrig.newExts().init().crtFiles();
		
		return ext;
	}
}