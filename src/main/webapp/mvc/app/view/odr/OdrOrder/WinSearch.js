Ext.define('mvc.view.odr.OdrOrder.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'OdrOrder_searchWin_',
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
			url : base_path+'/odr_OdrOrder_list',
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
								text : '用户'
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
								text : '订单号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_orderNum',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'orderNum'}
							
						,{
								xtype : 'label',
								text : '运单号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_expressNum',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'expressNum'}
							
						,{
								xtype : 'label',
								text : '发货方式'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_delivery',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'delivery'}
							
						,{
								xtype : 'label',
								text : '包裹备注'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_pagRemarks',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'pagRemarks'}
							
						,{
								xtype : 'label',
								text : '订单备注'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_odrRemarks',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'odrRemarks'}
							
						,{
								xtype : 'label',
								text : '下单时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_time',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'time',this.oldId + 'timeEd',this.oldId + 'time_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'time',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'time'
								},{
									xtype : 'datefieldexp',
									name : 'timeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'timeEd'
								}
							
						,{
								xtype : 'label',
								text : '订单状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_state',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'state',
											store : Ext.create('mvc.combo.odr.OdrOdrState')
										})
							
						,{
								xtype : 'label',
								text : '订单类型'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_type',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'type',
											store : Ext.create('mvc.combo.odr.OdrOdrType')
										})
							
						,{
								xtype : 'label',
								text : '运费'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_freightPrice',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'freightPrice',decimalPrecision : 4}
							
						,{
								xtype : 'label',
								text : '保险费'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_insurancePrice',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'insurancePrice',decimalPrecision : 4}
							
						,{
								xtype : 'label',
								text : '产品总价'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_prodPrice',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'prodPrice',decimalPrecision : 4}
							
						,{
								xtype : 'label',
								text : '总价'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_priceTotal',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'priceTotal',decimalPrecision : 4}
							
						,{
								xtype : 'label',
								text : '货币'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_currency',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'plt_PltErate','',{
											name : 'currency'
										})
							
						,{
								xtype : 'label',
								text : '地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_address',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'address'}
							
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
								text : '邮政编码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_postalcode',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'postalcode'}
							
						,{
								xtype : 'label',
								text : '电话号码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_phone',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'phone'}
							
						,{
								xtype : 'label',
								text : '支付内容'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_paycontent',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'paycontent'}
							
						,{
								xtype : 'label',
								text : '取消原因'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_odrCancel',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'odrCancel',
											store : Ext.create('mvc.combo.odr.OdrCancelType')
										})
							
						,{
								xtype : 'label',
								text : '支付方式'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_payType',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'payType',
											store : Ext.create('mvc.combo.odr.OdrPayType')
										})
							
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