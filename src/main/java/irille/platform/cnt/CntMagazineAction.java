package irille.platform.cnt;

import com.fasterxml.jackson.databind.ObjectMapper;
import irille.action.ActionBase;
import irille.pub.svr.LoginUserMsg;
import irille.pub.util.upload.ImageUpload;
import irille.shop.cnt.CntMagazine;
import irille.shop.cnt.CntMagazineDAO;
import irille.view.plt.ImageView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

@Data
public class CntMagazineAction extends ActionBase<CntMagazine> {
    @Override
    public Class beanClazz() {
        return CntMagazine.class;
    }

    public CntMagazine getBean() {
        return _bean;
    }

    public void setBean(CntMagazine bean) {
        this._bean = bean;
    }

    @Getter
    @Setter
    private String name;//查询杂志名称
    @Getter
    @Setter
    private Integer id;//杂志pkey

    @Getter
    @Setter
    private String fileFileName = "";
    @Getter
    @Setter
    private File file;
    public void list() throws IOException {
        write(CntMagazineDAO.listCM(name, getStart(), getLimit()));
    }

    private String pkeys;

    //删除多个杂志
    public void deletes() throws IOException {
        String[] list = pkeys.split(",");
        for (String st : list) {
            CntMagazineDAO.del dl = new CntMagazineDAO.del();
            dl.setBKey(Integer.valueOf(st));
            dl.commit();
        }
        write();
    }

    public void inscm() throws IOException {
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        getBean().setCreatedBy(lu.get_user().getPkey());
        CntMagazineDAO.Ins dl = new CntMagazineDAO.Ins();
        dl.setB(getBean());
        dl.commit();
        write();
    }

    /**
     * @Description: 删除单条杂志
     * *@date 2019/1/15 14:54
     * *@anthor zjl
     */
    public void delete() throws IOException {
        CntMagazineDAO.remove remove = new CntMagazineDAO.remove();
        remove.setBKey(id);
        remove.commit();
        write();
    }
    /**
     * @Description: 获取单条杂志
     * *@date 2019/1/15 15:16
     * *@anthor zjl
     */
    public void getMagazine() throws IOException {
        write(CntMagazineDAO.getMagazine(id));
    }

    public void updMagazine() throws IOException {
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        getBean().setCreatedBy(lu.get_user().getPkey());
        CntMagazineDAO.UpdCntMagazine upd = new CntMagazineDAO.UpdCntMagazine();
        upd.setB(getBean());
        upd.commit();
        write();
    }
    /**
     * 上传图片
     *
     * @Date 219/1/14 15:13
     * @author zjl
     */
    public void upload() throws Exception {
        if (getLoginSys() == null) {
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
