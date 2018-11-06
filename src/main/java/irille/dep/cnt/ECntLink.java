package irille.dep.cnt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.cnt.CntLink;

public class ECntLink extends CntLink{
	public static void main(String[] args) {
		new ECntLink().crtCnt().crtFiles();
	}
	
	public EMCrt crtCnt(){
		VFlds vflds = new VFlds(TB);
		VFlds searchVflds = new VFlds(T.NAME,T.UPDATED_TIME);//搜索工具栏设置
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		VFlds vfl = ext.getVfldsForm();
		vfl.get(T.UPDATED_TIME ).setReadOnly("!this.insFlag");
		vfl.get(T.CREATED_BY ).setReadOnly("!this.insFlag");
		vfl.get(T.CREATED_TIME ).setReadOnly("!this.insFlag");
	
		
		ext.getVfldsForm().del(T.UPDATED_TIME);
		
		ext.getVfldsForm().del(T.CREATED_BY);
		ext.getVfldsForm().del(T.CREATED_TIME);
		
		ext.newExts().init();
		return ext;
	}
	
	//@formatter:off	
	
	/** Begin onTodo ********
		
	 *** End onTodo *********/
	/** Begin onUndo ********

	 *** End onUndo *********/
	//@formatter:on
	
	
	

}
