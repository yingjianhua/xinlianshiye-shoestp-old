package irille.shop.pdt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import irille.action.dataimport.util.StringUtil;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.platform.pdt.View.PdtColorView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidForm;
import irille.sellerAction.SellerAction;
import irille.shop.pdt.PdtColor.T;
import irille.shop.plt.PltConfigDAO;
import irille.view.Page;

public class PdtColorDAO {
  public static final Log LOG = new Log(PdtColorDAO.class);

  /* public static void main(String[] args) {
      PdtColor.TB.getCode();
      new Table(Query.SELECT(T.PKEY, T.NAME).FROM(PdtColor.class).queryMaps()).print();
  }*/

  /**
   * 查询产品颜色列表
   *
   * @param start
   * @param limit
   * @return
   * @author lingjian
   * @date 2019/1/22 13:37
   */
  public static Page listview(String name, Integer start, Integer limit) {
    if (null == start) {
      start = 0;
    }
    if (null == limit) {
      limit = 5;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(PdtColor.class).FROM(PdtColor.class).WHERE(T.DELETED, "=0");
            if (name != null) {
              WHERE(PdtColor.T.NAME, "like ?", "%" + name + "%");
            }
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<PdtColorView> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                bean ->
                    new PdtColorView() {
                      {
                        setId((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
                        setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
                        //            setCreatedby(BeanBase.load(SysUser.class,
                        // Integer.valueOf(String.valueOf(bean.get(T.CREATE_BY.getFld().getCodeSqlField())))).getLoginName());
                        Integer c =
                            (Integer) bean.get(PdtSize.T.CREATE_BY.getFld().getCodeSqlField());
                        if (null != c) {
                          setCreatedby(BeanBase.load(SysUser.class, c).getLoginName());
                        }
                        setCreatedtime((Date) bean.get(T.CREATE_TIME.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  /**
   * 查询产品颜色列表
   *
   * @param start
   * @param limit
   * @return
   * @author lingjian
   * @date 2019/1/22 13:37
   */
  public static Page newListview(String name, Integer start, Integer limit) {
    if (null == start) {
      start = 0;
    }
    if (null == limit) {
      limit = 5;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(PdtColor.class).FROM(PdtColor.class).WHERE(T.DELETED, "=0");
            if (name != null) {
              WHERE(PdtColor.T.NAME, "like ?", "%" + name + "%");
            }
          }
        };
    sql.WHERE(PdtColor.T.TYPE, " =? ", Pdt.OVer.NEW_1.getLine().getKey());
    Integer count = Query.sql(sql).queryCount();
    List<PdtColorView> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                bean ->
                    new PdtColorView() {
                      {
                        setId((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
                        setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
                        //            setCreatedby(BeanBase.load(SysUser.class,
                        // Integer.valueOf(String.valueOf(bean.get(T.CREATE_BY.getFld().getCodeSqlField())))).getLoginName());
                        Integer c =
                            (Integer) bean.get(PdtSize.T.CREATE_BY.getFld().getCodeSqlField());
                        if (null != c) {
                          setCreatedby(BeanBase.load(SysUser.class, c).getLoginName());
                        }
                        setCreatedtime((Date) bean.get(T.CREATE_TIME.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  /**
   * 新增产品颜色
   *
   * @author lingjian
   * @date 2019/1/22 13:38
   */
  public static class InsColor extends IduIns<PdtColorDAO.Ins, PdtColor> {
    @Override
    public void before() {
      getB().setDeleted(OYn.NO.getLine().getKey());
      getB().setCreateTime(Env.getTranBeginTime());
      setB(translateUtil.autoTranslate(getB()));
      super.before();
    }
  }

  /**
   * 修改产品颜色
   *
   * @author lingjian
   * @date 2019/1/22 13:38
   */
  public static class UpdColor extends IduUpd<PdtColorDAO.Upd, PdtColor> {
    @Override
    public void before() {
      PdtColor dbBean = loadThisBeanAndLock();
      getB().setCreateTime(Env.getSystemTime()); // 自动生成修改时间
      PropertyUtils.copyPropertiesWithout(
          dbBean,
          translateUtil.autoTranslateByManageLanguage(getB(), true),
          T.PKEY,
          T.SUPPLIER,
          T.CREATE_BY,
          T.CREATE_TIME,
          T.DELETED,
          T.ROW_VERSION);
      setB(dbBean);
    }
  }

  /**
   * 删除产品颜色
   *
   * @author lingjian
   * @date 2019/1/22 13:38
   */
  public static class DelColor extends IduUpd<PdtColorDAO.Del, PdtColor> {
    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.YES.getLine().getKey());
      PdtColor dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
      setB(dbBean);
    }
  }

  public static class InsColorBySup extends IduOther<InsColorBySup, PdtColor> {
    public void before() {
      getB().setSupplier(SellerAction.getSupplier().getPkey());
      getB().setDeleted(Sys.OYn.NO.getLine().getKey());
      getB().setCreateTime(Env.getSystemTime());
      setB(translateUtil.autoTranslate(getB()));
    }

    public void valid() {
      ValidForm.validEmpty(getB().getName(), T.NAME);
    }

    public void run() {
      PdtColor color = getB().ins();
    }

    public void after() {
      setB(
          translateUtil.getAutoTranslate(
              getB(), PltConfigDAO.supplierLanguage(SellerAction.getSupplier())));
    }
  }

  public static Integer delColorBySup(Serializable id) {
    PdtColor color = BeanBase.load(PdtColor.class, id);
    if (color.getCreateBy() != null) {
      throw LOG.err("noAccess", "您没有权限删除平台尺寸");
    }
    color.setDeleted(Sys.OYn.YES.getLine().getKey());
    color = color.upd();
    return color.getPkey();
  }

  public static class PageSelect extends IduOther<PageSelect, PdtColor> {
    private Integer type = -1;

    public List getAllColorList(FldLanguage.Language language) {
      FormaterSql sql = FormaterSql.build();
      sql.select(T.NAME)
          .selectAs(T.PKEY, "id")
          .select(T.CREATE_BY)
          .eqAutoAnd(T.DELETED, OYn.NO.getLine().getKey())
          .Andwhere(T.SUPPLIER.getFld().getCodeSqlField() + " is null ")
          .eqAutoAnd(T.TYPE, Pdt.OVer.ELSE.getLine().getKey());
      List<Map> sysColor = sql.castListMap(Bean.list(sql.buildSql(), sql.getParms()), language);
      List<Map> supColor = null;
      if (type != -1) {
        FormaterSql supSql = FormaterSql.build();
        supSql
            .select(T.NAME)
            .selectAs(T.PKEY, "id")
            .select(T.CREATE_BY)
            .select(T.ROW_VERSION)
            .eqAutoAnd(T.DELETED, OYn.NO.getLine().getKey())
            .eqAutoAnd(T.SUPPLIER, SellerAction.getSupplier().getPkey())
            .eqAutoAnd(T.TYPE, Pdt.OVer.ELSE.getLine().getKey());
        supColor = supSql.castListMap(Bean.list(supSql.buildSql(), supSql.getParms()), language);
      }
      sysColor.addAll(supColor);
      return sysColor;
    }

    public Integer getType() {
      return type;
    }

    public void setType(Integer type) {
      this.type = type;
    }
  }

  public static class Ins extends IduIns<Ins, PdtColor> {

    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.NO.getLine().getKey());
      //            getB().setCreateBy(getUser());
      getB().setCreateTime(Env.getTranBeginTime());
      translateUtil.autoTranslate(getB());
    }
  }

  public static class Upd extends IduUpd<Upd, PdtColor> {

    @Override
    public void before() {
      super.before();
      PdtColor dbBean = loadThisBeanAndLock();
      PropertyUtils.copyPropertiesWithout(
          dbBean,
          translateUtil.autoTranslateByManageLanguage(getB(), true),
          T.PKEY,
          T.SUPPLIER,
          T.CREATE_BY,
          T.CREATE_TIME,
          T.DELETED);
      setB(dbBean);
    }
  }

  public static class InsInit extends IduOther<InsInit, PdtColor> {
    private List<PdtColor> list = new ArrayList<>();

    public static PdtColor build(String name) {
      PdtColor c = new PdtColor().init();
      c.setName(name);
      return c;
    }

    @Override
    public void run() {
      super.run();
      for (PdtColor c : list) {
        c.setDeleted(OYn.NO.getLine().getKey());
        translateUtil.autoTranslate(c).ins();
      }
    }

    public List<PdtColor> getList() {
      return list;
    }

    public void setList(List<PdtColor> list) {
      this.list = list;
    }
  }

  public static class Del extends IduUpd<Upd, PdtColor> {
    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.YES.getLine().getKey());
      PdtColor dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
      setB(dbBean);
    }
  }

  /**
   * -新增颜色
   *
   * @param color
   * @param supplier
   */
  public static PdtColor insColor(PdtColor color, Integer supplier) {
    if (color == null
        || !StringUtil.hasValue(color.getName())
        || !StringUtil.hasValue(color.getPicture()))
      throw new WebMessageException(ReturnCode.service_wrong_data, "请输入完整");
    color.setSupplier(supplier);
    color.setDeleted(OYn.NO.getLine().getKey());
    color.setCreateTime(Env.getSystemTime());
    color.setType(Pdt.OVer.NEW_1.getLine().getKey());
    color.setDefaultColor(OYn.NO.getLine().getKey());
    color.setRowVersion((short) 0);
    translateUtil.autoTranslate(color);
    color.ins();
    return color;
  }

  /**
   * -修改
   *
   * @param pkey
   * @param supplier
   * @param name
   * @param pictrue
   */
  public static PdtColor updColor(Integer pkey, Integer supplier, String name, String pictrue) {
    PdtColor color =
        Query.selectFrom(PdtColor.class)
            .WHERE(PdtColor.T.SUPPLIER, " =? ", supplier)
            .WHERE(PdtColor.T.PKEY, " =? ", pkey)
            .query();
    if (color == null) throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    if (StringUtil.hasValue(name)) {
      color.setName(name);
    }
    if (StringUtil.hasValue(pictrue)) {
      color.setPicture(pictrue);
    }
    translateUtil.autoTranslate(color);
    color.upd();
    return color;
  }

  /**
   * -查询新颜色数据中系统默认的颜色
   *
   * @param pdtPkey
   * @param supplier
   * @return
   */
  public static List<irille.view.pdt.PdtColorView> getList() {
    SQL sql = new SQL();
    sql.SELECT(PdtColor.T.PKEY, PdtColor.T.NAME, PdtColor.T.PICTURE, PdtColor.T.DEFAULT_COLOR);
    sql.FROM(PdtColor.class);
    sql.WHERE(PdtColor.T.TYPE, " =? ", Pdt.OVer.NEW_1.getLine().getKey());
    sql.WHERE(PdtColor.T.DEFAULT_COLOR, " =? ", OYn.YES.getLine().getKey());
    sql.WHERE(PdtColor.T.DELETED, " =? ", OYn.NO);
    List<PdtColor> colorList = Query.sql(sql).queryList(PdtColor.class);
    List<irille.view.pdt.PdtColorView> collect =
        colorList.stream()
            .map(
                bean ->
                    new irille.view.pdt.PdtColorView() {
                      {
                        setId(bean.getPkey());
                        setName(bean.getName());
                        setType((int) bean.getDefaultColor());
                        setUrl(bean.getPicture());
                      }
                    })
            .collect(Collectors.toList());
    return collect;
  }

  /**
   * 根据产品中颜色外键查询
   *
   * @param pkeys
   * @return
   */
  public static List<irille.view.pdt.PdtColorView> getPdtColorList(String pkeys) {
    if (!StringUtil.hasValue(pkeys)) {
      return null;
    }
    if (pkeys.substring(pkeys.length() - 1, pkeys.length()).equals(",")) {
      pkeys = pkeys.substring(0, pkeys.length() - 1);
    }
    SQL sql = new SQL();
    sql.SELECT(PdtColor.class);
    sql.FROM(PdtColor.class);
    sql.WHERE(PdtColor.T.PKEY, " in(" + pkeys + ") ");
    sql.WHERE(PdtColor.T.TYPE, " =? ", Pdt.OVer.NEW_1);
    List<PdtColor> colorList = Query.sql(sql).queryList(PdtColor.class);
    return colorList.stream()
        .map(
            bean ->
                new irille.view.pdt.PdtColorView() {
                  {
                    setId(bean.getPkey());
                    setName(bean.getName());
                    setType((int) bean.getDefaultColor());
                    setUrl(bean.getPicture());
                  }
                })
        .collect(Collectors.toList());
  }

  public static void delColor(Integer pkey) {
    PdtColor color = Query.SELECT(PdtColor.class, pkey);
    if (color != null && color.getDefaultColor() != OYn.YES.getLine().getKey()) {
      color.setDeleted(OYn.YES.getLine().getKey());
      color.upd();
    }
  }
}
