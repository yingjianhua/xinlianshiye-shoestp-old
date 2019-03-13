package irille.shop.plt;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import irille.platform.plt.View.Plttrantslate;
import irille.platform.plt.View.TrantslateConditionView;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.svr.Env;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.TranslateLanguage.TranslateBean;
import irille.view.Page;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/9/7 Time: 13:26 */
public class PltTrantslateDAO {

  public static void main(String[] args) {
    Class<?> bean = PltTrantslate.class;
    Field[] Fields = bean.getDeclaredFields();
    for (Field field : Fields) {
      field.setAccessible(true);
      System.out.println(field.getName().substring(1));
    }
  }

  public static class Select extends IduOther<Select, PltTrantslate> {
    public TranslateBean getTransLatesByHashCode(String sourceText, String targetLanguage) {
      FormaterSql sql = FormaterSql.build();
      sql.eqAutoAnd(PltTrantslate.T.HASHCODE, sourceText.hashCode())
          .eqAutoAnd(PltTrantslate.T.TARGET, targetLanguage);
      List<PltTrantslate> list =
          PltTrantslate.list(PltTrantslate.class, sql.toWhereString(), false, sql.getParms());
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

  public static class deltran extends IduDel<deltran, PltTrantslate> {
    @Override
    public void before() {}
  }

  public static Page ListTrantslate(
      List<TrantslateConditionView> tc, Integer start, Integer limit) {
    if (null == start) {
      start = 0;
    }
    if (null == limit || 0 == limit) {
      limit = 15;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(PltTrantslate.class).FROM(PltTrantslate.class);
            if (null != tc) {
              for (TrantslateConditionView tv : tc) {
                if (tv.getMode().equalsIgnoreCase(PltTrantslate.T.HASHCODE.toString())) {
                  WHERE(
                      PltTrantslate.T.HASHCODE, tv.getCondition() + " '%" + tv.getContent() + "%'");
                }
                if (tv.getMode().equalsIgnoreCase(PltTrantslate.T.SOURCE_TEXT.toString())) {
                  WHERE(
                      PltTrantslate.T.SOURCE_TEXT,
                      tv.getCondition() + " '%" + tv.getContent() + "%'");
                }
                if (tv.getMode().equalsIgnoreCase(PltTrantslate.T.TARGET.toString())) {
                  WHERE(PltTrantslate.T.TARGET, tv.getCondition() + " '%" + tv.getContent() + "%'");
                }
                if (tv.getMode().equalsIgnoreCase(PltTrantslate.T.TARGET_TEXT.toString())) {
                  WHERE(
                      PltTrantslate.T.TARGET_TEXT,
                      tv.getCondition() + " '%" + tv.getContent() + "%'");
                }
                if (tv.getMode().equalsIgnoreCase(PltTrantslate.T.CREATED_TIME.toString())) {

                  WHERE(
                      PltTrantslate.T.CREATED_TIME,
                      tv.getCondition() + " ?",
                      new Date(Long.parseLong(tv.getContent())));
                }
              }
            }
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<Plttrantslate> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                bean ->
                    new Plttrantslate() {
                      {
                        setId((Integer) bean.get(PltTrantslate.T.PKEY.getFld().getCodeSqlField()));
                        setHashcode(
                            (String) bean.get(PltTrantslate.T.HASHCODE.getFld().getCodeSqlField()));
                        setSourceText(
                            (String)
                                bean.get(PltTrantslate.T.SOURCE_TEXT.getFld().getCodeSqlField()));
                        setTarget(
                            (String) bean.get(PltTrantslate.T.TARGET.getFld().getCodeSqlField()));
                        setTargetText(
                            (String)
                                bean.get(PltTrantslate.T.TARGET_TEXT.getFld().getCodeSqlField()));
                        setTime(
                            (Date)
                                bean.get(PltTrantslate.T.CREATED_TIME.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }
}
