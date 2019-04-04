Vue.component('register-box', {
    template: `
    <div id="register-box-global" v-cloak>
        <el-dialog
                :class="{'no-dialog-close-btn': propsReg.step == 3}"
                custom-class="popup-register-box-global"
                :visible.sync="propsReg.showRegister"
                width="400px">
            <!--注册第一步 - 填写表单-->
            <div class="login-form-panel" v-show="propsReg.step == 1">
                <!-- form 表单 -->
                <el-form ref="registerForm" class="login-form"
                         label-position="top" size="medium"
                         :hide-required-asterisk="true"
                         :model="registerForm" :rules="registerFormRules">
                    <!-- 防止用户名、密码自动填充 -->
                    <input type="text" name="catch-name" class="incase-autocomplete-input">
                    <input type="password" name="catch-psd" class="incase-autocomplete-input">

                    <el-form-item label="Email" prop="email" :error="emailErrorMsg">
                        <el-input v-model.trim="registerForm.email"
                                  @change="changeEmail"
                                  placeholder="Email address or member ID" auto-complete="on"></el-input>
                    </el-form-item>

                    <el-form-item label="Password" prop="psd">
                        <el-input v-model.trim="registerForm.psd"
                                  onkeyup="this.value=this.value.replace(/\s+/g,'')"
                                  placeholder="Enter Password" auto-complete="on" type="password"></el-input>
                    </el-form-item>

                    <el-form-item label="UserName" prop="nickName">
                        <el-input v-model.trim="registerForm.nickName"
                                  placeholder="Enter Nickname"></el-input>
                    </el-form-item>

                    <el-form-item label="Country" prop="country">
                        <el-select v-model.trim="registerForm.country" style="width: 100%;"
                                   placeholder="Please select the country">
                            <el-option
                                    v-for="country in countryList"
                                    :label="country.name"
                                    :value="country.id"
                                    :key="country.id"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>

                <div class="policy-wrap">
                    By clicking “Join Us” Iagree to the
                    <a class="link" target="_blank" href="/home/cnt_CntSglPageCategory_gosglpage?pkey=4">Terms & Conditions</a>
                    and have read and understood the
                    <a class="link" target="_blank" href="/home/cnt_CntSglPageCategory_gosglpage?pkey=3">Privacy Policy</a>
                </div>
                <el-button type="primary" size="medium" type="submit" class="my-primary-btn btn-login"
                           @click="submit">Join us
                </el-button>
            </div>

            <!--注册第二步 - 显示去激活邮箱信息 -->
            <div class="register-step2-wrap" v-show="propsReg.step == 2">
                <div class="tc">
                    <img class="email-img" src="./images/emailpx.png" alt="">
                </div>
                <div class="step2-tips1">
                    A confirmation email has been sent to your mailbox
                    <span class="strong">{{registerForm.email}}</span>
                </div>
                <div class="step2-tips2">
                    Please check your email in box and continue your registration within 15 Minutes.
                </div>

                <div class="tc">
                    <el-button type="primary" size="medium" class="my-primary-btn"
                               @click="checkEmail">Check my email
                    </el-button>

                    <!-- 对email有异议时的提示弹窗 -->
                    <el-popover
                            placement="bottom-start"
                            :visible-arrow="false"
                            popper-class="my-popover-tips-no-email"
                            width="300"
                            trigger="hover"
                            v-model="isReceiveTipsShow">
                        <ul class="tips-list">
                            <li class="tip-item">
                                Please check your spam folder
                            </li>
                            <li class="tip-item">
                                <span>If you have not received theemail,</span>

                                <div class="btn-resend" @click="resend">
                                    {{countDown>0?"Emall has been sent again":"Click here to resend the emall"}}
                                </div>
                                <div v-if="countDown > 0">
                                    You could send again after {{countDown}}s
                                </div>

                            </li>
                            <li class="tip-item">
                                Have not received?
                                <span class="go-back" @click="goBack">Try using another emall address.</span>
                            </li>
                        </ul>
                        <div class="step2-tips3" slot="reference">
                            Having problem receiving email?
                        </div>
                    </el-popover>


                </div>
            </div>

            <!--注册第三步 - 激活邮箱回来后，显示成功信息 -->
            <div class="register-step3-wrap" v-show="propsReg.step == 3">
                <div class="tc">
                    <img class="suc-img" src="./images/chenggong.png" alt="">
                    <div class="step3-tips1">Successful</div>
                    <el-button type="primary" size="medium" class="my-primary-btn"
                               @click="RFQ">RFQ
                    </el-button>
                </div>
            </div>

            <!--注册错误 - 激活邮箱回来后，显示过期信息 -->
            <div class="register-step3-wrap" v-show="propsReg.step == 404">
                <div class="tc">
                    Link has expired or is invalid, Please try again！
                </div>
            </div>
        </el-dialog>
    </div>
    `,
    props: {
      // 判断显示与否、显示第几步
      propsReg: {
        type: Object,
        default: {
          showRegister: false,
          step: 1,
        }
      }
    },
    data() {
        return {
          emailAddrList: {
            'qq.com': 'http://mail.qq.com',
            'gmail.com': 'http://mail.google.com',
            'sina.com': 'http://mail.sina.com.cn',
            '163.com': 'http://mail.163.com',
            '126.com': 'http://mail.126.com',
            'yeah.net': 'http://www.yeah.net/',
            'sohu.com': 'http://mail.sohu.com/',
            'tom.com': 'http://mail.tom.com/',
            'sogou.com': 'http://mail.sogou.com/',
            '139.com': 'http://mail.10086.cn/',
            'hotmail.com': 'http://www.hotmail.com',
            'live.com': 'http://login.live.com/',
            'live.cn': 'http://login.live.cn/',
            'live.com.cn': 'http://login.live.com.cn',
            '189.com': 'http://webmail16.189.cn/webmail/',
            'yahoo.com.cn': 'http://mail.cn.yahoo.com/',
            'yahoo.cn': 'http://mail.cn.yahoo.com/',
            'eyou.com': 'http://www.eyou.com/',
            '21cn.com': 'http://mail.21cn.com/',
            '188.com': 'http://www.188.com/',
            'foxmail.com': 'http://www.foxmail.com',
            'outlook.com': 'http://www.outlook.com'
          },
          // step: 1,
          // showRegister: true, //是否显示注册弹框 - 外部改变
          // jumpUrl: null, //传给后台的返回地址
          countryList: [], //国家列表
          emailErrorMsg: null, //邮箱已注册时显示用 - 非本地验证

          registerForm: {
              email: '',
              psd: '',
              nickName: '',
              country: "",
          },
          registerFormRules: {
              email: [
                  {required: true, message: 'Email can\'t be empty!', trigger: 'blur'},
                  {
                      pattern: util_regular_obj.register.email,
                      message: 'E-mail format is incorrect',
                      trigger: 'blur'
                  }
              ],
              psd: [
                  // {validator: validatePsd, trigger: 'change'},
                  { required: true, message: 'Password can\'t be empty', trigger: 'blur' },
                  {
                      pattern: util_regular_obj.register.psd,
                      message: 'Please enter 6 to 20 characters',
                      trigger: 'blur'
                  }
              ],
              nickName: [
                  { required: true, message: 'Nickname can\'t be empty', trigger: 'change' },
                  {
                      pattern: util_regular_obj.register.nameGlobal,
                      message: 'Please enter the correct name',
                      trigger: 'change'
                  }
              ],
              country: [
                  { required: true, message: 'Country can\'t be empty', trigger: 'change' },
              ],
          },

          isReceiveTipsShow: false, //第二步中，鼠标移入时的提示框
          countDown: 0, //计时器
          countDownTimer: null, //重新发送 计时器
        }
    },
    mounted() {
      this.getCountryList();
    },
    methods: {
      // 改变邮箱大小写
      changeEmail(val){
          this.registerForm.email = val.toLowerCase();
          this.emailErrorMsg = "";
      },

      // 获取国家列表
      getCountryList() {
          axios.get('/home/plt_PltCountry_list',)
              .then((res) => {
              console.log("获取国家列表 suc")
              if (res.data.ret != 1) {
                  this.$message.error(res.data.msg || "Get country list error,please try again later");
                  return
              }
              ;
              this.countryList = res.data.result;
          })
          .catch((error) => {
                  this.$message.error(error || 'Network error,please try again later');
          });
      },

      // 第一步 - 提交注册信息
      submit() {
          // 邮箱已注册时（自定义错误），不可进行点击
          if (this.emailErrorMsg) return false;

          this.$refs.registerForm.validate((valid, errObj) => {
              // 验证全部正确时
              if (valid) {
                  console.log('submit');
                  this.sendEmail();
              }
          });

      },

      // 发送验证码至邮箱
      sendEmail() {
          var backUrl = (window.location.href.indexOf("?")!=-1)?window.location.href.substring(0,window.location.href.indexOf("?")):window.location.href;
          axios.post('/home/usr_UsrMain_sendEm', Qs.stringify({
              email: this.registerForm.email,
              "bean.password": this.registerForm.psd,
              "bean.nickname": this.registerForm.nickName,
              "bean.country": this.registerForm.country,
              backUrl: backUrl,
              type: 4,
          }))
              .then((res) => {
              if (res.data.ret != 1) {
                  if(res.data.ret == 207){
                      this.emailErrorMsg="The mailbox has been registered, please try other one"
                      return
                  }
                  this.$message.error(res.data.msg || "Send email error,please try again later");
                  return
              };

              // 发送成功后，生成对应邮箱跳转地址
              let mailAddr = this.registerForm.email.split('@')[1];  //获取邮箱域名
              this.mailDetailAddr = "";
              for (var key in this.emailAddrList) {
                  if (key == mailAddr) {
                      console.log(this.emailAddrList[key])
                      this.mailDetailAddr = this.emailAddrList[key]
                  }
              }

              this.propsReg.step = 2;
              // 邮箱跳转回来刷新时会无限显示成功页 so这边加个参数进行判断
              localStorage.setItem("landPageHaveSendEmail",true)
          })
          .catch((error) => {
                  this.$message.error(error || 'Network error,please try again later');
          });
      },

      // 第二步 - 去邮箱进行验证
      checkEmail(){
          // 获取到匹配的邮箱地址时，跳转过去，否则提示让他自己去邮箱激活
          if(this.mailDetailAddr){
              window.open(this.mailDetailAddr);
          }else{
              this.$alert("Can't find the email address, please login to your email to activate",{
                  confirmButtonText: 'Ok',
                  customClass: "my-custom-element-alert-class fs-content-18",
                  center: true,
                  callback: action =>{
                      return
                  }
              });
              return
          }
      },
      // 第二步时 重新发送
      resend() {
          if (this.countDown > 0) return;
          this.submit();

          // 倒计时
          clearInterval(this.countDownTimer)
              this.countDown = 60;
              this.countDownTimer = setInterval(() => {
                  this.countDown--;
              if (this.countDown < 0) {
                  clearInterval(this.countDownTimer)
              }
          }, 1000)
      },
      // 返回上一步 - 重填信息
      goBack() {
          this.propsReg.step = 1;
          this.isReceiveTipsShow = false;
          // this.$refs.registerForm.resetFields();
      },

      // 第三步 - 成功后的具体操作
      RFQ(){
        this.propsReg.showRegister = false;
        this.$emit("registersuccess")
      },
    },
    // watch:{
    //   ["propsReg.step"](val){
    //     console.log("step change")
    //     this.step= val;
    //   }
    // }

})
