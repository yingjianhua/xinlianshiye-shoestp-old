package irille.homeAction.pm;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;
import com.xinlianshiye.shoestp.plat.service.pm.IPMReadService;

import irille.Entity.pm.PM.ORCVRType;
import irille.Entity.pm.PMMessage;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.homeAction.HomeAction;
import irille.homeAction.pm.inf.IPMMessageAction;
import lombok.Getter;
import lombok.Setter;

public class PMMessageAction extends HomeAction<PMMessage> implements IPMMessageAction {
  @Inject private IPMMessageService messageService;

  @Inject private IPMReadService readService;

  @Getter @Setter private Integer message;

  @Override
  @NeedLogin
  public void read() throws Exception {
    readService.read(getPurchase().getPkey(), getMessage());
    writeSuccess();
  }

  @Override
  @NeedLogin
  public void list() throws Exception {
    write(messageService.list(null, getPurchase(), ORCVRType.PURCHASE, getStart(), getLimit()));
  }
}
