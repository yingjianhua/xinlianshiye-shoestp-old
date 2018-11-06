Ext.define('mvc.view.usr.UsrPurchase.UdaWin',{
extend : 'Ext.window.Window',
layout : 'fit',
width : 600,
form : null,
resizable : true,
modal : true,
iconCls : 'app-icon',
insFlag : true,
initComponent : function(){
		this.items =[{
		anchor : '100%',
		plain : true,
		xtype : Ext.create('mvc.view.usr.UsrPurchase.UdaForm',{	insFlag : this.insFlag})
	}];
		this.buttonAlign = 'right',
this.buttons =[{
		text : '重置',
		iconCls : 'win-refresh-icon',
		scope : this,
		handler : this.onReset
	},{
		text : '关闭',
		iconCls : 'win-close-icon',
		scope : this,
		handler : this.onClose
	},{
		text : '确认修改',
		iconCls : 'win-save-icon',
		scope : this,
		handler : this.onSave
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
onReset : function(){
		this.setActiveRecord(this.form.activeRecord);
},
onClose : function(){
		this.close();
},
onSave : function(){
		var form = this.form.getForm();
		var formData = form.getValues();
		form.findField('pkey').setValue(this.data.unionPkey);
		form.findField('rowVersion').setValue(this.data.rowVersion);
		if (form.isValid()) {
			form.submit({
				url : this.form.url,
				submitEmptyText: false,
				type : 'ajax',
				params : formData,
				success : function(form, action) {
					this.onClose();
				},
				failure : mvc.Tools.formFailure(),
				waitTitle : wait_title,
				waitMsg : wait_msg,
				scope : this
			});
		}
}
});