Ext.define('mvc.view.plt.PltFreight.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'PltFreight_searchWin_',
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
			url : base_path+'/plt_PltFreight_list',
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
											name : 'optcht_userSys',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'userSys',
								bean : 'SysUser',
								beanType : 'sys',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '快递公司'
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
								text : '启用标志'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_enabled',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'enabled',
											store : Ext.create('mvc.combo.sys.SysOEnabled')
										})
							
						,{
								xtype : 'label',
								text : '快递单号查询地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_expressUrl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'expressUrl'}
							
						,{
								xtype : 'label',
								text : '排序'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sort',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'sort',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '使用接口'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_useInterface',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'useInterface',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '最小重量/体积/件数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_weightMin',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'weightMin',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '最大重量/体积/件数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_weightMax',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'weightMax',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '重量计算方式选择'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_type',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'type',
											store : Ext.create('mvc.combo.plt.PltWeightType')
										})
							
						,{
								xtype : 'label',
								text : '建档员'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_createdBy',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'createdBy',
								bean : 'SysUser',
								beanType : 'sys',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '建档时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_createdTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'createdTime',this.oldId + 'createdTimeEd',this.oldId + 'createdTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'createdTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'createdTime'
								},{
									xtype : 'datefieldexp',
									name : 'createdTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'createdTimeEd'
								}
							
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