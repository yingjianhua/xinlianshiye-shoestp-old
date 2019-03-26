package irille.shop.pub;

import java.util.List;

import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.svr.Env;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.TranslateLanguage.TranslateBean;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/9/7 Time: 13:26 */
public class PubTrantslateDAO {
  public static class Select extends IduOther<Select, PubTrantslate> {
    public TranslateBean getTransLatesByHashCode(String sourceText, String targetLanguage) {
      FormaterSql sql = FormaterSql.build();
      sql.eqAutoAnd(PubTrantslate.T.HASHCODE, sourceText.hashCode())
          .eqAutoAnd(PubTrantslate.T.TARGET, targetLanguage);
      List<PubTrantslate> list =
          PubTrantslate.list(PubTrantslate.class, sql.toWhereString(), false, sql.getParms());
      if (list.size() > 0) {
        PubTrantslate pubTrantslate = list.get(0);
        TranslateBean translateBean = new TranslateBean();
        translateBean.setTargetLanguage(pubTrantslate.getTarget());
        translateBean.setText(pubTrantslate.getTargetText());
        return translateBean;
      }
      return null;
    }
  }

  public static class Ins extends IduIns<Ins, PubTrantslate> {
    @Override
    public void before() {
      getB().setCreatedTime(Env.getSystemTime());
      super.before();
    }
  }
}
