Ext.define('mvc.view.pdt.PdtSpec.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'PdtSpec_searchWin_',
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
			url : base_path+'/pdt_PdtSpec_list',
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
								text : '名称'
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
								text : '产品'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_product',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'pdt_PdtProduct','',{
											name : 'product'
										})
							
						,{
								xtype : 'label',
								text : '产品顏色'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_color',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'pdt_PdtColor','',{
											name : 'color'
										})
							
						,{
								xtype : 'label',
								text : '产品尺寸'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_size',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'pdt_PdtSize','',{
											name : 'size'
										})
							
						,{
								xtype : 'label',
								text : '组合属性'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_keyName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'keyName'}
							
						,{
								xtype : 'label',
								text : 'jiage'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_price',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'price',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '加价'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_markup',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'markup',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '库存数量'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_storeCount',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'storeCount',decimalPrecision : 4}
							
						,{
								xtype : 'label',
								text : '单价'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_weight',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'weight',decimalPrecision : 4}
							
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