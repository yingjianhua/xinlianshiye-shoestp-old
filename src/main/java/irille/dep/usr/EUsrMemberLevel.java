package irille.dep.usr;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.usr.UsrMemberLevel;


public class EUsrMemberLevel extends UsrMemberLevel{
	public static void main(String[] args) {
		new EUsrMemberLevel().crtExt().crtFiles();
	}
	
	public EMCrt crtExt(){
		VFlds vflds = new VFlds(TB);
		VFlds searchFlds = new VFlds(T.NAME,T.ENABLED);
		EMCrt ext = new EMCrtSimple(TB,vflds,searchFlds);
		VFlds form = ext.getVfldsForm();
		form.del(T.CREATED_BY,T.CREATED_TIME,T.UPDATED_BY,T.UPDATED_TIME);
		
		ext.newExts().init();
		return ext;
	}
}
