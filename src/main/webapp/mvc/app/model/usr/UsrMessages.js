Ext.define('mvc.model.usr.UsrMessages',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrMessages_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.senduser',mapping : 'senduser',type : 'int',useNull : true}
	,{name : 'bean.reciver',mapping : 'reciver',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.content',mapping : 'content',type : 'string'}
	,{name : 'bean.reply',mapping : 'reply',type : 'string'}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.type',mapping : 'type',type : 'int',useNull : true}
	,{name : 'bean.sendTime',mapping : 'sendTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.readTime',mapping : 'readTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.replyTime',mapping : 'replyTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});