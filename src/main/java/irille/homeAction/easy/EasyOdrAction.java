package irille.homeAction.easy;

import java.util.List;

import com.google.inject.Inject;

import irille.Service.Eo.EasyOdrService;
import irille.homeAction.HomeAction;
import irille.shop.easy.EasyOdr;
import irille.view.Easy.EasyodrView;
import irille.view.Easy.EolineView;
import lombok.Getter;
import lombok.Setter;

public class EasyOdrAction extends HomeAction<EasyOdr> {
  @Inject private EasyOdrService easyOdrService;
  @Getter @Setter private Integer purchaseLine;
  @Getter @Setter private List<EasyodrView> list;
  @Getter @Setter private List<EolineView> list2;

  public void generateOrder() throws Exception {
    //        新的订单
    easyOdrService.generateOrder(purchaseLine, getPurchase().getPkey(), list, curLanguage());
    write();
  }
}
