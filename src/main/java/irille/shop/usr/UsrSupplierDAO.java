package irille.shop.usr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import irille.core.sys.Sys;
import irille.homeAction.usr.dto.Page_supplierInfoView;
import irille.homeAction.usr.dto.Page_supplierProductView;
import irille.pub.DateTools;
import irille.pub.LogMessage;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.*;
import irille.pub.svr.DbPool;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.DataFilters;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SEOUtils;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidForm;
import irille.pub.validate.ValidRegex;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import irille.shop.pdt.PdtSpec;
import irille.shop.plt.*;
import irille.shop.plt.PltPay.OPay_Mode;
import irille.shop.prm.PrmGroupPurchase;
import irille.shop.usr.Usr.OStatus;
import irille.shop.usr.UsrSupplier.T;
import irille.view.usr.AccountSettingsView;
import irille.view.usr.SupplierView;
import irille.view.usr.shopSettingView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsrSupplierDAO {
    public static final LogMessage LOG = new LogMessage(UsrSupplierDAO.class);

    public static SupplierView getSupplierInfo(Integer pkey) {
        UsrSupplier bean = UsrSupplier.load(UsrSupplier.class, pkey);
        SupplierView view = new SupplierView();
        view.setName(bean.getName());
        return view;
    }

    /**
     * 商家端,商家账户设置页面需要的信息
     *
     * @author yingjianhua
     */
    public static SupplierView findById4AccountSet(Integer id) {
        UsrSupplier bean = irille.pub.bean.Query.SELECT(UsrSupplier.class, id);
        SupplierView view = new SupplierView();
        view.setPkey(bean.getPkey());
        view.setLoginName(bean.getLoginName());
        view.setStatus(bean.getStatus());
        view.setName(bean.getName());
        view.setCompanyType(bean.getCompanyType());
        view.setLocation(bean.getLocation());
        view.setWebsite(bean.getWebSite());

        view.setLogo(bean.getLogo());
        view.setProduction(bean.getProduction());
        view.setDeveloper(bean.getDeveloper());
        view.setTotalEmployees(bean.getTotalEmployees());
        view.setAnnualSales(bean.getAnnualSales());
        view.setTop3Markets(bean.getTop3Markets());
        view.setMaterials(bean.getMaterials());
        view.setHeadPic(bean.getHeadPic());
        view.setDepartment(bean.getDepartment());
        view.setJobTitle(bean.getJobTitle());
        System.out.println(view);
        return view;
    }

    public static void main(String[] args) throws Exception {
        SupplierView view = findById4AccountSet(8);
        System.out.println(new ObjectMapper().writeValueAsString(view.getJobTitle()));
        //initAllSupplierPassword();
        //initAllSupplierFreight();
//    	initAdminPurchasePassword();
//    	initAllSupplierPayType();
//        initAllSupplierShowName();
//        DbPool.getInstance().releaseAll();
    }

    /**
     * @author yingjianhua
     */
    public static class Query extends IduOther<Query, PdtSpec> {
        public List<UsrSupplier> listByProduct(List<PdtProduct> products) {
            if (products.size() == 0) return new ArrayList<>();
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < products.size(); i++) {
                if (i != 0) b.append(",");
                b.append(products.get(i).getSupplier());
            }
            String where = UsrSupplier.T.PKEY.getFld().getCodeSqlField() + " in (" + b.toString() + ")";
            return BeanBase.list(UsrSupplier.class, where, false);
        }
    }

    public static class Ins extends IduIns<Ins, UsrSupplier> {
        public String _mm;
        public String _mmcheck;

        @Override
        public void before() {
            super.before();
            validatePw(_mm, _mmcheck);
            getB().stIsPro(true);
            getB().setUpdateTime(Env.getTranBeginTime());
        }

        @Override
        public void after() {
            super.after();
            getB().setPassword(inCode(getB().getPkey() + _mm));
            getB().upd();
        }
    }

    /***
     *  前端供应商列表页面查询类
     * @author lijie@shoestp.cn
     * @date 2018/7/19 15:51
     */
    public static class pageSelect extends IduOther<pageSelect, UsrSupplier> {
        private final boolean Debug = false;

        public Map SupplierList(IduPage page, int cat, FldLanguage.Language lang) {
            //获取商品总数及条数
            SQL productSQL = new SQL() {{
                SELECT("count(*)");
                FROM(PdtProduct.class);
                WHERE(PdtProduct.T.IS_VERIFY, " = " + Sys.OYn.YES.getLine().getKey());
                WHERE(PdtProduct.T.PRODUCT_TYPE, " = " + Pdt.OProductType.GENERAL.getLine().getKey());
                GROUP_BY(PdtProduct.T.SUPPLIER);
                HAVING(PdtProduct.T.SUPPLIER.getFld().getCodeSqlField() + " = " + UsrSupplier.class.getSimpleName() + "." + UsrSupplier.T.PKEY.getFld().getCodeSqlField());
            }};
            //获取供应商
            SQL sql = new SQL() {{
                SELECT(UsrSupplier.T.PKEY, UsrSupplier.T.LOGO, UsrSupplier.T.NAME, UsrSupplier.T.PROD_PATTERN);
                SELECT("(" + productSQL.toString() + ") as prodCount ");
                FROM(UsrSupplier.class);
                if (cat > 0)
                    WHERE(UsrSupplier.T.CATEGORY, " = " + cat);
                WHERE(T.STATUS, " = ?", OStatus.APPR);
                ORDER_BY(T.SORT, "asc");
                ORDER_BY(T.UPDATE_TIME, "desc");
            }};

            Map map = new HashMap();
            map.put("total", irille.pub.bean.Query.sql(sql).queryCount());
            sql.LIMIT(page.getStart(), page.getLimit());


            List<Page_supplierInfoView> views = irille.pub.bean.Query.sql(sql).queryMaps().stream().map(bean -> new Page_supplierInfoView() {{
                setPkey((Integer) bean.get(UsrSupplier.T.PKEY.getFld().getCodeSqlField()));
                setLogo((String) bean.get(UsrSupplier.T.LOGO.getFld().getCodeSqlField()));
                setName((String) bean.get(UsrSupplier.T.NAME.getFld().getCodeSqlField()));
                setProdpattern((String) bean.get(UsrSupplier.T.PROD_PATTERN.getFld().getCodeSqlField()));
                setProDuctCount(Long.valueOf((bean.get("prodCount") == null ? 0 : bean.get("prodCount")).toString()));
                SQL prodSQL = new SQL() {{
                    SELECT(PdtProduct.T.PKEY, PdtProduct.T.PICTURE, PdtProduct.T.NAME);
                    FROM(PdtProduct.class);
                    WHERE(PdtProduct.T.IS_VERIFY, "=?", Sys.OYn.YES);
                    WHERE(PdtProduct.T.SUPPLIER, "=?", getPkey());
                    WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL);
                    ORDER_BY(PdtProduct.T.IS_NEW, " DESC ");
                    LIMIT(0, 3);
                }};
                setProDuctDtos(irille.pub.bean.Query.sql(prodSQL).queryList(PdtProduct.class).stream().map(prod -> new Page_supplierProductView() {{
                    translateUtil.getAutoTranslate(prod, lang);
                    setPkey(prod.getPkey());
                    String pic = prod.getPicture() == null ? "" : (prod.getPicture().split(",").length > 0 ? prod.getPicture().split(",")[0] : prod.getPicture());
                    setPicture(pic);
                    setRewrite(SEOUtils.getPdtProductTitle((int) getPkey(), getName()));
                }}).collect(Collectors.toList()));
            }}).collect(Collectors.toList());

            map.put("items", views);
            return map;
        }


        /***
         *  首页 供应商列表
         *  审核  通过了  店铺 可以显示
         *  认证只是有个认证标识
         *
         * 主键 公司图片  图片链接 公司名
         * @return
         */
        public List getSupplierInfo(IduPage page) {
            FormaterSql sql = FormaterSql.build();
            sql.select(
                    T.PKEY,
                    T.LOGO,
                    T.COMPANY_PHOTO,
                    T.COMPANY_PHOTO_LINK,
                    T.NAME
            ).page(page)
                    .eqAutoAnd(T.STATUS, OStatus.APPR)
                    .eqAutoAnd("logo != '' and company_photo!='' ")
                    .asc(T.SORT)
                    .desc(T.UPDATE_TIME)
            ;
            List list = BeanBase.list(sql.buildSql(), sql.getParms());
            return sql.castListMap(list);
        }

        /***
         * 获取分类
         * @author lijie@shoestp.cn
         * @param
         * @return
         * @date 2018/7/23 16:58
         */
        public List getCategory() {
            FormaterSql sql = FormaterSql.build(Debug);
            sql.select(
                    UsrSupplierCategory.T.PKEY,
                    UsrSupplierCategory.T.SHOW_NAME
            );
            return BeanBase.list(sql.buildSql());
        }

        /***
         * 获取Supplier页面数据       返回供应商列表及三个商品
         * @author lijie@shoestp.cn
         * @param
         * @return
         * @date 2018/7/19 16:26
         */
        public List<Page_supplierInfoView> getSupplierListAndPdtList(IduPage page) {
            FormaterSql sql = FormaterSql.build();
            //获取所有公司
            sql.select(
                    T.PKEY,
                    T.LOGO,
                    T.PROD_PATTERN, //公司生产模式
                    T.NAME //公司名称
            ).desc(UsrSupplier.T.SORT)
                    .page(page.getStart(), page.getLimit()).eqAutoAnd(T.IS_AUTH, Usr.OIsAuth.YES);
            if (page.getWhere() != null && !page.getWhere().equalsIgnoreCase("-1")) {
                sql.leftjoin(UsrSupplierCategory.T.SHOW_NAME, T.CATEGORY)
                        .eqAutoAnd(UsrSupplierCategory.T.PKEY, page.getWhere());
            }
            List queryResult = sql.castListMap(BeanBase.list(sql.buildSql(), sql.getParms()));
            List<Page_supplierInfoView> result = new ArrayList();
            for (int i = 0; i < queryResult.size(); i++) {
                Page_supplierInfoView page_supplierDto = SetBeans.set(queryResult.get(i), Page_supplierInfoView.class);
                List proDucts = new ArrayList();
                List<Object[]> t = BeanBase.list(
                        sql.clean().select(
                                PdtProduct.T.PKEY,
                                PdtProduct.T.PICTURE,
                                PdtProduct.T.NAME
                        )
                                .desc(PdtProduct.T.MY_ORDER)
                                .eqAutoAnd(PdtProduct.T.SUPPLIER, page_supplierDto.getPkey())
                                .eqAutoAnd(PdtProduct.T.IS_VERIFY, Sys.OYn.YES.getLine().getKey())
                                .limit(3)
                                .offset(0)
                                .buildSql()
                        , sql.getParms()
                );
                page_supplierDto.setProDuctCount(
                        sql.castLong(
                                BeanBase.queryOneRow(
                                        sql.buildCountSql(), sql.getParms()
                                )
                        )
                );
                for (Object[] objects1 : t) {
                    Page_supplierProductView supplierProDuctDto = new Page_supplierProductView();
                    supplierProDuctDto.setPkey(sql.castLong(objects1));
                    supplierProDuctDto.setPicture(DataFilters.ImageUrl_NoHost_String(String.valueOf(objects1[1])));
                    supplierProDuctDto.setRewrite(SEOUtils.getPdtProductTitle((int) supplierProDuctDto.getPkey(), String.valueOf(objects1[2])));
                    proDucts.add(supplierProDuctDto);
                }
                page_supplierDto.setProDuctDtos(
                        proDucts
                );
                result.add(page_supplierDto);
            }
            return result;
        }

        /***
         * 获取供应商列表
         * @author lijie@shoestp.cn
         * @param
         * @return
         * @date 2018/7/24 16:15
         */
        public String getSupplierList(IduPage page, int cated) throws JsonProcessingException {
            FormaterSql sql = FormaterSql.build();
            sql.select(
                    T.PKEY,
                    T.COMPANY_PHOTO,  //公司图片
                    T.COMPANY_PHOTO_LINK, //公司外链图片
                    T.NAME  //公司名称
            ).asc(UsrSupplier.T.SORT).desc(T.UPDATE_TIME)
                    .page(page.getStart(), page.getLimit());
            sql.eqAutoAnd(T.CATEGORY, cated, number -> {
                return number.intValue() > 0;
            });
            Map result = new HashMap();
            result.put("total", sql.castLong(BeanBase.queryOneRow(sql.buildCountSql(), sql.getParms())));
            result.put("items", sql.castListMap(BeanBase.list(sql.buildSql(), sql.getParms())));
            return new ObjectMapper().writeValueAsString(result);
        }
    }

    public static void validatePw(String mm, String mmcheck) {
        //TODO 密码暂时不做复杂的限制
        //String ptn = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,}$";
        if (mm == null || mm.length() < 6)
            throw LOG.err("err", "密码不能小于6位!");
        //		if (mm.matches(ptn) == false)
        //			throw LOG.err("err", "密码必须含有一个字母和一个数字!");
        if (mmcheck == null || mmcheck.equals(mm) == false)
            throw LOG.err("err", "密码确认不一致");
    }

    public static class UpdBase extends IduUpd<UpdBase, UsrSupplier> {
        @Override
        public void before() {
            super.before();
            UsrSupplier dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(),
                    T.NAME,
                    T.CATEGORY,
                    T.IS_AUTH,
                    T.SORT,
                    T.SEO_TITLE,
                    T.SEO_CONTENT,
                    T.AUTH_TIME,
                    T.SHOW_NAME,
                    T.ENTITY,
                    T.COMPANY_TYPE,
                    T.COMPANY_NATURE,
                    T.CREDIT_CODE,
                    T.COMPANY_ESTABLISH_TIME,
                    T.OPERATION_TERM,
                    T.MAIN_SALES_AREA,
                    T.MAIN_PROD,
                    T.PROD_PATTERN,
                    T.COMPANY_ADDR,
                    T.DES,
                    T.CONTACTS,
                    T.EMAIL,
                    T.PHONE,
                    T.TELEPHONE,
                    T.FAX,
                    T.QQ,
                    T.CERT_PHOTO,
                    T.ID_CARD_FRONT_PHOTO,
                    T.ID_CARD_BACK_PHOTO,
                    T.COOP_CERT_PHOTO);
            dbBean.setUpdateTime(Env.getTranBeginTime());
            setB(dbBean);
        }
    }

    public static class UpdPage extends IduUpd<UpdPage, UsrSupplier> {
        @Override
        public void before() {
            super.before();
            UsrSupplier dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(),
                    T.BUSINESS_TYP,
                    T.LOCATION,
                    T.PRODUCTION,
                    T.DEVELOPER,
                    T.TOTAL_EMPLOYEES,
                    T.ANNUAL_SALES,
                    T.TOP_3_MARKETS,
                    T.MATERIALS,
                    T.HEAD_PIC,
                    T.DEPARTMENT,
                    T.JOB_TITLE,
                    T.WEBSITE,
                    T.COUNTRY,
                    T.PROVINCE,
                    T.CITY,
                    T.Is_Pro,
                    T.LOGO,
                    T.SIGN_BACKGD,
                    T.AD_PHOTO,
                    T.AD_PHOTO_MOBILE,
                    T.AD_PHOTO_LINK,
                    T.COMPANY_PHOTO,
                    T.COMPANY_PHOTO_LINK);
            dbBean.setUpdateTime(Env.getTranBeginTime());
            setB(dbBean);
        }
    }

    public static class UpdDiy extends IduUpd<UpdDiy, UsrSupplier> {
        @Override
        public void before() {
            super.before();
            UsrSupplier dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(),
                    T.HOME_PAGE_DIY,
                    T.PRODUCT_PAGE_DIY,
                    T.CONTACT_PAGE_DIY,
                    T.HOME_PAGE_DIY_MOBILE,
                    T.PRODUCT_PAGE_DIY_MOBILE,
                    T.CONTACT_PAGE_DIY_MOBILE);
            dbBean.setUpdateTime(Env.getTranBeginTime());
            setB(dbBean);
        }
    }

    public static class UpdMarketing extends IduUpd<UpdMarketing, UsrSupplier> {
        @Override
        public void before() {
            super.before();
            UsrSupplier dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(),
                    T.TRACE_CODE,
                    T.WEB_SIZE_TITLE,
                    T.WEB_SITE,
                    T.TONGJI_URL,
                    T.TONGJI_PWD,
                    T.UPDATE_TIME);
            dbBean.setUpdateTime(Env.getTranBeginTime());
            setB(dbBean);
        }
    }

    private static String inCode(String str) {
        return DateTools.getDigest(str.toLowerCase());
    }

    /**
     * 更新店铺设置信息
     *
     * @author GS
     */
    public static class UpdBizDiy extends IduUpd<UpdBase, UsrSupplier> {
        @Override
        public void before() {
            super.before();
            try {
                new JSONObject(getB().getHomePageDiy());
            } catch (JSONException e) {
                try {
                    getB().setHomePageDiy(new JSONObject().put("en", getB().getHomePageDiy()).toString());
                    getB().setContactPageDiy(new JSONObject().put("en", getB().getContactPageDiy()).toString());
                    getB().setProductPageDiy(new JSONObject().put("en", getB().getProductPageDiy()).toString());
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
            setB(translateUtil.autoTranslate(getB(), true));
            UsrSupplier dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(),
                    T.BUSINESS_TYP,
                    T.LOCATION,
                    T.PRODUCTION,
                    T.DEVELOPER,
                    T.TOTAL_EMPLOYEES,
                    T.ANNUAL_SALES,
                    T.TOP_3_MARKETS,
                    T.MATERIALS,
                    T.HEAD_PIC,
                    T.DEPARTMENT,
                    T.JOB_TITLE,
                    T.WEB_SITE,
                    T.COUNTRY,
                    T.PROVINCE,
                    T.CITY,
                    T.Is_Pro,
                    T.LOGO,
                    T.SIGN_BACKGD,
                    T.AD_PHOTO,
                    T.AD_PHOTO_LINK,
                    T.AD_PHOTO_MOBILE,
                    T.COMPANY_PHOTO,
                    T.COMPANY_PHOTO_LINK,
                    T.HOME_PAGE_DIY,
                    T.PRODUCT_PAGE_DIY,
                    T.CONTACT_PAGE_DIY,
                    T.HOME_PAGE_DIY_MOBILE,
                    T.PRODUCT_PAGE_DIY_MOBILE,
                    T.CONTACT_PAGE_DIY_MOBILE,
                    T.ROLE,
                    T.HOME_PAGE_ON,
                    T.PRODUCT_PAGE_ON,
                    T.CONTACT_PAGE_ON);
            dbBean.setUpdateTime(Env.getTranBeginTime());
            setB(dbBean);
        }
    }

    /**
     * 更新账户设置
     *
     * @author zjl
     */
    public static void updAccount(Integer pkey, AccountSettingsView asv) {
        UsrSupplier bean = irille.pub.bean.Query.SELECT(UsrSupplier.class, pkey);
        bean.setProduction(asv.getProductionCapacity());
        bean.setDeveloper(asv.getTotalProduction());
        bean.setTotalEmployees(asv.getNumberEmployees());
        bean.setAnnualSales(asv.getAnnualSalesFigure());
        bean.setTop3Markets(asv.getThirdMarket());
        bean.setMaterials(asv.getMaterialSelection());
        bean.setHeadPic(asv.getAvatar());
        bean.setLogo(asv.getLogo());
        bean.setDepartment(asv.getDepartment());
        bean.setJobTitle(asv.getJobTitle());
        bean.setWebsite(asv.getWebsite());
        bean.upd();
    }

    /**
     * 更新商家logo
     *
     * @author yingjianhua
     */
    public static void updLogo(String logo, Integer id) {
        UsrSupplier bean = irille.pub.bean.Query.SELECT(UsrSupplier.class, id);
        bean.setLogo(logo);
        bean.upd();
    }

    /**
     * 获取指定供应商
     *
     * @author liyichao
     */
    public static class GtSup extends IduOther<GtSup, UsrSupplier> {
        private List<PdtProduct> proList;

        public List<PdtProduct> getProList() {
            return proList;
        }

        public void setProList(List<PdtProduct> proList) {
            this.proList = proList;
        }

        @Override
        public void before() {
            super.before();
            proList = BeanBase.list(PdtProduct.class, PdtProduct.T.SUPPLIER + " in (" + getB().getPkey() + ")", false);
        }
    }


    /*public static void main(String[] args) {
        int pkey = 1;
        String password = "123456";
        System.out.println(inCode(pkey + password));
    	//https://graph.facebook.com/debug_token?access_token=311019632992273%7C191eaf6470de890483ad21946911574c&input_token=EAAEa3uaZBIBEBAPcP3V5yTw3yb4Y6wBIxZCSQIpPaX0dd5ZCf0olpDxGFPE68EVzvXBsgoC910bkMXi6fQ8x95qDHnbB84XMovoWyZC9l5LtvEsU9obn74QjbNsdk6LwkIIpyRTC39Nmi351BuAXMwZCqYGaPa1xUp02TapLbvzin2QUgTx1KWM8lWtYp5KzlYSK1tFKKwRis64w36aBp
        try {
		String result=HttpRequestUtil.sendGet("https://graph.facebook.com/debug_token?access_token=311019632992273%7C191eaf6470de890483ad21946911574c&input_token=EAAEa3uaZBIBEBAPcP3V5yTw3yb4Y6wBIxZCSQIpPaX0dd5ZCf0olpDxGFPE68EVzvXBsgoC910bkMXi6fQ8x95qDHnbB84XMovoWyZC9l5LtvEsU9obn74QjbNsdk6LwkIIpyRTC39Nmi351BuAXMwZCqYGaPa1xUp02TapLbvzin2QUgTx1KWM8lWtYp5KzlYSK1tFKKwRis64w36aBp" ,"access_token=311019632992273%7C191eaf6470de890483ad21946911574c&input_token=EAAEa3uaZBIBEBAPcP3V5yTw3yb4Y6wBIxZCSQIpPaX0dd5ZCf0olpDxGFPE68EVzvXBsgoC910bkMXi6fQ8x95qDHnbB84XMovoWyZC9l5LtvEsU9obn74QjbNsdk6LwkIIpyRTC39Nmi351BuAXMwZCqYGaPa1xUp02TapLbvzin2QUgTx1KWM8lWtYp5KzlYSK1tFKKwRis64w36aBp", true);
		System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/

    //将所有的供应商的密码初始化为123456
    private static void initAllSupplierPassword() throws SQLException {
        List<UsrSupplier> list = irille.pub.bean.Query.SELECT(UsrSupplier.class).queryList();
        for (UsrSupplier bean : list) {
            bean.setPassword(DateTools.getDigest(bean.getPkey() + "123456"));
            bean.upd();
        }
        DbPool.getInstance().getConn().commit();
    }

    //给所有未设置运费模板的供应商初始化一条运费模板
    private static void initAllSupplierFreight() throws SQLException {
        PltFreight f = irille.pub.bean.Query.SELECT(PltFreight.class).limit(0, 1).query();
        PltFreightSeller s = new PltFreightSeller();
        s.setCompany(f.getCompany());
        s.setEnabled(f.getEnabled());
        s.setExpressUrl(f.getExpressUrl());
        s.setLogo(f.getLogo());
        s.setRowVersion((short) 1);
        s.setSort(f.getSort());
        s.setType(f.getType());
        s.setUseInterface(f.getUseInterface());
        s.setWeightMax(f.getWeightMax());
        s.setWeightMin(f.getWeightMin());
        PltFreightLine l = irille.pub.bean.Query.SELECT(PltFreightLine.class).WHERE(PltFreightLine.T.MAIN, "=?", f.getPkey()).limit(0, 1).query();
        PltFreightSellerLine sl = new PltFreightSellerLine();
        sl.setAggravatePrice(l.getAggravatePrice());
        sl.setAggravateSection(l.getAggravateSection());
        sl.setBrief(l.getBrief());
        sl.setExtraPrice(l.getExtraPrice());
        sl.setFree(l.getFree());
        sl.setFreePrice(l.getFreePrice());
        sl.setRowVersion((short) 1);
        sl.setSection(l.getSection());
        sl.setWeightPrice(l.getWeightPrice());
        sl.setWeightSection(l.getWeightSection());
        List<UsrSupplier> list = irille.pub.bean.Query.SELECT(UsrSupplier.class).queryList();
        for (UsrSupplier bean : list) {
            int c = irille.pub.bean.Query.SELECT(PltFreightSeller.class).WHERE(PltFreightSeller.T.SUPPLIER, "=?", bean.getPkey()).queryCount();
            if (c == 0) {
                s.setPkey(null);
                s.setSupplier(bean.getPkey());
                s.ins();
                sl.setPkey(null);
                sl.setMain(s.getPkey());
                sl.ins();
            }
        }
        DbPool.getInstance().getConn().commit();
    }

    //给所有的没有支付设置的供应商添加一个默认的支付设置
    public static void initAllSupplierPayType() throws JSONException, SQLException {
        PltPay p = new PltPay();
        JSONObject json = new JSONObject();
        json.put("account_name", "1");
        json.put("bank_account", "1");
        json.put("depos_it_bank_subbranch", "1");
        json.put("depos_it_bank_subbranch_ascription", "1");
        p.stMode(OPay_Mode.OFFINE_PAY);
        p.setPoundage(BigDecimal.ZERO);
        p.setExtraCosts(BigDecimal.ZERO);
        p.stEnabled(true);
        p.setPaysetting(json.toString());
        List<UsrSupplier> list = irille.pub.bean.Query.SELECT(UsrSupplier.class).queryList();
        for (UsrSupplier bean : list) {
            int c = irille.pub.bean.Query.SELECT(PltPay.class).WHERE(PltPay.T.SUPPLIER, "=?", bean.getPkey()).queryCount();
            if (c == 0) {
                p.setPkey(null);
                p.setSupplier(bean.getPkey());
                p.setRowVersion((short) 0);
                p.ins();
            }
        }
        DbPool.getInstance().getConn().commit();
    }

    //将admin@163.com的采购商的密码初始化为123456
    public static void initAdminPurchasePassword() throws SQLException {
        UsrPurchase purchase = irille.pub.bean.Query.SELECT(UsrPurchase.class).WHERE(UsrPurchase.T.LOGIN_NAME, "=?", "admin@163.com").query();
        purchase.setPassword(DateTools.getDigest(purchase.getPkey() + "123456"));
        purchase.upd();
        DbPool.getInstance().getConn().commit();

    }

    public static void initAllSupplierShowName() throws SQLException {
        List<UsrSupplier> list = irille.pub.bean.Query.SELECT(UsrSupplier.class).queryList();
        for (UsrSupplier bean : list) {
            String name = bean.getShowName();
            JSONObject showname = new JSONObject();
            Stream.of(Language.values()).forEach(lang -> {
                try {
                    showname.put(lang.name(), name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            ;
            bean.stShowName(showname);
            bean.upd();
        }
        DbPool.getInstance().getConn().commit();
    }

    public static boolean judgeAuthority(Integer pkey) {
        UsrSupplier supplier = BeanBase.load(UsrSupplier.class, pkey);
        UsrSupplierRole role = supplier.gtRole();
        String roleDetail = role.getActs();
        if (roleDetail.contains(PrmGroupPurchase.TB.getCode())) {
            return true;
        }
        return false;
    }

    /**
     * 供应商入驻后的默认排序
     */
    private static final Integer default_sort = 999;

    /**
     * 商家申请入驻
     *
     * @throws JSONException
     * @author yingjianhua
     */
    public static UsrSupplier apply(SupplierView view, Integer purchasePkey, Language lang) throws JSONException {
        UsrPurchase purchase = irille.pub.bean.Query.SELECT(UsrPurchase.class, purchasePkey);
        UsrSupplier bean = valid4Apply(view.toBean(), purchase);
        bean.setLoginName(purchase.getLoginName());
        bean.setPassword(purchase.getPassword());
        bean.stCompanyType(new JSONObject().put(lang.name(), bean.getCompanyType()));
        bean.stCompanyNature(new JSONObject().put(lang.name(), bean.getCompanyNature()));
        bean.stCompanyAddr(new JSONObject().put(lang.name(), bean.getCompanyAddr()));
        bean.stCity(new JSONObject().put(lang.name(), bean.getCity()));
        bean.stStatus(Usr.OStatus.INIT);
        bean.stRole(UsrSupplierRoleDAO.getDefault());
        bean.stIsAuth(Usr.OIsAuth.NO);
        bean.stIsPro(false);
        bean.setUpdateTime(Env.getTranBeginTime());
        bean.setSort(default_sort);
        bean.ins();
        //审核通过才将运费模板复制给供应商
        //PltFreightSellerAction pltFreightSellerAction = new PltFreightSellerAction();
        //pltFreightSellerAction.insfreight();
        return bean;
    }

    /**
     * 商家申请入驻信息校验
     *
     * @param bean
     * @return
     */
    private static UsrSupplier valid4Apply(UsrSupplier bean, UsrPurchase purchase) {

        if (UsrSupplier.chkUniqueLogin_name(false, purchase.getLoginName()) != null) {
            throw LOG.errTran("addressfrom%Has_Residence", "您已申请入驻,不能重复申请!");
        }

        ValidForm vf = new ValidForm(bean);
        ValidRegex vr = new ValidRegex(bean);

        vf.validNotEmpty(T.NAME, T.CATEGORY, T.COUNTRY, T.PROVINCE, T.CITY, T.COMPANY_ADDR, T.EMAIL, T.CREDIT_CODE, T.ENTITY);
        //不是长期的营业执照需要填写营业执照有效期
        if (!bean.gtBusinessLicenseIsSecular()) {
            vf.validNotEmpty(T.BUSINESS_LICENSE_BEGIN_TIME, T.BUSINESS_LICENSE_END_TIME);
        }
        vf.validExists(UsrSupplierCategory.TB, UsrSupplierCategory.T.PKEY, bean.getCategory());
        vf.validExists(PltCountry.TB, PltCountry.T.PKEY, bean.getCountry());
        vf.validExists(PltProvince.TB, PltProvince.T.PKEY, bean.getProvince());
        vr.validEmail(T.EMAIL);

        /* vf.validNotEmpty(T.CONTACTS, T.PHONE, T.SETTLEMENT_BANK, T.BANK_ACCOUNT, T.BANK_BRANCH, T.BANK_COUNTRY, T.BANK_PROVINCE);*/
        if (bean.getBankCountry() != -1) {
            vf.validExists(PltCountry.TB, PltCountry.T.PKEY, bean.getBankCountry());
        }
        if (bean.getBankProvince() != -1) {
            vf.validExists(PltProvince.TB, PltProvince.T.PKEY, bean.getBankProvince());
        }

        vf.validNotEmpty(T.ID_CARD, T.CERT_PHOTO, T.ID_CARD_FRONT_PHOTO, T.ID_CARD_BACK_PHOTO);

        return bean;
    }

    /**
     * 供应商审核
     *
     * @author yingjianhua
     */
    public static UsrSupplier approve(Integer pkey) {
        UsrSupplier supplier = BeanBase.load(UsrSupplier.class, pkey);
        supplier.stApprBy(Idu.getUser());
        supplier.setApprTime(Env.getTranBeginTime());
        supplier.stStatus(OStatus.APPR);
        supplier.upd();

        //初始化商家的运费设置,根据平台端的运费模板
        PltFreightSellerDAO.init(pkey, false);

        //初始化商家的支付设置,没有明确支付设置的信息 没办法初始化,TODO
        //PltPayDAO.init(supplier);

        return supplier;
    }

    /**
     * 供应商弃审
     *
     * @author yingjianhua
     */
    public static UsrSupplier unapprove(Integer pkey) {
        UsrSupplier supplier = BeanBase.load(UsrSupplier.class, pkey);
        supplier.stApprBy(Idu.getUser());
        supplier.setApprTime(Env.getTranBeginTime());
        supplier.stStatus(OStatus.INIT);
        supplier.upd();
        return supplier;
    }


    /**
     * 更新供应商信息
     *
     * @author liyichao
     */
    public static UsrSupplier updInfo(UsrSupplier supplier) {
        UsrSupplier model = BeanBase.load(UsrSupplier.class, supplier.getPkey());
        PropertyUtils.copyProperties(model, supplier,
                T.COMPANY_NATURE,//企业性质 多国语言
                T.COMPANY_TYPE,//企业类型 多国语言
                T.CATEGORY,//供应商分类
                T.WEB_SITE,//网址
                T.FAX,//传真
                T.OPERATION_TERM,//业务期限
                T.MAIN_SALES_AREA,//主销售区 多国语言
                T.MAIN_PROD,//主要产品 多国语言
                T.PROD_PATTERN,//生产模式 多国语言
                T.DES,//备注
                T.CREDIT_CODE,//信用代码
                T.QQ,//QQ
                T.TAXPAYER_TYPE,//纳税人类型
                T.HEAD_PIC,//头像
                T.CONTACTS,//联系人
                T.DEPARTMENT,//Department 多国语言
                T.JOB_TITLE,//Job_Title 多国语言
                T.PHONE,//手机 必填重要！新的询盘会通知到这里！
                T.SETTLEMENT_BANK,//结算开户行
                T.BANK_ACCOUNT,//银行账号
                T.BANK_BRANCH,//银行开户行
                T.BANK_COUNTRY,//,"开户行国家"
                T.BANK_PROVINCE,//"开户行省份"

                T.SHOW_NAME,// 网站显示名称
                T.SEO_TITLE,//客人能通过这些字搜索到店铺，多个关键字用空格分开
                T.SEO_CONTENT,// 客人能通过这些字描述决定进不进店，多个描述字用空格分开
                T.COOP_CERT_PHOTO,//合作凭证

                T.TRACE_CODE,//跟踪代码
                T.WEB_SIZE_TITLE,//自定义链接名称
                T.TONGJI_URL,//第三方统计 地址
                T.TONGJI_PWD//第三方统计 密码
        );
        translateUtil.autoTranslate(model, true).upd();
        return model;
    }

    //================<2018-9-29 && new>==================

    /**
     * 客户端,获取商家页面信息
     *
     * @author liyichao
     */
    public static class Select extends IduOther<Select, UsrSupplier> {

        /**
         * 根据pkey获取商家信息
         * id => 供应商id
         * type = 1 获取商家首页信息
         * type = 2 获取商家产品页信息
         * type = 3&4 获取商家公司页或者联系页信息
         */
        public static SupplierView getSupView(FldLanguage.Language language, Serializable id, Integer type) {
            UsrSupplier supplier = translateUtil.getAutoTranslate(BeanBase.load(UsrSupplier.class, id), language);
            SupplierView view = new SupplierView();
            PltCountry country = translateUtil.getAutoTranslate(BeanBase.load(PltCountry.class, supplier.getCountry()), language);
            PltProvince province = translateUtil.getAutoTranslate(BeanBase.load(PltProvince.class, supplier.getProvince()), language);
            //公司信息
            view.setPkey(supplier.getPkey());
            view.setLogo(supplier.getLogo());//公司logo
            view.setShowName(supplier.getShowName());//公司网站显示名称
            view.setAuthAge(getAuthTime(supplier));//公司认证年限
            view.setCompanyType(supplier.getCompanyType());//企业类型
            view.setCompanyNature(supplier.getCompanyNature());//企业性质
            //页面设置
            view.setIsAuth(supplier.getIsAuth());//公司是否通过认证
            view.setPrmAuthrity(judgeAuthorityByRole(supplier.gtRole()));//公司是否存在联合采购权限
            view.setImList(UsrSupImDAO.getEnabledImSetting(supplier.getPkey()));//店铺内聊天插件
            view.setTraceCode(supplier.getTraceCode());// 跟踪代码 STR(100)<null>
            view.setWebSizeTitle(supplier.getWebSizeTitle());// 跟踪代码 STR(100)<null>
            switch (type) {
                case 1://首页
                    view.setHomePageOn(supplier.getHomePageOn());//公司是否启用首页个性化装修
                    view.setHomePageDiy(supplier.getHomePageDiy());//公司首页个性装修
                    view.setIsPro(supplier.getIsPro());//是否展示首页产品
                    view.setMainSalesArea(supplier.getMainSalesArea());//主要销售区
                    view.setAdPhoto(supplier.getAdPhoto());//首页大轮播图
                    view.setAdPhotoLink(supplier.getAdPhotoLink());//首页大轮播图链接
                    view.setCompanyPhoto(supplier.getCompanyPhoto());//企业图片
                    view.setCompanyPhotoLink(supplier.getCompanyPhotoLink());//企业图片链接
                    view.setAdPhotoMobile(supplier.getAdPhotoMobile());
                    if (supplier.getIsPro().equals(Sys.OYn.YES.getLine().getKey())) {
                        view.setProductList(PdtProductDAO.Select.getIndexProduct(view.getPkey(), language));
                    }
                    break;
                case 2://产品中心
                    view.setProductPageOn(supplier.getProductPageOn());//公司是否启用产品页个性化装修
                    view.setProductPageDiy(supplier.getProductPageDiy());//产品页个性化装修
                    break;
                case 3://公司信息或者联系我们
                    view.setName(supplier.getName());//公司名称
                    view.setCountryName(country.getName());//公司所在地国家
                    view.setCity(supplier.getCity());//公司所在地城市
                    view.setProvinceName(province.getName());//公司所在地省份
                    view.setCreditCode(supplier.getCreditCode());//企业信用代码
                    view.setFax(supplier.getFax());//传真
                    view.setCompanyEstablishTime(supplier.getCompanyEstablishTime());//公司成立时间
                    view.setBusinessTyp(supplier.getBusinessTyp());//公司商业模式
                    view.setTop3Markets(supplier.getTop3Markets());//前三市场
                    view.setCompanyAddr(supplier.getCompanyAddr());//公司地址
                    view.setMainProd(supplier.getMainProd());//公司主要产品
                    view.setProdPattern(supplier.getProdPattern());//公司生产模式
                    view.setTelephone(supplier.getTelephone());//公司电话
                    view.setPhone(supplier.getPhone());//联系人电话
                    view.setRegisteredCapital(supplier.getRegisteredCapital());//注册资金
                    view.setEntity(supplier.getEntity());//企业法人
                    view.setWebSizeTitle(supplier.getWebSizeTitle());//企业网站标题
                    view.setWebsite(supplier.getWebsite());//企业网站
                    view.setDeveloper(supplier.getDeveloper());//开发者编号
                    view.setTotalEmployees(supplier.getTotalEmployees());//员工总数
                    view.setAnnualSales(supplier.getAnnualSales());//年销售额
                    view.setMaterials(supplier.getMaterials());//公司所选材料
                    view.setAuthTime(supplier.getAuthTime());//公司认证时间
                    view.setLocation(supplier.getLocation());//公司位置
                    //运营信息
                    view.setHeadPic(supplier.getHeadPic());//公司联系人头像
                    view.setContacts(supplier.getContacts());//联系人名称
                    view.setDepartment(supplier.getDepartment());//联系人部门
                    view.setJobTitle(supplier.getJobTitle());//联系人职位
                    //页面设置
                    view.setContactPageOn(supplier.getContactPageOn());//是否启用联系页个性化装修
                    view.setContactPageDiy(supplier.getContactPageDiy());//联系页个性化装修
                    break;
            }
            return view;
        }

    }

    /**
     * 获取公司联合采购权限
     *
     * @author liyichao
     */
    private static boolean judgeAuthorityByRole(UsrSupplierRole role) {
        String roleDetail = role.getActs();
        if (roleDetail.contains(PrmGroupPurchase.TB.getCode())) {
            return true;
        }
        return false;
    }

    /**
     * 获取公司认证时间年数
     *
     * @author liyichao
     */
    private static int getAuthTime(UsrSupplier supplier) {
        if (supplier.getAuthTime() == null) {
            return 0;
        }
        int time1 = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date authTime2 = supplier.getAuthTime();
        String time = formatter.format(authTime2);
        Calendar c = Calendar.getInstance();
        int y1 = Integer.parseInt(time);
        c.setTime(new Date());
        int y2 = c.get(Calendar.YEAR);
        time1 = y1 - y2 == 0 ? 1 : y1 - y2;
        return time1;
    }

    //================<2018-9-29 && end>==================

    /**
     * 加载获取商家端店铺装修
     *
     * @throws JSONException
     * @author wilson zhang
     */
    public static shopSettingView loadshopsetting(Integer supkey, Language lang) throws JSONException {
        UsrSupplier us = BeanBase.load(UsrSupplier.class, supkey);
        shopSettingView ssv = new shopSettingView();
        ssv.setLocation(lang == null ? us.getLocation() : us.getLocation(lang));
        ssv.setProductionCapacity(lang == null ? us.getProduction() : us.getProduction(lang));
        ssv.setTotalProduction(lang == null ? us.getDeveloper() : us.getDeveloper(lang));
        ssv.setNumberEmployees(lang == null ? us.getTotalEmployees() : us.getTotalEmployees(lang));
        ssv.setAnnualSalesFigure(lang == null ? us.getAnnualSales() : us.getAnnualSales(lang));
        ssv.setThirdMarket(lang == null ? us.getTop3Markets() : us.getTop3Markets(lang));
        ssv.setMaterialSelection(lang == null ? us.getMaterials() : us.getMaterials(lang));
        ssv.setDepartment(lang == null ? us.getDepartment() : us.getDepartment(lang));
        ssv.setJobTitle(lang == null ? us.getJobTitle() : us.getJobTitle(lang));
        ssv.setWebsite(us.getWebsite());
        ssv.setCountryPkey(us.getCountry());
        ssv.setProvincPpkey(us.getProvince());
        ssv.setCity(lang == null ? us.getCity() : us.getCity(lang));
        ssv.setIsPro(us.getIsPro());
        ssv.setAdPhotoLink(us.getAdPhotoLink());
        ssv.setCompanyPhotoLink(us.getCompanyPhotoLink());
        ssv.setHeadimgurl(us.getHeadPic());
        ssv.setLogo(us.getLogo());
        ssv.setSignBackGD(us.getSignBackgd());
        ssv.setAdPhotoMobile(us.getAdPhotoMobile());
        ssv.setAdimg(us.getAdPhoto());
        ssv.setCompanyPhoto(us.getCompanyPhoto());
        ssv.setHomePageDIY(us.getHomePageDiy());
        ssv.setProductPageDIY(us.getProductPageDiy());
        ssv.setContactPageDIY(us.getContactPageDiy());
        ssv.setShopcategory(us.getCategory());
        return ssv;
    }
}