Ext.define('mvc.view.prm.PrmGroupPurchase.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'PrmGroupPurchase_searchWin_',
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
			url : base_path+'/prm_PrmGroupPurchase_list',
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
								text : '活动标题'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_title',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'title'}
							
						,{
								xtype : 'label',
								text : '开始时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_startTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'startTime',this.oldId + 'startTimeEd',this.oldId + 'startTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'startTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'startTime'
								},{
									xtype : 'datefieldexp',
									name : 'startTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'startTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '结束时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_endTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'endTime',this.oldId + 'endTimeEd',this.oldId + 'endTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'endTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'endTime'
								},{
									xtype : 'datefieldexp',
									name : 'endTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'endTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '活动状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_status',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'status',
											store : Ext.create('mvc.combo.prm.PrmOStatus')
										})
							
						,{
								xtype : 'label',
								text : '提前预告'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_preTime',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'preTime',
											store : Ext.create('mvc.combo.prm.PrmOPreTime')
										})
							
						,{
								xtype : 'label',
								text : '供应商'
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
								bean : 'UsrSupplier',
								beanType : 'usr',
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
							
						,{
								xtype : 'label',
								text : '更新时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_updatedTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'updatedTime',this.oldId + 'updatedTimeEd',this.oldId + 'updatedTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'updatedTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'updatedTime'
								},{
									xtype : 'datefieldexp',
									name : 'updatedTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'updatedTimeEd'
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