Ext.define('mvc.view.prm.PrmGroupPurchase.InfoForm',{
extend : 'Ext.form.Panel',
id : "InfoData",
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/prm_PrmGroupPurchase_',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			if (this.insFlag)
			this.url = this.url + 'send';
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'address',fieldLabel : '收货地址'}
	,{xtype : 'textfield',name : 'name',fieldLabel : '收货人'}
	,{xtype : 'textfield',name : 'phone',fieldLabel : '电话号码'}
	,{xtype : 'textfield',name : 'code',fieldLabel : '邮编'}
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