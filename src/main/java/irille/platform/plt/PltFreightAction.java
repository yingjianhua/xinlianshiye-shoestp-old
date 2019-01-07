package irille.platform.plt;

import irille.Dao.PltFreightDao;
import irille.action.MgtAction;
import irille.platform.plt.View.DeliveryAreaView;
import irille.platform.plt.View.FreightManagementView;
import irille.shop.plt.PltFreight;
import irille.view.Page;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;

public class PltFreightAction extends MgtAction<PltFreight> {
    @Override
    public Class beanClazz() {
        return null;
    }

    @Inject
    private PltFreightDao pltFreightDAO;
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private FreightManagementView freightManagementView;
    @Getter
    @Setter
    private DeliveryAreaView deliveryAreaView;

    /**
     * @Description: (平台)获取运费管理列表
     * *@date 2019/1/3 16:33
     * *@anthor zjl
     */
    public Page getShippingList() {
        return pltFreightDAO.getShippingList(getStart(), getLimit());
    }

    /**
     * @Description: (平台)获取配送区域列表
     * *@date 2019/1/3 16:49
     * *@anthor zjl
     */
    public Page getDeliveryAreaList() {
        return pltFreightDAO.getDeliveryAreaList(id, name, getStart(), getLimit());
    }

    /**
     * @Description: (平台)新增运费管理
     * *@date 2019/1/4 9:56
     * *@anthor zjl
     */
    public void insShipping() {
        pltFreightDAO.insShipping(freightManagementView);
    }

    /**
     * @Description: (平台)新增配送区域
     * *@date 2019/1/4 10:16
     * *@anthor zjl
     */
    public void insDeliveryArea() {
        pltFreightDAO.insDeliveryArea(deliveryAreaView);
    }

    /**
     * @Description: (平台)获取单个运费管理
     * *@date 2019/1/4 11:03
     * *@anthor zjl
     */
    public FreightManagementView getShipping() {
        return pltFreightDAO.getShipping(id);
    }

    /**
     * @Description: (平台)修改单个运费管理
     * *@date 2019/1/4 11:26
     * *@anthor zjl
     */
    public void updShipping() {
        pltFreightDAO.updShipping(freightManagementView);
    }

    /**
     * @Description: (平台)获取单个配送区域
     * *@date 2019/1/4 11:52
     * *@anthor zjl
     */
    public DeliveryAreaView getDeliveryArea() {
        return pltFreightDAO.getDeliveryArea(id);
    }

    /**
     * @Description: (平台)更新单个配送区域
     * *@date 2019/1/4 13:54
     * *@anthor zjl
     */
    public void updDeliveryArea() {
        pltFreightDAO.updDeliveryArea(deliveryAreaView);
    }
}
