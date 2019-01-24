package irille.platform.pdt;

import irille.action.ActionBase;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import lombok.Data;


/**
 * @author lingjian
 * @date 2019/1/24 14:29
 * @Version 1.0
 */
@Data
public class PdtProductAction extends ActionBase<PdtProduct> {

    @Override
    public Class beanClazz() {
        return PdtProduct.class;
    }
    public PdtProduct getBean() { return _bean; }
    public void setBean(PdtProduct bean) {
        this._bean = bean;
    }

    //搜索字段
    private String fldvalue;
    //搜索字段内容
    private String condition;

    /**
     * 查询采购商列表
     * @author lingjian
     * @date 2019/1/24 14:30
     * @throws Exception
     */
    public void list() throws Exception {
        write(PdtProductDAO.listproduct(fldvalue,condition,getStart(), getLimit()));
    }
}
