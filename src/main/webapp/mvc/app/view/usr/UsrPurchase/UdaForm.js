Ext.define('mvc.view.usr.UsrPurchase.UdaForm',{
extend : 'Ext.form.Panel',
id : "UdaData",
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
			this.url = this.url + 'uda';
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'password',fieldLabel : '修改密码'}
	,{xtype : 'textfield',name : 'copyPassword',fieldLabel : '确认密码'}
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