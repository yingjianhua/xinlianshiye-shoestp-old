package irille.homeAction.pdt;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;
import com.xinlianshiye.shoestp.shop.service.pdt.PdtProductService;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultMessageService;
import com.xinlianshiye.shoestp.shop.view.pdt.ProdSearchView;
import com.xinlianshiye.shoestp.shop.view.pdt.SortView;

import irille.Dao.SVS.SVSInfoService;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.Service.Pdt.IPdtProductService;
import irille.Service.Pdt.Imp.PdtproductPageselect;
import irille.action.BeanAction;
import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.pdt.dto.ProductInfoView;
import irille.homeAction.pdt.dto.SEOView;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.i18n.I18NUtil;
import irille.pub.idu.Idu;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.odr.OdrOrderDAO;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtAttr;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtComment;
import irille.shop.pdt.PdtCommentDAO;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import irille.shop.pdt.PdtSize;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierDAO;
import irille.view.O2O.O2OMapView;
import irille.view.Page;
import irille.view.ResultView;
import irille.view.pdt.CommentView;
import irille.view.pdt.PdtCommentSatisFactionView;
import irille.view.pdt.PdtCommentViewPageView;
import irille.view.usr.SupplierView;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

@Setter
@Getter
public class PdtProductAction extends HomeAction<PdtProduct> {

  private static final long serialVersionUID = 1L;

  @Inject private PdtproductPageselect pdtpageSelect = new PdtproductPageselect();
  private static final OdrOrderDAO.Query Odrderquery = new OdrOrderDAO.Query();
  private static final PdtCommentDAO.pageSelect commentPageSelect = new PdtCommentDAO.pageSelect();
  @Inject private ObjectMapper objectMapper;
  @Inject private RFQConsultMessageService rFQConsultMessageService;

  @Inject private PdtProductService productService;

  @Inject private IPdtProductService pdtProduct;

  @Inject private SVSInfoService svsInfoService;

  @Override
  public PdtProduct insRun() throws Exception {
    PdtProductDAO.Publish publishDao = new PdtProductDAO.Publish();
    setBean(translateUtil.autoTranslate(getBean())); // 转
    publishDao.setB(getBean());
    publishDao.commit();
    return publishDao.getB();
  }

