Ext.define('mvc.view.pdt.PdtProduct.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/pdt_PdtProduct_',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			if (this.insFlag)
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({xtype : 'multilanguagefield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isVerify',
					fieldLabel : '产品审核',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.verifyBy',
		fieldLabel : '审核人员',
		bean : 'SysUser',
		beanType : 'sys',
		emptyText : form_empty_text
	},{xtype : 'datefield',name : 'bean.verifyTime',value : 'Env.getTranBeginTime()',fieldLabel : '审核时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,
		mvc.Tools.crtComboTrigger(false,'pdt_PdtCat','',{
					name : 'bean.category',
					fieldLabel : '产品类目'
				})
	,
		mvc.Tools.crtComboTrigger(true,'usr_UsrProductCategory','',{
					name : 'bean.categoryDiy',
					fieldLabel : '店铺类目'
				})
	,{xtype : 'textfield',name : 'bean.code',fieldLabel : '编号'}
	,{xtype : 'textfield',name : 'bean.sku',fieldLabel : 'SKU（出厂编码）'}
	,{
		xtype : 'beantrigger',
		name : 'bean.supplier',
		fieldLabel : '供应商',
		bean : 'UsrSupplier',
		beanType : 'usr',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},
		mvc.Tools.crtComboTrigger(true,'usr_UsrMemberLevel','',{
					name : 'bean.memberLevel',
					fieldLabel : '会员等级'
				})
	,{xtype : 'imagefield',name : 'bean.picture',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '产品图片',labelWidth : 100}
	,{xtype : 'numberfield',name : 'bean.mktPrice',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '市场价$',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.curPrice',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '商城价$',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.purPrice',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '进货价$',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.wsPrice',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '批发价',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.minOq',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '起订量',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.maxOq',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '最大购买量',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.sales',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '产品销量',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.stock',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '库存',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.warnStock',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '警告库存',allowDecimals : false}
	,{xtype : 'textfield',name : 'bean.normAttr',fieldLabel : '商品属性'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.stockOut',
					fieldLabel : '脱销状态',
					store : Ext.create('mvc.combo.pdt.PdtOStockOut'),
					value : 0
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.state',
					fieldLabel : '销售状态',
					store : Ext.create('mvc.combo.pdt.PdtOState'),
					value : 1
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.soldInTime',
					fieldLabel : '定时上架',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,{xtype : 'datefield',name : 'bean.soldTimeB',fieldLabel : '上架时间(开始)',format : 'Y-m-d H:i:s'}
	,{xtype : 'datefield',name : 'bean.soldTimeE',fieldLabel : '上架时间(结束)',format : 'Y-m-d H:i:s'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isDefaultReview',
					fieldLabel : '默认评论',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,{xtype : 'numberfield',name : 'bean.defaultReviewRating',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '默认评论平均分',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.defaultReviewCount',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '默认评论人数',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.favoriteCount',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '收藏数',allowDecimals : false}
	,{
		xtype : 'textfield',
		name : 'bean.sourceProduct',
		fieldLabel : '产品',
		bean : 'PdtProduct',
		beanType : 'pdt',
		emptyText : form_empty_text,
	},
		mvc.Tools.crtComboForm(false,{
					name : 'bean.productType',
					fieldLabel : '产品种类',
					store : Ext.create('mvc.combo.pdt.PdtOProductType'),
					value : 0
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isNew',
					fieldLabel : '新品',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isIndex',
					fieldLabel : '首页显示',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isHot',
					fieldLabel : '热卖',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isbestdeals',
					fieldLabel : '畅销',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,{xtype : 'numberfield',name : 'bean.myOrder',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '排序优先级',allowDecimals : false}
	,{xtype : 'textfield',name : 'bean.seoTitleEn',fieldLabel : '标题'}
	,{xtype : 'textfield',name : 'bean.seoKeywordEn',fieldLabel : '关键词'}
	,{xtype : 'textfield',name : 'bean.seoDescriptionEn',fieldLabel : '简述'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isFreeShipping',
					fieldLabel : '免运费',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,{xtype : 'numberfield',name : 'bean.weight',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '重量',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.length',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '长',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.width',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '宽',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.height',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '高',decimalPrecision : 2}
	,{xtype : 'textfield',name : 'bean.briefDescription',fieldLabel : '简短描述'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isShowTab1',
					fieldLabel : '选项卡1',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,{xtype : 'textfield',name : 'bean.tabname1',fieldLabel : '选项卡1标题'}
	,{xtype : 'textfield',name : 'bean.tab1',fieldLabel : '选项卡1内容'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isShowTab2',
					fieldLabel : '选项卡2',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,{xtype : 'textfield',name : 'bean.tabname2',fieldLabel : '选项卡2标题'}
	,{xtype : 'textfield',name : 'bean.tab2',fieldLabel : '选项卡2内容'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isShowTab3',
					fieldLabel : '选项卡3',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,{xtype : 'textfield',name : 'bean.tabname3',fieldLabel : '选项卡3标题'}
	,{xtype : 'textfield',name : 'bean.tab3',fieldLabel : '选项卡3内容'}
	,{xtype : 'datefield',name : 'bean.updateTime',value : 'Env.getTranBeginTime()',fieldLabel : '更新时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'textareafield',name : 'bean.description',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '详细介绍'}
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey'
	});
	this.items = [{
		layout : {
				type : 'table',
				columns : 3,
				itemCls : 'x-layout-table-items-form'
		},
		border : false,
		items : formFlds,
	}];
	this.callParent(arguments);
}
});