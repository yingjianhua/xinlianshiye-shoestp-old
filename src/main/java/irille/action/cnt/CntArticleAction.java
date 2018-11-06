package irille.action.cnt;

import irille.action.MgtAction;
import irille.shop.cnt.CntArticle;

public class CntArticleAction extends MgtAction<CntArticle> {
    public CntArticle getBean() {
        return _bean;
    }

    public void setBean(CntArticle bean) {
        this._bean = bean;
    }

    @Override
    public Class beanClazz() {
        return CntArticle.class;
    }


}
