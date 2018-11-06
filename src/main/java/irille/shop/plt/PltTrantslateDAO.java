package irille.shop.plt;

import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.svr.Env;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.TranslateLanguage.TranslateBean;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/9/7
 * Time: 13:26
 */
public class PltTrantslateDAO {
    public static class Select extends IduOther<Select, PltTrantslate> {
        public TranslateBean getTransLatesByHashCode(String sourceText, String  targetLanguage) {
            FormaterSql sql = FormaterSql.build();
            sql.eqAutoAnd(PltTrantslate.T.HASHCODE, sourceText.hashCode()).eqAutoAnd(PltTrantslate.T.TARGET, targetLanguage);
            List<PltTrantslate> list = PltTrantslate.list(PltTrantslate.class, sql.toWhereString(), false, sql.getParms());
            if (list.size() > 0) {
                PltTrantslate pubTrantslate = list.get(0);
                TranslateBean translateBean = new TranslateBean();
                translateBean.setTargetLanguage(pubTrantslate.getTarget());
                translateBean.setText(pubTrantslate.getTargetText());
                return translateBean;
            }
            return null;
        }
    }

    public static class Ins extends IduIns<Ins, PltTrantslate> {
        @Override
        public void before() {
            getB().setCreatedTime(Env.getSystemTime());
            super.before();
        }
    }

}
