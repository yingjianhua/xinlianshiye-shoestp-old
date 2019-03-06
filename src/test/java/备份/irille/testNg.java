package 备份.irille;

import com.fasterxml.jackson.core.JsonProcessingException;
import irille.Dao.PdtProductCatDao;
import irille.core.prv.PrvRoleAct;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.svr.StartInitServlet;
import irille.shop.usr.UsrSupplier;
import org.json.JSONException;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/7
 * Time: 10:16
 */
public class testNg {

    PdtProductCatDao pdtProductCatDao = new PdtProductCatDao();

    @Test
    public void test1() throws JSONException, JsonProcessingException {
        StartInitServlet.initBeanLoad();
        PrvRoleAct.TB.getCode();
        System.out.println(UsrSupplier.get(UsrSupplier.class, 262).getTop3Markets());
        SQL sql = new SQL();
        sql.SELECT(UsrSupplier.T.TOP_3_MARKETS.getFld().getCodeSqlField())
                .FROM(UsrSupplier.class).WHERE(UsrSupplier.T.PKEY, "=?", 23);

        System.out.println(Query.sql(sql).queryMaps());

//        pdtProductCatDao.getPdtCatBySupplierId(262).stream().map(map -> {
//            System.out.println(map);
//            return SetBeans.set(map, PdtCatsView.class);
//        }).collect(Collectors.toList()).forEach(pdtCatsView -> {
//            System.out.println(pdtCatsView);
//        });
//        String url = "/activity/html/romania/fengsheng/index.html";
//        Pattern pattern=Pattern.compile("/activity/html/romania/(\\w+)[^/]?");
//        if (pattern.matcher(url).find()){
//            System.out.println(pattern.matcher(url).matches());
//        }
    }
}
