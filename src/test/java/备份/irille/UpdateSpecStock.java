package 备份.irille;

import irille.core.prv.PrvRoleAct;
import irille.pub.svr.DbPool;
import irille.pub.svr.StartInitServlet;
import irille.pub.tb.FldLanguage;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import org.json.JSONException;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/9/30
 * Time: 18:22
 */
public class UpdateSpecStock {
    public static void main(String[] args) throws JSONException, SQLException {
        StartInitServlet.initBeanLoad();
        PrvRoleAct.TB.getCode();
        for (PdtProduct pdtProduct : PdtProduct.list(PdtProduct.class, null, false)) {
            int stock = 0;
            for (PdtSpec pdtSpec : PdtProduct.list(PdtSpec.class, PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + pdtProduct.getPkey(), false)) {
                if (pdtSpec.getStoreCount() > 0)
                    stock += pdtSpec.getStoreCount();
            }

            if (stock != 0) {
                if (stock != pdtProduct.getStock()) {
                    System.out.printf("商品Id %d   商品名称:%s \r\n 数量: %d 变更后 %d\r\n", pdtProduct.getPkey(), pdtProduct.getName(FldLanguage.Language.en), pdtProduct.getStock(), stock);
                    pdtProduct.setStock(stock);
                    pdtProduct.upd();
                    DbPool.getInstance().getConn().commit();
                }
            }
        }

    }
}
