package irille.shop.plt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import irille.core.sys.Sys;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduUpdLines;
import irille.sellerAction.SellerAction;
import irille.shop.plt.Plt.ErrMsgs;
import irille.shop.plt.PltFreightSeller.T;
import irille.view.Page;
import irille.view.plt.FreightSellerView;
import irille.view.plt.FreightSellerlistView;

public class PltFreightSellerDAO {
  private static final Log LOG = new Log(PltFreightSellerDAO.class);

  public enum Msgs implements IMsg { // 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
    priceErr("【{0}】不能低于或等于【{1}】"),
    priceMinErr("【{0}】不能低于0"),
    ;
    private String _msg;

    private Msgs(String msg) {
      _msg = msg;
    }

    public String getMsg() {
      return _msg;
    }
  } // @formatter:on

  // 插入快递公司
  public static class Ins extends IduInsLines<Ins, PltFreightSeller, PltFreightSellerLine> {

    @Override
    public void before() {
      super.before();
      getB().stEnabled(true);
      getB().setSupplier(SellerAction.getSupplier().getPkey());
    }

    @Override
    public void after() {

      insLine(getB(), getLines(), PltFreightSellerLine.T.MAIN.getFld());
      super.after();
    }

    @Override
    public void valid() {
      super.valid();

      List<PltFreightSellerLine> s = getLines();
      if (getB().getWeightMin().compareTo(getB().getWeightMax()) >= 0)
        throw LOG.err(
            Msgs.priceErr,
            PltFreightSeller.T.WEIGHT_MAX.getFld().getName(),
            PltFreightSeller.T.WEIGHT_MIN.getFld().getName());
      if (getB().getWeightMin() < 0)
        throw LOG.err(Msgs.priceMinErr, PltFreightSeller.T.WEIGHT_MIN.getFld().getName());
      if (getB().getWeightMax() < 0)
        throw LOG.err(Msgs.priceMinErr, PltFreightSeller.T.WEIGHT_MAX.getFld().getName());
      if (PltFreightSeller.chkUniqueSupplier_company(
              true, SellerAction.getSupplier().getPkey(), getB().getCompany())
          != null) throw LOG.err(ErrMsgs.uniqueErr, getB().getCompany());
      if (getB().getSort() != null) {
        if (getB().getSort() < 0)
          throw LOG.err(ErrMsgs.lowPriceErr, PltFreightSeller.T.SORT.getFld().getName(), 0);
      }
      if (getLines() != null)
        for (int i = 0; i < s.size(); i++) {
          if (s.get(i).getWeightPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw LOG.err(
                ErrMsgs.lowPriceErr, PltFreightSellerLine.T.WEIGHT_PRICE.getFld().getName(), 0);
          }
          if (s.get(i).getAggravatePrice().compareTo(BigDecimal.ZERO) < 0) {
            throw LOG.err(
                ErrMsgs.lowPriceErr, PltFreightSellerLine.T.AGGRAVATE_PRICE.getFld().getName(), 0);
          }
        }
    }
  }

  public static class Upd extends IduUpdLines<Upd, PltFreightSeller, PltFreightSellerLine> {
    @Override
    public void before() {
      PltFreightSeller dbBean = loadThisBeanAndLock();
      PropertyUtils.copyPropertiesWithout(
          dbBean, getB(), PltFreightSeller.T.PKEY, PltFreightSeller.T.SUPPLIER);
      SQL sql =
          new SQL() {
            {
              SELECT(PltFreightSeller.T.PKEY);
              FROM(PltFreightSeller.class);
              WHERE(PltFreightSeller.T.ENABLED, " =?", 1);
              WHERE(PltFreightSeller.T.SUPPLIER, " =?", dbBean.getSupplier());
            }
          };
      List<PltFreightSeller> num = Query.sql(sql).queryList(PltFreightSeller.class);
      System.out.println(num.size() + "总共");
      if (num.size() == 1) {
        if (num.get(0).getPkey().equals(dbBean.getPkey())) {
          System.out.println(dbBean.getEnabled() + "enabled");
          if (dbBean.getEnabled() == 0) {
            throw LOG.err("UPDATE ERROR", "至少要有一个运费公司启用");
          }
        }
      }
      if (num.size() < 1) {
        throw LOG.err("UPDATE ERROR", "至少要有一个运费公司启用");
      }
      setB(dbBean);
    }

    @Override
    public void valid() {
      super.valid();

      List<PltFreightSellerLine> s = getLines();
      if (getB().getWeightMin().compareTo(getB().getWeightMax()) >= 0)
        throw LOG.err(
            Msgs.priceErr,
            PltFreightSeller.T.WEIGHT_MAX.getFld().getName(),
            PltFreightSeller.T.WEIGHT_MIN.getFld().getName());
      if (getB().getWeightMin() < 0)
        throw LOG.err(Msgs.priceMinErr, PltFreightSeller.T.WEIGHT_MIN.getFld().getName());
      if (getB().getWeightMax() < 0)
        throw LOG.err(Msgs.priceMinErr, PltFreightSeller.T.WEIGHT_MAX.getFld().getName());
      if (getB().getSort() != null) {
        if (getB().getSort() < 0)
          throw LOG.err(ErrMsgs.lowPriceErr, PltFreightSeller.T.SORT.getFld().getName(), 0);
      }
      if (getLines() != null)
        for (int i = 0; i < s.size(); i++) {
          if (s.get(i).getWeightPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw LOG.err(
                ErrMsgs.lowPriceErr, PltFreightSellerLine.T.WEIGHT_PRICE.getFld().getName(), 0);
          }
          if (s.get(i).getAggravatePrice().compareTo(BigDecimal.ZERO) < 0) {
            throw LOG.err(
                ErrMsgs.lowPriceErr, PltFreightSellerLine.T.AGGRAVATE_PRICE.getFld().getName(), 0);
          }
        }
    }
  }

