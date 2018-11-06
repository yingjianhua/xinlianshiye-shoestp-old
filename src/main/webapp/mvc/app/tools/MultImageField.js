Ext.define('mvc.tools.MultImageField', {
    extend: 'Ext.form.Panel',
    alias: 'widget.multiimagefield',
    Text: "多图上传",
    imageWidth: 128,
    imageHeight: 128,
    name: 'bean.images',
    maxFileNumber: 2, //文件上传数量。。-1 为不作为限制
    path: null,
    widthLimit: 0,
    url: base_path + '/sys_SysAccessory_uploadImage',
    border: false,
    initComponent: function () {
        _self = this
        _self.imgaddButon = this.imgBody("/images/mvc/addImg.png", {
            el: {
                click: function () {
                    Ext.getDom("multfiles").click()
                }
            }
        })
        this.items =
            [{
                xtype: "label",
                style: {
                    "font-weight": "bold"
                },
                text: _self.Text,
            }, {
                xtype: 'container',
                layout: 'column',
                style: {
                    "overflow-x": "auto",
                    height: (this.imageHeight + 10) + "px"
                },
                items: [],
                id: "ImgView",
            },
                {
                    xtype: "hidden",
                    name: _self.name,
                    id: "fileValue", listeners: {
                        scope: this,
                        change:
                            function () {
                                images = Ext.getCmp("fileValue").getValue().split(",")
                                view = Ext.getCmp("ImgView")
                                if (view.items != null && view.items.length > 0) {
                                    view.removeAll()
                                    view.doLayout()
                                }
                                for (i = 0; i < images.length; i++) {
                                    imgurl = images[i];
                                    if (images[i].length > 2) {
                                        if (images[i].indexOf("/") != 0) {
                                            imgurl = "/" + imgurl
                                        }
                                        view.insert(0, _self.imgBody(imgurl, {
                                            el: {
                                                click: function () {
                                                    srcs = Ext.getCmp(this.dom.id).autoEl.src
                                                    if (srcs.indexOf("/") == 0) {
                                                        srcs = srcs.substring(1, srcs.length)
                                                    }
                                                    Ext.getCmp("fileValue").setValue(Ext.Array.remove(Ext.getCmp("fileValue").getValue().split(","), srcs));
                                                }
                                            }
                                        }))
                                    }
                                }
                                view.doLayout()
                                if (view.items.length < _self.maxFileNumber || _self.maxFileNumber == -1)
                                    view.add(_self.imgaddButon)
                            }
                    }
                },
                {
                    buttonOnly: true,
                    // xtype: 'filefield',
                    // name: "",
                    // id: "multfiles",
                    autoEl: {
                        html: "<input type='file'  id='multfiles'>"
                    },
                    style: {
                        display: "none"
                    },
                    listeners: {
                        scope: this,
                        change:
                            function (b, a) {
                                console.log("!212121")
                                this.uploads();
                            }
                    }
                }
            ]
        this.callParent();
        Ext.getCmp("fileValue").fireEvent("change");

    }, 
    afterRender:function() {
        this.callParent()
        Ext.getDom("multfiles").onchange = function () {
            _self.uploads("file", this);
        }
    },

    imgBody: function (url, fun) {
        return {
            xtype: 'box',
            autoEl: {
                tag: "img",
                src: url,
                style: {
                    height: "118px",
                    Width: "118px",
                    padding: "10px",
                    border: "1px #ccc  dashed",
                }
            },
            listeners: fun
        }
    },
    uploads: function (a, b) {
        console.log(a)
        console.log(b)
        var me = this;
        view = Ext.getCmp("ImgView")
        if (view.items.length - 1 >= me.maxFileNumber && me.maxFileNumber != -1) {
            Ext.MessageBox.show({
                title: "错误",
                msg: "以达到上传图片上限<br/>图片限制数为:" + me.maxFileNumber + "张",
                buttons: Ext.MessageBox.OK,
                icon: Ext.MessageBox.ERROR
            });
            // view.remove(view.items.last())
            return;
        }
        if (me.isValid()) {
            //使用原声Html5 提交文件
            try {
                var formData = new FormData();

            } catch (e) {
                alert("您的浏览器不支持多图上传，请升级到IE10以上的版本")
            }
            formData.append("file", b.files[0]);
            var oReq = new XMLHttpRequest();
            oReq.onreadystatechange = function () {
                if (oReq.readyState == 4) {
                    if (oReq.status == 200) {
                        result = JSON.parse(oReq.responseText);
                        images = Ext.getCmp("fileValue").getValue().split(",");
                        images.push(result.url)
                        Ext.getCmp("fileValue").setValue(images)
                    }
                }
            };
            oReq.open("POST", me.url + '?widthLimit=' + me.widthLimit);
            oReq.send(formData);
        }
    },
    getSubmitValue: function () {
        return this.path;
    }
});
