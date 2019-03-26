package irille.platform.prm.View.GroupPurchaseView;

import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class StatusAndTypeView implements BaseView {
  List<StatusView> status; // 活动状态列表
  List<TypeView> types; // 提前预告列表
  List<SupplierView> supplier; // 供应商列表
}
