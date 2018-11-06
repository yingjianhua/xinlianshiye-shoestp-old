Ext.define('mvc.view.usr.UsrPurchase.ListLineUsrPurchaseLine',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '名字',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '姓氏',width : 100,dataIndex : 'bean.surname',sortable : true}
	,{text : '邮件编码',width : 100,dataIndex : 'bean.emailcode',sortable : true}
	,{text : '国家管理',width : 100,dataIndex : 'bean.country',sortable : true,renderer : mvc.Tools.JsonRenderer(),md : 'plt',mn : 'view.plt.PltCountry.List'}
	,{text : '省份',width : 100,dataIndex : 'bean.region',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	,{text : '城市',width : 100,dataIndex : 'bean.city',sortable : true}
	,{text : '具体地址',width : 100,dataIndex : 'bean.address',sortable : true}
	,{text : '联系电话',width : 100,dataIndex : 'bean.phonenumber',sortable : true}
	,{text : '默认地址',width : 100,dataIndex : 'bean.isdefault',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '收货地址类型',width : 100,dataIndex : 'bean.addrsstype',sortable : true,renderer : mvc.Tools.optRenderer('usr','Usr','OAddress')}
	];
		this.store=Ext.create('mvc.store.usr.UsrPurchaseLine');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
		this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		xtype : 'pagingtoolbar',
		store : this.store,
		dock : 'bottom',
		displayInfo : true,
		displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : '没有数据',
		items : [{
				xtype : Ext.create('mvc.tools.ComboxPaging',{myList : this})
			}]
	}];
		this.callParent(arguments);}
});