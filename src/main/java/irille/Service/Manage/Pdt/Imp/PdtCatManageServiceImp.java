package irille.Service.Manage.Pdt.Imp;

import com.google.api.client.json.Json;
import irille.Dao.PdtProductCatDao;
import irille.Service.Manage.Pdt.IPdtCatManageService;
import irille.pub.tb.FldLanguage;
import irille.sellerAction.view.ProductSEOsView;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.view.Page;
import irille.view.usr.Manage.PdtCatsView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.start2do.SetBean.SetBeans;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/8
 * Time: 11:32
 */
public class PdtCatManageServiceImp implements IPdtCatManageService {
    @Inject
    PdtProductCatDao pdtProductCatDao;

    @Override
    public Page getProductCatList(int start, int limit, int supplierId) {
        List<PdtCatsView> list = pdtProductCatDao.getPdtCatBySupplierId(supplierId).stream().map(map -> {
            PdtCatsView pdtCatsView = new PdtCatsView();
            return SetBeans.set(map, PdtCatsView.class);
        }).collect(Collectors.toList());
        return null;
    }

    @Override
    public Page getProductSEOs(Integer statr, Integer limit, Integer supplier, FldLanguage.Language language) {
        List<ProductSEOsView> list = pdtProductCatDao.getProductSEOs(supplier).stream().map(o -> {
            JSONObject json;
            ProductSEOsView view = new ProductSEOsView();
            view.setProductId(Integer.valueOf(String.valueOf(o.get(PdtProduct.T.PKEY.getFld().getCodeSqlField()))));
            view.setProductName(String.valueOf(o.get(PdtProduct.T.NAME.getFld().getCodeSqlField())));
            try {
                if(String.valueOf(o.get(PdtProduct.T.SEO_TITLE.getFld().getCodeSqlField()))!=null){
                    json = new JSONObject(String.valueOf(o.get(PdtProduct.T.SEO_TITLE.getFld().getCodeSqlField())));
                    if(json.get(language.toString())!=null){
                        view.setSeo_title(json.get(language.toString()).toString());
                    }else{
                        view.setSeo_title(json.get(FldLanguage.Language.en.toString()).toString());
                    }
                }
                if(String.valueOf(o.get(PdtProduct.T.SEO_KEYWORD.getFld().getCodeSqlField()))!=null){
                    json = new JSONObject(String.valueOf(o.get(PdtProduct.T.SEO_KEYWORD.getFld().getCodeSqlField())));
                    if(json.get(language.toString())!=null){
                        view.setSeo_keyword(json.get(language.toString()).toString());
                    }else{
                        view.setSeo_keyword(json.get(FldLanguage.Language.en.toString()).toString());
                    }
                }
                if(String.valueOf(o.get(PdtProduct.T.SEO_DESCRIPTION.getFld().getCodeSqlField()))!=null){
                    json = new JSONObject(String.valueOf(o.get(PdtProduct.T.SEO_DESCRIPTION.getFld().getCodeSqlField())));
                    if(json.get(language.toString())!=null){
                        view.setSeo_description(json.get(language.toString()).toString());
                    }else{
                        view.setSeo_description(json.get(FldLanguage.Language.en.toString()).toString());
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return view;
        }).collect(Collectors.toList());
        Integer count = pdtProductCatDao.getProductSEOs(supplier).size();
        return new Page(list, statr, limit, count);
    }
}
