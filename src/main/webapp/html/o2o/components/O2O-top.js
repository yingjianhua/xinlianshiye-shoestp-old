Vue.component('o2o-top', {
    template: `<div id="o2otop">
        <div class="o2otopcon">
            <a href="/"><img src="images/o2otoplogo.png" alt=""></a>
            <a href=""><span class="bigspan">O2O Show Room</span></a>
            <a href="highClassO2O.jsp"><span class="smallspan newDesign"  :class="show == 1? 'smallactive' : ''" >New Design</span></a>
            <a href="O2Oinputform.jsp"><span class="smallspan district">Bargain District</span></a>
        </div>
 </div>`,
    props: {
        show:{
            type:Number
        }
    },
    data() {
        return {
            user: {},
        }
    },
    methods: {
        goto: function (where) {
            window.location = where;
        },
        getconfig: function () {
            var self = this
            axios.get('/home/plt_PltConfig_getSysConfig').then(function (res) {
                if (res.data.ret == 1) {
                    if (res.data.result.user) {
                        self.$set(self, 'user', res.data.result.user)
                    }
                }
            })
        }
    },
    mounted() {
        this.getconfig();
    }
})
