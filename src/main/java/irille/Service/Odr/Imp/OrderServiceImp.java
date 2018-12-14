package irille.Service.Odr.Imp;

import irille.Dao.OdrOrderDao;
import irille.Service.Odr.IOrderService;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.view.prm.GroupProductInfoView;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/14
 * Time: 10:15
 */
public class OrderServiceImp implements IOrderService {

    @Inject
    private OdrOrderDao odrOrderDao;

    @Override
    public GroupProductInfoView getPrmSaleInfo(Integer sourcePdtId, Integer prmPdtId) {
        GroupProductInfoView groupProductInfoView = new GroupProductInfoView();
        PrmGroupPurchaseLine prmGroupPurchaseLine = odrOrderDao.getPrmGroupPurchaseLine(sourcePdtId);
        switch (prmPdtId) {
            case 977: {
                groupProductInfoView.setPurCount(12);
                groupProductInfoView.setBought_count(600);
            }
            break;
            case 870: {
                groupProductInfoView.setPurCount(9);
                groupProductInfoView.setBought_count(870);
            }
            break;
            case 1145: {
                groupProductInfoView.setPurCount(3);
                groupProductInfoView.setBought_count(50);
            }
            break;
            default: {
                Integer temp = odrOrderDao.getEasyOrder(sourcePdtId);
                if (temp != null && temp > 0 && prmGroupPurchaseLine.getBoughtCount().intValue() == 0) {
                    groupProductInfoView.setBought_count(temp);
                } else {
                    groupProductInfoView.setBought_count(prmGroupPurchaseLine.getBoughtCount().intValue());
                }
                groupProductInfoView.setPurCount(odrOrderDao.getEasyOrderCount(sourcePdtId));
            }
        }
        groupProductInfoView.setCount(prmGroupPurchaseLine.getCount().intValue());
        return groupProductInfoView;
    }
}
