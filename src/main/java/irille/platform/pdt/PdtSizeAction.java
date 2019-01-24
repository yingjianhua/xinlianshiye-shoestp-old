package irille.platform.pdt;

import irille.action.ActionBase;
import irille.pub.svr.LoginUserMsg;
import irille.shop.pdt.PdtSize;
import irille.shop.pdt.PdtSizeDAO;
import lombok.Data;

import java.io.IOException;


/**
 * @Author: lingjian
 * @Date: Created in 2019/1/21 13:59
 * @Version 1.0
 */
@Data
public class PdtSizeAction extends ActionBase<PdtSize> {

    @Override
    public Class beanClazz() {
        return PdtSize.class;
    }
    public PdtSize getBean() { return _bean; }
    public void setBean(PdtSize bean) {
        this._bean = bean;
    }

    private String name; //搜索的产品尺寸名称
    private String productCategory; //搜索的产品尺寸类目
    private Integer id;

    /**
     * 查询产品尺寸列表+搜索
     * @author lingjian
     * @date 2019/1/22 13:36
     * @throws Exception
     */
    public void list() throws Exception {
        write(PdtSizeDAO.listSize(name,productCategory,getStart(), getLimit()));
    }

    /**
     * 新增产品尺寸
     * @author lingjian
     * @date 2019/1/22 13:36
     * @throws IOException
     */
    public void ins() throws IOException {
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        getBean().setCreateBy(lu.get_user().getPkey());
        PdtSizeDAO.InsSize dl = new PdtSizeDAO.InsSize();
        dl.setB(getBean());
        dl.commit();
        write();
    }

    /**
     * 修改产品尺寸
     * @author lingjian
     * @date 2019/1/22 13:36
     * @throws IOException
     */
    public void upd() throws IOException {
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        getBean().setCreateBy(lu.get_user().getPkey());
        PdtSizeDAO.UpdSize upd = new PdtSizeDAO.UpdSize();
        upd.setB(getBean());
        upd.commit();
        write();
    }

    /**
     * 删除产品尺寸
     * @author lingjian
     * @date 2019/1/22 13:38
     * @throws IOException
     */
    public void delete() throws IOException {
        PdtSizeDAO.DelSize remove = new PdtSizeDAO.DelSize();
        remove.setBKey(getBean().getPkey());
        remove.commit();
        write();
    }
}
