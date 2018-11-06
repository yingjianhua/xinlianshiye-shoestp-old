Ext.define('mvc.view.usr.UsrSupplierRole.ListAct',{
	extend : 'Ext.panel.Panel',
	loadMask : true,
	mainPkey : null, //主表的主键值，取LIST的默认条件
	layout : 'border',
	menuPanel:null,
	initComponent : function(){
		
		var urlMenu = base_path+'/usr_UsrAccess_listModuleName';
		var records = null;
		Ext.Ajax.request({
			async : false,
			url : urlMenu,
			method : 'GET',
			success : function(response) {
    			var rtn = Ext.JSON.decode(response.responseText, true);
    			records = rtn.items;		
			},
			failure : function(response) {
				Ext.example.msg(msg_title, msg_ajax);
			}
		});
		
		var typestore = Ext.create('Ext.data.Store', {
		    fields: ['value', 'text'],
		    data : records
		});
		//===============右侧面板内容
		this.items = [{
			xtype : 'panel',
			region : 'north',
			items : [{
				xtype : 'toolbar',
				items : [{
						xtype : 'label',
						text : '模块类型：'
					},{
						xtype : 'combobox',
						name : 'type',
						mode : 'local',
						valueField : 'value',triggerAction : 'all',
						forceSelection : true,typeAhead : true,editable : false,
						emptyText : form_empty_text,
						store : typestore,
						value : "0",
						listeners : {
							scope : this,
							change : function(field,newv,oldv,opts){
								this.refreshMenu(newv);
							}
						}
					},'->',{
						text : '反选',
						iconCls : 'win-ok-icon',
						scope : this,
						handler : this.onSelectInvert
					},{
						text : '保存',
						iconCls : 'win-save-icon',
						scope : this,
						handler : this.onSave
					},{
						text : '重置',
						iconCls : 'win-refresh-icon',
						scope : this,
						handler : this.onReset
					}]
			},{
				xtype : 'panel',
				layout: {type: 'hbox'},
				overflowY : 'scroll',
				items : [{
					xtype : 'button',text : '菜单',flex : 1
				},{
					xtype : 'button',text : '功能',flex : 2.1
				}]
			}]
		},{
		//===============右侧面板-中间菜单项N
			region : 'center',
			xtype : 'panel',
			action : 'menuPanel',
			loadMask : true,
			overflowY : 'scroll'
		}
		];
		this.callParent(arguments);
	},
	
	//模块类型组件变更事件
	refreshMenu : function(type){
		if (!this.mainPkey)
			return;
		var newpanel = this.getMenuPanel(type);
		this.menuPanel = newpanel;
		if (newpanel){
			var spanel = this.down('panel[action=menuPanel]');
			spanel.destroy();
			this.add(newpanel).show();
			this.doLayout();
		}
	},
	
	resetMainPkey : function(mainPkey){
		this.mainPkey = mainPkey;
		var combo = this.down('combobox[name=type]');
		this.refreshMenu(combo.getValue());
	},
	
	getMenuPanel : function(type){
		var me = this;
		var cmpmenus = [];
		var newpanel;
		var myMask = new Ext.LoadMask(me, {msg:"Please wait..."});  
   		myMask.show(); 
		Ext.Ajax.request({
			
			async : false,
			url : base_path+ '/usr_UsrSupplierRole_loadAuthorityByRole?pkey=' + me.mainPkey + '&jyType='
					+ type,
			method : 'GET',
			success : function(response) {
				var rtn = Ext.JSON.decode(response.responseText, true);
				for (i = 0; i < rtn.length; i++) {
					cmpmenus.push({
						xtype : Ext.create('mvc.view.usr.UsrSupplierRole.ListActSub',{infos : rtn[i]})
					});
				}
				newpanel = Ext.create('Ext.panel.Panel',{
					region : 'center',
					xtype : 'panel',
					action : 'menuPanel',
					loadMask : true,
					overflowY : 'scroll',
					items : cmpmenus
				});
			},
			failure : function(response) {
				Ext.example.msg(msg_title, msg_ajax);
			}
		});
		myMask.hide();
		return newpanel;
	},
	onSelectInvert : function(){
		var allBox = this.menuPanel.query("checkbox");
		Ext.Array.each(allBox, function(box) {
			box.setValue(!box.getValue())
		})
		
	},
	
	onSave : function(){
		var lines = '';
		var firstFlag = true;
		this.down('panel[action=menuPanel]').items.each(function(mp){
			mp.items.each(function(cp){
				if (cp.action == 'checkPanel'){
					cp.items.each(function(checkbox){
						if (checkbox.getValue()==true) {
							if(firstFlag) {
								lines += checkbox.inputValue;
								firstFlag = false;
							} else {
								lines += ","+checkbox.inputValue;
							}
						}
					});
				}
			});
		});
		var combo = this.down('combobox[name=type]');
		Ext.Ajax.request({
			url : base_path+ '/usr_UsrSupplierRole_updCtrl?pkey=' + this.mainPkey,
			method : 'GET',
			params : {lines : lines, type : combo.getValue()},
			scope : this,
			success : function (response, options) {
				var result = Ext.decode(response.responseText);
				if (result.success){
					Ext.example.msg(msg_title, '权限修改成功');
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
	},
	
	onReset : function(){
		var combo = this.down('combobox[name=type]');
		this.refreshMenu(combo.getValue());
	},
});