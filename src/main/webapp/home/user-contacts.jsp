<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="/home/v3/static/css/user/ureset.css"/>
<link rel="stylesheet" href="/home/v3/static/css/user/uindex.css"/>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>

</head>

<body>
<jsp:include page="v3/nav-nobody.jsp"></jsp:include>
   <div id="personalCenter" class="clearfix" v-cloak>
        <div class="user-menu fl">
            <div class="user-menu-title"><img src="/home/v3/static/images/user/icon_account.png" alt="" style="margin:0 8px 2px 0;">My Account
        </div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_userIndex">Home <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrMessages_center">Message Center <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a style="color:#10389c;" href="/home/usr_UsrPurchase_contacts">Contacts <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrFavorites_myfavorite">My Favoutities <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_usrSetting">Account Settings <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        </div>
        <!-- 联系 -->
        <div class="contacts-main fr clearfix">
            <div class="contacts-main-left fl">
                <!-- "contactPkey" == {{contactPkey}} -->
                <!-- "start" == {{start}} -->  
                <!-- "allGroupCount" == {{allGroupCount}} <br>   
                "nowGroupCount" == {{nowGroupCount}} -->
                <!-- <div class="select">ALL CONTACTS-2</div> -->
                <!-- <dl @mouseenter="hoverMouseEnter" @mouseleave="hoverMouseLeave"> -->
                <dl class="usermessage" ref="box">
                    <dt class="select-header flexSb usermessage" @click.stop="clickSelect">
                        <div class="flexCc" style="text-align:center;width:100%;">
                         <span class="ellipsis1" style="display: inline-block;max-width:116px;line-height: 16px;">
                            {{groupName}}&nbsp;
                         </span> 
                         <span> 
                               - {{nowGroupCount}}
                         </span> 
                        
                        </div>
                        <i class="el-icon-arrow-up" :class="isShowHoverSelect?'rotate-i':''"></i>
                    </dt>
                    <dd  class="select-content usermessage" v-show="isShowHoverSelect">
                        <div class="select-title">
                            <div class="flexSb" @click="clickGroupItem($event,'','ALL CONTACTS',allGroupCount)" style="cursor: pointer;">
                                <div style="width:130px;" class="ellipsis1">ALL CONTACTS</div>
                                <div>{{allGroupCount}}</div>
                            </div>
                        </div>
                        <div class="add-group">
                            <div class="flexSb" @click="clickShowAddGruop">
                                <div>Groups</div>
                                <div class="add-font">+</div>
                            </div>
                            <div class="add-group-input clearfix" v-if="isAddSearchGroup">
                                <input type="text"  ref="addInputFocus" placeholder="Add a new group" v-model.trim="addInput" @keyup.enter="addGroup">
                                <div class="fr add-group-go" @click="addGroup">Go</div>
                            </div>
                            <div class="edit-group-input clearfix" v-if="isEditSearchGroup">
                                <input type="text" ref="editInputFocus" placeholder="Edit group" v-model.trim="editInput" @keyup.enter="editGroup">
                                <div class="fr add-group-go" @click="editGroup">Go</div>
                            </div>

                        </div>
                        <ul>
                            <li v-if="item.pkey" class="flexSb" v-for="(item,index) in groupList" :key="index" @click="clickGroupItem($event,item.pkey,item.name,item.count)">
                                <div class="ellipsis1">{{item.name}}</div>
                                <div><img src="/home/v3/static/images/user/bianxie.png" alt="Edit" @click.stop="clickShowEditGruop(item.name,item.pkey)"><img
                                        src="/home/v3/static/images/user/lajitong.png" alt="Delete" style="padding-right:0;" @click.stop="deleteGroup(item.pkey)"></div>
                            </li>
                        </ul>
                    </dd>
                </dl>
                <div class="search clearfix">
                    <input type="text" placeholder="please input the keyword" v-model.trim="contactKeyWord" @keyup.enter="getContactList(contactKeyWord,groupPkey,0,limit)"/>
                    <div class="fr" @click="getContactList(contactKeyWord,groupPkey,0,limit)"><img src="/home/v3/static/images/user/icon_search2.png" alt=""></div>
                </div>
                <div class="cml-list" v-if="contactList.length <= 0">
                    <div class="contact-list-no-data">
                        <img src="/home/v3/static/images/user/no_data1.png" alt="">
                        <div style="font-size:12px;">No results found. <br>
                             Please check search terms and try again.</div>
                    </div>
                </div>
                <div class="cml-list" v-else>
                    <div class="cml-content" v-for="(item,index) in contactList" :key="index">
                        <div class="cml-box"
                            :data-index="index" :class="popupindex == index?'sele':''" @click="clickContact(index,item.supplier.pkey)">
                            <div class="h1 fl">
                                <img :src="image(item.supplier.logo)" alt="">
                            </div>
                            <div class="h2 fl">
                                <div class="supplier-name">{{item.supplier.name}}</div>
                                <div class="fc999 supplier-svs">
                                    <img src="/home/v3/static/images/user/icon-svs.png" alt="">SVS
                                    <span style="margin-left:5px;">{{item.supplier.contacts}}</span>
                                </div>
                            </div>
                            <div class="h3 fr">
                                <div class="fc7f7f7f">{{timeFormat(item.createdDate)}}</div>
                                <el-popover placement="bottom" title="" width="80" trigger="hover">
                                    <div class="cml-pupbox">
                                        <ul>
                                            <li>
                                                <p class="font-b fc666">Add to Grop</p>
                                                <dl>
                                                <!-- {{groupItem.pkey}} -->
                                                    <dd v-if="groupItem.pkey"  @click="moveGroup(item.pkey,groupItem.pkey,groupItem.name,groupItem.count)" v-for="(groupItem,index) in groupList" :key="index">{{groupItem.name}}--{{groupItem.count}}</dd>
                                                </dl>
                                            </li>
                                            <li @click="deleteContact(item.supplier.pkey)">
                                                <p>Delete Contact</p>
                                            </li>
                                        </ul>
                                    </div>
                                    <div slot="reference" class="diandian clearfix">
                                        <i></i><i></i><i></i>
                                    </div>
                                </el-popover>
                            </div>
                        </div>
                        <div class="cml-box2" v-if="item.relation != ''">
                            <h2>Inquiry History</h2>
                            <ul>
                                <li v-for="(inquiryItem,index) in item.relation" :key="index">
                                   <a :href="'/home/usr_UsrMessages_center?supplierPkey=' + item.supplier.pkey + '&consultPkey=' + inquiryItem.consult.pkey + '&relationPkey=' + inquiryItem.quotation.pkey " class="flexSb clearfix">
                                         <div class="h1">
                                        <img v-if="inquiryItem.consult.images"
                                        :src="inquiryItem.consult.type==3?image(inquiryItem.consult.images[0]) + '?x-oss-process=image/resize,w_43,h_43/blur,r_5,s_20':image(inquiryItem.consult.images[0])" alt="" />
                                        <i v-if="inquiryItem.quotation.isNew"></i>
                                    </div>
                                    <div class="h2">{{inquiryItem.consult.title}}</div>
                                    <div class="h3">{{timeFormat(inquiryItem.quotation.createDate).substr(5)}}</div>
                                   </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- {{supplierInfo}} -->
            <div class="contacts-main-right fr" v-if="supplierInfo == ''">
                <div class="supplier-detail-no-data flexCc">
                   <div style="text-align:center;margin-bottom:20px;">
                        <img src="/home/v3/static/images/user/no_data2.png" alt="">
                    <div>No results found. Please check search terms and try again.</div>
                   </div>
                </div>
            </div>
            <div class="contacts-main-right fr" v-else>
                <div class="contacts-info">
                    <div class="flexSb fl">
                        <img :src="image(supplierInfo.logo)" alt="">
                        <span>{{supplierInfo.name}}</span>
                    </div>

                    <div class="conti-more fr">
                        <div class="h1">Move to <i class="el-icon-arrow-up"></i></div>
                        <ul>
                            <li v-if="item.pkey" v-for="(item,index) in groupList" :key="index" @click="moveGroup(contactPkey,item.pkey,item.name,item.count)">{{item.name}}---{{item.count}}</li>
                        </ul>
                    </div>
                </div>
                <div class="comBox clearfix">
                    <div class="title">Contact Information</div>
                    <ul class="info">
                        <li class="clearfix">
                            <span class="h1">Country/Region:</span>
                            <span class="info-span">{{supplierInfo.country}}</span>
                        </li>
                        <li class="clearfix">
                            <span class="h1">Member Since:</span>
                            <span class="info-span">{{timeFormat(supplierInfo.memberSince)}}</span>
                        </li>
                        <li class="clearfix">
                            <span class="h1">Department:</span>
                            <span class="info-span">{{supplierInfo.department}}</span>
                        </li>
                        <li class="clearfix">
                            <span class="h1">Job Title:</span>
                            <span class="info-span">{{supplierInfo.jobTitle}}</span>
                        </li>
                    </ul>
                </div>
                <div class="comBox clearfix">
                    <div class="title">Company Information</div>
                    <ul class="info">
                        <li class="clearfix">
                            <span class="h1">Company:</span>
                            <span class="info-span">{{supplierInfo.company}}</span>
                        </li>
                        <li class="clearfix">
                            <span class="h1">Business Type:</span>
                            <span class="info-span">{{supplierInfo.businessType}}</span>
                        </li>
                        <li class="clearfix">
                            <span class="h1">Contract Manufacturing:</span>
                            <span class="info-span">{{supplierInfo.contractManufacturing}}</span>
                        </li>
                        <li class="clearfix">
                            <span class="h1">Company Certification:</span>
                            <span class="info-span">{{supplierInfo.companyCertification}}</span>
                        </li>
                    </ul>
                    <ul class="info">
                        <li class="clearfix">
                            <span class="h1 info-h1">Main Products:</span>
                            <span class="info-span">{{supplierInfo.mainProducts}}</span>
                        </li>
                        <li class="clearfix">
                            <span class="h1 info-h1">Main Market:</span>
                            <span class="info-span">{{supplierInfo.mainMarket}}</span>
                        </li>
                        <li class="clearfix">
                            <span class="h1 info-h1">Overseas Office:</span>
                            <span class="info-span">{{supplierInfo.overseasOffice}}</span>
                        </li>
                    </ul>
                </div>
                <div class="comBox clearfix" style="padding-bottom: 8px;">
                    <div class="title" style="padding-bottom: 4px;">Key Activity Information</div>
                    <div>
                        <div class="comBox-b2 clearfix">
                            <h2>Visit Activilty(last 90 days)</h2>
                            <ul class="info">
                                <li class="clearfix">
                                    <span class="h1">Days visited:</span>
                                    <span class="info-span">Hidden</span>
                                </li>
                                <li class="clearfix">
                                    <span class="h1">Add to Blacklist:</span>
                                    <span class="info-span">Hidden</span>
                                </li>
                            </ul>
                            <ul class="info">
                                <li class="clearfix">
                                    <span class="h1 info-h1">Listed as a Contact:</span>
                                    <span class="info-span">Hidden</span>
                                </li>
                            </ul>
                        </div>
                        <div class="comBox-b2 clearfix">
                            <h2>RFQ Activilty(last 90 days)</h2>
                            <ul class="info">
                                <li class="clearfix">
                                    <span class="h1">Valid RFQs Submitted:</span>
                                    <span class="info-span">Hidden</span>
                                </li>
                            </ul>
                            <ul class="info">
                                <li class="clearfix">
                                    <span class="h1 info-h1">Quotation Approved:</span>
                                    <span class="info-span">Hidden</span>
                                </li>
                            </ul>
                        </div>

                        <div class="comBox-b2 clearfix">
                            <h2>Inquiry Activilty(last 90 days)</h2>
                            <ul class="info">
                                <li class="clearfix">
                                    <span class="h1">Suppliers Response Rate:</span>
                                    <span class="info-span">Hidden</span>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
