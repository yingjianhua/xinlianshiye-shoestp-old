package irille.view.pdt;

import irille.view.BaseView;
import lombok.Data;

import java.util.List;

/**
 *  后台页面|商品页
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/22
 * Time: 16:42
 */
@Data
public class PdtProductCatView implements BaseView {
    private int value;
    private String label;
    private List children;


}