  @Override
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
      line.setName(line.getName(HomeAction.curLanguage())); // 转
      line.setDescription(line.getDescription(HomeAction.curLanguage())); // 转
      lineJson = crtJsonByBean(line);
      lineJson.put("categoryName", line.gtCategory().getName());
      ja.put(lineJson);
    }
    json.put(STORE_ROOT, ja);
    json.put(STORE_TOTAL, page.getCount());
    writerOrExport(json);
  }

  /**
   * * 转发页面到产品列表 所有商品列表页
   *
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/23 15:45
   */
  @Override
  public String execute() throws Exception {
    setResult("/home/products.jsp");
    return HomeAction.TRENDS;
  }

  /**
   * * 转发页面到产品列表 O2O商品列表页
   *
   * @param
   * @return
   */
  public String o2oList() throws Exception {
    setResult("/home/products-o2o.jsp");
    return HomeAction.TRENDS;
  }

  private boolean _order;
  private String price;
  @Setter private String[] orderfld;
  private int cated = -1;
  private String where;
  private String spec;
  private String _onlyFld;

  @Getter @Setter private String goodsInfo;

  public String getOnlyFld() {
    return _onlyFld;
  }

  public PdtProductAction setOnlyFld(String onlyFld) {
    this._onlyFld = onlyFld;
    return this;
  }

  public boolean isOrder() {
    return _order;
  }

  public void setOrder(boolean order) {
    this._order = order;
  }

  private String keyword;

  private int searchtype;

  private String pName;

  private Integer cate;

  private Integer level;

  private String export;

  private Integer mOrder;

  private BigDecimal min;

  private BigDecimal max;

  private Integer lose;

  private Integer IsO2o;

  private Integer supplier;

  private String o2oAddress;

  private ProdSearchView search;
  private List<SortView> newSort;

  /**
   * * 获取商品列表
   *
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/23 19:16
   */
  public void gtProductsIndexListAjax() throws Exception {
    IduPage iduPage = new IduPage();
    iduPage.setLimit(getLimit());
    iduPage.setStart(getPage());
    iduPage.setWhere(getWhere());
    //        新版API
    if (v == 2) {
      write(
          objectMapper.writeValueAsString(
              pdtProduct.getProductListByCategoryV2(
                  iduPage,
                  orderfld,
                  isOrder(),
                  getCated(),
                  getSpec(),
                  getOnlyFld(),
                  getKeyword(),
                  getSearchtype())));
    } else if (v == 3) {
      Language curLanguage = curLanguage();
      UsrPurchase purchase = getPurchase();
      if (getStart() < 0) setStart(0);
      if (getLimit() == 0 || getLimit() < 0) setLimit(10);
      write(
          pdtProduct.searchPdt(
              orderfld,
              purchase,
              supplier,
              curLanguage,
              lose,
              pName,
              cate,
              level,
              export,
              mOrder,
              min,
              max,
              IsO2o,
              o2oAddress,
              getStart(),
              getLimit()));
    } else if (v.equals(4)) {
      write(
          productService.list(
              getPurchase(), search, getNewSort(), getStart(), getLimit(), curLanguage()));
    } else {
      write(
          objectMapper.writeValueAsString(
              pdtProduct.getProductListByCategory(
                  iduPage,
                  orderfld,
                  isOrder(),
                  getCated(),
                  getSpec(),
                  getOnlyFld(),
                  getKeyword(),
                  getType())));
    }
  }

  /**
   * * 获取热榜 ajax
   *
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/23 19:16
   */
  public void gtProductsIndexHotListAjax() throws Exception {
    IduPage iduPage = new IduPage();
    iduPage.setLimit(getLimit());
    iduPage.setStart(getPage());
    write(pdtProduct.getProductsIndexHot(iduPage));
  }

  /**
   * * 获取商品分类 ajax
   *
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/23 19:16
   */
  public void gtProductsIndexCategoriesListAjax() throws Exception {
    write(pdtProduct.getProductsIndexCategories(getStart(), getLimit(), HomeAction.curLanguage()));
  }

  /**
   * * 获取新品列表
   *
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/24 15:49
   */
  private Integer v;

  public void gtNewProducts() throws Exception {
    IduPage iduPage = new IduPage();
    iduPage.setLimit(getLimit());
    iduPage.setStart(getPage());
    iduPage.setWhere(String.valueOf(getCated()));
    if (v != null && v == 2) {
      write(pdtProduct.getNewProducts(iduPage, getPurchase(), HomeAction.curLanguage()));
    } else {
      // TODO 老接口 要重构
      write(objectMapper.writeValueAsString(pdtpageSelect.getNewProducts(iduPage)));
    }
  }

  private SupplierView supView;

  private SEOView seoView;

  private String expoKey; // 私人展厅产品的密钥, 没有密钥或者密钥过期都不能进入页面

  /**
   * @Description: 商品详情页 Jsp
   *
   * @author lijie@shoestp.cn
   * @date 2018/7/27 10:56
   */
  public String gtProductsInfo() throws Exception {
    if (Long.valueOf(getId().toString()) < 1) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.product_wrong_data, curLanguage()));
    }
    UsrSupplier supplier = BeanBase.load(PdtProduct.class, getId()).gtSupplier();
    setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), supplier.getPkey(), 0));
    ProductInfoView infoView = null;
    if (!isMobile()) {
      infoView =
          pdtpageSelect.getProductsById(
              Integer.valueOf(getId().toString()),
              Sys.OYn.YES,
              Pdt.OState.ON,
              getPurchase() != null ? getPurchase().getPkey() : -1,
              HomeAction.curCurrency());
      if (infoView == null) {
        throw new WebMessageException(
            MessageBuild.buildMessage(ReturnCode.product_wrong_data, curLanguage()));
        //        throw LOG.err("not exists", "产品id[{0}]不存在", getId());
      }
      UserView user = getUser();
      boolean flag = false;
      if (user != null && null != user.getSupplierId()) {
        if (infoView.getSupId() == (long) user.getSupplierId()) {
          flag = true;
        }
      }
      if (infoView.getType() != null
          && infoView.getType().equals(Pdt.OProductType.PrivateExpo.getLine().getKey())) {
        // 若产品类型为私人展厅产品, 需要判断链接密钥有效期 只有正确的密钥能获取进入页面,否则返回404页面
        if (!flag) {
          Integer expoProductPkey;
          if (expoKey == null
              || (expoProductPkey = rFQConsultMessageService.checkPrivateExpoKey(expoKey)) == null
              || !Long.valueOf(expoProductPkey.toString()).equals(infoView.getPdtId())) {
            setResult("404.jsp");
            return HomeAction.TRENDS;
          }
        }
      }

      if (null != infoView.getMap()) setMap(infoView.getMap());
      seoView = new SEOView();
      infoView.setSvsInfo(svsInfoService.getSvsRatingAndRos(supplier.getPkey()));
      seoView.setTitle(translateUtil.getLanguage(infoView.getSeoTitle(), HomeAction.curLanguage()));
      seoView.setDescription(
          translateUtil.getLanguage(infoView.getSeoDescription(), HomeAction.curLanguage()));
      seoView.setKeyWord(
          translateUtil.getLanguage(infoView.getSeoKeywords(), HomeAction.curLanguage()));
      setGoodsInfo(objectMapper.writeValueAsString(infoView));
    }
    setResult("/home/v3/jsp/productInfo/productInfo.jsp");
    return HomeAction.TRENDS;
  }

  /***
   * 新的商品详情接口  返回json信息
   * @throws Exception
   */
  public void getProductsInfo() throws Exception {
    if (Long.valueOf(getId().toString()) < 1) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.product_wrong_data, curLanguage()));
    }
    UsrSupplier supplier = BeanBase.load(PdtProduct.class, getId()).gtSupplier();
    setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), supplier.getPkey(), 0));
    ProductInfoView infoView = null;
    infoView =
        pdtpageSelect.getProductsById(
            Integer.valueOf(getId().toString()),
            Sys.OYn.YES,
            Pdt.OState.ON,
            getPurchase() != null ? getPurchase().getPkey() : -1,
            HomeAction.curCurrency());
    if (infoView == null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.product_wrong_data, curLanguage()));
      //        throw LOG.err("not exists", "产品id[{0}]不存在", getId());
    }
    if (infoView.getType() != null
        && infoView.getType().equals(Pdt.OProductType.PrivateExpo.getLine().getKey())) {
      // 若产品类型为私人展厅产品, 需要判断链接密钥有效期 只有正确的密钥能获取进入页面,否则返回404页面
      Integer expoProductPkey;
      if (expoKey == null
          || (expoProductPkey = rFQConsultMessageService.checkPrivateExpoKey(expoKey)) == null
          || !Long.valueOf(expoProductPkey.toString()).equals(infoView.getPdtId())) {
        /*setResult("404.jsp");
        return HomeAction.TRENDS;*/
        throw new WebMessageException(
            MessageBuild.buildMessage(ReturnCode.service_gone, curLanguage()));
      }
    }

    if (null != infoView.getMap()) setMap(infoView.getMap());
    seoView = new SEOView();
    seoView.setTitle(translateUtil.getLanguage(infoView.getSeoTitle(), HomeAction.curLanguage()));
    seoView.setDescription(
        translateUtil.getLanguage(infoView.getSeoDescription(), HomeAction.curLanguage()));
    seoView.setKeyWord(
        translateUtil.getLanguage(infoView.getSeoKeywords(), HomeAction.curLanguage()));
    setGoodsInfo(objectMapper.writeValueAsString(infoView));
    write(infoView);
  }

  /** ===============O2O INFO START=============== */
  private O2OMapView map;

  /** ===============O2O INFO END===============* */

  /**
   * @Description:你可能喜欢
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/14 11:29
   */
  public void gtYouMayLike() throws IOException {
    IduPage page = new IduPage();
    page.setStart(getPage());
    page.setLimit(getLimit());
    write(pdtProduct.getYouMayLike(page, getCated()));
  }

  private Integer id;

  /** 根据id查询数据 */
  public void detailById() throws Exception {
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    // 目前过滤器的搜索，是肯定会带初始条件的
    String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
    where = PdtProduct.T.PKEY + " in(" + getId() + ")" + " AND " + where;
    IduPage page = newPage();
    page.setStart(getStart());
    page.setLimit(getLimit());
    page.setWhere(where);
    page.commit();
    List<PdtProduct> list = page.getList();
    for (int i = 0; i < list.size(); i++) { // 转
      list.get(i).setName(list.get(i).getName(HomeAction.curLanguage()));
      list.get(i).setDescription(list.get(i).getDescription(HomeAction.curLanguage()));
    }
    JSONObject lineJson = null;
    for (PdtProduct line : list) {
      JSONObject sizeJson = new JSONObject();
      JSONArray sizeArray = new JSONArray();
      for (String sizepk : line.getSizeAttr().split(",")) {
        List<PdtSize> sizeList = Idu.getLines(PdtSize.T.PKEY.getFld(), sizepk);
        for (PdtSize pdtSize : sizeList) {
          sizeJson = crtJsonByBean(pdtSize);
          sizeJson.put(
              PdtSize.T.NAME.getFld().getCode(), pdtSize.getName(HomeAction.curLanguage()));
          sizeJson.put(PdtSize.T.PKEY.getFld().getCode(), pdtSize.getPkey());
          sizeArray.put(sizeJson);
        }
      }
      JSONObject colorJson = new JSONObject();
      JSONArray colorArray = new JSONArray();
      for (String colorPk : line.getColorAttr().split(",")) {
        List<PdtColor> colorList = Idu.getLines(PdtColor.T.PKEY.getFld(), colorPk);
        for (PdtColor pdtColor : colorList) {
          colorJson = crtJsonByBean(pdtColor);
          colorJson.put(
              PdtColor.T.NAME.getFld().getCode(), pdtColor.getName(HomeAction.curLanguage()));
          colorJson.put(PdtColor.T.PKEY.getFld().getCode(), pdtColor.getPkey());
          colorArray.put(colorJson);
        }
      }
      List<PdtAttr> attrList = BeanBase.list(PdtAttr.class, null, false);
      JSONObject attrJson = new JSONObject();
      JSONArray attrArray = new JSONArray();
      for (PdtAttr pdtAttr : attrList) {
        JSONObject attrLineJson = new JSONObject();
        JSONArray attrLineArray = new JSONArray();
        for (String pdtAttrlinepk : line.getNormAttr().split(",")) {
          List<PdtAttrLine> pdtAttrLineList =
              Idu.getLines(PdtAttrLine.T.PKEY.getFld(), pdtAttrlinepk);
          for (PdtAttrLine pdtAttrLine : pdtAttrLineList) {
            if (pdtAttr.getPkey().equals(pdtAttrLine.getMain())) {
              attrLineJson = crtJsonByBean(pdtAttrLine);
              attrLineJson.put(PdtAttrLine.T.NAME.getFld().getCode(), pdtAttrLine.getName());
              attrLineJson.put(PdtAttrLine.T.PKEY.getFld().getCode(), pdtAttrLine.getPkey());
              attrLineArray.put(attrLineJson);
            }
          }
        }
        attrJson = crtJsonByBean(pdtAttr);
        attrJson.put(PdtAttr.T.NAME.getFld().getCode(), pdtAttr.getName());
        attrJson.put("attrLine", attrLineArray);

        attrArray.put(attrJson);
      }
      lineJson = crtJsonByBean(line);
      lineJson.put("size", sizeArray);
      lineJson.put("color", colorArray);
      lineJson.put("attrAll", attrArray);
      ja.put(lineJson);
    }
    json.put(STORE_ROOT, ja);
    json.put(STORE_TOTAL, page.getCount());
    writerOrExport(json);
  }

  /**
   * @Description: 提交评论
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/10 18:46
   */
  private String comment;

  @Getter @Setter private String images;
  private String satisfaction;
  private PdtCommentViewPageView commentViewPageView;

  public String viewComments() {
    setResult("/home/comment_view.jsp");
    PdtCommentViewPageView pdtCommentViewPageView = new PdtCommentViewPageView();
    pdtCommentViewPageView.setPdtProduct(pdtpageSelect.getProduct(getId()));
    pdtCommentViewPageView.setBreadcrumbNav(pdtpageSelect.getBreadcrumbNavByPdtId(getId()));
    pdtCommentViewPageView.setLikeViews(pdtpageSelect.getYouMayLikeByPdtId(id));
    pdtCommentViewPageView.setPdtCommentViews(
        commentPageSelect.getCommentListByProId(null, getId()));
    pdtCommentViewPageView.setTotal(commentPageSelect.getCommentCountByProId(getId()));
    setCommentViewPageView(pdtCommentViewPageView);
    return HomeAction.TRENDS;
  }

  public String writeComment() throws IOException {
    PdtCommentViewPageView pdtCommentViewPageView = new PdtCommentViewPageView();
    if (getPurchase() == null) {
      pdtCommentViewPageView.setCommentPermission(
          false); // "You need to succeed in purchasing this product can comment."
      pdtCommentViewPageView.setCommentPermissionMessage(
          I18NUtil.getBundle("WriteComment_Nocomment"));
    } else {
      if (!Odrderquery.isBuyProduct(getId(), getPurchase().getPkey())) {
        pdtCommentViewPageView.setCommentPermission(false);
        pdtCommentViewPageView.setCommentPermissionMessage(
            I18NUtil.getBundle("WriteComment_Nocomment"));
      }
      if (commentPageSelect.hasComment(getId(), getPurchase().getPkey())) {
        pdtCommentViewPageView.setCommentPermission(false);
        pdtCommentViewPageView.setCommentPermissionMessage(
            I18NUtil.getBundle("WriteComment_Commented"));
      }
    }

    pdtCommentViewPageView.setPdtProduct(pdtpageSelect.getProduct(getId()));
    pdtCommentViewPageView.setTotal(commentPageSelect.getCommentCountByProId(getId()));
    pdtCommentViewPageView.setProductAvg(pdtpageSelect.getProductAvgById(getId()));
    pdtCommentViewPageView.setBreadcrumbNav(pdtpageSelect.getBreadcrumbNavByPdtId(getId()));
    setCommentViewPageView(pdtCommentViewPageView);
    setResult("/home/comment_write.jsp");
    return HomeAction.TRENDS;
  }

  public void addcomment() throws IOException {
    if (getPurchase() == null) {
      writeErr(-1, I18NUtil.getBundle("You_Are_Not_Logged"));
      return;
    }
    if (!Odrderquery.isBuyProduct(getId(), getPurchase().getPkey())) {
      writeErr(-1, I18NUtil.getBundle("AfterSuccessfulPurchase"));
      return;
    }
    PdtComment pdtComment = new PdtComment();
    pdtComment.setProduct(getId());
    pdtComment.setUsersPkey(getPurchase().getPkey());
    pdtComment.setComment(getComment());
    pdtComment.setImages(getImages());
    ObjectMapper objectMapper = new ObjectMapper();
    PdtCommentSatisFactionView pdtCommentSatisFactionView =
        objectMapper.readValue(getSatisfaction(), PdtCommentSatisFactionView.class);
    pdtComment.setProductSatisfaction((byte) pdtCommentSatisFactionView.getOverall_rating());
    objectMapper.setFilterProvider(
        new SimpleFilterProvider()
            .addFilter(
                "PdtCommentSatisFactionView",
                SimpleBeanPropertyFilter.serializeAllExcept("overall_rating")));
    pdtComment.setOthoerSatisfaction(objectMapper.writeValueAsString(pdtCommentSatisFactionView));
    PdtCommentDAO.Ins ins = new PdtCommentDAO.Ins();
    ins.setB(pdtComment);
    ins.commit();
    writeErr(1, I18NUtil.getBundle("Success"));
  }

  public void listComment() throws IOException {
    if (getId() == null || getId() < 1) {
      writeErr(-1, I18NUtil.getBundle("Id_Can_Be_Empty"));
      return;
    }
    IduPage page = new IduPage();
    page.setStart(getPage());
    page.setLimit(getLimit());
    ResultView resultView = new ResultView();
    resultView.setRet(1);
    Map map = new HashMap();
    PdtCommentDAO.pageSelect pageSelect = new PdtCommentDAO.pageSelect();
    map.put("total", pageSelect.getCommentCountByProId(getId()));
    map.put("items", pageSelect.getCommentListByProId(page, getId()));
    write(
        new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .writeValueAsString(map));
  }

  private Page<CommentView> commentView;

  /** 查询当前采购商评论的所有商品 */
  @NeedLogin
  public String selectComment() throws IOException {
    commentView = PdtCommentDAO.pageByPurchase(getPurchase().getPkey(), getStart(), 8);
    setResult("/home/my-Reviews.jsp");
    return TRENDS;
  }

  public void replycomment() throws IOException {
    PdtCommentDAO.Upd upd = new PdtCommentDAO.Upd();
    PdtComment pdtComment = new PdtComment();
    pdtComment.setReply(getComment());
    pdtComment.setPkey(getId());
    upd.replay(pdtComment, 1);
    int code = upd.replay(pdtComment, 1);
    String msg = null;
    switch (code) {
      case 1:
        msg = I18NUtil.getBundle("Success");
        break;
      case 0:
        msg = I18NUtil.getBundle("Ineligible");
        break;
      case -1:
        msg = I18NUtil.getBundle("Failure");
        break;
    }
    writeErr(code, msg);
  }

  public void vote() throws IOException {
    if (getType() != null && getType() > -1 && getType() < 2 && getId() != null && getId() > 0) {
      PdtCommentDAO.Upd upd = new PdtCommentDAO.Upd();
      int code = upd.unuseful(getId(), getType());
      String msg = null;
      switch (code) {
        case 1:
          msg = I18NUtil.getBundle("Success");
          break;
        case 0:
          msg = I18NUtil.getBundle("Ineligible");
          break;
        case -1:
          msg = I18NUtil.getBundle("Failure");
          break;
      }
      writeErr(code, msg);
    } else {
      writeErr(-1, I18NUtil.getBundle("ID_Or_Type_Does_Not_Match_The_Definition"));
    }
  }

  private String sort;

  private Integer type;

  /** 获取店铺内产品分页(手机) */
  public void gtSupPro() throws Exception {
    String where =
        PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField()
            + " = "
            + Sys.OYn.YES.getLine().getKey()
            + " AND "
            + PdtProduct.T.SUPPLIER.getFld().getCodeSqlField()
            + " = "
            + getId()
            + " AND "
            + PdtProduct.T.STATE.getFld().getCodeSqlField()
            + " = "
            + Pdt.OState.ON.getLine().getKey()
            + " AND "
            + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
            + " = "
            + Pdt.OProductType.GENERAL.getLine().getKey();
    Integer idx = (getPage() - 1) * getLimit() - 1;
    if (getCated() != -1) {
      where += " AND " + PdtProduct.T.CATEGORY_DIY.getFld().getCodeSqlField() + " = " + getCated();
    }
    if (getSort() != null && sort.trim() != "") {
      if (type != null) {
        if (type.intValue() == 1) {
          where += " ORDER BY " + sort + " DESC ";
        } else {
          where += " ORDER BY " + sort + " ASC ";
        }
      } else {
        throw new WebMessageException(
            MessageBuild.buildMessage(ReturnCode.service_wrong_data, curLanguage()));
        //        throw new Exception("缺少必要参数 type");
      }
    }
    List<PdtProduct> pdtList =
        translateUtil.getAutoTranslateList(
            BeanBase.list(PdtProduct.class, false, where, idx, getLimit()),
            HomeAction.curLanguage());
    JSONArray ja = new JSONArray(pdtList, false);
    JSONObject json = new JSONObject();
    json.put(STORE_ROOT, ja);
    writerOrExport(json);
  }

  private Integer rankingBasis;

  private Integer basis = 0;

  public void getProductBySup() throws Exception {
    setStart(getPage() <= 1 ? 0 : (getPage() - 1) * getLimit());
    Map map =
        PdtProductDAO.Select.getProductBySup(
            curLanguage(),
            getPkey(),
            getStart(),
            getLimit(),
            getCated(),
            getRankingBasis(),
            getBasis());
    map.put("page", getPage());
    write(objectMapper.writeValueAsString(map));
  }

  //  首页随机商品接口
  public void getRandomProduct() throws IOException {
    if (getLimit() < 1) setLimit(10);
    write(pdtProduct.getRandomPdt(getLimit(), getCated(), getPurchase()));
  }

  private Integer pkey;
  private Integer checkType;

  public void findSupplierPdtList() throws Exception {
    if (pkey == null || checkType == null)
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_wrong_data, curLanguage()));
    if (getLimit() == 0) setLimit(20);
    BeanAction.write(pdtProduct.findPdtList(pkey, getStart(), getLimit(), checkType));
  }
}
