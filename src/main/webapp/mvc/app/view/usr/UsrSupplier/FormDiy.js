Ext.define('mvc.view.usr.UsrSupplier.FormDiy',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
bodyPadding : '5 5 5 5',
url : base_path+'/usr_UsrSupplier_updDiy',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'bean.homePageDiyMobile',fieldLabel : '首页个性装修（移动）'}
	,{xtype : 'multilanguagefield',name : 'bean.homePageDiy',fieldLabel : '首页个性装修'}
	,{xtype : 'textfield',name : 'bean.productPageDiyMobile',fieldLabel : '产品页个性装修（移动）'}
	,{xtype : 'multilanguagefield',name : 'bean.productPageDiy',fieldLabel : '产品页个性装修'}
	,{xtype : 'textfield',name : 'bean.contactPageDiyMobile',fieldLabel : '联系页个性装修（移动）'}
	,{xtype : 'multilanguagefield',name : 'bean.contactPageDiy',fieldLabel : '联系页个性装修'}
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey'
	});
	this.items = [{
		layout : {
			type : 'table',
			columns : 2,
			itemCls : 'x-layout-table-items-form'
		},
		border : false,
		items : formFlds
	}];
	this.callParent(arguments);
}
});