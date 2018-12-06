package irille.view.EO;

import irille.pub.bean.BeanBase;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class  easyodrView  implements BaseView {
    private  Integer id;// 主键
    private Date time;//下单时间
    private Integer supplierid;//供应商ID
    private  String remarks;//备注
    private  String name;//联系人
    private String phone;//联系 电话
    private String email;//邮箱
    private String address;//地址
    private  Integer count;//总数
    private List<eolineView> list;//规格集合

}
