package irille.Service.Manage.Pdt.Imp;

import static irille.pub.util.AppConfig.objectMapper;

import com.google.gson.JsonObject;
import irille.Dao.PdtProductDao;
import irille.Service.Manage.Pdt.IPdtProductManageService;
import irille.core.sys.Sys;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.CacheUtils;
import irille.shop.pdt.*;
import irille.shop.usr.UsrProductCategory;
import irille.view.Page;
import irille.view.pdt.PdtProductCatView;

import irille.view.pdt.PdtProductSaveView;
import irille.view.pdt.PdtProductSpecSaveView;
import irille.view.pdt.WarehouseView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/7 Time: 15:55
 */
public class PdtProductManageServiceImp implements IPdtProductManageService {

    @Inject
    PdtProductDao pdtProductDao;

    @Inject
    private PdtProductDAO.Publish pdtSave;
    @Inject
    private PdtProductDAO.Upd2 pdtUpdate;


    @Override
    public Page getProductList(String name, String number, Integer supplierId, int cat, int start,
                               int limit) {
        return pdtProductDao.getProductListManage(name, number, supplierId, cat, start, limit);
    }

    @Override
    public List<PdtProductCatView> getCatChildNodesByCatId(int i,
                                                           FldLanguage.Language supplierLanguage) {
        return pdtProductDao.getCatChildNodesByCatId(i, supplierLanguage);
    }

    @Override
    public Integer saveProduct(String data, Integer supId) throws IOException, ExecutionException {
        PdtProductSaveView pdtProductSaveView = objectMapper
                .readValue(data, PdtProductSaveView.class);
        PdtProduct pdtProduct = new PdtProduct();
        pdtProduct.setSupplier(supId);
        pdtProduct.setPkey(pdtProductSaveView.getId());
        pdtProduct.setName(objectMapper.writeValueAsString(pdtProductSaveView.getPdtName()));
        //审核通过
        pdtProduct.stIsVerify(true);
        //产品分类
        pdtProduct.setCategory(pdtProductSaveView.getProductCat());
        //店铺分类
        pdtProduct.setCategoryDiy(pdtProductSaveView.getSupplierCat());
        pdtProduct.setCode(
                pdtProductSaveView.getNumber_left() + "-" + pdtProductSaveView.getNumber_right()
        );
        pdtProduct.setSku(pdtProductSaveView.getSku());
        pdtProduct.setCurPrice(BigDecimal.valueOf(pdtProductSaveView.getPrice()));
        pdtProduct.setPurPrice(BigDecimal.valueOf(pdtProductSaveView.getPurPrice()));
        pdtProduct.setMktPrice(BigDecimal.valueOf(pdtProductSaveView.getMktPrice()));
        pdtProduct.setWsPrice(BigDecimal.valueOf(pdtProductSaveView.getPrice()));
        /**
         * @Description: 如果没有填写起订量 那么为1双
         * @date 2018/11/6 15:00
         * @author lijie@shoestp.cn
         */
        if (pdtProductSaveView.getMin_oq() < 1) {
            pdtProduct.setMinOq(1);
        } else {
            pdtProduct.setMinOq(pdtProductSaveView.getMin_oq());
        }
        pdtProduct.setMaxOq(pdtProductSaveView.getMax_oq());
        pdtProduct.setSales(0);
        pdtProduct.setWarnStock(pdtProductSaveView.getWarnStock());
        pdtProduct.setNormAttr(
                pdtProductSaveView.getAttr().stream().filter(o -> o != null).map(String::valueOf)
                        .collect(Collectors.joining(",")));
        pdtProduct.setColorAttr(pdtProductSaveView.getSpecColor().stream().map(String::valueOf)
                .collect(Collectors.joining(",")));
        pdtProduct.setSizeAttr(pdtProductSaveView.getSpecSize().stream().map(String::valueOf)
                .collect(Collectors.joining(",")));
        pdtProduct.stState(pdtProductSaveView.isState() ? Pdt.OState.ON : Pdt.OState.OFF);
        pdtProduct.stSoldInTime(pdtProductSaveView.isSoldInStatus());
        if (pdtProductSaveView.isSoldInStatus()) {
            if (pdtProductSaveView.getSoldInTime() == null) {
                return -2;
            }
            pdtProduct.setSoldTimeB(pdtProductSaveView.getSoldInTime().get(0));
            pdtProduct.setSoldTimeE(pdtProductSaveView.getSoldInTime().get(1));
        }
        pdtProduct.setProductType((byte) 0);
        pdtProduct.setIsNew((byte) 1);
        pdtProduct.setIsIndex((byte) 1);
        pdtProduct.setIsHot((byte) 1);
        pdtProduct.setFavoriteCount(0);
        pdtProduct.stIsFreeShipping(pdtProductSaveView.isFreeShipping());
        pdtProduct.setWidth(BigDecimal.valueOf(pdtProductSaveView.getWidth()));
        pdtProduct.setHeight(BigDecimal.valueOf(pdtProductSaveView.getHeight()));
        pdtProduct.setLength(BigDecimal.valueOf(pdtProductSaveView.getLength()));
        if (pdtProductSaveView.getBriefDescription() != null
                && pdtProductSaveView.getBriefDescription().length() > 500) {

        }
        pdtProduct.setBriefDescription(pdtProductSaveView.getBriefDescription());
        pdtProduct.setDescription(objectMapper.writeValueAsString(pdtProductSaveView.getDescription()));
        List<PdtSpec> list = new ArrayList<>();
        pdtProduct.setPicture(String.join(",", pdtProductSaveView.getPdtPics().values()));
        pdtProduct.setWeight(BigDecimal.valueOf(pdtProductSaveView.getWeight()));
        int countStock = 0;
        if (pdtProductSaveView.getSpec() == null) {
            return 0;
        }
        if (pdtProductSaveView.getSpec().size() < 1) {
            return 0;
        }
        for (PdtProductSpecSaveView specSaveView : pdtProductSaveView.getSpec()) {
            PdtSpec spec = new PdtSpec();
            spec.setPkey(specSaveView.getId());
            if (pdtProduct.getPkey() > 0) {
                spec.setProduct(pdtProduct.getPkey());
            }
            spec.setColor(specSaveView.getColor());
            spec.setSize(specSaveView.getSize());
            //取数据库中的
            JsonObject jsonObject = new JsonObject();
            for (FldLanguage.Language value : FldLanguage.Language.values()) {
                String color = CacheUtils.colorCache.get(spec.getColor()).get(value.name()).getAsString();
                String size = CacheUtils.sizeCache.get(spec.getSize()).get(value.name()).getAsString();
                jsonObject.addProperty(value.name(), color + " " + size);
            }
            spec.setKeyName(jsonObject.toString());
            spec.setSku(specSaveView.getSku() != null && specSaveView.getSku().length() > 0 ? specSaveView
                    .getSku() : pdtProduct.getSku());
            spec.setPrice(specSaveView.getPrice() != null ? BigDecimal.valueOf(specSaveView.getPrice())
                    : pdtProduct.getCurPrice());
            spec.setWeight(
                    BigDecimal.valueOf(specSaveView.getWeight() != null ? specSaveView.getWeight() : 0));
            spec.setPics(String.join(",", specSaveView.getPic().values()));
            spec.setMarkup(BigDecimal.ZERO);
            spec.setDeleted(Sys.OYn.NO.getLine().getKey());
            if (specSaveView.getStock() != null && specSaveView.getStock() > 0) {
                spec.setStoreCount(specSaveView.getStock());
            } else {
                spec.setStoreCount(500);
            }
            countStock += spec.getStoreCount();
            list.add(spec);
        }
        JsonObject seoTitle = new JsonObject();
        JsonObject seoDescription = new JsonObject();
        JsonObject seoKeyword = new JsonObject();
        for (Language value : Language.values()) {
            Object getObj = pdtProductSaveView.getPdtName().get(value.name());
            if (getObj == null) {
                getObj = pdtProductSaveView.getPdtName().get(Language.en.name());
            }
            seoTitle.addProperty(
                    value.name(), String.valueOf(getObj) + " - shoestp.com"
            );
            seoDescription.addProperty(
                    value.name(), String.valueOf(getObj) + " - shoestp.com"
            );
            seoKeyword.addProperty(
                    value.name(), String.valueOf(getObj) + " - shoestp.com"
            );
        }
        pdtProduct.setSeoTitle(seoTitle.toString());
        pdtProduct.setSeoDescription(seoDescription.toString());
        pdtProduct.setSeoKeyword(seoKeyword.toString());
        pdtProduct.setStock(countStock);
        if (pdtProduct.getPkey() < 0) {
            pdtSave.setB(pdtProduct);
            pdtSave.setLines(list);
            pdtSave.commit();
        } else {
            pdtUpdate.setB(pdtProduct);
            pdtUpdate.setLines(list);
            pdtUpdate.commit();
        }
        return 1;
    }

