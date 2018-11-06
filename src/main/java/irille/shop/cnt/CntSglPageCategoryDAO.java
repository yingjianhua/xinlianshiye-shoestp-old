package irille.shop.cnt;

import irille.pub.Log;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.util.TranslateLanguage.translateUtil;

public class CntSglPageCategoryDAO {
    public static final Log LOG = new Log(CntSglPageCategoryDAO.class);

    public static class Ins extends IduIns<Ins, CntSglPageCategory> {
        @Override
        public void valid() {
            FormValid().validNotEmpty(CntSglPageCategory.T.PAGE_NAME);
//             RegexValid().validAZLen(8, );
            RegexValid().validNotStartWithNumber(CntSglPageCategory.T.PAGE_NAME);
            super.valid();
        }

        @Override
        public void before() {
            getB().setCreateTime(Env.getTranBeginTime());
            getB().setCreateBy(Idu.getUser().getPkey());

            CntSglPageCategory category = translateUtil.autoTranslate(getB());
            setB(category);

            super.before();
        }
    }

    public static class Upd extends IduUpd<Upd, CntSglPageCategory> {
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

            FormValid().validNotEmpty(CntSglPageCategory.T.PAGE_NAME);
//            RegexValid().validAZLen(8, CntSglPageCategory.T.LANG);
            RegexValid().validNotStartWithNumber(CntSglPageCategory.T.PAGE_NAME);

            super.valid();
        }
    }

    public static class Del extends IduDel<Del, CntSglPageCategory> {
        @Override
        public void before() {
            super.before();
        }

        @Override
        public void valid() {
            super.valid();
        }
    }


}