package irille.platform.usr.View;

import irille.view.BaseView;
import lombok.Data;

import java.util.List;

@Data
public class SenderAndRecipientView implements BaseView {
    List<SenderView> senders;//收件人列表
    List<RecipientView> recipients;//发件人列表
}
