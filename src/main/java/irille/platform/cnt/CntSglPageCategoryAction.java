package irille.platform.cnt;

import irille.action.ActionBase;
import irille.shop.cnt.CntSglPage;
import irille.shop.cnt.CntSglPageCategory;
import irille.shop.cnt.CntSglPageCategoryDAO;

import java.io.IOException;

public class CntSglPageCategoryAction extends ActionBase<CntSglPageCategory> {
    @Override
    public Class beanClazz() {
        return CntSglPageCategory.class;
    }
    public CntSglPageCategory getBean() {
        return _bean;
    }
    public void setBean(CntSglPageCategory bean) {
        this._bean = bean;
    }

    public void  listcategory() throws IOException {
        write(CntSglPageCategoryDAO.listcategory());
    }
}
