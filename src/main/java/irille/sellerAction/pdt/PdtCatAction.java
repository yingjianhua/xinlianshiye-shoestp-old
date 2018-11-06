package irille.sellerAction.pdt;

import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.sellerAction.SellerAction;
import irille.sellerAction.pdt.inf.IPdtCatAction;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtCatDAO;
import irille.shop.plt.PltConfigDAO;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class PdtCatAction extends SellerAction<PdtCat> implements IPdtCatAction {

    public String orderBy() {
        return " ORDER BY PKEY ASC";
    }

    @Override
    public void getComboTrigger() throws Exception {
        String where = crtQueryAll();
        if (!Str.isEmpty(getSarg1()))
            where += " AND (" + getSarg1() + ")";
        where += orderBy();
        IduPage page = newPage();
        page.setStart(getStart());
        page.setLimit(0); // 取所有数据，下拉框不分页
        page.setWhere(where);
        page.commit();
        List<PdtCat> list = page.getList();

        Map<Integer, String> map1 = new TreeMap<Integer, String>();
        Map<Integer, String> map2 = new TreeMap<Integer, String>();
        Map<Integer, String> map3 = new TreeMap<Integer, String>();
        for (PdtCat line : list) {
            if (line.getCategoryUp() == null)
                map1.put(line.getPkey(), "├" + line.getExtName());
        }
        for (PdtCat line : list) {
            if (line.getCategoryUp() != null && map1.containsKey(line.getCategoryUp()))
                map2.put(line.getPkey(), "｜├" + line.getExtName());
        }
        for (PdtCat line : list) {
            if (line.getCategoryUp() != null && map2.containsKey(line.getCategoryUp()))
                map3.put(line.getPkey(), "｜｜├" + line.getExtName());
        }
        map1.putAll(map2);
        map1.putAll(map3);
        JSONArray ja = new JSONArray();
        JSONObject lineJson = null;
        for (Integer value : map1.keySet()) {
            lineJson = new JSONObject();
            // 注意不论主键是否为STRING,都转成字符串
            // 前EXT组件初始化时,数字也是以字符形式判断其值
            lineJson.put("value", value.toString());
            lineJson.put("text", map1.get(value));
            ja.put(lineJson);
        }
        JSONObject json = new JSONObject();
        json.put(STORE_ROOT, ja);
        ServletActionContext.getResponse().getWriter().print(json.toString());
    }

    public void getPdtCatList() {
        PdtCatDAO.Query query = new PdtCatDAO.Query();
        try {
            write(query.getCatChildNodesByCatId(0, PltConfigDAO.supplierLanguage(getSupplier())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}