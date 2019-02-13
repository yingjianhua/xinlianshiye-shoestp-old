<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/home/v2/template/header.jsp"></jsp:include>
<link rel="stylesheet" href="css/reset.css"/>
<script src="./js/jquery-1.7.2.min.js"></script>
<!-- index为以上几个合并后的压缩文件 - 加前缀 -->
<link rel="stylesheet" href="./css/element-ui/element-ui.css"/>
<link rel="stylesheet" href="./css/index.css"/>
<style>
    #o2otop .o2otopcon a:nth-of-type(3) .smallspan {
        color: white;
        background: linear-gradient(to right, rgb(113, 139, 223), rgb(159, 87, 254));
        display: inline-block;
        width: 207px;
        height: 54px;
        line-height: 54px;
        font-size: 22px;
        border-radius: 27px;
        cursor: pointer;
        text-align: center;
    }
</style>


<jsp:include page="/home/v3/nav.jsp"></jsp:include>

<div id="o2oinputform" style="background: #F5F5F5">
    <o2o-top :show="1"></o2o-top>

    <form action="" class="o2o-form" id="form">
        <h3 class="form-title">
            "Shoestp New Product Meeting"
            <div class="form-title2">
                Registration Form
            </div>
        </h3>
        <h3 class="form-title" style="font-family:Arial; font-size: 16px;margin-bottom: 15px;font-weight: bold">Basic
            Information</h3>
        <div class="form-body">
            <!-- 第一行 -->
            <el-row :gutter="20">
                <el-col :span="8">
                    <el-input placeholder="Full Name"
                              v-model="form.fullName" size="medium"
                    ></el-input>
                </el-col>
                <el-col :span="8">
                    <el-select v-model="form.gender" placeholder="Gender" size="medium">
                        <el-option
                                v-for="item in sexoptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
                <el-col :span="8">
                    <el-select v-model="form.country" placeholder="Country" size="medium" :filter-method="filterCountry"
                               filterable>
                        <el-option
                                v-for="item in contoryfilteroptions"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <!-- 第一行 - end -->


            <!-- 第二行 -->
            <el-row :gutter="20">
                <el-col :span="8">
                    <el-input v-model="form.email" placeholder="Email" size="medium"></el-input>
                </el-col>
                <el-col :span="2" style="padding-right:unset">
                    <el-input v-model="form.countryCode" placeholder="Code" size="medium"></el-input>
                </el-col>
                <el-col :span="6">
                    <el-input v-model="form.telphone" placeholder="Phone Number" size="medium"></el-input>
                </el-col>
            </el-row>
            <!-- 第二行 - end -->

            <!-- 鞋款 多选 -->
            <el-row>
                <el-col :span="24">
                    <div class="shoes-choice-wrap">
                        <h6 class="shoes-choice-title"
                            style="    font-family: Arial;    font-size: 16px;    margin-bottom: 15px;    font-weight: bold;">
                            Interested Footwear (Multiple choice)
                        </h6>
                        <ul class="category-list">
                            <li class="category-item" v-for="i in checkboxdata">
                                <h5 class="category-title">
                                    {{i.label}}
                                </h5>
                                <el-checkbox-group v-model="form.Collections" v-for="item in i.children">

                                    <ul class="sub-category-list" v-if="item.children">
                                        <li class="sub-category-item" v-for="childitem in item.children"
                                            :key="childitem.value">
                                            <el-checkbox :label="childitem.value">{{childitem.label}}</el-checkbox>
                                        </li>
                                    </ul>

                                </el-checkbox-group>
                            </li>
                        </ul>
                    </div>
                </el-col>
            </el-row>
            <!-- 鞋款 多选 - end -->

            <!-- 第3行 input -->
            <el-row :gutter="20">
                <el-col :span="16">
                    <el-select v-model="form.marketing" multiple placeholder="Marketing (Multiple choice)"
                               size="medium">
                        <el-option
                                v-for="item in marketingoptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
                <el-col :span="8">
                    <el-select v-model="form.buyerType" placeholder="Buyer Type" size="medium">
                        <el-option
                                v-for="item in buyerTypeyoptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <!-- 第3行 input - end -->

            <!-- 第4行 input -->
            <el-row :gutter="20">
                <el-col :span="16">
                    <el-select v-model="form.exhibitionCountry" multiple
                               placeholder="Areas where you wish to participate (Multiple choice)"
                               size="medium">
                        <el-option
                                v-for="item in exhibitionCountryoptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <!-- 第4行 input - end -->

            <!-- 第5行 input -->
            <el-input
                    class="remarks-input"
                    type="textarea"
                    :rows="7"
                    placeholder="Remark"
                    v-model="form.remarks">
            </el-input>
            <!-- 第5行 input - end -->

            <div class="form-tips">
                Dear guest, please complete <span style="color: #e54544;">the above information</span> or your
                participation request may be rejected.
            </div>
        </div>

        <div class="btn-submit" @click="submit">Submit</div>
    </form>
    <o2o-bottom></o2o-bottom>
    <el-dialog
            :visible.sync="centerDialogVisible"
            width="340px"
            center>
        <span style="padding:50px 30px 60px">{{errmessage}}</span>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="centerDialogVisible = false">Cancel</el-button>
            </span>
    </el-dialog>
