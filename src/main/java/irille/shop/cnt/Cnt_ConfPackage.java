package irille.shop.cnt;

import irille.core.sys.ConfPackage;
import irille.exp.exp.Exp;

public class Cnt_ConfPackage extends ConfPackage{
	public static final Cnt_ConfPackage INST = new Cnt_ConfPackage();

	@Override
	public void initPacks() {
		_packsFlag = true;
		installSys();
		add(Exp.class, 8200);
		add(Cnt.class,9500);
	
	}
	
	

}
