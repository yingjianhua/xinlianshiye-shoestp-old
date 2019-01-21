package irille.platform.usr;

import com.fasterxml.jackson.databind.ObjectMapper;
import irille.action.MgtAction;
import irille.pub.svr.LoginUserMsg;
import irille.pub.util.upload.ImageUpload;
import irille.shop.usr.UsrMemberLevel;
import irille.shop.usr.UsrMemberLevelDAO;
import irille.view.plt.ImageView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class UsrMemberLevelAction extends MgtAction<UsrMemberLevel> {
    @Override
    public Class beanClazz() {
        return UsrMemberLevel.class;
    }
    public UsrMemberLevel getBean() {
        return _bean;
    }

    public void setBean(UsrMemberLevel bean) {
        this._bean = bean;
    }
    public void listnumber() throws IOException {
        write(UsrMemberLevelDAO.listnumer());
    }
    public void insnumber() throws IOException {
        UsrMemberLevelDAO.Ins is=new UsrMemberLevelDAO.Ins();
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        Integer pkeyid= lu.get_user().getPkey();
        getBean().setCreatedBy(pkeyid);
        getBean().setUpdatedBy(pkeyid);
        is.setB(getBean());
        is.commit();
        write();
    }
    public void updnumber() throws IOException {
        UsrMemberLevelDAO.Upd is=new UsrMemberLevelDAO.Upd();
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        getBean().setUpdatedBy(lu.get_user().getPkey());
        is.setB(getBean());
        is.commit();
        write();
    }
    public void delnumber() throws IOException {
        UsrMemberLevelDAO.delnumber is=new UsrMemberLevelDAO.delnumber();
       is.setBKey(getBean().getPkey());
        is.commit();
        write();
    }
    @Setter
    @Getter
    private String fileFileName = "";
    @Setter
    @Getter
    private File file;

    public void upload() throws Exception {
        if(getLoginSys() == null) {
            JSONObject json = new JSONObject();
            json.put("success", false);
            json.put("msg", "登录超时,请重新登录");
            writerOrExport(json);
        } else {
            ImageView view = ImageUpload.upload(beanClazz(), fileFileName, file);
            JSONObject json = new JSONObject();
            json.put("ret", 1);
            json.put("result", new JSONObject(new ObjectMapper().writeValueAsString(view)));
            json.put("success", true);
            writerOrExport(json);
        }
    }


}
