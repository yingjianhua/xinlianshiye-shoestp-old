Ext.define('mvc.view.pdt.PdtAppraise.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'PdtAppraise_searchWin_',
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
			url : base_path+'/pdt_PdtAppraise_list',
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
								text : '产品'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_goodsPkey',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'goodsPkey',
								bean : 'PdtProduct',
								beanType : 'pdt',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '采购商'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_usersPkey',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'usr_UsrPurchase','',{
											name : 'usersPkey'
										})
							
						,{
								xtype : 'label',
								text : '评论内容'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_appraiseContent',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'appraiseContent'}
							
						,{
								xtype : 'label',
								text : '产品满意度'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_goodsSatisfaction',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'goodsSatisfaction',
											store : Ext.create('mvc.combo.pdt.PdtSatisfaction')
										})
							
						,{
								xtype : 'label',
								text : '供应商满意度'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_supplierSatisfaction',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'supplierSatisfaction',
											store : Ext.create('mvc.combo.pdt.PdtSatisfaction')
										})
							
						,{
								xtype : 'label',
								text : '评论时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_appraiseTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'appraiseTime',this.oldId + 'appraiseTimeEd',this.oldId + 'appraiseTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'appraiseTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'appraiseTime'
								},{
									xtype : 'datefieldexp',
									name : 'appraiseTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'appraiseTimeEd'
								}
							
						,{
								xtype : 'label',
								text : 'IP'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_appraiseIp',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'appraiseIp'}
							
						,{
							xtype : 'label',
							text : '',
							colspan : 3
						}]
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