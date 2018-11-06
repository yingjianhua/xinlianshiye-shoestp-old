Ext.define('mvc.view.usr.UsrConsult.List', {
    extend: 'mvc.tools.RowexpanderGrid',
    oldId: 'btn_UsrConsult',
    lock: true,
    disableSelection: false,
    loadMask: true,
    multiSelect: true,
    roles: '',
    selModel: {
        selType: 'checkboxmodel'
    },
    //viewConfig : {enableTextSelection : true},
    initComponent: function () {
        var mainActs = [];
        if (this.roles.indexOf('del') != -1)
            mainActs.push({
                text: '删除',
                iconCls: 'del-icon',
                itemId: this.oldId + 'del',
                scope: this,
                handler: this.onDel,
                disabled: this.lock
            });
        this.columns = [{
            text: '标题',
            width: 100,
            dataIndex: 'bean.title',
            sortable: true
        }, {
            text: '图片',
            width: 123,
            dataIndex: 'bean.image',
            sortable: true,
            renderer: mvc.Tools.imgRenderer(50, 50)
        }, {
            text: '国家管理',
            width: 104,
            dataIndex: 'bean.country',
            sortable: true,
            renderer: mvc.Tools.JsonRenderer(),
            md: 'plt',
            mn: 'view.plt.PltCountry.List',
            expandCol: true,
            hidden: true
        }, {
            text: '产品',
            width: 380,
            dataIndex: 'bean.product',
            sortable: true,
            renderer: mvc.Tools.JsonRenderer(),
            md: 'pdt',
            mn: 'view.pdt.PdtProduct.List'
        }, {
            text: '内容',
            width: 100,
            dataIndex: 'bean.content',
            sortable: true,
            expandCol: true,
            hidden: true
        }, {
            text: '剩余抢单次数',
            width: 100,
            dataIndex: 'bean.count',
            sortable: true
        }, {
            text: '商品数量',
            width: 100,
            dataIndex: 'bean.quantity',
            sortable: true,
            expandCol: true,
            hidden: true
        }, {
            text: '采购商',
            width: 100,
            dataIndex: 'bean.purchase',
            sortable: true,
            renderer: mvc.Tools.beanRendererHref(),
            md: 'usr',
            mn: 'view.usr.UsrPurchase.List'
        }, {
            text: '名字',
            width: 100,
            dataIndex: 'bean.name',
            sortable: true
        }, {
            text: '邮箱',
            width: 198,
            dataIndex: 'bean.email',
            sortable: true
        }, {
            text: '创建时间',
            width: 140,
            dataIndex: 'bean.createTime',
            sortable: true,
            renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')
        }];
        if (mainActs.length > 0)
            this.tbar = mainActs;
        this.store = Ext.create('mvc.store.usr.UsrConsult');
        this.store.remoteFilter = true;
        this.store.proxy.filterParam = 'filter';
        this.on({
            cellclick: mvc.Tools.onCellclick
        });
        this.dockedItems = [{
            dock: 'top',
            xtype: 'toolbar',
            items: [{
                xtype: 'label',
                text: '名字：'
            }, {
                xtype: 'textfield',
                name: 'name'
            }, '', {
                xtype: 'label',
                text: '标题：'
            }, {
                xtype: 'textfield',
                name: 'title'
            }, '', {
                xtype: 'label',
                text: '产品：'
            }, {
                xtype: 'beantrigger',
                name: 'product',
                bean: 'PdtProduct',
                beanType: 'pdt',
                emptyText: form_empty_text
            }, '', {
                xtype: 'label',
                text: '采购商：'
            }, {
                xtype: 'beantrigger',
                name: 'purchase',
                bean: 'UsrPurchase',
                beanType: 'usr',
                emptyText: form_empty_text
            }, '', {
                xtype: 'label',
                text: '内容：'
            }, {
                xtype: 'textfield',
                name: 'content'
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
                menu: [{
                    text: '高级搜索',
                    iconCls: 'win-ok-icon',
                    scope: this,
                    handler: this.onSearchAdv
                }]
            }]
        }, {
            xtype: 'pagingtoolbar',
            store: this.store,
            dock: 'bottom',
            displayInfo: true,
            displayMsg: '显示 {0} - {1} 条，共计 {2} 条',
            emptyMsg: '没有数据',
            items: [{
                xtype: Ext.create('mvc.tools.ComboxPaging', {
                    myList: this
                })
            }]
        }];
        this.callParent(arguments);
        mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'), this);
    },
    listeners: {
        selectionchange: function (selModel, selected) {
            if (this.roles.indexOf('del') != -1)
                this.down('#' + this.oldId + 'del').setDisabled(selected.length !== 1);
        }
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
        var win = Ext.create('mvc.view.usr.UsrConsult.WinSearch', {
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
    },
    onDel: function () {
        var selections = this.getView().getSelectionModel().getSelection();
        if (selections) {
        	var selection = selections[0]; 
            var me = this;
            Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
                function (btn) {
                    if (btn != 'yes')
                        return;
                    Ext.Ajax.request({
                        url: base_path + '/usr_UsrConsult_del?pkey=' + selection.get('bean.pkey') + '&rowVersions=' + selection.get(BEAN_VERSION),
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
});