package irille.Dao.RFQ.view;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SellerIndexConsultView implements BaseView{
  private  Integer pruductpkey; // 商品pkey
  private String name; // 商品名称
  private Long pdtcount;// 商品询盘总数
}
