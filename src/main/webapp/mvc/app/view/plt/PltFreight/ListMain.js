Ext.define('mvc.view.plt.PltFreight.ListMain',{
extend : 'mvc.tools.RowexpanderGrid',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
//viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[
	{text : '快递公司',width : 150,dataIndex : 'bean.company',sortable : true,expandCol : true}
	,{text : 'LOGO',width : 100,dataIndex : 'bean.logo',sortable : true,renderer : mvc.Tools.imgRenderer(0, 31)}
	,{text : '启用标志',width : 75,dataIndex : 'bean.enabled',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled'),expandCol : true}
	,{text : '快递单号查询地址',width : 150,dataIndex : 'bean.expressUrl',sortable : true,expandCol : true,hidden : true}
	,{text : '排序',width : 50,dataIndex : 'bean.sort',sortable : true,expandCol : true,hidden : true}
	,{text : '使用接口',width : 70,dataIndex : 'bean.useInterface',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol : true,hidden : true}
	,{text : '最小重量/体积/件数',width : 150,dataIndex : 'bean.weightMin',sortable : true,expandCol : true,hidden : true}
	,{text : '最大重量/体积/件数',width : 150,dataIndex : 'bean.weightMax',sortable : true,expandCol : true,hidden : true}
	,{text : '重量计算方式选择',width : 150,dataIndex : 'bean.type',sortable : true,renderer : mvc.Tools.optRenderer('plt','Plt','WeightType'),expandCol : true}
	,{text : '建档员',width : 75,dataIndex : 'bean.createdBy',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'sys',mn : 'view.sys.SysUser.List',expandCol : true,hidden : true}
	,{text : '建档时间',width : 140,dataIndex : 'bean.createdTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),expandCol : true,hidden : true}
	,{width : 700}];
		this.store=Ext.create('mvc.store.plt.PltFreight');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
		this.dockedItems=[{
		xtype : 'pagingtoolbar',
		store : this.store,
		dock : 'bottom',
		displayInfo : true,
		displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : '没有数据',
		items : [{
				xtype : Ext.create('mvc.tools.ComboxPaging',{myList : this})
			}]
	}];
		this.callParent(arguments);},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.plt.PltFreight', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getSelectionModel().deselectAll();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
},
onUpdRow : function(grid, rowIndex){
		var selection = this.getStore().getAt(rowIndex);
		this.getView().deselect(this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);
},
onUpdWin : function(selection){
		if (selection){
			var win = Ext.create('mvc.view.plt.PltFreight.Win',{
				title : this.title+'>修改',
				insFlag : false
			});
			win.on('create',this.onUpdateRecord,this);
			win.show();
			win.setActiveRecord(selection);
		}
},
onDelRow : function(grid, rowIndex){
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
			function(btn) {
				if (btn != 'yes')
					return;
				var row = me.getStore().getAt(rowIndex);
				Ext.Ajax.request({
					url : '/plt_PltFreight_del?pkey='+row.get('bean.pkey')+'&rowVersion='+row.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							me.getStore().removeAt(rowIndex);
							Ext.example.msg(msg_title, msg_del);
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
});