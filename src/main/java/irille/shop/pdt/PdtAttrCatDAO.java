package irille.shop.pdt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import irille.core.sys.Sys.OYn;
import irille.core.sys.SysEm;
import irille.platform.pdt.View.PdtAttrCatView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.util.BatchUtils;
import irille.pub.util.GetValue;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidForm;
import irille.shop.pdt.PdtAttrCat.T;
import irille.view.Page;
import lombok.Getter;
import lombok.Setter;

public class PdtAttrCatDAO {
  public static final Log LOG = new Log(PdtAttrCatDAO.class);

  /**
   * 查询属性分类
   *
   * @param start
   * @param limit
   * @param name
   * @return
   */
  public Page getAttrCat(Integer start, Integer limit, String name) {
    SQL sql = new SQL();
    sql.SELECT(PdtAttrCat.T.PKEY, "pkey");
    sql.SELECT(PdtAttrCat.T.CREATE_TIME, "time");
    sql.SELECT(PdtAttrCat.T.NAME);
    sql.SELECT(SysEm.T.NAME, "crateName");
    sql.SELECT(PdtAttrCat.T.STATE, "state");
    sql.FROM(PdtAttrCat.class);
    sql.LEFT_JOIN(SysEm.class, PdtAttrCat.T.CREATE_BY, SysEm.T.PKEY);
    sql.WHERE(PdtAttrCat.T.DELETED, "=?", 0);
    if (name != null) {
      sql.WHERE(PdtAttrCat.T.NAME, " like ?", "%" + name + "%");
    }
    Integer count = Query.sql(sql).queryCount();
    List<PdtAttrCatView> list =
        Query.sql(sql.LIMIT(start, limit).ORDER_BY(PdtAttrCat.T.CREATE_TIME, "DESC")).queryMaps()
            .stream()
            .map(
                o -> {
                  PdtAttrCatView view = new PdtAttrCatView();
                  Integer pkey = GetValue.get(o, "pkey", Integer.class, -1);
                  view.setId(pkey);
                  view.setName(String.valueOf(o.get(PdtAttrCat.T.NAME.getFld().getCodeSqlField())));
                  view.setCreateName(String.valueOf(o.get("crateName")));
                  view.setCreateTime((Date) o.get("time"));
                  view.setState(Integer.valueOf(String.valueOf(o.get("state"))));
                  if (Query.sql(
                              "SELECT pkey FROM pdt_attr WHERE category="
                                  + Integer.valueOf(String.valueOf(o.get("pkey")))
                                  + " AND deleted=0")
                          .queryCount()
                      > 0) {
                    view.setShould(true);
                  } else {
                    view.setShould(false);
                  }

                  List<PdtAttrPro> pros =
                      BeanBase.list(
                          PdtAttrPro.class,
                          PdtAttrPro.T.ATTRCAT.getFld().getCodeSqlField() + "=?",
                          false,
                          pkey);
                  if (null != pros && pros.size() > 0) {
                    view.setProdCats(
                        pros.stream()
                            .map(
                                b -> {
                                  return b.getProcat();
                                })
                            .collect(Collectors.toList()));
                  }
                  return view;
                })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  public static class InsAttrCat extends IduIns<InsAttrCat, PdtAttrCat> {
    @Setter @Getter private List<Integer> pdtCat;

    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.NO.getLine().getKey());
      getB().setCreateTime(Env.getSystemTime());
    }

    @Override
    public void valid() {
      super.valid();
      ValidForm.validEmpty(getB().getName(), PdtAttrCat.T.NAME);
    }

