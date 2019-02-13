package irille.platform.usr.View.UsrSupplierView;

import irille.view.BaseView;
import lombok.Data;

import java.util.List;

@Data
public class ListView implements BaseView {
    private List<StatusView> status;//供应商审核状态
    private List<CategorysView> categorys;//供应商分类
    private List<AuthView> auths;//供应商是否已认证
    private List<IsProsView> isPros;//供应商首页产品展示
    private List<CountryView> countrys;//国家列表
}
