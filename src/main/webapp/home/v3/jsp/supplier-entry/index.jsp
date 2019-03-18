<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/home/v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<link rel="stylesheet" href="/home/v3/static/css/supplierEntry/index.css"/>
</head>
<body>
<jsp:include page="/home/v3/nav-nobody.jsp"></jsp:include>
<div id='shopenter'>
    <index-top></index-top>
    <div class="msgcon">
        <div class="enterstep">
            <div class="hen left"></div>
            <div class="hen center"></div>
            <div class="hen right"></div>
            <div class="steps">
                <div class="step" v-for="item,index in steps">
                    <div>
                        <img src="/home/v3/static/images/supplierEntry/stepback.png" alt="" :class="{showstep : step >= index}">
                        <span>{{index + 1}}</span>
                    </div>
                    <p>{{item}}</p>
                </div>
            </div>
        </div>
        <div class="step1" v-if="step == 0">
            <div class="conditioncon">
                <h3>协议确认</h3>
                <div class="condition">
                    <p>致***公司：</p>
                    <p>贵司与我两司于****年**月**日签订了《*****协议》，约定：*********(协议相关内容)
                        我司在协议签订后即按协议规定完全履行了合同义务*****(履行情况)，但是贵司仍未履行自己的相关合同义务。期间，经我司与贵司多次沟通，贵司均一直拖延搪塞，至今仍未履行合同义务。并且，贵司从未主动向我司通报关于履行合同相关义务的计划。贵司的行为已严重违反上述《******协议》约定，构成根本违约，也伤害了合作双方之间的信任。</p>
                    <p>现我司根据协议第****条郑重向贵司通知如下：</p>
                    <p>1、贵我两司于****年**月**日签订的《*****协议》于贵司收到本通知函之日起解除;</p>
                    <p>2、贵司应在收到本函之日起**日内给付所欠我司货款及相应利息损失。
                        若贵司未在上述期限内未能给付所欠我司货款及相应利息损失，我司将通过诉讼途径解决此事，届时贵司不仅需向我司给付上述货款及利息，还将承担逾期付款违约金、案件受理费等费用。</p>
                    <p>特此通知!</p>
                    <p>****公司</p>
                    <p>**年**月**日</p>
                    <p>二、相关法律</p>
                    <p>第四十一条 主张解除合同的一方，一般应当举证证明：</p>
                    <p>(一)当事人双方之间存在有效合同;</p>
                    <p>(二)合同解除条件已经成就的要件事实;</p>
                    <p>
                        根据合同法的相关规定，合同解除权人可以通过行使解除权解除合同，而不是都要经过法院的认定才能解除合同。但是，解除合同所需的条件具备后，合同也不是当然解除的，解除权人还须作出解除合同的意思表示，并将该意思表示通知对方，方能发生解除的效果。因此，在诉讼中主张合同已经解除的一方，就应当对已经发生合同解除效力所要具备的前述要件事实承担举证责任。解除合同的通知以口头形式作出的，只要能够提供证据证明，也是可以的。当然，在司法实践中，当事人往往诉请法院“依法解除合同”，这种主张实际上既有解除权人尚未行使解除权，而以诉讼方式替代解除通知而诉请解除合同的，也有表面上是要求“依法解除合同”而实质上是要求法院确认合同已经解除的情形。因此，当事人的意思到底是要确认合同已经解除，还是要求解除合同，要根据当事人在诉讼中表示出来的意思来综合判断。</p>
                </div>
            </div>
            <div class="checkcon">
                <el-checkbox v-model="checkconditiong">我已阅读并同意接受以上所有协议</el-checkbox>
                <div @click="agree" :class="{allowed : checkconditiong}">同意并继续</div>
            </div>
        </div>
        <div class="step2" v-if="step == 1">
            <!--公司信息 and 运营信息-->
            <div class="companyForm">
                <!--公司信息-->
                <p class="companyForm-title">公司信息</p>
                <el-form label-position="right" label-width="150px" :model="basicInfo" :rules="firstform"
                         ref="basicInfo">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="*公司名称:" prop="name">
                                <el-input v-model="basicInfo.name" :class="{'null' : !basicInfo.name}"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="*公司英文名称:" prop="englishName">
                                <el-input v-model="basicInfo.englishName"
                                          :class="{'null' : !basicInfo.englishName}"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="公司类型">
                                <el-select v-model="basicInfo.companyType" placeholder="请选择">
                                    <el-option label="有限责任公司" value="有限责任公司"></el-option>
                                    <el-option label="股份有限公司" value="股份有限公司"></el-option>
                                    <el-option label="无限责任公司" value="无限责任公司"></el-option>
                                    <el-option label="两合公司" value="两合公司"></el-option>
                                    <el-option label="股份两合公司" value="股份两合公司"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="公司性质">
                                <el-select v-model="basicInfo.companyNature" placeholder="请选择">
                                    <el-option label="国有企业" value="国有企业"></el-option>
                                    <el-option label="集体企业" value="集体企业"></el-option>
                                    <el-option label="私营企业" value="私营企业"></el-option>
                                    <el-option label="个体工商户" value="个体工商户"></el-option>
                                    <el-option label="合伙企业" value="合伙企业"></el-option>
                                    <el-option label="联营企业" value="联营企业"></el-option>
                                    <el-option label="股份合作制企业" value="股份合作制企业"></el-option>
                                    <el-option label="有限责任公司" value="有限责任公司"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="企业成立时间">
                                <el-date-picker type="date" placeholder="选择日期" v-model="basicInfo.companyEstablishTime"
                                                style="width: 100%;" format="yyyy 年 MM 月 dd 日"
                                                value-format="yyyy-MM-dd"></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="*公司详细地址:" prop="companyAddr">
                                <el-input v-model="basicInfo.companyAddr"
                                          :class="{'null' : !basicInfo.companyAddr}"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="公司电话:" prop="telephone">
                                <el-input v-model="basicInfo.telephone"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="邮编:" prop="postcode">
                                <el-input v-model="basicInfo.postcode"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="传真:" prop="fax">
                                <el-input v-model="basicInfo.fax"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="公司官网地址:">
                                <el-input v-model="basicInfo.website"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12" class="targetmarket">
                            <el-form-item label="*目标市场:" prop="targetedMarket">
                                <div class="countrycon">
                    <span class="countrybotton" @click="showselectcon">
                      你已选择{{basicInfo.targetedMarket.length}}个国家
                    </span>
                                    <div class="select" :class="iscountryshow? '' : 'selecthidden'" style="z-index:99">
                                        <div class="showselect" v-show="basicInfo.targetedMarket != 0">
                        <span v-for="item,index in selectcountry">
                          {{item.name}}
                          <i class="el-icon-error" style="cursor: pointer;opacity: 0.4;"
                             @click="deletecountry(index)"></i>
                        </span>
                                        </div>
                                        <el-input placeholder="请输入内容" prefix-icon="el-icon-search"
                                                  v-model="countrysearch" v-on:input="filterCountry"></el-input>
                                        <div class="countrychoose">
                                            <el-checkbox-group v-model="basicInfo.targetedMarket"
                                                               @change="selectshow">
                                                <el-checkbox v-for="item in exhibitionCountry"
                                                             :label="item.id"
                                                             :key="item.id">
                                                    {{item.name}}
                                                </el-checkbox>
                                            </el-checkbox-group>
                                        </div>
                                    </div>
                                </div>
    <div style="position: fixed;top:0;left:0;width: 100%;height: 100%;background-color: transparent;z-index:98" v-show="iscountryshow" @click="iscountryshow = !iscountryshow"></div>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="年产量:">
                                <el-input v-model="basicInfo.annualProduction"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="*生产模式:" prop="prodPattern">
                                <el-checkbox-group v-model="basicInfo.prodPattern"
                                                   :class="{'null' : basicInfo.prodPattern.length == 0}">
                                    <el-checkbox label="OEM">OEM</el-checkbox>
                                    <el-checkbox label="ODM">ODM</el-checkbox>
                                </el-checkbox-group>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="*统一社会信用代码:" prop="creditCode">
                                <el-input v-model="basicInfo.creditCode" v-on:input="toUpper"
                                          :class="{'null' : !basicInfo.creditCode}"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="注册资本:" prop="registeredCapital">
                                <el-input v-model="basicInfo.registeredCapital"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="法定代表人:" prop="entity">
                                <el-input v-model="basicInfo.entity"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">

                            <el-form-item label="营业执照的有效期:" class="LicenseTime">
                                <el-date-picker type="date" placeholder="开始日期"
                                                v-model="basicInfo.businessLicenseBeginTime"
                                                style="width: 30%;" format="yyyy-MM-dd"
                                                value-format="yyyy-MM-dd"
                                                prefix-icon="none"></el-date-picker>
                                -
                                <el-date-picker type="date" placeholder="结束日期"
                                                :disabled="basicInfo.businessLicenseIsSecular == 1"
                                                v-model="basicInfo.businessLicenseEndTime"
                                                style="width: 30%;" format="yyyy-MM-dd"
                                                value-format="yyyy-MM-dd"
                                                prefix-icon="none"></el-date-picker>
                                <el-checkbox v-model="basicInfo.businessLicenseIsSecular" true-label="1"
                                             false-label="0">长期
                                </el-checkbox>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="纳税人类型:">
                                <el-select v-model="basicInfo.taxpayerType" placeholder="请选择">
                                    <el-option label="自然人" value="自然人"></el-option>
                                    <el-option label="个体工商户" value="个体工商户"></el-option>
                                    <el-option label="法人" value="法人"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <!-- </el-form> -->
                    <!--运营信息-->
                    <p class="companyForm-title">运营信息</p>
                    <!-- <el-form label-position="right" label-width="150px" :model="basicInfo" :rules="firstform"> -->
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="联系人姓名">
                                <el-input v-model="basicInfo.contacts"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="联系人部门" prop="department">
                                <el-input v-model="basicInfo.department"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="联系人职称" prop="jobTitle">
                                <el-input v-model="basicInfo.jobTitle"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="联系人手机">
                                <el-input v-model="basicInfo.phone"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="联系人邮箱" prop="contactEmail">
                                <el-input v-model="basicInfo.contactEmail"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div class="bottons">
                    <div class="botton botton1" @click="goback">上一步</div>
                    <div class="botton botton2" @click="formSubmit">下一步(资质上传)</div>
                </div>
            </div>
        </div>
        <div class="step3" v-if="step == 2">
            <h3>公司信息</h3>
            <el-form :model="basicInfo" :rules="secondform" ref="secondbasicInfo">
                <div class="tips">
                    <p><img src="/home/v3/static/images/supplierEntry/steptip.png" alt="">所有资质必须清晰可辨，如是复印件请加盖贵司自己的红章。</p>
                    <p><img src="/home/v3/static/images/supplierEntry/steptip.png" alt="">以下所需上传电子版资质仅支持JPG、JPEG、GIF、PNG格式的图片，大小不超过2M。且必须<span>加盖企业红色公章。</span>
                    </p>
                </div>
                <el-form-item prop="certPhoto">
                    <div class="imgmsgs">
                        <div class="companyimg">
                            <p><span>*</span>企业营业执照副本复印件(需加盖红章)<span @click="showimg(0)">查看范本</span></p>
                            <el-upload
                                    class="upload-demo"
                                    action="/home/usr_UsrSupplier_upload"
                                    :file-list="comimg"
                                    :on-success="comsuccess"
                                    :before-upload="beforeAvatarUpload">
                                <div class="result">
                                    <div class="resultname" v-if="basicInfo.certPhotoName">{{basicInfo.certPhotoName}}
                                    </div>
                                    <div class="botton">点击上传</div>
                                </div>
                            </el-upload>
                        </div>
                    </div>
                </el-form-item>
                <el-form-item prop="idCard">
                    <div class="imgmsgs">
                        <div class="companyimg">
                            <p><span></span>法人代表身份证正反面复印件<span
                                    @click="showimg(1)">查看范本</span><br><span>身份证号码</span><input type="text"
                                                                                                v-model="basicInfo.idCard"
                                                                                                placeholder="请输入"></p>
                            <el-upload
                                    class="upload-demo"
                                    action="/home/usr_UsrSupplier_upload"
                                    :file-list="legalimg"
                                    :on-success="legalsuccess"
                                    :before-upload="beforeAvatarUpload">
                                <div class="result">
                                    <div class="resultname" v-if="basicInfo.idCardFrontPhotoName">
                                        {{basicInfo.idCardFrontPhotoName}}
                                    </div>
                                    <div class="botton">点击上传</div>
                                </div>
                            </el-upload>
                        </div>
                    </div>
                </el-form-item>
                <el-form-item prop="operateIdCard">
                    <div class="imgmsgs">
                        <div class="companyimg">
                            <p><span></span>运营人代表身份证正反面复印件<span
                                    @click="showimg(1)">查看范本</span><br><span>身份证号码</span><input type="text"
                                                                                                v-model="basicInfo.operateIdCard"
                                                                                                placeholder="请输入"></p>
                            <el-upload
                                    class="upload-demo"
                                    action="/home/usr_UsrSupplier_upload"
                                    :file-list="operateimg"
                                    :on-success="operatesuccess"
                                    :before-upload="beforeAvatarUpload">
                                <div class="result">
                                    <div class="resultname" v-if="basicInfo.contactsIdCardFrontPhotoName">
                                        {{basicInfo.contactsIdCardFrontPhotoName}}
                                    </div>
                                    <div class="botton">点击上传</div>
                                </div>
                            </el-upload>
                        </div>
                    </div>
                </el-form-item>
            </el-form>
            <div class="bottons">
                <div class="botton botton1" @click="goback">上一步</div>
                <div class="botton botton2" @click="submitimg">Submit</div>
            </div>
        </div>
        <div class="step4" v-if="step == 3">
            <h3 v-if="!state">入驻申请已提交,请等待管理员审核</h3>
            <h3 v-if="state">审核不通过 原因:信息有误 <span style="color: #10389c;;cursor: pointer;" @click="step = 1">修改</span>
            </h3>
        </div>
        <el-dialog
                title="范本"
                :visible.sync="img1show"
                width="80%"
                style="text-align: center;">
            <img style="max-height: 100%;max-width: 100%;" :src="showimgurl" alt="">
        </el-dialog>
    </div>
    <index-bottom></index-bottom>
