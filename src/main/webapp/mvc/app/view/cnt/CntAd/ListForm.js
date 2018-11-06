Ext.define('mvc.view.cnt.CntAd.ListForm', {
    extend: 'Ext.grid.Panel',
    disableSelection: false,
    loadMask: true,
    cellEditing: Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit: 1
    }),
    mainPkey: null,
    initComponent: function () {
        var mainActs = [{
            text: '新增',
            iconCls: 'ins-icon',
            scope: this,
            handler: this.onIns
        }, {
            text: '删除',
            iconCls: 'del-icon',
            scope: this,
            handler: this.onDel
        }];
        this.tbar = mainActs;
        this.columns = [{
            text: '启用标志',
            width: 75,
            dataIndex: 'bean.enabled',
            sortable: true,
            renderer: mvc.Tools.optRenderer('sys', 'Sys', 'OEnabled'),
            editor: mvc.Tools.crtComboForm(true, {
                name: 'bean.enabled',
                store: Ext.create('mvc.combo.sys.SysOEnabled')
            })
        }, {
            text: '广告名称',
            width: 120,
            dataIndex: 'bean.name',
            sortable: true,
            editor: {}
        }, {
            text: '广告简介',
            width: 120,
            dataIndex: 'bean.brief',
            sortable: true,
            editor: {}
        }, {
            text: '广告图片',
            width: 100,
            dataIndex: 'bean.image',
            sortable: true,
            renderer: mvc.Tools.imgRenderer(50)
        }, {
            text: '广告链接',
            width: 200,
            dataIndex: 'bean.url',
            sortable: true,
            editor: {}
        }, {
            text: '排序',
            width: 80,
            dataIndex: 'bean.sort',
            sortable: true,
            editor: {
                xtype: 'numberfield',
                allowDecimals: false
            }
        }, {
            text: '是否主图',
            width: 100,
            dataIndex: 'bean.mainImg',
            sortable: true,
            renderer: mvc.Tools.optRenderer('sys', 'Sys', 'OYn'),
            editor: mvc.Tools.crtComboForm(true, {
                name: 'bean.mainImg',
                store: Ext.create('mvc.combo.sys.SysOYn')
            })
        }];
        this.store = Ext.create('mvc.store.cnt.CntAdLine');
        this.store.pageSize = 0;
        this.store.remoteFilter = true;
        this.store.proxy.filterParam = 'filter';
        this.plugins = [this.cellEditing];
        this.callParent(arguments);
    },
    listeners: {
        cellclick: function (grid, td, cellIndex, record, tr, rowIndex, e, eOpts) {
            if (cellIndex == 3) {
                var win = Ext.create('Ext.window.Window', {
                    items: {
                        anchor: '100%',
                        plain: true,
                        xtype: Ext.create('Ext.form.Panel', {
                        	layout: 'column',
                            items: [{
                                xtype: 'imagefield',
                                buttonName:"上传",
                                url: base_path + 'cnt_CntAd_upload'
                            }],
                        	buttons: [{
                        		text:'保存',
                        		handler: function(){
                                    var form = this.up('form');
                                    record.set("bean.image", form.down("panel").text.getValue());
                                    this.up("window").close();
                                }
                        	}]
                        })
                    }
                })
                win.show();
            }
        }
    },
    onIns: function () {
        var model = Ext.create('mvc.store.cnt.CntAdLine');
        this.store.insert(0, model);
        this.cellEditing.startEditByPosition({
            row: 0,
            column: 0
        });
    },
    onDel: function () {
        var selection = this.getView().getSelectionModel().getSelection();
        if (selection) {
            this.getStore().remove(selection);
        }
    }
});