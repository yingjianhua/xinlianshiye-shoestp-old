package irille.Service.Odr;

import com.google.inject.ImplementedBy;

import irille.Service.Odr.Imp.OrderServiceImp;
import irille.view.prm.GroupProductInfoView;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/14 Time: 10:15 */
@ImplementedBy(OrderServiceImp.class)
public interface IOrderService {
  /**
   * @Description: 查看联合采购
   *
   * @date 2018/12/14 15:50
   * @author lijie@shoestp.cn
   */
  GroupProductInfoView getPrmSaleInfo(Integer sourcePdtId, Integer prmPdtId);
}
