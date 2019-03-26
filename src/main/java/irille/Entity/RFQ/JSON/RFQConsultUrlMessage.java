package irille.Entity.RFQ.JSON;

import irille.Entity.RFQ.Enums.RFQConsultMessageType;
import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/29 Time: 20:41 */
@Data
public class RFQConsultUrlMessage implements BaseView, ConsultMessage {

  private String showMsg; // 显示内容
  private String url; // 链接地址

  @Override
  public Byte type() {
    return RFQConsultMessageType.URL.getLine().getKey();
  }
}
