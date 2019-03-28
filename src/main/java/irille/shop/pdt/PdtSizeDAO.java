package irille.shop.pdt;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;

import irille.Dao.PdtProductDao;
import irille.action.dataimport.util.StringUtil;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.platform.pdt.View.PdtSizeView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.I18NSQL;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidForm;
import irille.sellerAction.SellerAction;
import irille.shop.pdt.PdtSize.T;
import irille.shop.plt.PltConfigDAO;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

public class PdtSizeDAO {
  public static final Log LOG = new Log(PdtSizeDAO.class);

  /**
   * 查询产品尺寸
   *
   * @param start
   * @param limit
   * @return
   * @author lingjian
   * @date 2019/1/22 13:37
   */
  public static Page listSize(String name, String productCategory, Integer start, Integer limit) {
    if (null == start) {
      start = 0;
    }
    if (null == limit) {
      limit = 5;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(PdtSize.class)
                .FROM(PdtSize.class)
                .WHERE(PdtSize.T.DELETED, "=0")
                .WHERE(T.TYPEVER, "!=0")
                .WHERE(PdtSize.T.SUPPLIER, " IS NULL ");
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<PdtSizeView> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                bean ->
                    new PdtSizeView() {
                      {
                        setId((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
                        setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
                        setType((Byte) bean.get(T.TYPE.getFld().getCodeSqlField()));
                        Integer s =
                            (Integer) bean.get(T.PRODUCT_CATEGORY.getFld().getCodeSqlField());
                        if (null != s) {
                          setProductCategory(BeanBase.load(PdtCat.class, s).getName());
                        }
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  /**
   * 查询产品尺寸
   *
   * @param start
   * @param limit
   * @return
   * @author xy
   * @date 2019/3/1 15:30
   */
  public static Page newListSize(
      String name, String productCategory, Integer start, Integer limit) {
    if (null == start) {
      start = 0;
    }
    if (null == limit) {
      limit = 5;
    }
    System.out.println(name + "---" + productCategory);
    SQL sql =
        new SQL() {
          {
            SELECT(PdtSize.class).FROM(PdtSize.class).WHERE(PdtSize.T.DELETED, "=0");
            if (name != null) {
              WHERE(PdtSize.T.NAME, "like ?", "%" + name + "%");
            }
            if (productCategory != null) {
              WHERE(T.PRODUCT_CATEGORY, "=?", productCategory);
            }
          }
        };
    sql.WHERE(T.TYPEVER, " =? ", Pdt.OVer.NEW_1.getLine().getKey());
    Integer count = Query.sql(sql).queryCount();
    List<PdtSizeView> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                bean ->
                    new PdtSizeView() {
                      {
                        setId((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
                        setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));

                        Integer s =
                            (Integer) bean.get(T.PRODUCT_CATEGORY.getFld().getCodeSqlField());
                        if (null != s) {
                          setProductCategory(BeanBase.load(PdtCat.class, s).getName());
                        }
                        setCreatedTime((Date) bean.get(T.CREATE_TIME.getFld().getCodeSqlField()));
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
   * 新增产品尺寸
   *
   * @author lingjian
   * @date 2019/1/22 13:37
   */
  public static class InsSize extends IduIns<PdtSizeDAO.Ins, PdtSize> {
    @Override
    public void before() {
      getB().setDeleted(OYn.NO.getLine().getKey());
      getB().setCreateTime(Env.getTranBeginTime());
      // setB(translateUtil.autoTranslate(getB()));
      super.before();
    }
  }

  /**
   * 修改产品尺寸
   *
   * @author lingjian
   * @date 2019/1/22 13:37
   */
  public static class UpdSize extends IduUpd<PdtSizeDAO.Upd, PdtSize> {
    @Override
    public void before() {
      PdtSize dbBean = loadThisBeanAndLock();
      getB().setCreateTime(Env.getSystemTime()); // 自动生成修改时间
      PropertyUtils.copyPropertiesWithout(
          dbBean,
          getB(),
          PdtSize.T.PKEY,
          PdtSize.T.SUPPLIER,
          PdtSize.T.CREATE_BY,
          PdtSize.T.CREATE_TIME,
          PdtSize.T.DELETED,
          PdtSize.T.TYPEVER,
          PdtSize.T.ROW_VERSION);
      setB(dbBean);
    }
  }

  /**
   * 删除产品尺寸
   *
   * @author lingjian
   * @date 2019/1/22 13:38
   */
  public static class DelSize extends IduUpd<PdtSizeDAO.Del, PdtSize> {
    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.YES.getLine().getKey());
      PdtSize dbBean = loadThisBeanAndLock();
      chooseSizePdt(dbBean, 2);
      PropertyUtils.copyProperties(dbBean, getB(), PdtSize.T.DELETED);
      setB(dbBean);
    }
  }

  public static List<PdtSize> listSummary(Language lang, Integer type) {

    SQL sql =
        new I18NSQL(lang) {
          {
            SELECT(T.PKEY, T.NAME, T.CREATE_BY, T.ROW_VERSION);
            FROM(PdtSize.class)
                .WHERE(T.DELETED, "=?", OYn.NO)
                .WHERE(T.SUPPLIER, " is null ")
                .WHERE(T.TYPEVER, " =? ", Pdt.OVer.ELSE.getLine().getKey());
          }
        };
    List<PdtSize> sysSize = Query.sql(sql).queryList(PdtSize.class);
    if (type == 1) {
      SQL supSql =
          new I18NSQL(lang) {
            {
              SELECT(T.PKEY, T.NAME, T.CREATE_BY, T.ROW_VERSION);
              FROM(PdtSize.class)
                  .WHERE(T.DELETED, "=?", OYn.NO)
                  .WHERE(T.SUPPLIER, " =? ", SellerAction.getSupplier().getPkey())
                  .WHERE(T.TYPEVER, " =? ", Pdt.OVer.ELSE.getLine().getKey());
            }
          };
      List<PdtSize> supSize = Query.sql(supSql).queryList(PdtSize.class);
      sysSize.addAll(supSize);
    }
    return sysSize;
  }

  public static List<PdtSize> newListSummary(
      UsrSupplier supplier, Language lang, Integer type, Integer cat) {

    /*SQL sql = new I18NSQL(lang) {{
        SELECT(T.PKEY, T.NAME, T.CREATE_BY, T.ROW_VERSION);
        FROM(PdtSize.class).WHERE(T.DELETED, "=?", OYn.NO).WHERE(T.SUPPLIER, " is null ");
    }};
    List<PdtSize> sysSize = Query.sql(sql).queryList(PdtSize.class);
    if (type == 1) {
        SQL supSql = new I18NSQL(lang) {{
            SELECT(T.PKEY, T.NAME, T.CREATE_BY, T.ROW_VERSION);
            FROM(PdtSize.class).WHERE(T.DELETED, "=?", OYn.NO).WHERE(T.SUPPLIER, " =? ", SellerAction.getSupplier().getPkey());
        }};
        List<PdtSize> supSize = Query.sql(supSql).queryList(PdtSize.class);
        sysSize.addAll(supSize);
    }
    return sysSize;*/
    if (null == cat) {
      throw new WebMessageException(ReturnCode.failure, "请先选择产品分类");
    }
    PdtProductDao pdtProductDao = new PdtProductDao();
    SQL sql =
        new I18NSQL(lang) {
          {
            SELECT(T.PKEY, T.NAME, T.CREATE_BY, T.ROW_VERSION, T.SUPPLIER, T.TYPE);
            FROM(PdtSize.class)
                .WHERE(T.DELETED, "=?", OYn.NO)
                .WHERE(T.TYPEVER, " =? ", Pdt.OVer.NEW_1.getLine().getKey());
            if (type.equals(1)) {
              WHERE(
                  "("
                      + PdtSize.class.getSimpleName()
                      + "."
                      + PdtSize.T.SUPPLIER.getFld().getCodeSqlField()
                      + " IS NULL OR "
                      + PdtSize.class.getSimpleName()
                      + "."
                      + PdtSize.T.SUPPLIER.getFld().getCodeSqlField()
                      + " =? )",
                  supplier.getPkey());
              WHERE(
                  "("
                      + PdtSize.class.getSimpleName()
                      + "."
                      + PdtSize.T.PRODUCT_CATEGORY.getFld().getCodeSqlField()
                      + " in("
                      + String.join(",", pdtProductDao.getParent(cat))
                      + ") OR "
                      + PdtSize.class.getSimpleName()
                      + "."
                      + PdtSize.T.PRODUCT_CATEGORY.getFld().getCodeSqlField()
                      + " IS NULL )");
            } else {
              WHERE(
                  PdtSize.class.getSimpleName()
                      + "."
                      + PdtSize.T.SUPPLIER.getFld().getCodeSqlField()
                      + " IS NULL");
            }
          }
        };
    //    SQL sql =
    //        new I18NSQL(lang) {
    //          {
    //            SELECT(T.PKEY, T.NAME, T.CREATE_BY, T.ROW_VERSION, T.SUPPLIER, T.TYPE);
    //            FROM(PdtSize.class)
    //                .WHERE(T.DELETED, "=?", OYn.NO)
    //                .WHERE(T.SUPPLIER, " is null ")
    //                .WHERE(T.TYPEVER, " =? ", Pdt.OVer.NEW_1.getLine().getKey());
    //          }
    //        };
    List<PdtSize> sysSize = Query.sql(sql).queryList(PdtSize.class);
    //    if (type == 1) {
    //      SQL supSql =
    //          new I18NSQL(lang) {
    //            {
    //              SELECT(T.PKEY, T.NAME, T.CREATE_BY, T.ROW_VERSION, T.SUPPLIER, T.TYPE);
    //              FROM(PdtSize.class)
    //                  .WHERE(T.DELETED, "=?", OYn.NO)
    //                  .WHERE(T.SUPPLIER, " =? ", SellerAction.getSupplier().getPkey())
    //                  .WHERE(T.TYPEVER, " =? ", Pdt.OVer.NEW_1.getLine().getKey());
    //            }
    //          };
    //      List<PdtSize> supSize = Query.sql(supSql).queryList(PdtSize.class);
    //      sysSize.addAll(supSize);
    //    }
    return sysSize;
  }

  public static class InsSizeBySup extends IduOther<InsSizeBySup, PdtSize> {
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
      PdtSize size = getB().ins();
    }

    public void after() {
      setB(
          translateUtil.getAutoTranslate(
              getB(), PltConfigDAO.supplierLanguage(SellerAction.getSupplier())));
    }
  }

  public static Integer delSizeBySup(Serializable id) {
    PdtSize size = BeanBase.load(PdtSize.class, id);
    if (size.getCreateBy() != null) {
      throw LOG.err("noAccess", "您没有权限删除平台尺寸");
    }
    size.setDeleted(Sys.OYn.YES.getLine().getKey());
    size = size.upd();
    return size.getPkey();
  }

  public static class Ins extends IduIns<Ins, PdtSize> {

    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.NO.getLine().getKey());
      getB().stCreateBy(getUser());
      getB().setCreateTime(Env.getTranBeginTime());
      translateUtil.autoTranslate(getB());
    }
  }

  public static class Upd extends IduUpd<Upd, PdtSize> {

    @Override
    public void before() {
      super.before();
      PdtSize dbBean = loadThisBeanAndLock();
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

  public static class InsInit extends IduOther<InsInit, PdtSize> {
    private Stream<String> stream = Stream.empty();

    public Stream<String> getStream() {
      return stream;
    }

    public void setStream(Stream<String> stream) {
      this.stream = stream;
    }

    @Override
    public void run() {
      super.run();
      stream.forEach(
          (name) -> {
            PdtSize b = new PdtSize().init();
            b.setDeleted(OYn.NO.getLine().getKey());
            b.setName(name);
            translateUtil.autoTranslate(b).ins();
          });
    }
  }

  public static class Del extends IduUpd<Upd, PdtSize> {

    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.YES.getLine().getKey());
      PdtSize dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
      setB(dbBean);
    }
  }

  /**
   * xy
   *
   * @param type
   * @param name
   * @param supplier
   * @return
   */
  public static PdtSize insSize(Byte type, String name, Integer supplier, Language lag) {
    checkName(name, true);
    String engName = "";
    try {
      JSONObject json = new JSONObject(name);
      engName = String.valueOf(json.get(FldLanguage.Language.en.name()));
    } catch (JSONException e) {
      throw new WebMessageException(ReturnCode.failure, "非法格式");
    }
    if ("".equals(engName)) {
      throw new WebMessageException(ReturnCode.failure, "请输入英文尺码");
    }
    List<PdtSize> attrs =
        BeanBase.list(
            PdtSize.class,
            PdtSize.T.NAME.getFld().getCodeSqlField()
                + "->'$.en' = ? AND "
                + PdtSize.T.SUPPLIER.getFld().getCodeSqlField()
                + " =? AND "
                + PdtSize.T.DELETED.getFld().getCodeSqlField()
                + " =? AND "
                + PdtSize.T.TYPE.getFld().getCodeSqlField()
                + " =? ",
            false,
            engName,
            supplier,
            OYn.NO.getLine().getKey(),
            type);
    if (null != attrs && attrs.size() > 0) {
      throw new WebMessageException(ReturnCode.failure, "尺码【" + engName + "】已存在,请勿重复添加");
    }
    PdtSize size = new PdtSize();
    size.setName(name);
    size.setSupplier(supplier);
    size.setDeleted(OYn.NO.getLine().getKey());
    size.setCreateTime(Env.getSystemTime());
    if (type.equals(Pdt.OSizeType.EU.getLine().getKey()))
      size.setType(Pdt.OSizeType.EU.getLine().getKey());
    else if (type.equals(Pdt.OSizeType.USA.getLine().getKey()))
      size.setType(Pdt.OSizeType.USA.getLine().getKey());
    size.setTypever(Pdt.OVer.NEW_1.getLine().getKey());
    size.setRowVersion((short) 0);
    size.ins();
    try {
      size.setName(size.getName(lag));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return size;
  }

  /**
   * xy
   *
   * @param supplier
   * @param pkey
   * @param name
   */
  public static void updSize(Integer supplier, Integer pkey, String name) {
    checkName(name, true);
    String engName = "";
    try {
      JSONObject json = new JSONObject(name);
      engName = String.valueOf(json.get(FldLanguage.Language.en.name()));
    } catch (JSONException e) {
      throw new WebMessageException(ReturnCode.failure, "非法格式");
    }
    if ("".equals(engName)) {
      throw new WebMessageException(ReturnCode.failure, "请输入英文尺码");
    }
    List<PdtSize> attrs =
        BeanBase.list(
            PdtSize.class,
            PdtSize.T.NAME.getFld().getCodeSqlField()
                + "->'$.en' = ? AND "
                + PdtSize.T.SUPPLIER.getFld().getCodeSqlField()
                + " =? AND "
                + PdtSize.T.DELETED.getFld().getCodeSqlField()
                + " =? ",
            false,
            engName,
            supplier,
            OYn.NO.getLine().getKey());
    if (null != attrs && attrs.size() > 0) {
      throw new WebMessageException(ReturnCode.failure, "尺码【" + engName + "】已存在,请勿重复添加");
    }
    SQL sql = new SQL();
    sql.SELECT(PdtSize.class);
    sql.FROM(PdtSize.class);
    sql.WHERE(PdtSize.T.PKEY, " =? ", pkey);
    sql.WHERE(PdtSize.T.SUPPLIER, " =? ", supplier);
    PdtSize size = Query.sql(sql).query(PdtSize.class);
    if (size == null && size.getDeleted() != OYn.YES.getLine().getKey())
      throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    size.setName(name);
    size.upd();
  }

  /**
   * xy
   *
   * @param supplier
   * @param pkey
   */
  public static void delSize(Integer supplier, Integer pkey) {
    SQL sql = new SQL();
    sql.SELECT(PdtSize.class);
    sql.FROM(PdtSize.class);
    sql.WHERE(PdtSize.T.PKEY, " =? ", pkey);
    sql.WHERE(PdtSize.T.SUPPLIER, " =? ", supplier);
    PdtSize size = Query.sql(sql).query(PdtSize.class);
    if (size == null) throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    size.setDeleted(OYn.YES.getLine().getKey());
    size.upd();
  }

  /**
   * 平台添加
   *
   * @param user
   * @param type
   * @param name
   */
  public static void plaInsSize(SysUser user, Byte type, String name, Integer pdtCate) {
    PdtSize size = new PdtSize();
    size.setName(name);
    checkName(size.getName(), true);
    if (pdtCate != null) {
      PdtCat cat = Query.SELECT(PdtCat.class, pdtCate);
      if (cat != null) size.setProductCategory(pdtCate);
    }
    size.setDeleted(OYn.NO.getLine().getKey());
    size.setCreateBy(user.getPkey());
    size.setCreateTime(Env.getSystemTime());
    size.setType(type);
    size.setTypever(Pdt.OVer.NEW_1.getLine().getKey());
    size.setRowVersion((short) 0);
    checkNameInUse(size, name);
    translateUtil.autoTranslate(size);
    size.ins();
  }

  /**
   * 平台修改
   *
   * @param pkey
   * @param type
   * @param name
   * @param pdtCate
   */
  public static void plaUpdSize(Integer pkey, Byte type, String name, Integer pdtCate) {
    PdtSize size = Query.SELECT(PdtSize.class, pkey);
    if (size == null) throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    size.setType(type);
    size.setName(name);
    checkName(size.getName(), true);
    chooseSizePdt(size, 1);
    checkNameInUse(size, size.getName());
    if (pdtCate != null) {
      PdtCat cat = Query.SELECT(PdtCat.class, pdtCate);
      if (cat != null) size.setProductCategory(pdtCate);
    }
    translateUtil.autoTranslate(size);
    size.upd();
  }

  // 校验名称合法
  public static void checkName(String name, boolean lag) {
    if (lag) {
      try {
        JSONObject json = new JSONObject(name);
        Object object = json.get("en");
        Pattern p = Pattern.compile("[0-9]+([.]{1}[0-9]+){0,1}");
        Matcher matcher = p.matcher(object.toString());
        if (!matcher.matches())
          throw new WebMessageException(ReturnCode.service_wrong_data, "名称只能为数字");
        if (object.toString().length() > 5)
          throw new WebMessageException(ReturnCode.service_wrong_data, "长度最大为5");
      } catch (JSONException | NullPointerException e) {
        throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
      }
    } else {
      Pattern p = Pattern.compile("[0-9]+([.]{1}[0-9]+){0,1}");
      Matcher matcher = p.matcher(name);
      if (!matcher.matches())
        throw new WebMessageException(ReturnCode.service_wrong_data, "名称只能为数字");
      if (name.length() > 5) throw new WebMessageException(ReturnCode.service_wrong_data, "长度最大为5");
    }
  }

  // 校验该名称是否被使用
  public static void checkNameInUse(PdtSize size, String name) {
    SQL sql = new SQL();
    sql.SELECT(PdtSize.class).FROM(PdtSize.class);
    sql.WHERE(PdtSize.T.DELETED, " =? ", OYn.NO.getLine().getKey());
    if (size.getTypever() == Pdt.OVer.ELSE.getLine().getKey()) {
      sql.WHERE(PdtSize.T.TYPEVER, " =? ", Pdt.OVer.ELSE.getLine().getKey());
    } else if (size.getTypever() == Pdt.OVer.NEW_1.getLine().getKey()) {
      sql.WHERE(PdtSize.T.TYPEVER, " =? ", Pdt.OVer.NEW_1.getLine().getKey());
    }
    if (size.getType() != null) {
      sql.WHERE(PdtSize.T.TYPE, " =? ", size.getType());
    }
    if (size.getSupplier() != null) {
      sql.WHERE(PdtSize.T.SUPPLIER, " =? ", size.getSupplier());
    } else {
      sql.WHERE(PdtSize.T.SUPPLIER, " is null");
    }
    String str = "";
    try {
      str = new JSONObject(name).get(PltConfigDAO.manageLanguage().toString()).toString();
      if (!StringUtil.hasValue(str))
        throw new WebMessageException(ReturnCode.service_wrong_data, "当前语言下的名称不能为空");
    } catch (JSONException e) { // TODO Auto-generated catch block
      e.printStackTrace();
      throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    }
    sql.WHERE(
        "JSON_EXTRACT(PdtSize.name,?) = ?", "$." + PltConfigDAO.manageLanguage().toString(), str);
    sql.WHERE(PdtSize.T.PKEY," <>? ",size.getPkey());
    Integer count = Query.sql(sql).queryCount();
    if (count > 0) {
      String msg = "";
      if (size.getType() == Pdt.OSizeType.USA.getLine().getKey()) {
        msg = "美码";
      } else if (size.getType() == Pdt.OSizeType.EU.getLine().getKey()) {
        msg = "欧码";
      }
      throw new WebMessageException(ReturnCode.failure, msg + "尺码【" + str + "】已存在,请勿重复添加");
    }
  }

  // 校验该数据是否被引用
  public static void chooseSizePdt(PdtSize size, Integer type) {
    SQL sql1 = new SQL();
    sql1.SELECT(PdtProduct.T.PKEY).FROM(PdtProduct.class);
    sql1.WHERE(
        "size_attr like (?) or size_attr like (?) or size_attr like (?) or size_attr = ?",
        "%," + size.getPkey(),
        "%," + size.getPkey() + ",%",
        size.getPkey() + ",%",
        size.getPkey());
    Integer count1 = Query.sql(sql1).queryCount();
    if (count1 > 0) {
      String strSize = "";
      if (type == 1) {
        strSize = "当前尺寸已被产品使用,无法修改";
      } else if (type == 2) {
        strSize = "当前尺寸已被产品使用,无法删除";
      }
      throw new WebMessageException(ReturnCode.service_gone, strSize);
    }
  }
}
