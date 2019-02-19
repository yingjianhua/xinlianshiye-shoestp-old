Vue.component('index-bottom', {
    template: `<div id="o2obottom">
<div class="brand-list" v-if="LogoIsShow()">
          <a class="brand-item" target="_blank" href="https://www.shoeslogo.com/">
            <img src="/home/v3/static/images/brand01.png" alt="" />
          </a>
          <a class="brand-item" target="_blank" href="http://www.shoesmat.com/">
            <img src="/home/v3/static/images/brand02.png" alt="" />
          </a>
          <a class="brand-item" target="_blank" href="http://www.shoesrd.com/">
            <img src="/home/v3/static/images/brand03.png" alt="" />
          </a>
          <a class="brand-item" target="_blank" href="http://www.wzsomt.com/">
            <img src="/home/v3/static/images/brand04.png" alt="" />
          </a>
        </div>
        <div class="emailform">
            <p>Trade Alert - Delivering the latest product trends and industry news straight to your inbox.</p>
            <div class="email">
                <input type="text" v-model="email" placeholder="Your Email">
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
            <ul class="share-links">
                <li class="ultitle">Follow Us</li>
                <li class="ullink">
                    <a href="http://www.facebook.com/share.php?src=bm&v=4&u=http%3A%2F%2Flocalhost%3A8080%2Fhome%2Fcnt_CntSglPageCategory_gosglpage%3Fpkey%3D8&t=facebook" target="_blank">
                        <img class="icon" src="/home/v3/static/images/icon-facebook.png" alt="" />
                    </a>
                    <a href="https://twitter.com/intent/tweet?status=twitter:+http%3A%2F%2Flocalhost%3A8080%2Fhome%2Fcnt_CntSglPageCategory_gosglpage%3Fpkey%3D8" target="_blank">
                        <img class="icon" src="/home/v3/static/images/icon-bird.png" alt="" />
                    </a>
                    <a href="javascript:void(0);" target="_blank">
                        <img class="icon" src="/home/v3/static/images/icon-g.png" alt="" />
                    </a>
                    <a href="http://www.linkedin.com/cws/share?url=http%3A%2F%2Flocalhost%3A8080%2Fhome%2Fcnt_CntSglPageCategory_gosglpage%3Fpkey%3D8&title=linkedin" target="_blank">
                        <img class="icon" src="/home/v3/static/images/icon-in.png" alt="" />
                    </a>
                    <a href="javascript:void(0);" target="_blank">
                        <img class="icon" src="/home/v3/static/images/icon-tube.png" alt="" />
                    </a>
                </li>
            </ul>
        </div>
        <div class="o2obottomcopyright">
            <a href="/home/cnt_CntSglPageCategory_gosglpage?pkey=2"><p>Privacy Policy - Terms & Conditions - <span>Refund Policy</span> -Regarding Payment - FAQ</p>        
            <p><img src="/home/v3/static/images/o2obottomzf.png" alt="">©1988-2019 xinlian.com ALL right reserved</p>
            </a>
        </div>
        <a href="javascript:void(0)" @click="goto('o2otop')">
            <div class="o2ogototop">
                <img src="/home/v3/static/images/o2ogototop.png" alt="">
            </div>
        </a>
</div> `,
    props: {},
    data() {
        return {
            email: '',
            links: [
                {
                    title: 'Help',
                    url: [
                        {
                            atitle: 'Regarding Payment',
                            alink: '/home/cnt_CntSglPageCategory_gosglpage?pkey=1'
                        }, {
                            atitle: 'FAQ',
                            alink: '/home/cnt_CntSglPageCategory_gosglpage?pkey=2'
                        }
                    ]
                },
                {
                    title: 'Trade Shows',
                    url: [
                        {
                            atitle: 'Romania',
                            alink: '/country/Romania-Pantofi-en-gros/romania-index-ro.html'
                        }
                    ]
                },
                {
                    title: 'Terms & Conditions',
                    url: [
                        {
                            atitle: 'Privacy Policy',
                            alink: '/home/cnt_CntSglPageCategory_gosglpage?pkey=3'
                        }, {
                            atitle: 'Terms & Conditions',
                            alink: '/home/cnt_CntSglPageCategory_gosglpage?pkey=4'
                        }, {
                            atitle: 'Refund Policy',
                            alink: '/home/cnt_CntSglPageCategory_gosglpage?pkey=5'
                        }
                    ]
                }, {
                    title: 'About us',
                    url: [
                        {
                            atitle: 'About shoestp.com',
                            alink: '/home/cnt_CntSglPageCategory_gosglpage?pkey=6'
                        },
                        {
                            atitle: 'shoestp coopertation plan',
                            alink: '/home/cnt_CntSglPageCategory_gosglpage?pkey=7'
                        },
                        {
                            atitle: 'Contact Us',
                            alink: '/home/cnt_CntSglPageCategory_gosglpage?pkey=8'
                        },
                        {
                            atitle: '店铺管理',
                            alink: '/newseller/'
                        }
                    ]
                }
                // {
                //     title: 'WholeSale',
                //     url: [
                //         {
                //             atitle: 'Men',
                //             alink: '/home/pdt_PdtProduct?cated=373'
                //         },
                //         {
                //             atitle: 'Women',
                //             alink: '/home/pdt_PdtProduct?cated=380'
                //         },
                //         {
                //             atitle: 'Children',
                //             alink: '/home/pdt_PdtProduct?cated=387'
                //         }
                //     ]
                // },


            ]
        }
    },
    methods: {
        LogoIsShow() {
            if (window.location.pathname === '/home/cnt_CntSglPageCategory_gosglpage') {
                if (window.location.search.split('=')[1] === '6' || window.location.search.split('=')[1] === '7' || window.location.search.split('=')[1] === '8') {
                    return true
                }
            }
        },
        goto: function (id) {
            document.getElementById(id).scrollIntoView(true);
        },
        submit: function () {
            let regEmail = /.+@[a-z0-9\.]+\.(com|cn|net)$/;
            let self = this;
            if (!this.email) {
                this.$alert('email can not be empty', '', {
                    confirmButtonText: '确定',
                    closeOnClickModal: true,
                }).catch(() => {
                });
                return false;
            } else if (!regEmail.test(this.email)) {
                this.$alert('email format error', '', {
                    confirmButtonText: '确定',
                    closeOnClickModal: true,
                }).catch(() => {
                });
                return false;
            }
            axios.post('/home/usr_UsrSubscribe_ins',
                Qs.stringify({
                    'bean.email': this.email
                })
            ).then(function (res) {
                console.log(res)
                if (res.data.success) {
                    self.$alert('Added to Subscribe Successful', '', {
                        confirmButtonText: '确定',
                        closeOnClickModal: true,
                    }).catch(() => {
                    });
                }
            })
        }
    }
})
