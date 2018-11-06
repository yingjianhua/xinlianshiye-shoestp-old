Ext.define('mvc.view.usr.UsrMessages.Form', {
    extend: 'Ext.form.Panel',
    requires: ['Ext.ux.DataTip'],
    layout: 'form',
    border: false,
    frame: false,
    insFlag: true,
    reallowBlank: false,
    bodyPadding: '5 5 5 5',
    url: base_path + '/usr_UsrMessages_',
    fieldDefaults: {
        labelWidth: 100,
        labelStyle: 'font-weight : bold'
    },
    initComponent: function () {
        if (this.insFlag)
            this.url = this.url + 'ins';
        else
            this.url = this.url + 'upd';
        var formFlds = [];
        formFlds.push
        ({
                xtype: 'beantrigger',
                name: 'bean.reciver',
                value: 0,
                afterLabelTextTpl: required,
                allowBlank: this.reallowBlank,
                fieldLabel: '收件人',
                bean: 'UsrPurchase',
                beanType: 'usr',
                emptyText: form_empty_text,
                allowDecimals: false, hidden: this.sysmessage
            }
            , {xtype: 'textfield', name: 'bean.title', afterLabelTextTpl: required, allowBlank: false, fieldLabel: '标题'}
            , {
                xtype: 'hiddenfield',
                name: 'bean.senduser',
                value: -1,
                afterLabelTextTpl: required,
                allowBlank: false,
                fieldLabel: '发件人'
            }
            , {xtype: 'textareafield', name: 'bean.content', fieldLabel: '内容'}
            , {xtype: 'hiddenfield', name: 'bean.reply', fieldLabel: '回复', hidden: this.sysmessage}
            , {
                xtype: 'numberfield',
                name: 'bean.rowVersion',
                value: 0,
                afterLabelTextTpl: required,
                allowBlank: false,
                fieldLabel: '版本',
                hidden: true,
                allowDecimals: false
            }
            , {
                xtype: 'hiddenfield',
                name: 'bean.pkey'
            });
        this.items = [{
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            border: false,
            items: formFlds
        }];
        this.callParent(arguments);
    }
});
