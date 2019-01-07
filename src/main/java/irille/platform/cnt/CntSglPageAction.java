package irille.platform.cnt;

import irille.action.ActionBase;
import irille.shop.cnt.CntSglPage;

public class CntSglPageAction extends ActionBase<CntSglPage> {
    @Override
    public Class beanClazz() {
        return CntSglPage.class;
    }
    public CntSglPage getBean() {
        return _bean;
    }

    public void setBean(CntSglPage bean) {
        this._bean = bean;
    }


}
