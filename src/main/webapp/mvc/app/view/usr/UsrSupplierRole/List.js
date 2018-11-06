Ext.define('mvc.view.usr.UsrSupplierRole.List', {
	extend : 'Ext.panel.Panel',
	loadMask : true,
	action : 'mainPanel',
	roles : '',
	layout : 'border',
	initComponent : function() {
		this.items = [ Ext.create('mvc.view.usr.UsrSupplierRole.ListRole', {
			region : 'west',
			roles : this.roles,
			padding : '0 2 0 0',
			width : 300,
		}), Ext.create('mvc.view.usr.UsrSupplierRole.ListAct', {
			region : 'center',
			action : 'rightPanel',
			roles : this.roles
		}) ]
		this.callParent(arguments);
	},
	getStore : function() {
	},
});