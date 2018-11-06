package irille.shop.cnt;

import irille.pub.Log;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.util.TranslateLanguage.translateUtil;

public class CntSglPageDAO {
    public static final Log LOG = new Log(CntSglPageDAO.class);

    public static class Ins extends IduIns<Ins, CntSglPage> {
        @Override
        public void valid() {
            FormValid().validNotEmpty(CntSglPage.T.TITLE);
//            RegexValid().validAZLen(8, CntSglPage.T.LANG);

            super.valid();
        }

        @Override
        public void before() {
            getB().setCreateTime(Env.getSystemTime());
            getB().setCreateBy(Idu.getUser().getPkey());
            setB(translateUtil.autoTranslate(getB()));
            super.before();
        }
    }

    public static class Upd extends IduUpd<Upd, CntSglPage> {
        @Override
        public void before() {
            getB().setCreateTime(Env.getSystemTime());
            getB().setCreateBy(Idu.getUser().getPkey());
            translateUtil.autoTranslateByManageLanguage(getB(), true);
            setB(getB());
            super.before();
        }

        @Override
        public void valid() {
            FormValid().validNotEmpty(CntSglPage.T.TITLE);
//            RegexValid().validAZLen(8, CntSglPage.T.LANG);
            super.valid();
        }
    }

    public static class Del extends IduDel<Del, CntSglPage> {
    }


}