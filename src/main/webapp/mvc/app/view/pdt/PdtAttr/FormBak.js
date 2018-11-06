Ext.define('mvc.view.pdt.PdtAttr.Form', {
	extend : 'Ext.form.Panel',
	requires : [ 'Ext.ux.DataTip' ],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path + '/pdt_PdtAttr_',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	initComponent : function() {
		if (this.insFlag)
			this.url = this.url + 'ins';
		else
			this.url = this.url + 'upd';
		var formFlds = [];
		formFlds.push({
			xtype : 'textfield',
			name : 'bean.name',
			afterLabelTextTpl : required,
			allowBlank : false,
			fieldLabel : '名称'
		}, mvc.Tools.crtComboTrigger(false, 'pdt_PdtAttrCat', '', {
			name : 'bean.category',
			fieldLabel : '分类'
		}), mvc.Tools.crtComboForm(false, {
			name : 'bean.isSpecAttr',
			fieldLabel : '规格属性',
			store : Ext.create('mvc.combo.sys.SysOYn'),
			value : 1,
			listeners : {
				change : function(combo, newV) {
					var colorAttr = combo.nextSibling("[name='bean.isColorAttr']")||combo.previousSibling("[name='bean.isColorAttr']");
					if(newV == 0) {
						colorAttr.hide();
					} else {
						colorAttr.show();
					}
		}
			}
		}), mvc.Tools.crtComboForm(false, {
			name : 'bean.isColorAttr',
			fieldLabel : '颜色属性',
			store : Ext.create('mvc.combo.sys.SysOYn'),
			value : 1
		}), mvc.Tools.crtComboForm(false, {
			name : 'bean.typingType',
			fieldLabel : '录入方式',
			store : Ext.create('mvc.combo.pdt.PdtOTypingType'),
			value : 0,
			listeners : {
				change : function(combo, newV) {
					this.fireEvent("hideListForm", this, newV==0?true:false)
				},
				scope : this
			}
		}), {
			xtype : 'numberfield',
			name : 'bean.rowVersion',
			value : 0,
			afterLabelTextTpl : required,
			allowBlank : false,
			fieldLabel : '版本',
			hidden : true,
			allowDecimals : false
		}, {
			xtype : 'hiddenfield',
			name : 'bean.pkey'
		});
		this.items = [ {
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			border : false,
			items : formFlds
		} ];
		this.callParent(arguments);
		this.addEvents('hideListForm');
	}
});