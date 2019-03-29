package irille.view.plt;

import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/10/30 Time: 10:00 */
@Data
public class PaySettingView implements BaseView {
  @SetBean(OriginalField = "pkey")
  private int id;

  private int mode;
  private String name;
  private boolean enabled;
  private double poundage;
  private double extra_costs;

  @SetBean(OriginalField = "paysetting")
  private String setting;

  public void setEnabled(Integer enabled) {
    this.enabled = enabled == 1;
  }
}
