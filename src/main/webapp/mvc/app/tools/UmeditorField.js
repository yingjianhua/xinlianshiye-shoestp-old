Ext.define('mvc.tools.UmeditorField', {
	extend : 'Ext.form.FieldContainer',
	alias : 'widget.umeditorfield',
	mixins : {
		field : Ext.form.field.Field
	},
	fieldLabel : "",
	name : '',
	height : 400,
	fieldWdith : 0,
	ueditorId : null,
	ueditor : null,
	ueditorContent : '',
	initialized : false,
	imageUrl : '',
	layout : {
		type : 'table',
		columns : 1,
		itemCls : 'x-layout-table-items-form'
	},
	border : false,

	initComponent : function() {
		var me = this;
		me.ueditorId = me.id + '-ueditor';
		me.html = '<script id="' + me.ueditorId + '" type="text/plain" name="'
				+ me.name + '"></script>';
		this.callParent();
		//this.initField();
	},
	listeners : {
		render : function() {
			var me = this;
			var options = {
					initialFrameWidth : this.up("window").getWidth() - 125,
					initialFrameHeight : this.height - 77,
					imagePath : imageBaseUrl
				}
			if(me.imageUrl != '') {
				options["imageUrl"]=me.imageUrl;
			}
			this.ueditor = UM.getEditor(this.ueditorId, options);
			this.ueditor.ready(function() {
				this.setContent(me.ueditorContent);
				me.initialized = true;
			});
		}
	},
	getValue : function() {
		var me = this, value = '';
		if (me.initialized) {
			value = me.ueditor.getContent();
		}
		me.value = value;
		return value;
	},
	setValue : function(value) {
		var me = this;
		if (value === null || value === undefined) {
			value = '';
		} else {
			me.isChanged = true;
		}
		if (me.initialized) {
			me.ueditor.setContent(value);
		} else {
			me.ueditorContent = value;
		}
		return me;
	},
	onDestroy : function() {
		this.ueditor.destroy();
	}
});
