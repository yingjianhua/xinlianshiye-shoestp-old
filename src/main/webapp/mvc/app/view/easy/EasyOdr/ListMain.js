Ext.define('mvc.view.easy.EasyOdr.ListMain', {
    extend: 'Ext.grid.Panel',
    disableSelection: false,
    loadMask: true,
    multiSelect: true,
    roles: '',
    selModel: {selType: 'checkboxmodel'},
    viewConfig: {enableTextSelection: true},
    initComponent: function () {
        this.columns = [{
            text: '采购商',
            width: 150,
            dataIndex: 'bean.purchase',
            sortable: true,
            renderer: mvc.Tools.beanRendererHref(),
            md: 'usr',
            mn: 'view.usr.UsrPurchase.List'
        }
            , {
                text: '供应商',
                width: 150,
                dataIndex: 'bean.supplier',
                sortable: true,
                renderer: mvc.Tools.beanRendererHref(),
                md: 'usr',
                mn: 'view.usr.UsrSupplier.List'
            }
            , {text: '订单号', width: 150, dataIndex: 'bean.orderNum', sortable: true}
            , {
                text: '下单时间',
                width: 150,
                dataIndex: 'bean.time',
                sortable: true,
                renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')
            }
            , {text: '名字', width: 100, dataIndex: 'bean.name', sortable: true}
            , {text: '电话号码', width: 150, dataIndex: 'bean.phone', sortable: true}
            , {text: '收货地址', width: 200, dataIndex: 'bean.address', sortable: true}
            , {text: '商品总数量', width: 100, dataIndex: 'bean.counypd', sortable: true}
        ];
        this.store = Ext.create('mvc.store.easy.EasyOdr');
        this.store.remoteFilter = true;
        this.store.proxy.filterParam = 'filter';
        this.on({cellclick: mvc.Tools.onCellclick});
        this.dockedItems = [{
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
    },
    onUpdateRecord: function (form, data) {
        var selection = this.getView().getSelectionModel().getSelection()[0];
        var bean = Ext.create('mvc.model.easy.EasyOdr', data);
        Ext.apply(selection.data, bean.data);
        selection.commit();
        this.getSelectionModel().deselectAll();
        this.getView().select(selection);
        Ext.example.msg(msg_title, msg_text);
    },
    onUpdRow: function (grid, rowIndex) {
        var selection = this.getStore().getAt(rowIndex);
        this.getView().deselect(this.getView().getSelectionModel().getSelection());
        this.getView().select(selection);
        this.onUpdWin(selection);
    },
    onUpdWin: function (selection) {
        if (selection) {
            var win = Ext.create('mvc.view.easy.EasyOdr.Win', {
                title: this.title + '>修改',
                insFlag: false
            });
            win.on('create', this.onUpdateRecord, this);
            win.show();
            win.setActiveRecord(selection);
        }
    },
    onDelRow: function (grid, rowIndex) {
        var me = this;
        Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
            function (btn) {
                if (btn != 'yes')
                    return;
                var row = me.getStore().getAt(rowIndex);
                Ext.Ajax.request({
                    url: '/easy_EasyOdr_del?pkey=' + row.get('bean.pkey') + '&rowVersion=' + row.get(BEAN_VERSION),
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
    }
});
