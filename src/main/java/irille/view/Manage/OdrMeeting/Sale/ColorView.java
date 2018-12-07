package irille.view.Manage.OdrMeeting.Sale;

import irille.view.BaseView;
import lombok.Data;

@Data
public class ColorView implements BaseView {
    private String color;//颜色名称
    private String image;//颜色图片
    private SizeView size;//尺寸
}
