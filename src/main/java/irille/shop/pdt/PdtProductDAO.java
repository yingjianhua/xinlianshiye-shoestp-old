package irille.shop.pdt;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.PdtView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SEOUtils;
import irille.pub.util.TranslateLanguage.TranslateFilter;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.sellerAction.SellerAction;
import irille.shop.pdt.Pdt.OState;
import irille.shop.pdt.PdtProduct.T;
import irille.shop.usr.UsrCart;
import irille.shop.usr.UsrCartDAO;
import irille.shop.usr.UsrProductCategoryDAO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PdtProductDAO {

  public static final Log LOG = new Log(PdtProductDAO.class);

  public static class QueryDetail extends IduOther<Query, PdtProduct> {

    public PdtProduct load(Integer pkey) {
      return PdtProduct.load(PdtProduct.class, pkey);
    }
  }

  /**
   * 产品审核弃审
   * <p>
   * 弃审同时下架产品
   */
  public static PdtProduct verify(boolean verify, Integer pkey) {
    PdtProduct bean = BeanBase.load(PdtProduct.class, pkey);
    bean.stIsVerify(verify);

    if (!verify) {
      bean.stState(OState.OFF);
    }

    bean.upd();
    return bean;
  }

  private static final UsrCartDAO.Query cartQuery = new UsrCartDAO.Query();

  /**
   * @author yingjianhua
   */
  public static class Query extends IduOther<Query, PdtProduct> {

    public List<PdtProduct> listByPurchaseCart(Integer pkey) {
      List<UsrCart> carts = cartQuery.listByPurchase(pkey);
      Map<Integer, PdtProduct> productMap = new HashMap<Integer, PdtProduct>();
      for (UsrCart cart : carts) {
        Integer pid = cart.gtSpec().getProduct();
        if (!productMap.containsKey(pid)) {
          productMap.put(pid, cart.gtSpec().gtProduct());
        }
      }
      return languageShift(new ArrayList<PdtProduct>(productMap.values()));//转
    }

    public List<PdtProduct> listBySpec(List<PdtSpec> specs) {
      if (specs.size() == 0) {
        return new ArrayList<>();
      }
      StringBuilder b = new StringBuilder();
      for (int i = 0; i < specs.size(); i++) {
        if (i != 0) {
          b.append(",");
        }
        b.append(specs.get(i).getProduct());
      }
      String where = PdtProduct.T.PKEY.getFld().getCodeSqlField() + " in (" + b.toString() + ")";
      return languageShift(BeanBase.list(PdtProduct.class, where, false));//转
    }
  }

  public static class Ins extends IduIns<Ins, PdtProduct> {


    @Override
    public void before() {
      super.before();
      getB().setUpdateTime(Env.getTranBeginTime());
      try {
        translateUtil.autoTranslate(getB());//转
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


  /**
   * 商家发布产品
   *
   * @author yingjianhua
   */
  public static class Publish extends IduIns<Publish, PdtProduct> {

    private List<PdtSpec> _lines;

    public List<PdtSpec> getLines() {
      return _lines;
    }

    public void setLines(List<PdtSpec> _lines) {
      this._lines = _lines;
    }

    @Override
    public void before() {
//			super.before();
      PdtProduct dbBean = new PdtProduct().init();
      dbBean.setSupplier(SellerAction.getSupplier().getPkey());
//            dbBean.setSupplier(262);
      dbBean.setMemberLevel(null);
      TranslateFilter translateFilter = new TranslateFilter();
      translateFilter.setLanguageList(new ArrayList<>());
      JsonObject jsonObject = new JsonParser().parse(getB().getName()).getAsJsonObject();
      translateFilter.setMode(0);
      jsonObject.entrySet().forEach(stringJsonElementEntry -> {
        //如果不相等  添加到清单
        if (
            stringJsonElementEntry.getValue().getAsString().length() > 0
        ) {
          translateFilter.getLanguageList()
              .add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
        }
      });
      PropertyUtils.copyProperties(dbBean, getB(),
          T.NAME, //名字
          T.CATEGORY, //产品类目
          T.CATEGORY_DIY, //店铺-产品类目
          T.CODE, //编号
          T.SKU, //sku
          T.PICTURE,//产品图片
          T.NORM_ATTR, //属性
          T.CUR_PRICE, //价格
          T.MIN_OQ,//起订量
          T.MAX_OQ, //最大购买量
          T.STOCK, //库存
          T.WARN_STOCK, //警告库存
          T.STATE,//销售状态上架下架
          T.SOLD_IN_TIME,//定时上架
          T.SOLD_TIME_B,//上架时间开始
          T.SOLD_TIME_E,//上架时间结束
          T.SIZE_ATTR, //规格属性    PdtSize的pkeys
          T.COLOR_ATTR, //颜色属性   PdtColor的pkeys
          T.IS_FREE_SHIPPING, //免运费
          T.WEIGHT, //重量
          T.LENGTH, //长
          T.WIDTH, //宽
          T.HEIGHT, //高
          T.BRIEF_DESCRIPTION, //简短描述
          T.DESCRIPTION, //详细介绍
          T.SEO_DESCRIPTION,
          T.SEO_KEYWORD,
          T.SEO_TITLE
      );
      translateUtil.newAutoTranslate(dbBean, translateFilter);
      getB().setUpdateTime(Env.getTranBeginTime());
      setB(dbBean);

    }

    @Override
    public void valid() {
    }

    @Override
    public void after() {
      super.after();
      for (PdtSpec line : getLines()) {
        translateUtil.autoTranslate(line);
      }
      insLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
    }
  }

  /**
   * 商家发布产品
   *
   * @author yingjianhua
   */
  public static class ExcelLoad extends IduIns<ExcelLoad, PdtProduct> {

    private List<PdtSpec> _lines;

    public List<PdtSpec> getLines() {
      return _lines;
    }

    public void setLines(List<PdtSpec> _lines) {
      this._lines = _lines;
    }

    @Override
    public void before() {
//			super.before();
      PdtProduct dbBean = new PdtProduct().init();
//            dbBean.setSupplier(SellerAction.getSupplier().getPkey());
      dbBean.setSupplier(262);
      dbBean.setMemberLevel(null);
      TranslateFilter translateFilter = new TranslateFilter();
      translateFilter.setLanguageList(new ArrayList<>());
      JsonObject jsonObject = new JsonParser().parse(getB().getName()).getAsJsonObject();
      translateFilter.setMode(0);
      translateUtil.addFilterToGlobalFilter(PdtSpec.class, PdtSpec.T.KEY_NAME);
      jsonObject.entrySet().forEach(stringJsonElementEntry -> {
        //如果不相等  添加到清单
        if (
            stringJsonElementEntry.getValue().getAsString().length() > 0
        ) {
          translateFilter.getLanguageList()
              .add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
        }
      });
      PropertyUtils.copyProperties(dbBean, getB(),
          T.NAME, //名字
          T.CATEGORY, //产品类目
          T.CATEGORY_DIY, //店铺-产品类目
          T.CODE, //编号
          T.SKU, //sku
          T.PICTURE,//产品图片
          T.NORM_ATTR, //属性
          T.CUR_PRICE, //价格
          T.MIN_OQ,//起订量
          T.MAX_OQ, //最大购买量
          T.STOCK, //库存
          T.WARN_STOCK, //警告库存
          T.STATE,//销售状态上架下架
          T.SOLD_IN_TIME,//定时上架
          T.SOLD_TIME_B,//上架时间开始
          T.SOLD_TIME_E,//上架时间结束
          T.SIZE_ATTR, //规格属性    PdtSize的pkeys
          T.COLOR_ATTR, //颜色属性   PdtColor的pkeys
          T.IS_FREE_SHIPPING, //免运费
          T.WEIGHT, //重量
          T.LENGTH, //长
          T.WIDTH, //宽
          T.HEIGHT, //高
          T.BRIEF_DESCRIPTION, //简短描述
          T.DESCRIPTION //详细介绍
      );
      translateUtil.newAutoTranslate(dbBean, translateFilter);

      getB().setUpdateTime(Env.getTranBeginTime());
      setB(dbBean);

    }

    @Override
    public void valid() {
    }

    @Override
    public void after() {
      super.after();
      for (PdtSpec line : getLines()) {
        translateUtil.autoTranslate(line);
      }
      insLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
    }
  }


  public static class Upd extends IduUpd<Upd, PdtProduct> {

    private List<PdtSpec> _lines;

    public List<PdtSpec> getLines() {
      return _lines;
    }

    public void setLines(List<PdtSpec> _lines) {
      this._lines = _lines;
    }

    @Override
    public void before() {

//			super.before();
      PdtProduct dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(),
          T.NAME, //名字
          T.CATEGORY, //产品类目
          T.CATEGORY_DIY, //店铺-产品类目
          T.CODE, //编号
          T.SKU, //sku
          T.PICTURE,//产品图片
          T.NORM_ATTR, //属性
          T.CUR_PRICE, //价格
          T.MIN_OQ,//起订量
          T.MAX_OQ, //最大购买量
          T.STOCK, //库存
          T.WARN_STOCK, //警告库存
          T.STATE,//销售状态上架下架
          T.SOLD_IN_TIME,//定时上架
          T.SOLD_TIME_B,//上架时间开始
          T.SOLD_TIME_E,//上架时间结束
          T.SIZE_ATTR, //规格属性    PdtSize的pkeys
          T.COLOR_ATTR, //颜色属性   PdtColor的pkeys
          T.IS_FREE_SHIPPING, //免运费
          T.WEIGHT, //重量
          T.LENGTH, //长
          T.WIDTH, //宽
          T.HEIGHT, //高
          T.BRIEF_DESCRIPTION, //简短描述
          T.DESCRIPTION //详细介绍
      );

//			dbBean.setSupplier(SellerAction.getSupplier().getPkey());
//			dbBean.setMemberLevel(null);
      try {
        dbBean.setName(dbBean.getName(HomeAction.curLanguage()));//转
        dbBean.setDescription(dbBean.getDescription(HomeAction.curLanguage()));//转
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      getB().setUpdateTime(Env.getTranBeginTime());
//            translateUtil.autoTranslateSaveOrUpdate(dbBean, true, false, null);
//			updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
//			getB().upd();
    }

    public void updateFavorite(int id) {
      String sql =
          "update " + FormaterSql.getTableName(T.PKEY) + " set " + T.Favorite_Count.getFld()
              .getCodeSqlField() + "=" + T.Favorite_Count.getFld().getCodeSqlField() + "+1 where "
              + T.PKEY + " = ?";
      BeanBase.executeUpdate(sql, id);
    }

    public void removeFavorite(int id) {
      String sql =
          "update " + FormaterSql.getTableName(T.PKEY) + " set " + T.Favorite_Count.getFld()
              .getCodeSqlField() + "=" + T.Favorite_Count.getFld().getCodeSqlField() + "-1 where "
              + T.PKEY + " = ?";
      BeanBase.executeUpdate(sql, id);
    }

    @Override
    public void after() {
      super.after();
      for (PdtSpec line : getLines()) {
        line.setProduct(getB().getPkey());

        line.setKeyName(line.gtColor().getName() + "+" + line.gtSize().getName());
        line.setMarkup(null);

        line.setMarkup(BigDecimal.ZERO);
      }
      updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
    }
  }


  /**
   * xiayan
   *
   * @author xinlian
   */
  public static class Upd1 extends IduUpd<Upd1, PdtProduct> {

    private List<PdtSpec> _lines;

    public List<PdtSpec> getLines() {
      return _lines;
    }

    public void setLines(List<PdtSpec> _lines) {
      this._lines = _lines;
    }

    @Override
    public void before() {

//			super.before();
      PdtProduct dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(),
          T.NAME, //名字
          T.CATEGORY, //产品类目
          T.CATEGORY_DIY, //店铺-产品类目
          T.CODE, //编号
          T.SKU, //sku
          T.PICTURE,//产品图片
          T.NORM_ATTR, //属性
          T.CUR_PRICE, //价格
          T.MIN_OQ,//起订量
          T.MAX_OQ, //最大购买量
          T.STOCK, //库存
          T.WARN_STOCK, //警告库存
          T.STATE,//销售状态上架下架
          T.SOLD_IN_TIME,//定时上架
          T.SOLD_TIME_B,//上架时间开始
          T.SOLD_TIME_E,//上架时间结束
          T.SIZE_ATTR, //规格属性    PdtSize的pkeys
          T.COLOR_ATTR, //颜色属性   PdtColor的pkeys
          T.IS_FREE_SHIPPING, //免运费
          T.WEIGHT, //重量
          T.LENGTH, //长
          T.WIDTH, //宽
          T.HEIGHT, //高
          T.BRIEF_DESCRIPTION, //简短描述
          T.DESCRIPTION //详细介绍
      );

//			dbBean.setSupplier(SellerAction.getSupplier().getPkey());
//			dbBean.setMemberLevel(null);
      setB(dbBean);
      getB().setUpdateTime(Env.getTranBeginTime());
//			updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
//			getB().upd();
    }

  }

  /**
   * xiayan
   *
   * @author xinlian
   */
  public static class Upd2 extends IduUpd<Upd2, PdtProduct> {

    private List<PdtSpec> _lines;

    public List<PdtSpec> getLines() {
      return _lines;
    }

    public void setLines(List<PdtSpec> _lines) {
      this._lines = _lines;
    }

    @Override
    public void before() {
      PdtProduct dbBean = load(getB().getPkey());
      TranslateFilter translateFilter = new TranslateFilter();
      translateFilter.setLanguageList(new ArrayList<>());
      JsonObject dbJson = new JsonParser().parse(dbBean.getName()).getAsJsonObject();
      JsonObject jsonObject = new JsonParser().parse(getB().getName()).getAsJsonObject();
      jsonObject.addProperty(FldLanguage.Language.en.name(),
          SEOUtils.firstUpperCase(jsonObject.get(FldLanguage.Language.en.name()).getAsString()));
      if (
          dbJson.get(FldLanguage.Language.en.name()).getAsString().hashCode()
              !=
              jsonObject.get(FldLanguage.Language.en.name()).getAsString().hashCode()) {
        translateFilter.setMode(0);
        //基准发生修改 用黑名单模式,修改的字段不翻译
        jsonObject.entrySet().forEach(stringJsonElementEntry -> {
          //如果不相等  添加到清单
          if (
              dbJson.get(stringJsonElementEntry.getKey()).getAsString().hashCode()
                  !=
                  (stringJsonElementEntry.getValue().getAsString()).hashCode()
          ) {
            translateFilter.getLanguageList()
                .add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
          }
        });
      } else {
        translateFilter.setMode(2);
        jsonObject.entrySet().forEach(stringJsonElementEntry -> {
          if (stringJsonElementEntry.getValue().getAsString().length() < 1) {
            translateFilter.getLanguageList()
                .add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
          }
        });
      }

      PropertyUtils.copyProperties(dbBean, getB(),
          T.NAME, //名字
          T.CATEGORY, //产品类目
          T.CATEGORY_DIY, //店铺-产品类目
          T.CODE, //编号
          T.SKU, //sku
          T.PICTURE,//产品图片
          T.NORM_ATTR, //属性
          T.CUR_PRICE, //价格
          T.MIN_OQ,//起订量
          T.MAX_OQ, //最大购买量
          T.STOCK, //库存
          T.WARN_STOCK, //警告库存
          T.STATE,//销售状态上架下架
          T.SOLD_IN_TIME,//定时上架
          T.SOLD_TIME_B,//上架时间开始
          T.SOLD_TIME_E,//上架时间结束
          T.SIZE_ATTR, //规格属性    PdtSize的pkeys
          T.COLOR_ATTR, //颜色属性   PdtColor的pkeys
          T.IS_FREE_SHIPPING, //免运费
          T.WEIGHT, //重量
          T.LENGTH, //长
          T.WIDTH, //宽
          T.HEIGHT, //高
          T.BRIEF_DESCRIPTION, //简短描述,
          T.DESCRIPTION,//简短描述
          T.SEO_DESCRIPTION,
          T.SEO_KEYWORD,
          T.SEO_TITLE
      );
      setB(translateUtil.newAutoTranslate(dbBean, translateFilter));
      getLines().forEach(pdtSpec -> {
        pdtSpec.setRowVersion((short) 0);
        translateUtil.autoTranslate(pdtSpec);
      });
      updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());

    }

    @Override
    public void valid() {
    }
  }


  public static class DelDetails extends IduOther<DelDetails, PdtProduct> {

    public void before() {
      getB().setState(Pdt.OState.DELETE.getLine().getKey());
    }

    public void run() {
      getB().upd();
    }

  }

  /**
   * xiayan List<PdtProduct> 查询时多国语言转换
   */
  public static List<PdtProduct> languageShift(List<PdtProduct> _productList) {//转
    for (int i = 0; i < _productList.size(); i++) {
      try {
        _productList.get(i).setName(_productList.get(i).getName(HomeAction.curLanguage()));
        _productList.get(i)
            .setDescription(_productList.get(i).getDescription(HomeAction.curLanguage()));
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return _productList;
  }

  public static class Select extends IduOther<Select, PdtProduct> {

    /**
     * 获取店铺产品页产品(条件:/对应商家/上架/通过审核/普通及采集产品 (搜索条件:/页码/店铺分类) (排序条件)
     *
     * @param lang 语言
     * @param supplier 供应商pkey
     * @param start 查询起始位置
     * @param limit 查询记录数
     * @param cat 商家产品分类
     * @param sort 排序依据		1->热卖	2->收藏数 3->新品 4->产品价格 默认审核时间
     * @param orderType 排序方式 	1->ASC 默认->DESC
     */
    public static Map getProductBySup(FldLanguage.Language lang, Serializable supplier,
        Integer start, Integer limit, Integer cat, Integer sort, Integer orderType) {
      Map map = new HashMap();
      SQL sql = new SQL() {{
        SELECT(T.PKEY, T.PICTURE, T.NAME, T.CUR_PRICE, T.IS_HOT)
            .FROM(PdtProduct.class)
            .WHERE(T.SUPPLIER, "=?", supplier)
            .WHERE(T.STATE, "=?", Pdt.OState.ON)
            .WHERE(T.IS_VERIFY, "=?", Sys.OYn.YES)
            .WHERE(T.PRODUCT_TYPE, "<>?", Pdt.OProductType.GROUP);
      }};
      if (cat != -1) {
        sql.WHERE(T.CATEGORY_DIY,
            "in(" + UsrProductCategoryDAO.Sellect.getAllChild(lang, supplier, cat) + ")");
      }
      map.put("pageAll", Math.ceil((double) irille.pub.bean.Query.sql(sql).queryCount() / limit));
      String rankingBasis = " DESC ";
      if (orderType == 1) {
        rankingBasis = " ASC ";
      }
      switch (sort) {
        case 1:
          sql.ORDER_BY(T.SALES, rankingBasis);
          break;
        case 2:
          sql.ORDER_BY(T.Favorite_Count, rankingBasis);
          break;
        case 3:
          sql.ORDER_BY(T.IS_NEW, rankingBasis);
          break;
        case 4:
          sql.ORDER_BY(T.CUR_PRICE, rankingBasis);
          break;
        default:
          sql.ORDER_BY(T.IS_HOT,
              rankingBasis.trim().toLowerCase().equals("desc") ? " ASC " : " DESC ");
          break;
      }
      sql.ORDER_BY(T.PKEY, rankingBasis);
      sql.LIMIT(start, limit);
      map.put("items", irille.pub.bean.Query.sql(sql).queryList(PdtProduct.class)
          .stream()
          .map(bean -> new PdtView() {{
            String name = bean.getName();
            translateUtil.getAutoTranslate(bean, lang);
            setPdt(bean);
            setRewrite(bean.getPkey(), name);
          }}).collect(Collectors.toList()));
      return map;
    }

    /**
     * 获取店铺首页产品(条件:/对应商家/新品/上架/通过审核/首页显示/普通产品
     */
    public static List<PdtView> getIndexProduct(Integer supplier, FldLanguage.Language lang) {
      SQL sql = new SQL() {{
        SELECT(T.PKEY, T.PICTURE, T.NAME, T.CUR_PRICE)
            .FROM(PdtProduct.class)
            .WHERE(T.SUPPLIER, "=?", supplier)
//                        .WHERE(T.IS_NEW, "=?", Sys.OYn.YES)
            .WHERE(T.STATE, "=?", Pdt.OState.ON)
            .WHERE(T.IS_INDEX, "=?", Sys.OYn.YES)
            .WHERE(T.IS_VERIFY, "=?", Sys.OYn.YES)
            .WHERE(T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL)
            .ORDER_BY(T.UPDATE_TIME, "DESC")
            .LIMIT(0, 8);
      }};

      return irille.pub.bean.Query.sql(sql).queryList(PdtProduct.class)
          .stream()
          .map(bean -> new PdtView() {{
            String name = bean.getName();
            translateUtil.getAutoTranslate(bean, lang);
            setPdt(bean);
            setRewrite(bean.getPkey(), name);
          }}).collect(Collectors.toList());

    }
  }

}