  public static class Del extends IduDel<Del, PltFreightSeller> {
    @Override
    public void before() {
      if (getLines(PltFreightSellerLine.T.MAIN, getB().getPkey()) != null) {
        List<PltFreightSellerLine> sline = getLines(PltFreightSellerLine.T.MAIN, getB().getPkey());
        for (PltFreightSellerLine psl : sline) {
          Idu.delLine(
              BeanBase.list(
                  PltCountryFreight.class,
                  PltCountryFreight.T.REGION.getFld().getCodeSqlField() + "=? ",
                  false,
                  psl.getPkey()));
        }
        delLine(getLines(PltFreightSellerLine.T.MAIN, getB().getPkey()));
      }
    }
  }

  /**
   * 供应商过审后,根据平台的运费模板,自动初始化供应商的运费模板
   *
   * @param force 是否强制初始化,若否,在供应商已经有运费设置的数据的情况下不做处理
   * @author yingjianhua
   */
  public static void init(Integer supplier, boolean force) {
    if (!force
        && Query.SELECT(PltFreightSeller.class).WHERE(T.SUPPLIER, "=?", supplier).queryCount() > 0)
      return;
    List<PltFreight> list = Query.SELECT(PltFreight.class).queryList();
    for (PltFreight line : list) {
      PltFreightSeller bean = new PltFreightSeller();
      if (line.gtEnabled()) {
        bean.setSupplier(supplier);
        bean.setCompany(line.getCompany());
        bean.setLogo(line.getLogo());
        bean.setEnabled(line.getEnabled());
        bean.setExpressUrl(line.getExpressUrl());
        bean.setSort(line.getSort());
        bean.setUseInterface(line.getUseInterface());
        bean.setType(line.getType());
        bean.setWeightMin(line.getWeightMin());
        bean.setWeightMax(line.getWeightMax());
        bean.setRowVersion((short) 0);
        bean.ins();
        PltFreightSellerLineDAO.init(line, bean);
      }
    }
  }

  /**
   * 查询当前供应商下的所有运费模板
   *
   * @author zw
   */
  public static List<FreightSellerView> listfreight(Integer pkey) {
    SQL sql =
        new SQL() {
          {
            SELECT(T.PKEY, T.COMPANY);
            FROM(PltFreightSeller.class);
            WHERE(T.SUPPLIER.getFld().getCodeSqlField() + " = ? ", String.valueOf(pkey));
          }
        };
    List<PltFreightSeller> frightSelect = Query.sql(sql).queryList(PltFreightSeller.class);
    List<FreightSellerView> views = new ArrayList<FreightSellerView>();
    for (PltFreightSeller seller : frightSelect) {
      FreightSellerView view = new FreightSellerView();
      view.setId(seller.getPkey());
      view.setCompany(seller.getCompany());
      views.add(view);
    }
    return views;
  }

  /**
   * 搜索当前供应商模糊查询快递公司
   *
   * @author zw
   */
  public static Page<FreightSellerlistView> listfreightlike(
      Integer pkey, String selectcompany, Integer getStart, Integer getLimit) {
    BeanQuery<PltFreightSeller> q =
        Query.SELECT(PltFreightSeller.class)
            .WHERE(T.SUPPLIER, "=?", pkey)
            .WHERE(selectcompany != null, T.COMPANY, "like ?", "%" + selectcompany + "%")
            .limit(getStart, getLimit);
    List<FreightSellerlistView> items =
        q.queryList().stream()
            .map(
                bean -> {
                  return new FreightSellerlistView() {
                    {
                      setId(bean.getPkey());
                      setCompany(bean.getCompany());
                      setLOGO(bean.getLogo());
                      setEnabled(bean.gtEnabled());
                      setType(bean.gtType().getLine().getName());
                    }
                  };
                })
            .collect(Collectors.toList());

    return new Page(items, getStart, getLimit, q.queryCount());
  }

  /** <<<<<<<<<<<<<<<<<<< ---------------- 获取供应商所有启用的运费模板 ----------------->>>>>>>>>>>>>>>>>>>>>> */
  public static List<FreightSellerlistView> getFreightBySupplier(Integer supplier) {
    return Query.SELECT(T.PKEY, T.COMPANY).FROM(PltFreightSeller.class)
        .WHERE(T.SUPPLIER, "=?", supplier)
        .WHERE(T.ENABLED, "=?", Sys.OEnabled.TRUE.getLine().getKey()).queryList().stream()
        .map(
            bean ->
                new FreightSellerlistView() {
                  {
                    setId(bean.getPkey());
                    setCompany(bean.getCompany());
                  }
                })
        .collect(Collectors.toList());
  }
}
