Ext.define('mvc.view.plt.PltCountry.List',{
extend : 'Ext.panel.Panel',
oldId : 'PltCountry_list_',
loadMask : true,
multiSelect : true,
roles : '',
layout : 'border',
lock : true,
mdSearch : null,
mdAct : null,
mdMain : null,
mdMainTable : null,
mdLineTable : null,
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('ins') != -1)
mainActs.push({
		text : '新增',
		iconCls : 'ins-icon',
		itemId : this.oldId+'ins',
		scope : this,
		handler : this.onIns
	});
		if (this.roles.indexOf('upd') != -1)
mainActs.push({
		text : '修改',
		iconCls : 'upd-icon',
		itemId : this.oldId+'upd',
		scope : this,
		handler : this.onUpd,
		disabled : this.lock
	});
		if (this.roles.indexOf('del') != -1)
mainActs.push({
		text : '删除',
		iconCls : 'del-icon',
		itemId : this.oldId+'del',
		scope : this,
		handler : this.onDel,
		disabled : this.lock
	});
		if (this.roles.indexOf('doEnabled') != -1)
mainActs.push({
		text : '启用',
		iconCls : 'doEnabled-icon',
		itemId : this.oldId+'doEnabled',
		scope : this,
		handler : this.onDoEnabled,
		disabled : this.lock
	});
		if (this.roles.indexOf('unEnabled') != -1)
mainActs.push({
		text : '停用',
		iconCls : 'unEnabled-icon',
		itemId : this.oldId+'unEnabled',
		scope : this,
		handler : this.onUnEnabled,
		disabled : this.lock
	});
		if (this.roles.indexOf('hot') != -1)
			mainActs.push({
					text : '热门国家',
					iconCls : 'doEnabled-icon',
					itemId : this.oldId+'hot',
					scope : this,
					handler : this.onHot,
					disabled : this.lock
	});		
		this.items =[{
		region : 'north',
		xtype : 'panel',
		border : false,
		items : [{
				xtype : 'toolbar',
				itemId : this.oldId+'search',
				items : [{
						xtype : 'label',
						text : '国家：'
					},{
						xtype : 'textfield',
						name : 'name'
					},'',{
						xtype : 'label',
						text : '国家简称：'
					},{
						xtype : 'textfield',
						name : 'shortName'
					},'',{
						xtype : 'label',
						text : '区号：'
					},{
						xtype : 'textfield',
						name : 'zone'
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
				xtype : 'toolbar',
				itemId : this.oldId+'act',
				items : mainActs
			},{
				xtype : 'form',
				itemId :  this.oldId+'main',
				bodyPadding : '5 5 0 5',
				fieldDefaults : {
					anchor : '100%',
					labelWidth : 100,
					width : 275,
					labelAlign : 'right',
					readOnly : true
				},
				items : [{
						xtype : 'fieldset',
						title : '国家管理信息',
						collapsible : true,
						layout : {
							type : 'table',
							columns : 3
						},
						items : [{
								xtype : 'hiddenfield',
								name : 'bean.pkey'
							},{xtype : 'multilanguagefield',name : 'bean.name',fieldLabel : '国家'}
							]
					}]
			}]
	},{
		region : 'center',
		xtype : 'tabpanel',
		tabBar : {
			style : 'background:#fff'
		},
		items : [{
				xtype : Ext.create('mvc.view.plt.PltCountry.ListMain',{
							title : '国家管理',
							itemId : this.oldId+'maintable',
							iconCls : 'tab-user-icon',
							roles : this.roles,
							listeners : 							 {
								scope : this,
				                selectionchange: function(model, records) {
				                    if (records.length === 1){
				                        this.mdMain.getForm().loadRecord(records[0]);
        								this.mdLineTable.store.filter([{'id':'filter', 'property':'main','value':records[0].get('bean.pkey')}]);
    									if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(false);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(false);
										if (this.roles.indexOf('doEnabled') != -1){
											if(records[0].data["bean.enabled"]==1)
											{
												this.down('#'+this.oldId+'unEnabled').setDisabled(false);
												this.down('#'+this.oldId+'doEnabled').setDisabled(true);
											}
										}
										if (this.roles.indexOf('unEnabled') != -1) {
											if(records[0].data["bean.enabled"]==0)
											{
												this.down('#'+this.oldId+'unEnabled').setDisabled(true);
												this.down('#'+this.oldId+'doEnabled').setDisabled(false);
											}
											
										}
										if (this.roles.indexOf('hot') != -1)
											this.down('#'+this.oldId+'hot').setDisabled(false);											
				                    }else{
				                    	this.mdMain.getForm().reset();
				                    	this.mdLineTable.store.removeAll();
				                    	if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(true);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(true);
										if (this.roles.indexOf('doEnabled') != -1)
											this.down('#'+this.oldId+'doEnabled').setDisabled(true);
										if (this.roles.indexOf('unEnabled') != -1)
											this.down('#'+this.oldId+'unEnabled').setDisabled(true);
									  	if (this.roles.indexOf('hot') != -1)
											this.down('#'+this.oldId+'hot').setDisabled(true);											
				                    }
				                }
			                }

						})
			},{
				xtype : Ext.create('mvc.view.plt.PltCountry.ListLinePltProvince',{
							title : '省份',
							itemId : this.oldId+'linetable',
							iconCls : 'tab-user-icon'
						})
			}]
	}];
		this.callParent(arguments);
		this.mdSearch = this.down('#'+this.oldId+'search');
		this.mdAct = this.down('#'+this.oldId+'act');
		this.mdMain = this.down('#'+this.oldId+'main');
		this.mdMainTable = this.down('#'+this.oldId+'maintable');
		this.mdLineTable = this.down('#'+this.oldId+'linetable');
		mvc.Tools.onENTER2SearchBar(this.mdSearch,this);
		if (mainActs.length == 0)
			this.down('[region=north]').remove(this.mdAct);
},
getStore : function(){
		return this.mdMainTable.store;
},
onSaveRecord : function(form, data){
		this.mdMainTable.store.insert(0,data);
		this.mdMainTable.getView().select(0);
		Ext.example.msg(msg_title, msg_text);
},
onIns : function(){
		var win = Ext.create('mvc.view.plt.PltCountry.Win',{
			title : this.title+'>新增'
		});
		win.on('create',this.onSaveRecord,this);
		win.show();
},
onUpd : function(){
		var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
		this.mdMainTable.onUpdWin(selection);
},
onHot : function()
{
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
},
onDel : function(){
		var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
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
						url : base_path+'/plt_PltCountry_delMulti?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
						success : function (response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success){
								me.mdMainTable.getStore().remove(selection);
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
onDoEnabled : function(){
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
	if (selection) {
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, '您确认要启用所选的记录吗?',
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
						url : base_path + '/plt_PltCountry_doEnabled?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
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
},
onUnEnabled : function(){
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
	if (selection) {
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, '您确认要停用所选的记录吗?',
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
						url : base_path + '/plt_PltCountry_unEnabled?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
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
},
onSearchCancel : function(){
		this.mdMainTable.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.mdSearch);
		this.mdMainTable.store.clearFilter();
},
onSearch : function(){
		var array = mvc.Tools.searchValues(this.mdSearch);
		this.onSearchDo(array);
},
onSearchAdv : function(){
		var win = Ext.create('mvc.view.plt.PltCountry.WinSearch',{
			title : this.title+'>高级搜索',
			listCmp : this
		});
		win.show();
},
onSearchDo : function(array){
		this.mdMainTable.getSelectionModel().deselectAll();
		if (array.length == 0){
			this.mdMainTable.store.clearFilter();
			return;
		}
		this.mdMainTable.store.clearFilter(true);
		this.mdMainTable.store.filter(array);
}
});