package irille.view.Manage.RFQ;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 15:47
 */
@Data
public class RFQManageInfoView implements BaseView {
    private int id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date create_date;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date valid_date;
    private int left_count;
    private boolean inquiry;
    private String image;
    private int min_price;
    private int max_price;
    private int countryId;
    private int quantity;
    private String descriotion; //描述
    private String pay_type;
    private String shipping_type;
    private Integer currency;
    private String destination;//目的地
    private List quotation_record;
}