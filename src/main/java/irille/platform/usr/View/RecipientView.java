package irille.platform.usr.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RecipientView implements BaseView {
  private Integer recipientid; // 收件人id
  private String recipient; // 收件人
}
