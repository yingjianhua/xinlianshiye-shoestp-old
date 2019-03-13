package irille.view.Easy;

import java.util.Date;
import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class EasyodrView implements BaseView {
  private Integer id; // 主键
  private String odrnum; // 订单号
  private String company; // 公司名称
  private Date time; // 下单时间
  private Integer supplierid; // 供应商ID
  private String remarks; // 备注
  private String name; // 联系人
  private String phone; // 联系 电话
  private String email; // 邮箱
  private String address; // 地址
  private Integer count; // 总数
  private List<EolineView> list; // 规格集合
}
