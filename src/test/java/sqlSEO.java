import irille.pub.bean.Query;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.svr.DbPool;
import irille.pub.tb.FldLanguage;
import irille.shop.pdt.PdtProduct;
import org.json.JSONException;
import java.sql.SQLException;
import java.util.List;

public class sqlSEO {

  public static void main(String[] args) throws JSONException, SQLException {
    PdtProduct p = new PdtProduct();
    SQL sql = new SQL() {
      {
        SELECT(PdtProduct.T.NAME, PdtProduct.T.PKEY)
            .FROM(PdtProduct.class);
      }
    };
    StringBuffer sb = new StringBuffer();
    StringBuffer allSql = new StringBuffer();
    List<PdtProduct> prods = Query.sql(sql).queryList(PdtProduct.class);
    System.out.println("===START===");
    for (int i = 0; i < prods.size(); i++) {
      PdtProduct o = prods.get(i);

      for (FldLanguage.Language value : FldLanguage.Language.values()) {
        if (o.getName(value).indexOf("'") != -1) {
          String name = o.getName(value).replaceAll("'", "\\\\\\\'");
          sb.append("\"" + value + "\":\"" + name + " - shoestp.com\",");//
        } else {
          sb.append("\"" + value + "\":\"" + o.getName(value) + " - shoestp.com\",");
        }
      }
      String str = "{" + sb.substring(0, sb.length() - 1) + "}";
      str = str.replace("\t", " ");
      sb = new StringBuffer();
      String sql2 = "UPDATE pdt_product product " +
          "SET product.seo_title = '" + str + "'," +
          "product.seo_keyword = '" + str + "'," +
          "product.seo_description = '" + str + "'" +
          " WHERE product.pkey = " + o.getPkey() +
          " AND (product.seo_title = 1" +
          " OR product.seo_keyword = 1" +
          " OR product.seo_description = 1" +
          " OR product.seo_title is NULL" +
          " OR product.seo_keyword is NULL" +
          " OR product.seo_description is NULL);";
      System.out.println(new SqlQuery(sql2).executeUpdate());
    }
    System.out.println("====END====");
    DbPool.getInstance().getConn().commit();
  }
}
