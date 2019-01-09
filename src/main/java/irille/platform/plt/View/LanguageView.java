package irille.platform.plt.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class LanguageView implements BaseView {
    private String displayName;
    private String shortName;
}
