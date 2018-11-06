Ext.define('mvc.view.usr.UsrSupplier.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'UsrSupplier_searchWin_',
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
			url : base_path+'/usr_UsrSupplier_list',
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
								text : '供应商角色'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_role',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'usr_UsrSupplierRole','',{
											name : 'role'
										})
							
						,{
								xtype : 'label',
								text : '登录账号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_loginName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'loginName'}
							
						,{
								xtype : 'label',
								text : '密码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_password',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'password'}
							
						,{
								xtype : 'label',
								text : '状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_status',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'status',
											store : Ext.create('mvc.combo.usr.UsrOStatus')
										})
							
						,{
								xtype : 'label',
								text : '审核员'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_apprBy',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'apprBy',
								bean : 'SysUser',
								beanType : 'sys',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '审核时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_apprTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'apprTime',this.oldId + 'apprTimeEd',this.oldId + 'apprTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'apprTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'apprTime'
								},{
									xtype : 'datefieldexp',
									name : 'apprTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'apprTimeEd'
								}
							
						,{
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
								text : '注册资金'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_registeredCapital',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'registeredCapital'}
							
						,{
								xtype : 'label',
								text : '供应商分类'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_category',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'usr_UsrSupplierCategory','',{
											name : 'category'
										})
							
						,{
								xtype : 'label',
								text : '供应商认证'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isAuth',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isAuth',
											store : Ext.create('mvc.combo.usr.UsrOIsAuth')
										})
							
						,{
								xtype : 'label',
								text : '排序号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sort',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'sort',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '店铺关键字'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_seoTitle',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'seoTitle'}
							
						,{
								xtype : 'label',
								text : '搜索引擎说明'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_seoContent',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'seoContent'}
							
						,{
								xtype : 'label',
								text : '认证时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_authTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'authTime',this.oldId + 'authTimeEd',this.oldId + 'authTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'authTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'authTime'
								},{
									xtype : 'datefieldexp',
									name : 'authTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'authTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '网站显示名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_showName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'showName'}
							
						,{
								xtype : 'label',
								text : '企业法人'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_entity',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'entity'}
							
						,{
								xtype : 'label',
								text : '企业类型'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_companyType',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'companyType'}
							
						,{
								xtype : 'label',
								text : '企业性质'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_companyNature',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'companyNature'}
							
						,{
								xtype : 'label',
								text : '信用代码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_creditCode',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'creditCode'}
							
						,{
								xtype : 'label',
								text : '成立时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_companyEstablishTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'companyEstablishTime',this.oldId + 'companyEstablishTimeEd',this.oldId + 'companyEstablishTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'companyEstablishTime',
									format : 'Y-m-d',
									itemId : this.oldId + 'companyEstablishTime'
								},{
									xtype : 'datefieldexp',
									name : 'companyEstablishTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'companyEstablishTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '业务期限'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_operationTerm',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'operationTerm'}
							
						,{
								xtype : 'label',
								text : '主销售区'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_mainSalesArea',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'mainSalesArea'}
							
						,{
								xtype : 'label',
								text : '主要产品'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_mainProd',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'mainProd'}
							
						,{
								xtype : 'label',
								text : '生产模式'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_prodPattern',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'prodPattern'}
							
						,{
								xtype : 'label',
								text : '地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_companyAddr',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'companyAddr'}
							
						,{
								xtype : 'label',
								text : '描述'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_des',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'des'}
							
						,{
								xtype : 'label',
								text : 'Email'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_email',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'email'}
							
						,{
								xtype : 'label',
								text : '营业执照开始时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_businessLicenseBeginTime',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'businessLicenseBeginTime'}
							
						,{
								xtype : 'label',
								text : '营业执照到期时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_businessLicenseEndTime',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'businessLicenseEndTime'}
							
						,{
								xtype : 'label',
								text : '是否长期'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_businessLicenseIsSecular',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'businessLicenseIsSecular',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '电话'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_telephone',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'telephone'}
							
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
								text : 'QQ'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_qq',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'qq'}
							
						,{
								xtype : 'label',
								text : '资质证书'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_certPhoto',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'certPhoto'}
							
						,{
								xtype : 'label',
								text : '身份证正面'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_idCardFrontPhoto',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'idCardFrontPhoto'}
							
						,{
								xtype : 'label',
								text : '身份证反面'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_idCardBackPhoto',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'idCardBackPhoto'}
							
						,{
								xtype : 'label',
								text : '合作凭证'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_coopCertPhoto',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'coopCertPhoto'}
							
						,{
								xtype : 'label',
								text : '纳税人类型'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_taxpayerType',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'taxpayerType'}
							
						,{
								xtype : 'label',
								text : '法人身份证号码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_idCard',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'idCard'}
							
						,{
								xtype : 'label',
								text : '运营身份证号码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_operateIdCard',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'operateIdCard'}
							
						,{
								xtype : 'label',
								text : '联系人'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_contacts',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'contacts'}
							
						,{
								xtype : 'label',
								text : '手机'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_phone',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'phone'}
							
						,{
								xtype : 'label',
								text : '结算开户行'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_settlementBank',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'settlementBank'}
							
						,{
								xtype : 'label',
								text : '银行账号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_bankAccount',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'bankAccount'}
							
						,{
								xtype : 'label',
								text : '银行开户行'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_bankBranch',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'bankBranch'}
							
						,{
								xtype : 'label',
								text : '国家管理'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_bankCountry',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'plt_PltCountry','',{
											name : 'bankCountry'
										})
							
						,{
								xtype : 'label',
								text : '省份'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_bankProvince',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'plt_PltProvince','',{
											name : 'bankProvince'
										})
							
						,{
								xtype : 'label',
								text : '运营负责人身份证正面'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_contactsIdCardFrontPhoto',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'contactsIdCardFrontPhoto'}
							
						,{
								xtype : 'label',
								text : '运营负责人身份证反面'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_contactsIdCardBackPhoto',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'contactsIdCardBackPhoto'}
							
						,{
								xtype : 'label',
								text : 'Business_typ'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_businessTyp',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'businessTyp'}
							
						,{
								xtype : 'label',
								text : 'Location'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_location',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'location'}
							
						,{
								xtype : 'label',
								text : 'Production'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_production',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'production'}
							
						,{
								xtype : 'label',
								text : 'Developer'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_developer',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'developer'}
							
						,{
								xtype : 'label',
								text : 'Total_employees'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_totalEmployees',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'totalEmployees'}
							
						,{
								xtype : 'label',
								text : 'Annual_sales'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_annualSales',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'annualSales'}
							
						,{
								xtype : 'label',
								text : 'Top 3 Markets'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_top3Markets',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'top3Markets'}
							
						,{
								xtype : 'label',
								text : 'Materials'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_materials',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'materials'}
							
						,{
								xtype : 'label',
								text : '头像'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_headPic',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'headPic'}
							
						,{
								xtype : 'label',
								text : 'Department'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_department',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'department'}
							
						,{
								xtype : 'label',
								text : 'Job_Title'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_jobTitle',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'jobTitle'}
							
						,{
								xtype : 'label',
								text : 'Website'
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
								text : '国家管理'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_country',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'plt_PltCountry','',{
											name : 'country'
										})
							
						,{
								xtype : 'label',
								text : '省份'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_province',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'plt_PltProvince','',{
											name : 'province'
										})
							
						,{
								xtype : 'label',
								text : 'City'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_city',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'city'}
							
						,{
								xtype : 'label',
								text : '供应商首页产品展示'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isPro',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isPro',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : 'logo'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_logo',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'logo'}
							
						,{
								xtype : 'label',
								text : '店招背景'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_signBackgd',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'signBackgd'}
							
						,{
								xtype : 'label',
								text : '广告图'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_adPhoto',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'adPhoto'}
							
						,{
								xtype : 'label',
								text : '移动端广告图'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_adPhotoMobile',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'adPhotoMobile'}
							
						,{
								xtype : 'label',
								text : '广告连接'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_adPhotoLink',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'adPhotoLink'}
							
						,{
								xtype : 'label',
								text : '企业图片'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_companyPhoto',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'companyPhoto'}
							
						,{
								xtype : 'label',
								text : '企业图片连接'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_companyPhotoLink',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'companyPhotoLink'}
							
						,{
								xtype : 'label',
								text : '首页个性装修'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_homePageDiy',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'homePageDiy'}
							
						,{
								xtype : 'label',
								text : '产品页个性装修'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_productPageDiy',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'productPageDiy'}
							
						,{
								xtype : 'label',
								text : '联系页个性装修'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_contactPageDiy',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'contactPageDiy'}
							
						,{
								xtype : 'label',
								text : '首页个性装修（移动）'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_homePageDiyMobile',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'homePageDiyMobile'}
							
						,{
								xtype : 'label',
								text : '产品页个性装修（移动）'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_productPageDiyMobile',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'productPageDiyMobile'}
							
						,{
								xtype : 'label',
								text : '联系页个性装修（移动）'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_contactPageDiyMobile',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'contactPageDiyMobile'}
							
						,{
								xtype : 'label',
								text : '跟踪代码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_traceCode',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'traceCode'}
							
						,{
								xtype : 'label',
								text : '自定义链接名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_webSizeTitle',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'webSizeTitle'}
							
						,{
								xtype : 'label',
								text : '网址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_webSite',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'webSite'}
							
						,{
								xtype : 'label',
								text : '第三方统计地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tongjiUrl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tongjiUrl'}
							
						,{
								xtype : 'label',
								text : '第三方统计密码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tongjiPwd',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tongjiPwd'}
							
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