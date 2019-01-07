package irille.shop.plt;


import irille.platform.plt.View.CountryView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.I18NSQL;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.*;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.plt.Plt.ErrMsgs;
import irille.shop.plt.PltCountry.T;

import java.util.List;
import java.util.stream.Collectors;

public class PltCountryDAO {
    private static final Log LOG = new Log(PltCountryDAO.class);
    private static List<PltCountry> addCountry = null;

    public static PltCountry loadByName(String name) {
        return PltCountry.chkUniqueShort_name(false, name);
    }

    public PltCountry loadById(int id) {
        return PltCountry.chk(PltCountry.class, id);
    }

    public PltProvince loadProvinceById(int id) {
        return PltProvince.chk(PltProvince.class, id);
    }

    public static List<PltCountry> list(Language lang) {
        SQL sql = new I18NSQL(lang) {{
            SELECT(T.PKEY, T.NAME, T.SHORT_NAME);
            FROM(PltCountry.class);
        }};
        return Query.sql(sql).queryList(PltCountry.class);
    }


    /**
     * 在运行plt的时候像数据库录入初始化数据
     *
     * @author yingjianhua
     */
    public static class InsInit extends IduOther<InsInit, PltCountry> {
        private List<PltCountry> list;

        public static PltCountry build(String name, String shortName, String zone, Integer currency, String nationalFlag, boolean enabled, boolean hot, boolean isDefault) {
            PltCountry c = new PltCountry();
            c.setName(name);
            c.setShortName(shortName);
            c.setZone(zone);
            c.setCurrency(currency);
            c.setNationalFlag(nationalFlag);
            c.stEnabled(enabled);
            c.stHot(hot);
            c.stIsdefault(isDefault);
            c.setCreatedBy(getUser().getPkey());
            c.setCreatedTime(Env.getTranBeginTime());
            c.setRowVersion((short) 0);
            return c;
        }

        @Override
        public void run() {
            super.run();
            if (list != null) {
                for (PltCountry line : list) {
                    line.ins();
                }
            }
        }

        public List<PltCountry> getList() {
            return list;
        }

        public void setList(List<PltCountry> list) {
            this.list = list;
        }
    }

    public static class Ins extends IduInsLines<Ins, PltCountry, PltProvince> {

        @Override
        public void before() {
            super.before();
            getB().stEnabled(true);
            getB().stHot(false);
            getB().stIsdefault(false);
            getB().setCreatedTime(Env.getSystemTime());
            getB().setCreatedBy(Idu.getUser().getPkey());
        }

        @Override
        public void after() {
            insLine(getB(), getLines(), PltProvince.T.MAIN.getFld());
            addCountry = null;
            super.after();
        }

        @Override
        public void valid() {
            super.valid();
            if (PltCountry.chkUniqueShort_name(true, getB().getShortName()) != null)
                throw LOG.err(ErrMsgs.uniqueErr, T.NAME.getFld().getName());

        }
    }

    public static class Upd extends IduUpdLines<Upd, PltCountry, PltProvince> {
        @Override
        public void before() {
            PltCountry dbBean = loadThisBeanAndLock();
            PropertyUtils.copyPropertiesWithout(dbBean, getB(), T.PKEY, T.ENABLED, T.HOT, T.CREATED_BY, T.CREATED_TIME);
            translateUtil.autoTranslateByManageLanguage(dbBean, true);
            setB(dbBean);
            String wheresql = PltProvince.T.MAIN.getFld().getCodeSqlField() + "=?";
            List<PltProvince> listOld = BeanBase.list(PltProvince.T.MAIN.getFld().getTb().getClazz(), wheresql, false, getB().pkeyValues());
            boolean insFlag;
            for (PltProvince formBean : getLines()) {
                formBean.propertySet(PltProvince.T.MAIN.getFld(), getB().getPkey());
                insFlag = true;
                for (PltProvince bean : listOld) {
                    try {
                        if (null != formBean.getPkey() && bean.getPkey().intValue() == formBean.getPkey().intValue() && !bean.getName(PltConfigDAO.manageLanguage()).equals(formBean.getName())) {
                            insFlag = false;
                            PropertyUtils.copyWithoutCollection(bean, formBean);
                            validFromOut(bean);
                            bean = translateUtil.autoTranslateByManageLanguage(bean, true);
                            bean.upd();
                            break;
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (formBean.getPkey() == null) {
                    formBean.setRowVersion((short) 0);
                    validFromOut(formBean);
                    formBean = translateUtil.autoTranslateByManageLanguage(formBean, true);
                    formBean.ins();
                }
            }
            for (PltProvince bean : listOld) {
                if (getLines().contains(bean))
                    continue;
                bean.del();
            }
        }

        @Override
        public void after() {
            addCountry = null;
            super.after();
        }
    }

    public static class Del extends IduDel<Del, PltCountry> {
        @Override
        public void before() {
            super.before();
            delLine(getLines(PltProvince.T.MAIN, getB().getPkey()));
        }

        @Override
        public void after() {
            addCountry = null;
            super.after();
        }
    }

    public static class Hot extends IduOther<Hot, PltCountry> {
        @Override
        public void run() {
            super.run();
            getB().stHot(!getB().gtHot()); //判断是否选中
            getB().upd();
        }
        @Override
        public void after() {
            addCountry = null;
            super.after();
        }
    }

    public static class enable extends IduOther<enable, PltCountry> {
        @Override
        public void run() {
            super.run();
            getB().stHot(!getB().gtEnabled()); //判断是否选中
            getB().upd();
        }
    }
    //获取国家列表  参数 国家名称
    public static List<CountryView> list(String Countryname, Integer start, Integer limit) throws Exception {
        if (null != start) {
            start = 0;
        }
        if (null != limit) {
            start = 0;
        }
        SQL sql=new SQL(){{
            SELECT(PltCountry.class).FROM(PltCountry.class);
            if(null !=Countryname){
                WHERE(T.NAME,"like '%"+Countryname+"%'");
            }
        }};
        List<CountryView> list= Query.sql(sql).limit(start,limit) .queryMaps().stream().map(bean ->new CountryView(){{
            setId((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
            setName((String)bean.get(T.NAME.getFld().getCodeSqlField()));
            setShortName((String)bean.get(T.SHORT_NAME.getFld().getCodeSqlField()));
            setZone((String)bean.get(T.ZONE.getFld().getCodeSqlField()));
            setCurrency(BeanBase.load(PltErate.class,(Integer)bean.get(T.CURRENCY.getFld().getCodeSqlField())).getSymbol());
            setFlag((String)bean.get(T.NATIONAL_FLAG.getFld().getCodeSqlField()));
            setIsenable((Integer) bean.get(T.ENABLED.getFld().getCodeSqlField())==1?true:false);
            setIshot((Integer)bean.get(T.HOT.getFld().getCodeSqlField())==1?true:false);
        }}).collect(Collectors.toList());
        return  list;
    }
}
