Ext.define('mvc.model.pdt.PdtProduct',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtProduct_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.isVerify',mapping : 'isVerify',type : 'int',useNull : true}
	,{name : 'bean.verifyBy',mapping : 'verifyBy',type : 'string',outkey : true}
	,{name : 'bean.verifyTime',mapping : 'verifyTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.category',mapping : 'category',type : 'string',outkey : true}
	,{name : 'bean.categoryDiy',mapping : 'categoryDiy',type : 'string',outkey : true}
	,{name : 'bean.code',mapping : 'code',type : 'string'}
	,{name : 'bean.sku',mapping : 'sku',type : 'string'}
	,{name : 'bean.supplier',mapping : 'supplier',type : 'string',outkey : true}
	,{name : 'bean.memberLevel',mapping : 'memberLevel',type : 'string',outkey : true}
	,{name : 'bean.picture',mapping : 'picture',type : 'string', convert: mvc.Tools.imgConvert()}
	,{name : 'bean.mktPrice',mapping : 'mktPrice',type : 'float',useNull : true}
	,{name : 'bean.curPrice',mapping : 'curPrice',type : 'float',useNull : true}
	,{name : 'bean.purPrice',mapping : 'purPrice',type : 'float',useNull : true}
	,{name : 'bean.wsPrice',mapping : 'wsPrice',type : 'float',useNull : true}
	,{name : 'bean.minOq',mapping : 'minOq',type : 'int',useNull : true}
	,{name : 'bean.maxOq',mapping : 'maxOq',type : 'int',useNull : true}
	,{name : 'bean.sales',mapping : 'sales',type : 'int',useNull : true}
	,{name : 'bean.stock',mapping : 'stock',type : 'int',useNull : true}
	,{name : 'bean.warnStock',mapping : 'warnStock',type : 'int',useNull : true}
	,{name : 'bean.normAttr',mapping : 'normAttr',type : 'string'}
	,{name : 'bean.sizeAttr',mapping : 'sizeAttr',type : 'string'}
	,{name : 'bean.colorAttr',mapping : 'colorAttr',type : 'string'}
	,{name : 'bean.stockOut',mapping : 'stockOut',type : 'int',useNull : true}
	,{name : 'bean.state',mapping : 'state',type : 'int',useNull : true}
	,{name : 'bean.soldInTime',mapping : 'soldInTime',type : 'int',useNull : true}
	,{name : 'bean.soldTimeB',mapping : 'soldTimeB',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.soldTimeE',mapping : 'soldTimeE',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.isDefaultReview',mapping : 'isDefaultReview',type : 'int',useNull : true}
	,{name : 'bean.defaultReviewRating',mapping : 'defaultReviewRating',type : 'float',useNull : true}
	,{name : 'bean.defaultReviewCount',mapping : 'defaultReviewCount',type : 'int',useNull : true}
	,{name : 'bean.favoriteCount',mapping : 'favoriteCount',type : 'int',useNull : true}
	,{name : 'bean.sourceProduct',mapping : 'sourceProduct',type : 'string',outkey : true, convert: mvc.Tools.JsonRenderer()}
	,{name : 'bean.productType',mapping : 'productType',type : 'int',useNull : true}
	,{name : 'bean.isNew',mapping : 'isNew',type : 'int',useNull : true}
	,{name : 'bean.isIndex',mapping : 'isIndex',type : 'int',useNull : true}
	,{name : 'bean.isHot',mapping : 'isHot',type : 'int',useNull : true}
	,{name : 'bean.isbestdeals',mapping : 'isbestdeals',type : 'int',useNull : true}
	,{name : 'bean.myOrder',mapping : 'myOrder',type : 'int',useNull : true}
	,{name : 'bean.seoTitleEn',mapping : 'seoTitleEn',type : 'string'}
	,{name : 'bean.seoKeywordEn',mapping : 'seoKeywordEn',type : 'string'}
	,{name : 'bean.seoDescriptionEn',mapping : 'seoDescriptionEn',type : 'string'}
	,{name : 'bean.isFreeShipping',mapping : 'isFreeShipping',type : 'int',useNull : true}
	,{name : 'bean.weight',mapping : 'weight',type : 'float',useNull : true}
	,{name : 'bean.length',mapping : 'length',type : 'float',useNull : true}
	,{name : 'bean.width',mapping : 'width',type : 'float',useNull : true}
	,{name : 'bean.height',mapping : 'height',type : 'float',useNull : true}
	,{name : 'bean.briefDescription',mapping : 'briefDescription',type : 'string'}
	,{name : 'bean.description',mapping : 'description',type : 'string', convert: mvc.Tools.JsonRenderer()}
	,{name : 'bean.isShowTab1',mapping : 'isShowTab1',type : 'int',useNull : true}
	,{name : 'bean.tabname1',mapping : 'tabname1',type : 'string'}
	,{name : 'bean.tab1',mapping : 'tab1',type : 'string'}
	,{name : 'bean.isShowTab2',mapping : 'isShowTab2',type : 'int',useNull : true}
	,{name : 'bean.tabname2',mapping : 'tabname2',type : 'string'}
	,{name : 'bean.tab2',mapping : 'tab2',type : 'string'}
	,{name : 'bean.isShowTab3',mapping : 'isShowTab3',type : 'int',useNull : true}
	,{name : 'bean.tabname3',mapping : 'tabname3',type : 'string'}
	,{name : 'bean.tab3',mapping : 'tab3',type : 'string'}
	,{name : 'bean.updateTime',mapping : 'updateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});