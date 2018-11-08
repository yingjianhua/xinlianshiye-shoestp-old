package irille.homeAction.cnt;

import irille.homeAction.HomeAction;
import irille.pub.bean.BeanBase;
import irille.pub.util.CacheUtils;
import irille.shop.cnt.CntSglPage;
import irille.shop.cnt.CntSglPageCategory;
import irille.view.cnt.CntSglPageCategoryView;
import irille.view.cnt.CntSglPageView;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CntSglPageCategoryAction extends HomeAction<CntSglPageCategory> {

    /**
     * 查询所有单页
     *
     * @throws Exception
     * @author zw
     */
    public void listAll() throws Exception {
        List<CntSglPageCategoryView> pcvs = (List<CntSglPageCategoryView>) CacheUtils.cache.get("CntSglPageCategoryAction_listAll", o -> {
            List<CntSglPageCategory> cspc = BeanBase.list(CntSglPageCategory.class, null, false);
            List<CntSglPage> csp = BeanBase.list(CntSglPage.class, false, null, 0, 8);
            List<CntSglPageCategoryView> pcvss = new ArrayList<>();
            for (CntSglPageCategory cspcLine : cspc) {
                List<CntSglPageView> pvs = new ArrayList<>();
                for (CntSglPage p : csp) {
                    if (cspcLine.getPkey() == p.getPageType()) {
                        CntSglPageView pv = new CntSglPageView();
                        pv.setId(p.getPkey());
                        try {
                            pv.setTitle(p.getTitle(HomeAction.curLanguage()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pvs.add(pv);
                    }
                }
                CntSglPageCategoryView pcv = null;
                try {
                    pcv = CntSglPageCategoryView.build(cspcLine, pvs, HomeAction.curLanguage());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pcvss.add(pcv);
            }
            return pcvss;
        });
        write(pcvs);
    }


    /**
     * 转发页面
     */
    public String gosglpage() throws Exception {
        setResult("/home/sglPage.jsp");
        return HomeAction.TRENDS;
    }

    /**
     * 转发页面
     */
    public String gosglpageshoestp() throws Exception {
        setResult("/home/sglPageshoestp.jsp");
        return HomeAction.TRENDS;
    }

}
