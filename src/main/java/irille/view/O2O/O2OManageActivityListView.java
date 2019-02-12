package irille.view.O2O;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Lijie<HelloBox@outlook.com>
 * Date: 2019/1/26
 * Time: 16:08
 */
@Data
public class O2OManageActivityListView implements BaseView {
    private Integer id;
    private String title;
    private int catId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date start_date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date end_date;
    private String address;
    private Integer countryId;
    private String status;

}
