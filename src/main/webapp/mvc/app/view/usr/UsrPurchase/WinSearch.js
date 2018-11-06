Ext.define('mvc.view.usr.UsrPurchase.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'UsrPurchase_searchWin_',
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
			url : base_path+'/usr_UsrPurchase_list',
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
								text : '名字'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_name',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'name'}
							
						,{
								xtype : 'label',
								text : '姓氏'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_surname',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'surname'}
							
						,{
								xtype : 'label',
								text : '性别'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sex',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'sex',
											store : Ext.create('mvc.combo.sys.SysOSex')
										})
							
						,{
								xtype : 'label',
								text : '邮箱'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_email',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'email'}
							
						,{
								xtype : 'label',
								text : '注册时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_regTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'regTime',this.oldId + 'regTimeEd',this.oldId + 'regTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'regTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'regTime'
								},{
									xtype : 'datefieldexp',
									name : 'regTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'regTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '登录账号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_loginName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'loginName'}
							
						,{
								xtype : 'label',
								text : '默认货币'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_currency',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'plt_PltErate','',{
											name : 'currency'
										})
							
						,{
								xtype : 'label',
								text : '手机号码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_telphone',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'telphone'}
							
						,{
								xtype : 'label',
								text : '公司名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_company',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'company'}
							
						,{
								xtype : 'label',
								text : '地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_address',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'address'}
							
						,{
								xtype : 'label',
								text : '最后登录时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_lastLoginTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'lastLoginTime',this.oldId + 'lastLoginTimeEd',this.oldId + 'lastLoginTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'lastLoginTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'lastLoginTime'
								},{
									xtype : 'datefieldexp',
									name : 'lastLoginTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'lastLoginTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '国家'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_country',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'plt_PltCountry','',{
											name : 'country'
										})
							
						,{
								xtype : 'label',
								text : 'FACEBOOK用户ID'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_facebookUserId',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'facebookUserId'}
							
						,{
								xtype : 'label',
								text : '谷歌用户ID'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_googleUserId',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'googleUserId'}
							
						,{
								xtype : 'label',
								text : '会员等级'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_memberLevel',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'usr_UsrMemberLevel','',{
											name : 'memberLevel'
										})
							
						]
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