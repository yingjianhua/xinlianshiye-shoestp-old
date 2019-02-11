Vue.component('index-top',{
    template:`<div><div class="o2otop-placeholder"></div><div id="o2otop">
        <div class="o2otopcon">
            <!--<img src="/home/v3/static/images/o2otoplogo.png" alt="">-->
            <a href="/home/usr_UsrPurchase" target="view_window"><img src="/home/v3/static/images/o2otoplogo.png" class="logo" alt="logo"></a>
            <div class="topsearch">
                <el-select   v-model="select" placeholder="Product" @change="selected">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                 </el-select>
                 <input type="text" v-model="input" style="height: 100%;padding: 0;">
                 <div class="searchbotton" @click="submit"><img src="/home/v3/static/images/indexiconserch.png" alt=""></div>
            </div>
            <a class="o2otoplikes"
              :href="sysConfig.user?'/home/usr_UsrPurchase_userIndex':'/home/usr_UsrPurchase_sign'">
                <img src="/home/v3/static/images/indextopren.png" alt="">
                <div class="o2otoplikenum">
                    <div style="height: 20px;">
                      <p v-if="sysConfig.user && sysConfig.user.inquiry_count">
                        {{sysConfig.user.inquiry_count}}
                      </p>
                       <!--<p v-if="!sysConfig.user">-->
                       <!--0-->
                      <!--</p>-->
                    </div>
                    <p>My Shosetp</p>
                </div>
            </a>
            <a class="o2otoplikes" href="/home/usr_UsrFavorites_myfavorite">
                <img src="/home/v3/static/images/o2otopfavoroite.png" alt="">
                <div class="o2otoplikenum">
                    <div style="height: 20px;">
                      <p v-if="sysConfig.user">{{sysConfig.user.favorite_count }}</p>
                      <p v-if="!sysConfig.user"> 0</p>
                    </div>
                    <p>Favorites</p>
                </div>
            </a>
        </div>
 </div></div>`,
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
            ],
            user:null
        }
    },
    mounted(){
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
                    console.log(res.data.ret)
                    if (res.data.result.user) {
                        self.$set(self,'user',res.data.result.user)
                    }
                }
            })
        }
    }
})
