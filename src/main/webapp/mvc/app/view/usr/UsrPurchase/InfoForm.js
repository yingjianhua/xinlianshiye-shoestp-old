Ext.define('mvc.view.usr.UsrPurchase.InfoForm',{
extend : 'Ext.form.Panel',
id : "InfoData",
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/usr_UsrPurchase_',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			if (this.insFlag)
			this.url = this.url + 'bcme';
			var formFlds = [];
			formFlds.push
(mvc.Tools.crtComboTrigger(false,'usr_UsrSupplierCategory','',{
	name : 'usrInfo.category',
	fieldLabel : '供应商分类'
})
,{xtype : 'numberfield',name : 'usrInfo.sort',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '排序号',allowDecimals : false}
	,mvc.Tools.crtComboForm(false,{
		name : 'usrInfo.isAuth',
		fieldLabel : '供应商认证',
		store : Ext.create('mvc.combo.usr.UsrOIsAuth'),
		value : 0
	})
	,{xtype : 'textfield',name : 'usrInfo.showName',fieldLabel : '网站显示名称'}
	,{xtype : 'textfield',name : 'usrInfo.entity',fieldLabel : '企业法人'}
	,{xtype : 'textfield',name : 'usrInfo.companyType',fieldLabel : '企业类型'}
	,{xtype : 'textfield',name : 'usrInfo.password',fieldLabel : '请输入密码确认'}
	,{xtype : 'textfield',name : 'usrInfo.copyPassword',fieldLabel : '核实密码'}
	,{xtype : 'hidden',name : 'pkey'}
	,{xtype : 'hidden',name : 'rowVersion'});
		
	this.items = [{
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		border : false,
		items : formFlds
	}];
	this.callParent(arguments);
}
});