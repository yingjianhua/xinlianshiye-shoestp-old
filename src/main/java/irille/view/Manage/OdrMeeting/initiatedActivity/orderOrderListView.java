package irille.view.Manage.OdrMeeting.initiatedActivity;

import irille.view.BaseView;
import lombok.Data;

/**
 * 订购会订单列表
 * @author zjl
 * @date 2018/11/29 16:29
 */
@Data
public class orderOrderListView implements BaseView {
    private String orderNum;//订单号
    private String email;//邮箱
    private String prodPrice;//产品总价
    private String priceTotal;//订单总额
    private String country;//国家
    private String paymentTime;//付款时间
//    private String commoditySource;//商品来源
    private String whetherSettlement;//是否结算
    private String classification;//是否(自有/合作商) 分类
    private String orderStatus;//订单状态
}
