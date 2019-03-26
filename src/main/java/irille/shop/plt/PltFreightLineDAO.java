package irille.shop.plt;

import java.util.List;

import irille.pub.Log;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.shop.plt.PltFreightLine.T;

public class PltFreightLineDAO {
  private static final Log LOG = new Log(PltFreightLineDAO.class);

  /**
   * 根据运费模板查询配送区域列表
   *
   * @author yingjianhua
   */
  public static List<PltFreightLine> listByFreight(Integer freight) {
    return Query.SELECT(PltFreightLine.class).WHERE(T.MAIN, "=?", freight).queryList();
  }

  public static void delsection(Integer pkey) {
    PltFreightLine pl = BeanBase.load(PltFreightLine.class, pkey);
    pl.del();
    Integer num =
        BeanBase.list(
                PltFreightLine.class,
                T.MAIN.getFld().getCodeSqlField() + " =" + pl.getMain(),
                false)
            .size();
    if (num == 0) {
      throw LOG.err(Plt.ErrMsgs.last, "区间");
    }
  }
}
