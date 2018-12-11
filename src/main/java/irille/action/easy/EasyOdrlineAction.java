package irille.action.easy;

import irille.action.MgtAction;
import irille.shop.easy.EasyOdrline;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/12/6
 * Time: 20:04
 */
public class EasyOdrlineAction extends MgtAction<EasyOdrline> {
    @Override
    public Class beanClazz() {
        return EasyOdrline.class;
    }

//    @Override
//    public void list() throws Exception {
//         JSONObject json = new JSONObject();
//        JSONArray ja = new JSONArray();
//        String where = Str.isEmpty(this.getQuery()) ? this.crtFilter() : this.crtQuery();
//        IduPage page = this.newPage();
//        page.setStart(this.getStart());
//        page.setLimit(this.getLimit());
//        page.setWhere(where);
//        page.commit();
//        List<EasyOdrline> list = page.getList();
//        JSONObject lineJson = null;
//        Iterator var7 = list.iterator();
//
//        while(var7.hasNext()) {
//            EasyOdrline line = (EasyOdrline)var7.next();
//            line.setProductname(line.getProductname(PltConfigDAO.manageLanguage()));
//            lineJson = this.crtJsonByBean(line);
//            ja.put(lineJson);
//        }
//
//        json.put("items", ja);
//        json.put("total", page.getCount());
//        this.writerOrExport(json);
//    }
}
