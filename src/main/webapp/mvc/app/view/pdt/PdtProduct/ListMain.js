Ext.define('mvc.view.pdt.PdtProduct.ListMain',{
extend : 'mvc.tools.RowexpanderGrid',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
//viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[{text : '产品图片',width : 100,dataIndex : 'bean.picture',sortable : true,renderer : mvc.Tools.imgRenderer(80)}
	,{text : '名称',width : 313,dataIndex : 'bean.name',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	,{text : 'SKU（出厂编码）',width : 141,dataIndex : 'bean.sku',sortable : true}
	,{text : '产品审核',width : 100,dataIndex : 'bean.isVerify',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '审核人员',width : 75,dataIndex : 'bean.verifyBy',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'sys',mn : 'view.sys.SysUser.List'}
	,{text : '审核时间',width : 140,dataIndex : 'bean.verifyTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '产品类目',width : 100,dataIndex : 'bean.category',sortable : true,renderer : mvc.Tools.JsonRenderer(),md : 'pdt',mn : 'view.pdt.PdtCat.List'}
	,{text : '店铺类目',width : 100,dataIndex : 'bean.categoryDiy',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'pdt',mn : 'view.usr.UsrProductCategory.List',expandCol:true,hidden:true}
	,{text : '编号',width : 100,dataIndex : 'bean.code',sortable : true,expandCol:true,hidden:true}
	,{text : '供应商',width : 206,dataIndex : 'bean.supplier',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'usr',mn : 'view.usr.UsrSupplier.List'}
	,{text : '会员等级',width : 100,dataIndex : 'bean.memberLevel',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'usr',mn : 'view.usr.UsrMemberLevel.List',expandCol:true,hidden:true}
	,{text : '市场价$',width : 100,dataIndex : 'bean.mktPrice',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',expandCol:true,hidden:true}
	,{text : '商城价$',width : 100,dataIndex : 'bean.curPrice',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',expandCol:true,hidden:true}
	,{text : '进货价$',width : 100,dataIndex : 'bean.purPrice',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',expandCol:true,hidden:true}
	,{text : '批发价',width : 100,dataIndex : 'bean.wsPrice',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',expandCol:true,hidden:true}
	,{text : '起订量',width : 100,dataIndex : 'bean.minOq',sortable : true,expandCol:true,hidden:true}
	,{text : '最大购买量',width : 100,dataIndex : 'bean.maxOq',sortable : true,expandCol:true,hidden:true}
	,{text : '产品销量',width : 100,dataIndex : 'bean.sales',sortable : true,expandCol:true,hidden:true}
	,{text : '库存',width : 100,dataIndex : 'bean.stock',sortable : true,expandCol:true,hidden:true}
	,{text : '警告库存',width : 100,dataIndex : 'bean.warnStock',sortable : true,expandCol:true,hidden:true}
	,{text : '脱销状态',width : 100,dataIndex : 'bean.stockOut',sortable : true,renderer : mvc.Tools.optRenderer('pdt','Pdt','OStockOut'),expandCol:true,hidden:true}
	,{text : '销售状态',width : 100,dataIndex : 'bean.state',sortable : true,renderer : mvc.Tools.optRenderer('pdt','Pdt','OState'),expandCol:true,hidden:true}
	,{text : '定时上架',width : 100,dataIndex : 'bean.soldInTime',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol:true,hidden:true}
	,{text : '上架时间(开始)',width : 140,dataIndex : 'bean.soldTimeB',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),expandCol:true,hidden:true}
	,{text : '上架时间(结束)',width : 140,dataIndex : 'bean.soldTimeE',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),expandCol:true,hidden:true}
	,{text : '默认评论',width : 100,dataIndex : 'bean.isDefaultReview',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol:true,hidden:true}
	,{text : '默认评论平均分',width : 100,dataIndex : 'bean.defaultReviewRating',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',expandCol:true,hidden:true}
	,{text : '默认评论人数',width : 100,dataIndex : 'bean.defaultReviewCount',sortable : true,expandCol:true,hidden:true}
	,{text : '收藏数',width : 100,dataIndex : 'bean.favoriteCount',sortable : true,expandCol:true,hidden:true}
	,{text : '产品种类',width : 100,dataIndex : 'bean.productType',sortable : true,renderer : mvc.Tools.optRenderer('pdt','Pdt','OProductType'),expandCol:true,hidden:true}
	,{text : '新品',width : 100,dataIndex : 'bean.isNew',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol:true,hidden:true}
	,{text : '首页显示',width : 100,dataIndex : 'bean.isIndex',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol:true,hidden:true}
	,{text : '热卖',width : 100,dataIndex : 'bean.isHot',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol:true,hidden:true}
	,{text : '畅销',width : 100,dataIndex : 'bean.isbestdeals',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol:true,hidden:true}
	,{text : '排序优先级',width : 100,dataIndex : 'bean.myOrder',sortable : true,expandCol:true,hidden:true}
	,{text : '标题',width : 100,dataIndex : 'bean.seoTitleEn',sortable : true,expandCol:true,hidden:true}
	,{text : '关键词',width : 100,dataIndex : 'bean.seoKeywordEn',sortable : true,expandCol:true,hidden:true}
	,{text : '简述',width : 100,dataIndex : 'bean.seoDescriptionEn',sortable : true,expandCol:true,hidden:true}
	,{text : '免运费',width : 100,dataIndex : 'bean.isFreeShipping',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol:true,hidden:true}
	,{text : '重量',width : 100,dataIndex : 'bean.weight',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',expandCol:true,hidden:true}
	,{text : '长',width : 100,dataIndex : 'bean.length',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',expandCol:true,hidden:true}
	,{text : '宽',width : 100,dataIndex : 'bean.width',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',expandCol:true,hidden:true}
	,{text : '高',width : 100,dataIndex : 'bean.height',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',expandCol:true,hidden:true}
	,{text : '简短描述',width : 100,dataIndex : 'bean.briefDescription',sortable : true,expandCol:true,hidden:true}
	,{text : '选项卡1',width : 100,dataIndex : 'bean.isShowTab1',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol:true,hidden:true}
	,{text : '选项卡1标题',width : 100,dataIndex : 'bean.tabname1',sortable : true,expandCol:true,hidden:true}
	,{text : '选项卡1内容',width : 100,dataIndex : 'bean.tab1',sortable : true,expandCol:true,hidden:true}
	,{text : '选项卡2',width : 100,dataIndex : 'bean.isShowTab2',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol:true,hidden:true}
	,{text : '选项卡2标题',width : 100,dataIndex : 'bean.tabname2',sortable : true,expandCol:true,hidden:true}
	,{text : '选项卡2内容',width : 100,dataIndex : 'bean.tab2',sortable : true,expandCol:true,hidden:true}
	,{text : '选项卡3',width : 100,dataIndex : 'bean.isShowTab3',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),expandCol:true,hidden:true}
	,{text : '选项卡3标题',width : 100,dataIndex : 'bean.tabname3',sortable : true,expandCol:true,hidden:true}
	,{text : '选项卡3内容',width : 100,dataIndex : 'bean.tab3',sortable : true,expandCol:true,hidden:true}
	,{text : '更新时间',width : 140,dataIndex : 'bean.updateTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	];
		this.store=Ext.create('mvc.store.pdt.PdtProduct');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
		this.dockedItems=[{
		xtype : 'pagingtoolbar',
		store : this.store,
		dock : 'bottom',
		displayInfo : true,
		displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : '没有数据',
		items : [{
				xtype : Ext.create('mvc.tools.ComboxPaging',{myList : this})
			}]
	}];
		this.callParent(arguments);},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.pdt.PdtProduct', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getSelectionModel().deselectAll();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
},
onUpdRow : function(grid, rowIndex){
		var selection = this.getStore().getAt(rowIndex);
		this.getView().deselect(this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);
},
onUpdWin : function(selection){
		if (selection){
			var win = Ext.create('mvc.view.pdt.PdtProduct.Win',{
				title : this.title+'>修改',
				insFlag : false
			});
			win.on('create',this.onUpdateRecord,this);
			win.show();
			win.setActiveRecord(selection);
		}
},
onDelRow : function(grid, rowIndex){
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
			function(btn) {
				if (btn != 'yes')
					return;
				var row = me.getStore().getAt(rowIndex);
				Ext.Ajax.request({
					url : '/pdt_PdtProduct_del?pkey='+row.get('bean.pkey')+'&rowVersion='+row.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							me.getStore().removeAt(rowIndex);
							Ext.example.msg(msg_title, msg_del);
						}else{
							Ext.MessageBox.show({ 
								title : msg_title,
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
			}
		);
}
});