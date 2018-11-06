package irille.dep.pdt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.pdt.PdtComment;

public class EPdtComment extends PdtComment{
	
	public static void main(String[] args) {
		new EPdtComment().crtExt().crtFiles();
	}
	
	public EMCrt crtExt(){
		VFlds vflds = new VFlds(TB);
		VFlds searchVFlds = new VFlds(T.PRODUCT,T.PRODUCT_SATISFACTION);
		EMCrt ext = new EMCrtSimple(TB , vflds , searchVFlds);
//		ext.getVfldsList().del(T.COMMENT_TIME);
//		ext.getVfldsForm().del(T.IP);
		
		ext.newExts().init();
		
		
		//选择器
//				EMCrtTrigger trigger = new EMCrtTrigger(TB, vflds, T.NAME, new VFlds(T.NAME, T.LOGIN_NAME));
//				trigger.getVfldsList().del(T.LOGIN_STATE);
//				trigger.newExts().init().crtFiles();

		
		return ext;
	}
	

}
