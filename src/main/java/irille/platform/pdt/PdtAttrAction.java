package irille.platform.pdt;

import irille.action.ActionBase;
import irille.pub.svr.LoginUserMsg;
import irille.shop.pdt.PdtAttr;
import irille.shop.pdt.PdtAttrDAO;
import irille.shop.pdt.PdtSize;
import lombok.Data;

import java.io.IOException;


/**
 * 产品属性
 * @author lingjian
 * @date 2019/1/22 15:59
 */
@Data
public class PdtAttrAction extends ActionBase<PdtAttr> {

    @Override
    public Class beanClazz() {
        return PdtSize.class;
    }
    public PdtAttr getBean() { return _bean; }
    public void setBean(PdtAttr bean) {
        this._bean = bean;
    }

    private String name; //搜索的产品属性名称
    private String category; //搜索的产品属性类目
    
    /**
     * 查询产品属性列表+搜索
     * @author lingjian
     * @date 2019/1/22 16:04
     */
    public void list() throws Exception {
        write(PdtAttrDAO.listAttr(name,category,getStart(), getLimit()));
    }

    /**
     * 新增产品属性
     * @author lingjian
     * @date 2019/1/22 13:36
     * @throws IOException
     */
    public void ins() throws IOException {
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        getBean().setCreateBy(lu.get_user().getPkey());
        PdtAttrDAO.InsAttr dl = new PdtAttrDAO.InsAttr();
        dl.setB(getBean());
        dl.commit();
        write();
    }

    /**
     * 修改产品属性
     * @author lingjian
     * @date 2019/1/22 13:36
     * @throws IOException
     */
    public void upd() throws IOException {
        LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
        getBean().setCreateBy(lu.get_user().getPkey());
        PdtAttrDAO.UpdAttr upd = new PdtAttrDAO.UpdAttr();
        upd.setB(getBean());
        upd.commit();
        write();
    }

    /**
     * 删除产品属性
     * @author lingjian
     * @date 2019/1/22 13:38
     * @throws IOException
     */
    public void delete() throws IOException {
        PdtAttrDAO.DelAttr remove = new PdtAttrDAO.DelAttr();
        remove.setBKey(getBean().getPkey());
        remove.commit();
        write();
    }
}
