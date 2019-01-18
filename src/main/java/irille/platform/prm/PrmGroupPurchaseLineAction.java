package irille.platform.prm;

import irille.action.ActionBase;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.prm.PrmGroupPurchaseLineDAO;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class PrmGroupPurchaseLineAction extends ActionBase<PrmGroupPurchaseLine> {
    @Override
    public Class beanClazz() {
        return PrmGroupPurchaseLine.class;
    }

    public PrmGroupPurchaseLine getBean() {
        return _bean;
    }

    public void setBean(PrmGroupPurchaseLine bean) {
        this._bean = bean;
    }

    @Getter
    @Setter
    private Integer id;

    /**
     * @Description: 根据联合采购主键获取联合采购商品列表
     * *@date 2019/1/18 10:39
     * *@anthor zjl
     */
    public void getProducts() throws IOException {
        write(PrmGroupPurchaseLineDAO.getProducts(getStart(), getLimit(), id));
    }

    /**
     * @Description: 获取联合采购商品状态列表
     * *@date 2019/1/18 11:31
     * *@anthor zjl
     */
    public void getStatus() throws IOException {
        write(PrmGroupPurchaseLineDAO.getStatus());
    }
}
