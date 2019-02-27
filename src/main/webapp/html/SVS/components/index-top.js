Vue.component('index-top',{
    template:`<div id="o2otop">
        <div class="o2otopcon">
            <img src="images/o2otoplogo.png" alt="">
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
                 <div class="searchbotton" @click="submit"><img src="images/indexiconserch.png" alt=""></div>
            </div>
            <div class="o2otoplikes" @click="goto('/home/usr_UsrCart_cartshopping')">
                <img src="images/indextopren.png" alt="">
                <div class="o2otoplikenum">
                    <p>{{user.shopping_cart_count}}</p>
                    <p>My Shosetp</p>
                </div>
            </div>
            <div class="o2otoplikes" @click="goto('/home/usr_UsrFavorites_myfavorite')">
                <img src="images/o2otopfavoroite.png" alt="">
                <div class="o2otoplikenum">
                    <p>{{user.favorite_count}}</p>
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
            user:{
                favorite_count :0,
                id:null,
                inquiry_count:0,
                name:null,
                shopping_cart_count :0,
            }
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
        goto:function(where){
            window.location = where;
        },
        getconfig:function () {
            var self =this
            axios.get('/home/plt_PltConfig_getSysConfig').then(function (res) {
                if (res.data.ret == 1){
                    if (res.data.result.user) {
                        self.$set(self,'user',res.data.result.user)
                    }else{
                        setTimeout(function () {
                            window.location = '/home/usr_UsrPurchase_sign'
                        },3000)
                    }
                }
            })
        }
    }
})
