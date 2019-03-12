package irille.platform.usr.View;

import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SenderAndRecipientView implements BaseView {
  List<SenderView> senders; // 收件人列表
  List<RecipientView> recipients; // 发件人列表
}
