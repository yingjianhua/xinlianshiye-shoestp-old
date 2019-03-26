package irille.Service.Odr.Imp;

import javax.inject.Inject;

import irille.Dao.OdrOrderDao;
import irille.Service.Odr.IOrderService;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.view.prm.GroupProductInfoView;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/14 Time: 10:15 */
public class OrderServiceImp implements IOrderService {

  @Inject private OdrOrderDao odrOrderDao;

  @Override
  public GroupProductInfoView getPrmSaleInfo(Integer sourcePdtId, Integer prmPdtId) {
    GroupProductInfoView groupProductInfoView = new GroupProductInfoView();
    PrmGroupPurchaseLine prmGroupPurchaseLine = odrOrderDao.getPrmGroupPurchaseLine(sourcePdtId);
    switch (prmPdtId) {
      case 977:
        {
          groupProductInfoView.setPurCount(12);
          groupProductInfoView.setBought_count(600);
          groupProductInfoView.setCount(500);
        }
        break;
      case 870:
        {
          groupProductInfoView.setPurCount(9);
          groupProductInfoView.setBought_count(870);
          groupProductInfoView.setCount(300);
        }
        break;
      case 1145:
        {
          groupProductInfoView.setPurCount(3);
          groupProductInfoView.setBought_count(50);
          groupProductInfoView.setCount(500);
        }
        break;
      case 883:
        {
          groupProductInfoView.setPurCount(6);
          groupProductInfoView.setBought_count(300);
          groupProductInfoView.setCount(500);
        }
        break;
      case 845:
        {
          groupProductInfoView.setPurCount(4);
          groupProductInfoView.setBought_count(500);
          groupProductInfoView.setCount(600);
        }
        break;
      case 1073:
        {
          groupProductInfoView.setPurCount(4);
          groupProductInfoView.setBought_count(300);
          groupProductInfoView.setCount(500);
        }
        break;
      case 1143:
        {
          groupProductInfoView.setPurCount(10);
          groupProductInfoView.setBought_count(650);
          groupProductInfoView.setCount(800);
        }
        break;
      case 1026:
        {
          groupProductInfoView.setPurCount(3);
          groupProductInfoView.setBought_count(200);
          groupProductInfoView.setCount(500);
        }
        break;
      case 1094:
        {
          groupProductInfoView.setPurCount(8);
          groupProductInfoView.setBought_count(800);
          groupProductInfoView.setCount(600);
        }
        break;
      case 1133:
        {
          groupProductInfoView.setPurCount(4);
          groupProductInfoView.setBought_count(400);
          groupProductInfoView.setCount(600);
        }
        break;
      case 1054:
        {
          groupProductInfoView.setPurCount(9);
          groupProductInfoView.setBought_count(550);
          groupProductInfoView.setCount(800);
        }
        break;
      case 822:
        {
          groupProductInfoView.setPurCount(3);
          groupProductInfoView.setBought_count(550);
          groupProductInfoView.setCount(500);
        }
        break;
      default:
        {
          Integer temp = odrOrderDao.getEasyOrder(sourcePdtId);
          if (temp != null && temp > 0 && prmGroupPurchaseLine.getBoughtCount().intValue() == 0) {
            groupProductInfoView.setBought_count(temp);
          } else {
            groupProductInfoView.setBought_count(prmGroupPurchaseLine.getBoughtCount().intValue());
          }
          groupProductInfoView.setPurCount(odrOrderDao.getEasyOrderCount(sourcePdtId));
          groupProductInfoView.setCount(prmGroupPurchaseLine.getCount().intValue());
        }
    }
    return groupProductInfoView;
  }
}
