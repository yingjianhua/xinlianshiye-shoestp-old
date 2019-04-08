package irille.Service.Manage.O2O.Imp;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;

import irille.Dao.PdtCatDao;
import irille.Dao.O2O.O2OActivityDao;
import irille.Dao.O2O.O2OProductDao;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_JoinInfo;
import irille.Entity.O2O.O2O_Map;
import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.O2O.Enums.O2O_ActivityStatus;
import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.Entity.pm.PM.OTempType;
import irille.Service.Manage.O2O.O2OActivityService;
import irille.pub.bean.BeanBase;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.FldLanguage;
import irille.pub.util.AppConfig;
import irille.pub.util.EmailUtils;
import irille.pub.util.GetValue;
import irille.pub.util.SEOUtils;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.O2O.O2OActivityView;
import irille.view.O2O.O2OProductView;
import irille.view.O2O.PdtSearchView;
import irille.view.se.sendEmail;

public class O2OActivityServiceImpl implements O2OActivityService {

  @Inject private O2OActivityDao o2OActivityDao;
  @Inject private O2OProductDao o2OProductDao;
  @Inject private PdtCatDao pdtCatDao;

  @Inject private IPMMessageService messageService;

  @Override
  public Page<O2OActivityView> list(Integer start, Integer limit, O2OActivityView condition) {
    Page<O2OActivityView> page = o2OActivityDao.pageView(start, limit, condition);

    Map<Integer, String> cat_pkey_name_map = new HashMap<>();
    page.getItems()
        .forEach(
            view -> {
              List<Map<String, Object>> activityCat =
                  activitCat_pkey2Name(view.getActivityCat(), cat_pkey_name_map);
              view.setCatList(activityCat);
            });

    return page;
  }

