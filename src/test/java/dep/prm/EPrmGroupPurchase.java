package dep.prm;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.view.VFlds;
import irille.shop.prm.PrmGroupPurchase;
import irille.shop.prm.PrmGroupPurchaseLine;

public class EPrmGroupPurchase extends PrmGroupPurchase{
	public static void main(String[] args) {
		//new EPrmGroupPurchase().crtExt().crtFiles();
	}

	public EMCrt crtExt(){
		PrmGroupPurchaseLine.TB.getCode();
		VFlds[] vflds = new VFlds[]{new VFlds(TB)};
		VFlds[] mflds = new VFlds[]{new VFlds(T.TITLE)};
		VFlds[] searchVflds = new VFlds[] { new VFlds(T.TITLE,T.STATUS,T.CREATED_BY) };
		EMCrt ext = new EMCrtComp(TB, vflds, mflds, searchVflds,
				new VFlds[] { new VFlds(PrmGroupPurchaseLine.T.MAIN)});

		ext.getVfldsList().get(T.STATUS).setWidthList(137);
		ext.getVfldsList().get(T.CREATED_BY).setWidthList(208);

		ext.getVfldsForm().del(T.CREATED_BY,T.CREATED_TIME,T.UPDATED_TIME,T.STATUS);

		ext.newExts().init();
		return ext;
	}
	//@formatter:off
		/** Begin onSend ********
		if (this.roles.indexOf('send') != -1)
mainActs.push({
	text : '发送订单信息至供应商',
	iconCls : 'del-icon',
	itemId : this.oldId+'send',
	scope : this,
	handler : this.onSend,
	disabled : this.lock
});

		onSend : function(){
			var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
			var unionPkey = selection[0].get('bean.pkey');
			var rowVersion = selection[0].get('bean.rowVersion');

			var win = Ext.create('mvc.view.prm.PrmUnion.InfoWin',{
				title : this.title+'>发送订单',
				data : {"unionPkey" : unionPkey , "rowVersion" : rowVersion}
			});
			win.on('create',this.onSaveRecord,this);
			win.show();
		}
		 *** End onSend *********/
	//@formatter:on

}
