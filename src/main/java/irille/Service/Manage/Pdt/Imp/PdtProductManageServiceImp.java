package irille.Service.Manage.Pdt.Imp;

import static irille.pub.util.AppConfig.objectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.gson.JsonObject;

import irille.Dao.PdtProductDao;
import irille.Dao.O2O.O2OProductDao;
import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.SVS.SVSNewestPdt;
import irille.Service.Manage.Pdt.IPdtProductManageService;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.svr.DbPool;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.BatchUtils;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtColorDAO;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import irille.shop.pdt.PdtSpec;
import irille.shop.pdt.PdtTieredPricing;
import irille.shop.pdt.PdtTieredPricingDao;
import irille.shop.usr.UsrProductCategory;
import irille.view.Page;
import irille.view.pdt.PdtProductCatView;
import irille.view.pdt.PdtProductSaveView;
import irille.view.pdt.PdtTieredPricingView;
import irille.view.pdt.WarehouseView;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/7 Time: 15:55 */
public class PdtProductManageServiceImp implements IPdtProductManageService, Job {

  private final Log LOG = new Log(PdtProductManageServiceImp.class);

  @Inject PdtProductDao pdtProductDao;

  private O2OProductDao o2oProductDao = new O2OProductDao();

  @Inject private PdtProductDAO.Publish pdtSave;
  @Inject private PdtProductDAO.Upd2 pdtUpdate;

  @Override
  public Page getProductList(
      String name,
      String number,
      Integer supplierId,
      int cat,
      int start,
      int limit,
      Integer search) {
    return pdtProductDao.getProductListManage(name, number, supplierId, cat, start, limit, search);
  }

  @Override
  public List<PdtProductCatView> getCatChildNodesByCatId(
      int i, FldLanguage.Language supplierLanguage) {
    return pdtProductDao.getCatChildNodesByCatId(i, supplierLanguage);
  }

