package irille.platform.pm;

import java.io.IOException;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;
import com.xinlianshiye.shoestp.plat.view.pm.MessageView;

import irille.Entity.pm.PMMessage;
import irille.action.MgtAction;
import lombok.Data;

@Data
public class PMMessageAction extends MgtAction<PMMessage> {

  @Override
  public Class beanClazz() {
    return PMMessage.class;
  }

  @Inject private IPMMessageService messageService;

  private MessageView view;

  public void list() throws IOException {
    write(messageService.list(getView(), getStart(), getLimit()));
  }
}
