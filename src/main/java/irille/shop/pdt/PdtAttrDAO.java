package irille.shop.pdt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import irille.Dao.PdtProductDao;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.platform.pdt.View.PdtAttrView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.idu.IduUpdLines;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtAttr.T;
import irille.shop.plt.PltConfigDAO;
import irille.view.Page;
import irille.view.pdt.PdtProductVueView;

public class PdtAttrDAO {
  public static final Log LOG = new Log(PdtAttrDAO.class);

  /**
   * 查询产品属性
   *
   * @param start
   * @param limit
   * @return
   * @author lingjian
   * @date 2019/1/22 16:06
   */
  public static Page listAttr(String name, String category, Integer start, Integer limit) {
    if (null == start) {
      start = 0;
    }
    if (null == limit) {
      limit = 5;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(PdtAttr.class).FROM(PdtAttr.class).WHERE(PdtAttr.T.DELETED, "=0");
            if (name != null) {
              WHERE(PdtAttr.T.NAME, "like ?", "%" + name + "%");
            }
            if (category != null) {
              WHERE(T.CATEGORY, "=?", category);
            }
            WHERE(PdtAttr.T.SUPPLIER, " IS NULL ");
            WHERE(PdtAttr.T.DELETED, " =? ", OYn.NO.getLine().getKey());
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<PdtAttrView> list =
        Query.sql(sql.ORDER_BY(PdtAttr.T.CREATE_TIME, "DESC").LIMIT(start, limit)).queryMaps()
            .stream()
            .map(
                bean ->
                    new PdtAttrView() {
                      {
                        setId((Integer) bean.get(PdtAttr.T.PKEY.getFld().getCodeSqlField()));
                        setName((String) bean.get(PdtAttr.T.NAME.getFld().getCodeSqlField()));
                        setCATEGORY(
                            Bean.load(
                                    PdtAttrCat.class,
                                    (Integer) bean.get(T.CATEGORY.getFld().getCodeSqlField()))
                                .getName());
                        setCreatedTime(
                            (Date) bean.get(PdtAttr.T.CREATE_TIME.getFld().getCodeSqlField()));
                        Integer c =
                            (Integer) bean.get(PdtSize.T.CREATE_BY.getFld().getCodeSqlField());
                        if (null != c) {
                          setCreatedBy(BeanBase.load(SysUser.class, c).getLoginName());
                        }
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  /**
   * 新增产品属性
   *
   * @author lingjian
   * @date 2019/1/22 16:23
   */
  public static class InsAttr extends IduIns<PdtAttrDAO.Ins, PdtAttr> {
    @Override
    public void before() {
      try {
        JSONObject json = new JSONObject(getB().getName());
        for (Language l : FldLanguage.Language.values()) {
          if (null != json.getString(l.name()) && !"".equals(json.getString(l.name()).trim())) {
            SQL sql = new SQL();
            sql.SELECT(PdtAttr.T.PKEY);
            sql.FROM(PdtAttr.class);
            sql.WHERE(
                PdtAttr.class.getSimpleName() + "." + PdtAttr.T.NAME + "->'$." + l.name() + "' = ?",
                json.getString(l.name()));
            sql.WHERE(PdtAttr.T.CATEGORY, " =? ", getB().getCategory());
            sql.WHERE(PdtAttr.T.DELETED, "=?", OYn.NO.getLine().getKey());
            Integer attrs = Query.sql(sql).queryCount();
            if (null != attrs && attrs > 0) {
              throw LOG.err(
                  "nameCopy", l.displayName() + "名称【" + json.getString(l.name()) + "】已存在");
            }
          }
        }
      } catch (JSONException e) {
        throw LOG.err("noaccess", "非法参数");
      }

      getB().setDeleted(OYn.NO.getLine().getKey());
      getB().setCreateTime(Env.getTranBeginTime());
      setB(
          translateUtil.newAutoTranslate(
              getB(), translateUtil.buildFilter(getB().getName(), PltConfigDAO.manageLanguage())));
      super.before();
    }
  }

  /**
   * 修改产品属性
   *
   * @author lingjian
   * @date 2019/1/22 16:23
   */
  public static class UpdAttr extends IduUpd<PdtAttrDAO.Upd, PdtAttr> {
    @Override
    public void before() {
      try {
        JSONObject json = new JSONObject(getB().getName());
        for (Language l : FldLanguage.Language.values()) {
          if (null != json.getString(l.name()) && !"".equals(json.getString(l.name()).trim())) {
            SQL sql = new SQL();
            sql.SELECT(PdtAttr.T.PKEY);
            sql.FROM(PdtAttr.class);
            sql.WHERE(
                PdtAttr.class.getSimpleName() + "." + PdtAttr.T.NAME + "->'$." + l.name() + "' = ?",
                json.getString(l.name()));
            sql.WHERE(PdtAttr.T.CATEGORY, " =? ", getB().getCategory());
            sql.WHERE(PdtAttr.T.DELETED, "=?", OYn.NO.getLine().getKey());
            Integer attrs = Query.sql(sql).queryCount();
            if (null != attrs && attrs > 1) {
              throw LOG.err(
                  "nameCopy", l.displayName() + "名称【" + json.getString(l.name()) + "】已存在");
            }
          }
        }
      } catch (JSONException e) {
        throw LOG.err("noaccess", "非法参数");
      }
      PdtAttr dbBean = loadThisBeanAndLock();
      // getB().setCreateTime(Env.getSystemTime());//自动生成修改时间
      PropertyUtils.copyPropertiesWithout(
          dbBean,
          translateUtil.newAutoTranslate(
              getB(),
              translateUtil.buildFilter(
                  dbBean.getName(), getB().getName(), PltConfigDAO.manageLanguage())),
          PdtAttr.T.PKEY,
          PdtAttr.T.CREATE_BY,
          PdtAttr.T.CREATE_TIME,
          PdtAttr.T.DELETED,
          PdtAttr.T.ROW_VERSION);
      setB(dbBean);
    }
  }

  /**
   * 删除产品属性
   *
   * @author lingjian
   * @date 2019/1/22 16:23
   */
  public static class DelAttr extends IduUpd<PdtAttrDAO.Del, PdtAttr> {
    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.YES.getLine().getKey());
      PdtAttr dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), PdtAttr.T.DELETED);
      setB(dbBean);
    }
  }

  public static class Ins extends IduInsLines<Ins, PdtAttr, PdtAttrLine> {

    @Override
    public void before() {
      super.before();
      getB().stCreateBy(getUser());
      getB().setCreateTime(Env.getTranBeginTime());
      getB().setDeleted(OYn.NO.getLine().getKey());
      translateUtil.autoTranslate(getB());
    }

    @Override
    public void after() {
      super.after();
      for (PdtAttrLine line : getLines()) {
        line.setDeleted(OYn.NO.getLine().getKey());
        line.stCreateBy(getUser());
        line.setCreateTime(Env.getTranBeginTime());
        translateUtil.autoTranslate(line);
      }
      insLine(getB(), getLines(), PdtAttrLine.T.MAIN.getFld());
    }
  }

  public static class PageSelect extends IduOther<PageSelect, PdtAttr> {

    /**
     * @Description: 获取商品所有规格属性
     *
     * @author lijie@shoestp.cn
     * @date 2018/8/22 17:05
     */
    public List getAllAttr(FldLanguage.Language language) {
      List result = new ArrayList();
      // 不做分类查询
      String sql = PdtAttr.T.DELETED.getFld().getCodeSqlField() + " = " + OYn.NO.getLine().getKey();
      PdtAttr.list(PdtAttr.class, sql, false)
          .forEach(
              l -> {
                PdtProductVueView attr = new PdtProductVueView();
                translateUtil.getAutoTranslate(l, language);
                attr.setId(l.getPkey());
                attr.setName(l.getName());
                List lineList = new ArrayList();
                PdtAttrLine.list(
                        PdtAttrLine.class,
                        PdtAttrLine.T.MAIN
                            + "="
                            + l.getPkey()
                            + " AND "
                            + PdtAttrLine.T.DELETED
                            + " = "
                            + OYn.NO.getLine().getKey(),
                        false)
                    .forEach(
                        ll -> {
                          translateUtil.getAutoTranslate(ll, language);
                          PdtProductVueView line = new PdtProductVueView();
                          line.setId(ll.getPkey());
                          line.setName(ll.getName());
                          lineList.add(line);
                        });
                attr.setItems(lineList);
                result.add(attr);
              });
      return result;
    }

    /**
     * xy
     *
     * @param language
     * @param supplier
     * @return
     */
    public List getAllAttr(FldLanguage.Language language, Integer supplier, Integer cat) {
      if (null == cat) {
        throw new WebMessageException(ReturnCode.failure, "请选择产品分类");
      }
      PdtProductDao pdtproductDao = new PdtProductDao();
      String parentCat = String.join(",", pdtproductDao.getParent(cat));

      SQL sql1 = new SQL();
      sql1.SELECT(" DISTINCT " + PdtAttr.class.getSimpleName() + ".*").FROM(PdtAttr.class);
      sql1.LEFT_JOIN(PdtAttrCat.class, PdtAttrCat.T.PKEY, PdtAttr.T.CATEGORY);
      sql1.LEFT_JOIN(PdtAttrPro.class, PdtAttrPro.T.ATTRCAT, PdtAttrCat.T.PKEY);
      sql1.WHERE(PdtAttr.T.DELETED, " =? ", OYn.NO.getLine().getKey());
      sql1.WHERE(
          "("
              + PdtAttr.class.getSimpleName()
              + "."
              + PdtAttr.T.SUPPLIER.getFld().getCodeSqlField()
              + " IS NULL OR "
              + PdtAttr.class.getSimpleName()
              + "."
              + PdtAttr.T.SUPPLIER.getFld().getCodeSqlField()
              + " =?)",
          supplier);
      sql1.WHERE(PdtAttrCat.T.STATE, " =? ", OYn.NO.getLine().getKey());
      sql1.WHERE(PdtAttrPro.T.PROCAT, " IN (" + parentCat + ") ");
      sql1.ORDER_BY(PdtAttr.T.SUPPLIER, " ASC ");

      return Query.sql(sql1).queryList(PdtAttr.class).stream()
          .map(
              l -> {
                PdtProductVueView attr = new PdtProductVueView();
                translateUtil.getAutoTranslate(l, language);
                attr.setId(l.getPkey());
                attr.setName(l.getName());
                attr.setSupplier(l.getSupplier());
                List lineList = new ArrayList();
                PdtAttrLine.list(
                        PdtAttrLine.class,
                        PdtAttrLine.T.MAIN
                            + "="
                            + l.getPkey()
                            + " AND "
                            + PdtAttrLine.T.DELETED
                            + " = "
                            + OYn.NO.getLine().getKey(),
                        false)
                    .forEach(
                        ll -> {
                          translateUtil.getAutoTranslate(ll, language);
                          PdtProductVueView line = new PdtProductVueView();
                          line.setId(ll.getPkey());
                          line.setName(ll.getName());
                          lineList.add(line);
                        });
                attr.setItems(lineList);
                return attr;
              })
          .filter(
              l -> {
                if (l.getItems().size() > 0) {
                  return true;
                } else {
                  return false;
                }
              })
          .collect(Collectors.toList());
    }
  }

  public static class Upd extends IduUpdLines<Upd, PdtAttr, PdtAttrLine> {
    @Override
    public void before() {
      super.before();
      try {
        JSONObject json = new JSONObject(getB().getName());
        String value = json.getString(PltConfigDAO.manageLanguage().name());
        getB().setName(value);
        PdtAttr dbBean = loadThisBeanAndLock();
        dbBean.setDeleted(Sys.OYn.NO.getLine().getKey());
        PropertyUtils.copyProperties(
            dbBean, translateUtil.autoTranslate(getB()), T.NAME, T.CATEGORY);
        setB(dbBean);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void valid() {
      super.valid();
    }

    @Override
    public void after() {
      super.after();
      for (PdtAttrLine line : getLines()) {
        if (line.getPkey() == null) {
          line.setDeleted(Sys.OYn.NO.getLine().getKey());
          line.stCreateBy(getUser());
          line.setCreateTime(Env.getTranBeginTime());
        } else {
          line.setDeleted(Sys.OYn.NO.getLine().getKey());
        }
        line = translateUtil.autoTranslateByManageLanguage(line, true);
      }
      PdtAttrLineDAO.updByMain(getLines(), getB().getPkey());
      //            updLine(getB(),getLines(), PdtAttrLine.T.MAIN.getFld());
    }
  }

  public static class Del extends IduUpd<Upd, PdtAttr> {
    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.YES.getLine().getKey());
      PdtAttr dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
      setB(dbBean);
      List<PdtAttrLine> list = getLines(PdtAttrLine.T.MAIN, getB().getPkey());
      for (PdtAttrLine pal : list) {
        pal.setDeleted(OYn.YES.getLine().getKey());
        pal.upd();
      }
    }
  }

  /**
   * -验证商家添加了多少属性
   *
   * @param supplier
   * @return
   */
  public static Integer verifySupplierCount(Integer supplier) {
    SQL sql = new SQL();
    sql.SELECT(PdtAttr.class);
    sql.FROM(PdtAttr.class);
    sql.WHERE(PdtAttr.T.SUPPLIER, " =? ", supplier);
    sql.WHERE(PdtAttr.T.DELETED, " =? ", OYn.NO.getLine().getKey());
    Integer count = Query.sql(sql).queryCount();
    return count;
  }

  /**
   * -商家删除属性与属性明细
   *
   * @param attrPkey
   */
  public static void delAttrAndAttrLine(Integer attrPkey, Integer supplier) {
    PdtAttr attr = Query.SELECT(PdtAttr.class, attrPkey);
    if (attr == null || attr.getSupplier() == null || !attr.getSupplier().equals(supplier)) {
      throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    }
    List<PdtAttrLine> lines =
        BeanBase.list(
            PdtAttrLine.class,
            PdtAttrLine.T.MAIN.getFld().getCodeSqlField()
                + " =? AND "
                + PdtAttrLine.T.DELETED.getFld().getCodeSqlField()
                + " =? ",
            false,
            attrPkey,
            OYn.NO.getLine().getKey());
    if (lines != null && lines.size() > 0) {
      List<Integer> linePkeys =
          lines.stream()
              .map(
                  bean -> {
                    return bean.getPkey();
                  })
              .collect(Collectors.toList());
      String condition =
          linePkeys.stream()
              .map(
                  b -> {
                    return String.valueOf(b);
                  })
              .collect(Collectors.joining("|"));
      List<PdtProduct> products =
          BeanBase.list(
              PdtProduct.class,
              PdtProduct.T.NORM_ATTR.getFld().getCodeSqlField() + " REGEXP (?) ",
              false,
              condition);
      if (null != products && products.size() > 0) {
        try {
          String name = attr.getName(PltConfigDAO.supplierLanguage(supplier));
          throw new WebMessageException(ReturnCode.failure, "属性【" + name + "】存在商品不可删除");
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }

    PdtAttrDAO.Del del = new Del();
    del.setB(attr);
    del.commit();
  }

  public static PdtProductVueView insAttrAndAttrLine(
      Integer supplier, String name, String value, Language lag) {

    List<PdtAttr> attrs =
        BeanBase.list(
            PdtAttr.class,
            PdtAttr.T.NAME.getFld().getCodeSqlField()
                + "->'$.en' = ? AND "
                + PdtAttr.T.SUPPLIER.getFld().getCodeSqlField()
                + " =? AND "
                + PdtAttr.T.DELETED.getFld().getCodeSqlField()
                + " =? ",
            false,
            name,
            supplier,
            OYn.NO.getLine().getKey());

    if (null != attrs && attrs.size() > 0) {
      throw new WebMessageException(ReturnCode.failure, "属性名【" + name + "】已存在,请勿重复添加");
    }

    PdtAttr attr = new PdtAttr();
    attr.setName(name);
    attr.setCategory(132);
    attr.setDeleted(OYn.NO.getLine().getKey());
    attr.setCreateTime(Env.getSystemTime());
    attr.setSupplier(supplier);
    attr.setRowVersion((short) 0);
    translateUtil.autoTranslate(attr);
    PdtAttr insAttr = attr.ins();
    PdtAttrLine attrLine = new PdtAttrLine();
    attrLine.setName(value);
    attrLine.setMain(insAttr.getPkey());
    attrLine.setDeleted(OYn.NO.getLine().getKey());
    attrLine.setCreateTime(Env.getSystemTime());
    attrLine.setRowVersion((short) 0);
    translateUtil.autoTranslate(attrLine);
    attrLine.ins();
    PdtProductVueView attrView = new PdtProductVueView();
    attrView.setId(attr.getPkey());
    try {
      attrView.setName(attr.getName(Language.en));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    attrView.setSupplier(attr.getSupplier());
    List lineList = new ArrayList();
    PdtAttrLine.list(
            PdtAttrLine.class,
            PdtAttrLine.T.MAIN
                + "="
                + attr.getPkey()
                + " AND "
                + PdtAttrLine.T.DELETED
                + " = "
                + OYn.NO.getLine().getKey(),
            false)
        .forEach(
            ll -> {
              translateUtil.getAutoTranslate(ll, lag);
              PdtProductVueView line = new PdtProductVueView();
              line.setId(ll.getPkey());
              line.setName(ll.getName());
              lineList.add(line);
            });
    attrView.setItems(lineList);
    return attrView;
  }

  public boolean getCount(Integer cat) {
    String sql = "SELECT  pkey FROM pdt_attr_line WHERE main = " + cat + " AND deleted=0";
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
}
