package irille.view.RFQ;

import irille.view.BaseView;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 11:39
 */
@Data
public class RFQPdtInfo implements BaseView {
    private int id;//产品的Id
    private String supName;
    private String title;
    private String image;
    private String suplevel;
}
