Ext.define("mvc.tools.MultiLanguageField", {
	extend : 'Ext.form.FieldContainer',
	alias : 'widget.multilanguagefield',
	onlyCurLanguage:true,
	afterLabelTextTpl : undefined,
	allowBlank : undefined,
	fieldLabel : '',
	name : '',
	initComponent : function() {
		var me = this;
		var formFlds=[];
		var array = [];
		Ext.Ajax.request({
			async:false,
		    url: 'plt_PltConfig_enabledLanguage',
		    success: function(response){
		    	array = JSON.parse(response.responseText);
				Ext.Object.each(array, function(key, value) {
					var item = {
							xtype : 'textfield',
							name : value.shortName,
							beanName : me.name,
							fieldLabel : value.displayName,
							getSubmitValue : function() {
								return null;
							}}
					if(me.onlyCurLanguage) {
						if(value.shortName==cur_lang) {
							item["afterLabelTextTpl"]=me.afterLabelTextTpl;
							item["allowBlank"]=me.allowBlank;
						} else {
							item["hidden"]=true;
						}
					} else {
						if(value.isEnabled) {
							if(value.shortName==cur_lang)
								item["afterLabelTextTpl"]=me.afterLabelTextTpl;
								item["allowBlank"]=me.allowBlank;
						} else {
							item["hidden"]=true;
						}
					}
					formFlds.push(item);
				});
				formFlds.push({
					xtype : 'textfield',
					name : me.name,
					hidden : true,
					listeners: {
						change: function(field, newv, oldv) {
							var parent = this.findParentByType("fieldcontainer")
								if(newv!="")
								Ext.Object.each(JSON.parse(newv), function(key, value) {
									parent.query("[name='"+key+"']")[0].setValue(value);
								});
						}
					},
					getSubmitValue : function() {
						var a = {};
						var parent = this.findParentByType("fieldcontainer");
						Ext.Array.each(parent.query("[beanName='"+me.name+"']"),function(field, index) {
									a[field.getName()] = field.getValue();
								});
						a = JSON.stringify(a);
						return a;
					}
				})
		    }
		});
		this.items = formFlds;
		this.callParent(arguments);
	}
})