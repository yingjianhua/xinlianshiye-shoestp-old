package irille.homeAction.pdt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import irille.Service.Pdt.IPdtProductService;
import irille.Service.Pdt.Imp.PdtproductPageselect;
import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.pdt.dto.ProductInfoView;
import irille.homeAction.pdt.dto.SEOView;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.i18n.I18NUtil;
import irille.pub.idu.Idu;
import irille.pub.idu.IduPage;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.odr.OdrOrderDAO;
import irille.shop.pdt.*;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierDAO;
import irille.view.Page;
import irille.view.ResultView;
import irille.view.pdt.CommentView;
import irille.view.pdt.PdtCommentSatisFactionView;
import irille.view.pdt.PdtCommentViewPageView;
import irille.view.usr.SupplierView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PdtProductAction extends HomeAction<PdtProduct> {

    private static final PdtProductDAO.Query Pdtquery = new PdtProductDAO.Query();


    @Inject
    private PdtproductPageselect pdtpageSelect = new PdtproductPageselect();
    private static final OdrOrderDAO.Query Odrderquery = new OdrOrderDAO.Query();
    private static final PdtCommentDAO.pageSelect commentPageSelect = new PdtCommentDAO.pageSelect();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    private IPdtProductService pdtProduct;

    @Override
    public PdtProduct insRun() throws Exception {
        PdtProductDAO.Publish publishDao = new PdtProductDAO.Publish();
        setBean(translateUtil.autoTranslate(getBean()));//转
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
            line.setName(line.getName(HomeAction.curLanguage())); //转
            line.setDescription(line.getDescription(HomeAction.curLanguage()));//转
            lineJson = crtJsonByBean(line);
            lineJson.put("categoryName", line.gtCategory().getName());
            ja.put(lineJson);
        }
        json.put(STORE_ROOT, ja);
        json.put(STORE_TOTAL, page.getCount());
        writerOrExport(json);
    }

    /***
     * 转发页面到产品列表
     * 所有商品列表页
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

    private boolean _order;
    private String _price;
    private String[] _orderfld;
    private int _cated = -1;
    private String _where;
    private String _spec;
    private String _onlyFld;

    @Getter
    @Setter
    private String goodsInfo;

    public String getOnlyFld() {
        return _onlyFld;
    }

    public PdtProductAction setOnlyFld(String onlyFld) {
        this._onlyFld = onlyFld;
        return this;
    }

    public String getSpec() {
        return _spec;
    }

    public void setSpec(String _spec) {
        this._spec = _spec;
    }

    public String getWhere() {
        return _where;
    }

    public void setWhere(String where) {
        this._where = where;
    }

    public boolean isOrder() {
        return _order;
    }

    public void setOrder(boolean order) {
        this._order = order;
    }

    public String getPrice() {
        return _price;
    }

    public void setPrice(String price) {
        this._price = price;
    }

    public String[] getOrderfld() {
        return _orderfld;
    }

    public void setOrderfld(String[] orderfld) {
        this._orderfld = orderfld;
    }

    public int getCated() {
        return _cated;
    }

    public void setCated(int cated) {
        this._cated = cated;
    }

    private String _keyword;

    public String getKeyword() {
        return _keyword;
    }

    public void setKeyword(String keyword) {
        this._keyword = keyword;
    }

    /***
     * 获取商品列表
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
        write(objectMapper.writeValueAsString(pdtProduct.getProductListByCategory(iduPage, getOrderfld(), isOrder(), getCated(), getSpec(), getOnlyFld(), getKeyword(), getType())));
    }

    /***
     * 获取热榜  ajax
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

    /***
     * 获取商品分类  ajax
     * @author lijie@shoestp.cn
     * @param
     * @return
     * @date 2018/7/23 19:16
     */
    public void gtProductsIndexCategoriesListAjax() throws Exception {
        write(pdtProduct.getProductsIndexCategories(getStart(), getLimit(), HomeAction.curLanguage()));
    }

    /***
     * 获取新品列表
     * @author lijie@shoestp.cn
     * @param
     * @return
     * @date 2018/7/24 15:49
     */
    @Getter
    @Setter
    private Integer v;

    public void gtNewProducts() throws Exception {
        IduPage iduPage = new IduPage();
        iduPage.setLimit(getLimit());
        iduPage.setStart(getPage());
        iduPage.setWhere(String.valueOf(getCated()));
        if (v != null && v == 2) {
            write(pdtProduct.getNewProducts(iduPage, getPurchase(), HomeAction.curLanguage()));
        } else {
            //TODO 老接口 要重构
            write(objectMapper.writeValueAsString(pdtpageSelect.getNewProducts(iduPage)));
        }
    }


    private SupplierView supView;

    public SupplierView getSupView() {
        return supView;
    }

    public void setSupView(SupplierView supView) {
        this.supView = supView;
    }

    public SEOView getSeoView() {
        return seoView;
    }

    public void setSeoView(SEOView seoView) {
        this.seoView = seoView;
    }

    private SEOView seoView;
    /**
     * @Description: 商品详情页 Jsp
     * @author lijie@shoestp.cn
     * @date 2018/7/27 10:56
     */
    public String gtProductsInfo() throws Exception {
        if (Long.valueOf(getId().toString()) < 1) {
            throw LOG.err("not exists", "产品id[{0}]不存在", getId());
        }
        UsrSupplier supplier = BeanBase.load(PdtProduct.class, getId()).gtSupplier();
        setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), supplier.getPkey(), 0));
        if (!isMobile()) {
            ProductInfoView infoView = pdtpageSelect.getProductsById(Integer.valueOf(getId().toString()), Sys.OYn.YES, Pdt.OState.ON, getPurchase() != null ? getPurchase().getPkey() : -1, HomeAction.curCurrency());
            if (infoView == null) {
                throw LOG.err("not exists", "产品id[{0}]不存在", getId());
            }
            setGoodsInfo(objectMapper.writeValueAsString(infoView));
            SEOView view = new SEOView();
            view.setTitle(new JSONObject(infoView.getSeoTitle()).get(curLanguage().toString()).toString());
            view.setKeyWord(new JSONObject(infoView.getSeoKeywords()).get(curLanguage().toString()).toString());
            view.setDescription(new JSONObject(infoView.getSeoDescription()).get(curLanguage().toString()).toString());
            setSeoView(view);
        }
        setResult("goods-info.jsp");
        return HomeAction.TRENDS;
    }

    /**
     * @Description: 商品详情页 ajax 返回json
     * @author lijie@shoestp.cn
     * @date 2018/7/27 11:03
     */
    public void gtProductsInfoAjax() throws Exception {
        if (Long.valueOf(getId().toString()) < 1) {
            return;
        }
        write(pdtpageSelect.getProductsById(Integer.valueOf(getId().toString()), Sys.OYn.YES, Pdt.OState.ON, getPurchase() != null ? getPurchase().getPkey() : -1, HomeAction.curCurrency()));
    }

    /**
     * @Description:你可能喜欢
     * @author lijie@shoestp.cn
     * @date 2018/8/14 11:29
     */
    public void gtYouMayLike() throws IOException {
        IduPage page = new IduPage();
        page.setStart(getPage());
        page.setLimit(getLimit());
        write(pdtProduct.getYouMayLike(page, getCated()));
    }

    /**
     * @Description: 完全随机商品
     * @date 2018/12/14 19:16
     * @author lijie@shoestp.cn
     */
    public void getRandomPdt() throws IOException {
        write(pdtProduct.getRandomPdt(getLimit(), getCated(), getPurchase()));
    }

    private Integer id;

    /**
     * 根据id查询数据
     */
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
        for (int i = 0; i < list.size(); i++) {//转
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
                    sizeJson.put(PdtSize.T.NAME.getFld().getCode(), pdtSize.getName(HomeAction.curLanguage()));
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
                    colorJson.put(PdtColor.T.NAME.getFld().getCode(), pdtColor.getName(HomeAction.curLanguage()));
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
                    List<PdtAttrLine> pdtAttrLineList = Idu.getLines(PdtAttrLine.T.PKEY.getFld(), pdtAttrlinepk);
                    for (PdtAttrLine pdtAttrLine : pdtAttrLineList) {
                        if (pdtAttr.getPkey() == pdtAttrLine.getMain()) {
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
     * @author lijie@shoestp.cn
     * @date 2018/8/10 18:46
     */
    private String _comment;
    private String _images;
    private String _satisfaction;
    private PdtCommentViewPageView _commentViewPageView;

    public PdtCommentViewPageView getCommentViewPageView() {
        return _commentViewPageView;
    }

    public PdtProductAction setCommentViewPageView(PdtCommentViewPageView _CommentViewPageView) {
        this._commentViewPageView = _CommentViewPageView;
        return this;
    }


    public String getComment() {
        return _comment;
    }

    public String getSatisfaction() {
        return _satisfaction;
    }

    public PdtProductAction setSatisfaction(String _satisfaction) {
        this._satisfaction = _satisfaction;
        return this;
    }


    public void setComment(String comment) {
        this._comment = comment;
    }

    public String getImages() {
        return _images;
    }

    public PdtProductAction setImages(String _images) {
        this._images = _images;
        return this;
    }

    public String viewComments() {
        setResult("/home/comment_view.jsp");
        PdtCommentViewPageView pdtCommentViewPageView = new PdtCommentViewPageView();
        pdtCommentViewPageView.setPdtProduct(pdtpageSelect.getProduct(getId()));
        pdtCommentViewPageView.setBreadcrumbNav(pdtpageSelect.getBreadcrumbNavByPdtId(getId()));
        pdtCommentViewPageView.setLikeViews(pdtpageSelect.getYouMayLikeByPdtId(id));
        pdtCommentViewPageView.setPdtCommentViews(commentPageSelect.getCommentListByProId(null, getId()));
        pdtCommentViewPageView.setTotal(commentPageSelect.getCommentCountByProId(getId()));
        setCommentViewPageView(pdtCommentViewPageView);
        return HomeAction.TRENDS;
    }

    public String writeComment() throws IOException {
        PdtCommentViewPageView pdtCommentViewPageView = new PdtCommentViewPageView();
        if (getPurchase() == null) {
            pdtCommentViewPageView.setCommentPermission(false);//"You need to succeed in purchasing this product can comment."
            pdtCommentViewPageView.setCommentPermissionMessage(I18NUtil.getBundle("WriteComment_Nocomment"));
        } else {
            if (!Odrderquery.isBuyProduct(getId(), getPurchase().getPkey())) {
                pdtCommentViewPageView.setCommentPermission(false);
                pdtCommentViewPageView.setCommentPermissionMessage(I18NUtil.getBundle("WriteComment_Nocomment"));
            }
            if (commentPageSelect.hasComment(getId(), getPurchase().getPkey())) {
                pdtCommentViewPageView.setCommentPermission(false);
                pdtCommentViewPageView.setCommentPermissionMessage(I18NUtil.getBundle("WriteComment_Commented"));

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
        PdtCommentSatisFactionView pdtCommentSatisFactionView = objectMapper.readValue(getSatisfaction(), PdtCommentSatisFactionView.class);
        pdtComment.setProductSatisfaction((byte) pdtCommentSatisFactionView.getOverall_rating());
        objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter("PdtCommentSatisFactionView",
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
        write(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(map));

    }

    private Page<CommentView> commentView;

    public Page<CommentView> getCommentView() {
        return commentView;
    }

    public void setCommentView(Page<CommentView> commentView) {
        this.commentView = commentView;
    }

    /**
     * 查询当前采购商评论的所有商品
     *
     * @throws IOException
     */
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取店铺内产品分页(手机)
     *
     * @throws Exception
     */
    public void gtSupPro() throws Exception {
        String where = PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField() + " = " + Sys.OYn.YES.getLine().getKey() + " AND " + PdtProduct.T.SUPPLIER.getFld().getCodeSqlField() + " = " + getId() + " AND " + PdtProduct.T.STATE.getFld().getCodeSqlField() + " = " + Pdt.OState.ON.getLine().getKey() + " AND " + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField() + " = " + Pdt.OProductType.GENERAL.getLine().getKey();
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
                throw new Exception("缺少必要参数 type");
            }

        }
        List<PdtProduct> pdtList = translateUtil.getAutoTranslateList(BeanBase.list(PdtProduct.class, false, where, idx, getLimit()), HomeAction.curLanguage());
        JSONArray ja = new JSONArray(pdtList, false);
        JSONObject json = new JSONObject();
        json.put(STORE_ROOT, ja);
        writerOrExport(json);
    }


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    private Integer rankingBasis;
    private Integer basis = 0;

    public Integer getRankingBasis() {
        return rankingBasis;
    }

    public void setRankingBasis(Integer rankingBasis) {
        this.rankingBasis = rankingBasis;
    }

    public void getProductBySup() throws Exception {
        setStart(getPage() <= 1 ? 0 : (getPage() - 1) * getLimit());
        Map map = PdtProductDAO.Select.getProductBySup(curLanguage(), getPkey(), getStart(), getLimit(), getCated(), getRankingBasis(), getBasis());
        map.put("page", getPage());
        write(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(map));
    }

    public Integer getBasis() {
        return basis;
    }

    public void setBasis(Integer basis) {
        this.basis = basis;
    }


}
