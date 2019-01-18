package irille.platform.prm.View.GroupPurchaseView;

import irille.view.BaseView;
import lombok.Data;

import java.util.List;

@Data
public class StatusAndTypeView implements BaseView {
    List<StatusView> status;//活动状态列表
    List<TypeView> types;//提前预告列表
    List<SupplierView> supplier;//供应商列表
}
