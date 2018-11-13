package irille.Service.Pdt.Imp;

import irille.Aops.Caches;
import irille.Dao.PdtProductDao;
import irille.Service.Pdt.IPdtProductService;
import irille.homeAction.HomeAction;
import irille.homeAction.pdt.dto.PdtProductView;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage;
import irille.pub.util.SEOUtils;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.view.pdt.PdtProductBaseInfoView;
import irille.view.pdt.PdtProductCatView;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/7
 * Time: 14:54
 */
public class PdtProductServiceImp implements IPdtProductService {

    @Inject
    PdtProductDao pdtProductDao;

    @Override
    public List<PdtProductBaseInfoView> getNewProductsListByIndex(IduPage iduPage) {
        List<Map> result = pdtProductDao.getNewProductsList(iduPage.getStart(), iduPage.getLimit(), iduPage.getWhere());
        return SetBeans.setList(fromPdtProductBaseInfoView(result), PdtProductBaseInfoView.class);
    }

    @Override
    public List<PdtProductBaseInfoView> getProductsIndexHot(IduPage iduPage) {
        List<Map> result = pdtProductDao.getProductsIndexHot(iduPage.getStart(), iduPage.getLimit());
        return SetBeans.setList(fromPdtProductBaseInfoView(result), PdtProductBaseInfoView.class);
    }

    @Override
    public Map getProductListByCategory(IduPage page, String[] flds, boolean order, int category, String spec, String onlyFld, String keyword, Integer type) {
        PdtProductView pdtProductView = new PdtProductView();
        pdtProductView
                .setPage(page)
                .setFlds(flds)
                .setOrder(order)
                .setCategory(category)
                .setSpec(spec)
                .setKeyword(keyword)
        ;
        if (type == null) type = 0;
        switch (type) {
            case 0:
                pdtProductView.setProductType(Pdt.OProductType.GENERAL);
                break;
            case 1:
                pdtProductView.setProductType(Pdt.OProductType.GROUP);
                break;
        }
        if (onlyFld != null) {
            try {
                pdtProductView.setOnlyFld(PdtProductView.onlyFld.valueOf(onlyFld));
            } catch (Exception e) {

            }
        }
        /**
         * @Description: 现在转变分页逻辑 不再是 页面数
         * @date 2018/11/8 17:51
         * @author lijie@shoestp.cn
         */
        if (page.getStart() > 0) {
            page.setStart(page.getStart() - 1);
        }
        page.setStart(page.getStart() * page.getLimit());
        return pdtProductDao.getProductList(pdtProductView);
    }

    @Override
    public List<PdtProductBaseInfoView> getYouMayLike(IduPage iduPage, int cat) {
        List<PdtProduct> result = pdtProductDao.getYouMayLike(iduPage, cat);
        return result.stream().map(bean -> new PdtProductBaseInfoView() {{
            setRewrite(SEOUtils.getPdtProductTitle(bean.getPkey(), bean.getName()));
            translateUtil.getAutoTranslate(bean, HomeAction.curLanguage());
            setId(bean.getPkey());
            setPrice(bean.getCurPrice());
            setImage(bean.getPicture());
            setName(bean.getName());
        }}).collect(Collectors.toList());
    }

    @Caches
    @Override
    public List<PdtProductCatView> getProductsIndexCategories(int start, int limit, FldLanguage.Language language) {
        return pdtProductDao.getCatChildNodesByCatId(0, language);
    }


    private List fromPdtProductBaseInfoView(List<Map> result) {
        return result.stream().map(o -> {
            String name = String.valueOf(o.get("name"));
            if (name != null && name.length() > 0) {
                try {
                    o.put("rewrite", SEOUtils.getPdtProductTitle(Integer.parseInt(String.valueOf(o.get("pkey"))), name));
                    o.put("name", translateUtil.getLanguage(name, HomeAction.curLanguage()));
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            return o;
        }).collect(Collectors.toList());
    }

}
