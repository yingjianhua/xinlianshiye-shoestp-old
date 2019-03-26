package irille.Dao;

import java.util.List;

import irille.Aops.Caches;
import irille.pub.bean.Query;
import irille.pub.bean.sql.I18NSQL;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduOther;
import irille.pub.tb.FldLanguage;
import irille.shop.pdt.PdtCat;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/13 Time: 10:06 */
public class PdtCatDao extends IduOther<PdtCatDao, PdtCat> {
  private static String pkeys = "";

  /** 通过id查询所有子分类id */
  @Caches
  public String getAllChild(FldLanguage.Language lang, Integer id) {
    List<PdtCat> allCatBySup = getAllCat(lang);
    pkeys = String.valueOf(id);
    getChildPkeys(allCatBySup, id);
    return pkeys;
  }

  /** 递归查询子分类id */
  public void getChildPkeys(List<PdtCat> categories, Integer id) {
    for (PdtCat cat : categories) {
      if (cat.getCategoryUp() == null) {
        continue;
      }
      if (cat.getCategoryUp().equals(id)) {
        pkeys += "," + String.valueOf(cat.getPkey());
        getChildPkeys(categories, cat.getPkey());
      } else {
        continue;
      }
    }
  }

  /** 查询所有分类 */
  @Caches
  public List<PdtCat> getAllCat(FldLanguage.Language lang) {
    SQL sql =
        new I18NSQL(lang) {
          {
            SELECT(PdtCat.class).FROM(PdtCat.class);
          }
        };
    return irille.pub.bean.Query.sql(sql).queryList(PdtCat.class);
  }

  public PdtCat findById(Integer id) {
    return Query.SELECT(PdtCat.class).WHERE(PdtCat.T.PKEY, "=?", id).query();
  }
}
