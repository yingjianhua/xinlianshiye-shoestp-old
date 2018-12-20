<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="footer">

    <div class="footer-1">
        <div>
            <div class="right">
                <h3>Subscribe Now！</h3>
                <!-- <p>Receive The Latest Offers and Promotions Free your Email  Subscribe</p> -->
                <p>Receive The Latest Offers and Promotions Free </p>
                <input type="text" v-model="form.email" placeholder="Your Email"><span
                    @click="subscribe">Subscribe</span>
            </div>
            <div class="left">
                <h3 style="text-indent: 2px;">Follow Us</h3>
                <div class="logos">

                    <a href="http://www.facebook.com/share.php?src=bm&v=4&u=https%3A%2F%2Fwww.shoestp.com%2Fhome%2Fusr_UsrPurchase&t=facebook"><img
                            src="images/ljxlogo1.png" alt=""></a>
                    <a href="https://twitter.com/intent/tweet?status=twitter:+https%3A%2F%2Fwww.shoestp.com%2Fhome%2Fusr_UsrPurchase"><img
                            src="images/ljxlogo2.png" alt=""></a>
                    <a href="https://www.baidu.com/link?url=U7A1o2bi8aWiIxRt-ojE-oGQL6-OE9SPkWyhfUBtayRPB4d2lKrLs21wYbw_m0Qx&wd=&eqid=ad5b5a8400044197000000045c138692"><img
                            src="images/ljxlogo3.png" alt=""></a>
                    <a href="http://www.linkedin.com/cws/share?url=https%3A%2F%2Fwww.shoestp.com%2Fhome%2Fusr_UsrPurchase&title=linkedin"><img
                            src="images/ljxlogo4.png" alt=""></a>
                    <a href="javascript: void(0);"><img src="images/ljxlogo5.png" alt=""></a>
                    <a href="javascript: void(0);"><img src="images/ljxlogo6.png" alt=""></a>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-2">
        <div>
            <ul class="licon" v-for="d in list">
                <li class="lititle"><a>{{d.name}}</a></li>
                <li class="libody" v-for="dd in d.pages"><a
                        :href="'/home/cnt_CntSglPageCategory_gosglpage?pkey='+dd.id">{{dd.title}}</a></li>
            </ul>
        </div>
    </div>
</div>

<!-- <div class="footer-3">
<h3>版权所有©2016-2017温州新联实业股份有限公司。版权所有。浙ICP备16034166号-1浙公网安备33030402000493号 </h3>
</div> -->
<script>
    var footer = new Vue({
        el: "#footer", i18n,
        data() {
            return {
                list: [],
                form: {
                    email: ''
                }
            }
        },
        computed: {},
        mounted() {
            var self = this
            axios({
                url: "/home/cnt_CntSglPageCategory_listAll",
                method: "get"
            }).then(function (res) {
                if (res.data.ret && res.data.ret == 1) {
                    self.$data.list = res.data.result
                } else {
                    console.error("ERR::FLAG")
                }
            }).catch(function (err) {
                console.error(err)
                console.error("ERR::FLAG")
            });
        },
        methods: {
            subscribe() {
                if (!this.form.email || this.form.email == "") {
                    this.$message.error("Email cannot be empty, please re-fill");
                    return;
                } else if (!(/[\w]+(\.[\w]+)*@[\w]+(\.[\w])+/.test(this.form.email))) {
                    this.$message.error("The mailbox format is incorrect, please re-fill");
                    return;
                }
                axios.post('/home/usr_UsrSubscribe_ins', Qs.stringify({
                    "bean.email": this.form.email,
                }, {allowDots: true}))
                    .then((res) => {
                        if (res.data.success) {
                            this.form = {};
                            this.$message.success("Submitted successfully");
                        } else {
                            console.error("ERR::FLAG")
                        }
                    })
                    .catch((err) => {
                        console.error("ERR::FLAG")
                        console.log(err);
                    });
            }
        }
    })


</script>
