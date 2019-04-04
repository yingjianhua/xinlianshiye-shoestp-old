package irille.shop.cnt;

import java.util.List;
import java.util.stream.Collectors;

import irille.platform.cnt.View.CntSqlPageCG;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
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
      BeanBase.executeUpdate(
          "DELETE FROM "
              + CntSglPage.TB.getCodeSqlTb()
              + "  WHERE "
              + CntSglPage.T.PAGE_TYPE.getFld().getCodeSqlField()
              + " =?",
          getB().getPkey());
    }

    @Override
    public void valid() {
      super.valid();
    }
  }

  public static List<CntSqlPageCG> listcategory() {

    SQL sql =
        new SQL() {
          {
            SELECT(CntSglPageCategory.T.PKEY, CntSglPageCategory.T.PAGE_NAME)
                .FROM(CntSglPageCategory.class);
          }
        };
    List<CntSqlPageCG> list =
        Query.sql(sql).queryMaps().stream()
            .map(
                bean ->
                    new CntSqlPageCG() {
                      {
                        setId(
                            (Integer)
                                bean.get(CntSglPageCategory.T.PKEY.getFld().getCodeSqlField()));
                        setName(
                            (String)
                                bean.get(
                                    CntSglPageCategory.T.PAGE_NAME.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    return list;
  }

  /**
   * @Description: 单页分类添加
   *
   * @date 2019/1/11 15:35
   * @anthor wilson zhang
   */
  public static class InsCategory extends IduIns<InsCategory, CntSglPageCategory> {
    @Override
    public void valid() {}

    @Override
    public void before() {
      getB().setCreateTime(Env.getTranBeginTime());
    }
  }

  public static class UpdCategory extends IduUpd<UpdCategory, CntSglPageCategory> {
    @Override
    public void before() {
      getB().setCreateTime(Env.getSystemTime());
      CntSglPageCategory cntSglPageCategory = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          cntSglPageCategory,
          getB(),
          CntSglPageCategory.T.PAGE_NAME,
          CntSglPageCategory.T.TAG,
          CntSglPageCategory.T.PAGE_TYPE_TEXT,
          CntSglPageCategory.T.CREATE_BY);
      setB(cntSglPageCategory);
    }

    @Override
    public void valid() {}
  }
}
