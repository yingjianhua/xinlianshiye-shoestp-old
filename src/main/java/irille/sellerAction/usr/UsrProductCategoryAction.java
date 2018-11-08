package irille.sellerAction.usr;

import irille.Service.Manage.Pdt.IPtdCatService;
import irille.pub.Exp;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduPage;
import irille.sellerAction.SellerAction;
import irille.sellerAction.usr.dto.UsrProductCategoryView;
import irille.sellerAction.usr.inf.IUsrProductCategoryAction;
import irille.shop.plt.PltConfigDAO;
import irille.shop.usr.UsrProductCategory;
import irille.shop.usr.UsrProductCategoryDAO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class UsrProductCategoryAction extends SellerAction<UsrProductCategory> implements IUsrProductCategoryAction {

    @Inject
    IPtdCatService usrSupplierCatService;


    /**
     * 添加供应商的产品分类
     *
     * @author yingjianhua
     */
    @Override
    public void ins() throws IOException {
        try {
            UsrProductCategoryDAO.add(getBean(), getSupplier().getPkey());
            write();
        } catch (Exp e) {
            writeErr(e.getLastMessage());
        }
    }

    @Override
    public void list() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        // 目前过滤器的搜索，是肯定会带初始条件的
        String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
        IduPage page = newPage();
        page.setStart(getStart());
        page.setLimit(getLimit());
        page.setWhere(where);
        page.commit();
        List<UsrProductCategory> list = BeanBase.list(UsrProductCategory.class, UsrProductCategory.T.SUPPLIER + " in(" + SellerAction.getSupplier().getPkey() + ")", false);//根据登陆id查询
        JSONObject lineJson = null;
        for (UsrProductCategory line : list) {
            line.setName(line.getName(PltConfigDAO.supplierLanguage(getSupplier())));
            //lineJson.put("categoryUp", line.gtCategoryUp().getName(PltConfigDAO.manageLanguage()));
        }
        ja = new JSONArray(list, false);
        json.put(STORE_ROOT, ja);
        json.put(STORE_TOTAL, page.getCount());
        writerOrExport(json);
    }

    /**
     * 获取所有产品分类
     *
     * @throws Exception
     * @author liyichao
     */
    public void getCategoryTree() throws JSONException, Exception {
        JSONObject json = new JSONObject();
        List<UsrProductCategory> catList = BeanBase.list(UsrProductCategory.class, UsrProductCategory.T.SUPPLIER.getFld().getCodeSqlField() + " = ? AND " + UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField() + " is null ", false, SellerAction.getSupplier().getPkey());
        List<UsrProductCategoryView> views = UsrProductCategoryView.build(catList);
        JSONArray carArray = UsrProductCategoryView.buildCat(views);
        json.put(STORE_ROOT, carArray);
        writerOrExport(json);
    }

    public void getCategory() throws Exception {
        JSONObject json = new JSONObject();

        JSONArray cat1Array = new JSONArray();
        JSONArray cat2Array = new JSONArray();
        JSONArray cat3Array = new JSONArray();

        List<UsrProductCategory> catList = BeanBase.list(UsrProductCategory.class, UsrProductCategory.T.SUPPLIER.getFld().getCodeSqlField() + " = ? AND " + UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField() + " is null ", false, SellerAction.getSupplier().getPkey());
        for (UsrProductCategory pdtProduct : catList) {
            JSONObject cat1 = new JSONObject();
            cat1.put("pkey", pdtProduct.getPkey());
            cat1.put("name", pdtProduct.getName(PltConfigDAO.supplierLanguage(getSupplier().getPkey())));
            cat1.put("categoryUp", pdtProduct.getCategoryUp());
            cat1.put("enabled", pdtProduct.getEnabled());
            cat1Array.put(cat1);
        }
        List<UsrProductCategory> cat2List = null;
        List<UsrProductCategory> cat3List = null;
        if (catList != null && catList.size() > 0) {
            cat2List = BeanBase.list(UsrProductCategory.class, UsrProductCategory.T.SUPPLIER.getFld().getCodeSqlField() + " = ? AND " + UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField() + " in(" + UsrProductCategoryDAO.getPkeys(catList) + ") ", false, SellerAction.getSupplier().getPkey());
            for (UsrProductCategory pdtProduct : cat2List) {
                JSONObject cat2 = new JSONObject();
                cat2.put("pkey", pdtProduct.getPkey());
                cat2.put("name", pdtProduct.getName(PltConfigDAO.supplierLanguage(getSupplier().getPkey())));
                cat2.put("categoryUp", pdtProduct.getCategoryUp());
                cat2.put("enabled", pdtProduct.getEnabled());
                cat2Array.put(cat2);
            }
        }
        if (cat2List != null && cat2List.size() > 0) {
            cat3List = BeanBase.list(UsrProductCategory.class, UsrProductCategory.T.SUPPLIER.getFld().getCodeSqlField() + " = ? AND " + UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField() + " in(" + UsrProductCategoryDAO.getPkeys(cat2List) + ") ", false, SellerAction.getSupplier().getPkey());
            for (UsrProductCategory pdtProduct : cat3List) {
                JSONObject cat3 = new JSONObject();
                cat3.put("pkey", pdtProduct.getPkey());
                cat3.put("name", pdtProduct.getName(PltConfigDAO.supplierLanguage(getSupplier().getPkey())));
                cat3.put("categoryUp", pdtProduct.getCategoryUp());
                cat3.put("enabled", pdtProduct.getEnabled());
                cat3Array.put(cat3);
            }
        }

        json.put("grandPa", cat1Array);
        json.put("parents", cat2Array);
        json.put("children", cat3Array);
        writerOrExport(json);

    }

    /**
     * 修改分类的上下架状态
     *
     * @throws Exception
     */
    public void upperAndLowerFrame() throws Exception {
        UsrProductCategoryDAO.UpperAndLowerFrame upperAndLower = new UsrProductCategoryDAO.UpperAndLowerFrame();
        UsrProductCategory cat = BeanBase.load(UsrProductCategory.class, getBean().getPkey());
        cat.setEnabled(getBean().getEnabled());
        upperAndLower.setB(cat);
        JSONObject json = new JSONObject();
        try {
            upperAndLower.commit();
            write();
        } catch (Exp e) {
            writeErr(e.getLastMessage());
        }
    }

    /**
     * TODO
     * 如果删除了分类,此分类下的产品如何处理???//将其分类设置未null未分类状态
     *
     * @throws Exception
     */

    public void update() throws Exception {
        JSONObject json = new JSONObject();
        UsrProductCategoryDAO.Update update = new UsrProductCategoryDAO.Update();
        update.setB(getBean());
        try {
            update.commit();
            write();
        } catch (Exp e) {
            writeErr(e.getLastMessage());
        }
    }
//分割线

    public void getProductCats() throws IOException {
        write(usrSupplierCatService.getProductCatList(getStart(), getLimit(), getSupplier().getPkey()));
    }


}
