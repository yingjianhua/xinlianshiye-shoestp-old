Ext.define('mvc.view.sys.SysOrgTemp.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'SysOrg_searchWin_',
width : 680,
layout : 'fit',
resizable : true,
iconCls : 'app-icon',
listStore : null,
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
			url : base_path+'/sys_SysOrg_list',
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
								text : '机构代码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_codeThis',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'codeThis'}
							
						,{
								xtype : 'label',
								text : '机构名称'
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
								text : '机构简称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_shortName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'shortName'}
							
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
								text : '上级机构'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_orgUp',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'sys_SysOrg','',{
											name : 'orgUp'
										})
							
						,{
								xtype : 'label',
								text : '机构状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_state',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'state',
											store : Ext.create('mvc.combo.sys.SysOOrgState')
										})
							
						,{
								xtype : 'label',
								text : '科目模板'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_templat',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'sys_SysTemplat','',{
											name : 'templat'
										})
							
						,{
								xtype : 'label',
								text : '存货计价方式'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_valuationMethods',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'valuationMethods',
											store : Ext.create('mvc.combo.sys.SysOValuationMethods')
										})
							
						,{
								xtype : 'label',
								text : '是否有国际贸易'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_internationTrade',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'internationTrade',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '币种'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_currency',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'currency',
											store : Ext.create('mvc.combo.sys.SysOCurrency')
										})
							
						,{
								xtype : 'label',
								text : '电话1'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tel1',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tel1'}
							
						,{
								xtype : 'label',
								text : '电话2'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tel2',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tel2'}
							
						,{
								xtype : 'label',
								text : '传真'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_fax',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'fax'}
							
						,{
								xtype : 'label',
								text : '网址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_website',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'website'}
							
						,{
								xtype : 'label',
								text : '地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_addr',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'addr'}
							
						,{
								xtype : 'label',
								text : '邮编'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_zipCode',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'zipCode'}
							
						,{
								xtype : 'label',
								text : '备注'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_rem',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'rem'}
							
						,{
								xtype : 'label',
								text : '更新员'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_updatedBy',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'sys_SysUser','',{
											name : 'updatedBy'
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
							,
								mvc.Tools.crtComboTrigger(true,'sys_SysUser','',{
											name : 'createdBy'
										})
							
						,{
							xtype : 'label',
							text : '',
							colspan : 3
						},{
								xtype : 'label',
								text : '工作日期'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_workDate',
											width : 80,
											value : 11,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'workDate_label',this.oldId + 'workDateEd',this.oldId + 'workDate_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'workDate',
									format : 'Y-m-d'
								},{
									xtype : 'label',
									text : '至',
									itemId : this.oldId + 'workDate_label'
								},{
									xtype : 'datefield',
									name : 'workDateEd',
									format : 'Y-m-d',
									colspan : 2,
									itemId : this.oldId + 'workDateEd'
								},{
									xtype : 'label',
									text : '',
									colspan : 3,
									hidden : true,
									itemId : this.oldId + 'workDate_empty'
								}
							
						,{
								xtype : 'label',
								text : '更新时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_updatedDateTime',
											width : 80,
											value : 11,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'updatedDateTime_label',this.oldId + 'updatedDateTimeEd',this.oldId + 'updatedDateTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'updatedDateTime',
									format : 'Y-m-d H:i:s'
								},{
									xtype : 'label',
									text : '至',
									itemId : this.oldId + 'updatedDateTime_label'
								},{
									xtype : 'datefield',
									name : 'updatedDateTimeEd',
									format : 'Y-m-d H:i:s',
									colspan : 2,
									itemId : this.oldId + 'updatedDateTimeEd'
								},{
									xtype : 'label',
									text : '',
									colspan : 3,
									hidden : true,
									itemId : this.oldId + 'updatedDateTime_empty'
								}
							
						,{
								xtype : 'label',
								text : '建档时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_createdDateTime',
											width : 80,
											value : 11,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'createdDateTime_label',this.oldId + 'createdDateTimeEd',this.oldId + 'createdDateTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'createdDateTime',
									format : 'Y-m-d H:i:s'
								},{
									xtype : 'label',
									text : '至',
									itemId : this.oldId + 'createdDateTime_label'
								},{
									xtype : 'datefield',
									name : 'createdDateTimeEd',
									format : 'Y-m-d H:i:s',
									colspan : 2,
									itemId : this.oldId + 'createdDateTimeEd'
								},{
									xtype : 'label',
									text : '',
									colspan : 3,
									hidden : true,
									itemId : this.oldId + 'createdDateTime_empty'
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
		handler : this.onSearch
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
onBetween : function(newv,label,dateEd,empty){
	if(newv == 11) {
		this.down('#'+label).show();
		this.down('#'+dateEd).show();
		this.down('#'+empty).hide();
	} else {
		this.down('#'+label).hide();
		this.down('#'+dateEd).hide();
		this.down('#'+empty).show();
	}
},
onReset : function(){
		this.setActiveRecord(this.form.activeRecord);
},
onClose : function(){
		this.close();
},
onSearch : function(){
	var array = mvc.Tools.advancedSearchValues(this.down('form'));
	if (array.length == 0){
		this.listStore.clearFilter();
		this.close();
		return;
	}
	this.listStore.clearFilter(true);
	this.listStore.filter(array);
	this.close();
}
});