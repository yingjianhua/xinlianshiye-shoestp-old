package dep.plt;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.view.VFlds;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;

public class EPltCountry extends PltCountry {
	public static void main(String[] args) {
		new EPltCountry().crtExt().crtFiles();
	}
	public EMCrt crtExt(){
		PltProvince.TB.getCode();
		VFlds[] vflds =new VFlds[]{  new VFlds(TB)};
		VFlds[] mflds = new VFlds[] { new VFlds(T.PKEY, T.NAME) };
		VFlds[] searchVflds =new VFlds[]{ new VFlds(T.NAME, T.SHORT_NAME,T.ZONE)};
		EMCrt ext = new EMCrtComp(TB, vflds,mflds ,searchVflds,
				new VFlds[] { new VFlds(PltProvince.T.MAIN) });
		VFlds vfl = ext.getVfldsForm();
		vfl.del(T.ENABLED);//删除FORM表单里的字段
		vfl.del(T.HOT);
		vfl.del(T.CREATED_BY);
		vfl.del(T.CREATED_TIME);
		ext.newExts().init();
		return ext;
	}
	//@formatter:off
	/** Begin onHot ********
	  var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
	if (selection) {
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, '您确认要是否热门国家吗?',
				function(btn) {
					if (btn != 'yes')
						return;
					var arr = new Array();
					var arrv = new Array();
					for (var i = 0; i < selection.length; i++) {
						arr.push(selection[i].get('bean.pkey'));
						arrv.push(selection[i].get(BEAN_VERSION));
					}
					Ext.Ajax.request({
						url : base_path + '/plt_PltCountry_hot?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
						success : function(response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success) {
								me.getStore().reload();
								me.mdMainTable.getSelectionModel().deselectAll();
								Ext.example.msg(msg_title, msg_submit);
							} else {
								Ext.MessageBox.show({
									title : msg_title,
									msg : result.msg,
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.ERROR
								});
							}
						}
					});
				});
	}


	 *** End onHot *********/
	//@formatter:on
}
