Vue.component('login-box', {
    template: `
    <div id="login-box-global" v-cloak>
    <el-dialog
            custom-class="popup-login-box-global"
            :visible.sync="propsLogin.showLogin"
            :show-close="false"
            width="400px">
        <div class="login-form-panel">
            <div class="join-free">
                <a @click="toRegister" href="javascript: void(0);">Join Free</a>
            </div>

            <!-- form 表单 -->
            <el-form ref="loginForm" class="login-form"
                     label-position="top" size="medium"
                     :hide-required-asterisk="true"
                     :model="loginForm" :rules="loginFormRules">
                <!-- 防止用户名、密码自动填充 -->
                <input type="text" name="catch-name" class="incase-autocomplete-input">
                <input type="password" name="catch-psd" class="incase-autocomplete-input">

                <el-form-item label="Email" prop="email">
                    <el-input v-model="loginForm.email"
                              @keyup.enter.native="submit"
                              placeholder="Email address or member ID" auto-complete="on"></el-input>
                </el-form-item>

                <el-form-item label="Password" prop="psd">
                    <el-input v-model="loginForm.psd"
                              onkeyup="this.value=this.value.replace(/\s+/g,'')"
                              @keyup.enter.native="submit"
                              placeholder="Password" auto-complete="on" type="password"></el-input>
                </el-form-item>
            </el-form>

            <div class="forget-psd-wrap">
                <a href="https://www.shoestp.com/home/usr_UsrMain_forget" class="forget-psd">
                    Forgot your password?
                </a>
            </div>
            <el-button type="primary" size="medium" type="submit" class="btn-login my-primary-btn"
                       @click="submit">Sign In
            </el-button>
        </div>
    </el-dialog>
</div>
    `,
    props: {
      // 判断显示与否、显示第几步
      propsLogin: {
        type: Object,
        default: {
          showLogin: false,
          step: 1,
        }
      }
    },
    data() {
        return {
            loginForm: {
                email: '',
                psd: '',
            },
            loginFormRules: {
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
            },
        }
    },
    mounted() {

    },
    methods: {
            // 点击去注册
            toRegister(){
                this.$emit("toregister")
            },

            // 自己的登录接口
            login() {
                  var postData = {
                      loginName: this.loginForm.email,
                      pwd: this.loginForm.psd,
                  }
                axios.post('/home/usr_UsrMain_login', Qs.stringify(postData))
                    .then((res) => {
                    if (res.data.ret != 1) {
                    this.$message.error(res.data.msg || "Login error, please try again later");
                    return
                };
                this.$message({
                    message: 'Login Successfully',
                    type: 'success'
                });
                this.propsLogin.showLogin = false;
                this.$emit("loginsuccess")
            })
            .catch((error) => {
                    this.$message.error(error || 'Network error,please try again later');
            });
            },

            // 提交账号信息
            submit() {
                this.$refs.loginForm.validate((valid, errObj) => {
                    // 验证全部正确时
                    if (valid) {
                        this.login();
                    }
            });

            },

    },
    // watch:{
    //   ["propsLogin.step"](val){
    //     console.log("step change")
    //     this.step= val;
    //   }
    // }

})
