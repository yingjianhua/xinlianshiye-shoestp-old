package irille.shop.usr;

import irille.platform.usr.View.UsrSupplierCategoryActionView.UsrSupplierCategoryView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.usr.UsrSupplierCategory.T;
import irille.view.Page;
import irille.view.usr.SupplierCategoryView;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsrSupplierCategoryDAO {
    public static final Log LOG = new Log(UsrSupplierCategoryDAO.class);

    public enum Msgs implements PubInfs.IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
        equalErr("上级不能是【{0}】"),
        alreadyErr("【{0}】已经有上级"),
        ;
        private String _msg;

        private Msgs(String msg) {
            _msg = msg;
        }

        public String getMsg() {
            return _msg;
        }
    } //@formatter:on

    /**
     * 列表供应商一级分类
     *
     * @author yingjianhua
     */
    public static List<SupplierCategoryView> listViewIsTop(Language lang) {

        return Query
                .SELECT(lang, T.PKEY, T.SHOW_NAME)
                .FROM(UsrSupplierCategory.class)
                .queryList()
                .stream()
                .map(bean -> new SupplierCategoryView() {{
                    setId(bean.getPkey());
                    setName(bean.getShowName());
                }})
                .collect(Collectors.toList());

    }

    /**
     * 列表供应商分类
     *
     * @author liyichao
     */
    public static List<irille.sellerAction.view.SupplierCategoryView> listViews() {
        return Query
                .SELECT(T.PKEY, T.SHOW_NAME)
                .FROM(UsrSupplierCategory.class)
                .queryList()
                .stream()
                .map(bean -> new irille.sellerAction.view.SupplierCategoryView() {{
                    setId(bean.getPkey());
                    setShowname(bean.getShowName());
                }})
                .collect(Collectors.toList());

    }

    /**
     * 列表供应商分类
     *
     * @author liyichao
     */
    public static List<SupplierCategoryView> listView() {
        return Query
                .SELECT(T.PKEY, T.NAME)
                .FROM(UsrSupplierCategory.class)
                .queryList()
                .stream()
                .map(bean -> new SupplierCategoryView() {{
                    setId(bean.getPkey());
                    setName(bean.getName());
                }})
                .collect(Collectors.toList());

    }


    public static class Ins extends IduIns<Ins, UsrSupplierCategory> {
        @Override
        public void before() {
            super.before();
            getB().stCreateBy(getUser());
            getB().setCreateTime(Env.getTranBeginTime());
            setB(translateUtil.autoTranslate(getB()));
        }
    }

    public static class Upd extends IduUpd<Upd, UsrSupplierCategory> {
        @Override
        public void before() {
            super.before();
            setB(translateUtil.autoTranslateByManageLanguage(getB(), true));
            UsrSupplierCategory dbBean = loadThisBeanAndLock();
            PropertyUtils.copyPropertiesWithout(dbBean, getB(), T.PKEY, T.CREATE_BY, T.CREATE_TIME);
            setB(dbBean);
        }
    }

    public static class Del extends IduDel<Del, UsrSupplierCategory> {
        public void valid() {
            super.valid();
            SQL sql = new SQL() {{
                SELECT(UsrSupplierCategory.class);
                FROM(UsrSupplierCategory.class);
                WHERE(T.CATEGORY_UP, "=?").PARAM(getB().getPkey());
            }};
            SqlQuery query = Query.sql(sql);
            Integer count = query.queryCount();
            if (count > 0) {
                throw LOG.err("hasChild", "存在下级分类,不可删除");
            }
        }
    }

    public static class InsInit extends IduOther<InsInit, UsrSupplierCategory> {
        private Stream<String> stream = Stream.empty();

        public Stream<String> getStream() {
            return stream;
        }

        public void setStream(Stream<String> stream) {
            this.stream = stream;
        }

        @Override
        public void run() {
            super.run();
            stream.forEach((name) -> {
                UsrSupplierCategory b = new UsrSupplierCategory().init();
                b.setName(name);
                b.setShowName(name);
                b.ins();
            });
        }
    }

    /**
     * —————————————————分割线(新平台)———————————————————————————
     */
    public static Page getUsrSupplierCategoryList(Integer start, Integer limit, String name, Integer supplierCategory) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        SQL sql = new SQL() {
            {
                SELECT(UsrSupplierCategory.class).FROM(UsrSupplierCategory.class);
                if (name != null) {
                    WHERE(T.NAME, "like '%" + name + "%'");
                }
                if (supplierCategory != null) {
                    WHERE(T.CATEGORY_UP, "=?", supplierCategory);
                }
            }
        };
        Integer count = Query.sql(sql.LIMIT(start, limit)).queryCount();
        List<UsrSupplierCategoryView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(o -> new UsrSupplierCategoryView() {{
            setId((Integer) o.get(T.PKEY.getFld().getCodeSqlField()));
            setName((String) o.get(T.NAME.getFld().getCodeSqlField()));
            setShowName((String) o.get(T.SHOW_NAME.getFld().getCodeSqlField()));
            setCategoryUpId((Integer) o.get(T.CATEGORY_UP.getFld().getCodeSqlField()));
            if (o.get(T.CATEGORY_UP.getFld().getCodeSqlField()) != null) {
                setCategoryUp(BeanBase.load(UsrSupplierCategory.class, (Integer) o.get(T.CATEGORY_UP.getFld().getCodeSqlField())).getName());
            }
            setCreateBy(Byte.valueOf(String.valueOf(o.get(T.CREATE_BY.getFld().getCodeSqlField()))));
            setCreateTime((Date) o.get(T.CREATE_TIME.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    public static class deletes extends IduDel<deletes, UsrSupplierCategory> {
        @Override
        public void before() {
            super.before();
        }
    }

    private static void valids(UsrSupplierCategory b) {
        if (b.getCategoryUp() != null) {
            if (b.getCategoryUp().equals(b.getPkey())) {
                throw LOG.err(Msgs.equalErr, b.getName());
            }
            UsrSupplierCategory usrSupplierCategory = BeanBase.load(UsrSupplierCategory.class, b.getCategoryUp());
            if (usrSupplierCategory.getCategoryUp() != null) {
                throw LOG.err(Msgs.alreadyErr, usrSupplierCategory.getName());
            }
        }
    }

    public static class UpdCategory extends IduOther<UpdCategory, UsrSupplierCategory> {
        @Override
        public void before() {
        }

        @Override
        public void valid() {
            valids(getB());
        }

        @Override
        public void run() {
            UsrSupplierCategory dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), T.NAME, T.SHOW_NAME, T.CATEGORY_UP);
            dbBean.upd();
            super.run();
        }
    }

    public static class InsCategory extends IduIns<InsCategory, UsrSupplierCategory> {
        @Override
        public void valid() {
            valids(getB());
        }

        @Override
        public void before() {
            getB().setCreateTime(Env.getTranBeginTime());
            setB(translateUtil.autoTranslate(getB()));
            super.before();
        }
    }

    /**—————————————————分割线(新平台)END———————————————————————————*/
}
