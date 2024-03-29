package irille.platform.usr.View.UsrSupplierView;

import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class ListView implements BaseView {
  private List<StatusView> status; // 供应商审核状态
  private List<CategorysView> categorys; // 供应商分类
  private List<AuthView> auths; // 供应商是否已认证
  private List<IsProsView> isPros; // 供应商首页产品展示
  private List<CountryView> countrys; // 国家列表
  private List<StoreStatusView> storeStatus; // 店铺列表
  private List<SVSStatusView> svsStatus; // svs认证状态
  private List<SVSGradeView> svsGrade; // svs认证状态
}
