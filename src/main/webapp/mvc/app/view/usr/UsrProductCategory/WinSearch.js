Ext.define('mvc.view.usr.UsrProductCategory.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'UsrProductCategory_searchWin_',
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
			url : base_path+'/usr_UsrProductCategory_list',
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
											name : 'optcht_name',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'name'}
							
						,{
								xtype : 'label',
								text : '隶属分类'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_categoryUp',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'usr_UsrProductCategory','',{
											name : 'categoryUp'
										})
							
						,{
								xtype : 'label',
								text : '所属供应商'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_supplier',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'usr_UsrSupplier','',{
											name : 'supplier'
										})
							
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
								text : '标题'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_seoTitleEn',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'seoTitleEn'}
							
						,{
								xtype : 'label',
								text : '关键词'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_seoKeywordEn',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'seoKeywordEn'}
							
						,{
								xtype : 'label',
								text : '简述'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_seoDescriptionEn',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'seoDescriptionEn'}
							
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