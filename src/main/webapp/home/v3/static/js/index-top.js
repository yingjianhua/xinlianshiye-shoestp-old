Vue.component('index-top',{
    template:`<div id="o2otop">
        <div class="o2otopcon">
            <img src="/home/v3/static/images/o2otoplogo.png" alt="">
            <div class="topsearch">
                <el-select   v-model="select" placeholder="Product" @change="selected">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                 </el-select>
                 <input type="text" v-model="input">
                 <div class="searchbotton" @click="submit"><img src="/home/v3/static/images/indexiconserch.png" alt=""></div>
            </div>
            <!--<div class="o2otoplikes" v-if="user">-->
                <!--<img src="/home/v3/static/images/indextopren.png" alt="">-->
                <!--<div class="o2otoplikenum">-->
                    <!--<p>{{user.shopping_cart_count}}</p>-->
                    <!--<p>My Shosetp</p>-->
                <!--</div>-->
            <!--</div>-->
            <div class="o2otoplikes">
                <img src="/home/v3/static/images/o2otopfavoroite.png" alt="">
                <div class="o2otoplikenum">
                    <p v-if="user">{{user.favorite_count}}</p>
                    <p v-if="!user">0</p>
                    <p>Favorites</p>
                </div>
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
