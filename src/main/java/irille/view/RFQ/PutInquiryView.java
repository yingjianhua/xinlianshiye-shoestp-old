package irille.view.RFQ;

import irille.view.BaseView;
import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 9:43 */
@Data
public class PutInquiryView implements BaseView {
  private String title;
  private int pdtId;
  private int quantity;
  private int unitType;
  private String descriotion;
  private String images;
}
