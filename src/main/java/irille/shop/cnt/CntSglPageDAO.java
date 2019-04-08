package irille.shop.cnt;

import java.util.List;
import java.util.stream.Collectors;

import irille.platform.cnt.View.CntSqlPagelistView;
import irille.pub.Log;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
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
    @Override
    public void before() {}

    @Override
    public void valid() {}
  }

  /**
   * @Description: 平台单页列表显示
   *
   * @date 2019/1/14 16:17
   * @anthor wilson zhang
   */
  public static List<CntSqlPagelistView> listview() {
    SQL sql =
        new SQL() {
          {
            SELECT(
                    CntSglPage.T.PKEY,
                    CntSglPage.T.PAGE_TYPE,
                    CntSglPage.T.SORT,
                    CntSglPage.T.BRIEF,
                    CntSglPage.T.DESCRIP)
                .FROM(CntSglPage.class);
          }
        };
    List<CntSqlPagelistView> list =
        Query.sql(sql).queryMaps().stream()
            .map(
                bean ->
                    new CntSqlPagelistView() {
                      {
                        setId((Integer) bean.get(CntSglPage.T.PKEY.getFld().getCodeSqlField()));
                        setBrief((String) bean.get(CntSglPage.T.BRIEF.getFld().getCodeSqlField()));
                        setPageType(
                            (Integer) bean.get(CntSglPage.T.PAGE_TYPE.getFld().getCodeSqlField()));
                        setDescrip(
                            (String) bean.get(CntSglPage.T.DESCRIP.getFld().getCodeSqlField()));
                        setSort(
                            Integer.valueOf(
                                (short) bean.get(CntSglPage.T.SORT.getFld().getCodeSqlField())));
                      }
                    })
            .collect(Collectors.toList());
    return list;
  }

  /**
   * @Description: 平台单页列表插入
   *
   * @date 2019/1/14 16:17
   * @anthor wilson zhang
   */
  public static class Inssgl extends IduIns<Inssgl, CntSglPage> {
    @Override
    public void valid() {}

    @Override
    public void before() {
      getB().setCreateTime(Env.getSystemTime());
      super.before();
    }
  }

  /**
   * @Description: 平台单页列表修改
   *
   * @date 2019/1/14 16:17
   * @anthor wilson zhang
   */
  public static class Updsql extends IduUpd<Updsql, CntSglPage> {
    @Override
    public void before() {
      getB().setCreateTime(Env.getSystemTime());
    }

    @Override
    public void valid() {}
  }
}
