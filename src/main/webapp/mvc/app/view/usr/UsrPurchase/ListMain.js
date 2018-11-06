Ext.define('mvc.view.usr.UsrPurchase.ListMain',{
extend : 'mvc.tools.RowexpanderGrid',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
//viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[{text : '头像',width : 100,dataIndex : 'bean.icon',sortable : true,renderer : mvc.Tools.imgRenderer(85)}
	,{text : '名字',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '姓氏',width : 100,dataIndex : 'bean.surname',sortable : true}
	,{text : '会员等级',width : 100,dataIndex : 'bean.memberLevel',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'usr',mn : 'view.usr.UsrMemberLevel.List'}
	,{text : '性别',width : 100,dataIndex : 'bean.sex',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OSex')}
	,{text : '邮箱',width : 100,dataIndex : 'bean.email',sortable : true}
	,{text : '注册时间',width : 140,dataIndex : 'bean.regTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '登录账号',width : 100,dataIndex : 'bean.loginName',sortable : true}
	,{text : '默认货币',width : 100,dataIndex : 'bean.currency',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'plt',mn : 'view.plt.PltErate.List'}
	,{text : '手机号码',width : 100,dataIndex : 'bean.telphone',sortable : true}
	,{text : '公司名称',width : 100,dataIndex : 'bean.company',sortable : true}
	,{text : '地址',width : 100,dataIndex : 'bean.address',sortable : true,expandCol:true,hidden:true}
	,{text : '国家',width : 100,dataIndex : 'bean.country',sortable : true,renderer : mvc.Tools.JsonRenderer(),md : 'plt',mn : 'view.plt.PltCountry.List'}
	,{text : 'FACEBOOK用户ID',width : 100,dataIndex : 'bean.facebookUserId',sortable : true,expandCol:true,hidden:true}
	,{text : '谷歌用户ID',width : 100,dataIndex : 'bean.googleUserId',sortable : true,expandCol:true,hidden:true}
	,{text : '最后登录时间',width : 140,dataIndex : 'bean.lastLoginTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	];
		this.store=Ext.create('mvc.store.usr.UsrPurchase');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
		this.dockedItems=[{
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
		this.callParent(arguments);},
onDelRow : function(grid, rowIndex){
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
			function(btn) {
				if (btn != 'yes')
					return;
				var row = me.getStore().getAt(rowIndex);
				Ext.Ajax.request({
					url : '/usr_UsrPurchase_del?pkey='+row.get('bean.pkey')+'&rowVersion='+row.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							me.getStore().removeAt(rowIndex);
							Ext.example.msg(msg_title, msg_del);
						}else{
							Ext.MessageBox.show({ 
								title : msg_title,
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
			}
		);
}
});