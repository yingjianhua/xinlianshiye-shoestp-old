package 备份.irille;

import com.fasterxml.jackson.core.JsonProcessingException;
import irille.Dao.PdtProductCatDao;
import irille.core.prv.PrvRoleAct;
import irille.pub.svr.StartInitServlet;
import irille.view.usr.Manage.PdtCatsView;
import org.json.JSONException;
import org.junit.Test;
import org.start2do.SetBean.SetBeans;

import java.util.stream.Collectors;

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
        pdtProductCatDao.getPdtCatBySupplierId(262).stream().map(map -> {
            System.out.println(map);
            return SetBeans.set(map, PdtCatsView.class);
        }).collect(Collectors.toList()).forEach(pdtCatsView -> {
            System.out.println(pdtCatsView);
        });
    }
}
