Ext.define('mvc.view.usr.UsrMemberLevel.ImptWin',{
extend : 'Ext.window.Window',
width : 400,
layout : 'fit',
form : null,
resizable : true,
modal : true,
iconCls : 'app-icon',
insFlag : true,
initComponent : function(){
		this.items ={
	anchor : '100%',
	plain : true,
	xtype : Ext.create('mvc.view.usr.UsrMemberLevel.ImptForm',{	insFlag : this.insFlag})
};
		this.callParent(arguments);
}
});