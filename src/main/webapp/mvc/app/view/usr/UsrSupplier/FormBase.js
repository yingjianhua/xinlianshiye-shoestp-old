Ext.define('mvc.view.usr.UsrSupplier.FormBase', {
    extend: 'Ext.form.Panel',
    requires: ['Ext.ux.DataTip'],
    layout: 'form',
    border: false,
    frame: false,
    insFlag: true,
    bodyPadding: '5 5 5 5',
    url: base_path + '/usr_UsrSupplier_updBase',
    fieldDefaults: {
        labelWidth: 100,
        labelStyle: 'font-weight : bold'
    },
    initComponent: function () {
        var formFlds = [];
        formFlds.push
        ({xtype: 'textfield', name: 'bean.name', afterLabelTextTpl: required, allowBlank: false, fieldLabel: '名称'}
            ,
            mvc.Tools.crtComboTrigger(false, 'usr_UsrSupplierCategory', '', {
                name: 'bean.category',
                fieldLabel: '供应商分类'
            })
            ,
            mvc.Tools.crtComboForm(false, {
                name: 'bean.isAuth',
                fieldLabel: '供应商认证',
                store: Ext.create('mvc.combo.usr.UsrOIsAuth'),
                value: 0
            })
            , {xtype: 'datefield', name: 'bean.authTime', fieldLabel: '认证时间', format: 'Y-m-d H:i:s'}
            , {
                xtype: 'textfield',
                name: 'bean.entity',
                afterLabelTextTpl: required,
                allowBlank: false,
                fieldLabel: '企业法人'
            }

            , {xtype: 'textfield', name: 'bean.creditCode', fieldLabel: '信用代码'}
            , {xtype: 'datefield', name: 'bean.companyEstablishTime', fieldLabel: '成立时间', format: 'Y-m-d'}
            , {xtype: 'textfield', name: 'bean.operationTerm', fieldLabel: '业务期限'}
            , {xtype: 'textfield', name: 'bean.des', fieldLabel: '描述'}
            , {xtype: 'textfield', name: 'bean.contacts', fieldLabel: '联系人'}
            , {
                xtype: 'textfield',
                name: 'bean.email',
                afterLabelTextTpl: required,
                allowBlank: false,
                fieldLabel: 'Email'
            }
            , {xtype: 'textfield', name: 'bean.phone', fieldLabel: '手机'}
            , {xtype: 'textfield', name: 'bean.telephone', fieldLabel: '电话'}
            , {xtype: 'textfield', name: 'bean.fax', fieldLabel: '传真'}
            , {xtype: 'textfield', name: 'bean.qq', fieldLabel: 'QQ'}
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
            , {xtype: 'multilanguagefield', name: 'bean.seoTitle', fieldLabel: '店铺关键字'}
            , {xtype: 'multilanguagefield', name: 'bean.seoContent', fieldLabel: '搜索引擎说明'}
            , {xtype: 'multilanguagefield', name: 'bean.showName', fieldLabel: '网站显示名称'}
            , {xtype: 'multilanguagefield', name: 'bean.companyNature', fieldLabel: '企业性质'}
            , {xtype: 'multilanguagefield', name: 'bean.companyType', fieldLabel: '企业类型'}
            , {xtype: 'multilanguagefield', name: 'bean.mainSalesArea', fieldLabel: '主销售区'}
            , {xtype: 'multilanguagefield', name: 'bean.mainProd', fieldLabel: '主要产品'}
            , {xtype: 'multilanguagefield', name: 'bean.prodPattern', fieldLabel: '生产模式'}
            , {xtype: 'multilanguagefield', name: 'bean.companyAddr', fieldLabel: '地址'}

            , {
                xtype: 'imagefield',
                name: 'bean.certPhoto',
                fieldLabel: '资质证书',
                url: base_path + '/usr_UsrMemberLevel_upload'
            }
            , {
                xtype: 'imagefield',
                name: 'bean.idCardFrontPhoto',
                fieldLabel: '身份证正面',
                url: base_path + '/usr_UsrMemberLevel_upload'
            }
            , {
                xtype: 'imagefield',
                name: 'bean.idCardBackPhoto',
                fieldLabel: '身份证反面',
                url: base_path + '/usr_UsrMemberLevel_upload'
            }
            , {
                xtype: 'imagefield',
                name: 'bean.coopCertPhoto',
                fieldLabel: '合作凭证',
                url: base_path + '/usr_UsrMemberLevel_upload'
            }
            , {
                xtype: 'hiddenfield',
                name: 'bean.pkey'
            }     , {
                xtype: 'numberfield',
                name: 'bean.sort',
                value: 0,
                afterLabelTextTpl: required,
                allowBlank: false,
                fieldLabel: '排序号',
                allowDecimals: false
            }
            );
        this.items = [{
            layout: {
                type: 'table',
                columns: 3,
                itemCls: 'x-layout-table-items-form'
            },
            border: false,
            items: formFlds
        }];
        this.callParent(arguments);
    }
});
