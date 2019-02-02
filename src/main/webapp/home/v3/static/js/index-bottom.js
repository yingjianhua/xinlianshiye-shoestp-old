Vue.component('index-bottom',{
    template:`<div id="o2obottom">
        <div class="emailform">
            <p>Trade Alert - Delivering the latest product trends and industry news straight to your inbox.</p>
            <div class="email">
                <input type="text" v-model="email" placeholder="Your Emial">
                <div class="botton" @click="submit">Subscribe</div>
            </div>
            <p>We’ll never share your email address with a third-party</p>
        </div>
        <div class="o2obottomlinks">
            <ul v-for="ulitem,index in links">
                <li class="ultitle">{{ulitem.title}}</li>            
                <li class="ullink" v-for="liitem,index in ulitem.url">
                    <a :href="liitem.alink">{{liitem.atitle}}</a>
                </li>
            </ul>
        </div>
        <div class="o2obottomcopyright">
            <p>Privacy Policy - Team and Conditions - <span>Refund Policy</span> -Regarding Payment - FAQ</p>        
            <p><img src="/home/v3/static/images/o2obottomzf.png" alt="">©1988-2019 xinlian.com ALL right reserved</p>
        </div>
        <a href="javascript:void(0)" @click="goto('o2otop')">
            <div class="o2ogototop">
                <img src="/home/v3/static/images/o2ogototop.png" alt="">
            </div>
        </a>
</div> `,
    props:{

    },
    data(){
        return{
            email:'',
            links:[
                {
                    title:'OEM',
                    url:[
                        {
                            atitle:'Men',
                            alink:''
                        },
                        {
                            atitle:'Women',
                            alink:''
                        },
                        {
                            atitle:'Children',
                            alink:''
                        }
                    ]
                },
                {
                    title:'WholeSale',
                    url:[
                        {
                            atitle:'Men',
                            alink:''
                        },
                        {
                            atitle:'Women',
                            alink:''
                        },
                        {
                            atitle:'Children',
                            alink:''
                        }
                    ]
                },
                {
                    title:'Trade Shows',
                    url:[
                        {
                            atitle:'Romania',
                            alink:''
                        }
                    ]
                },
                {
                    title:'About us',
                    url:[
                        {
                            atitle:'About shoestp.com',
                            alink:''
                        },
                        {
                            atitle:'shoestp coopertation plan',
                            alink:''
                        },
                        {
                            atitle:'Contact US',
                            alink:''
                        }
                    ]
                }
            ]
        }
    },
    methods:{
        goto:function (id) {
            document.getElementById(id).scrollIntoView(true);
        },
        submit:function () {
            let regEmail = /.+@[a-z0-9\.]+\.(com|cn|net)$/;
            let self = this;
            if(!this.email){
                this.$alert('email can not be empty', '', {
                    confirmButtonText: '确定',
                    closeOnClickModal:true,
                }).catch(() => {});
                return false;
            }else if(!regEmail.test(this.email)){
                this.$alert('email format error', '', {
                    confirmButtonText: '确定',
                    closeOnClickModal:true,
                }).catch(() => {});
                return false;
            }
            axios.post('/home/usr_UsrSubscribe_ins',
                Qs.stringify({
                    'bean.email':this.email
                })
            ).then(function (res) {
                console.log(res)
                if (res.data.success) {
                    self.$alert('Added to Subscribe Successful', '', {
                        confirmButtonText: '确定',
                        closeOnClickModal:true,
                    }).catch(() => {});
                }
            })
        }
    }
})
