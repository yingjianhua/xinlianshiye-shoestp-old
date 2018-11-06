Ext.define('mvc.model.usr.UsrSupplier',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrSupplier_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.role',mapping : 'role',type : 'string',outkey : true}
	,{name : 'bean.loginName',mapping : 'loginName',type : 'string'}
	,{name : 'bean.password',mapping : 'password',type : 'string'}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.apprBy',mapping : 'apprBy',type : 'string',outkey : true}
	,{name : 'bean.apprTime',mapping : 'apprTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.registeredCapital',mapping : 'registeredCapital',type : 'string'}
	,{name : 'bean.category',mapping : 'category',type : 'string',outkey : true}
	,{name : 'bean.isAuth',mapping : 'isAuth',type : 'int',useNull : true}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.seoTitle',mapping : 'seoTitle',type : 'string'}
	,{name : 'bean.seoContent',mapping : 'seoContent',type : 'string'}
	,{name : 'bean.authTime',mapping : 'authTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.showName',mapping : 'showName',type : 'string'}
	,{name : 'bean.entity',mapping : 'entity',type : 'string'}
	,{name : 'bean.companyType',mapping : 'companyType',type : 'string'}
	,{name : 'bean.companyNature',mapping : 'companyNature',type : 'string'}
	,{name : 'bean.creditCode',mapping : 'creditCode',type : 'string'}
	,{name : 'bean.companyEstablishTime',mapping : 'companyEstablishTime',type : 'date'}
	,{name : 'bean.operationTerm',mapping : 'operationTerm',type : 'string'}
	,{name : 'bean.mainSalesArea',mapping : 'mainSalesArea',type : 'string'}
	,{name : 'bean.mainProd',mapping : 'mainProd',type : 'string'}
	,{name : 'bean.prodPattern',mapping : 'prodPattern',type : 'string'}
	,{name : 'bean.companyAddr',mapping : 'companyAddr',type : 'string'}
	,{name : 'bean.des',mapping : 'des',type : 'string'}
	,{name : 'bean.email',mapping : 'email',type : 'string'}
	,{name : 'bean.businessLicenseBeginTime',mapping : 'businessLicenseBeginTime',type : 'string'}
	,{name : 'bean.businessLicenseEndTime',mapping : 'businessLicenseEndTime',type : 'string'}
	,{name : 'bean.businessLicenseIsSecular',mapping : 'businessLicenseIsSecular',type : 'int',useNull : true}
	,{name : 'bean.telephone',mapping : 'telephone',type : 'string'}
	,{name : 'bean.fax',mapping : 'fax',type : 'string'}
	,{name : 'bean.qq',mapping : 'qq',type : 'string'}
	,{name : 'bean.certPhoto',mapping : 'certPhoto',type : 'string', convert: mvc.Tools.imgConvert()}
	,{name : 'bean.idCardFrontPhoto',mapping : 'idCardFrontPhoto',type : 'string',convert:mvc.Tools.imgConvert()}
	,{name : 'bean.idCardBackPhoto',mapping : 'idCardBackPhoto',type : 'string',convert:mvc.Tools.imgConvert()}
	,{name : 'bean.coopCertPhoto',mapping : 'coopCertPhoto',type : 'string'}
	,{name : 'bean.taxpayerType',mapping : 'taxpayerType',type : 'string'}
	,{name : 'bean.idCard',mapping : 'idCard',type : 'string'}
	,{name : 'bean.operateIdCard',mapping : 'operateIdCard',type : 'string'}
	,{name : 'bean.contacts',mapping : 'contacts',type : 'string'}
	,{name : 'bean.phone',mapping : 'phone',type : 'string'}
	,{name : 'bean.settlementBank',mapping : 'settlementBank',type : 'string'}
	,{name : 'bean.bankAccount',mapping : 'bankAccount',type : 'string'}
	,{name : 'bean.bankBranch',mapping : 'bankBranch',type : 'string'}
	,{name : 'bean.bankCountry',mapping : 'bankCountry',type : 'string',outkey : true}
	,{name : 'bean.bankProvince',mapping : 'bankProvince',type : 'string',outkey : true}
	,{name : 'bean.contactsIdCardFrontPhoto',mapping : 'contactsIdCardFrontPhoto',type : 'string',convert:mvc.Tools.imgConvert()}
	,{name : 'bean.contactsIdCardBackPhoto',mapping : 'contactsIdCardBackPhoto',type : 'string',convert:mvc.Tools.imgConvert()}
	,{name : 'bean.businessTyp',mapping : 'businessTyp',type : 'string'}
	,{name : 'bean.location',mapping : 'location',type : 'string'}
	,{name : 'bean.production',mapping : 'production',type : 'string'}
	,{name : 'bean.developer',mapping : 'developer',type : 'string'}
	,{name : 'bean.totalEmployees',mapping : 'totalEmployees',type : 'string'}
	,{name : 'bean.annualSales',mapping : 'annualSales',type : 'string'}
	,{name : 'bean.top3Markets',mapping : 'top3Markets',type : 'string'}
	,{name : 'bean.materials',mapping : 'materials',type : 'string'}
	,{name : 'bean.headPic',mapping : 'headPic',type : 'string',convert:mvc.Tools.imgConvert()}
	,{name : 'bean.department',mapping : 'department',type : 'string'}
	,{name : 'bean.jobTitle',mapping : 'jobTitle',type : 'string'}
	,{name : 'bean.website',mapping : 'website',type : 'string'}
	,{name : 'bean.country',mapping : 'country',type : 'string',outkey : true}
	,{name : 'bean.province',mapping : 'province',type : 'string',outkey : true}
	,{name : 'bean.city',mapping : 'city',type : 'string'}
	,{name : 'bean.isPro',mapping : 'isPro',type : 'int',useNull : true}
	,{name : 'bean.logo',mapping : 'logo',type : 'string'}
	,{name : 'bean.signBackgd',mapping : 'signBackgd',type : 'string'}
	,{name : 'bean.adPhoto',mapping : 'adPhoto',type : 'string',convert:mvc.Tools.imgConvert()}
	,{name : 'bean.adPhotoMobile',mapping : 'adPhotoMobile',type : 'string',convert:mvc.Tools.imgConvert()}
	,{name : 'bean.adPhotoLink',mapping : 'adPhotoLink',type : 'string'}
	,{name : 'bean.companyPhoto',mapping : 'companyPhoto',type : 'string',convert:mvc.Tools.imgConvert()}
	,{name : 'bean.companyPhotoLink',mapping : 'companyPhotoLink',type : 'string'}
	,{name : 'bean.homePageDiy',mapping : 'homePageDiy',type : 'string'}
	,{name : 'bean.productPageDiy',mapping : 'productPageDiy',type : 'string'}
	,{name : 'bean.contactPageDiy',mapping : 'contactPageDiy',type : 'string'}
	,{name : 'bean.homePageDiyMobile',mapping : 'homePageDiyMobile',type : 'string'}
	,{name : 'bean.productPageDiyMobile',mapping : 'productPageDiyMobile',type : 'string'}
	,{name : 'bean.contactPageDiyMobile',mapping : 'contactPageDiyMobile',type : 'string'}
	,{name : 'bean.traceCode',mapping : 'traceCode',type : 'string'}
	,{name : 'bean.webSizeTitle',mapping : 'webSizeTitle',type : 'string'}
	,{name : 'bean.webSite',mapping : 'webSite',type : 'string'}
	,{name : 'bean.tongjiUrl',mapping : 'tongjiUrl',type : 'string'}
	,{name : 'bean.tongjiPwd',mapping : 'tongjiPwd',type : 'string'}
	,{name : 'bean.updateTime',mapping : 'updateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});