Vue.component('o2o-bottom',{
    template:`<div id="o2obottom">
        <div class="o2obottomlinks">
            <ul v-for="ulitem,index in links">
                <li class="ultitle">{{ulitem.title}}</li>            
                <li class="ullink" v-for="liitem,index in ulitem.url">
                    <a :href="liitem.alink">{{liitem.atitle}}</a>
                </li>
            </ul>
        </div>
        <div class="o2obottomcopyright">
            <p>Privacy Policy - Team and Conditions - Refund Policy -Regarding Payment - FAQ</p>        
            <p><img src="images/o2obottomzf.png" alt="">©1988-2019 xinlian.com ALL right reserved</p>
        </div>
        <a href="javascript: void(0);" @click="goto('o2otop')">
            <div class="o2ogototop">
                <img src="images/o2ogototop.png" alt="">
            </div>
        </a>
</div> `,
    props:{

    },
    data(){
        return{
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
        }
    }
})
