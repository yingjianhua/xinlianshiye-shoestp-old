package irille.Service.Manage.OdrMeeting;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingProductManageServiceImp;
import irille.pub.idu.IduPage;
import irille.view.Manage.OdrMeeting.OdrMeetingProductView;
import irille.view.Manage.OdrMeeting.initiatedActivity.AllProductsView;
import irille.view.Page;

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
    void insProductToOdrMeeting(OdrMeetingProductView OdrMeetingProductView, int odrMeetingId, int supplierId);

    /**
     * @Description: 获取订购会商品列表 + 搜索
     * @date 2018/11/19 18:56
     * @author zjl
     */
    Page getOrderGoodsList(Integer start, Integer limit, Integer id, Integer status, String inputContent,Integer supplierId,Integer supplier);

    /**
     * @Description: 修改订购会产品上下架状态
     * @date 2018/11/20 11:31
     * @author zjl
     */
    void updateStatus(Integer id);

    /**
     * @Description: 删除订购会商品
     * @date 2018/11/20 19:43
     * @author zjl
     */
    void removeProduct(Integer id);

    /**
     * @Description: 获取所有商品
     * @date 2018/11/21 11:29
     * @author zjl
     */
    List<AllProductsView> getProducts(Integer id );

    /**
     * @Description: 获取订购会已添加商品
     * @date 2018/11/21 14:17
     * @author zjl
     */
    List getAddedProducts(Integer id,Integer supplierId);

    /**
     * @Description: 添加订购会商品
     * @date 2018/11/22 9:55
     * @author zjl
     */
    void addProducts(Integer id,Integer pkey,List<AllProductsView> list);
}
