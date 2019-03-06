package irille.platform.usr;

import irille.action.ActionBase;
import irille.pub.svr.LoginUserMsg;
import irille.shop.usr.UsrSupplierCategory;
import irille.shop.usr.UsrSupplierCategoryDAO;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class UsrSupplierCategoryAction extends ActionBase<UsrSupplierCategory> {
    @Override
    public Class beanClazz() {
        return UsrSupplierCategory.class;
    }

    public UsrSupplierCategory getBean() {
        return _bean;
    }

    public void setBean(UsrSupplierCategory bean) {
        this._bean = bean;
    }

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer supplierCategory;
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String ids;

    /**
     * @Description: 获取供应商分类
     * *@date 2019/1/21 16:45
     * *@anthor zjl
     */
    public void getUsrSupplierCategoryList() throws IOException {
        write(UsrSupplierCategoryDAO.getUsrSupplierCategoryList(getStart(), getLimit(), name, supplierCategory));
    }

    /**
     * @Description: 删除多个订阅以及单个
     * *@date 2019/1/21 14:14
     * *@anthor zjl
     */
    public void deletes() throws IOException {
        UsrSupplierCategoryDAO.deletes dl = new UsrSupplierCategoryDAO.deletes();
        if (id != null) {
            dl.setBKey(id);
            dl.commit();
        } else {
            String[] list = ids.split(",");
            for (String s : list) {
                dl.setBKey(Integer.valueOf(s));
                dl.commit();
            }
        }
        write();
    }
    /**
     * @Description: 修改供应商分类
     * *@date 2019/1/21 20:37
     * *@anthor zjl
     */
    public void upd() throws IOException {
        UsrSupplierCategoryDAO.UpdCategory  upd= new UsrSupplierCategoryDAO.UpdCategory();
        upd.setB(getBean());
        upd.commit();
        write();
    }
    /**
     * @Description: 修改供应商分类
     * *@date 2019/1/21 20:37
     * *@anthor zjl
     */
    public void insCategory() throws IOException {
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        getBean().setCreateBy(lu.get_user().getPkey());
        UsrSupplierCategoryDAO.InsCategory ins = new UsrSupplierCategoryDAO.InsCategory();
        ins.setB(getBean());
        ins.commit();
        write();
    }
}
