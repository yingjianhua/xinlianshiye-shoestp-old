package irille.action.cnt;

import irille.action.MgtAction;
import irille.shop.cnt.CntSglPage;

public class CntSglPageAction extends MgtAction<CntSglPage> {
    public CntSglPage getBean() {
        return _bean;
    }

    public void setBean(CntSglPage bean) {
        this._bean = bean;
    }

    @Override
    public Class beanClazz() {
        return CntSglPage.class;
    }

    @Override
    public void insBefore() {
        System.out.println(getBean().getTitle());
        super.insBefore();
    }
}
