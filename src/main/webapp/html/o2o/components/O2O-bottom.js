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
            <a href="/home/cnt_CntSglPageCategory_gosglpage?pkey=2"><p>Privacy Policy - Team and Conditions - Refund Policy -Regarding Payment - FAQ</p>        
            <p><img src="images/o2obottomzf.png" alt="">©1988-2019 xinlian.com ALL right reserved</p>
            </a>
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
                    title:'WholeSale',
                    url:[
                        {
                            atitle:'Men',
                            alink:'/home/pdt_PdtProduct?cated=373'
                        },
                        {
                            atitle:'Women',
                            alink:'/home/pdt_PdtProduct?cated=380'
                        },
                        {
                            atitle:'Children',
                            alink:'/home/pdt_PdtProduct?cated=387'
                        }
                    ]
                },
                {
                    title:'Trade Shows',
                    url:[
                        {
                            atitle:'Romania',
                            alink:'/country/Romania-Pantofi-en-gros/romania-index-ro.html'
                        }
                    ]
                },
                {
                    title:'About us',
                    url:[
                        {
                            atitle:'About shoestp.com',
                            alink:'/home/cnt_CntSglPageCategory_gosglpage?pkey=6'
                        },
                        {
                            atitle:'shoestp coopertation plan',
                            alink:'/home/cnt_CntSglPageCategory_gosglpage?pkey=7'
                        },
                        {
                            atitle:'Contact US',
                            alink:'/home/cnt_CntSglPageCategory_gosglpage?pkey=8'
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
