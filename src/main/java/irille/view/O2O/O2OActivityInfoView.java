package irille.view.O2O;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/28
 * Time: 11:34
 */
@Data
public class O2OActivityInfoView implements BaseView {
    private int id;
    private String title;
    private int countryId;
    private String country;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date endDate;
    private String status;
    private String category;
    private String rules;
    private String tel;
    private String name; //负责人
    private List<Map<String,Object>> categories;
    private List<O2OActivityPdtInfoView> items;
}
