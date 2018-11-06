package irille.sellerAction.pdt;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import irille.core.sys.Sys;
import irille.core.sys.SysSeqDAO;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.idu.Idu;
import irille.pub.idu.IduPage;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.Tb;
import irille.pub.util.CacheUtils;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.sellerAction.SellerAction;
import irille.sellerAction.pdt.inf.IPdtProductAction;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import irille.shop.pdt.PdtSpec;
import irille.shop.pdt.PdtSpecDAO;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltFreightSeller;
import irille.shop.usr.UsrProductCategory;
import irille.view.Page;
import irille.view.pdt.PdtProductSaveView;
import irille.view.pdt.PdtProductSpecSaveView;

public class PdtProductAction extends SellerAction<PdtProduct> implements IPdtProductAction {

	private static final long serialVersionUID = 1L;
	private List<PdtSpec> _SpecListLine = new ArrayList<PdtSpec>();
    private static PdtProductDAO.pageSelect pageSelect = new PdtProductDAO.pageSelect();

    public String pdtProductList() throws Exception {
        setResult("Product-List.html");
        return SellerAction.TRENDS;
    }

    public List<PdtSpec> getSpecListLine() {
        return _SpecListLine;
    }

    public void setSpecListLine(List<PdtSpec> _SpecListLine) {
        this._SpecListLine = _SpecListLine;
    }

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * @throws Exception
     * @Description: VUe 发布商品
     * @author lijie@shoestp.cn
     * @date 2018/8/24 10:19
     */
    public void saveProduct() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PdtProductSaveView pdtProductSaveView = objectMapper.readValue(data, PdtProductSaveView.class);

        PdtProduct pdtProduct = new PdtProduct();
        pdtProduct.setPkey(pdtProductSaveView.getId());
        pdtProduct.setName(objectMapper.writeValueAsString(pdtProductSaveView.getPdtName()));
        //审核通过
        pdtProduct.stIsVerify(true);
        //产品分类
        pdtProduct.setCategory(pdtProductSaveView.getProductCat());
        //店铺分类
        pdtProduct.setCategoryDiy(pdtProductSaveView.getSupplierCat());
        pdtProduct.setCode(pdtProductSaveView.getNumber_left() + "-" + pdtProductSaveView.getNumber_right());
        pdtProduct.setSupplier(getSupplier().getPkey());
        pdtProduct.setSku(pdtProductSaveView.getSku());
        pdtProduct.setCurPrice(BigDecimal.valueOf(pdtProductSaveView.getPrice()));
        pdtProduct.setPurPrice(BigDecimal.valueOf(pdtProductSaveView.getPurPrice()));
        pdtProduct.setMktPrice(BigDecimal.valueOf(pdtProductSaveView.getMktPrice()));
        pdtProduct.setWsPrice(BigDecimal.valueOf(pdtProductSaveView.getPrice()));
        pdtProduct.setMinOq(pdtProductSaveView.getMin_oq());
        pdtProduct.setMaxOq(pdtProductSaveView.getMax_oq());
        pdtProduct.setSales(0);
        pdtProduct.setWarnStock(pdtProductSaveView.getWarnStock());
        
        pdtProduct.setNormAttr(pdtProductSaveView.getAttr().stream().filter(o->o!=null).map(String::valueOf).collect(Collectors.joining(",")));
        pdtProduct.setColorAttr(pdtProductSaveView.getSpecColor().stream().map(String::valueOf).collect(Collectors.joining(",")));
        pdtProduct.setSizeAttr(pdtProductSaveView.getSpecSize().stream().map(String::valueOf).collect(Collectors.joining(",")));
        
