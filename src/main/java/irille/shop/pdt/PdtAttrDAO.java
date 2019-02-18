package irille.shop.pdt;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.platform.pdt.View.PdtAttrView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.*;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtAttr.T;
import irille.shop.plt.PltConfigDAO;
import irille.view.Page;
import irille.view.pdt.PdtProductVueView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PdtAttrDAO {
    public static final Log LOG = new Log(PdtAttrDAO.class);

    /**
     * 查询产品属性
     *
     * @param start
     * @param limit
     * @return
     * @author lingjian
     * @date 2019/1/22 16:06
     */
    public static Page listAttr(String name, String category, Integer start, Integer limit) {
        if (null == start) {
            start = 0;
        }
        if (null == limit) {
            limit = 5;
        }
        SQL sql = new SQL() {{
            SELECT(PdtAttr.class).FROM(PdtAttr.class).WHERE(PdtAttr.T.DELETED, "=0");
            if (name != null) {
                WHERE(PdtAttr.T.NAME, "like ?", "%" + name + "%");
            }
            if (category != null) {
                WHERE(T.CATEGORY, "=?", category);
            }
        }};
        Integer count = Query.sql(sql).queryCount();
        List<PdtAttrView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(bean -> new PdtAttrView() {{
            setId((Integer) bean.get(PdtAttr.T.PKEY.getFld().getCodeSqlField()));
            setName((String) bean.get(PdtAttr.T.NAME.getFld().getCodeSqlField()));
            setCATEGORY(Bean.load(PdtAttrCat.class, (Integer) bean.get(T.CATEGORY.getFld().getCodeSqlField())).getName());
            setCreatedTime((Date) bean.get(PdtAttr.T.CREATE_TIME.getFld().getCodeSqlField()));
            Integer c = (Integer) bean.get(PdtSize.T.CREATE_BY.getFld().getCodeSqlField());
            if (null != c) {
                setCreatedBy(BeanBase.load(SysUser.class, c).getLoginName());
            }

        }}).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    /**
     * 新增产品属性
     *
     * @author lingjian
     * @date 2019/1/22 16:23
     */
    public static class InsAttr extends IduIns<PdtAttrDAO.Ins, PdtAttr> {
        @Override
        public void before() {
            getB().setDeleted(OYn.NO.getLine().getKey());
            getB().setCreateTime(Env.getTranBeginTime());
            setB(translateUtil.autoTranslate(getB()));
            super.before();
        }
    }

    /**
     * 修改产品属性
     *
     * @author lingjian
     * @date 2019/1/22 16:23
     */
    public static class UpdAttr extends IduUpd<PdtAttrDAO.Upd, PdtAttr> {
        @Override
        public void before() {
            PdtAttr dbBean = loadThisBeanAndLock();
//            getB().setCreateTime(Env.getSystemTime());//自动生成修改时间
            PropertyUtils.copyPropertiesWithout(
                    dbBean, translateUtil.autoTranslateByManageLanguage(getB(), true),
                    PdtAttr.T.PKEY, PdtAttr.T.CREATE_BY, PdtAttr.T.CREATE_TIME, PdtAttr.T.DELETED, PdtAttr.T.ROW_VERSION);
            setB(dbBean);
        }
    }

    /**
     * 删除产品属性
     *
     * @author lingjian
     * @date 2019/1/22 16:23
     */
    public static class DelAttr extends IduUpd<PdtAttrDAO.Del, PdtAttr> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.YES.getLine().getKey());
            PdtAttr dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), PdtAttr.T.DELETED);
            setB(dbBean);
        }
    }

    public static class Ins extends IduInsLines<Ins, PdtAttr, PdtAttrLine> {

        @Override
        public void before() {
            super.before();
            getB().stCreateBy(getUser());
            getB().setCreateTime(Env.getTranBeginTime());
            getB().setDeleted(OYn.NO.getLine().getKey());
            translateUtil.autoTranslate(getB());
        }

        @Override
        public void after() {
            super.after();
            for (PdtAttrLine line : getLines()) {
                line.setDeleted(OYn.NO.getLine().getKey());
                line.stCreateBy(getUser());
                line.setCreateTime(Env.getTranBeginTime());
                translateUtil.autoTranslate(line);
            }
            insLine(getB(), getLines(), PdtAttrLine.T.MAIN.getFld());
        }

    }

    public static class PageSelect extends IduOther<PageSelect, PdtAttr> {

        /**
         * @Description: 获取商品所有规格属性
         * @author lijie@shoestp.cn
         * @date 2018/8/22 17:05
         */
        public List getAllAttr(FldLanguage.Language language) {
            List result = new ArrayList();
            //不做分类查询
            String sql = PdtAttr.T.DELETED.getFld().getCodeSqlField() + " = " + OYn.NO.getLine().getKey();
            PdtAttr.list(PdtAttr.class, sql, false).forEach(l -> {
                PdtProductVueView attr = new PdtProductVueView();
                translateUtil.getAutoTranslate(l, language);
                attr.setId(l.getPkey());
                attr.setName(l.getName());
                List lineList = new ArrayList();
                PdtAttrLine.list(PdtAttrLine.class, PdtAttrLine.T.MAIN + "=" + l.getPkey() + " AND " + PdtAttrLine.T.DELETED + " = " + OYn.NO.getLine().getKey(), false).forEach(ll -> {
                    translateUtil.getAutoTranslate(ll, language);
                    PdtProductVueView line = new PdtProductVueView();
                    line.setId(ll.getPkey());
                    line.setName(ll.getName());
                    lineList.add(line);
                });
                attr.setItems(lineList);
                result.add(attr);
            });
            return result;
        }
    }

    public static class Upd extends IduUpdLines<Upd, PdtAttr, PdtAttrLine> {
        @Override
        public void before() {
            super.before();
            try {
                JSONObject json = new JSONObject(getB().getName());
                String value = json.getString(PltConfigDAO.manageLanguage().name());
                getB().setName(value);
                PdtAttr dbBean = loadThisBeanAndLock();
                dbBean.setDeleted(Sys.OYn.NO.getLine().getKey());
                PropertyUtils.copyProperties(dbBean, translateUtil.autoTranslate(getB()), T.NAME, T.CATEGORY);
                setB(dbBean);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void valid() {
            super.valid();

        }

        @Override
        public void after() {
            super.after();
            for (PdtAttrLine line : getLines()) {
                if (line.getPkey() == null) {
                    line.setDeleted(Sys.OYn.NO.getLine().getKey());
                    line.stCreateBy(getUser());
                    line.setCreateTime(Env.getTranBeginTime());
                } else {
                    line.setDeleted(Sys.OYn.NO.getLine().getKey());
                }
                line = translateUtil.autoTranslateByManageLanguage(line, true);
            }
            PdtAttrLineDAO.updByMain(getLines(), getB().getPkey());
//            updLine(getB(),getLines(), PdtAttrLine.T.MAIN.getFld());
        }

    }

    public static class Del extends IduUpd<Upd, PdtAttr> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.YES.getLine().getKey());
            PdtAttr dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
            setB(dbBean);
            List<PdtAttrLine> list = getLines(PdtAttrLine.T.MAIN, getB().getPkey());
            for (PdtAttrLine pal : list) {
                pal.setDeleted(OYn.YES.getLine().getKey());
                pal.upd();
            }
        }
    }

}
