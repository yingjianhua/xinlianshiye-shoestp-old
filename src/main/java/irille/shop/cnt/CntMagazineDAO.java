package irille.shop.cnt;

import irille.core.sys.SysUser;
import irille.platform.cnt.View.magazineView;
import irille.pub.Log;
import irille.pub.bean.BeanBase;
import irille.view.Page;
import irille.pub.PropertyUtils;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.util.TranslateLanguage.translateUtil;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class CntMagazineDAO {
    public static final Log LOG = new Log(CntMagazineDAO.class);


    public static class Ins extends IduIns<Ins, CntMagazine> {

        @Override
        public void before() {
            getB().setCreatedTime(Env.getTranBeginTime());
            setB(translateUtil.autoTranslate(getB()));
            super.before();
        }
    }

    public static class Upd extends IduUpd<Upd, CntMagazine> {

        @Override
        public void before() {
            CntMagazine dbBean = loadThisBeanAndLock();
            if (getB().getSpecialPictures().indexOf("https://image.shoestp.com") != -1) {
                getB().setSpecialPictures(getB().getSpecialPictures().replace("https://image.shoestp.com", ""));
            }
            //getB().setCreatedTime(Env.getSystemTime());//自动生成修改时间
            PropertyUtils.copyPropertiesWithout(dbBean, getB(), CntMagazine.T.CREATED_BY
                    , CntMagazine.T.CREATED_TIME);
            setB(dbBean);
        }
    }

    public static class UpdCntMagazine extends IduUpd<Upd, CntMagazine> {

        @Override
        public void before() {
            CntMagazine dbBean = loadThisBeanAndLock();
            if (getB().getSpecialPictures().indexOf("https://image.shoestp.com") != -1) {
                getB().setSpecialPictures(getB().getSpecialPictures().replace("https://image.shoestp.com", ""));
            }
            //getB().setCreatedTime(Env.getSystemTime());//自动生成修改时间
            PropertyUtils.copyProperties(dbBean, getB(),
                    CntMagazine.T.NAME,
                    CntMagazine.T.SPECIAL_PICTURES
                    , CntMagazine.T.TIME
                    , CntMagazine.T.CYCLE
                    , CntMagazine.T.CONTENT
                    , CntMagazine.T.CONTENTURL);
            setB(dbBean);
        }
    }

    public static class del extends IduDel<del, CntMagazine> {
        @Override
        public void before() {
            super.before();

        }
    }

    public static Page listCM(String name, Integer start, Integer limit) {
        if (null == start) {
            start = 0;
        }
        if (null == limit) {
            limit = 10;
        }
        SQL sql = new SQL() {{
            SELECT(CntMagazine.class).FROM(CntMagazine.class);
            if (name != null) {
                WHERE(CntMagazine.T.NAME, "=?", name);
            }
        }};
        Integer count = Query.sql(sql).queryCount();
        List<magazineView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(bean -> new magazineView() {{
            setId((Integer) bean.get(CntMagazine.T.PKEY.getFld().getCodeSqlField()));
            setName((String) bean.get(CntMagazine.T.NAME.getFld().getCodeSqlField()));
            setSpecialPictures((String) bean.get(CntMagazine.T.SPECIAL_PICTURES.getFld().getCodeSqlField()));
            setTime((Date) bean.get(CntMagazine.T.TIME.getFld().getCodeSqlField()));
            setCycle((Integer) bean.get(CntMagazine.T.CYCLE.getFld().getCodeSqlField()));
            setContent((String) bean.get(CntMagazine.T.CONTENT.getFld().getCodeSqlField()));
            setUrl((String) bean.get(CntMagazine.T.CONTENTURL.getFld().getCodeSqlField()));
            setCreatedby(BeanBase.load(SysUser.class, Integer.valueOf(String.valueOf(bean.get(CntMagazine.T.CREATED_BY.getFld().getCodeSqlField())))).getLoginName());
            setCreatedtime((Date) bean.get(CntMagazine.T.CREATED_TIME.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    public static class remove extends IduDel<del, CntMagazine> {
        @Override
        public void before() {
            super.before();
        }
    }

    public static magazineView getMagazine(Integer id) {
        SQL sql = new SQL() {{
            SELECT(CntMagazine.class).FROM(CntMagazine.class).WHERE(CntMagazine.T.PKEY, "=?", id);
        }};
        CntMagazine cntMagazine = Query.sql(sql).query(CntMagazine.class);
        magazineView view = new magazineView();
        view.setId(cntMagazine.getPkey());
        view.setName(cntMagazine.getName());
        view.setSpecialPictures(cntMagazine.getSpecialPictures());
        view.setTime(cntMagazine.getTime());
        view.setCycle(cntMagazine.getCycle());
        view.setContent(cntMagazine.getContent());
        view.setUrl(cntMagazine.getContenturl());
        return view;
    }
}
