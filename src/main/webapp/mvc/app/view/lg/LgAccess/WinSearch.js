Ext.define('mvc.view.lg.LgAccess.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'LgAccess_searchWin_',
width : 680,
layout : 'fit',
resizable : true,
iconCls : 'app-icon',
listCmp : null,
initComponent : function(){
	var strstore = Ext.create('mvc.combo.sys.SysOOptCht');
	var datestore = Ext.create('mvc.combo.sys.SysOOptCht');
	var numstore = Ext.create('mvc.combo.sys.SysOOptCht');
	var outstore = Ext.create('mvc.combo.sys.SysOOptCht');
	strstore.filter('value', new RegExp('^([1-4|9]|[1][0])$'));
	datestore.filter('value', new RegExp('^([3-9]|[1][0-1])$'));
	numstore.filter('value', new RegExp('^([3-9]|[1][0])$'));
	outstore.filter('value', new RegExp('^([3|4|9]|[1][0])$'));
	this.items ={
	anchor : '100%',
	plain : true,
	items : [{
			xtype : 'panel',
			layout : 'form',
			border : false,
			frame : false,
			bodyPadding : '5 5 5 5',
			url : base_path+'/lg_LgAccess_list',
			fieldDefaults : {
				labelWidth : 100,
				labelStyle : 'font-weight : bold'
			},
			items : [{
					xtype : 'form',
					border : false,
					layout : {
						type : 'table',
						columns : 6,
						itemCls : 'x-layout-table-items-form'
					},
					items : [{
								xtype : 'label',
								text : '用户'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_user',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'user'}
							
						,{
								xtype : 'label',
								text : '登录名'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_loginname',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'loginname'}
							
						,{
								xtype : 'label',
								text : '远程地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_remoteaddr',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'remoteaddr'}
							
						,{
								xtype : 'label',
								text : '请求地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_requesturl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'requesturl'}
							
						,{
								xtype : 'label',
								text : '请求参数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_params',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'params'}
							
						,{
								xtype : 'label',
								text : '处理用时'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_processtime',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'processtime',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '请求时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_requesttime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'requesttime',this.oldId + 'requesttimeEd',this.oldId + 'requesttime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'requesttime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'requesttime'
								},{
									xtype : 'datefieldexp',
									name : 'requesttimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'requesttimeEd'
								}
							
						,{
							xtype : 'label',
							text : '',
							colspan : 3
						}]
				}]
		}]
};
	this.buttonAlign = 'right',
	this.buttons =[{
		text : '重置',
		scope : this,
		iconCls : 'win-refresh-icon',
		handler : this.onReset
	},{
		text : '关闭',
		scope : this,
		iconCls : 'win-close-icon',
		handler : this.onClose
	},{
		text : '搜索',
		scope : this,
		iconCls : 'win-ok-icon',
		handler : this.onSearchAdv
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
onBetween : function(newv,label,dateEd){
	if(newv == 11) {
		this.down('#'+label).hide();
		this.down('#'+label).setValue(null);
		this.down('#'+dateEd).show();
	} else {
		this.down('#'+label).show();
		this.down('#'+dateEd).hide();
		this.down('#'+dateEd).setValue(null);
	}
},
onReset : function(){
		this.setActiveRecord(this.form.activeRecord);
},
onClose : function(){
		this.close();
},
onSearchAdv : function(){
	var array = mvc.Tools.advancedSearchValues(this.down('form'));
	this.listCmp.onSearchDo(array);
	this.close();
}
});