  /**
   * 把以逗号分隔的产品分类pkey,转换成以分类名称分隔的字符串
   *
   * @param activityCat 以逗号分隔的分类的pkey
   * @param cat_pkey_name_map pkey和分类名称键值对, 若不存在相应数据 则从数据库中查询后 放入map
   * @return 以逗号分隔的分类名称
   * @author Jianhua Ying
   */
  private List<Map<String, Object>> activitCat_pkey2Name(
      String activityCat, Map<Integer, String> cat_pkey_name_map) {
    List<Map<String, Object>> array = new ArrayList<Map<String, Object>>();

    if (activityCat == null || activityCat.trim().equals("")) {
      Map<String, Object> map = new HashMap<String, Object>();
      for (FldLanguage.Language language : FldLanguage.Language.values()) {
        map.put(language.name(), "通用");
      }
      array.add(map);
      return array;
    } else {
      Stream.of(activityCat.split(","))
          .forEach(
              spkey -> {
                Integer pkey = Integer.parseInt(spkey);
                Map<String, Object> map = new HashMap<String, Object>();
                if (cat_pkey_name_map != null && cat_pkey_name_map.containsKey(pkey)) {
                  JSONObject json = null;
                  try {
                    json = new JSONObject(cat_pkey_name_map.get(pkey));
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                  if (null != json) {
                    Iterator<String> ite = json.keys();
                    while (ite.hasNext()) {
                      String key = ite.next();
                      try {
                        map.put(key, json.get(key));
                      } catch (JSONException e) {
                        e.printStackTrace();
                      }
                    }
                    array.add(map);
                  }

                } else {
                  PdtCat cat = pdtCatDao.findById(pkey);
                  if (cat_pkey_name_map != null) cat_pkey_name_map.put(pkey, cat.getName());
                  JSONObject json = null;
                  try {
                    json = new JSONObject(cat.getName());
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                  if (null != json) {
                    Iterator<String> ite = json.keys();
                    while (ite.hasNext()) {
                      String key = ite.next();
                      try {
                        map.put(key, json.get(key));
                      } catch (JSONException e) {
                        e.printStackTrace();
                      }
                    }
                    array.add(map);
                  }
                }
              });
      return array;
    }
  }

  @Override
  public void cancel(Integer pkey) {
    if (pkey == null) {
      throw new WebMessageException(ReturnCode.valid_notnull, "请选中活动");
    }
    O2O_Activity bean = o2OActivityDao.findById(pkey);
    if (bean == null) {
      throw new WebMessageException(ReturnCode.service_gone, "活动不存在");
    }
    if (!bean.gtStatus().equals(O2O_ActivityStatus.TOBEGIN)) {
      throw new WebMessageException(ReturnCode.service_state_error, "活动已经开始或者已经完成,不能关闭");
    }
    if (o2OProductDao.countByActivity(pkey) > 0) {
      throw new WebMessageException(ReturnCode.service_unknow, "已有商品报名参加活动,不能关闭");
    }
    bean.stStatus(O2O_ActivityStatus.CLOSE);
    bean.upd();
  }

  /** 发布活动 */
  @Override
  public void deploy(O2OActivityView view) {
    valid(view);
    O2O_Activity activity = null;
    if (null != view.getPkey()) {
      activity = o2OActivityDao.findById(view.getPkey());
      if (activity.getStatus().equals(O2O_ActivityStatus.ACTIVITY.getLine().getKey()))
        throw new WebMessageException(ReturnCode.failure, "无法编辑进行中的活动");
      if (activity.getStatus().equals(O2O_ActivityStatus.CLOSE.getLine().getKey()))
        throw new WebMessageException(ReturnCode.failure, "无法编辑已关闭的活动");
    } else {
      activity = new O2O_Activity();
      Date now = new Date();
      if (now.after(view.getStartDate()) && now.before(view.getEndDate())) {
        activity.setStatus(O2O_ActivityStatus.ACTIVITY.getLine().getKey());
      } else if (now.before(view.getStartDate())) {
        activity.setStatus(O2O_ActivityStatus.TOBEGIN.getLine().getKey());
      } else if (now.after(view.getEndDate())) {
        activity.setStatus(O2O_ActivityStatus.END.getLine().getKey());
      }
    }
    activity.setName(view.getName());
    activity.setAddress(view.getAddr());
    activity.setActivityCat(view.getActivityCat());
    activity.setRules(view.getRules());
    activity.setStartDate(new Date(view.getStartTime()));
    activity.setEndDate(new Date(view.getEndTime()));
    activity.setUpdatedTime(new Date());
    if (null != view.getPkey()) {
      activity.upd();
    } else {
      activity.ins();
    }
  }

  /**
   * 活动新增和编辑时进行字段的校验
   *
   * @param view 页面传值
   * @author Jianhua Ying
   */
  private void valid(O2OActivityView view) {
    // 活动名称
    if (view.getName() == null || view.getName().trim().equals("")) {
      throw new WebMessageException(ReturnCode.valid_notblank, "请填写活动名称");
    }
    if (view.getName().length() > 20) {
      throw new WebMessageException(ReturnCode.valid_toolong, "活动名称不能超过20个字符");
    }

    // 活动分类
    if (view.getActivityCat() == null || view.getActivityCat().trim().equals("")) {
      throw new WebMessageException(ReturnCode.failure, "请选择活动产品分类");
    }
    if ("-1".equals(view.getActivityCat())) {
      view.setActivityCat(null);
    } else {
      List<PdtCat> cats =
          BeanBase.list(
              PdtCat.class,
              PdtCat.T.PKEY.getFld().getCodeSqlField() + " IN (?) ",
              false,
              view.getActivityCat());
      if (cats.size() != view.getActivityCat().split(",").length) {
        throw new WebMessageException(ReturnCode.service_wrong_data, "分类数据不存在");
      }
      for (PdtCat c : cats) {
        if (null != c.getCategoryUp()) {
          throw new WebMessageException(ReturnCode.service_unknow, "只能为一级分类发布活动");
        }
      }
      view.setActivityCat(
          Stream.of(view.getActivityCat().split(","))
              .map(
                  spkey -> {
                    return Integer.parseInt(spkey);
                  })
              .collect(Collectors.toSet()).stream()
              .map(
                  pkey -> {
                    return String.valueOf(pkey);
                  })
              .collect(Collectors.joining(",")));
    }

    // 活动起始时间
    if (view.getStartDate() == null) {
      throw new WebMessageException(ReturnCode.valid_notnull, "请填写起始时间");
    } else {
      // 清除时分秒
      LocalDate startDate =
          view.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      view.setStartDate(java.sql.Date.valueOf(startDate));
    }

    // 活动国家/地区
    if (view.getAddr() == null) {
      throw new WebMessageException(ReturnCode.valid_notnull, "请填写活动国家/地区");
    }
    O2O_Map map = BeanBase.chk(O2O_Map.class, view.getAddr());
    if (null == map) {
      throw new WebMessageException(
          ReturnCode.service_wrong_data, "主键为【" + view.getAddr() + "】的地址不存在");
    }

    // 活动截止时间
    if (view.getEndDate() == null) {
      throw new WebMessageException(ReturnCode.valid_notnull, "请填写截止时间");
    } else {
      // 清除时分秒
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      try {
        view.setEndDate(sdf.parse(sdf.format(view.getEndDate())));
      } catch (ParseException e) {
        throw new WebMessageException(ReturnCode.failure, "日期格式异常");
      }
    }
    if (view.getStartDate().before(new Date())) {
      throw new WebMessageException(ReturnCode.failure, "起始时间不能早于系统时间");
    }
    if (view.getStartDate().after(view.getEndDate())) {
      throw new WebMessageException(ReturnCode.failure, "起始时间不能晚于结束时间");
    }
    // 活动规则
    if (view.getRules() == null || view.getRules().trim().equals("")) {
      throw new WebMessageException(ReturnCode.valid_notblank, "请填写活动规则");
    }
    // 暂时不限定字数
    try {
      JSONObject rule = new JSONObject(view.getRules());
      if (!rule.has(FldLanguage.Language.zh_CN.name())) {
        throw new WebMessageException(ReturnCode.service_wrong_data, "请填写中文规则");
      }
      String r = String.valueOf(rule.get(FldLanguage.Language.zh_CN.name()));
      if ("".equals(r.trim())) {
        throw new WebMessageException(ReturnCode.service_wrong_data, "请填写中文规则");
      }
      if (r.length() > 800) {
        throw new WebMessageException(ReturnCode.valid_toolong, "规则限制800字");
      }

    } catch (JSONException e) {
      throw new WebMessageException(ReturnCode.service_wrong_data, "数据格式异常");
    }
  }

  /** 查看报名列表 */
  public Page<O2OProductView> enrollList(
      PdtSearchView search, Integer start, Integer limit, Integer type) {
    List<O2OProductView> items =
        o2OProductDao.enrollList(search, start, limit, type).stream()
            .map(
                map -> {
                  O2OProductView item = new O2OProductView();
                  item.setPkey(GetValue.get(map, O2O_Product.T.PKEY, Integer.class, null));
                  item.setStatus(
                      GetValue.get(map, O2O_Product.T.VERIFY_STATUS, Byte.class, (byte) 0));
                  item.setActAddress(GetValue.get(map, "mapName", String.class, null));
                  item.setActName(GetValue.get(map, O2O_Activity.T.NAME, String.class, null));
                  String pdtName = GetValue.get(map, "pdtName", String.class, null);
                  item.setProductName(pdtName);
                  item.setProductCat(GetValue.get(map, "catName", String.class, null));
                  item.setPrice(
                      GetValue.get(map, O2O_Product.T.PRICE, BigDecimal.class, BigDecimal.ZERO));
                  item.setMinOq(GetValue.get(map, O2O_Product.T.MIN_OQ, Integer.class, null));
                  item.setSupplierName(GetValue.get(map, "supName", String.class, null));
                  item.setSupplierLevel(GetValue.get(map, "roleName", String.class, null));
                  item.setContact(GetValue.get(map, "joinInfo", String.class, null));
                  item.setImage(
                      GetValue.getFirstImage(GetValue.get(map, "image", String.class, "")));
                  item.setMobile(GetValue.get(map, O2O_JoinInfo.T.Tel, String.class, null));
                  Integer pdtPkey = GetValue.get(map, "pdtPkey", Integer.class, -1);
                  item.setRewriter(SEOUtils.getPdtProductTitle(pdtPkey, pdtName));
                  String name = null;
                  for (SVSGradeType g : SVSGradeType.values()) {
                    Byte gid = GetValue.get(map, "grade", Byte.class, null);
                    if (null != gid) {
                      if (gid.equals(g.getLine().getKey())) {
                        name = g.getLine().getName();
                        break;
                      }
                    } else {
                      name = SVSGradeType.NotAvailable.getLine().getName();
                    }
                  }
                  item.setGrade(name);
                  item.setState(GetValue.get(map, O2O_Product.T.STATUS, Byte.class, null));
                  item.setMessage(GetValue.get(map, O2O_Product.T.REMARK, String.class, null));
                  return item;
                })
            .collect(Collectors.toList());
    return new Page<O2OProductView>(items, start, limit, o2OProductDao.countEnroll(search, type));
  }

  /** 审核报名 */
  public void appr(Integer id, String reason, O2O_ProductStatus status) {
    O2O_Product o2OProduct = o2OProductDao.findByPkey(id);
    if (null == o2OProduct) throw new WebMessageException(ReturnCode.failure, "商品不存在");
    if (!o2OProduct.getVerifyStatus().equals(O2O_ProductStatus._DEFAULT.getLine().getKey())) {
      throw new WebMessageException(ReturnCode.failure, "该商品已被审核");
    }
    o2OProduct.setVerifyStatus(status.getLine().getKey());
    sendEmail email = new sendEmail();
    if (!AppConfig.dev) {
      UsrSupplier supplier = o2OProduct.gtJoinInfoId().gtSupplier();
      email.setReceiver(supplier.getEmail());
    }
    if (status == O2O_ProductStatus.Failed) {
      if (null == reason) throw new WebMessageException(ReturnCode.failure, "请输入拒绝理由");
      o2OProduct.setMessage(reason);
      email.setSubject("【鞋贸港】O2O商品审核拒绝");
      email.setContent("您申请的商品已被审核拒绝，拒绝理由：" + reason);
    } else {
      PdtProduct pdt = o2OProduct.gtProductId();
      pdt.stProductType(Pdt.OProductType.O2O);
      pdt.upd();
      email.setSubject("【鞋贸港】O2O商品审核通过");
      email.setContent("您申请商品编号为【" + pdt.getCode() + "】的商品已通过审核");
    }
    o2OProduct.upd();
    try {
      EmailUtils.sendMail(email);
    } catch (IOException e) {
      e.printStackTrace();
    }
    UsrSupplier supplier = o2OProduct.gtJoinInfoId().gtSupplier();
    messageService.send(
        OTempType.O2O_PROD_APPR_NOTICE,
        supplier,
        null,
        supplier,
        o2OProduct,
        o2OProduct.gtProductId());
  }

  /** 私人展厅审核产品 */
  public void privatefindAppr(Integer id, String reason, O2O_PrivateExpoPdtStatus status) {
    O2O_PrivateExpoPdt o2O_privateExpoPdt = o2OProductDao.privatefindByPkey(id);
    if (null == o2O_privateExpoPdt) throw new WebMessageException(ReturnCode.failure, "商品不存在");
    o2O_privateExpoPdt.setVerifyStatus(status.getLine().getKey());
    sendEmail email = new sendEmail();
    if (!AppConfig.dev) {
      email.setReceiver(o2O_privateExpoPdt.gtPdtId().gtSupplier().getEmail());
    }
    if (status.getLine().getKey() == O2O_ProductStatus.Failed.getLine().getKey()) {
      if (null == reason) throw new WebMessageException(ReturnCode.failure, "请输入拒绝理由");
      o2O_privateExpoPdt.setMessage(reason);
      o2O_privateExpoPdt.stStatus(O2O_PrivateExpoPdtStatus.OFF);
      email.setSubject("【鞋贸港】私人展厅商品审核拒绝");
      email.setContent("您申请的商品已被审核拒绝，拒绝理由：" + reason);
    } else {
      PdtProduct pdt = o2O_privateExpoPdt.gtPdtId();
      pdt.stProductType(Pdt.OProductType.PrivateExpo);
      o2O_privateExpoPdt.stStatus(O2O_PrivateExpoPdtStatus.ON);
      pdt.upd();
      email.setSubject("【鞋贸港】私人展厅商品审核通过");
      email.setContent("您申请商品编号为【" + pdt.getCode() + "】的商品已通过审核");
    }
    o2O_privateExpoPdt.upd();
    try {
      EmailUtils.sendMail(email);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** 处理上下架 */
  public void lowerAndUpper(Integer id, String reason, O2O_ProductStatus status) {
    O2O_Product o2OProduct = o2OProductDao.findByPkey(id);
    if (null == o2OProduct) throw new WebMessageException(ReturnCode.failure, "商品不存在");
    if (!o2OProduct.getStatus().equals(O2O_ProductStatus.WAITOFF.getLine().getKey())) {
      throw new WebMessageException(ReturnCode.failure, "该条信息已被处理");
    }
    sendEmail email = new sendEmail();
    UsrSupplier supplier = o2OProduct.gtJoinInfoId().gtSupplier();
    if (!AppConfig.dev) {
      email.setReceiver(supplier.getEmail());
    }
    o2OProduct.setStatus(status.getLine().getKey());
    PdtProduct pdt = o2OProduct.gtProductId();
    if (status == O2O_ProductStatus.Failed) {
      if (null == reason) {
        throw new WebMessageException(ReturnCode.failure, "拒绝理由不能为空");
      }
      o2OProduct.setUpdatedTime(new Date());
      o2OProduct.setMessage("拒绝申请下架，拒绝理由：" + reason);
      email.setSubject("【鞋贸港】O2O商品下架失败");
      email.setContent("您申请商品编号为【" + pdt.getCode() + "】的商品拒绝下架，拒绝理由：" + reason);
      o2OProduct.upd();
    } else if (status.equals(O2O_ProductStatus.PASS)) {
      List<O2O_Product> o2oProds = o2OProductDao.findAllByProd(o2OProduct.getProductId());
      if (!(null != o2oProds && o2oProds.size() > 0)) {
        PdtProduct product = o2OProduct.gtProductId();
        product.setProductType(Pdt.OProductType.GENERAL.getLine().getKey());
        product.upd();
      }
      o2OProduct.del();
      email.setSubject("【鞋贸港】O2O商品下架成功");
      email.setContent("您申请商品编号为【" + pdt.getCode() + "】的商品下架成功");
    }

    try {
      EmailUtils.sendMail(email);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** 私人订购会商品处理上下架 */
  public void privateLowerAndUpper(Integer id, String reason, O2O_PrivateExpoPdtStatus status) {
    O2O_PrivateExpoPdt o2OProduct = o2OProductDao.privatefindByPkey(id);
    sendEmail email = new sendEmail();
    if (null == o2OProduct) throw new WebMessageException(ReturnCode.failure, "商品不存在");
    o2OProduct.setStatus(status.getLine().getKey());
    PdtProduct pdt = o2OProduct.gtPdtId();
    if (!AppConfig.dev) {
      email.setReceiver(pdt.gtSupplier().getEmail());
    }
    if (!status.equals(O2O_PrivateExpoPdtStatus.OFF)) {
      if (null == reason) {
        throw new WebMessageException(ReturnCode.failure, "拒绝理由不能为空");
      }
      o2OProduct.setMessage(reason);
      email.setSubject("【鞋贸港】私人订购会商品下架失败");
      email.setContent("您申请商品编号为【" + pdt.getCode() + "】的商品拒绝下架，拒绝理由：" + reason);
    } else {
      email.setSubject("【鞋贸港】私人订购会商品下架成功");
      email.setContent("您申请商品编号为【" + pdt.getCode() + "】的商品下架成功");
    }
    o2OProduct.setVerifyStatus(O2O_PrivateExpoPdtStatus.Failed.getLine().getKey());
    o2OProduct.upd();
    try {
      EmailUtils.sendMail(email);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public O2OActivityView load(Integer pkey) {
    O2O_Activity activity = o2OActivityDao.getActivityInfoById(pkey);
    O2OActivityView view = new O2OActivityView();
    view.setPkey(activity.getPkey());
    view.setName(activity.getName());
    view.setActivityCat(activity.getActivityCat());
    view.setStartDate(activity.getStartDate());
    view.setEndDate(activity.getEndDate());
    view.setAddr(activity.getAddress());
    view.setRules(activity.getRules());
    return view;
  }

  /**
   * @Description: 私人展会商品列表
   *
   * @date 2019/2/1 23:10
   * @author lijie@shoestp.cn
   */
  @Override
  public Page priveteList(
      int start,
      int limit,
      Integer status,
      Integer verify_status,
      String cat,
      String supName,
      String pdtName) {
    List<Map<String, Object>> maps =
        o2OActivityDao.privetePdtList(start, limit, status, verify_status, cat, supName, pdtName);
    List<O2OProductView> result = new ArrayList<>();
    for (Map<String, Object> map : maps) {
      O2OProductView o2OProductView = new O2OProductView();
      o2OProductView.setId(GetValue.get(map, O2O_PrivateExpoPdt.T.PKEY, Integer.class, -1));
      o2OProductView.setPkey(GetValue.get(map, PdtProduct.T.PKEY, Integer.class, -1));
      o2OProductView.setStatus(
          GetValue.get(map, O2O_PrivateExpoPdt.T.VERIFY_STATUS, Byte.class, (byte) 0));
      o2OProductView.setState(GetValue.get(map, O2O_PrivateExpoPdt.T.STATUS, Byte.class, (byte) 0));
      o2OProductView.setProductName(GetValue.get(map, PdtProduct.T.NAME, String.class, ""));
      o2OProductView.setPrice(
          GetValue.get(map, PdtProduct.T.CUR_PRICE, BigDecimal.class, BigDecimal.ZERO));
      o2OProductView.setMkt_price(
          GetValue.get(map, PdtProduct.T.MKT_PRICE, BigDecimal.class, BigDecimal.ZERO));
      o2OProductView.setMinOq(GetValue.get(map, PdtProduct.T.MIN_OQ, Integer.class, -1));
      o2OProductView.setRewriter(
          SEOUtils.getPdtProductTitle(o2OProductView.getPkey(), o2OProductView.getProductName()));
      o2OProductView.setImage(
          GetValue.getFirstImage(GetValue.get(map, PdtProduct.T.PICTURE, String.class, null)));
      o2OProductView.setSku(
          GetValue.getFirstImage(GetValue.get(map, PdtProduct.T.SKU, String.class, null)));
      o2OProductView.setStock(GetValue.get(map, PdtProduct.T.STOCK, Integer.class, null));
      o2OProductView.setStock(GetValue.get(map, PdtProduct.T.STOCK, Integer.class, null));
      o2OProductView.setUpdate_date(GetValue.get(map, PdtProduct.T.UPDATE_TIME, Date.class, null));
      o2OProductView.setSupplierName(GetValue.get(map, "supName", String.class, null));
      o2OProductView.setProductCat(GetValue.get(map, "catName", String.class, ""));
      o2OProductView.setSupCat(GetValue.get(map, "SuppdtCat", String.class, null));
      result.add(o2OProductView);
    }
    return new Page(
        result,
        start,
        limit,
        o2OActivityDao.privetePdtListCount(status, verify_status, cat, supName, pdtName));
  }
}