  @Override
  public Integer saveProduct(String data, Integer supId) throws IOException, ExecutionException {
    PdtProductSaveView pdtProductSaveView = objectMapper.readValue(data, PdtProductSaveView.class);
    PdtProduct pdtProduct = new PdtProduct();
    if (pdtProductSaveView.getId() > 0) {
      PdtProduct prod = pdtProductDao.findByPkey(pdtProductSaveView.getId());
      if (prod.getProductType().equals(Pdt.OProductType.O2O.getLine().getKey())) {
        List<O2O_Product> o2oProds = o2oProductDao.findAllByProd_Pkey(prod.getPkey());
        if (null != o2oProds && o2oProds.size() > 0) {
          boolean flag = false;
          for (O2O_Product o2oProd : o2oProds) {
            if (!o2oProd.getStatus().equals(O2O_ProductStatus.PASS.getLine().getKey())) {
              flag = true;
              break;
            }
          }
          if (flag) {
            throw new WebMessageException(ReturnCode.failure, "O2O商品无法编辑");
          }
        }
      }
      if (!prod.getSupplier().equals(supId)) {
        throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
      }
    }
    List<PdtTieredPricing> tieredPricing = new ArrayList<>();
    List<BigDecimal> minPriceList = new ArrayList<>();
    List<Integer> countList = new ArrayList<>();
    if (pdtProductSaveView.getTieredPricing() != null
        && !pdtProductSaveView.getTieredPricing().isEmpty()) {
      for (PdtTieredPricingView item : pdtProductSaveView.getTieredPricing()) {
        PdtTieredPricing pdtTP = new PdtTieredPricing();
        pdtTP.setMinOq(item.getCount());
        pdtTP.setCurPrice(item.getPrice());
        pdtTP.setMain(item.getType() == 1 ? OYn.YES.getLine().getKey() : OYn.NO.getLine().getKey());
        pdtTP.setDeleted(OYn.NO.getLine().getKey());
        minPriceList.add(item.getPrice());
        countList.add(item.getCount());
        if (pdtProductSaveView.getId() > 0) {
          pdtTP.setProduct(pdtProductSaveView.getId());
        }
        pdtTP.setRowVersion((short) 0);
        tieredPricing.add(pdtTP);
      }
    } else {
      return -2;
    }
    BigDecimal min = Collections.min(minPriceList); // 最小价格
    Set<Integer> colorSet = new HashSet<>(); // 颜色列表
    Set<Integer> sizeSet = new HashSet<>(); // 尺码列表
    int countStock = 0; // 总库存
    List<PdtSpec> list = new ArrayList<>();
    // 优先新增颜色
    if (pdtProductSaveView.getNewSpec() != null && !pdtProductSaveView.getNewSpec().isEmpty()) {
      for (int i = 0; i < pdtProductSaveView.getNewSpec().size(); i++) {
        if (pdtProductSaveView.getSpecColor() != null
            && !pdtProductSaveView.getSpecColor().isEmpty()) {
          if (!pdtProductSaveView
                  .getSpecColor()
                  .contains(pdtProductSaveView.getNewSpec().get(i).getColor())
              && pdtProductSaveView.getNewSpec().get(i).getColor() > 0) {
            PdtColorDAO.delColor(pdtProductSaveView.getNewSpec().get(i).getColor());
          }
        }
        PdtSpec spec = new PdtSpec();
        if (pdtProductSaveView.getNewSpec().get(i).getId() > 0) {
          spec.setPkey(pdtProductSaveView.getNewSpec().get(i).getId());
        }
        if (pdtProductSaveView.getId() <= 0) {
          if (pdtProductSaveView.getNewSpec().get(i).getColorType() == 0) {
            PdtColor color = new PdtColor();
            color.setName(pdtProductSaveView.getNewSpec().get(i).getColorName());
            color.setPicture(pdtProductSaveView.getNewSpec().get(i).getColorImg());
            PdtColor insColor = PdtColorDAO.insColor(color, supId);
            pdtProductSaveView.getNewSpec().get(i).setColor(insColor.getPkey());
            pdtProductSaveView.getNewSpec().get(i).setColorName(insColor.getName());
          }
          colorSet.add(pdtProductSaveView.getNewSpec().get(i).getColor());
        } else {
          spec.setProduct(pdtProductSaveView.getId());
          if (pdtProductSaveView.getNewSpec().get(i).getColor() <= 0) {
            PdtColor color = new PdtColor();
            color.setName(pdtProductSaveView.getNewSpec().get(i).getColorName());
            color.setPicture(pdtProductSaveView.getNewSpec().get(i).getColorImg());
            PdtColor insColor = PdtColorDAO.insColor(color, supId);
            pdtProductSaveView.getNewSpec().get(i).setColor(insColor.getPkey());
            pdtProductSaveView.getNewSpec().get(i).setColorName(insColor.getName());
          }
          colorSet.add(pdtProductSaveView.getNewSpec().get(i).getColor());
        }
        try {
          JSONObject jsonKeyNmae = new JSONObject();
          JSONObject jsonColorName =
              new JSONObject(pdtProductSaveView.getNewSpec().get(i).getColorName());
          for (Language item : Language.values()) {
            String str = "";
            str +=
                jsonColorName.get(item.toString())
                    + " "
                    + pdtProductSaveView.getNewSpec().get(i).getSizeName();
            jsonKeyNmae.put(item.toString(), str);
          }
          spec.setKeyName(jsonKeyNmae.toString());
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        sizeSet.add(pdtProductSaveView.getNewSpec().get(i).getSize());
        countStock += pdtProductSaveView.getNewSpec().get(i).getCount();
        spec.setColor(pdtProductSaveView.getNewSpec().get(i).getColor());
        spec.setSize(pdtProductSaveView.getNewSpec().get(i).getSize());
        spec.setSku(pdtProductSaveView.getNewSpec().get(i).getSku());
        spec.setPrice(min);
        spec.setMarkup(BigDecimal.valueOf(0));
        spec.setStoreCount(pdtProductSaveView.getNewSpec().get(i).getCount());
        spec.setWeight(BigDecimal.valueOf(0));
        spec.setPics(pdtProductSaveView.getNewSpec().get(i).getColorImg());
        spec.setDeleted(OYn.NO.getLine().getKey());
        spec.setRowVersion((short) 0);
        list.add(spec);
      }
    }
    if (pdtProductSaveView.getDesModule() != null) {
      for (int i = 0; i < pdtProductSaveView.getDesModule().length; i++) {
        String des = null;
        try {
          JSONObject jsonKeyNmae = new JSONObject(pdtProductSaveView.getDesModule()[i]);
          des = pdtProductSaveView.getDesModule()[i];
        } catch (JSONException | NullPointerException e) {
          // TODO: handle exception
          des = null;
        }
        if (i == 0) {
          pdtProduct.setDescribeModule1(des);
        } else if (i == 1) {
          if (pdtProduct.getDescribeModule1() == null) pdtProduct.setDescribeModule1(des);
          else pdtProduct.setDescribeModule2(des);
        } else if (i == 2) {
          if (pdtProduct.getDescribeModule1() == null) pdtProduct.setDescribeModule1(des);
          else if (pdtProduct.getDescribeModule2() == null) pdtProduct.setDescribeModule2(des);
          else pdtProduct.setDescribeModule3(des);
        } else {
          break;
        }
      }
    }

    pdtProduct.setSupplier(supId);
    pdtProduct.setPkey(pdtProductSaveView.getId());
    pdtProduct.setName(objectMapper.writeValueAsString(pdtProductSaveView.getPdtName()));
    // 产品分类
    pdtProduct.setCategory(pdtProductSaveView.getProductCat());
    // 店铺分类
    pdtProduct.setCategoryDiy(pdtProductSaveView.getSupplierCat());
    pdtProduct.setCode(pdtProductSaveView.getNumber_right());
    pdtProduct.setSku("");
    pdtProduct.setCurPrice(min); // 商城价
    pdtProduct.setPurPrice(BigDecimal.valueOf(0));
    pdtProduct.setMktPrice(BigDecimal.valueOf(0));
    pdtProduct.setWsPrice(BigDecimal.valueOf(0));
    /**
     * @Description: 如果没有填写起订量 那么为1双
     *
     * @date 2018/11/6 15:00
     * @author lijie@shoestp.cn
     */
    /*
     * if (pdtProductSaveView.getMin_oq() < 1) { pdtProduct.setMinOq(1); } else {
     * pdtProduct.setMinOq(pdtProductSaveView.getMin_oq()); }
     */
    pdtProduct.setMinOq(Collections.min(countList));
    pdtProduct.setMaxOq(99999); // 最大购买量
    pdtProduct.setSales(0); // 销量
    pdtProduct.setWarnStock(0); // 警告库存
    pdtProduct.setNormAttr(
        pdtProductSaveView.getAttr().stream()
            .filter(o -> o != null)
            .map(String::valueOf)
            .collect(Collectors.joining(",")));
    /*
     * pdtProduct.setColorAttr(pdtProductSaveView.getSpecColor().stream().map(String
     * ::valueOf) .collect(Collectors.joining(",")));
     */
    pdtProduct.setColorAttr(
        colorSet.stream().map(String::valueOf).collect(Collectors.joining(",")));
    /*
     * pdtProduct.setSizeAttr(pdtProductSaveView.getSpecSize().stream().map(String::
     * valueOf) .collect(Collectors.joining(",")));
     */
    pdtProduct.setSizeAttr(sizeSet.stream().map(String::valueOf).collect(Collectors.joining(",")));
    //        pdtProduct.stState(pdtProductSaveView.isState() ? Pdt.OState.ON : Pdt.OState.OFF);
    pdtProduct.stSoldInTime(pdtProductSaveView.isSoldInStatus());
    // 设置上架 --保存到仓库 判断上架时间是否小于当前时间 如果小于设置为立即上架 审核不通过 手动解除 后再判断是否定时器解除
    // 设置上架 --不保存仓库 判断上架时间是否小于当前时间 如果小于设置为立即上架 审核通过 定时器解除
    // 立即上架 --保存到仓库 审核不通过 手动解除
    // 立即上架 --不保存到仓库 审核通过
    if (pdtProduct.gtSoldInTime() && pdtProductSaveView.getWarehouse() == 0) {
      if (pdtProductSaveView.getPutawayDate().compareTo(new Date()) == -1) {
        pdtProduct.setSoldInTime(OYn.NO.getLine().getKey());
        pdtProduct.setSoldTimeB(Env.getSystemTime());
      } else {
        pdtProduct.setSoldInTime(OYn.YES.getLine().getKey());
        pdtProduct.setSoldTimeB(pdtProductSaveView.getPutawayDate());
      }
      pdtProduct.setState(Pdt.OState.OFF.getLine().getKey());
      pdtProduct.setIsVerify(Sys.OYn.NO.getLine().getKey());
    } else if (pdtProduct.gtSoldInTime() && pdtProductSaveView.getWarehouse() != 0) {
      if (pdtProductSaveView.getPutawayDate().compareTo(new Date()) == -1) {
        pdtProduct.setSoldInTime(OYn.NO.getLine().getKey());
        pdtProduct.setSoldTimeB(Env.getSystemTime());
      } else {
        pdtProduct.setSoldInTime(OYn.YES.getLine().getKey());
        pdtProduct.setSoldTimeB(pdtProductSaveView.getPutawayDate());
      }
      pdtProduct.setState(Pdt.OState.ON.getLine().getKey());
      pdtProduct.setIsVerify(Sys.OYn.YES.getLine().getKey());
    } else if (!pdtProduct.gtSoldInTime() && pdtProductSaveView.getWarehouse() == 0) {
      pdtProduct.setSoldInTime(OYn.NO.getLine().getKey());
      pdtProduct.setSoldTimeB(Env.getSystemTime());
      pdtProduct.setState(Pdt.OState.OFF.getLine().getKey());
      pdtProduct.setIsVerify(Sys.OYn.NO.getLine().getKey());
    } else {
      pdtProduct.setSoldInTime(OYn.NO.getLine().getKey());
      pdtProduct.setSoldTimeB(Env.getSystemTime());
      pdtProduct.setState(Pdt.OState.ON.getLine().getKey());
      pdtProduct.setIsVerify(Sys.OYn.YES.getLine().getKey());
    }
    pdtProduct.setIsNew((byte) 1);
    pdtProduct.setIsIndex((byte) 1);
    pdtProduct.setIsHot((byte) 1);
    pdtProduct.setFavoriteCount(0);
    pdtProduct.stIsFreeShipping(pdtProductSaveView.isFreeShipping());
    pdtProduct.setWidth(BigDecimal.valueOf(pdtProductSaveView.getWidth()));
    pdtProduct.setHeight(BigDecimal.valueOf(pdtProductSaveView.getHeight()));
    pdtProduct.setLength(BigDecimal.valueOf(pdtProductSaveView.getLength()));
    if (pdtProductSaveView.getBriefDescription() != null
        && pdtProductSaveView.getBriefDescription().length() > 500) {}

    pdtProduct.setBriefDescription(pdtProductSaveView.getBriefDescription());
    pdtProduct.setDescription(objectMapper.writeValueAsString(pdtProductSaveView.getDescription()));
    pdtProduct.setPicture(String.join(",", pdtProductSaveView.getPdtPics().values()));
    pdtProduct.setWeight(BigDecimal.valueOf(pdtProductSaveView.getWeight()));

    /*
     * if (pdtProductSaveView.getSpec() == null) { return 0; } if
     * (pdtProductSaveView.getSpec().size() < 1) { return 0; } for
     * (PdtProductSpecSaveView specSaveView : pdtProductSaveView.getSpec()) {
     * PdtSpec spec = new PdtSpec(); spec.setPkey(specSaveView.getId()); if
     * (pdtProduct.getPkey() > 0) { spec.setProduct(pdtProduct.getPkey()); }
     * spec.setColor(specSaveView.getColor()); spec.setSize(specSaveView.getSize());
     * //取数据库中的 JsonObject jsonObject = new JsonObject(); for (FldLanguage.Language
     * value : FldLanguage.Language.values()) { String color =
     * CacheUtils.colorCache.get(spec.getColor()).get(value.name()).getAsString();
     * String size =
     * CacheUtils.sizeCache.get(spec.getSize()).get(value.name()).getAsString();
     * jsonObject.addProperty(value.name(), color + " " + size); }
     * spec.setKeyName(jsonObject.toString()); spec.setSku(specSaveView.getSku() !=
     * null && specSaveView.getSku().length() > 0 ? specSaveView .getSku() :
     * pdtProduct.getSku()); spec.setPrice(specSaveView.getPrice() != null ?
     * BigDecimal.valueOf(specSaveView.getPrice()) : pdtProduct.getCurPrice());
     * spec.setWeight( BigDecimal.valueOf(specSaveView.getWeight() != null ?
     * specSaveView.getWeight() : 0)); spec.setPics(String.join(",",
     * specSaveView.getPic().values())); spec.setMarkup(BigDecimal.ZERO);
     * spec.setDeleted(Sys.OYn.NO.getLine().getKey()); if (specSaveView.getStock()
     * != null && specSaveView.getStock() > 0) {
     * spec.setStoreCount(specSaveView.getStock()); } else {
     * spec.setStoreCount(500); } countStock += spec.getStoreCount();
     * list.add(spec); }
     */
    JsonObject seoTitle = new JsonObject();
    JsonObject seoDescription = new JsonObject();
    JsonObject seoKeyword = new JsonObject();
    for (Language value : Language.values()) {
      Object getObj = pdtProductSaveView.getPdtName().get(value.name());
      if (getObj == null) {
        getObj = pdtProductSaveView.getPdtName().get(Language.en.name());
      }
      seoTitle.addProperty(value.name(), String.valueOf(getObj) + " - shoestp.com");
      seoDescription.addProperty(value.name(), String.valueOf(getObj) + " - shoestp.com");
      seoKeyword.addProperty(value.name(), String.valueOf(getObj) + " - shoestp.com");
    }
    pdtProduct.setSeoTitle(seoTitle.toString());
    pdtProduct.setSeoDescription(seoDescription.toString());
    pdtProduct.setSeoKeyword(seoKeyword.toString());
    pdtProduct.setStock(countStock);

    if (pdtProductSaveView.getRadio() != 0) {
      pdtProduct.setProductType(Pdt.OProductType.PrivateExpo.getLine().getKey());
      /*// 私人展会产品无视上架时间与发布仓库
      pdtProduct.setSoldInTime(OYn.NO.getLine().getKey());
      pdtProduct.setSoldTimeB(Env.getSystemTime());
      pdtProduct.setState(Pdt.OState.ON.getLine().getKey());
      pdtProduct.setIsVerify(Sys.OYn.YES.getLine().getKey());*/
    } else {
      pdtProduct.setProductType((byte) 0);
    }

    if (pdtProduct.getPkey() < 0) {
      pdtSave.setB(pdtProduct);
      pdtSave.setLines(list);
      pdtSave.commit();
    } else {
      pdtUpdate.setB(pdtProduct);
      pdtUpdate.setLines(list);
      pdtUpdate.commit();
    }
    PdtProduct pdt = null;
    if (pdtSave.getB() != null) {
      pdt = pdtSave.getB();
    }
    if (pdtUpdate.getB() != null) {
      pdt = pdtUpdate.getB();
    }
    // 添加积分
    addSvs(pdt, supId);
    if (pdt.getProductType() == Pdt.OProductType.PrivateExpo.getLine().getKey()) {
      O2O_PrivateExpoPdt o2o_privateExpoPdt =
          O2O_PrivateExpoPdt.chkUniquePdt_Id(false, pdt.getPkey());
      if (o2o_privateExpoPdt != null) {
        o2o_privateExpoPdt.setPdtId(pdt.getPkey());
        o2o_privateExpoPdt.setPrice(pdt.getCurPrice());
        o2o_privateExpoPdt.setMinOq(pdt.getMinOq());
        if (pdtProductSaveView.getWarehouse() == 0) {
          o2o_privateExpoPdt.setStatus(O2O_PrivateExpoPdtStatus.OFF.getLine().getKey());
        } else {
          o2o_privateExpoPdt.setStatus(O2O_PrivateExpoPdtStatus.ON.getLine().getKey());
        }
        o2o_privateExpoPdt.setVerifyStatus(O2O_PrivateExpoPdtStatus._DEFAULT.getLine().getKey());
        o2o_privateExpoPdt.setCreateTime(Env.getTranBeginTime());
        o2o_privateExpoPdt.upd();
      } else {
        O2O_PrivateExpoPdt privateExpoPdt = new O2O_PrivateExpoPdt();
        privateExpoPdt.setPdtId(pdt.getPkey());
        privateExpoPdt.setPrice(pdt.getCurPrice());
        privateExpoPdt.setMinOq(pdt.getMinOq());
        if (pdtProductSaveView.getWarehouse() == 0) {
          privateExpoPdt.setStatus(O2O_PrivateExpoPdtStatus.OFF.getLine().getKey());
        } else {
          privateExpoPdt.setStatus(O2O_PrivateExpoPdtStatus.ON.getLine().getKey());
        }
        privateExpoPdt.setVerifyStatus(O2O_PrivateExpoPdtStatus._DEFAULT.getLine().getKey());
        privateExpoPdt.setCreateTime(Env.getTranBeginTime());
        privateExpoPdt.ins();
      }
    }
    if (pdtProductSaveView.getRadio() == 0) {
      O2O_PrivateExpoPdt o2o_privateExpoPdt =
          O2O_PrivateExpoPdt.chkUniquePdt_Id(false, pdt.getPkey());
      if (o2o_privateExpoPdt != null) {
        o2o_privateExpoPdt.del();
      }
    }
    if (pdtProductSaveView.getId() <= 0) {
      for (int s = 0; s < tieredPricing.size(); s++) {
        tieredPricing.get(s).setProduct(pdt.getPkey());
      }
      PdtTieredPricingDao.ins(tieredPricing);
    } else {
      PdtTieredPricingDao.updByPdt(
          pdtProductSaveView.getId(), pdtProductSaveView.getTieredPricing(), pdt.getPkey());
    }
    return 1;
  }

  @Override
  public Page getWarehouse(
      Integer supplierm,
      String productName,
      String productNum,
      Integer status,
      Integer start,
      Integer limit,
      Integer type) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 15;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(PdtProduct.class)
                .FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.SUPPLIER, "=?", supplierm);
            if (type != null) {
              WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.MERCHANTDEL.getLine().getKey());
            } else {
              WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.OFF.getLine().getKey());
            }

            if (productName != null) {
              WHERE(PdtProduct.T.NAME, "like '%" + productName + "%'");
            }
            if (productNum != null) {
              WHERE(PdtProduct.T.CODE, "like '%" + productNum + "%'");
            }
            if (status != null) {
              WHERE(PdtProduct.T.CATEGORY, "=?", status);
            }
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<WarehouseView> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                o ->
                    new WarehouseView() {
                      {
                        setId((Integer) o.get(PdtProduct.T.PKEY.getFld().getCodeSqlField()));
                        setImg(
                            ((String) o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()))
                                .split(",")[0]);
                        setNum((String) o.get(PdtProduct.T.CODE.getFld().getCodeSqlField()));
                        setName((String) o.get(PdtProduct.T.NAME.getFld().getCodeSqlField()));
                        if (o.get(PdtProduct.T.CATEGORY.getFld().getCodeSqlField()) != null) {
                          setProductCate(
                              BeanBase.load(
                                      PdtCat.class,
                                      (Integer)
                                          o.get(PdtProduct.T.CATEGORY.getFld().getCodeSqlField()))
                                  .getName());
                        }
                        if (o.get(PdtProduct.T.CATEGORY_DIY.getFld().getCodeSqlField()) != null) {
                          setStoreCate(
                              BeanBase.load(
                                      UsrProductCategory.class,
                                      (Integer)
                                          o.get(
                                              PdtProduct.T.CATEGORY_DIY.getFld().getCodeSqlField()))
                                  .getName());
                        }
                        setPrice(
                            (BigDecimal) o.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField()));
                        setTime((Date) o.get(PdtProduct.T.SOLD_TIME_E.getFld().getCodeSqlField()));
                        setStatus(
                            Integer.valueOf(
                                String.valueOf(
                                    o.get(PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField()))));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  @Override
  public JSONArray getProductCates() {
    JSONArray array = new JSONArray();
    for (PdtCat pdtCat : BeanBase.list(PdtCat.class, null, false)) {
      JSONObject json = new JSONObject();
      try {
        json.put("id", pdtCat.getPkey());
        json.put("name", pdtCat.getName());
        array.put(json);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return array;
  }

  public void addSvs(PdtProduct pdtProduct, Integer supId) {
    if (pdtProduct.getState() == Pdt.OState.ON.getLine().getKey()
        && pdtProduct.getProductType() != Pdt.OProductType.PrivateExpo.getLine().getKey()
        && !pdtProduct.gtSoldInTime()
        && pdtProduct.getFirstPutaway() == OYn.NO.getLine().getKey()) {
      SVSNewestPdt svs = new SVSNewestPdt();
      svs.setProductId(pdtProduct.getPkey());
      svs.setSupplierId(supId);
      svs.setAddedTime(pdtProduct.getSoldTimeB());
      svs.setRowVersion((short) 0);
      svs.ins();
      pdtProduct.setFirstPutaway(OYn.YES.getLine().getKey());
      pdtProduct.upd();
    }
  }

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    // 只针对审核通过的定时上架 即发布产品功能
    System.out.println("<<<<<<<产品定时上架开启中>>>>>>>");
    SQL sql = new SQL();
    sql.SELECT(PdtProduct.class);
    sql.FROM(PdtProduct.class);
    sql.WHERE(PdtProduct.T.STATE, " =? ", Pdt.OState.ON.getLine().getKey());
    sql.WHERE(PdtProduct.T.IS_VERIFY, " =? ", OYn.YES.getLine().getKey());
    sql.WHERE(PdtProduct.T.SOLD_IN_TIME, " =? ", OYn.YES.getLine().getKey());
    sql.WHERE(PdtProduct.T.SOLD_TIME_B, " <=? ", new Date());
    sql.WHERE(PdtProduct.T.FIRST_PUTAWAY, " =? ", OYn.NO.getLine().getKey());
    sql.AND();
    sql.WHERE(PdtProduct.T.PRODUCT_TYPE, " =? ", Pdt.OProductType.GENERAL.getLine().getKey());
    sql.orWhere(PdtProduct.T.PRODUCT_TYPE, " =? ", Pdt.OProductType.PrivateExpo.getLine().getKey());
    List<PdtProduct> pdtList = Query.sql(sql).queryList(PdtProduct.class);
    List<SVSNewestPdt> svsList = new ArrayList<>();
    List<String> strList = new ArrayList<>();
    for (int i = 0; i < pdtList.size(); i++) {
      SVSNewestPdt svs = new SVSNewestPdt();
      svs.setProductId(pdtList.get(i).getPkey());
      svs.setSupplierId(pdtList.get(i).getSupplier());
      svs.setAddedTime(Env.getSystemTime());
      svs.setRowVersion((short) 0);
      svsList.add(svs);
      strList.add("" + pdtList.get(i).getPkey());
    }
    if (svsList != null && !svsList.isEmpty()) {
      BatchUtils.batchIns(SVSNewestPdt.class, svsList);
    }
    if (strList != null && !strList.isEmpty()) {
      Query.UPDATE(PdtProduct.class)
          .SET(PdtProduct.T.FIRST_PUTAWAY, OYn.YES.getLine().getKey())
          .WHERE(
              PdtProduct.T.PKEY,
              SQL.getInSql(strList.size()),
              strList.stream().toArray(Serializable[]::new))
          .executeUpdate();
    }
    try {
      DbPool.getInstance().getConn().commit();
    } catch (SQLException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    System.out.println(String.join(",", list));
  }

  @Override
  public Page getPrivatePdts(
      Integer supId, Integer start, Integer limit) { // TODO Auto-generated method stub
    return null;
  }
}
