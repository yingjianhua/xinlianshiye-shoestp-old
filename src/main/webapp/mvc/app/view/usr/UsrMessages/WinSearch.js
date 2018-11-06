Ext.define('mvc.view.usr.UsrMessages.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'UsrMessages_searchWin_',
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
			url : base_path+'/usr_UsrMessages_list',
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
								text : '发件人'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_senduser',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'senduser',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '收件人'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_reciver',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'reciver',allowDecimals : false}
							
						,{
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
								text : '内容'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_content',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'content'}
							
						,{
								xtype : 'label',
								text : '回复'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_reply',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'reply'}
							
						,{
								xtype : 'label',
								text : '阅读状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_status',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'status',
											store : Ext.create('mvc.combo.usr.UsrMessagesOMessageStaus')
										})
							
						,{
								xtype : 'label',
								text : '消息类别'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_type',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'type',
											store : Ext.create('mvc.combo.usr.UsrMessagesOMessageType')
										})
							
						,{
								xtype : 'label',
								text : '发送时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sendTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'sendTime',this.oldId + 'sendTimeEd',this.oldId + 'sendTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'sendTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'sendTime'
								},{
									xtype : 'datefieldexp',
									name : 'sendTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'sendTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '阅读时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_readTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'readTime',this.oldId + 'readTimeEd',this.oldId + 'readTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'readTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'readTime'
								},{
									xtype : 'datefieldexp',
									name : 'readTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'readTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '回复时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_replyTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'replyTime',this.oldId + 'replyTimeEd',this.oldId + 'replyTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'replyTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'replyTime'
								},{
									xtype : 'datefieldexp',
									name : 'replyTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'replyTimeEd'
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