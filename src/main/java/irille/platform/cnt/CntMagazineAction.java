package irille.platform.cnt;

import irille.action.ActionBase;
import irille.pub.bean.BeanBase;
import irille.pub.svr.LoginUserMsg;
import irille.shop.cnt.CntMagazine;
import irille.shop.cnt.CntMagazineDAO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
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
}
