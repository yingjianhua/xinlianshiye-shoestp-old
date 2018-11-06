package irille.dep.usr;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.html.EMCrtTrigger;
import irille.pub.view.VFlds;
import irille.shop.usr.UsrPurchaseLine;
import irille.shop.usr.UsrPurchase;

public class EUsrPurchase extends UsrPurchase{
	public static void main(String[] args) {
		//new EUsrPurchase().crtExt().crtFiles();
	}
	
	
	public EMCrt crtExt(){
		UsrPurchaseLine.TB.getCode();
		VFlds[] vflds = new VFlds[]{new VFlds(TB)};
		VFlds[] mflds = new VFlds[]{new VFlds(T.NAME)};
		VFlds[] searchVflds = new VFlds[] { new VFlds(T.NAME,T.SEX,T.EMAIL,T.LOGIN_NAME,T.TELPHONE,T.COMPANY) };
		EMCrt ext = new EMCrtComp(TB, vflds, mflds, searchVflds,
				new VFlds[] { new VFlds(UsrPurchaseLine.T.PURCHASE) });
		ext.getVfldsList().del(T.PASSWORD,T.REG_IP);
		ext.getVfldsModel().del(T.PASSWORD,T.REG_IP);
		ext.newExts().init();
		
		EMCrtTrigger trigger = new EMCrtTrigger(TB, vflds, T.NAME, new VFlds(T.NAME));
		trigger.newExts().init().crtFiles();
		
		return ext;
		
		
	}
	
	
	//@formatter:off	
			/** Begin onUda ********
			if (this.roles.indexOf('uda') != -1)
			mainActs.push({
					text : '修改密码',
					iconCls : 'upd-icon',
					itemId : this.oldId+'uda',
					scope : this,
					handler : this.onUda,
					disabled : this.lock
			});
			
			onUda : function(){
			var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
			var unionPkey = selection[0].get('bean.pkey');
			var rowVersion = selection[0].get('bean.rowVersion');
			
			var win = Ext.create('mvc.view.usr.UsrPurchase.UdaWin',{
				title : this.title+'>修改密码',
				data : {"unionPkey" : unionPkey , "rowVersion" : rowVersion}
			});
			win.on('create',this.onSaveRecord,this);
			win.show();
		},

			 *** End onUda *********/
	
	//@formatter:on
}