<script src="/home/v3/static/js/index-bottom.js"></script>
<div id="bottom" style="margin-top: 25px">
    <index-bottom></index-bottom>
</div>

<script>
    new Vue({
        el: "#bottom"
    })
</script>

    <script>
        new Vue({
            el: "#personalCenter",
            data: {
                start:0, //联系人列表分页 起始
                limit:8, //联系人列表分页 每页数量
                contactMoreSwitch:true, // 滚动加载更多开关
                popupindex: '',   // 联系人列表 索引
                isAddSearchGroup: false, // 是否显示添加分组输入框
                isEditSearchGroup: false, // 是否显示编辑分组输入框
                contactList: [], // 联系人列表
                addInput: '', // 添加分组的input值
                editInput: '', // 编辑分组的input值
                groupList: [], // 分组列表
                editNewPkey: '',
                contactKeyWord:'', // 联系人列表搜索值
                groupPkey:'', // 分组ID
                isShowHoverSelect:false,
                supplierInfo:[], // 供应商信息
                supplierPkey:'', // 供应商联系人pkeyy
                contactPkey:'', // 供应商移动分组 pkey
                isGroupShow: false,  // 是否显示分组
                groupName:'ALL CONTACTS',  // 分组名字
                nowGroupCount:this.allGroupCount,  // 当前分组联系人数量
                allGroupCount:'',  // 总联系人数量
            },
            created(){
                document.addEventListener('click',(e)=>{
                    console.log("this.$refs.box.contains(e.target)");
                    console.log(this.$refs.box.contains(e.target));
                    if(!this.$refs.box.contains(e.target)){
                        this.isShowHoverSelect = false;
                    }
                })
            },
            mounted() {
                this.getGroupList(); //获取分组
                this.getContactList(this.contactKeyWord,this.groupPkey,this.start,this.limit); //获取联系人
                this.$nextTick(() => {
                    const el = document.querySelector('.cml-list');
                    const offsetHeight = el.offsetHeight;
                    el.onscroll = () => {
                        const scrollTop = el.scrollTop;
                        const scrollHeight = el.scrollHeight;
                        if ((offsetHeight + scrollTop) - scrollHeight >= -1) {
                            console.log("到底了")
                        // 滚动到底部需要执行的代码
                        if(this.contactMoreSwitch){
                            this.start = this.start  + this.limit
                            //     this.getContactList(this.contactKeyWord,this.groupPkey,this.start,this.limit)
                            axios.get(
                                '/home/rfq_RFQContact_page', {
                                    params: {
                                        keyword:this.contactKeyWord,
                                        groupPkey:this.groupPkey,
                                        start:this.start,
                                        limit:this.limit,
                                    }
                                }
                            )
                            .then((res) => {
                                console.log(res);
                                // this.popupindex = 0;
                                this.contactMoreSwitch = true;
                                this.contactList.push(...res.data.result.items);

                                if(res.data.result.items.length <= 0){
                                    this.contactMoreSwitch = false
                                    this.$message('No more');
                                    this.supplierPkey = '';
                                    // this.contactPkey = '';
                                    // this.supplierInfo = '';
                                }else{
                                    this.popupindex = this.popupindex;
                                    this.supplierPkey = this.contactList[this.popupindex].supplier.pkey
                                    this.contactPkey = this.contactList[0].pkey
                                    console.log("this.supplierPkey ==== " + this.supplierPkey)
                                    this.getSupplierDetail(this.supplierPkey)
                                }
                                // self.contactSwitch

                            })
                            .catch((error) => {
                                console.log("fail");
                                console.log(error);
                            });
                        }else{
                            this.$message('No more');
                        }

                        }
                    };
                    setTimeout(() => {
                        this.nowGroupCount = this.allGroupCount;
                    }, 50);
                });
            },
            methods: {
                clickGroupItem(e,groupPkey,name,count){  // 点击分组
                    this.groupName = name;
                    this.isAddSearchGroup = false;
                    this.isEditSearchGroup = false;
                    this.start = 0;
                    this.groupPkey = groupPkey;
                    this.popupindex= 0;
                    this.contactKeyWord= '';
                    this.getContactList(this.contactKeyWord,this.groupPkey,this.start,this.limit)
                    if(name == "ALL CONTACTS"){
                        this.nowGroupCount = this.allGroupCount;
                    }else{
                      this.nowGroupCount = count;
                    }
                },
                getContactList(keyword,groupPkey,start,limit) { // 获取联系人列表
                    // var self = this;
                    this.start = 0;
                    
                    axios.get(
                            '/home/rfq_RFQContact_page', {
                                params: {
                                    keyword,
                                    groupPkey,
                                    start,
                                    limit,
                                }
                            }
                        )
                        .then((res) => {
                            console.log(res);
                            // this.popupindex = 0;
                            this.contactMoreSwitch = true;
                            this.contactList=[];
                            this.contactList = res.data.result.items;
                            if(res.data.result.items.length <= 0){
                                this.contactMoreSwitch = false
                                this.$message('No more');
                                this.supplierPkey = '';
                                // this.contactPkey = '';
                                this.supplierInfo = '';
                            }else{
                                this.popupindex = this.popupindex;
                                this.supplierPkey = this.contactList[0].supplier.pkey
                                this.contactPkey = this.contactList[0].pkey
                                console.log("this.supplierPkey ==== " + this.supplierPkey)
                                this.getSupplierDetail(this.supplierPkey)
                            }
                            // this.contactKeyWord = ''
                            if(res.data.result.items.length <= 0){

                            }
                            // self.contactSwitch

                        })
                        .catch((error) => {
                            console.log("fail");
                            console.log(error);
                        });
                },

                deleteContact(supplierPkey){  // 删除联系人
                    var self = this;
                    self.$confirm('Whether to delete the contact?', 'Prompt', {
                    confirmButtonText: 'Determine',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                    }).then(() => {
                        console.log("删除联系人")
                        self.nowGroupCount = self.nowGroupCount - 1;
                            return;
                        axios.post('/home/rfq_RFQContact_delete', Qs.stringify({
                            supplierPkey,
                        }, ))
                        .then(function (res) {
                            console.log(res);
                            if (res.data.ret != 1) {
                                self.$message.error(res.data.msg);
                                return
                            };
                            self.start =  0;
                            self.nowGroupCount = self.nowGroupCount - 1;
                            self.getContactList(self.contactKeyWord,self.groupPkey,self.start,self.limit);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                    }).catch(() => {

                    });

                },
                getGroupList() { // 获取分组列表
                    var self = this;
                    axios.get(
                            '/home/rfq_RFQContact_listGroup'
                        )
                        .then((res) => {
                            console.log(res);
                            if (res.data.ret != 1) {
                                self.$message.error(res.data.msg);
                                return
                            };
                            for (let i = 0; i < res.data.result.length; i++) {
                                if(res.data.result[i].name == 'all' && !res.data.result[i].pkey){
                                    self.allGroupCount = res.data.result[i].count;
                                }
                            }
                            // self.nowGroupCount = self.allGroupCount;
                            self.groupList = res.data.result;
                        })
                        .catch((error) => {
                            console.log("fail");
                            console.log(error);
                        });
                },
                clickShowEditGruop(name, pkey) { // 点击显示,隐藏编辑分组
                    var self = this;
                    if (pkey != self.editNewPkey) {
                        self.isEditSearchGroup = true;
                    } else {
                        self.isEditSearchGroup = !self.isEditSearchGroup;
                    }
                    self.editNewPkey = pkey;
                    // self.isEditSearchGroup = !self.isEditSearchGroup;
                    self.editInput = name;
                    if(self.isEditSearchGroup){
                        self.$nextTick(function () {
                            // input 获取焦点
                            self.$refs.editInputFocus.focus()
                        })
                    }
                    console.log(name)
                    console.log(pkey)
                    console.log(self.editNewPkey)
                },
                editGroup() { // 编辑分组
                    var self = this;
                    self.isEditSearchGroup = false;
                    axios.post('/home/rfq_RFQContact_editGroup', Qs.stringify({
                            groupPkey: self.editNewPkey,
                            groupName: self.editInput
                        }, ))
                        .then(function (res) {
                            console.log(res);
                            if (res.data.ret != 1) {
                                self.$message.error(res.data.msg);
                                return
                            };
                            self.$message({
                                        showClose: true,
                                        message: 'Successful editing',
                                        type: 'success'
                                    });
                           self.getGroupList();
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                },
                deleteGroup(pkey) { // 删除分组
                    console.log(pkey)
                    var self = this;
                    self.$confirm('Whether to delete the group?', 'Prompt', {
                    confirmButtonText: 'Determine',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                    }).then(() => {
                        axios.post('/home/rfq_RFQContact_deleteGroup', Qs.stringify({
                            groupPkey: pkey
                        }))
                        .then(function (res) {
                            console.log(res);
                            if (res.data.ret != 1) {
                                self.$message.error(res.data.msg);
                                return
                            };
                            self.$message({
                                        showClose: true,
                                        message: 'Successfully deleted',
                                        type: 'success',
                                        duration:500,
                                        onClose:function(){
                                            window.location.reload();
                                        }
                                    });
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                    }).catch(() => {

                    });
                },
                moveGroup(contactPkey,groupPkey,name,count){ // 移动分组
                    console.log(contactPkey)
                    console.log(groupPkey)
                    console.log("name==============" + name)
                    console.log("count===========" + count)
                    var self = this;
                    axios.post('/home/rfq_RFQContact_moveToGroup', Qs.stringify({
                            contactPkey,
                            groupPkey
                        }))
                        .then(function (res) {
                            console.log(res);
                            if (res.data.ret != 1) {
                                self.$message.error(res.data.msg);
                                return
                            };
                            self.start = 0;
                            self.popupindex = 0;
                            self.$message.success("Mobile success");
                            
                            // if(name == "ALL CONTACTS"){
                            //     self.nowGroupCount = self.allGroupCount;
                            // }else{
                                self.nowGroupCount = self.nowGroupCount - 1;
                            // }
                            self.getGroupList();
                            self.getContactList(self.contactKeyWord,self.groupPkey,self.start,self.limit);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                },
                clickShowAddGruop(){  // 点击是否显示 添加分组框
                    var self = this;
                    console.log(self.isAddSearchGroup)
                    self.isAddSearchGroup  = !self.isAddSearchGroup
                    if(self.isAddSearchGroup){
                        self.$nextTick(function () {
                            // input 获取焦点
                            self.$refs.addInputFocus.focus()
                        })
                    }
                },
                addGroup() { // 添加分组
                    var self = this;
                    if(self.isAddSearchGroup){
                        self.isAddSearchGroup = false;
                    }
                    axios.post('/home/rfq_RFQContact_addGroup', Qs.stringify({
                            groupName: self.addInput
                        }))
                        .then(function (res) {
                            console.log(res);
                            if (res.data.ret != 1) {
                                self.$message.error(res.data.msg);
                                return
                            };
                            self.$message.success("Added successfully");
                            self.addInput = ''
                            self.getGroupList();
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                },
                getSupplierDetail(supplierPkey){  // 获取供应商详情
                    var self = this;
                    axios.get('/home/usr_UsrSupplier_getDetail', {
                                params: {
                                    supplierPkey,
                                }
                            }
                        )
                        .then((res) => {
                            console.log(res);
                            if (res.data.ret != 1) {
                                self.$message.error(res.data.msg);
                                return
                            };
                            self.supplierInfo = res.data.result;
                        })
                        .catch((error) => {
                            console.log("fail");
                            console.log(error);
                        });
                },
                clickContact(index,pkey){ // 点击联系人
                    console.log(index)
                    console.log(pkey)
                    this.popupindex = index;
                    var self = this;
                    self.supplierPkey = pkey;
                    self.getSupplierDetail(pkey);

                },
                hoverMouseLeave(){
                    this.isAddSearchGroup =  false
                    this.isEditSearchGroup =  false
                    this.isShowHoverSelect =  false
                },
                hoverMouseEnter(){
                    this.isShowHoverSelect =  true
                    // this.isEditSearchGroup =  false
                },
                clickSelect(){  // 点击是否 显示  分组
                console.log("显示分组")
                    this.isShowHoverSelect = !this.isShowHoverSelect
                    if(!this.isShowHoverSelect){
                        this.isAddSearchGroup =  false
                        this.isEditSearchGroup =  false
                    }
                },
                image(v, params) { // 图片加前缀 地址
                    if (!v) {
                        return ""
                    }
                    if (!params) {
                        params = ""
                    }
                    return "https://image.shoestp.com" + v + params
                },
                //时间戳转化成时间格式
                 add0(m){return m<10?'0'+m:m },
                 timeFormat(timestamp){
                    var time = new Date(parseInt(timestamp));
                    var year = time.getFullYear();
                    var month = time.getMonth()+1;
                    var date = time.getDate();
                    return year+'-'+this.add0(month)+'-'+this.add0(date);
                },

            },
        })
    </script>

</body> count

</html>
