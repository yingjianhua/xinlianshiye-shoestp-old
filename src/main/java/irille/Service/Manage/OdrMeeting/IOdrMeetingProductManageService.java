package irille.Service.Manage.OdrMeeting;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingProductManageServiceImp;
import irille.pub.idu.IduPage;
import irille.view.Manage.OdrMeeting.OdrMeetingProductView;
import java.util.List;

/**
 * 订购会商品接口 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:58
 */

@ImplementedBy(OdrMeetingProductManageServiceImp.class)
public interface IOdrMeetingProductManageService {


  /**
   * @Description: 获取订购会商品列表
   * @date 2018/11/14 14:16
   * @author lijie@shoestp.cn
   */
  List<OdrMeetingProductView> getOdrMeetingProductList(IduPage iduPage, int odrMeetingId);


  /**
   * @Description: 添加商品到订购会
   * @date 2018/11/15 14:18
   * @author lijie@shoestp.cn
   */
  void insProductToOdrMeeting(OdrMeetingProductView OdrMeetingProductView,int odrMeetingId, int supplierId);
}
