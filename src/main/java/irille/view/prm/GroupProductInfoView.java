package irille.view.prm;

import irille.view.BaseView;
import lombok.Data;

/**
 * /home/prm_PrmGroupPurchase_getGroupPdt?pkey=658  页面 补充View
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/14
 * Time: 15:23
 */
@Data
public class GroupProductInfoView implements BaseView {


    //购买人数
    private int purCount;
    //    订购会 目标采购量
    private int count;
    //    已购买
    private int bought_count;
}
