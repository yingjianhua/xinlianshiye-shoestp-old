Ext.define('mvc.view.usr.UsrSupplier.FormPage', {
    extend: 'Ext.form.Panel',
    requires: ['Ext.ux.DataTip'],
    layout: 'form',
    border: false,
    frame: false,
    bodyPadding: '5 5 5 5',
    url: base_path + '/usr_UsrSupplier_updPage',
    fieldDefaults: {
        labelWidth: 100,
        // width : 275,
        labelStyle: 'font-weight : bold'
    },
    initComponent: function () {
        var me = this;
        var formFlds = [];
        formFlds.push
        ({xtype: 'textfield', name: 'bean.signBackgd', fieldLabel: '店招背景'}
            , {xtype: 'textfield', name: 'bean.adPhotoLink', fieldLabel: '广告连接'},
            mvc.Tools.crtComboTrigger(false, 'plt_PltCountry', '', {
                name: 'bean.country',
                fieldLabel: '国家管理',
                listeners: {
                    change: function (f, n, o) {
                        var province = me.down("combo[name='bean.province']");
                        province.getStore().proxy.extraParams = {sarg1: "main=" + n}
                        province.getStore().load();
                        if (o != "undefined") {
                            province.clearValue();
                        }
                    }
                }
            })
            ,
            mvc.Tools.crtComboTrigger(false, 'plt_PltProvince', '', {
                name: 'bean.province',
                fieldLabel: '省份'
            }),
            mvc.Tools.crtComboForm(false, {
                name: 'bean.isPro',
                fieldLabel: '供应商首页产品展示',
                store: Ext.create('mvc.combo.sys.SysOYn'),
                value: 0
            })
            , {xtype: 'textfield', name: 'bean.website', fieldLabel: 'Website'}
            , {xtype: 'textfield', name: 'bean.companyPhotoLink', fieldLabel: '企业图片连接'}
            , {xtype: 'multilanguagefield', name: 'bean.businessTyp', fieldLabel: 'Business_typ'}
            , {xtype: 'multilanguagefield', name: 'bean.location', fieldLabel: 'Location'}
            , {xtype: 'multilanguagefield', name: 'bean.developer', fieldLabel: 'Developer'}
            , {xtype: 'multilanguagefield', name: 'bean.production', fieldLabel: 'Production'}
            , {xtype: 'multilanguagefield', name: 'bean.totalEmployees', fieldLabel: 'Total_employees'}
            , {xtype: 'multilanguagefield', name: 'bean.annualSales', fieldLabel: 'Annual_sales'}
            , {xtype: 'multilanguagefield', name: 'bean.top3Markets', fieldLabel: 'Top 3 Markets'}
            , {xtype: 'multilanguagefield', name: 'bean.materials', fieldLabel: 'Materials'}
            , {xtype: 'multilanguagefield', name: 'bean.department', fieldLabel: 'Department'}
            , {xtype: 'multilanguagefield', name: 'bean.jobTitle', fieldLabel: 'Job_Title'}
            , {xtype: 'multilanguagefield', name: 'bean.city', fieldLabel: 'City'}
            , {
                xtype: 'imagefield',
                name: 'bean.adPhoto',
                fieldLabel: '广告图',
                url: base_path + '/usr_UsrMemberLevel_upload'
            }
            , {
                xtype: 'imagefield',
                name: 'bean.adPhotoMobile',
                fieldLabel: '移动端广告图',
                url: base_path + '/usr_UsrMemberLevel_upload'
            }
            , {
                xtype: 'imagefield',
                name: 'bean.companyPhoto',
                fieldLabel: '企业图片',
                url: base_path + '/usr_UsrMemberLevel_upload'
            }
            , {
                xtype: 'imagefield',
                name: 'bean.headPic',
                fieldLabel: '头像',
                url: base_path + '/usr_UsrMemberLevel_upload'
            }
            , {
                xtype: 'imagefield',
                name: 'bean.logo',
                fieldLabel: 'logo',
                url: base_path + '/usr_UsrMemberLevel_upload'
            }
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
            }
        )
        ;
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
