package irille.shop.usr;

import irille.core.sys.Sys;
import irille.platform.usr.View.UsrConsultView;
import irille.pub.Log;
import irille.pub.Str;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.validate.ValidForm;
import irille.pub.validate.ValidRegex;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrConsult.T;
import irille.view.Page;
import irille.view.usr.ConsultRelationView;
import irille.view.usr.ConsultView;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UsrConsultDAO {
    public static final Log LOG = new Log(UsrConsultDAO.class);
    //最大询盘抢单剩余次数
    public static final int max_count = 5;

    /**
     * 查询询盘列表
     * @author lingjian
     * @date 2019/1/23 14:00
     * @param start
     * @param limit
     * @return
     */
    public static Page listview(String name,String title,String content,Integer start, Integer limit) {
        if (null == start) {
            start = 0;
        }
        if (null == limit) {
            limit = 5;
        }
        SQL sql = new SQL() {{
            SELECT(UsrConsult.class).FROM(UsrConsult.class);
            if(null != name){
                WHERE(T.NAME, "like ?", "%" + name + "%");
            }
            if(null != title){
                WHERE(T.TITLE, "like ?", "%" + title + "%");
            }
            if(null != content){
                WHERE(T.CONTENT, "like ?", "%" + content + "%");
            }
        }};
        Integer count = Query.sql(sql).queryCount();
        List<UsrConsultView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(bean -> new UsrConsultView() {{
            setId((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
            setTitle((String) bean.get(T.TITLE.getFld().getCodeSqlField()));
            setImage((String) bean.get(T.IMAGE.getFld().getCodeSqlField()));
            String country = Bean.load(PltCountry.class,(Integer) bean.get(T.COUNTRY.getFld().getCodeSqlField())).getName();
            if(null != country) {
                setCountry(country);
            }
            Integer product = (Integer) bean.get(T.PRODUCT.getFld().getCodeSqlField());
            if(null != product){
                setProduct(BeanBase.load(PdtProduct.class,product).getName());
            }
            setHaveNewMsg((Byte) bean.get(T.HAVE_NEW_MSG.getFld().getCodeSqlField()));
            setContent((String) bean.get(T.CONTENT.getFld().getCodeSqlField()));
            setIsPublic((Byte) bean.get(T.IS_PUBLIC.getFld().getCodeSqlField()));
            setCount((Integer) bean.get(T.COUNT.getFld().getCodeSqlField()));
            setQuantity((Integer) bean.get(T.QUANTITY.getFld().getCodeSqlField()));
            String purchaseName = Bean.load(UsrPurchase.class,(Integer) bean.get(T.PURCHASE.getFld().getCodeSqlField())).getName();
            if(null != purchaseName){
                setPurchase(purchaseName);
            }
            setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
            setEmail((String) bean.get(T.EMAIL.getFld().getCodeSqlField()));
            setCreatedTime((Date) bean.get(PdtColor.T.CREATE_TIME.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    public static class remove extends IduDel<UsrConsultDAO.remove, UsrConsult> {
        @Override
        public void before() {
            super.before();
        }
    }

    /**
     * 统计该采购商当前发布了几个询盘
     *
     * @param purchase
     * @return
     * @author yingjianhua
     */
    public static Integer countByPurchase(Integer purchase) {
        return Query.SELECT(UsrConsult.class).WHERE(T.PURCHASE, "=?", purchase).queryCount();
//		SQL sql = new SQL(){{
//			SELECT(UsrConsult.class);
//			FROM(UsrConsult.class);
//			WHERE(T.PURCHASE, "=?", purchase);
//		}};
//		return Query.sql(sql).queryCount();
    }

    /**
     * 发布询盘
     *
     * @author yingjianhua
     */
    public static class Publish extends IduIns<Publish, UsrConsult> {
        private ConsultView v;
        private int purchase;

        public Publish(ConsultView v, int purchase) {
            this.v = v;
            this.purchase = purchase;
        }

        @Override
        public void before() {
            UsrConsult bean = new UsrConsult();
            ValidForm vf = FormValid(bean);
            ValidRegex vr = RegexValid(bean);

            bean.setTitle(v.getTitle());
            bean.setImage(v.getImage());
            if (v.getProduct() != null) {
                vf.validExists(PdtProduct.TB, PdtProduct.T.PKEY, v.getProduct());
                bean.setProduct(v.getProduct());
                bean.setCount(0);
                bean.stIsPublic(false);
            } else if (v.getSupplierId() != null) {
                vf.validExists(UsrSupplier.TB, UsrSupplier.T.PKEY, v.getSupplierId());
                bean.setCount(0);
                bean.stIsPublic(false);
            } else {
                bean.setCount(max_count);
                bean.stIsPublic(true);
            }
            bean.stHaveNewMsg(false);
            bean.setName(v.getName());
            bean.setEmail(v.getEmail());
            bean.setPurchase(purchase);
            bean.setCountry(v.getCountry());
            bean.setContent(v.getContent());
            bean.setQuantity(v.getQuantity());
            bean.setCreateTime(Env.getTranBeginTime());
            bean.setRowVersion((short) 0);

            vf.validExists(PltCountry.TB, PltCountry.T.PKEY, v.getCountry());
            vf.validNotEmpty(T.TITLE, T.NAME, T.EMAIL, T.CONTENT, T.QUANTITY);
            vr.validEmail(T.EMAIL);

            setB(bean);
        }

        @Override
        public void after() {
            super.after();
            if (v.getProduct() != null) {
                new UsrConsultRelationDAO.Ins(getB().getPkey(), getB().gtProduct().getSupplier()).commit();
            } else if (v.getSupplierId() != null) {
                new UsrConsultRelationDAO.Ins(getB().getPkey(), v.getSupplierId()).commit();
            }
        }
    }

    /**
     * 分页查询我的询盘列表
     *
     * @param start
     * @param limit
     * @return
     * @author yingjianhua
     */
    public static Page<ConsultView> pagePrivate(int start, int limit, int purchase) {
        BeanQuery<UsrConsult> q = Query.SELECT(UsrConsult.class).WHERE(T.PURCHASE, "=?", purchase);
        Integer totalCount = q.queryCount();
        List<UsrConsult> list = q.limit(start, limit).queryList();
        List<ConsultView> views = new ArrayList<>();
        for (UsrConsult bean : list) {
            List<ConsultRelationView> rvs = new ArrayList<>();
            boolean haveNewMsg = false;
            for (UsrConsultRelation r : UsrConsultRelationDAO.listByConsult(bean.getPkey())) {
                if (r.gtSToPNewMsg())
                    haveNewMsg = true;
                ConsultRelationView rv = new ConsultRelationView();
                rv.setSupplierName(r.gtSupplier().getName());
                rvs.add(rv);
            }
            ConsultView view = new ConsultView();
            view.setId(bean.getPkey());
            view.setTitle(bean.getTitle());
            view.setImage(bean.getImage());
            view.setCreateTime(bean.getCreateTime());
            view.setHaveNewMsg(haveNewMsg);
            view.setRelations(rvs);
            view.setCount(bean.getCount());
            view.setQuantity(bean.getQuantity());
            view.setSupplierCount(UsrConsultRelationDAO.countByConsult(bean.getPkey()));
            views.add(view);
        }
        return new Page<>(views, start, limit, totalCount);
    }

    /**
     * 分页查询公共询盘列表
     *
     * @param start
     * @param limit
     * @return
     * @throws JSONException
     * @author yingjianhua
     */
    public static Page<ConsultView> pagePublic(int start, int limit, String countryName, String title, String qdvalue, Integer supplier, Language lang) throws JSONException {
        String pkeys = "";
        if (supplier != null) {
            List<UsrConsultRelation> relations = Query.SELECT(UsrConsultRelation.T.CONSULT).FROM(UsrConsultRelation.class).WHERE(supplier != null, UsrConsultRelation.T.SUPPLIER, " = ?", supplier).queryList();

            for (UsrConsultRelation r : relations) {
                if (pkeys.equals("")) {
                    pkeys += r.getConsult();
                } else {
                    pkeys += "," + r.getConsult();
                }
            }
        }


        List<ConsultView> views = new ArrayList<>();
        String symbol = "";
        if (!Str.isEmpty(qdvalue)) {
            if (qdvalue.equals("0")) {
                symbol = " not in ";
            }
            if (qdvalue.equals("1")) {
                symbol = " in ";
            }
        }
        BeanQuery<UsrConsult> sql = Query.SELECT(UsrConsult.class);

        if (!Str.isEmpty(countryName)) {
            sql.LEFT_JOIN(PltCountry.class, T.COUNTRY, PltCountry.T.PKEY);
            sql.WHERE(PltCountry.T.NAME, " like ? ", "%" + countryName + "%");
        }
        Integer totalCount = 0;
        if (!Str.isEmpty(title))
            sql.WHERE(UsrConsult.T.TITLE, "like ?", "%" + title + "%");
        if (symbol.length() > 0)
            sql.WHERE(!pkeys.equals(""), UsrConsult.T.PKEY, symbol + "(" + pkeys + ")");
        sql.WHERE(UsrConsult.T.IS_PUBLIC, "=?", Sys.OYn.YES)
                .ORDER_BY(UsrConsult.T.CREATE_TIME, " DESC ")
                .ORDER_BY(UsrConsult.T.PKEY, " DESC ");

        if (qdvalue == null || !qdvalue.equals("1") || !pkeys.equals("")) {
            totalCount = sql.queryCount();
            List<UsrConsult> list = sql.limit(start, limit).queryList();

            for (UsrConsult bean : list) {
                //为私有询盘,不显示在公共询盘列表中
                //私有询盘为,剩余抢单次数为0,并且询盘关联表有一条数据的
                //公共询盘为,剩余抢单次数不为0,或者询盘关联表数据不止一条
                PltCountry country = bean.gtCountry();
                ConsultView view = new ConsultView();
                view.setId(bean.getPkey());
                view.setTitle(bean.getTitle());
                view.setImage(bean.getImage());
                view.setCountry(country.getPkey());
                view.setCountryName(country.getName(lang));
                view.setCountryFlag(country.getNationalFlag());
                view.setName(bean.getName());
                view.setCount(bean.getCount());
                view.setSupplierCount(UsrConsultRelationDAO.countByConsult(bean.getPkey()));
                view.setContent(bean.getContent());
                view.setCreateTime(bean.getCreateTime());
                view.setEmail(bean.gtPurchase().getEmail());
                views.add(view);
            }
        }

        return new Page<>(views, start, limit, totalCount);
    }

    /**
     * 判断采购商是否是该询盘的拥有着
     *
     * @param pkey
     * @param purchase
     */
    public static boolean isOwner(Integer pkey, Integer purchase) {
        SQL sql = new SQL() {{
            SELECT(UsrConsult.class);
            FROM(UsrConsult.class);
            WHERE(T.PKEY, "=?", pkey);
            WHERE(T.PURCHASE, "=?", purchase);
        }};
        Integer c = Query.sql(sql).queryCount();
        return c > 0;
    }

    public static void delete(Integer pkey) {
        //级联删除供应商关联
        UsrConsultRelationDAO.deleteByConsult(pkey);

        //删除询盘
        SQL sql = new SQL() {{
            DELETE_FROM(UsrConsult.class);
            WHERE(T.PKEY, "=?", pkey);
        }};
        Query.sql(sql).executeUpdate();
    }

    /**
     * 获取公共询盘详情信息
     *
     * @param pkey
     * @return
     * @throws JSONException
     * @author yingjianhua
     */
    public static ConsultView load(Integer pkey, Language lang) throws JSONException {
        UsrConsult bean = UsrConsult.chk(UsrConsult.class, pkey);
        if (bean == null)
            return null;
        else {

            PltCountry country = bean.gtCountry();
            ConsultView view = new ConsultView();
            view.setTitle(bean.getTitle());
            view.setImage(bean.getImage());
            view.setQuantity(bean.getQuantity());
            if (bean.getProduct() != null) {
                view.setProduct(bean.getProduct());
                view.setProductNum(bean.gtProduct().getCode());
            }
            view.setQuantity(bean.getQuantity());
            view.setCountry(country.getPkey());
            view.setCountryName(country.getName(lang));
            view.setCountryFlag(country.getNationalFlag());
            view.setName(bean.getName());
            view.setEmail(bean.gtPurchase().getEmail());
            view.setSupplierCount(UsrConsultRelationDAO.countByConsult(bean.getPkey()));
            view.setCount(bean.getCount());
            view.setHaveNewMsg(UsrConsultRelationDAO.countPurchaseNewMsg(pkey) > 0 ? true : false);
            view.setContent(bean.getContent());
            view.setCreateTime(bean.getCreateTime());
            return view;
        }
    }
}