    @Override
    public Page getWarehouse(Integer supplierm, String productName, String productNum, Integer status, Integer start, Integer limit, Integer type) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 15;
        }
        SQL sql = new SQL() {{
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
        }};
        Integer count = Query.sql(sql).queryCount();
        List<WarehouseView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(o -> new WarehouseView() {{
            setId((Integer) o.get(PdtProduct.T.PKEY.getFld().getCodeSqlField()));
            setImg(((String) o.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField())).split(",")[0]);
            setNum((String) o.get(PdtProduct.T.CODE.getFld().getCodeSqlField()));
            setName((String) o.get(PdtProduct.T.NAME.getFld().getCodeSqlField()));
            if (o.get(PdtProduct.T.CATEGORY.getFld().getCodeSqlField()) != null) {
                setProductCate(BeanBase.load(PdtCat.class, (Integer) o.get(PdtProduct.T.CATEGORY.getFld().getCodeSqlField())).getName());
            }
            if (o.get(PdtProduct.T.CATEGORY_DIY.getFld().getCodeSqlField()) != null) {
                setStoreCate(BeanBase.load(UsrProductCategory.class, (Integer) o.get(PdtProduct.T.CATEGORY_DIY.getFld().getCodeSqlField())).getName());
            }
            setPrice((BigDecimal) o.get(PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField()));
            setTime((Date) o.get(PdtProduct.T.SOLD_TIME_E.getFld().getCodeSqlField()));
            setStatus(Integer.valueOf(String.valueOf(o.get(PdtProduct.T.STATE.getFld().getCodeSqlField()))));
        }}).collect(Collectors.toList());
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
}
