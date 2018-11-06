Ext.define('mvc.view.usr.UsrSupplier.List',{
extend : 'mvc.tools.RowexpanderGrid',
oldId : 'btn_UsrSupplier',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
//viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('doAppr') != -1)
mainActs.push({
		text : '审核',
		iconCls : 'doAppr-icon',
		itemId : this.oldId+'doAppr',
		scope : this,
		handler : this.onDoAppr,
		disabled : this.lock
	});
		if (this.roles.indexOf('unAppr') != -1)
mainActs.push({
		text : '弃审',
		iconCls : 'unAppr-icon',
		itemId : this.oldId+'unAppr',
		scope : this,
		handler : this.onUnAppr,
		disabled : this.lock
	});
		if (this.roles.indexOf('updBase') != -1)
mainActs.push({
		text : '基本信息',
		iconCls : 'upd-icon',
		itemId : this.oldId+'updBase',
		scope : this,
		handler : this.onUpdBase,
		disabled : this.lock
	});
		if (this.roles.indexOf('updPage') != -1)
mainActs.push({
		text : '页面资料',
		iconCls : 'upd-icon',
		itemId : this.oldId+'updPage',
		scope : this,
		handler : this.onUpdPage,
		disabled : this.lock
	});
		if (this.roles.indexOf('updDiy') != -1)
mainActs.push({
		text : '个性装修',
		iconCls : 'upd-icon',
		itemId : this.oldId+'updDiy',
		scope : this,
		handler : this.onUpdDiy,
		disabled : this.lock
	});
		if (this.roles.indexOf('updMarketing') != -1)
mainActs.push({
		text : '营销设置',
		iconCls : 'upd-icon',
		itemId : this.oldId+'updMarketing',
		scope : this,
		handler : this.onUpdMarketing,
		disabled : this.lock
	});
this.columns = [{text : '企业名称',width : 198,dataIndex : 'bean.name',sortable : true,expandCol:true}
	,{text : '状态',width : 162,dataIndex : 'bean.status',sortable : true,renderer : mvc.Tools.optRenderer('usr','Usr','OStatus')}
	,{text : '供应商分类',width : 121,dataIndex : 'bean.category',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'usr',mn : 'view.usr.UsrSupplierCategory.List'}
	,{text : '供应商认证',width : 150,dataIndex : 'bean.isAuth',sortable : true,renderer : mvc.Tools.optRenderer('usr','Usr','OIsAuth')}
	,{text : '网址',width : 190,dataIndex : 'bean.webSite',sortable : true}
	,{text : '排序号',width : 170,dataIndex : 'bean.sort',sortable : true}
	,{text : '法人',width : 100,dataIndex : 'bean.entity',sortable : true,expandCol:true,hidden:true}
	,{text : '注册资金',width : 100,dataIndex : 'bean.registeredCapital',sortable : true,expandCol:true,hidden:true}
	,{text : '企业类型',width : 100,dataIndex : 'bean.companyType',sortable : true,expandCol:true,hidden:true,renderer : mvc.Tools.JsonRenderer()}
	,{text : '企业性质',width : 100,dataIndex : 'bean.companyNature',sortable : true,expandCol:true,hidden:true,renderer : mvc.Tools.JsonRenderer()}
	,{text : '成立时间 ',width : 100,dataIndex : 'bean.companyEstablishTime',sortable : true,expandCol:true,hidden:true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '电话',width : 100,dataIndex : 'bean.telephone',sortable : true,expandCol:true,hidden:true}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.usr.UsrSupplier'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '名称：'
			},{
				xtype : 'textfield',
				name : 'name'
			},'',{
				xtype : 'label',
				text : '供应商分类：'
			},
				mvc.Tools.crtComboTrigger(true,'usr_UsrSupplierCategory','',{
							name : 'category'
						})
			,'',{
				xtype : 'label',
				text : '状态：'
			},{
				xtype : 'combo',
				name : 'status',
				mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,
				typeAhead : true,
				editable : false,
				emptyText : form_empty_text,
				store : Ext.create('mvc.combo.usr.UsrOStatus')
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
		if (this.roles.indexOf('doAppr') != -1)
			this.down('#'+this.oldId+'doAppr').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('unAppr') != -1)
			this.down('#'+this.oldId+'unAppr').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('updBase') != -1)
			this.down('#'+this.oldId+'updBase').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('updPage') != -1)
			this.down('#'+this.oldId+'updPage').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('updDiy') != -1)
			this.down('#'+this.oldId+'updDiy').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('updMarketing') != -1)
			this.down('#'+this.oldId+'updMarketing').setDisabled(selected.length === 0);	
}
},
onDoAppr : function(){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	var me = this;
	if (selection){
		Ext.MessageBox.confirm(msg_confirm_title, '供应商['+selection.get('bean.name') + '] - 审核确认吗？',
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/usr_UsrSupplier_approve?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							var bean  = Ext.create('mvc.model.usr.UsrSupplier',result);
							Ext.apply(selection.data, bean.data);
							selection.commit();
							me.getSelectionModel().deselectAll();
							me.getView().select(selection);
							Ext.example.msg(msg_title, '审核--成功');
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
onUnAppr : function(){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	var me = this;
	if (selection){
		Ext.MessageBox.confirm(msg_confirm_title, '供应商['+selection.get('bean.name') + '] - 弃审确认吗？',
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/usr_UsrSupplier_unapprove?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							var bean  = Ext.create('mvc.model.usr.UsrSupplier',result);
							Ext.apply(selection.data, bean.data);
							selection.commit();
							me.getSelectionModel().deselectAll();
							me.getView().select(selection);
							Ext.example.msg(msg_title, '弃审--成功');
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
onUpdateRecord : function(form, data){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	var bean = Ext.create('mvc.model.usr.UsrSupplier', data);
	Ext.apply(selection.data,bean.data);
	selection.commit();
	this.getView().select(selection);
	Ext.example.msg(msg_title, msg_text);			
},
onUpdBase : function(){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	if (selection){
		var win = Ext.create('mvc.view.usr.UsrSupplier.WinBase',{
				title : this.title+'>基本信息'
			});
		win.on('create',this.onUpdateRecord,this);
		win.show();
		win.setActiveRecord(selection);
	}
},
onUpdPage : function(){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	if (selection){
		var win = Ext.create('mvc.view.usr.UsrSupplier.WinPage',{
				title : this.title+'>页面信息'
			});
		win.on('create',this.onUpdateRecord,this);
		win.show();
		win.setActiveRecord(selection);
	}
},
onUpdDiy : function(){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	if (selection){
		var win = Ext.create('mvc.view.usr.UsrSupplier.WinDiy',{
				title : this.title+'>个性装修'
			});
		win.on('create',this.onUpdateRecord,this);
		win.show();
		win.setActiveRecord(selection);
	}	
},
onUpdMarketing : function(){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	if (selection){
		var win = Ext.create('mvc.view.usr.UsrSupplier.WinMarketing',{
				title : this.title+'>营销设置'
			});
		win.on('create',this.onUpdateRecord,this);
		win.show();
		win.setActiveRecord(selection);
	}
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
		var win = Ext.create('mvc.view.usr.UsrSupplier.WinSearch',{
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