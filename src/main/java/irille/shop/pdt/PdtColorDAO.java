package irille.shop.pdt;

import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import irille.core.sys.SysUser;
import irille.platform.cnt.View.CntSqlPagelistView;
import irille.platform.pdt.View.PdtColorView;
import irille.pub.bean.sql.SQL;
import irille.shop.cnt.CntMagazine;
import irille.shop.cnt.CntMagazineDAO;
import irille.shop.cnt.CntSglPage;
import irille.view.Page;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.statistics.Table;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidForm;
import irille.sellerAction.SellerAction;
import irille.shop.pdt.PdtCatDAO.Upd;
import irille.shop.pdt.PdtColor.T;
import irille.shop.plt.PltConfigDAO;

public class PdtColorDAO {
    public static final Log LOG = new Log(PdtColorDAO.class);

    public static void main(String[] args) {
        PdtColor.TB.getCode();
        new Table(Query.SELECT(T.PKEY, T.NAME).FROM(PdtColor.class).queryMaps()).print();
    }


    /**
     * 查询产品颜色列表
     *
     * @param start
     * @param limit
     * @return
     */
    public static Page listview(String name, Integer start, Integer limit) {
        if (null == start) {
            start = 0;
        }
        if (null == limit) {
            limit = 5;
        }
        SQL sql = new SQL() {{
            SELECT(PdtColor.class).FROM(PdtColor.class).WHERE(T.DELETED, "=0");
            if (name != null) {
                WHERE(PdtColor.T.NAME, "like ?", "%" + name + "%");
            }
        }};
        Integer count = Query.sql(sql).queryCount();
        List<PdtColorView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(bean -> new PdtColorView() {{
            setId((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
            setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
            System.out.println(bean.get(T.CREATE_BY.getFld().getCodeSqlField()));
            setCreatedby(BeanBase.load(SysUser.class, Integer.valueOf(String.valueOf(bean.get(T.CREATE_BY.getFld().getCodeSqlField())))).getLoginName());
            setCreatedtime((Date) bean.get(T.CREATE_TIME.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    /**
     * 新增产品颜色
     */
    public static class InsColor extends IduIns<PdtColorDAO.Ins, PdtColor> {
        @Override
        public void before() {
            getB().setDeleted(OYn.NO.getLine().getKey());
            getB().setCreateTime(Env.getTranBeginTime());
            setB(translateUtil.autoTranslate(getB()));
            super.before();
        }
    }

    /**
     * 修改产品颜色
     */
    public static class UpdColor extends IduUpd<PdtColorDAO.Upd, PdtColor> {
        @Override
        public void before() {
            PdtColor dbBean = loadThisBeanAndLock();
            getB().setCreateTime(Env.getSystemTime());//自动生成修改时间
            PropertyUtils.copyPropertiesWithout(
                    dbBean, translateUtil.autoTranslateByManageLanguage(getB(), true),
                    T.PKEY, T.SUPPLIER, T.CREATE_BY, T.CREATE_TIME, T.DELETED, T.ROW_VERSION);
            setB(dbBean);
        }
    }

    /**
     * 删除产品颜色
     */
    public static class DelColor extends IduUpd<PdtColorDAO.Upd, PdtColor> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.YES.getLine().getKey());
            PdtColor dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
            setB(dbBean);
        }
    }


    public static class InsColorBySup extends IduOther<InsColorBySup, PdtColor> {
        public void before() {
            getB().setSupplier(SellerAction.getSupplier().getPkey());
            getB().setDeleted(Sys.OYn.NO.getLine().getKey());
            getB().setCreateTime(Env.getSystemTime());
            setB(translateUtil.autoTranslate(getB()));
        }

        public void valid() {
            ValidForm.validEmpty(getB().getName(), T.NAME);
        }

        public void run() {
            PdtColor color = getB().ins();
        }

        public void after() {
            setB(translateUtil.getAutoTranslate(getB(), PltConfigDAO.supplierLanguage(SellerAction.getSupplier())));
        }
    }

    public static Integer delColorBySup(Serializable id) {
        PdtColor color = BeanBase.load(PdtColor.class, id);
        if (color.getCreateBy() != null) {
            throw LOG.err("noAccess", "您没有权限删除平台尺寸");
        }
        color.setDeleted(Sys.OYn.YES.getLine().getKey());
        color = color.upd();
        return color.getPkey();

    }

    public static class PageSelect extends IduOther<PageSelect, PdtColor> {
        private Integer type = -1;

        public List getAllColorList(FldLanguage.Language language) {
            FormaterSql sql = FormaterSql.build();
            sql.select(T.NAME).selectAs(T.PKEY, "id").select(T.CREATE_BY).eqAutoAnd(T.DELETED, OYn.NO.getLine().getKey()).Andwhere(T.SUPPLIER.getFld().getCodeSqlField() + " is null ");
            List<Map> sysColor = sql.castListMap(Bean.list(sql.buildSql(), sql.getParms()), language);
            List<Map> supColor = null;
            if (type != -1) {
                FormaterSql supSql = FormaterSql.build();
                supSql.select(T.NAME).selectAs(T.PKEY, "id").select(T.CREATE_BY).select(T.ROW_VERSION).eqAutoAnd(T.DELETED, OYn.NO.getLine().getKey()).eqAutoAnd(T.SUPPLIER, SellerAction.getSupplier().getPkey());
                supColor = supSql.castListMap(Bean.list(supSql.buildSql(), supSql.getParms()), language);
            }
            sysColor.addAll(supColor);
            return sysColor;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

    }

    public static class Ins extends IduIns<Ins, PdtColor> {

        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.NO.getLine().getKey());
//            getB().setCreateBy(getUser());
            getB().setCreateTime(Env.getTranBeginTime());
            translateUtil.autoTranslate(getB());
        }
    }

    public static class Upd extends IduUpd<Upd, PdtColor> {

        @Override
        public void before() {
            super.before();
            PdtColor dbBean = loadThisBeanAndLock();
            PropertyUtils.copyPropertiesWithout(dbBean, translateUtil.autoTranslateByManageLanguage(getB(), true), T.PKEY, T.SUPPLIER, T.CREATE_BY, T.CREATE_TIME, T.DELETED);
            setB(dbBean);

        }
    }

    public static class InsInit extends IduOther<InsInit, PdtColor> {
        private List<PdtColor> list = new ArrayList<>();

        public static PdtColor build(String name) {
            PdtColor c = new PdtColor().init();
            c.setName(name);
            return c;
        }

        @Override
        public void run() {
            super.run();
            for (PdtColor c : list) {
                c.setDeleted(OYn.NO.getLine().getKey());
                translateUtil.autoTranslate(c).ins();
            }
        }

        public List<PdtColor> getList() {
            return list;
        }

        public void setList(List<PdtColor> list) {
            this.list = list;
        }

    }

    public static class Del extends IduUpd<Upd, PdtColor> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.YES.getLine().getKey());
            PdtColor dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
            setB(dbBean);
        }
    }

}
