package irille.platform.usr;

import irille.action.ActionBase;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrPurchaseDAO;
import lombok.Data;


/**
 * @author lingjian
 * @date 2019/1/24 14:29
 * @Version 1.0
 */
@Data
public class UsrPurchaseAction extends ActionBase<UsrPurchase> {

    @Override
    public Class beanClazz() {
        return UsrPurchase.class;
    }
    public UsrPurchase getBean() { return _bean; }
    public void setBean(UsrPurchase bean) {
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
        write(UsrPurchaseDAO.listpurselect(fldvalue,condition,getStart(), getLimit()));
    }
}
