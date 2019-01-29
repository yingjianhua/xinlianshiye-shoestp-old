package irille.Entity.RFQ_Messages.JSON;

import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/29
 * Time: 9:57
 */
@Data
public class RFQ_QuotationJson implements BaseView {
    private int pdtId;   //导入商品的外键名称
    private int pdtName;   //商品名称
    private int pdtDescribe;   //商品详细描述
    private int pdtImages;  //商品图片 多图
    private int number;  //数量总和
    private String price;   //价格区间
    private String currency;   //币种
    private Date validity;  //有效值至
    private int payType;  //支付方式外键
    private int transitType;//运输方式 枚举
    private boolean sample; //是否提供样品
    private boolean companyDescribe; //公司简介
    private String throwaway; //宣传图片


}
