package irille.shop.pdt;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeetingAuditRelease;
import irille.Entity.OdrerMeetings.OrderMeetingExhibition;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysEm;
import irille.platform.omt.View.ApplicationsView;
import irille.platform.pdt.View.PdtAttrCatView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.Idu;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidForm;
import irille.shop.cnt.CntAdLine;
import irille.shop.pdt.PdtAttr.T;
import irille.shop.pdt.PdtAttrDAO.Upd;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PdtAttrCatDAO {
    public static final Log LOG = new Log(PdtAttrCatDAO.class);

    /**
     * 查询属性分类
     *
     * @param start
     * @param limit
     * @param name
     * @return
     */
    public Page getAttrCat(Integer start, Integer limit, String name) {
        SQL sql = new SQL();
        sql.SELECT(PdtAttrCat.T.PKEY, "pkey");
        sql.SELECT(PdtAttrCat.T.CREATE_TIME, "time");
        sql.SELECT(PdtAttrCat.T.NAME);
        sql.SELECT(SysEm.T.NAME, "crateName");
        sql.FROM(PdtAttrCat.class);
        sql.LEFT_JOIN(SysEm.class, PdtAttrCat.T.CREATE_BY, SysEm.T.PKEY);
        sql.WHERE(PdtAttrCat.T.DELETED, "=?", 0);
        //sql.WHERE(PdtAttrCat.T.DELETED,"=?" 1);
        if (name != null) {
            sql.WHERE(PdtAttrCat.T.NAME, " like '%" + name + "%' ");
        }
        Integer count = Query.sql(sql).queryCount();
        List<PdtAttrCatView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(o -> {
            PdtAttrCatView view = new PdtAttrCatView();
            view.setId(Integer.valueOf(String.valueOf(o.get("pkey"))));
            view.setName(String.valueOf(o.get(PdtAttrCat.T.NAME.getFld().getCodeSqlField())));
            view.setCreateName(String.valueOf(o.get("crateName")));
            view.setCreateTime((Date) o.get("time"));
            return view;
        }).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    public static class InsAttrCat extends IduIns<InsAttrCat, PdtAttrCat> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.NO.getLine().getKey());
            getB().setCreateTime(Env.getSystemTime());
        }

        @Override
        public void valid() {
            super.valid();
            ValidForm.validEmpty(getB().getName(), PdtAttrCat.T.NAME);
        }
    }

    public static class UpdAttrCat extends IduUpd<UpdAttrCat, PdtAttrCat> {
        @Override
        public void before() {
            PdtAttrCat dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), PdtAttrCat.T.NAME);
            setB(dbBean);
        }

        @Override
        public void valid() {
        }
    }

    public static class Ins extends IduIns<Ins, PdtAttrCat> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.NO.getLine().getKey());
            getB().setCreateBy(getUser().getPkey());
            getB().setCreateTime(Env.getSystemTime());
        }

        @Override
        public void valid() {
            super.valid();
            ValidForm.validEmpty(getB().getName(), PdtAttrCat.T.NAME);
        }
    }

    public static class Upd extends IduUpd<Upd, PdtAttrCat> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.NO.getLine().getKey());
            getB().setCreateBy(getUser().getPkey());
            getB().setCreateTime(Env.getSystemTime());
        }

        @Override
        public void valid() {
            super.valid();
            ValidForm.validEmpty(getB().getName(), PdtAttrCat.T.NAME);
        }
    }

    public static class Del extends IduUpd<Upd, PdtAttrCat> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.YES.getLine().getKey());
            PdtAttrCat dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
            setB(dbBean);
        }
    }

    public static class InsMap extends IduOther<InsMap, PdtAttrCat> {
        Map<String, Map<String, List<String>>> categoryMap;

        public Map<String, Map<String, List<String>>> getCategoryMap() {
            return categoryMap;
        }

        public void setCategoryMap(Map<String, Map<String, List<String>>> categoryMap) {
            this.categoryMap = categoryMap;
        }

        @Override
        public void run() {
            super.run();
            try {
                for (String categoryName : categoryMap.keySet()) {
                    PdtAttrCat category = new PdtAttrCat().init();
                    category.setName(categoryName);
                    category.setDeleted(OYn.NO.getLine().getKey());
                    translateUtil.autoTranslate(category).ins();
                    for (String attrName : categoryMap.get(categoryName).keySet()) {
                        PdtAttr attr = new PdtAttr().init();
                        attr.stCategory(category);
                        attr.setName(attrName);
                        attr.setDeleted(OYn.NO.getLine().getKey());
                        translateUtil.autoTranslate(attr).ins();

                        for (String lineName : categoryMap.get(categoryName).get(attrName)) {
                            PdtAttrLine line = new PdtAttrLine().init();
                            line.setDeleted(OYn.NO.getLine().getKey());
                            line.stMain(attr);
                            line.setName(lineName);
                            translateUtil.autoTranslate(line).ins();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
