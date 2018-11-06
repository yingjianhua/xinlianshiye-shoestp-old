package irille.dep.exp;

import irille.exp.exp.ExpBase;
import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtSimple;
import irille.pub.view.VFlds;

public class EExpBase extends ExpBase {
	public static void main(String[] args) {
		new EExpBase().crtExt().crtFiles();
	}

	public EMCrt crtExt() {
		VFlds vflds = new VFlds(TB);
		VFlds searchVflds = new VFlds(T.CODE, T.NAME);//搜索工具栏设置
		EMCrt ext = new EMCrtSimple(TB, vflds, searchVflds);
		VFlds vfl = ext.getVfldsForm();
		vfl.get(T.CODE).setReadOnly("!this.insFlag");
		vfl.del(T.ENABLED, T.CREATE_BY, T.CREATE_TIME);//删除FORM表单里的字段
		vfl.get(T.OPT).setName("选项");
		vfl.get(T.KIND).setName("类型");
		ext.newExts().init();
		return ext;
	}
	
	//@formatter:off	
	/** Begin onTodo ********
	 var selection = this.getView().getSelectionModel().getSelection()[0];
	var me = this;
	if (selection){
		Ext.MessageBox.confirm(msg_confirm_title, '确认处理吗？',
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/exp_ExpBase_todo?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							var bean  = Ext.create('mvc.model.exp.ExpBase',result);
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
	 *** End onTodo *********/
	/** Begin onUndo ********
	var selection = this.getView().getSelectionModel().getSelection()[0];
	var me = this;
	if (selection){
		Ext.MessageBox.confirm(msg_confirm_title, '确认撤销吗？',
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/exp_ExpBase_undo?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							var bean  = Ext.create('mvc.model.exp.ExpBase',result);
							Ext.apply(selection.data, bean.data);
							selection.commit();
							me.getSelectionModel().deselectAll();
							me.getView().select(selection);
							Ext.example.msg(msg_title, '撤销--成功');
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
	 *** End onUndo *********/
	//@formatter:on

}
