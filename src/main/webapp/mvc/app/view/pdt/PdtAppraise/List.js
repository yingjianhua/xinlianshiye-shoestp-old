Ext.define('mvc.view.pdt.PdtAppraise.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_PdtAppraise',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('del') != -1)
mainActs.push({
		text : '删除',
		iconCls : 'del-icon',
		itemId : this.oldId+'del',
		scope : this,
		handler : this.onDel,
		disabled : this.lock
	});
this.columns = [{text : '产品',width : 100,dataIndex : 'bean.goodsPkey',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '采购商',width : 100,dataIndex : 'bean.usersPkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'usr',mn : 'view.usr.UsrPurchase.List'}
	,{text : '评论内容',width : 100,dataIndex : 'bean.appraiseContent',sortable : true}
	,{text : '图片描述1',width : 130,dataIndex : 'bean.image1',sortable : true,renderer : mvc.Tools.imgRenderer()}
	,{text : '图片描述2',width : 130,dataIndex : 'bean.image2',sortable : true,renderer : mvc.Tools.imgRenderer()}
	,{text : '图片描述3',width : 130,dataIndex : 'bean.image3',sortable : true,renderer : mvc.Tools.imgRenderer()}
	,{text : '图片描述4',width : 130,dataIndex : 'bean.image4',sortable : true,renderer : mvc.Tools.imgRenderer()}
	,{text : '图片描述5',width : 130,dataIndex : 'bean.image5',sortable : true,renderer : mvc.Tools.imgRenderer()}
	,{text : '图片描述6',width : 130,dataIndex : 'bean.image6',sortable : true,renderer : mvc.Tools.imgRenderer()}
	,{text : '产品满意度',width : 100,dataIndex : 'bean.goodsSatisfaction',sortable : true,renderer : mvc.Tools.optRenderer('pdt','Pdt','Satisfaction')}
	,{text : '供应商满意度',width : 100,dataIndex : 'bean.supplierSatisfaction',sortable : true,renderer : mvc.Tools.optRenderer('pdt','Pdt','Satisfaction')}
	,{text : '评论时间',width : 140,dataIndex : 'bean.appraiseTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.pdt.PdtAppraise'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '产品：'
			},{
				xtype : 'beantrigger',
				name : 'goodsPkey',
				bean : 'PdtProduct',
				beanType : 'pdt',
				emptyText : form_empty_text
			},'',{
				xtype : 'label',
				text : '产品满意度：'
			},{
				xtype : 'combo',
				name : 'goodsSatisfaction',
				mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,
				typeAhead : true,
				editable : false,
				emptyText : form_empty_text,
				store : Ext.create('mvc.combo.pdt.PdtSatisfaction')
			},'',{
				xtype : 'label',
				text : '供应商满意度：'
			},{
				xtype : 'combo',
				name : 'supplierSatisfaction',
				mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,
				typeAhead : true,
				editable : false,
				emptyText : form_empty_text,
				store : Ext.create('mvc.combo.pdt.PdtSatisfaction')
			},'',{
				xtype : 'button',
				text : '撤销',
				scope : this,
				iconCls : 'win-close-icon',
				handler : this.onSearchCancel
			},{
				xtype : 'splitbutton',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch,
				menu : [{text:'高级搜索',iconCls : 'win-ok-icon', scope : this,handler: this.onSearchAdv}]
			}]
	},{
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
		this.callParent(arguments);		mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'),this);},
listeners : {
	selectionchange : function(selModel, selected){
		if (this.roles.indexOf('del') != -1)
			this.down('#'+this.oldId+'del').setDisabled(selected.length === 0);	
}
},
onDel : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection){
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg, 
				function(btn) {
					if (btn != 'yes')
						return;
					var arr=new Array();
					var arrv = new Array();
					for(var i = 0; i < selection.length; i++){
						arr.push(selection[i].get('bean.pkey'));
						arrv.push(selection[i].get(BEAN_VERSION));
					}
					Ext.Ajax.request({
						url : base_path+'/pdt_PdtAppraise_delMulti?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
						success : function (response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success){
								me.getStore().remove(selection);
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
},
onDelRow : function(grid, rowIndex){
		var me = this;
		var row = me.getStore().getAt(rowIndex);
		Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/pdt_PdtAppraise_del?pkey='+row.get('bean.pkey')+'&rowVersion='+row.get(BEAN_VERSION),
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
},
onSearchCancel : function(){
		this.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
},
onSearch : function(){
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		this.onSearchDo(array);
},
onSearchAdv : function(){
		var win = Ext.create('mvc.view.pdt.PdtAppraise.WinSearch',{
			title : this.title+'>高级搜索',
			listCmp : this
		});
		win.show();
},
onSearchDo : function(array){
		this.getSelectionModel().deselectAll();
		if (array.length == 0){
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
}
});