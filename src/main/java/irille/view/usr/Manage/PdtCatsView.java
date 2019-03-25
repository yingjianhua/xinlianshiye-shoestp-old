package irille.view.usr.Manage;

import java.util.List;

import lombok.Data;
import org.start2do.SetBean.Annotations.SetBean;

/**
 * product/cat/list 列表使用的 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/8 Time:
 * 11:20
 */
@Data
public class PdtCatsView {
  @SetBean(OriginalField = "pkey")
  private int id;

  @SetBean(OriginalField = "name")
  private String title;

  private boolean enabled;
  private List<PdtCatsView> children;

  public int getId() {
    return id;
  }

  public void setId(String id) {
    this.id = Integer.parseInt(id);
  }

  public void setEnabled(Byte enabled) {
    this.enabled = enabled == 1;
  }
}
