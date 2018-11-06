Ext.define('mvc.view.usr.UsrMemberLevel.ImptForm',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/usr_UsrMemberLevel_',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
	this.items = [{
        xtype: 'filefield',
        name: 'file',
        fieldLabel: '文件：',
        labelWidth: 50,
        msgTarget: 'side',
        allowBlank: false,
        anchor: '100%',
        buttonText: '选择'
    }],
    this.buttons = [{
        text: '上传',
        handler: function() {
        	var me = this;
            var form = me.up('form').getForm();
            if(form.isValid()){
                form.submit({
                    url: base_path+'/usr_UsrMemberLevel_impt',
                    submitEmptyText: false,
					type : 'ajax',
                    waitMsg: '正在导入，请稍后...',
                    success: function(fp, o) {
                    	Ext.example.msg(msg_title, '导入--成功');
                    	me.up("window").close();
                    }
                });
            }
        }
    }]
	this.callParent(arguments);
}
});