package irille.platform.rfq.view;

import lombok.Data;

@Data
public class SupplierView {

  private Integer pkey; // 编号 INT
  private Integer role; // 供应商角色 <表主键:UsrSupplierRole> INT
  private String roleName; // 供应商角色名字
  private String name; // 名称 STR(100)
}
