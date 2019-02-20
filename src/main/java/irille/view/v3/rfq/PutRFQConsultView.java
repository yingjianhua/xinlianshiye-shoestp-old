package irille.view.v3.rfq;

import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 9:43
 */
@Data
public class PutRFQConsultView implements BaseView {
    private String title;
    private String descriotion;
    private String images;
    private int quantity;
    private int unit = 1;
    private int min_price;
    private int max_price;
    private int currency;
    private Date valid_date;
    private int pay_type;
    private int shipping_type;
    private String destination;
}
