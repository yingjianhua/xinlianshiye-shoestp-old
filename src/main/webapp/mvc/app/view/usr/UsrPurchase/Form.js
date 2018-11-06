Ext.define('mvc.view.usr.UsrPurchase.Form',{
extend : 'Ext.form.Panel',
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
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'bean.name',fieldLabel : '名字'}
	,{xtype : 'textfield',name : 'bean.surname',fieldLabel : '姓氏'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.sex',
					fieldLabel : '性别',
					store : Ext.create('mvc.combo.sys.SysOSex'),
					value : 0
				})
	,{xtype : 'imagefield',name : 'bean.icon',fieldLabel : '头像',labelWidth : 100}
	,{xtype : 'textfield',name : 'bean.email',fieldLabel : '邮箱'}
	,{xtype : 'textfield',name : 'bean.loginName',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '登录账号'}
	,{xtype : 'password',name : 'password',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '登录'}
	,{xtype : 'password',name : 'confirmPassword',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '确认密码'}
	,
		mvc.Tools.crtComboTrigger(false,'plt_PltErate','',{
					name : 'bean.currency',
					fieldLabel : '默认货币'
				})
	,{xtype : 'textfield',name : 'bean.telphone',fieldLabel : '手机号码'}
	,{xtype : 'textfield',name : 'bean.company',fieldLabel : '公司名称'}
	,{xtype : 'textfield',name : 'bean.address',fieldLabel : '地址'}
	,
		mvc.Tools.crtComboTrigger(true,'plt_PltCountry','',{
					name : 'bean.country',
					fieldLabel : '国家'
				})
	,
		mvc.Tools.crtComboTrigger(true,'usr_UsrMemberLevel','',{
					name : 'bean.memberLevel',
					fieldLabel : '会员等级'
				})
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey'
	});
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