import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import irille.core.prv.PrvRoleAct;
import irille.pub.svr.DbPool;
import irille.pub.svr.StartInitServlet;
import irille.shop.pdt.PdtProduct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/9/30
 * Time: 9:43
 */
public class Imgage_UrlReplayTest {

    public static void main(String[] args) throws SQLException {
        StartInitServlet.initBeanLoad();
        PrvRoleAct.TB.getCode();
        for (PdtProduct pdtProduct : PdtProduct.list(PdtProduct.class, null, false)) {
            JsonObject jsonObject = new JsonParser().parse(pdtProduct.getDescription()).getAsJsonObject();
            JsonObject jsonObject1 = new JsonObject();
            jsonObject.entrySet().forEach(stringJsonElementEntry -> {
                String html = stringJsonElementEntry.getValue().getAsString();
                Document document = Jsoup.parse(html);
                Elements elements = document.body().select("area");
                if (elements.size() > 0) {
                    System.out.printf("商品Id:%d 标签area 进行替换\r\n", pdtProduct.getPkey());
                    for (Element area : elements) {
                        try {
                        	String href = area.attr("href");
                        	if(href.equals("")||href.equals("#")||href.equals("javascript:void(0);"))
                        		continue;
                            URL url = new URL(href);
                            if(!url.getHost().equalsIgnoreCase("image.shoestp.com")) {
                            	continue;
                            }
                            area.attr("href", url.getPath());
                        } catch (MalformedURLException e) {
                    		System.out.println("\""+area+"\"");
                    		e.printStackTrace();
                        }
                    }
                }
                elements = document.body().select("a");
                if (elements.size() > 0) {
                    System.out.printf("商品Id:%d 标签A 进行替换\r\n", pdtProduct.getPkey());
                    for (Element area : elements) {
                        try {
                        	String href = area.attr("href");
                        	if(href.equals("")||href.equals("#")||href.equals("javascript:void(0);"))
                        		continue;
                            URL url = new URL(href);
                            if(!url.getHost().equalsIgnoreCase("image.shoestp.com")) {
                            	continue;
                            }
                            area.attr("href", url.getPath());
                        } catch (MalformedURLException e) {
                    		System.out.println("\""+area+"\"");
                    		e.printStackTrace();
                        }
                    }
                }
                jsonObject1.addProperty(stringJsonElementEntry.getKey(), document.body().html());
            });
            pdtProduct.setDescription(jsonObject1.toString());
            pdtProduct.upd();
            DbPool.getInstance().getConn().commit();
        }
        System.out.println("完成");
        System.exit(0);
    }
}
