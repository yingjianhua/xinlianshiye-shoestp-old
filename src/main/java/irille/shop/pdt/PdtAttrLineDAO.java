package irille.shop.pdt;

import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.platform.pdt.View.PdtAttrLineView;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.Idu;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtAttrLine.T;
import irille.view.Page;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PdtAttrLineDAO {

    /**
     * 查询产品属性
     *
     * @param start
     * @param limit
     * @return
     * @author lingjian
     * @date 2019/1/23 9:16
     */
    public static Page listAttrLine(String main, Integer start, Integer limit) {
        if (null == start) {
            start = 0;
        }
        if (null == limit) {
            limit = 5;
        }
        SQL sql = new SQL() {{
            SELECT(PdtAttrLine.class).FROM(PdtAttrLine.class).WHERE(PdtAttrLine.T.DELETED, "=0");
            if (main != null) {
                WHERE(T.MAIN, "=?", main);
            }
        }};
        Integer count = Query.sql(sql).queryCount();
        List<PdtAttrLineView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(bean -> new PdtAttrLineView() {{
            setId((Integer) bean.get(PdtAttr.T.PKEY.getFld().getCodeSqlField()));
            setName((String) bean.get(PdtAttr.T.NAME.getFld().getCodeSqlField()));
//            setCreatedBy(BeanBase.load(SysUser.class, Integer.valueOf(String.valueOf(bean.get(PdtAttr.T.CREATE_BY.getFld().getCodeSqlField())))).getLoginName());
            Integer c = (Integer) bean.get(PdtSize.T.CREATE_BY.getFld().getCodeSqlField());
            if (null != c) {
                setCreatedBy(BeanBase.load(SysUser.class, c).getLoginName());
            }
            setCreatedTime((Date) bean.get(PdtAttr.T.CREATE_TIME.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    /**
     * 新增产品属性
     *
     * @author lingjian
     * @date 2019/1/23 9:16
     */
    public static class InsAttrLine extends IduIns<PdtAttrLineDAO.InsAttrLine, PdtAttrLine> {
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
     * @date 2019/1/23 9:16
     */
    public static class UpdAttrLine extends IduUpd<PdtAttrLineDAO.UpdAttrLine, PdtAttrLine> {
        @Override
        public void before() {
            PdtAttrLine dbBean = loadThisBeanAndLock();
//            getB().setCreateTime(Env.getSystemTime());//自动生成修改时间
            PropertyUtils.copyPropertiesWithout(
                    dbBean, translateUtil.autoTranslateByManageLanguage(getB(), true),
                    PdtAttrLine.T.PKEY, PdtAttrLine.T.CREATE_BY, PdtAttrLine.T.CREATE_TIME, PdtAttrLine.T.DELETED, PdtAttrLine.T.ROW_VERSION);
            setB(dbBean);
        }
    }

    /**
     * 删除产品属性
     *
     * @author lingjian
     * @date 2019/1/23 9:16
     */
    public static class DelAttrLine extends IduUpd<PdtAttrLineDAO.DelAttrLine, PdtAttrLine> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.YES.getLine().getKey());
            PdtAttrLine dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), PdtAttrLine.T.DELETED);
            setB(dbBean);
        }
    }

    public static void updByMain(List<PdtAttrLine> list, Integer mainPkey) {
        String wheresql = T.MAIN + "=? and " + T.DELETED + "=?";
        List<PdtAttrLine> listOld = BeanBase.list(PdtAttrLine.class, wheresql, false, mainPkey, OYn.NO.getLine().getKey()); // 数据库旧数据
        boolean insFlag;
        for (PdtAttrLine formBean : (List<PdtAttrLine>) list) {
            formBean.setMain(mainPkey);
            insFlag = true; // 默认新增标志
            for (PdtAttrLine bean : listOld) {
                if (bean.equals(formBean)) {
                    insFlag = false; // 修改标志
                    PropertyUtils.copyWithoutCollection(bean, formBean);
                    Idu.validFromOut(bean);
                    bean.upd();
                    break;
                }
            }
            if (insFlag) {
                formBean.setRowVersion((short) 0);
                Idu.validFromOut(formBean);
                formBean.ins();
            }
        }
        // 删除不存的数据
        for (PdtAttrLine bean : listOld) {
            if (list.contains(bean))
                continue;
            bean.stDeleted(true);
            bean.upd();
        }
    }

    /**
     * xy
     *
     * @param mainPkey 父id
     * @return
     */
    public static List<PdtAttrLine> getListByMain(Integer... params) {
        SQL sql = new SQL();
        sql.SELECT(PdtAttrLine.T.PKEY, PdtAttrLine.T.NAME, PdtAttrLine.T.MAIN);
        sql.FROM(PdtAttrLine.class);
        for (Integer item : params) {
            sql.orWhere(PdtAttrLine.T.MAIN, " =? ", item);
        }
        return Query.sql(sql).queryList(PdtAttrLine.class);
    }
}
