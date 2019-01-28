package irille.view.O2O;

import irille.view.BaseView;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    private String address;
    private Date startDate;
    private Date endDate;
    private String status;
    private int cat;
    private String rules;
    private String tel;
    private String name; //负责人
    private List<O2OActivityPdtInfoView> items;
}
