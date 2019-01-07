package irille.platform.basesys;

import irille.action.sys.SysUserAction;
import irille.core.lg.Lg;
import irille.core.lg.LgLogin;
import irille.core.lg.LgLoginDAO;
import irille.core.sys.*;
import irille.platform.view.logininfoview;
import irille.pub.Exp;
import irille.pub.Log;
import irille.pub.Str;
import irille.pub.bean.Bean;
import irille.pub.svr.Env;
import irille.pub.svr.LoginUserMsg;
import irille.pub.verify.RandomImageServlet;
import irille.view.ResultView;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Setter
@Getter
public class basesysUserAction extends SysUserAction {

    private static final Log LOG = new Log(basesysUserAction.class);
    private String _mmOld; // 原密码
    private String _mmNew; // 新密码
    private String _mmCheck; // 新密码确认
    private String _checkCode; // 验证码
    public basesysUserAction() {
        super();
    }

    @Override
    public Class beanClazz() {
        return super.beanClazz();
    }

    @Override
    public SysUser getBean() {
        return super.getBean();
    }

    @Override
    public void setBean(SysUser bean) {
        super.setBean(bean);
    }
    // 取Session中的验证码
    private String verifyCode() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession ssn = request.getSession(false);
        if (ssn == null)
            return null;
        return (String) ssn.getAttribute(RandomImageServlet.RANDOM_LOGIN_KEY);
    }
    public void baselogin() throws  Exception {
        try {
            String verifyCode = verifyCode();
            if (Str.isEmpty(verifyCode) || Str.isEmpty(getCheckCode()) || verifyCode.equals(getCheckCode()) == false)
                throw LOG.err("errcode", "验证码错误");
            SysUser user = SysUserDAO.loginCheck(getBean().getLoginName(), getMmCheck());
            user.stLoginState(Sys.OLoginState.PC); // TODO 最近登录的记录
            user.upd();
            String agent = ServletActionContext.getRequest().getHeader("User-Agent").toLowerCase();
            LgLogin lg = LgLoginDAO.initLg(user, Lg.OClient.WINDOWS.getLine().getKey(), ServletActionContext.getRequest()
                    .getRemoteAddr(), getOs(agent), getBrowser(agent));
            LoginUserMsg umsg = new LoginUserMsg(lg);
            this.session.put(LOGIN, umsg);
            //Env.INST.initTran(umsg, "basesys_basesysUser_baselogin");
        } catch (Exp e) {
            throw e;
//            writeErr(e.getLastMessage());

//            setSarg1(e.getLastMessage());
        }
        write();
    }

    @Override
    public String loginTest() {
        return super.loginTest();
    }

    @Override
    public void loginout() {
        super.loginout();
    }


    public void getLoginMsg() throws Exception {
        SysUser user = getLoginSys();
        logininfoview lo=new logininfoview();
//        HttpServletResponse response = ServletActionContext.getResponse();
        lo.setId(user.getPkey());
        lo.setName(user.getName());
//        System.out.println(user);
//        SysCell cell = SysCellDAO.getCellByUser(user);
//        JSONObject json = new JSONObject();
//        json.put("userPkey", user.getPkey());
//        json.put("userName", user.getName());
//        json.put("deptPkey", user.getDept());
//        json.put("deptName", user.gtDept().getName());
//        json.put("cellPkey", cell.getPkey());
//        json.put("cellName", cell.getName());
//        json.put("orgPkey", user.getOrg());
//        json.put("orgName", user.gtOrg().getName());
//        json.put("success", true);
//        response.getWriter().print(json.toString());
        write(lo);
    }

    @Override
    public SysUser insRun() throws Exception {
        return super.insRun();
    }

    @Override
    public void updPwd() {
        super.updPwd();
    }

    @Override
    public void updPwdMy() {
        super.updPwdMy();
    }

    @Override
    public String orderBy() {
        return super.orderBy();
    }

    @Override
    public JSONObject crtJsonExt(JSONObject json, Bean bean, String pref) throws Exception {
        return super.crtJsonExt(json, bean, pref);
    }
}
