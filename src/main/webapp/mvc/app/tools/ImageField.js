Ext.define('mvc.tools.ImageField', {
		extend : 'Ext.form.Panel',
		alias : 'widget.imagefield',
		fieldLabel : "",
		buttonText : "上传图片",
		image: null,
		imageWidth : 170,
		imageHeight : 100,
		widthLimit : 0,
		name : '',
		path : null,
		file : null,
		text : null,
		url : null,
		layout : {
			type : 'table',
			columns : 1,
			itemCls : 'x-layout-table-items-form'
		},
		border : false,
		initComponent : function() {
			var me = this;
			this.items = [{
				xtype : 'textfield',
				name : me.name,
				fieldLabel : '照片',
				hidden : true,
				listeners : {
					scope : this,
					change : function(field, newv, oldv,opts) {
						this.image.getEl().dom.innerHTML = "<img src='"+(new RegExp("^(http|https)://").test(newv)?newv:(imageBaseUrl+newv))+"' style='width:"+this.imageWidth+"px;height:"+this.imageHeight+"px'/>";
					}
				}
			},{
				xtype : 'filefield',
				name : 'file',
				buttonOnly : true,
				fieldLabel : me.fieldLabel,
				buttonText : me.buttonText,
				listeners : {
					scope : this,
					change : function(field, newv, oldv,opts) {
						this.uploads();
					}
				}
			},{
				xtype : 'box',
				style : {
					float : "left"
				},
				width : me.imageWidth, // 图片宽度 
				height : me.imageHeight+20, // 图片高度 
				autoEl : {
					tag : 'div',
					html : "<img style='width:"+me.imageWidth+"px;height:"+me.imageHeight+"px'/>"
				}
			}],
			this.callParent();
			this.text = this.items.items[0];
			this.file = this.items.items[1];
			this.image = this.items.items[2];
		},
		uploads : function () { 
			var me = this;
			me.text.setDisabled(true);
			if (me.isValid()) {
				me.submit({
					url : me.url + '?widthLimit=' + me.widthLimit,
					submitEmptyText: false,
					type : 'ajax',
					params : {insFlag : this.insFlag},
					success : function(form, action) {
						var result = action.result.result;
						this.text.setValue(result.url);
						me.text.setDisabled(false);
					},
					failure : function(form, action) {
						console.log("failure")
						Ext.MessageBox.show({
							title : msg_title,
							msg : action.result.msg,
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.ERROR
						});
						me.text.setDisabled(false);
					},
					waitTitle : wait_title,
					waitMsg : wait_msg,
					scope : this
				});
			}
		},
		getSubmitValue : function() {
			return this.path;
		}
		});
