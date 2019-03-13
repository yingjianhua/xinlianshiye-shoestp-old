package irille.platform.rfq.view;

import java.util.Date;

import lombok.Data;

@Data
public class CountryView {

  private Integer pkey; // 编号 INT
  private String name; // 国家 JSONOBJECT
  private String shortName; // 国家简称 STR(20)
  private String zone; // 区号 STR(10)
  private Integer currency; // 货币 <表主键:PltErate> INT<null>
  private String nationalFlag; // 国旗 STR(400)<null>
  private Byte enabled; // 启用标志 <OEnabled> BYTE
  // TRUE:1,启用
  // FALSE:0,停用
  private Byte hot; // 热门国家 <OYn> BYTE
  // YES:1,是
  // NO:0,否
  private Byte isdefault; // 默认国家 <OYn> BYTE
  // YES:1,是
  // NO:0,否
  private Integer createdBy; // 建档员 <表主键:SysUser> INT
  private Date createdTime; // 建档时间 TIME
  private Short rowVersion; // 版本 SHORT
}
