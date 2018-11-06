Ext.define('mvc.view.pdt.PdtProduct.List',{
extend : 'Ext.panel.Panel',
oldId : 'PdtProduct_list_',
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
		text : '详情',
		iconCls : 'ins-icon',
		itemId : this.oldId+'ins',
		scope : this,
		handler : this.onUpd
	});
		if (this.roles.indexOf('upd') != -1)
mainActs.push({
		text : '审核',
		iconCls : 'upd-icon',
		itemId : this.oldId+'upd',
		scope : this,
		handler : this.onIns,
		disabled : this.lock
	});
		if (this.roles.indexOf('del') != -1)
mainActs.push({
		text : '弃审',
		iconCls : 'del-icon',
		itemId : this.oldId+'del',
		scope : this,
		handler : this.onDel,
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
						text : '名称：'
					},{
						xtype : 'textfield',
						name : 'name',
					},'',{
						xtype : 'label',
						text : 'sku：'
					},{
						xtype : 'textfield',
						name : 'sku'
					},'',{
						xtype : 'label',
						text : '供应商：'
					},{
						xtype : 'beantrigger',
						name : 'supplier',
						bean : 'UsrSupplier',
						beanType : 'usr',
						emptyText : form_empty_text
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
						title : '产品信息',
						collapsible : true,
						layout : {
							type : 'table',
							columns : 3
						},
						items : [{xtype : 'multilanguagefield',name : 'bean.name',fieldLabel : '名称'}
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
				xtype : Ext.create('mvc.view.pdt.PdtProduct.ListMain',{
							title : '产品',
							itemId : this.oldId+'maintable',
							iconCls : 'tab-user-icon',
							roles : this.roles,
							listeners : 							 {
								scope : this,
				                selectionchange: function(model, records) {
				                    if (records.length === 1){
				                        this.mdMain.getForm().loadRecord(records[0]);
        								this.mdLineTable.store.filter([{'id':'filter', 'property':'product','value':records[0].get('bean.pkey')}]);
    									if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(false);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(false);
				                    }else{
				                    	this.mdMain.getForm().reset();
				                    	this.mdLineTable.store.removeAll();
				                    	if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(true);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(true);
				                    }
				                }
			                }

						})
			},{
				xtype : Ext.create('mvc.view.pdt.PdtProduct.ListLinePdtSpec',{
							title : '产品规格',
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
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
	if (selection){
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, "审核之后产品将可以上架销售", 
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
					url : base_path+'/pdt_PdtProduct_verify?pkey='+arr.toString()+'&verify=true',
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							Ext.apply(selection[0].data,result);
							selection[0].commit();
							Ext.example.msg(msg_title, "审核成功");
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
onUpd : function(){
		var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
		this.mdMainTable.onUpdWin(selection);
},
onDel : function(){
		var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
		if (selection){
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, "弃审之后产品将下架,在审核之后才能重新上架", 
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
						url : base_path+'/pdt_PdtProduct_verify?pkey='+arr.toString()+'&verify=false',
						success : function (response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success){
								Ext.apply(selection[0].data,result);
								selection[0].commit();
								Ext.example.msg(msg_title, "弃审成功,商品已下架");
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
		var win = Ext.create('mvc.view.pdt.PdtProduct.WinSearch',{
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