    @Override
    public void after() {
      PdtAttrProDao.Ins ins = new PdtAttrProDao.Ins();
      for (Integer c : pdtCat) {
        PdtAttrPro ap = new PdtAttrPro();
        ap.setAttrcat(getB().getPkey());
        ap.setProcat(c);
        ins.setB(ap);
        ins.commit();
      }
    }
  }

  public static class UpdAttrCat extends IduUpd<UpdAttrCat, PdtAttrCat> {
    @Getter @Setter private List<Integer> cat;
    @Getter @Setter private List<Integer> pps;

    @Override
    public void before() {
      if (null == getB().getName()
          || (null != getB().getName() && "".equals(getB().getName().trim()))) {
        throw new WebMessageException(ReturnCode.failure, "属性分类名称不可为空");
      }
      List<PdtAttrPro> pros =
          BeanBase.list(
              PdtAttrPro.class,
              PdtAttrPro.T.ATTRCAT.getFld().getCodeSqlField() + " =? ",
              false,
              getB().getPkey());

      pps =
          pros.stream()
              .map(
                  p -> {
                    return p.getProcat();
                  })
              .collect(Collectors.toList());
      boolean f = false;
      if (null != getCat()) {
        for (Integer c : getCat()) {
          if (!pps.contains(c)) {
            f = true;
          }
        }
      }

      if ((null != getB().getState() && getB().getState().equals(OYn.YES.getLine().getKey()))
          || f) {
        PdtAttrCatDAO.valid(getB(), getCat());
      }

      if (null == cat && !pps.contains(cat)) {
        throw new WebMessageException(ReturnCode.failure, "请选择产品分类");
      }
    }
    /** @auther liyichao */
    @Override
    public void valid() {}

    /*
     * liyichao
     */
    @Override
    public void run() {
      PdtAttrCat dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), PdtAttrCat.T.NAME, PdtAttrCat.T.STATE);
      dbBean.setDeleted(OYn.NO.getLine().getKey());
      dbBean.setState(OYn.NO.getLine().getKey());
      setB(dbBean);
      List<PdtAttrPro> pros = new ArrayList<>();
      for (Integer c : getCat()) {
        if (null != getPps() && !getPps().contains(c)) {
          PdtAttrPro ap = new PdtAttrPro();
          ap.setAttrcat(getB().getPkey());
          ap.setProcat(c);
          ap.setRowVersion((short) 0);
          pros.add(ap);
        }
      }
      if (pros.size() > 0) {
        BatchUtils.batchIns(PdtAttrPro.class, pros);
      }
      super.run();
    }
  }

  public static class Ins extends IduIns<Ins, PdtAttrCat> {
    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.NO.getLine().getKey());
      getB().setCreateBy(getUser().getPkey());
      getB().setCreateTime(Env.getSystemTime());
    }

    @Override
    public void valid() {
      super.valid();
      ValidForm.validEmpty(getB().getName(), PdtAttrCat.T.NAME);
    }
  }

  public static class Upd extends IduUpd<Upd, PdtAttrCat> {
    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.NO.getLine().getKey());
      getB().setCreateBy(getUser().getPkey());
      getB().setCreateTime(Env.getSystemTime());
    }

    @Override
    public void valid() {
      super.valid();
      ValidForm.validEmpty(getB().getName(), PdtAttrCat.T.NAME);
    }
  }

  public static class Del extends IduUpd<Upd, PdtAttrCat> {
    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.YES.getLine().getKey());
      PdtAttrCat dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
      setB(dbBean);
    }
  }

  public static class InsMap extends IduOther<InsMap, PdtAttrCat> {
    Map<String, Map<String, List<String>>> categoryMap;

    public Map<String, Map<String, List<String>>> getCategoryMap() {
      return categoryMap;
    }

    public void setCategoryMap(Map<String, Map<String, List<String>>> categoryMap) {
      this.categoryMap = categoryMap;
    }

    @Override
    public void run() {
      super.run();
      try {
        for (String categoryName : categoryMap.keySet()) {
          PdtAttrCat category = new PdtAttrCat().init();
          category.setName(categoryName);
          category.setDeleted(OYn.NO.getLine().getKey());
          translateUtil.autoTranslate(category).ins();
          for (String attrName : categoryMap.get(categoryName).keySet()) {
            PdtAttr attr = new PdtAttr().init();
            attr.stCategory(category);
            attr.setName(attrName);
            attr.setDeleted(OYn.NO.getLine().getKey());
            translateUtil.autoTranslate(attr).ins();

            for (String lineName : categoryMap.get(categoryName).get(attrName)) {
              PdtAttrLine line = new PdtAttrLine().init();
              line.setDeleted(OYn.NO.getLine().getKey());
              line.stMain(attr);
              line.setName(lineName);
              translateUtil.autoTranslate(line).ins();
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static class UpdState extends IduUpd<Upd, PdtAttrCat> {
    @Getter @Setter private Byte state;

    @Override
    public void before() {
      if (getState().equals(OYn.YES.getLine().getKey())) {
        PdtAttrCatDAO.valid(getB(), null);
      }
      getB().setState(state);
      PdtAttrCat dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), T.STATE);
      setB(dbBean);
    }
  }

  public boolean getCount(Integer cat) {
    String sql =
        "SELECT  pkey FROM pdt_attr_line WHERE main in(SELECT pkey FROM pdt_attr WHERE category="
            + cat
            + " AND deleted=0)  AND deleted=0";
    List<PdtAttrLine> list = Query.sql(sql).queryList(PdtAttrLine.class);
    System.out.println(list.size());
    if (list.size() > 0) {
      for (PdtAttrLine pdtAttrLine : list) {
        String proSql =
            "SELECT norm_attr FROM pdt_product where FIND_IN_SET("
                + pdtAttrLine.getPkey()
                + ",norm_attr) AND is_verify=1 AND state=1";
        Integer count = Query.sql(proSql).queryCount();
        if (count > 0) {
          return false;
        } else {
          return true;
        }
      }
    }
    return true;
  }

  public static void valid(PdtAttrCat cat, List<Integer> cats) {
    List<PdtAttr> attrs =
        BeanBase.list(
            PdtAttr.class,
            PdtAttr.T.CATEGORY.getFld().getCodeSqlField()
                + " =? AND "
                + PdtAttr.T.DELETED.getFld().getCodeSqlField()
                + " =? ",
            false,
            cat.getPkey(),
            OYn.NO.getLine().getKey());
    if (null != attrs && attrs.size() > 0) {
      String as =
          attrs.stream()
              .map(
                  b -> {
                    return String.valueOf(b.getPkey());
                  })
              .collect(Collectors.joining(","));
      List<PdtAttrLine> lines =
          BeanBase.list(
              PdtAttrLine.class,
              PdtAttrLine.T.MAIN.getFld().getCodeSqlField() + " in (" + as + ") ",
              false);
      List<String> ls =
          lines.stream()
              .map(
                  b -> {
                    return String.valueOf(b.getPkey());
                  })
              .collect(Collectors.toList());
      SQL sql = new SQL();
      sql.SELECT(PdtProduct.T.NORM_ATTR).FROM(PdtProduct.class);
      List<Map<String, Object>> normAttrs = Query.sql(sql).queryMaps();
      boolean flag = false;
      for (Map<String, Object> m : normAttrs) {
        String s = GetValue.get(m, PdtProduct.T.NORM_ATTR, String.class, null);
        if (null != s) {
          String[] ss = s.split(",");
          for (String str : ss) {
            if (ls.contains(str)) {
              flag = true;
              break;
            }
          }
        }
        if (flag) {
          break;
        }
      }
      if (flag) {
        if (null != cats) {
          throw new WebMessageException(ReturnCode.failure, "该属性分类下存在产品,不可修改此分类");
        }
        if (null != cat.getState() && cat.getState().equals(OYn.NO.getLine().getKey())) {
          throw new WebMessageException(ReturnCode.failure, "该属性分类下存在产品,不可禁用");
        }
      }
    }
  }
}
