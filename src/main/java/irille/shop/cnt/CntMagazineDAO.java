package irille.shop.cnt;

import irille.platform.cnt.View.magazineView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.Idu;
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
            getB().setCreatedBy(Idu.getUser().getPkey());
            setB(translateUtil.autoTranslate(getB()));
            super.before();
        }
    }

    public static class Upd extends IduUpd<Upd, CntMagazine> {

        @Override
        public void before() {
            CntMagazine dbBean = loadThisBeanAndLock();
            if(getB().getSpecialPictures().indexOf("https://image.shoestp.com")!=-1) {
            	getB().setSpecialPictures(getB().getSpecialPictures().replace("https://image.shoestp.com", ""));
            }
            //getB().setCreatedTime(Env.getSystemTime());//自动生成修改时间
            PropertyUtils.copyPropertiesWithout(dbBean, getB(), CntMagazine.T.CREATED_BY
                    , CntMagazine.T.CREATED_TIME);
            setB(dbBean);
        }
    }

    public static class del extends IduDel<del, CntMagazine> {
        @Override
        public void before() {
            super.before();

        }
    }
    public static List<magazineView> listCM(String name, Integer start, Integer limit){
        if (null != start) {
            start = 0;
        }
        if (null != limit) {
            start = 0;
        }
        SQL sql=new SQL(){{
           SELECT(CntMagazine.class).FROM(CntMagazine.class);
        }};
        List<magazineView> list = Query.sql(sql).limit(start,limit) .queryMaps().stream().map(bean ->new magazineView(){{
           setId((Integer) bean.get(CntMagazine.T.PKEY.getFld().getCodeSqlField()));
           setName((String) bean.get(CntMagazine.T.NAME.getFld().getCodeSqlField()));
            setSpecialPictures((String) bean.get(CntMagazine.T.SPECIAL_PICTURES.getFld().getCodeSqlField()));
            setTime((Date) bean.get(CntMagazine.T.TIME.getFld().getCodeSqlField()));
            setCycle((Integer) bean.get(CntMagazine.T.CYCLE.getFld().getCodeSqlField()));
            setContent((String) bean.get(CntMagazine.T.CONTENT.getFld().getCodeSqlField()));
            setUrl((String) bean.get(CntMagazine.T.CONTENTURL.getFld().getCodeSqlField()));
            setCreatedby((String) bean.get(CntMagazine.T.CREATED_BY.getFld().getCodeSqlField()));
            setCreatedtime((Date) bean.get(CntMagazine.T.CREATED_TIME.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return  null;
    }
}
