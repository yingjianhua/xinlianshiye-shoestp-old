package irille.shop.plt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.shop.plt.PltErate.T;
import irille.view.plt.CurrencyView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PltErateDAO {
    public static final Log LOG = new Log(PltErateDAO.class);

    public static class InsInit extends IduOther<InsInit, PltErate> {
        private List<PltErate> list;

        public static PltErate build(String logo, String curName, String symbol, Boolean enabled, Boolean siteCur, BigDecimal rate, Boolean supCur, BigDecimal nowrate) {
            PltErate e = new PltErate();
            e.setLogo(logo);
            e.setCurName(curName);
            e.setSymbol(symbol);
            e.stEnabled(enabled);
            e.stSiteCur(siteCur);
            e.setRate(rate);
            e.stSupCur(supCur);
            e.setNowrate(nowrate);
            e.setCreatedBy(getUser().getPkey());
            e.setCreatedTime(Env.getTranBeginTime());
            e.setRowVersion((short) 0);
            return e;
        }

        @Override
        public void run() {
            super.run();
            if (list != null) {
                for (PltErate line : list) {
                    line.ins();
                }
            }
        }

        public List<PltErate> getList() {
            return list;
        }

        public void setList(List<PltErate> list) {
            this.list = list;
        }
    }

    public static PltErate find(Integer pkey) {
        return irille.pub.bean.Query.SELECT(PltErate.class, pkey);
    }

    /**
     * 所有启用的货币
     *
     * @return
     */
    public static List<CurrencyView> listCurrencyView() {
        List<PltErate> list = BeanBase.list(PltErate.class, null, false);
        List<CurrencyView> views = new ArrayList<>();
        for (PltErate line : list) {
            if (line.gtEnabled()) {
                CurrencyView view = new CurrencyView();
                view.setId(line.getPkey());
                view.setImg(line.getLogo());
                view.setShortName(line.getCurName());
                views.add(view);
            }
        }
        return views;
    }

    public static class Query extends IduOther<Query, PltErate> {

        private static PltErate supDefCurrency = null;
        private static PltErate siteDefCurrency = null;
        private static Map<Integer, PltErate> allCurrency = null;

        public synchronized static PltErate supDefCurrency() {
            if (supDefCurrency == null) {
                synchronized (Query.class) {
                    if (supDefCurrency == null) {
                        initBuffer();
                    }
                }
            }
            return supDefCurrency;
        }

        public static PltErate siteDefCurrency() {
            if (siteDefCurrency == null) {
                synchronized (Query.class) {
                    if (siteDefCurrency == null) {
                        initBuffer();
                    }
                }
            }
            return siteDefCurrency;
        }

        public static Map<Integer, PltErate> allCurrency() {
            if (allCurrency == null) {
                synchronized (Query.class) {
                    if (allCurrency == null) {
                        initBuffer();
                    }
                }
            }
            return allCurrency;
        }

        public static PltErate get(Integer id) {
            return allCurrency().get(id);
        }

        private static void initBuffer() {
            List<PltErate> all = BeanBase.list(PltErate.class, null, false);
            allCurrency = new LinkedHashMap<Integer, PltErate>();
            for (PltErate e : all) {
                allCurrency.put(e.getPkey(), e);
                if (e.gtSupCur())
                    supDefCurrency = e;
                if (e.gtSiteCur())
                    siteDefCurrency = e;
            }
        }

        public static void clearBuffer() {
            supDefCurrency = null;
            siteDefCurrency = null;
            allCurrency = null;
        }

        public static BigDecimal getTargetPrice(BigDecimal source, BigDecimal curCurrency) {
            //得到美元商品价格
            //BigDecimal temp = source.multiply(supDefCurrency().getRate());
            // 转换目标商品价格
            return curCurrency.multiply(source);
        }
    }

    public static class Ins extends IduIns<Ins, PltErate> {
        @Override
        public void before() {
            super.before();
            getB().setCreatedTime(Env.getTranBeginTime());
            getB().stCreatedBy(getUser());
            getB().stEnabled(true);
            getB().stSiteCur(false);
            getB().stSupCur(false);
            getB().setNowrate(getB().getRate().divide(Query.supDefCurrency().getRate(), 4, RoundingMode.HALF_UP));
        }

        @Override
        public void valid() {
            FormValid().validNotEmpty(PltErate.T.SYMBOL, PltErate.T.CUR_NAME);
            numberValid().validBigDecimalPositive(PltErate.T.RATE, PltErate.T.NOWRATE);
            super.valid();
        }
    }

    public static class Upd extends IduUpd<Upd, PltErate> {
        @Override
        public void before() {
            super.before();
            PltErate model = loadThisBeanAndLock();
            PropertyUtils.copyPropertiesWithout(model, getB(), PltErate.T.PKEY, PltErate.T.ENABLED, T.SITE_CUR, T.SUP_CUR, T.CREATED_BY);
            model.setCreatedTime(Env.getTranBeginTime());
            setB(model);
            getB().setNowrate(getB().getRate().divide(Query.supDefCurrency().getRate(), 4, RoundingMode.HALF_UP));
        }

        @Override
        public void valid() {
            FormValid().validNotEmpty(PltErate.T.SYMBOL, PltErate.T.CUR_NAME);
            numberValid().validBigDecimalPositive(PltErate.T.RATE, PltErate.T.NOWRATE);
            super.valid();
        }
    }

    /**
     * 设置网站默认货币
     *
     * @author guosong
     */
    public static class Defcur extends IduOther<Defcur, PltErate> {
        @Override
        public void run() {
            super.run();
            PltErate bean = getB();
            bean.stEnabled(true);
            bean.stSiteCur(true);
            bean.upd();
            for (PltErate line : PltErate.list(PltErate.class, null, false)) {
                if (!line.equals(bean)) {
                    line.stSiteCur(false);
                    line.upd();
                }
            }
            ;
        }

        @Override
        public void after() {
            super.after();
            Query.clearBuffer();
        }
    }

    /**
     * 设置商家默认货币
     *
     * @author guosong
     */
    public static class Bcdefcur extends IduOther<Bcdefcur, PltErate> {
        @Override
        public void run() {
            super.run();
            PltErate bean = getB();
            bean.stEnabled(true);
            bean.stSupCur(true);
            bean.setNowrate(BigDecimal.ONE);
            bean.upd();
            for (PltErate line : PltErate.list(PltErate.class, null, false)) {
                if (!line.equals(bean)) {
                    line.stSupCur(false);
                    line.setNowrate(line.getRate().divide(bean.getRate(), 4, RoundingMode.HALF_UP));
                    line.upd();
                }
            }
            ;
        }

        @Override
        public void after() {
            super.after();
            Query.clearBuffer();
        }
    }

}
