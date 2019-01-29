package irille.sellerAction.pdt;

import irille.Service.Manage.Pdt.IPdtProductManageService;
import irille.Service.Pdt.Imp.PdtproductPageselect;
import irille.core.sys.Sys;
import irille.core.sys.SysSeqDAO;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.idu.IduPage;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.Tb;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.sellerAction.SellerAction;
import irille.sellerAction.pdt.inf.IPdtProductAction;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import irille.shop.pdt.PdtSpec;
import irille.shop.pdt.PdtSpecDAO;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltFreightSeller;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PdtProductAction extends SellerAction<PdtProduct> implements IPdtProductAction {

  private static final long serialVersionUID = 1L;
  private List<PdtSpec> _SpecListLine = new ArrayList<PdtSpec>();

  @Inject
  private IPdtProductManageService pdtProductManage;
  @Inject
  private PdtproductPageselect pdtpageSelect;

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


  /**
   * @Description: VUe 发布商品
   * @author lijie@shoestp.cn
   * @date 2018/8/24 10:19
   */
  public void saveProduct() throws Exception {
    Integer result = pdtProductManage.saveProduct(getParams("data"), getSupplier().getPkey());
    switch (result) {
      case -1:
        writeErr(-2, "请填写时间");
        break;
      case 0:
        writeErr(0, "商品颜色尺码必须填写");
        break;
      default:
        write();
    }

  }

  /**
   * @Description: 获取根据id获取商品
   * @author lijie@shoestp.cn
   * @date 2018/8/24 16:35
   */
  public void viewProduct() throws IOException {
    try {
      write(
          pdtpageSelect.sellerGetProductById(
              Integer.valueOf(String.valueOf(getId())), getSupplier().getPkey()
          )
      );
      return;
    } catch (JSONException e) {
      e.printStackTrace();
    }
    writeErr(-1, "錯誤");
  }

  public void copyProduct() {
    try {
      write(pdtpageSelect.sellerCopyProductById(Integer.valueOf(String.valueOf(getId())),
          getSupplier().getPkey()));
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
      sql += " AND " + PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField() + "=" + SellerAction
          .getSupplier().getPkey();
      return crtFilterAll() + sql + orderByAsc();
    }
    JSONArray ja = new JSONArray(getFilter());
    Tb tb = tb();
    for (int i = 0; i < ja.length(); i++) {
      JSONObject json = ja.getJSONObject(i);
      String fldName = json.getString(QUERY_PROPERTY);
      String param = json.getString(QUERY_VALUE);
      sql += " AND " + PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField() + "=" + SellerAction
          .getSupplier().getPkey();
      if (Str.isEmpty(param)) {
        continue;
      }
      if (!tb.chk(fldName)) {
        continue;
      }
      Fld fld = tb.get(fldName);
      if (fld == null) {
        continue;
      }
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
      write(pdtProductManage
          .getProductList(getName(), getNumber(), getSupplier().getPkey(), getCat(), getStart(),
              getLimit()));
    }
  }


  /**
   * 获取所有普通商品
   *
   * @author liyichao
   */
  public void getAllPdt() throws Exception {
    JSONObject json = new JSONObject();
    List<PdtProduct> pdtList = BeanBase.list(PdtProduct.class,
        PdtProduct.T.SUPPLIER.getFld().getCodeSqlField() + " = " + SellerAction.getSupplier()
            .getPkey() + " AND " + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField() + " = "
            + Pdt.OProductType.GENERAL.getLine().getKey() + " AND " + PdtProduct.T.STATE.getFld()
            .getCodeSqlField() + " = " + Pdt.OState.ON.getLine().getKey() + " AND "
            + PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField() + " = " + Sys.OYn.YES.getLine()
            .getKey(), false);
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
        if (!load.getKeyName(PltConfigDAO.supplierLanguage(getSupplier()))
            .equals(specListLine.get(i).getKeyName())) {
          specListLine.get(i).setKeyName(
              translateUtil.getMultiLanguageTrans(specListLine.get(i).getKeyName(), true));
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
            specListLine.get(p).setKeyName(
                translateUtil.getMultiLanguageTrans(specListLine.get(p).getKeyName(), true));
            specListLine.get(p).setMarkup(BigDecimal.ZERO);
          }
          //删除后插入规格
          PdtProduct gtProduct = specListLine.get(0).gtProduct();
          List<PdtSpec> list = BeanBase
              .list(PdtSpec.class, PdtSpec.T.PRODUCT + " = " + gtProduct.getPkey(), false);
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
    if (multiLanguageTrans != null && !"".equals(multiLanguageTrans)) {
      _bean.setName(multiLanguageTrans);
    }
    JSONObject json = new JSONObject();
    String status = _bean.getDescription().substring(0, 1);
    if ("1".equals(status)) {
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
    if (!"".equals(_picture) && _picture != null) {
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
