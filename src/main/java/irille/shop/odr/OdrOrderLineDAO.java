package irille.shop.odr;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import irille.platform.odr.view.OdrOrderLine.OdrOrderLineView;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.odr.OdrOrderLine.T;
import irille.shop.pdt.PdtSpec;


public class OdrOrderLineDAO {

    /**
     * 列表该订单下的所有明细
     *
     * @param order 订单pkey
     * @author yingjianhua
     */
    public static List<OdrOrderLine> listByOrder(Long order) {
        return Query.SELECT(OdrOrderLine.class).WHERE(T.MAIN, "=?", order).queryList();
    }


    /**
     * 新增订单明细
     */
    public static OdrOrderLine buildOrderLine(PdtSpec spec, Integer qty) {
        OdrOrderLine line = new OdrOrderLine();
        line.setSpec(spec.getPkey());
        line.setQty(qty);
        BigDecimal price = BigDecimal.ZERO;
        if (spec.getPrice() == null) {
            price = spec.gtProduct().getCurPrice();
        } else {
            price = spec.getPrice();
        }
        BigDecimal subtotal = price.multiply(new BigDecimal(qty)).setScale(4, BigDecimal.ROUND_HALF_UP);
        line.setSubtotal(subtotal);
        return line;
    }

    /**
     * ———————————————————分割线(新平台)—————————————————————————
     */
    public static List<OdrOrderLineView> getOrderLines(Integer orderId) {
        SQL sql = new SQL() {
            {
                SELECT(OdrOrderLine.class).FROM(OdrOrderLine.class).WHERE(T.MAIN, "=?", orderId);
            }
        };
        List<OdrOrderLineView> list = Query.sql(sql).queryMaps().stream().map(o -> new OdrOrderLineView() {{
            setProduct(BeanBase.load(PdtSpec.class,(Integer)o.get(T.SPEC.getFld().getCodeSqlField())).getKeyName());
            setQty((Integer)o.get(T.QTY.getFld().getCodeSqlField()));
            setSubTotal((BigDecimal)o.get(T.SUBTOTAL.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return list;
    }
    /**———————————————————分割线(新平台)END—————————————————————————*/
}
