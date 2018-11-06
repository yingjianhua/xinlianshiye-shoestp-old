package irille.action.cnt;

import irille.action.MgtAction;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.cnt.CntArticleCategory;
import irille.shop.cnt.CntArticleCategoryDAO;

import org.json.JSONObject;

public class CntArticleCategoryAction extends MgtAction<CntArticleCategory> {

    public CntArticleCategory getBean() {
        return _bean;
    }

    public void setBean(CntArticleCategory bean) {
        this._bean = bean;
    }

    @Override
    public Class beanClazz() {
        return CntArticleCategory.class;
    }


    @Override
    public CntArticleCategory insRun() {
        insBefore();
        CntArticleCategoryDAO.Ins insDao = new CntArticleCategoryDAO.Ins();
        translateUtil.autoTranslate(getBean());
        insDao.setB(getBean());
        insDao.commit();
        insAfter();
        return insDao.getB();
    }

    @Override
    public CntArticleCategory updRun() {
        CntArticleCategoryDAO.Upd upd = new CntArticleCategoryDAO.Upd();
        String bean = _bean.getName();
        try {
            JSONObject json = new JSONObject(bean);
            String tw = json.getString("zh_TW");
            _bean.setName(tw);
            setBean(_bean);
            translateUtil.autoTranslate(getBean(), true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        upd.setB(getBean());
        upd.commit();
        return upd.getB();
    }
}
