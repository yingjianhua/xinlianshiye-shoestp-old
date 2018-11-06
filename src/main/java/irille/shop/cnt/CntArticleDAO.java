package irille.shop.cnt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.util.TranslateLanguage.translateUtil;

public class CntArticleDAO {
    public static final Log LOG = new Log(CntArticleDAO.class);

    public static class Ins extends IduIns<Ins, CntArticle> {
        @Override
        public void valid() {
            FormValid().validNotEmpty(CntArticle.T.TITLE);
            translateUtil.autoTranslate(getB());
            super.valid();
        }

        @Override
        public void before() {
            getB().setCreateTime(Env.getSystemTime());
            getB().setCreateBy(Idu.getUser().getPkey());
            translateUtil.autoTranslate(getB());
            super.before();
        }
    }

    public static class Upd extends IduUpd<Upd, CntArticle> {
        @Override
        public void before() {
        	CntArticle cntArticle=loadThisBeanAndLock();
            getB().setCreateTime(Env.getSystemTime());
            getB().setCreateBy(Idu.getUser().getPkey());
            PropertyUtils.copyPropertiesWithout(cntArticle,getB(),CntArticle.T.PKEY);
            cntArticle=translateUtil.autoTranslateByManageLanguage(cntArticle,true);
            setB(cntArticle);
        }

        @Override
        public void valid() {
            FormValid().validNotEmpty(CntArticle.T.TITLE);
            super.valid();
        }
    }

    public static class Del extends IduDel<Del, CntArticle> {
        @Override
        public void before() {
            super.before();
            translateUtil.autoTranslate(getB());
        }

        @Override
        public void valid() {
            super.valid();
            translateUtil.autoTranslate(getB());
        }
    }


}