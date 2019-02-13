package irille.platform.pdt;

import irille.action.ActionBase;
import irille.shop.usr.UsrProductCategory;
import irille.shop.usr.UsrProductCategoryDAO;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class PdtUsrProductCategoryAction extends ActionBase<UsrProductCategory> {
    @Override
    public Class beanClazz() {
        return UsrProductCategory.class;
    }

    public UsrProductCategory getBean() {
        return _bean;
    }

    public void setBean(UsrProductCategory bean) {
        this._bean = bean;
    }

    @Getter
    @Setter
    private String name;//查询属性分类名称
    @Getter
    @Setter
    private Integer category;//查询所属分
    @Getter
    @Setter
    private Integer supplier;//查询供应商
    @Getter
    @Setter
    private Integer enabled;//查询是否启用
    @Getter
    @Setter
    private String seoKeyword;//查询关键词

    /**
     * 查询所有分类
     * *@anthor kouhanyang
     * @throws java.io.IOException
     */
    public void getUsrProductCategory() throws IOException {
        write(UsrProductCategoryDAO.getUsrProductCategory(getStart(), getLimit(), name, category, supplier, enabled, seoKeyword));
    }


}

