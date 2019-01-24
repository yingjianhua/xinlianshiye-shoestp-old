package irille.platform.odr;

import irille.action.ActionBase;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderDAO;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class OdrOrderAction  extends ActionBase<OdrOrder> {
    @Override
    public Class beanClazz() {
        return OdrOrder.class;
    }

    public OdrOrder getBean() {
        return _bean;
    }

    public void setBean(OdrOrder bean) {
        this._bean = bean;
    }

    @Getter
    @Setter
    private String orderNum;//订单号
    @Getter
    @Setter
    private Integer state;//订单状态
    /**
     * @Description: 获取订单列表
     * *@date 2019/1/22 11:18
     * *@anthor zjl
     */
    public void getOrders() throws IOException {
        write(OdrOrderDAO.getOrders(getStart(),getLimit(),orderNum,state));
    }
    /**
     * @Description: 获取订单有关的所有状态
     * *@date 2019/1/22 14:59
     * *@anthor zjl
     */
    public void getStatusList() throws IOException {
        write(OdrOrderDAO.getStatusList());
    }
}
