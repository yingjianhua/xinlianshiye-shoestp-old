Ext.define('mvc.model.pdt.PdtAppraise',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtAppraise_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.goodsPkey',mapping : 'goodsPkey',type : 'string',outkey : true}
	,{name : 'bean.usersPkey',mapping : 'usersPkey',type : 'string',outkey : true}
	,{name : 'bean.appraiseContent',mapping : 'appraiseContent',type : 'string'}
	,{name : 'bean.image1',mapping : 'image1',type : 'string'}
	,{name : 'bean.image2',mapping : 'image2',type : 'string'}
	,{name : 'bean.image3',mapping : 'image3',type : 'string'}
	,{name : 'bean.image4',mapping : 'image4',type : 'string'}
	,{name : 'bean.image5',mapping : 'image5',type : 'string'}
	,{name : 'bean.image6',mapping : 'image6',type : 'string'}
	,{name : 'bean.goodsSatisfaction',mapping : 'goodsSatisfaction',type : 'int',useNull : true}
	,{name : 'bean.supplierSatisfaction',mapping : 'supplierSatisfaction',type : 'int',useNull : true}
	,{name : 'bean.appraiseTime',mapping : 'appraiseTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.appraiseIp',mapping : 'appraiseIp',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});