</div>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>

    var validateName = function (rule, value, callback) {
        if (!value) {
            return callback(new Error("请填写名称"));
        } else {
            callback();
        }
    };
    var validateAddr = function (rule, value, callback) {
        console.log(value)
        if (!value) {
            return callback(new Error("请填写详细地址"));
        } else {
            callback();
        }
    };
    var validateTel = function (rule, value, callback) {
        let reg = /^[0-9]+$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写数字"));
        } else {
            callback();
        }
    };
    var validatePost = function (rule, value, callback) {
        let reg = /^[0-9]{6}$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写6位数字"));
        } else {
            callback();
        }
    };
    var validateFax = function (rule, value, callback) {
        let reg = /^[0-9]{12,}$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写至少12位数字"));
        } else {
            callback();
        }
    };
    var validateRegist = function (rule, value, callback) {
        let reg = /^[A-Za-z0-9\u4e00-\u9fa5]+$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写中文,英文或数字"));
        } else {
            callback();
        }
    };
    var validateEntity = function (rule, value, callback) {
        let reg = /^[\u4e00-\u9fa5]{2,5}$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写2至5个中文"));
        } else {
            callback();
        }
    };
    var validateJob = function (rule, value, callback) {
        let reg = /^[A-Za-z\u4e00-\u9fa5]+$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写中文或英文"));
        } else {
            callback();
        }
    };
    var validateEmail = function (rule, value, callback) {
        let reg = /^.+@[A-Za-z0-9\.]+\.(cn|com|net)$/
        if (!value) {
            callback();
        } else if (!reg.test(value)) {
            return callback(new Error("请填写正确的邮箱格式"));
        } else {
            callback();
        }
    };
    var validateMarket = function (rule, value, callback) {
        if (value.length == 0) {
            return callback(new Error("请选择目标市场"));
        } else {
            callback();
        }
    };
    var validatePattern = function (rule, value, callback) {
        if (value.length == 0) {
            return callback(new Error("请选择生产模式"));
        } else {
            callback();
        }
    };
    var validateCredit = function (rule, value, callback) {
        let reg = /[0-9A-Za-z]{18}/;
        if (!value) {
            return callback(new Error("请填写统一社会信用代码"));
        } else if (!reg.test(value)) {
            return callback(new Error("请填写正确的统一社会信用代码"));
        } else {
            callback();
        }
    };
    var certPhoto = function (rule, value, callback) {
        if (!value) {
            return callback(new Error("请上传营业执照"));
        } else {
            callback();
        }
    };
    var idCard = function (rule, value, callback) {
        let regsfz = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (!value) {
            callback();
        } else if (!regsfz.test(value)) {
            return callback(new Error("如若上传,请填写正确的身份证"));
        } else {
            callback();
        }
    };

    new Vue({
        el: '#shopenter',
        data: {

            state: null,//是否是返回修改
            step: 0,//第(step+1)步

            checkconditiong: false,
            img1show: false,

            showimgurl: '',
            steps: ['开店须知', '填写公司信息', '资质上传', '等待审核'],
            showimgurls: ['/home/v3/static/images/supplierEntry/yyzz.png', '/home/v3/static/images/supplierEntry/sfz.png'],
            //国家集合
            country: [],
            //过滤国家集合
            exhibitionCountry: [],
            exhibitionCountrylength: 0,
            //国家搜索内容
            countrysearch: '',
            //选择国家内容
            selectcountry: [],
            iscountryshow: false,

            //公司,法人,运营者图片
            comimg: [],
            legalimg: [],
            operateimg: [],
            //法人,运营者身份证
            legalsfz: '',
            operatesfz: '',

            // 公司信息
            basicInfo: {
                name: '',
                englishName: '',
                companyType: '',
                companyNature: '',
                companyEstablishTime: '',
                companyAddr: '',
                telephone: '',
                postcode: '',
                fax: '',
                website: '',
                targetedMarket: [],
                annualProduction: '',
                prodPattern: [],
                creditCode: '',
                registeredCapital: '',
                entity: '',
                businessLicenseBeginTime: '',
                businessLicenseEndTime: '',
                businessLicenseIsSecular: '0',
                taxpayerType: '',
                contacts: '',
                department: '',
                jobTitle: '',
                phone: '',
                contactEmail: '',
                certPhoto: '',
                certPhotoName: '',
                idCardFrontPhoto: '',
                idCardFrontPhotoName: '',
                idCard: '',
                contactsIdCardFrontPhoto: '',
                contactsIdCardFrontPhotoName: '',
                operateIdCard: '',
                applicationTime: '',
                purchasePkey: 9999,
                lang: 'zh-TW',
                userid: null
            },
            //用户信息
            usermsg: {},
            pkey: null,
            lang: null,
            storeStatus: null,

            //验证
            firstform: {
                name: [
                    {validator: validateName, trigger: 'blur'}
                ],
                englishName: [
                    {validator: validateName, trigger: 'blur'}
                ],
                companyAddr: [
                    {validator: validateAddr, trigger: 'blur'}
                ],
                telephone: [
                    {validator: validateTel, trigger: 'blur'}
                ],
                postcode: [
                    {validator: validatePost, trigger: 'blur'}
                ],
                fax: [
                    {validator: validateFax, trigger: 'blur'}
                ],
                registeredCapital: [
                    {validator: validateRegist, trigger: 'blur'}
                ],
                entity: [
                    {validator: validateEntity, trigger: 'blur'}
                ],
                department: [
                    {validator: validateRegist, trigger: 'blur'}
                ],
                jobTitle: [
                    {validator: validateJob, trigger: 'blur'}
                ],
                contactEmail: [
                    {validator: validateEmail, trigger: 'blur'}
                ],
                targetedMarket: [
                    {validator: validateMarket, trigger: 'change'}
                ],
                prodPattern: [
                    {validator: validatePattern, trigger: 'change'}
                ],
                creditCode: [
                    {validator: validateCredit, trigger: 'blur'}
                ]
            },
            secondform: {
                certPhoto: [
                    {validator: certPhoto, trigger: 'blur'}
                ],
                idCard: [
                    {validator: idCard, trigger: 'blur'}
                ],
                operateIdCard: [
                    {validator: idCard, trigger: 'blur'}
                ]
            }
        },
        mounted() {
            let date = new Date();
            this.basicInfo.applicationTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
            this.getUsermsg();
        },
        methods: {
            //获取信息,判断是否修改
            getRetype: function (val) {
                let self = this;
                axios.get('/home/usr_UsrSupplier_loadOnlineSup', {
                    params: {
                        purchasePkey: self.basicInfo.userid,
                    }
                }).then(function (res) {
                    let data = res.data
                    if (data.status == null) {
                        return false;
                    } else if (data.status == 0) {
                        self.step = 3;
                    } else if (data.status == 1) {
                        console.log('成功,跳转去其他页面')
                    } else if (data.status == 2) {
                        self.state = true;
                        self.step = 3;
                    }

                    //需要处理的数据,先处理
                    self.pkey = data.pkey
                    self.storeStatus = data['storeStatus']
                    data['prodPattern'] = data['prodPattern'] ? data['prodPattern'].split(' / ') : []
                    data['userid'] = parseInt(data['userid'].split('##')[0])
                    data['certPhotoName'] = data['annex'].cert_photo_name
                    data['idCardFrontPhotoName'] = data['annex'].id_card_front_photo_name
                    data['contactsIdCardFrontPhotoName'] = data['annex'].contacts_id_card_front_photo_name
                    data['businessLicenseIsSecular'] = data['businessLicenseIsSecular'] + ''
                    if (data['businessLicenseIsSecular'] == '1') {
                        self.businessLicenseEndTime = '';
                    }
                    data['targetedMarket'] = data['targetedMarket'] ? data['targetedMarket'].split(',') : []
                    for (let i in data['targetedMarket']) {
                        data['targetedMarket'][i] = parseInt(data['targetedMarket'][i])
                    }
                    for (let i in self.basicInfo) {
                        for (let j in data) {
                            if (j == i) {
                                self.basicInfo[j] = data[i]
                            }
                        }
                    }
                    self.selectshow();
                })
            },
            //获取个人信息
            getUsermsg: function () {
                let self = this;
                axios.get('/home/plt_PltConfig_getSysConfig')
                    .then(function (res) {
                        if (res.data.ret == 1) {
                            self.$set(self, 'usermsg', res.data.result.user)
                            self.basicInfo.userid = res.data.result.user.id
                            self.basicInfo.lang = res.data.result.current_language
                            self.getcountry();
                            self.getRetype();
                        }
                    })
            },
            //获取国家
            getcountry: function () {
                var self = this
                axios.get('/home/plt_PltCountry_list', {
                    params: {
                        lang: self.basicInfo.lang
                    }
                }).then(function (res) {
                    self.$set(self, 'country', res.data.result)
                    self.$set(self, 'exhibitionCountry', res.data.result)
                    self.exhibitionCountrylength = Math.ceil(self.exhibitionCountry.length / 3)
                })
            },
            //承认条约
            agree: function () {
                if (this.checkconditiong) {
                    this.step += 1;
                }
            },
            //范本图片展示
            showimg: function (val) {
                this.img1show = true
                this.showimgurl = this.showimgurls[val]
            },
            //上传图片回传
            comsuccess: function (response, file, fileList) {
                this.basicInfo.certPhotoName = response.result.originalName;
                this.basicInfo.certPhoto = response.result.url;
            },
            legalsuccess: function (response, file, fileList) {
                this.basicInfo.idCardFrontPhotoName = response.result.originalName;
                this.basicInfo.idCardFrontPhoto = response.result.url;
            },
            operatesuccess: function (response, file, fileList) {
                this.basicInfo.contactsIdCardFrontPhotoName = response.result.originalName;
                this.basicInfo.contactsIdCardFrontPhoto = response.result.url;
            },
            //营业执照,身份证的验证,和最后提交
            submitimg: function () {
                let self = this;
                this.$refs['secondbasicInfo'].validate((vaild) => {
                    if (!vaild) {
                        self.$alert('请检查表单', {
                            closeOnClickModal: true,
                            showConfirmButton: false
                        }).catch(() => {
                        });
                        return false
                    } else {
                        self.$confirm('是否提交?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'info ',
                            center: true
                        }).then(() => {
                            let data = {};
                            for (let i in self.basicInfo) {
                                if (i == 'certPhotoName' || i == 'idCardFrontPhotoName' || i == 'contactsIdCardFrontPhotoName' || i == 'purchasePkey' || i == 'lang') {
                                    data[i] = self.basicInfo[i]
                                } else {
                                    data['bean.' + i] = self.basicInfo[i]
                                }
                            }
                            data['bean.targetedMarket'] = data['bean.targetedMarket'].join(',')
                            data['bean.prodPattern'] = data['bean.prodPattern'].join('/')
                            if (data['bean.businessLicenseIsSecular'] == '1') {
                                data['bean.businessLicenseEndTime'] = '';
                            }
                            for (let i in data) {
                                if (data[i] == '') {
                                    data[i] = null
                                }
                            }
                            console.log(data)
                            if (self.state) {
                                self.retypeSubmit(data)
                            } else {
                                self.firstSubmit(data)
                            }
                        }).catch(() => {
                            self.showmsg('Submit cancel')
                        })
                    }
                })
            },
            //首次提交
            firstSubmit: function (data) {
                let self = this
                axios.post('/home/usr_UsrSupplier_insInfo',
                    Qs.stringify(data)
                ).then(function (res) {
                    if (res.data.ret == 1) {
                        self.showmsg('Submit Success')
                        setTimeout(function () {
                            self.step += 1;
                        }, 1000)
                    } else {
                        self.showmsg('Submit Failed')
                    }
                }).catch(function (error) {
                    console.log(error)
                })
            },
            //修改提交
            retypeSubmit: function (data) {
                let self = this
                data['bean.pkey'] = self.pkey
                data['bean.storeStatus'] = self.storeStatus
                axios.post('/home/usr_UsrSupplier_updInfo',
                    Qs.stringify(data)
                ).then(function (res) {
                    if (res.data.ret == 1) {
                        self.state = false;
                        self.showmsg('Submit Success')
                        setTimeout(function () {
                            self.step += 1;
                        }, 1000)
                    } else {
                        self.showmsg('Submit Failed')
                    }
                }).catch(function (error) {
                    console.log(error)
                })
            },
            //表单信息验证
            formSubmit: function () {
                let self = this;
                this.$refs['basicInfo'].validate((vaild) => {
                    if (!vaild) {
                        self.$alert('请检查表单', {
                            closeOnClickModal: true,
                            showConfirmButton: false
                        }).catch(() => {
                        });
                        return false;
                    } else {
                        self.step += 1;
                    }
                })
            },
            //上一步
            goback: function (val) {
                this.step -= 1;
            },
            //搜索国家
            filterCountry: function (val) {
                var reg = new RegExp("^" + val, "i")
                if (!val) {
                    this.$set(this, 'exhibitionCountry', this.country)
                } else {
                    let option = [];
                    let k = 0;
                    for (let i  in this.exhibitionCountry) {
                        if (reg.test(this.exhibitionCountry[i].name)) {
                            option[k] = this.exhibitionCountry[i];
                            k++;
                        }
                    }
                    this.$set(this, 'exhibitionCountry', option)
                    console.log(this.exhibitionCountry)
                }
            },
            //消息提示
            showmsg: function (val) {
                this.$alert(val, {
                    closeOnClickModal: true,
                    showConfirmButton: false
                }).catch(() => {
                });
            },
            //判断图片
            beforeAvatarUpload: function (res) {
                const isJPG = res.type === 'image/jpeg';
                const isPNG = res.type === 'image/png';
                const isGIF = res.type === 'image/gif';
                const isLt2M = res.size / 1024 / 1024 < 2;

                if (!isJPG && !isPNG && !isGIF) {
                    this.$message.error('图片只能是 JPG ,GIF ,PNG格式!');
                }
                if (!isLt2M) {
                    this.$message.error('图片大小不能超过 2MB!');
                }
                return (isJPG || isPNG || isGIF) && isLt2M;
            },
            //转换大写
            toUpper: function () {
                this.basicInfo.creditCode = this.basicInfo.creditCode.toUpperCase();
            },
            //选择国家显示
            selectshow: function () {
                this.selectcountry = []
                for (let i in this.basicInfo.targetedMarket) {
                    for (let j in this.country) {
                        if (this.basicInfo.targetedMarket[i] == this.country[j].id) {
                            this.selectcountry.push(this.country[j])
                        }
                    }
                }
            },
            //删除所选国家
            deletecountry: function (index) {
                this.basicInfo.targetedMarket.splice(index, 1)
                this.selectcountry.splice(index, 1)
            },
            //内部国家选择框
            showselectcon: function (val) {
                this.iscountryshow = !this.iscountryshow;
            }
        }
    })
</script>
</body>
</html>
