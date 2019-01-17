package irille.platform.usr.View;

import irille.view.BaseView;
import lombok.Data;

import java.util.List;

@Data
public class SenderAndRecipientView implements BaseView {
    List<SenderView> senders;
    List<RecipientView> recipients;
}
