package irille.homeAction.usr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import irille.Service.Usr.IUsrSupplierService;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.Page_supplierView;
import irille.homeAction.usr.dto.ProductView;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduPage;
import irille.pub.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.shop.pdt.PdtCatDAO;
import irille.shop.plt.PltProvince;
import irille.shop.prm.PrmGroupPurchase;
import irille.shop.prm.PrmGroupPurchaseDAO;
import irille.shop.usr.Usr.OStatus;
import irille.shop.usr.*;
import irille.view.pdt.CategoryView;
import irille.view.usr.SupplierView;
import irille.view.usr.UserView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsrSupplierAction extends HomeAction<UsrSupplier> {
    private static final UsrSupplierDAO.pageSelect pageSelect = new UsrSupplierDAO.pageSelect();
    private Integer _length;
    private String category;
    private String sort;
    private Integer pageNumber;
    private List<ProductView> _productView;

    @Inject
    IUsrSupplierService usrSupplierService;


    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getLength() {
        return _length;
    }

    public void setLength(Integer _length) {
        this._length = _length;
    }

    public List<ProductView> get_productView() {
        return _productView;
    }

    public void set_productView(List<ProductView> _productView) {
        this._productView = _productView;
    }

    /**
     * 返回店铺首页
     *
     * @throws Exception
     * @author liyichao
     */
    public String gtSupIndex() {
        setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), getPkey(), 1));
        setResult("/home/shop-index.jsp");
        return HomeAction.TRENDS;
    }

    private double pageAll;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public double getPageAll() {
        return pageAll;
    }

    public void setPageAll(double pageAll) {
        this.pageAll = pageAll;
    }

    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    private SupplierView supView;

    public SupplierView getSupView() {
        return supView;
    }

    public void setSupView(SupplierView supView) {
        this.supView = supView;
    }

    /**
     * 返回供应商产品页面|根据产品分类和页码搜索产品|根据Most Popular/Sales/Favorites/New/Price进行排序
     *
     * @throws JSONException
     * @author liyichao
     */
    private List<UsrProductCategory> topDiyCat = new ArrayList<UsrProductCategory>();

    public List<UsrProductCategory> getTopDiyCat() {
        return topDiyCat;
    }

    public void setTopDiyCat(List<UsrProductCategory> topDiyCat) {
        this.topDiyCat = topDiyCat;
    }

    public String gtSupPro() throws JSONException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), getPkey(), 2));
        setTopDiyCat(UsrProductCategoryDAO.Sellect.getTopCat(curLanguage(), getPkey()));
        setResult("/home/shop-productCenter.jsp");
        return HomeAction.TRENDS;
    }


    /**
     * 返回供应商信息页面
     *
     * @author liyichao
     */
    public String gtSupInfo() throws JSONException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), getPkey(), 3));
        setResult("/home/shop-company.jsp");
        return HomeAction.TRENDS;
    }

    /**
     * 返回联系供应商页面
     *
     * @author liyichao
     */
    public String gtSupContact() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, JSONException {
        setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), getPkey(), 3));
        setResult("/home/shop-contactUs.jsp");
        return HomeAction.TRENDS;
    }

    private List<PrmGroupPurchase> unionList;
    private String groupState;

    public String getGroupState() {
        return groupState;
    }

    public void setGroupState(String groupState) {
        this.groupState = groupState;
    }

    public List<PrmGroupPurchase> getUnionList() {
        return unionList;
    }

    public void setUnionList(List<PrmGroupPurchase> unionList) {
        this.unionList = unionList;
    }

    private List<CategoryView> catList;

    public List<CategoryView> getCatList() {
        return catList;
    }

    public void setCatList(List<CategoryView> catList) {
        this.catList = catList;
    }

    /**
     * 返回联合采购页面
     *
     * @throws JSONException
     * @author liyichao
     */
    public String gtSupGroup() throws JSONException {
        setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), getPkey(), 1));
        PrmGroupPurchaseDAO.GroupList group = new PrmGroupPurchaseDAO.GroupList();
        group.setSellerPkey((Integer) getPkey());
        group.commit();
        unionList = group.getGroupList();
        setCatList(PdtCatDAO.listTopCatView(curLanguage()));
        setResult("/home/groupPurchase.jsp");
        return TRENDS;
    }


    /**
     * 获取指定供应商数据
     *
     * @author liyichao
     */
    private UsrSupplierDAO.GtSup getSup() {
        UsrSupplierDAO.GtSup gtSup = new UsrSupplierDAO.GtSup();
        gtSup.setBKey(getPkey());
        gtSup.commit();
        _bean = gtSup.getB();
        return gtSup;
    }

    /**
     * 供应商列表页面
     * Created by IntelliJ IDEA.
     * User: Passxml@gmail.com
     * Date: 2018/7/19
     * Time: 15:46
     */
    private int _cated = -1;

    public int getCated() {
        return _cated;
    }

    public void setCated(int cated) {
        this._cated = cated;
    }


    private Page_supplierView _supplierDto;

    public Page_supplierView getSupplierDto() {
        return _supplierDto;
    }

    public void setSupplierDto(Page_supplierView supplierDto) {
        this._supplierDto = supplierDto;
    }

    /***
     * 获取供应商列表信息 待优化，目标：json数据返回
     * @author Passxml@gmail.com
     * @param
     * @return
     * @date 2018/7/20 10:14
     */
    @Override
    public String execute() throws Exception {
        IduPage iduPage = new IduPage();
        iduPage.setStart(0);
        iduPage.setLimit(3);
        iduPage.setWhere(String.valueOf(getCated()));
        Page_supplierView page_supplierDto = new Page_supplierView();
        page_supplierDto.setRecommendList(usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage()));
        iduPage.setStart(getPage());
        iduPage.setWhere(String.valueOf(getCated()));
        int i = getLimit();
        if (i == 0) {
            i = 6;
        }
        iduPage.setStart(3);
        iduPage.setLimit(i);
        page_supplierDto.setManufacturersList(usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage()));
        page_supplierDto.setCategory(UsrSupplierCategoryDAO.listViewIsTop(curLanguage()));
        _supplierDto = page_supplierDto;
        setResult("/home/supplier.jsp");
        return TRENDS;
    }


    /***
     * 获取供应商列表信息 待优化，目标：json数据返回
     * @author liyichao
     * @date 2018/10/11
     */
    public void getSupplierList() throws JsonProcessingException, IOException {
        IduPage page = new IduPage();
        page.setStart(getStart());
        page.setLimit(getLimit());
        Map map = pageSelect.SupplierList(page, getCated(), curLanguage());
        write(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(map));
    }

    /***
     * ajax返回供应商及三个商品列表
     * @author lijie@shoestp.cn
     * @param
     * @return
     * @date 2018/7/20 16:27
     */
    public void gtSupplierAndPdtListAjax() throws Exception {
        IduPage iduPage = new IduPage();
        iduPage.setStart(getPage());
        iduPage.setLimit(getLimit());
        //修改
        iduPage.setWhere(String.valueOf(getCated()));
        write(new ObjectMapper().writeValueAsString(usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage())));

    }

