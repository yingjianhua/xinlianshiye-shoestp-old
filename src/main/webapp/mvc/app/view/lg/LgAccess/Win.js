Ext.define('mvc.view.lg.LgAccess.Win', {
	extend : 'Ext.window.Window',
	width : '80%',
	height: 600,
	layout : 'fit',
	resizable : true,
	modal : true,
	style: "font-family: Courier New;",
	iconCls : 'app-icon',
	initComponent : function() {
		var me = this;
		this.items = {
				xtype : "textarea",
				editable : false,
				anchor : '100%',
				fieldStyle : 'font-family:Courier New',
				plain : true
		};
		this.tbar=[{
			xtype : 'combo',
			valueField : 'name',
			displayField : 'name',
			editable : false,
			store : {
				proxy : {
					type : 'ajax',
					url : base_path + '/lg_LgAccess_queryList',
					reader : {
						type : 'json',
						root : 'items'
					}
				},
				fields : [ {
					name : 'name'
				}]
			},
			emptyText : '查询语句',
			listeners : {
				change : function(combo, newValue) {
					if(newValue == null) {
						me.items.items[0].setValue(null);
					} else {
						Ext.Ajax.request({
						    url: base_path+'/lg_LgAccess_query',
						    params: {
						        query: newValue
						    },
						    success: function(response){
						        var text = response.responseText;
						        me.items.items[0].setValue(Ext.JSON.decode(text).items);
						    }
						});
					}
				}
			}
		}];
		this.callParent(arguments);
	}
});