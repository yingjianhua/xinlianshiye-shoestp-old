Ext.define('mvc.view.cnt.CntArticle.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'CntArticle_searchWin_',
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
			url : base_path+'/cnt_CntArticle_list',
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
								text : '标题'
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
								text : '分类'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_articleCategory',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'cnt_CntArticleCategory','',{
											name : 'articleCategory'
										})
							
						,{
								xtype : 'label',
								text : 'URL'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_url',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'url'}
							
						,{
								xtype : 'label',
								text : '自定义URL地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_rewriteUrl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'rewriteUrl'}
							
						,{
								xtype : 'label',
								text : '日期'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_date',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'date',this.oldId + 'dateEd',this.oldId + 'date_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'date',
									format : 'Y-m-d',
									itemId : this.oldId + 'date'
								},{
									xtype : 'datefieldexp',
									name : 'dateEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'dateEd'
								}
							
						,{
								xtype : 'label',
								text : 'TAG'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tag',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tag'}
							
						,{
								xtype : 'label',
								text : '关键词'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_keyword',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'keyword'}
							
						,{
								xtype : 'label',
								text : '简述'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_intro',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'intro'}
							
						,{
								xtype : 'label',
								text : '详细描述'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_detail',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'detail'}
							
						,{
								xtype : 'label',
								text : '显示'
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
								text : '建档员'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_createBy',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'createBy',
								bean : 'SysUser',
								beanType : 'sys',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '建档时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_createTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'createTime',this.oldId + 'createTimeEd',this.oldId + 'createTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'createTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'createTime'
								},{
									xtype : 'datefieldexp',
									name : 'createTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'createTimeEd'
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