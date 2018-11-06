Ext.define('mvc.view.pdt.PdtProduct.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'PdtProduct_searchWin_',
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
			url : base_path+'/pdt_PdtProduct_list',
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
								text : '产品审核'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isVerify',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isVerify',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '审核人员'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_verifyBy',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'verifyBy',
								bean : 'SysUser',
								beanType : 'sys',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '审核时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_verifyTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'verifyTime',this.oldId + 'verifyTimeEd',this.oldId + 'verifyTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'verifyTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'verifyTime'
								},{
									xtype : 'datefieldexp',
									name : 'verifyTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'verifyTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '产品类目'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_category',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'pdt_PdtCat','',{
											name : 'category'
										})
							
						,{
								xtype : 'label',
								text : '店铺类目'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_categoryDiy',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'usr_UsrProductCategory','',{
											name : 'categoryDiy'
										})
							
						,{
								xtype : 'label',
								text : '编号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_code',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'code'}
							
						,{
								xtype : 'label',
								text : 'SKU（出厂编码）'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sku',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'sku'}
							
						,{
								xtype : 'label',
								text : '供应商'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_supplier',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'supplier',
								bean : 'UsrSupplier',
								beanType : 'usr',
								emptyText : form_empty_text
							}
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
							
						,{
								xtype : 'label',
								text : '市场价$'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_mktPrice',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'mktPrice',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '商城价$'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_curPrice',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'curPrice',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '进货价$'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_purPrice',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'purPrice',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '批发价'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_wsPrice',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'wsPrice',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '起订量'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_minOq',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'minOq',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '最大购买量'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_maxOq',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'maxOq',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '产品销量'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sales',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'sales',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '库存'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_stock',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'stock',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '警告库存'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_warnStock',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'warnStock',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '商品属性'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_normAttr',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'normAttr'}
							
						,{
								xtype : 'label',
								text : '规格属性'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sizeAttr',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'sizeAttr'}
							
						,{
								xtype : 'label',
								text : '颜色属性'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_colorAttr',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'colorAttr'}
							
						,{
								xtype : 'label',
								text : '脱销状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_stockOut',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'stockOut',
											store : Ext.create('mvc.combo.pdt.PdtOStockOut')
										})
							
						,{
								xtype : 'label',
								text : '销售状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_state',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'state',
											store : Ext.create('mvc.combo.pdt.PdtOState')
										})
							
						,{
								xtype : 'label',
								text : '定时上架'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_soldInTime',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'soldInTime',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '上架时间(开始)'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_soldTimeB',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'soldTimeB',this.oldId + 'soldTimeBEd',this.oldId + 'soldTimeB_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'soldTimeB',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'soldTimeB'
								},{
									xtype : 'datefieldexp',
									name : 'soldTimeBEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'soldTimeBEd'
								}
							
						,{
								xtype : 'label',
								text : '上架时间(结束)'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_soldTimeE',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'soldTimeE',this.oldId + 'soldTimeEEd',this.oldId + 'soldTimeE_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'soldTimeE',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'soldTimeE'
								},{
									xtype : 'datefieldexp',
									name : 'soldTimeEEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'soldTimeEEd'
								}
							
						,{
								xtype : 'label',
								text : '默认评论'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isDefaultReview',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isDefaultReview',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '默认评论平均分'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_defaultReviewRating',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'defaultReviewRating',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '默认评论人数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_defaultReviewCount',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'defaultReviewCount',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '收藏数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_favoriteCount',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'favoriteCount',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '产品'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sourceProduct',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'sourceProduct',
								bean : 'PdtProduct',
								beanType : 'pdt',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '产品种类'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_productType',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'productType',
											store : Ext.create('mvc.combo.pdt.PdtOProductType')
										})
							
						,{
								xtype : 'label',
								text : '新品'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isNew',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isNew',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '首页显示'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isIndex',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isIndex',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '热卖'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isHot',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isHot',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '畅销'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isbestdeals',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isbestdeals',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '排序优先级'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_myOrder',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'myOrder',allowDecimals : false}
							
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
								text : '免运费'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isFreeShipping',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isFreeShipping',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '重量'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_weight',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'weight',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '长'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_length',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'length',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '宽'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_width',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'width',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '高'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_height',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'height',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '简短描述'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_briefDescription',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'briefDescription'}
							
						,{
								xtype : 'label',
								text : '详细介绍'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_description',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'description'}
							
						,{
								xtype : 'label',
								text : '选项卡1'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isShowTab1',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isShowTab1',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '选项卡1标题'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tabname1',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tabname1'}
							
						,{
								xtype : 'label',
								text : '选项卡1内容'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tab1',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tab1'}
							
						,{
								xtype : 'label',
								text : '选项卡2'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isShowTab2',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isShowTab2',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '选项卡2标题'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tabname2',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tabname2'}
							
						,{
								xtype : 'label',
								text : '选项卡2内容'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tab2',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tab2'}
							
						,{
								xtype : 'label',
								text : '选项卡3'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isShowTab3',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isShowTab3',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '选项卡3标题'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tabname3',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tabname3'}
							
						,{
								xtype : 'label',
								text : '选项卡3内容'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tab3',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tab3'}
							
						,{
								xtype : 'label',
								text : '更新时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_updateTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'updateTime',this.oldId + 'updateTimeEd',this.oldId + 'updateTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'updateTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'updateTime'
								},{
									xtype : 'datefieldexp',
									name : 'updateTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'updateTimeEd'
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