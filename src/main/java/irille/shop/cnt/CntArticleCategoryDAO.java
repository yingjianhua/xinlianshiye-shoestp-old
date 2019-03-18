package irille.shop.cnt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.validate.ValidRegex;

public class CntArticleCategoryDAO {
  public static final Log LOG = new Log(CntArticleCategoryDAO.class);

  public static class Ins extends IduIns<Ins, CntArticleCategory> {
    @Override
    public void valid() {

      FormValid().validNotEmpty(CntArticleCategory.T.NAME);
      ValidRegex rv = RegexValid();
      rv.validNotStartWithNumber(CntArticleCategory.T.NAME);

      super.valid();
    }

    @Override
    public void before() {
      getB().setCreateTime(Env.getTranBeginTime());
      getB().setCreateBy(Idu.getUser().getPkey());
      super.before();
    }

    @Override
    public void after() {
      CntArticleCategory dbBean = loadThisBeanAndLock();
      PropertyUtils.copyPropertiesWithout(
          dbBean, getB(), CntArticleCategory.T.CREATE_BY, CntArticleCategory.T.CREATE_TIME);
      setB(dbBean);
      super.after();
    }
  }

  public static class Upd extends IduUpd<Upd, CntArticleCategory> {
    @Override
    public void before() {

      CntArticleCategory dbBean = loadThisBeanAndLock();
      PropertyUtils.copyPropertiesWithout(
          dbBean, getB(), CntArticleCategory.T.CREATE_BY, CntArticleCategory.T.CREATE_TIME);
      setB(dbBean);
    }

    @Override
    public void valid() {
      FormValid().validNotEmpty(CntArticleCategory.T.NAME);
      ValidRegex rv = RegexValid();
      rv.validNotStartWithNumber(CntArticleCategory.T.NAME);

      //            System.out.println(getName());
      //            Validate.notEmpty(LOG, getB().getName(), CntPageType.T.NAME.getFld().getName());
      //            Validate.regex(LOG, "[0-9]{1,}.*", getB().getName(),
      // CntPageType.T.NAME.getFld().getName(), "首字母不能为数字");
      //            Validate.hasa_Z_Length_Lt_8(LOG, getB().getLang(),
      // CntPageType.T.LANG.getFld().getName(), "应为小于八位的英文字母");
      //            for (CntPageTypeLine cntPageTypeLine : getLines()) {
      //                Validate.regex(LOG, "[0-9]{1,}.*", cntPageTypeLine.getName(),
      // CntPageTypeLine.T.NAME.getFld().getName(), "首位不能为数字");
      //            }
      //            super.valid();
    }
  }

  public static class Del extends IduDel<Del, CntArticleCategory> {
    @Override
    public void before() {
      super.before();
      //            delLineTid(getB(), CntPageTypeLine.class);
    }

    @Override
    public void valid() {
      super.valid();
    }
  }
}
