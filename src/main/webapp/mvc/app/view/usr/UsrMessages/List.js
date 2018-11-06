Ext.define('mvc.view.usr.UsrMessages.List', {
    extend: 'Ext.grid.Panel',
    oldId: 'btn_UsrMessages',
    lock: true,
    disableSelection: false,
    loadMask: true,
    multiSelect: true,
    roles: '',
    selModel: {selType: 'checkboxmodel'},
    viewConfig: {enableTextSelection: true},
    initComponent: function () {
        var mainActs = [];
        if (this.roles.indexOf('ins') != -1)
            mainActs.push({
                text: '新增',
                iconCls: 'ins-icon',
                itemId: this.oldId + 'ins',
                scope: this,
                handler: this.onIns
            }, {
                text: '发布系统消息',
                iconCls: 'ins-icon',
                itemId: this.oldId + 'Sysins',
                scope: this,
                handler: this.onSysIns
            });
        if (this.roles.indexOf('upd') != -1)
            mainActs.push({
                text: '回复',
                iconCls: 'upd-icon',
                itemId: this.oldId + 'upd',
                scope: this,
                handler: this.onUpd,
                disabled: this.lock
            });
        if (this.roles.indexOf('del') != -1)
            mainActs.push({
                text: '删除',
                iconCls: 'del-icon',
                itemId: this.oldId + 'del',
                scope: this,
                handler: this.onDel,
                disabled: this.lock
            });
        this.columns = [{text: '发件人', width: 100, dataIndex: 'bean.senduser', sortable: true, renderer: this.getNames()}
            , {text: '收件人', width: 100, dataIndex: 'bean.reciver', sortable: true, renderer: this.getNames()}
            , {text: '标题', width: 100, dataIndex: 'bean.title', sortable: true}
            , {text: '内容', width: 100, dataIndex: 'bean.content', sortable: true}
            , {text: '回复', width: 100, dataIndex: 'bean.reply', sortable: true}
            , {
                text: '阅读状态',
                width: 60,
                dataIndex: 'bean.status',
                sortable: true,
                renderer: mvc.Tools.optRenderer('usr', 'UsrMessages', 'OMessageStaus')
            }
            , {
                text: '消息类别',
                width: 100,
                dataIndex: 'bean.type',
                sortable: true,
                renderer: mvc.Tools.optRenderer('usr', 'UsrMessages', 'OMessageType')
            }
            , {
                text: '发送时间',
                width: 140,
                dataIndex: 'bean.sendTime',
                sortable: true,
                renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')
            }
            , {
                text: '阅读时间',
                width: 140,
                dataIndex: 'bean.readTime',
                sortable: true,
                renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')
            }
            , {
                text: '回复时间',
                width: 140,
                dataIndex: 'bean.replyTime',
                sortable: true,
                renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')
            }
        ];
        if (mainActs.length > 0)
            this.tbar = mainActs;
        this.store = Ext.create('mvc.store.usr.UsrMessages');
        this.store.remoteFilter = true;
        this.store.proxy.filterParam = 'filter';
        this.on({cellclick: mvc.Tools.onCellclick});
        this.dockedItems = [{
            dock: 'top',
            xtype: 'toolbar',
            items: [{
                xtype: 'label',
                text: '发件人：'
            }, {
                xtype: 'numberfield',
                name: 'senduser'
            }, '', {
                xtype: 'label',
                text: '收件人：'
            }, {
                xtype: 'numberfield',
                name: 'reciver'
            }, '', {
                xtype: 'button',
                text: '撤销',
                scope: this,
                iconCls: 'win-close-icon',
                handler: this.onSearchCancel
            }, {
                xtype: 'splitbutton',
                text: '搜索',
                scope: this,
                iconCls: 'win-ok-icon',
                handler: this.onSearch,
                menu: [{text: '高级搜索', iconCls: 'win-ok-icon', scope: this, handler: this.onSearchAdv}]
            }]
        }, {
            xtype: 'pagingtoolbar',
            store: this.store,
            dock: 'bottom',
            displayInfo: true,
            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',
            emptyMsg: '没有数据',
            items: [{
                xtype: Ext.create('mvc.tools.ComboxPaging', {myList: this})
            }]
        }];
        this.callParent(arguments);
        mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'), this);
    },
    listeners: {
        selectionchange: function (selModel, selected) {
            if (this.roles.indexOf('upd') != -1)
                this.down('#' + this.oldId + 'upd').setDisabled(selected.length === 0);
            if (this.roles.indexOf('del') != -1)
                this.down('#' + this.oldId + 'del').setDisabled(selected.length === 0);
        }
    },
    getNames() {
        return function (v) {
            if (v == -1) {
                return "系统管理员"
            }
            if (v == -2) {
                return "全体会员"
            } else {
                Ext.Ajax.request({
                    async: false,
                    url: base_path + '/usr_UsrMessages_getName?userId=' + v,
                    success: function (response, options) {
                        v = response.responseText
                    }
                });
            }
            return v
        };
    },
    onSaveRecord: function (form, data) {
        this.store.insert(0, data);
        this.getView().select(0);
        Ext.example.msg(msg_title, msg_text);
    },
    onIns: function () {
        var win = Ext.create('mvc.view.usr.UsrMessages.Win', {
            title: this.title + '>新增'
        });
        win.on('create', this.onSaveRecord, this);
        win.show();
    }, onSysIns: function () {
        var win = Ext.create('mvc.view.usr.UsrMessages.SysWin', {
            title: this.title + '>发布系统消息'
        });
        win.on('create', this.onSaveRecord, this);
        win.show();
    },
    onUpdateRecord: function (form, data) {
        var selection = this.getView().getSelectionModel().getSelection()[0];
        var bean = Ext.create('mvc.model.usr.UsrMessages', data);
        Ext.apply(selection.data, bean.data);
        selection.commit();
        this.getView().select(selection);
        Ext.example.msg(msg_title, msg_text);
    },
    onUpd: function () {
        var selection = this.getView().getSelectionModel().getSelection()[0];
        this.onUpdWin(selection);
    },
    onUpdRow: function (grid, rowIndex) {
        var selection = this.getStore().getAt(rowIndex);
        this.getView().deselect(this.getView().getSelectionModel().getSelection());
        this.getView().select(selection);
        this.onUpdWin(selection);
    },
    onUpdWin: function (selection) {
        if (selection) {
            var win = Ext.create('mvc.view.usr.UsrMessages.ReWin', {
                title: this.title + '>回复',
                insFlag: false
            });
            win.on('create', this.onUpdateRecord, this);
            win.show();
            win.setActiveRecord(selection);
        }
    },
    onDel: function () {
        var selection = this.getView().getSelectionModel().getSelection();
        if (selection) {
            var me = this;
            Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
                function (btn) {
                    if (btn != 'yes')
                        return;
                    var arr = new Array();
                    var arrv = new Array();
                    for (var i = 0; i < selection.length; i++) {
                        arr.push(selection[i].get('bean.pkey'));
                        arrv.push(selection[i].get(BEAN_VERSION));
                    }
                    Ext.Ajax.request({
                        url: base_path + '/usr_UsrMessages_delMulti?pkeys=' + arr.toString() + '&rowVersions=' + arrv.toString(),
                        success: function (response, options) {
                            var result = Ext.decode(response.responseText);
                            if (result.success) {
                                me.getStore().remove(selection);
                                Ext.example.msg(msg_title, msg_del);
                            } else {
                                Ext.MessageBox.show({
                                    title: msg_title,
                                    msg: result.msg,
                                    buttons: Ext.MessageBox.OK,
                                    icon: Ext.MessageBox.ERROR
                                });
                            }
                        }
                    });
                }
            );
        }
    },
    onDelRow: function (grid, rowIndex) {
        var me = this;
        var row = me.getStore().getAt(rowIndex);
        Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
            function (btn) {
                if (btn != 'yes')
                    return;
                Ext.Ajax.request({
                    url: base_path + '/usr_UsrMessages_del?pkey=' + row.get('bean.pkey') + '&rowVersion=' + row.get(BEAN_VERSION),
                    success: function (response, options) {
                        var result = Ext.decode(response.responseText);
                        if (result.success) {
                            me.getStore().removeAt(rowIndex);
                            Ext.example.msg(msg_title, msg_del);
                        } else {
                            Ext.MessageBox.show({
                                title: msg_title,
                                msg: result.msg,
                                buttons: Ext.MessageBox.OK,
                                icon: Ext.MessageBox.ERROR
                            });
                        }
                    }
                });
            }
        );
    },
    onSearchCancel: function () {
        this.getSelectionModel().deselectAll();
        mvc.Tools.searchClear(this.down('toolbar'));
        this.store.clearFilter();
    },
    onSearch: function () {
        var array = mvc.Tools.searchValues(this.down('toolbar'));
        this.onSearchDo(array);
    },
    onSearchAdv: function () {
        var win = Ext.create('mvc.view.usr.UsrMessages.WinSearch', {
            title: this.title + '>高级搜索',
            listCmp: this
        });
        win.show();
    },
    onSearchDo: function (array) {
        this.getSelectionModel().deselectAll();
        if (array.length == 0) {
            this.store.clearFilter();
            return;
        }
        this.store.clearFilter(true);
        this.store.filter(array);
    }
});
