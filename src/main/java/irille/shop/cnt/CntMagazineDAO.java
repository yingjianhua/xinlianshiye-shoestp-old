package irille.shop.cnt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.util.TranslateLanguage.translateUtil;

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
}
