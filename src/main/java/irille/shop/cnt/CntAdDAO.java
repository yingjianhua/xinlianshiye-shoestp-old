package irille.shop.cnt;


import irille.core.sys.Sys;
import irille.core.sys.Sys.OEnabled;
import irille.homeAction.cnt.dto.CntAd_CategoryAdView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduPage;
import irille.pub.idu.IduUpdLines;
import irille.pub.svr.Env;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.shop.cnt.CntAd.OAdLocation;
import irille.shop.cnt.CntAd.OSIGNAGE;
import irille.shop.cnt.CntAd.T;
import irille.view.cnt.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CntAdDAO {
    public static final Log LOG = new Log(CntAdDAO.class);

    /***
     * 因模型重构校验，暂时未重写
     * @author lijie@shoestp.cn
     * @date 2018/7/25 16:38
     */
    public static class Ins extends IduInsLines<Ins, CntAd, CntAdLine> {
        /*
         * 自动生成建党员和建档时间
         * @see irille.pub.idu.Idu#before()
         */
        @Override
        public void before() {
            if (getB().getHeight() == null) {
                getB().setHeight(0);
            }
            if (getB().getWidth() == null) {
                getB().setWidth(0);

            }
            getB().setEnabled(Sys.OEnabled.TRUE.getLine().getKey());
            getB().setCreatedTime(Env.getSystemTime());
            getB().setCreatedBy(Idu.getUser().getPkey());
            super.before();
        }

        /*
         * 验证数据正确性
         * @see irille.pub.idu.IduIns#valid()
         */
        @Override
        public void valid() {
//            CntAd Number = CntAd.chkUniqueNumber(false, getB().get());
//            CntAd Adposition = CntAd.chkUniqueAdposition(false, getB().getAdposition());
            //验证数值唯一性
//            Validate.validUniqueErr(new Object[]
//                            {Number, Adposition}
//                    , CntAd.T..getFld().getName()
//                    , CntAd.T.AD_POSITION.getFld().getName());
            //验证不能为空
//            Validate validDate = new Validate(getB());
//            FormValid().validNotEmpty(CntAd.T.PAGENAME, CntAd.T.ADPOSITION
//                    , CntAd.T.PICPATH, CntAd.T.URL);

            //验证正整数
//            numberValid().validIntegerPositive(CntAd.T.WIDTH, CntAd.T.HEIGHT);
//            Validate.validNumber(new Integer[]
//                            {
//                                    getB().getHeight(),
//                                    getB().getWidth(),
////                                    , getB().getPiccount()
//                                    getB().getWidth()
//                                    , getB().getHeight()
//                                    , getB().getCreatedBy()}
////                    , CntAd.T.WIDTH.getFld().getName()
////                    , CntAd.T..getFld().getName()
//                    , CntAd.T.WIDTH.getFld().getName()
//                    , CntAd.T.HEIGHT.getFld().getName()
////                    , CntAd.T.NAME.getFld().getName()
//            );
//            FormValid().validNotNull(CntAd.T.CREATED_TIME);
            //验证数值不能为null
//            Validate.validNotNull(new Object[]{getB().getCreatedTime()}, CntAd.T.CREATED_TIME.getFld().getName());
//            for (CntAdLine cntAdvertisingLine : getLines()) {
//                验证不能为null
//                Validate.validNotNull(new Object[]{cntAdvertisingLine.getName(), cntAdvertisingLine.getUrl()}, cntAdvertisingLine.getUrl());
//            }
            super.valid();

        }

        /*
         * 新增子明细
         * @see irille.pub.idu.Idu#after()
         */
        @Override
        public void after() {
            insLine(getB(), getLines(), CntAdLine.T.MAIN.getFld());//新增子明细
            super.after();
        }
    }

    public static class Upd extends IduUpdLines<Upd, CntAd, CntAdLine> {
        /*
         * 验证数值正确性
         * @see irille.pub.idu.IduUpd#valid()
         */
        @Override
        public void valid() {
            super.valid();
//            //验证正整数
//            Validate.validNumber(new Integer[]
//                            {getB().getNumber()
//                                    , getB().getPiccount()
//                                    , getB().getWidth()
//                                    , getB().getHeight()
//                                    , getB().getCreateBy()}
//                    , CntAd.T.NUMBER.getFld().getName()
//                    , CntAd.T.PICCOUNT.getFld().getName()
//                    , CntAd.T.WIDTH.getFld().getName()
//                    , CntAd.T.HEIGHT.getFld().getName()
//                    , CntAd.T.NAME.getFld().getName());
//            //验证不能为空
//            Validate validDate = new Validate(getB());
//            FormValid().validNotEmpty(CntAd.T.PAGENAME, CntAd.T.ADPOSITION
//                    , CntAd.T.PICPATH, CntAd.T.URL);
//
//            //验证数值不能为null
//            Validate.validNotNull(new Object[]{getB().getCreateTime()}, CntAd.T.CREATE_TIME.getFld().getName());
//            for (CntAdLine cntAdvertisingLine : getLines()) {
//                //验证不能为null
//                Validate.validNotNull(new Object[]{cntAdvertisingLine.getName(), cntAdvertisingLine.getUrl()}, CntAd.T.NAME.getFld().getName(), CntAd.T.URL.getFld().getName());
//            }
        }

        /*
         * 定义不修改的字段
         * @see irille.pub.idu.Idu#before()
         */
        @Override
        public void before() {
            CntAd dbBean = loadThisBeanAndLock();
            PropertyUtils.copyPropertiesWithout(dbBean, getB(),CntAd.T.PKEY,CntAd.T.CREATED_BY,CntAd.T.CREATED_TIME);
            setB(dbBean);
            updLine(getB(), getLines(), CntAdLine.T.MAIN.getFld());
        }
    }


    public static class Del extends IduDel<Del, CntAd> {
        /**
         * 删除子明细
         */
        @Override
        public void before() {
            super.before();
            delLine(getLines(CntAdLine.T.MAIN, getB().getPkey()));
        }
    }
    

    /**
     * 获取指定系统和位置的广告信息,包括广告明细
     * @author yingjianhua
     */
    public static List<AdView> listViewBySignagePosition(OSIGNAGE signage, OAdLocation position, Integer start, Integer limit) {
    	List<AdView> views = new ArrayList<>();
    	Query
    	.SELECT(T.PKEY, T.CATEGORY)
    	.FROM(CntAd.class)
    	.WHERE(T.ENABLED, "=?", OEnabled.TRUE)
    	.WHERE(T.AD_POSITION, "=?", position)
    	.WHERE(T.SIGNAGE, "=?", signage)
    	.queryList().forEach(bean->{
    		views.add(new AdView(){{
    			setCategory(bean.getCategory());
    			setItems(CntAdLineDAO.listViewByMain(bean.getPkey(), start, limit));
    		}});
    	});
		return views;
    }

    /***
     *  广告查询
     * @author lijie@shoestp.cn
     * @param
     * @return
     * @date 2018/7/25 14:07
     */
    public static class pageSelect extends IduOther<pageSelect, CntAd> {
        public static final boolean Debug = false;


//        public List getHasAdCatInfos(IduPage page) {
//            return getAdList(page, CntAd.OAdLocation.PDT_CAT, CntAd.OSIGNAGE.PC, -1);
//        }

        /***
         * 获取有广告信息板块
         * @author lijie@shoestp.cn
         * @param
         * @return
         * @date 2018/7/25 16:52
         */
        public List<Map> getHasAdCatInfos(IduPage page, CntAd.OAdLocation adLocation, CntAd.OSIGNAGE osignage) {
            FormaterSql sql = FormaterSql.build(Debug);
            sql.select(
                    CntAd.T.PKEY,
                    CntAd.T.CATEGORY
            ).page(page)
                    .eq(CntAd.T.ENABLED).and()
                    .eq(CntAd.T.AD_POSITION).and()
                    .eq(CntAd.T.SIGNAGE);
            List parmList = new ArrayList();
            parmList.add(Sys.OEnabled.TRUE.getLine().getKey());
            parmList.add(adLocation.getLine().getKey());
            parmList.add(osignage.getLine().getKey());
            return sql.castListMap(BeanBase.list(sql.buildSql(), sql.getParms(parmList)));
        }

        /***
         * 获取相关分类广告
         * @author lijie@shoestp.cn
         * @param
         * @return
         * @date 2018/7/25 16:52
         */
        public List<Map> getAdsInfo(IduPage page, CntAd.OAdLocation adLocation, CntAd.OSIGNAGE osignage) {
            FormaterSql sql = FormaterSql.build(Debug);
            sql.select(
                    CntAdLine.T.NAME,
                    CntAdLine.T.IMAGE,
                    CntAdLine.T.URL
            ).from(CntAdLine.T.NAME)
                    .page(page)
                    .leftjoin(CntAd.T.PKEY, CntAdLine.T.MAIN)
                    .eq(CntAd.T.AD_POSITION).and()
                    .eq(CntAd.T.SIGNAGE).and()
                    .eq(CntAdLine.T.ENABLED).and()
                    .eq(CntAd.T.ENABLED)
                    .asc(CntAdLine.T.SORT);
            List parmList = new ArrayList();
            parmList.add(adLocation.getLine().getKey());
            parmList.add(osignage.getLine().getKey());
            parmList.add(Sys.OYn.YES.getLine().getKey());
            parmList.add(Sys.OYn.YES.getLine().getKey());
            return sql.castListMap(BeanBase.list(sql.buildSql(), sql.getParms(parmList)));
        }

        /**
         * @Description: 首页 获取 板块广告明细
         * @author lijie
         * @date 2018/7/30 9:35
         */
        public CntAd_CategoryAdView getCatAdInfoList(IduPage page, long category, CntAd.OSIGNAGE osignage) {
            CntAd_CategoryAdView cntAd_categoryAdView = new CntAd_CategoryAdView();
            List<Map<String, Object>> mainList = getCatAdInfoListByIsMain(page, category, Sys.OYn.YES.getLine().getKey(), osignage);
            if (mainList.size() > 0)
                cntAd_categoryAdView.setMain(mainList.get(0));
            cntAd_categoryAdView.setItems(getCatAdInfoListByIsMain(page, category, Sys.OYn.NO.getLine().getKey(), osignage));
            return cntAd_categoryAdView;
        }

        /**
         * @Description: 板块分类广告明细
         * @author lijie@shoestp.cn
         * @date 2018/7/30 10:00
         */
        private List<Map<String, Object>> getCatAdInfoListByIsMain(IduPage page, long category, int isMain, CntAd.OSIGNAGE osignage) {
            FormaterSql sql = FormaterSql.build(Debug);
            List parmList = new ArrayList();
            parmList.add(Sys.OEnabled.TRUE.getLine().getKey());
            sql.select(
                    CntAdLine.T.NAME,
                    CntAdLine.T.IMAGE,
                    CntAdLine.T.URL

            ).from(CntAdLine.T.NAME).page(page)
                    .asc(CntAdLine.T.SORT).leftjoin(CntAd.T.PKEY, CntAdLine.T.MAIN)
                    .eq(CntAdLine.T.ENABLED).and().
                    eq(CntAd.T.CATEGORY).and()
                    .eq(CntAdLine.T.MAIN_IMG).and()
                    .eq(CntAd.T.SIGNAGE);
            parmList.add(category);
            parmList.add(isMain);
            parmList.add(osignage.getLine().getKey());
            List result = sql.castListMap(BeanBase.list(sql.buildSql(), sql.getParms(parmList)));
            return result;
        }
    }

}
