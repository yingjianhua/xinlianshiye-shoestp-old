Vue.component('index-top', {
template: `<div>
    <div class="o2otop-placeholder"></div>
    <div id="o2otop">
        <div class="o2otopcon">
            <!--<img src="/home/v3/static/images/o2otoplogo.png" alt="">-->
            <a href="/home/usr_UsrPurchase" target="view_window"><img src="/home/v3/static/images/o2otoplogo.png" class="logo"
                    alt="logo"></a>
            <div class="topsearch">
                <el-select v-model="select" placeholder="Product" @change="selected">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>
                <input type="text" v-model="input">
                <div class="searchbotton" @click="submit"><img src="/home/v3/static/images/indexiconserch.png" alt=""></div>
            </div>
            <dl class="o2otoplikes">
                <dt>
                    <img src="/home/v3/static/images/indextopren.png" alt="">
                    <div class="o2otoplikenum">
                        <div style="height:20px;">
                         <div  v-if="!sysConfig.user">
                            <a href="/home/usr_UsrPurchase_sign" style="border-right: 1px solid #b7b7b7;padding-right: 3px;color: #4fa2d7;font-size:12px;" target="_blank">Register</a>
                            <a href="/home/usr_UsrPurchase_sign" style="color: #4fa2d7;font-size:12px;" target="_blank">Login</a>
                         </div>
                        </div>
                        <p>My Shoestp</p>
                    </div>
                </dt>
                <!--  没有登录看到的  -->
                <dd v-if="!sysConfig.user">
                    <div style="font-size:16px;margin:  18px  0;">Get started now</div>
                    <div class="login-btn"><a href="/home/usr_UsrPurchase_sign" target="_blank">Login</a></div>
                    <div style="text-align:center;font-size:16px;margin:  8px  0;">or</div>
                    <div class="registered-btn"><a href="/home/usr_UsrPurchase_sign" target="_blank">Registration</a></div>
                    <div style="color:#777777;text-align:center;margin:  15px  0;">Continue with：</div>
                    <div class="clearfix  other-login-list">
                        <div class="fl  other-login-item">
                            <a href="" target="_blank">
                                <img src="/home/v3/static/images/nav/facebook.png" alt="facebook">
                            </a>
                        </div>
                        <div class="fl  other-login-item">
                            <a href="" target="_blank">
                                <img src="/home/v3/static/images/nav/google.png" alt="google">
                            </a>
                        </div>
                        <div class="fl  other-login-item">
                            <a href="" target="_blank">
                                <img src="/home/v3/static/images/nav/in.png" alt="in">
                            </a>
                        </div>
                        <div class="fl  other-login-item">
                            <a href="" target="_blank">
                                <img src="/home/v3/static/images/nav/twitter.png" alt="twitter">
                            </a>
                        </div>
                    </div>
                    <ul>
                        <li><a href="/home/usr_UsrPurchase_userIndex" target="_blank">My Shoestp <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="/home/usr_UsrMessages_center" target="_blank">RFQ List <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="/home/usr_UsrConsult_listView" target="_blank">My Inquiry <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="/home/usr_UsrPurchase_userIndex" target="_blank">My Account <i class="el-icon-arrow-right"></i></a></li>
                        <li style="font-size: 15px;font-weight: bold;"><a href="/home/usr_UsrSupplier_supplierEntry" target="_blank">我要开店 <i class="el-icon-arrow-right"></i></a></li>
                    </ul>
                    <div class="after-login"><a href="/home/usr_UsrConsult_publishView" target="_blank">Submit RFQ</a></div>
                    <div class="hours  after-login">Get multiple quotes within 24 hours！</div>
                </dd>
                <!--  登陆后看到的  -->
                <dd v-if="sysConfig.user">
                    <ul>
                        <li class="flexSb user-name"><div class="ellipsis_1">Hi {{sysConfig.user.name}}</div><a href="/home/usr_UsrPurchase_signOut" target="_blank">sign out</a></li>
                        <li><a href="/home/usr_UsrPurchase_userIndex" target="_blank">My Shoestp <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="//home/usr_UsrMessages_center" target="_blank">RFQ List <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="/home/usr_UsrConsult_listView" target="_blank">My Inquiry <i class="el-icon-arrow-right"></i></a></li>
                        <li><a href="/home/usr_UsrPurchase_userIndex" target="_blank">My Account <i class="el-icon-arrow-right"></i></a></li>
                        <li style="font-size: 15px;font-weight: bold;"><a href="/home/usr_UsrSupplier_supplierEntry" target="_blank">我要开店 <i class="el-icon-arrow-right"></i></a></li>
                    </ul>
                    <div class="after-login"><a href="/home/usr_UsrConsult_publishView" target="_blank">Submit RFQ</a></div>
                    <div class="hours after-login">Get multiple quotes within 24 hours！</div>
                </dd>
            </dl>

            <a class="o2otoplikes" href="/home/usr_UsrFavorites_myfavorite">
                <img src="/home/v3/static/images/icon_top_love.png" alt="" style="margin-right: 10px">
                <div class="o2otoplikenum">
                    <div style="height: 20px;">
                        <p v-if="sysConfig.user">{{sysConfig.user.favorite_count }}</p>
                        <p v-if="!sysConfig.user"> 0</p>
                    </div>
                    <p>Favorites</p>
                </div>
            </a>
        </div>
    </div>
</div>`,
  props:{

    },
    data(){
        return {
            select:0,
            input:'',
            options: [
                {
                    value: 0,
                    label: 'Product'
                },
                {
                    value: 1,
                    label: 'Suppiler'
                }
            ],sysConfig:{}
        }
    },
    mounted(){
      Vue.set(this.$data,'input',unescape(decodeURIComponent(getParams('Keyword',getParams('keyWord',getParams('keyword',''))))))
      this.getconfig();
    },
    methods:{
        submit:function () {
            if(this.input){
                window.location = '/home/pdt_PdtProduct?Keyword=' + this.input + '&v=2&searchtype=' + this.select;
            }else {
                return false;
            }
        },
        selected:function (res) {

        },
        getconfig:function () {
            var self =this
            axios.get('/home/plt_PltConfig_getSysConfig').then(function (res) {
                if (res.data.ret == 1){
                    if (res.data.result.user) {
                        self.$set(self,'user',res.data.result.user)
                        self.$set(self,'sysConfig',res.data.result)
                    }
                }
            })
        }
    }
})
