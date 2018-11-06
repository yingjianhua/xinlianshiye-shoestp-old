Ext.define('mvc.view.plt.PltErate.List',{
extend : 'mvc.tools.RowexpanderGrid',
oldId : 'btn_PltErate',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('ins') != -1)
mainActs.push({
		text : '新增',
		iconCls : 'ins-icon',
		itemId : this.oldId+'ins',
		scope : this,
		handler : this.onIns
	});
		if (this.roles.indexOf('upd') != -1)
mainActs.push({
		text : '修改',
		iconCls : 'upd-icon',
		itemId : this.oldId+'upd',
		scope : this,
		handler : this.onUpd,
		disabled : this.lock
	});
		if (this.roles.indexOf('doEnabled') != -1)
mainActs.push({
		text : '启用',
		iconCls : 'doEnabled-icon',
		itemId : this.oldId+'doEnabled',
		scope : this,
		handler : this.onDoEnabled,
		disabled : this.lock
	});
		if (this.roles.indexOf('unEnabled') != -1)
mainActs.push({
		text : '停用',
		iconCls : 'unEnabled-icon',
		itemId : this.oldId+'unEnabled',
		scope : this,
		handler : this.onUnEnabled,
		disabled : this.lock
	});
		if (this.roles.indexOf('defcur') != -1)
mainActs.push({
		text : '网站默认货币',
		iconCls : 'doEnabled-icon',
		itemId : this.oldId+'defcur',
		scope : this,
		handler : this.onDefcur,
		disabled : this.lock
	});
		if (this.roles.indexOf('supdefcur') != -1)
mainActs.push({
		text : '商家默认货币',
		iconCls : 'doEnabled-icon',
		itemId : this.oldId+'supdefcur',
		scope : this,
		handler : this.onSupdefcur,
		disabled : this.lock
	});
this.columns = [{text : 'LOGO',width : 60,dataIndex : 'bean.logo',sortable : true,renderer : mvc.Tools.imgRenderer(33, 20)}
	,{text : '货币简称',width : 100,dataIndex : 'bean.curName',sortable : true}
	,{text : '符号',width : 100,dataIndex : 'bean.symbol',sortable : true}
	,{text : '启用标志',width : 75,dataIndex : 'bean.enabled',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled')}
	,{text : '网站默认货币',width : 100,dataIndex : 'bean.siteCur',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '美元汇率',width : 100,dataIndex : 'bean.rate',sortable : true,align : 'right'}
	,{text : '商家默认货币',width : 100,dataIndex : 'bean.supCur',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '现用汇率',width : 100,dataIndex : 'bean.nowrate',sortable : true,align : 'right'}
	,{text : '建档员',width : 75,dataIndex : 'bean.createdBy',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'sys',mn : 'view.sys.SysUser.List',expandCol:true,hidden:true}
	,{text : '建档时间',width : 140,dataIndex : 'bean.createdTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),expandCol:true,hidden:true}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.plt.PltErate'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '货币简称：'
			},{
				xtype : 'textfield',
				name : 'curName'
			},'',{
				xtype : 'button',
				text : '撤销',
				scope : this,
				iconCls : 'win-close-icon',
				handler : this.onSearchCancel
			},{
				xtype : 'splitbutton',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch,
				menu : [{text:'高级搜索',iconCls : 'win-ok-icon', scope : this,handler: this.onSearchAdv}]
			}]
	},{
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
		this.callParent(arguments);		mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'),this);},
listeners : {
	selectionchange : function(selModel, selected){
		if (this.roles.indexOf('upd') != -1)
			this.down('#'+this.oldId+'upd').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('del') != -1)
			this.down('#'+this.oldId+'del').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('doEnabled') != -1)
			this.down('#'+this.oldId+'doEnabled').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('unEnabled') != -1)
			this.down('#'+this.oldId+'unEnabled').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('defcur') != -1)
			this.down('#'+this.oldId+'defcur').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('supdefcur') != -1)
			this.down('#'+this.oldId+'supdefcur').setDisabled(selected.length === 0);	
}
},
onSaveRecord : function(form, data){
		this.store.insert(0,data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);	
},
onIns : function(){
		var win = Ext.create('mvc.view.plt.PltErate.Win',{
			title : this.title+'>新增'
		});
		win.on('create',this.onSaveRecord,this);
		win.show();		
},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.plt.PltErate', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);			
},
onUpd : function(){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		this.onUpdWin(selection);
},
onUpdRow : function(grid, rowIndex){
		var selection = this.getStore().getAt(rowIndex);
		this.getView().deselect(this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);			
},
onUpdWin : function(selection){
		if (selection){
			var win = Ext.create('mvc.view.plt.PltErate.Win',{
				title : this.title+'>修改',
				insFlag : false
			});
			win.on('create',this.onUpdateRecord,this);
			win.show();
			win.setActiveRecord(selection);
		}			
},
onDel : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection){
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg, 
				function(btn) {
					if (btn != 'yes')
						return;
					var arr=new Array();
					var arrv = new Array();
					for(var i = 0; i < selection.length; i++){
						arr.push(selection[i].get('bean.pkey'));
						arrv.push(selection[i].get(BEAN_VERSION));
					}
					Ext.Ajax.request({
						url : base_path+'/plt_PltErate_delMulti?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
						success : function (response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success){
								me.getStore().remove(selection);
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
},
onDelRow : function(grid, rowIndex){
		var me = this;
		var row = me.getStore().getAt(rowIndex);
		Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/plt_PltErate_del?pkey='+row.get('bean.pkey')+'&rowVersion='+row.get(BEAN_VERSION),
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
},
onDoEnabled : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection) {
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, '您确认要启用所选的记录吗?',
					function(btn) {
						if (btn != 'yes')
							return;
						var arr = new Array();
						var arrv = new Array();
						for (var i = 0; i < selection.length; i++) {
							arr.push(selection[i].get('bean.pkey'));
							arrv.push(selection[i].get(BEAN_VERSION));
						}
						Ext.Ajax.request({
							url : base_path + '/plt_PltErate_doEnabled?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
							success : function(response, options) {
								var result = Ext.decode(response.responseText);
								if (result.success) {
									me.getStore().reload();
									me.getView().deselect(me.getView().getSelectionModel().getSelection());
									Ext.example.msg(msg_title, msg_submit);
								} else {
									Ext.MessageBox.show({
										title : msg_title,
										msg : result.msg,
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
								}
							}
						});
					});
		}
},
onUnEnabled : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection) {
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, '您确认要停用所选的记录吗?',
					function(btn) {
						if (btn != 'yes')
							return;
						var arr = new Array();
						var arrv = new Array();
						for (var i = 0; i < selection.length; i++) {
							arr.push(selection[i].get('bean.pkey'));
							arrv.push(selection[i].get(BEAN_VERSION));
						}
						Ext.Ajax.request({
							url : base_path + '/plt_PltErate_unEnabled?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
							success : function(response, options) {
								var result = Ext.decode(response.responseText);
								if (result.success) {
									me.getStore().reload();
									me.getView().deselect(me.getView().getSelectionModel().getSelection());
									Ext.example.msg(msg_title, msg_submit);
								} else {
									Ext.MessageBox.show({
										title : msg_title,
										msg : result.msg,
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
								}
							}
						});
					});
		}
},
onDefcur : function(){
	 var selection = this.getView().getSelectionModel().getSelection()[0];
	var me = this;
	if (selection){
		Ext.MessageBox.confirm(msg_confirm_title, '您确认要将['+selection.get('bean.curName')+']设置为网站默认货币吗？',
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/plt_PltErate_defcur?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
						    me.onSearchCancel(); 
							var bean  = Ext.create('mvc.model.plt.PltErate',result);
							Ext.apply(selection.data, bean.data);
							selection.commit();
							me.getSelectionModel().deselectAll();
							me.getView().select(selection);
							Ext.example.msg(msg_title, '处理--成功');
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
},
onSupdefcur : function(){
	 var selection = this.getView().getSelectionModel().getSelection()[0];
	var me = this;
	if (selection){
		Ext.MessageBox.confirm(msg_confirm_title, '您确认要将['+selection.get('bean.curName')+']设置为商家默认货币吗? <br/><br/>这将会对所有商家的产品产生影响!',
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/plt_PltErate_bcdefcur?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
						    me.onSearchCancel(); 
							var bean  = Ext.create('mvc.model.plt.PltErate',result);
							Ext.apply(selection.data, bean.data);
							selection.commit();
							me.getSelectionModel().deselectAll();
							me.getView().select(selection);
							Ext.example.msg(msg_title, '处理--成功');
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
},
onSearchCancel : function(){
		this.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
},
onSearch : function(){
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		this.onSearchDo(array);
},
onSearchAdv : function(){
		var win = Ext.create('mvc.view.plt.PltErate.WinSearch',{
			title : this.title+'>高级搜索',
			listCmp : this
		});
		win.show();
},
onSearchDo : function(array){
		this.getSelectionModel().deselectAll();
		if (array.length == 0){
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
}
});