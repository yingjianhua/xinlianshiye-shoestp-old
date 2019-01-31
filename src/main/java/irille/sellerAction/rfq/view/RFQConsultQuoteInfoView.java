package irille.sellerAction.rfq.view;

import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/29
 * Time: 20:41
 */
@Data
public class RFQConsultQuoteInfoView {
    private int rfqId;  //询盘的ID
    private String  title;   //商品名称
    private String descriotion;   //商品详细描述
    private String images;  //商品图片 多图
    private int quantity;  //数量
    private int min_price;   //价格区间
    private int max_price;   //价格区间
    private int currency;   //币种
    private Date validity;  //有效值至
    private int payType;  //支付方式外键
    private int transitType;//运输方式 枚举
    private boolean sample; //是否提供样品
    private String companyDescribe; //公司简介
    private String throwaway; //宣传图片
}
