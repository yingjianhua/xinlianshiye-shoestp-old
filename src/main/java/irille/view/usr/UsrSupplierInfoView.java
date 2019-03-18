package irille.view.usr;

import java.util.List;

import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/5 Time: 15:53 */
@Data
public class UsrSupplierInfoView implements BaseView {
  @SetBean(OriginalField = "pkey")
  private int id;

  private String name;
  private String logo;

  @SetBean(OriginalField = "role_name")
  private List<String> roles;
}
