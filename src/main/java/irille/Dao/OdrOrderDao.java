package irille.Dao;

import irille.pub.bean.query.BeanQuery;
import irille.shop.easy.EasyOdr;
import irille.shop.easy.EasyOdrline;
import irille.shop.pdt.PdtSpec;
import irille.shop.prm.PrmGroupPurchaseLine;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/14
 * Time: 15:50
 */
public class OdrOrderDao {

    /**
     * @Description: 获取销售信息
     * @date 2018/12/14 16:12
     * @author lijie@shoestp.cn
     */
    public PrmGroupPurchaseLine getPrmGroupPurchaseLine(Integer integer) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                PrmGroupPurchaseLine.T.COUNT,
                PrmGroupPurchaseLine.T.BOUGHT_COUNT
        ).FROM(
                PrmGroupPurchaseLine.class
        ).WHERE(
                PrmGroupPurchaseLine.T.PRODUCT, "=?", integer
        );
        return (PrmGroupPurchaseLine) query.query(PrmGroupPurchaseLine.class);
    }


    /**
     * @Description: 查询以销售的数量(查询订单的方式
     * @date 2018/12/14 16:08
     * @author lijie@shoestp.cn
     */
    public Integer getEasyOrder(Integer integer) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                EasyOdr.T.COUNYPD
        ).FROM(
                EasyOdr.class
        ).LEFT_JOIN(
                EasyOdrline.class, EasyOdrline.T.ORDER_ID, EasyOdr.T.PKEY
        ).LEFT_JOIN(
                PdtSpec.class, PdtSpec.T.PKEY, EasyOdrline.T.SPEC
        ).WHERE(
                PdtSpec.T.PRODUCT, "=?", integer
        );
        EasyOdr easyOdr = (EasyOdr) query.query(EasyOdr.class);
        if (easyOdr == null)
            return 0;
        return easyOdr.getCounypd();
    }

    /**
     * @Description: 查询以销售的订单数量
     * @date 2018/12/14 16:08
     * @author lijie@shoestp.cn
     */
    public Integer getEasyOrderCount(Integer integer) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                EasyOdr.T.COUNYPD
        ).FROM(
                EasyOdr.class
        ).LEFT_JOIN(
                EasyOdrline.class, EasyOdrline.T.ORDER_ID, EasyOdr.T.PKEY
        ).LEFT_JOIN(
                PdtSpec.class, PdtSpec.T.PKEY, EasyOdrline.T.SPEC
        ).WHERE(
                PdtSpec.T.PRODUCT, "=?", integer
        );
        return query.queryCount();
    }
}
