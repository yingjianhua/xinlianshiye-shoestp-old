//Created on 2005-10-24
package irille.exp.exp;

import irille.core.sys.ConfPackage;

public class Exp_ConfPackage extends ConfPackage {
	public static final Exp_ConfPackage INST = new Exp_ConfPackage();

	@Override
	public void initPacks() {
		_packsFlag = true;
		installSys();
//		add(Exp.class, 8200);
	}
}
