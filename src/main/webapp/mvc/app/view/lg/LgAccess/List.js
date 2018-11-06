Ext.define('mvc.view.lg.LgAccess.List', {
	extend : 'mvc.tools.RowexpanderGrid',
	oldId : 'btn_LgAccess',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {
		selType : 'checkboxmodel'
	},
	initComponent : function() {
		var mainActs = [];
		if (this.roles.indexOf('query') != -1)
			mainActs.push({
				text : '统计',
				iconCls : 'app-icon',
				itemId : this.oldId + 'query',
				scope : this,
				handler : this.onQuery
			});
		this.columns = [ {
			text : '用户',
			width : 100,
			dataIndex : 'bean.user',
			sortable : true,
		}, {
			text : '登录名',
			width : 120,
			dataIndex : 'bean.loginname',
			sortable : true
		}, {
			text : '远程地址',
			width : 100,
			dataIndex : 'bean.remoteaddr',
			sortable : true
		}, {
			text : '请求地址',
			width : 500,
			dataIndex : 'bean.requesturl',
			sortable : true
		}, {
			text : '请求参数',
			width : 500,
			dataIndex : 'bean.params',
			sortable : true,
			expandCol:true,
			hidden:true
		}, {
			text : '是否成功',
			width : 70,
			dataIndex : 'bean.success',
			sortable : true
		}, {
			text : '备注',
			width : 200,
			dataIndex : 'bean.remark',
			sortable : true,
			expandCol:true,
			hidden:true
			
		}, {
			text : '处理用时',
			width : 100,
			align : 'right',
			dataIndex : 'bean.processtime',
			sortable : true,
			expandCol:true,
			hidden:true
		}, {
			text : '请求时间',
			width : 140,
			dataIndex : 'bean.requesttime',
			sortable : true,
			renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),
			expandCol:true,
			hidden:true
		} ];
		if (mainActs.length > 0)
			this.tbar = mainActs;
		this.store = Ext.create('mvc.store.lg.LgAccess');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({
			cellclick : mvc.Tools.onCellclick
		});
		this.dockedItems = [ {
			dock : 'top',
			xtype : 'toolbar',
			items : [ {
				xtype : 'label',
				text : '用户：'
			}, {
				xtype : 'textfield',
				name : 'user'
			}, '',{
				xtype : 'label',
				text : '登录名：'
			}, {
				xtype : 'textfield',
				name : 'loginname'
			}, '',{
				xtype : 'label',
				text : '请求地址：'
			}, {
				xtype : 'textfield',
				name : 'requesturl'
			}, '', {
				xtype : 'button',
				text : '撤销',
				scope : this,
				iconCls : 'win-close-icon',
				handler : this.onSearchCancel
			}, {
				xtype : 'splitbutton',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch,
				menu : [ {
					text : '高级搜索',
					iconCls : 'win-ok-icon',
					scope : this,
					handler : this.onSearchAdv
				} ]
			} ]
		}, {
			xtype : 'pagingtoolbar',
			store : this.store,
			dock : 'bottom',
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : '没有数据',
			items : [ {
				xtype : Ext.create('mvc.tools.ComboxPaging', {
					myList : this
				})
			} ]
		} ];
		this.callParent(arguments);
		mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'), this);
	},
	onQuery : function() {
		var win = Ext.create('mvc.view.lg.LgAccess.Win', {
			title : this.title + '>统计'
		});
		win.show();
	},
	onSearchCancel : function() {
		this.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
	},
	onSearch : function() {
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		this.onSearchDo(array);
	},
	onSearchAdv : function() {
		var win = Ext.create('mvc.view.lg.LgAccess.WinSearch', {
			title : this.title + '>高级搜索',
			listCmp : this
		});
		win.show();
	},
	onSearchDo : function(array) {
		this.getSelectionModel().deselectAll();
		if (array.length == 0) {
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
	}
});