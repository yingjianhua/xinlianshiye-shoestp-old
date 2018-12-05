package irille.view.EO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class  easyodrView<Int> {
    private  Integer id;// 主键
    private Date time;//下单时间
    private  String name;//联系人
    private String phone;//联系 电话
    private String email;//邮箱
    private String address;//地址
    private  Integer count;//总数
    private List<eolineView> list;//规格集合

}
