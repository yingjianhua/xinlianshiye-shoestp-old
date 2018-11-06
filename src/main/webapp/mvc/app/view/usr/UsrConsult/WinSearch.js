Ext.define('mvc.view.usr.UsrConsult.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'UsrConsult_searchWin_',
width : 680,
layout : 'fit',
resizable : true,
iconCls : 'app-icon',
listCmp : null,
initComponent : function(){
	var strstore = Ext.create('mvc.combo.sys.SysOOptCht');
	var datestore = Ext.create('mvc.combo.sys.SysOOptCht');
	var numstore = Ext.create('mvc.combo.sys.SysOOptCht');
	var outstore = Ext.create('mvc.combo.sys.SysOOptCht');
	strstore.filter('value', new RegExp('^([1-4|9]|[1][0])$'));
	datestore.filter('value', new RegExp('^([3-9]|[1][0-1])$'));
	numstore.filter('value', new RegExp('^([3-9]|[1][0])$'));
	outstore.filter('value', new RegExp('^([3|4|9]|[1][0])$'));
	this.items ={
	anchor : '100%',
	plain : true,
	items : [{
			xtype : 'panel',
			layout : 'form',
			border : false,
			frame : false,
			bodyPadding : '5 5 5 5',
			url : base_path+'/usr_UsrConsult_list',
			fieldDefaults : {
				labelWidth : 100,
				labelStyle : 'font-weight : bold'
			},
			items : [{
					xtype : 'form',
					border : false,
					layout : {
						type : 'table',
						columns : 6,
						itemCls : 'x-layout-table-items-form'
					},
					items : [{
								xtype : 'label',
								text : '标题'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_title',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'title'}
							
						,{
								xtype : 'label',
								text : '国家管理'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_country',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'plt_PltCountry','',{
											name : 'country'
										})
							
						,{
								xtype : 'label',
								text : '产品'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_product',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'product',
								bean : 'PdtProduct',
								beanType : 'pdt',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '有新消息'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_haveNewMsg',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'haveNewMsg',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '内容'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_content',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'content'}
							
						,{
								xtype : 'label',
								text : '剩余抢单次数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_count',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'count',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '商品数量'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_quantity',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'quantity',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '采购商'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_purchase',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'purchase',
								bean : 'UsrPurchase',
								beanType : 'usr',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '名字'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_name',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'name'}
							
						,{
								xtype : 'label',
								text : '邮箱'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_email',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'email'}
							
						,{
								xtype : 'label',
								text : '创建时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_createTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'createTime',this.oldId + 'createTimeEd',this.oldId + 'createTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'createTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'createTime'
								},{
									xtype : 'datefieldexp',
									name : 'createTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'createTimeEd'
								}
							
						]
				}]
		}]
};
	this.buttonAlign = 'right',
	this.buttons =[{
		text : '重置',
		scope : this,
		iconCls : 'win-refresh-icon',
		handler : this.onReset
	},{
		text : '关闭',
		scope : this,
		iconCls : 'win-close-icon',
		handler : this.onClose
	},{
		text : '搜索',
		scope : this,
		iconCls : 'win-ok-icon',
		handler : this.onSearchAdv
	}];
		this.callParent(arguments);
		this.addEvents('create');
		this.form = this.items.items[0];
},
setActiveRecord : function(record){
		this.form.activeRecord = record;
		if (record || this.form.activeRecord) {
			this.form.getForm().loadRecord(record);
		} else {
			this.form.getForm().reset();
		}
},
onBetween : function(newv,label,dateEd){
	if(newv == 11) {
		this.down('#'+label).hide();
		this.down('#'+label).setValue(null);
		this.down('#'+dateEd).show();
	} else {
		this.down('#'+label).show();
		this.down('#'+dateEd).hide();
		this.down('#'+dateEd).setValue(null);
	}
},
onReset : function(){
		this.setActiveRecord(this.form.activeRecord);
},
onClose : function(){
		this.close();
},
onSearchAdv : function(){
	var array = mvc.Tools.advancedSearchValues(this.down('form'));
	this.listCmp.onSearchDo(array);
	this.close();
}
});