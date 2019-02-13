package irille.view.Manage.RFQ;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 15:47
 */
@Data
public class RFQListBodyInfoView implements BaseView {
    private int id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date create_date;
    private int left_count;
    private boolean inquiry;
    private String image;
    private int countryId;
    private int quantity;
    private boolean favorite;


    private String content;
    private Byte unit;
}