//    /***
//     * ajax返回供应商列表
//     * @author lijie@shoestp.cn
//     * @param
//     * @return
//     * @date 2018/7/20 16:27
//     */
//    public void gtSupplierAjax() throws Exception {
//        UsrSupplierDAO.pageSelect pageSelect = new UsrSupplierDAO.pageSelect();
//        IduPage iduPage = new IduPage();
//        iduPage.setStart(getPage());
//        iduPage.setLimit(getLimit());
//        write(pageSelect.getSupplierList(iduPage, getCated()));
//    }

    /***
     * ajax返回供应商列表
     * @author lijie@shoestp.cn
     * @param
     * @return
     * @date 2018/7/20 16:27
     */
    public void gtSupplierAndPdtAjax() throws Exception {
        UsrSupplierDAO.pageSelect pageSelect = new UsrSupplierDAO.pageSelect();
        IduPage iduPage = new IduPage();
        iduPage.setStart(getPage());
        iduPage.setLimit(getLimit());
        JSONObject json = new JSONObject();
        json.put(STORE_TOTAL, BeanBase.list(UsrSupplier.class, "", false).size());
        json.put(STORE_ROOT, new JSONArray(usrSupplierService.getSupplierListAndPdtList(iduPage, HomeAction.curLanguage()), false));
        writerOrExport(json);
    }

    @Override
    public void list() throws Exception {
        super.list();
    }


    /**
     * 转发页面到店铺内产品详情
     *
     * @return
     * @throws Exception
     */
    public String goProduct() throws Exception {
        setResult("/home/storegoosinfo.jsp");
        return TRENDS;

    }


    private Byte entryStep;

    /**
     * 商家入驻页面
     *
     * @return
     */
    @NeedLogin
    public String supplierEntry() {
        UserView user = getUser();
        if (!user.isSupplier())
            entryStep = 0;
        else if (user.getSupplier().gtStatus() == OStatus.INIT) {
            entryStep = 4;
        } else if (user.getSupplier().gtStatus() == OStatus.APPR) {
            setResult("/seller", false);
            return RTRENDS;
        }
        setResult("/home/supplier-entry.jsp");
        return TRENDS;
    }
//
//    public String enterSupPage() {
//        if (getPurchase() == null) {
//            setResult("/home/home.jsp");
//        }
//        if (UsrSupplier.chkUniqueLogin_name(false, getPurchase().getLoginName()) != null) {
//            setResult("/seller");
//            return RTRENDS;
//        } else {
//            pltCountry = BeanBase.list(PltCountry.class, "1=1", false);
//            usrSupplierCategory = BeanBase.list(UsrSupplierCategory.class, "1=1", false);
//            setResult("/home/storeapplication0.jsp");
//        }
//        return TRENDS;
//    }

    private SupplierView view;

    /**
     * 商家入驻信息提交
     *
     * @author yingjianhua
     */
    @NeedLogin
    public void applyEntry() throws Exception {
        UsrSupplier supplier = UsrSupplierDAO.apply(view, getPurchase().getPkey(), curLanguage());
        UserView user = getUser();
        user.setSupplier(supplier);
        setUser(user);
        write();
    }
//
//	/**
//     * 商家入驻
//     * @throws Exception
//     */
//    public void enterSup() throws Exception {
//        UsrSupplierDAO.Enter ins = new UsrSupplierDAO.Enter();
//        ins.setPurchase(getPurchase());
//        ins.setB(getBean());
//        ins.commit();
//        writeSuccess(ins.getB());
//    }

    private List<PltProvince> pltProvince;
    private Integer id;

    public void pltprovince() throws Exception {
        pltProvince = BeanBase.list(PltProvince.class, PltProvince.T.MAIN + " = " + getId(), false);
        JSONArray proJSON = new JSONArray(pltProvince, false);
        JSONObject json = new JSONObject();
        json.put(STORE_ROOT, proJSON);
        writerOrExport(json);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<PltProvince> getPltProvince() {
        return pltProvince;
    }

    public void setPltProvince(List<PltProvince> pltProvince) {
        this.pltProvince = pltProvince;
    }

    public SupplierView getView() {
        return view;
    }

    public void setView(SupplierView view) {
        this.view = view;
    }

    public Byte getEntryStep() {
        return entryStep;
    }

    public void setEntryStep(Byte entryStep) {
        this.entryStep = entryStep;
    }

}
