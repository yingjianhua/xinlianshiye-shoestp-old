package irille.platform.usr.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SenderView implements BaseView {
  private Integer senderid; // 发件人id
  private String sender; // 发件人
}
