package irille.action.easy;

import irille.action.MgtAction;
import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.shop.easy.EasyOdr;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltCountryDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/6
 * Time: 20:04
 */
public class EasyOdrAction extends MgtAction<EasyOdr> {

    @Inject
    private PltCountryDAO pltCountryDAO;

    @Override
    public Class beanClazz() {
        return EasyOdr.class;
    }

    @Override
    public void list() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        String where = Str.isEmpty(this.getQuery()) ? this.crtFilter() : this.crtQuery();
        IduPage page = this.newPage();
        page.setStart(this.getStart());
        page.setLimit(this.getLimit());
        page.setWhere(where);
        page.commit();
        List<EasyOdr> list = page.getList();
        JSONObject lineJson = null;
        Iterator var7 = list.iterator();

        while (var7.hasNext()) {
            EasyOdr line = (EasyOdr) var7.next();
            JSONObject object = line.gtAddress();
            String country = pltCountryDAO.loadById(object.getInt("countryid")).getName(PltConfigDAO.manageLanguage());
            String regionid = pltCountryDAO.loadProvinceById(object.getInt("regionid")).getName(PltConfigDAO.manageLanguage());
            line.setAddress(country + regionid + object.getString("city") + object.getString("address"));
            lineJson = this.crtJsonByBean(line);

            ja.put(lineJson);

        }

        json.put("items", ja);
        json.put("total", page.getCount());
        this.writerOrExport(json);
    }
}
