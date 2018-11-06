package irille.dep.plt;
import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;
import irille.shop.plt.PltErate;

public class EPltErate extends PltErate {
	public static void main(String[] args) {
		//new EPltErate().crtExt().crtFiles();
	}
	public EMCrt crtExt() {
		VFlds vflds = new VFlds(TB);
		VFlds searchVflds = new VFlds(T.CUR_NAME);//搜索工具栏设置
		EMCrt ext = new EMCrtSimple(TB,vflds,searchVflds);
		VFlds vfl = ext.getVfldsForm();
		VFlds vflist = ext.getVfldsList();
		vfl.getCode();
		vfl.del(T.ENABLED);//删除FORM表单里的字段
		vfl.del(T.CREATED_TIME);
		vfl.del(T.CREATED_BY);
		vflist.get(T.LOGO).setWidthList(60);
		vflist.get(T.RATE).setWidthList(100);
		//vfl.del(T.NOWRATE);
		vfl.del(T.SITE_CUR);
		vfl.del(T.SUP_CUR);
		vfl.del(T.NOWRATE);
		
		ext.newExts().init();
		return ext;
	}
	//@formatter:off	
	/** Begin onDefcur ********
	 var selection = this.getView().getSelectionModel().getSelection()[0];
	var me = this;
	if (selection){
		Ext.MessageBox.confirm(msg_confirm_title, '确认设置吗？',
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/plt_PltErate_defcur?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
						    me.onSearchCancel(); 
							var bean  = Ext.create('mvc.model.plt.PltErate',result);
							Ext.apply(selection.data, bean.data);
							selection.commit();
							me.getSelectionModel().deselectAll();
							me.getView().select(selection);
							Ext.example.msg(msg_title, '处理--成功');
						}else{
							Ext.MessageBox.show({
								title : msg_title, 
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
			}
		);
	}
	 *** End onDefcur *********/
	/** Begin onSupdefcur ********
	 var selection = this.getView().getSelectionModel().getSelection()[0];
	var me = this;
	if (selection){
		Ext.MessageBox.confirm(msg_confirm_title, '确认设置吗？',
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/plt_PltErate_bcdefcur?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
						    me.onSearchCancel(); 
							var bean  = Ext.create('mvc.model.plt.PltErate',result);
							Ext.apply(selection.data, bean.data);
							selection.commit();
							me.getSelectionModel().deselectAll();
							me.getView().select(selection);
							Ext.example.msg(msg_title, '处理--成功');
						}else{
							Ext.MessageBox.show({
								title : msg_title, 
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
			}
		);
	}
	 *** End onSupdefcur *********/
	
	//@formatter:on

}