</div>

<script>
    new Vue({
        el: "#o2oinputform",
        data() {
            return {
                test: "",
                form: {
                    fullName: "",
                    gender: "",
                    country: "",
                    email: "",
                    countryCode: "",
                    telphone: "",
                    Collections: [],

                    marketing: [],
                    buyerType: "",
                    exhibitionCountry: [],
                    remarks: ""
                },

                checkboxdata: null,
                centerDialogVisible: false,
                errmessage: null,

                sexoptions: [
                    {
                        value: 1,
                        label: "Men",
                    },
                    {
                        value: 2,
                        label: "Women",
                    }
                ],
                contoryoptions: [
                    {
                        value: "Men",
                        label: "Men",
                    },
                    {
                        value: "Women",
                        label: "Women",
                    },
                    {
                        value: "Child",
                        label: "Child",
                    }
                ],
                contoryfilteroptions: [],
                marketingoptions: [
                    {
                        value: 1,
                        label: "North Ameriva",
                    },
                    {
                        value: 2,
                        label: "South Ameriva",
                    },
                    {
                        value: 3,
                        label: "East Asia",
                    },
                    {
                        value: 4,
                        label: "Central Asia",
                    },
                    {
                        value: 5,
                        label: "South Asia",
                    },
                    {
                        value: 6,
                        label: "North Asia",
                    },
                    {
                        value: 7,
                        label: "South Africa",
                    },
                    {
                        value: 8,
                        label: "Eastem Europe",
                    },
                    {
                        value: 9,
                        label: "Westem Europe",
                    },
                    {
                        value: 10,
                        label: "Northem Europe",
                    },
                    {
                        value: 11,
                        label: "Central Europe",
                    },
                    {
                        value: 12,
                        label: "Austealia",
                    },
                    {
                        value: 13,
                        label: "Other",
                    }
                ],
                buyerTypeyoptions: [
                    {
                        value: 1,
                        label: "Dealer",
                    },
                    {
                        value: 2,
                        label: "Distributor",
                    },
                    {
                        value: 3,
                        label: "Wholesaler",
                    },
                    {
                        value: 4,
                        label: "Other",
                    }
                ],
                exhibitionCountryoptions: [
                    {
                        value: 1,
                        label: "Hungary",
                    },
                    {
                        value: 2,
                        label: "Italy",
                    },
                    {
                        value: 3,
                        label: "France",
                    },
                    {
                        value: 4,
                        label: "Russia",
                    },
                    {
                        value: 5,
                        label: "Romania",
                    },
                    {
                        value: 6,
                        label: "Poland",
                    },
                    {
                        value: 7,
                        label: "Other",
                    }
                ],

                //测试数据
            }
        },
        mounted() {
            this.getcountry();
            this.getCategoriesList();
        },
        methods: {
            getquery: function (name, defaultvalue) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return decodeURI(r[2]);
                return defaultvalue;
            },
            getcountry: function () {
                var self = this
                axios.get('/home/plt_PltCountry_list').then(function (res) {
                    self.$set(self, 'contoryoptions', res.data.result)
                    self.$set(self, 'contoryfilteroptions', res.data.result)
                })
            },
            getCategoriesList: function () {
                var self = this;
                axios.get('/home/pdt_PdtProduct_gtProductsIndexCategoriesListAjax'
                ).then(function (res) {
                    if (res.data.ret == 1) {
                        data = res.data.result;
                        self.$set(self, 'checkboxdata', data)
                    }
                })
            },
            showerrmesssage: function (msg) {
                this.centerDialogVisible = true;
                this.$set(this, 'errmessage', msg);
                var self = this;
                setTimeout(function () {
                    self.$set(self, 'centerDialogVisible', false)
                }, 2000)
            },
            regTest: function (name, value) {
                let regEmail = /.+@[a-z0-9\.]+\.(com|cn|net)$/;
                let regTel = /[0-9]{6,}/;
                let regCode = /[0-9]+/;
                if (!value || value.length == 0) {
                    this.showerrmesssage(name + ' can not be empty');
                    return false;
                } else if (name == 'email' && !regEmail.test(value)) {
                    this.showerrmesssage(name + ' format error');
                    return false;
                } else if (name == 'telphone' && !regTel.test(value)) {
                    this.showerrmesssage(name + ' format error');
                    return false;
                } else if (name == 'countryCode' && !regCode.test(value)) {
                    this.showerrmesssage(name + ' format error');
                    return false;
                } else {
                    return true;
                }
            },
            filterCountry: function (val) {
                var reg = new RegExp("^" + val, "i")
                if (!val) {
                    this.$set(this, 'contoryfilteroptions', this.contoryoptions)
                } else {
                    let option = [];
                    let k = 0;
                    for (let i  in this.contoryoptions) {
                        if (reg.test(this.contoryoptions[i].name)) {
                            option[k] = this.contoryoptions[i];
                            k++;
                        }
                    }
                    this.$set(this, 'contoryfilteroptions', option)
                }
            },
            submit: function () {
                for (let i in this.form) {
                    if (!this.regTest(i, this.form[i])) {
                        return false;
                    }
                }
                console.log('form表单数据', this.form)
                let data = {};
                for (let i in this.form) {
                    data[i] = this.form[i]
                    if (Object.prototype.toString.call(data[i]) === '[object Array]') {
                        data[i] = data[i].join(',')
                    }
                }
                data.activityId = this.getquery('activityId', 1);
                console.log('data中的数据', data)
                let self = this;
                axios.post('/home/o2o_O2oRegistration_apply',
                    Qs.stringify({
                        'view.fullName': data.fullName,
                        'view.gender': data.gender,
                        'view.country': data.country,
                        'view.email': data.email,
                        'view.telphone': "00" + data.countryCode + "+" + data.telphone,
                        'view.footwear': data.Collections,
                        'view.marketing': data.marketing,
                        'view.buyertype': data.buyerType,
                        'view.exhibitionCountry': data.exhibitionCountry,
                        'view.remarks': data.remarks,
                        'view.activityId': data.activityId
                    })
                ).then(function (res) {
                    if (res.data.ret == 1) {
                        self.showerrmesssage('Submit success');
                        setTimeout(function () {
                            window.location.reload();
                        }, 3000)
                    } else {
                        self.showerrmesssage(res.data.msg)
                    }
                })
            }
        }
    })
</script>
<script src="components/O2O-top.js"></script>
<script src="components/O2O-bottom.js"></script>

</body>
</html>