        pdtProduct.setState(pdtProductSaveView.isState() ? Sys.OYn.YES.getLine().getKey() : Sys.OYn.NO.getLine().getKey());
        pdtProduct.stSoldInTime(pdtProductSaveView.isSoldInStatus());
        if (pdtProductSaveView.isSoldInStatus()) {
            if (pdtProductSaveView.getSoldInTime() == null) {
                writeErr(-2, "请填写时间");
                return;
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
        pdtProduct.setBriefDescription(pdtProductSaveView.getBriefDescription());
        pdtProduct.setDescription(objectMapper.writeValueAsString(pdtProductSaveView.getDescription()));
        List<PdtSpec> list = new ArrayList<>();
        //            pdtProduct.setStock(pdtProductSaveView.getStock());
        pdtProduct.setPicture(String.join(",", pdtProductSaveView.getPdtPics().values()));
        pdtProduct.setWeight(BigDecimal.valueOf(pdtProductSaveView.getWeight()));
        int countStock = 0;
//        boolean isAddStock = true;
        if (pdtProductSaveView.getSpec() == null) {
            writeErr(0, "商品颜色尺码必须填写");
            return;
        }
        if (pdtProductSaveView.getSpec().size() < 1) {
            writeErr(0, "商品颜色尺码必须填写");
            return;
        }
        for (PdtProductSpecSaveView specSaveView : pdtProductSaveView.getSpec()) {
            PdtSpec spec = new PdtSpec();
            spec.setPkey(specSaveView.getId());
            if (pdtProduct.getPkey() > 0)
                spec.setProduct(pdtProduct.getPkey());
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

            //
            spec.setSku(specSaveView.getSku() != null && specSaveView.getSku().length() > 0 ? specSaveView.getSku() : pdtProduct.getSku());
            spec.setPrice(specSaveView.getPrice() != null ? BigDecimal.valueOf(specSaveView.getPrice()) : pdtProduct.getCurPrice());
            spec.setWeight(BigDecimal.valueOf(specSaveView.getWeight() != null ? specSaveView.getWeight() : 0));
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


        pdtProduct.setStock(countStock);
//            pdtProduct.setStock(pdtProductSaveView.getStock());
//        }
        if (pdtProduct.getPkey() < 0) {
            PdtProductDAO.Publish publishDao = new PdtProductDAO.Publish();
            publishDao.setB(pdtProduct);
            publishDao.setLines(list);
            publishDao.commit();
            write();
        } else {
            PdtProductDAO.Upd2 upd2 = new PdtProductDAO.Upd2();
            upd2.setB(pdtProduct);
            upd2.setLines(list);
            upd2.commit();
            write();
        }

    }

    /**
     * @Description: 获取根据id获取商品
     * @author lijie@shoestp.cn
     * @date 2018/8/24 16:35
     */
    public void viewProduct() throws IOException {
        PdtProductDAO.pageSelect pageSelec = new PdtProductDAO.pageSelect();
        try {
            write(pageSelec.sellerGetProductById(Integer.valueOf(String.valueOf(getId())), getSupplier().getPkey()));
            return;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        writeErr(-1, "錯誤");
    }

    public void copyProduct() {
        PdtProductDAO.pageSelect pageSelec = new PdtProductDAO.pageSelect();
        try {
            write(pageSelec.sellerCopyProductById(Integer.valueOf(String.valueOf(getId())), getSupplier().getPkey()));
            return;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writeErr(-1, "錯誤");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成递增编号
     */
    public void getSeqnumInt() throws Exception {
        JSONObject json = new JSONObject();
        json.put("CODE", SysSeqDAO.getSeqnumInt(PdtProduct.TB));
        writerOrExport(json);

    }

    /**
     * 默认条件当前商家
     *
     * @author zw
     */
    public String crtFilter() throws JSONException {
        String sql = "";
        if (Str.isEmpty(getFilter())) {
            sql += " AND " + PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField() + "=" + SellerAction.getSupplier().getPkey();
            return crtFilterAll() + sql + orderByAsc();
        }
        JSONArray ja = new JSONArray(getFilter());
        Tb tb = tb();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject json = ja.getJSONObject(i);
            String fldName = json.getString(QUERY_PROPERTY);
            String param = json.getString(QUERY_VALUE);
            sql += " AND " + PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField() + "=" + SellerAction.getSupplier().getPkey();
            if (Str.isEmpty(param))
                continue;
            if (!tb.chk(fldName))
                continue;
            Fld fld = tb.get(fldName);
            if (fld == null)
                continue;
            sql += " AND " + Env.INST.getDB().crtWhereSearch(fld, param);
        }
        return crtFilterAll() + sql + orderByAsc();
    }

    private String name;
    //分类
    private int cat;
    //产品编号
    private String number;

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 根据登陆id查询产品
     */
    public void layuiList() throws Exception {
        if (getSupplier() == null) {
            writeTimeout();
        } else {
            BeanQuery q = Query
                    .SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.CODE, PdtProduct.T.CUR_PRICE, PdtProduct.T.PICTURE, PdtProduct.T.UPDATE_TIME)
                    .SELECT(PdtCat.T.NAME, "category")
                    .SELECT(UsrProductCategory.T.NAME, "categoryDiy")
                    .FROM(PdtProduct.class)
                    .LEFT_JOIN(PdtCat.class, PdtProduct.T.CATEGORY, PdtCat.T.PKEY)
                    .LEFT_JOIN(UsrProductCategory.class, PdtProduct.T.CATEGORY_DIY, UsrProductCategory.T.PKEY)
                    .WHERE(getName() != null && getName().length() > 0, PdtProduct.T.NAME, "like ?", "%" + getName() + "%")
                    .WHERE(getNumber() != null && getNumber().length() > 0, PdtProduct.T.CODE, "like ?", "%" + getNumber() + "%")
                    .WHERE(PdtProduct.T.SUPPLIER, "=?", getSupplier().getPkey())
                    .WHERE(PdtProduct.T.PRODUCT_TYPE, " <> ?", Pdt.OProductType.GROUP.getLine().getKey())
                    .WHERE(PdtProduct.T.STATE, "<>2").ORDER_BY(PdtProduct.T.UPDATE_TIME, "desc");
            if (getCat() > 0) {
                List list= (List) pageSelect.getCatsNodeByCatId(getCat()).stream().map(o -> {
                    return String.valueOf(o);
                }).collect(Collectors.toList());
                q.WHERE(getCat() != 0, PdtProduct.T.CATEGORY, "in (" + String.join(",",list) + ")");
            }
            Integer totalCount = q.queryCount();
            setStart(getStart() - 1 > -1 ? getStart() - 1 : 0);
            List<Map> list = q.limit(getStart() * getLimit(), getLimit()).queryMaps();

//             目前过滤器的搜索，是肯定会带初始条件的
            Page page = new Page(list.stream().map(o -> {
                o.put("category", translateUtil.getLanguage(o.get("category"), PltConfigDAO.supplierLanguage(getSupplier())));
                o.put("name", translateUtil.getLanguage(o.get("name"), PltConfigDAO.supplierLanguage(getSupplier())));
                o.put("categoryDiy", translateUtil.getLanguage(o.get("categoryDiy"), PltConfigDAO.supplierLanguage(getSupplier())));
                o.put("update_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o.get("update_time")));
                return o;
            }).collect(Collectors.toList()), getStart(), getLimit(), totalCount);
            write(page);
        }
    }


    /**
     * 获取所有普通商品
     *
     * @throws Exception
     * @author liyichao
     */
    public void getAllPdt() throws Exception {
        JSONObject json = new JSONObject();
        List<PdtProduct> pdtList = BeanBase.list(PdtProduct.class, PdtProduct.T.SUPPLIER.getFld().getCodeSqlField() + " = " + SellerAction.getSupplier().getPkey() + " AND " + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField() + " <> " + Pdt.OProductType.GROUP.getLine().getKey() + " AND " + PdtProduct.T.STATE.getFld().getCodeSqlField() + " = " + Pdt.OState.ON.getLine().getKey() + " AND " + PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField() + " = " + Sys.OYn.YES.getLine().getKey(), false);
        JSONArray pdtArray = new JSONArray();
        for (PdtProduct pdt : pdtList) {
            pdtArray.put(crtJsonByBean(pdt));
        }
        json.put(STORE_ROOT, pdtArray);
        json.put(STORE_TOTAL, pdtList.size());
        writerOrExport(json);
    }


    public void list() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        // 目前过滤器的搜索，是肯定会带初始条件的
        String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
        IduPage page = newPage();
        page.setStart(getStart());
        page.setLimit(getLimit());
        page.setWhere(where);
        page.commit();
        List<PdtProduct> list = page.getList();
        JSONObject lineJson = null;
        for (PdtProduct line : list) {
			/*line.setName(line.getName(PltConfigDAO.supplierLanguage(getSupplier())));
			System.out.println(line.getName());*/
            line.setDescription(line.getDescription(PltConfigDAO.supplierLanguage(getSupplier())));
        }
        ja = new JSONArray(list, false);
        json.put(STORE_ROOT, ja);
        json.put(STORE_TOTAL, page.getCount());
        writerOrExport(json);
    }


    /**
     * 商家修改
     */
    public void usrUpdRun() throws Exception {
        List<PdtSpec> specListLine = getSpecListLine();
        PdtSpecDAO.upd specUpd = new PdtSpecDAO.upd();
        for (int i = 0; i < specListLine.size(); i++) {
            if (specListLine.get(i).getPkey() != -1) {
                PdtSpec load = PdtSpec.load(PdtSpec.class, specListLine.get(i).getPkey());
                if (!load.getKeyName(PltConfigDAO.supplierLanguage(getSupplier())).equals(specListLine.get(i).getKeyName())) {
                    specListLine.get(i).setKeyName(translateUtil.getMultiLanguageTrans(specListLine.get(i).getKeyName(), true));
                    specListLine.get(i).setMarkup(BigDecimal.ZERO);
                    specListLine.get(i).upd();
                } else {
                    specListLine.get(i).setMarkup(BigDecimal.ZERO);
                    //specListLine.get(i).upd();
                    specUpd.setB(specListLine.get(i));
                    specUpd.commit();
                }
            } else {
                if (i == 0) {
                    for (int p = 0; p < specListLine.size(); p++) {
                        specListLine.get(p).setKeyName(translateUtil.getMultiLanguageTrans(specListLine.get(p).getKeyName(), true));
                        specListLine.get(p).setMarkup(BigDecimal.ZERO);
                    }
                    //删除后插入规格
                    PdtProduct gtProduct = specListLine.get(0).gtProduct();
                    List<PdtSpec> list = BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT + " = " + gtProduct.getPkey(), false);
                    for (int j = 0; j < list.size(); j++) {
                        list.get(j).del();
                    }
                    //新增
                    Idu.insLine(gtProduct, specListLine, PdtSpec.T.PRODUCT.getFld());
                    break;
                }
            }
        }

        JSONObject name = new JSONObject(_bean.getName());
        String multiLanguageTrans = "";
        if (!name.has("en") || !name.has("zh_TW")) {
            if (name.has("en")) {
                multiLanguageTrans = translateUtil.getMultiLanguageTrans(name.getString("en"), true);
            } else if (name.has("zh_TW")) {
                multiLanguageTrans = translateUtil.getMultiLanguageTrans(name.getString("zh_TW"), true);
            }
        }
        if (multiLanguageTrans != null && !"" .equals(multiLanguageTrans)) {
            _bean.setName(multiLanguageTrans);
        }
        JSONObject json = new JSONObject();
        String status = _bean.getDescription().substring(0, 1);
        if ("1" .equals(status)) {
            _bean.setDescription(_bean.getDescription().substring(1, _bean.getDescription().length()));
            _bean.setDescription(translateUtil.getMultiLanguageTrans(_bean.getDescription(), true));
            _bean = subPro(_bean);
            setBean(_bean);
            updBefore();
            PdtProductDAO.Upd1 upd = new PdtProductDAO.Upd1();
            upd.setB(getBean());
            //upd.setLines(specListLine);
            upd.commit();
            updAfter();
            json.put("success", true);
        } else {
            PdtProductDAO.Upd2 upd = new PdtProductDAO.Upd2();
            _bean = subPro(_bean);
            upd.setB(getBean());
            //upd.setLines(specListLine);
            upd.commit();
            updAfter();
            json.put("success", true);
        }
		/*setBean(_bean);
		updBefore();
		PdtProductDAO.Upd1 upd = new PdtProductDAO.Upd1();
		upd.setB(getBean());
		//upd.setLines(specListLine);
		upd.commit();
		updAfter();
		json.put("success", true);*/
        writerOrExport(json);
    }

    private PdtProduct subPro(PdtProduct pro) {
        String picture = pro.getPicture();

        String _picture = "";
        String substring = picture.substring(0, 1);
        if (substring.equals(",")) {
            String substring1 = picture.substring(1, picture.length());
            String substring2 = substring1.substring(substring1.length() - 1, substring1.length());
            if (substring2.equals(",")) {
                _picture = substring2.substring(0, picture.length() - 1);
            } else {
                _picture = substring1;
            }
        } else {
            String substring3 = picture.substring(picture.length() - 1, picture.length());
            if (substring3.equals(",")) {
                _picture = picture.substring(0, picture.length() - 1);
            } else {
                _picture = picture;
            }
        }
        if (!"" .equals(_picture) && _picture != null) {
            pro.setPicture(_picture);
            return pro;
        }
        return pro;
    }

    /*
     * 删除
     */
    public void delDetails() throws Exception {
        JSONObject json = new JSONObject();
        PdtProductDAO.DelDetails del = new PdtProductDAO.DelDetails();
        del.setBKey(getPkey());
        del.commit();
//		writeSuccess(getBean());
        json.put("success", true);
        writerOrExport(json);
    }

    @Override
    public String detail() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


}
