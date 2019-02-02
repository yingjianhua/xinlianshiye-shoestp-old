package irille.view.O2O;

import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class O2OProductView implements BaseView {

    private Integer pkey;
    private Byte status;//审核状态
    private Byte state;//上下架状态
    private String actName;
    private String actAddress;
    private String productName;
    private String productCat;
    private BigDecimal price;
    private BigDecimal mkt_price; //私有展会展品特有
    private Integer minOq;
    private String supplierName;
    private String supplierLevel;
    private String contact;
    private String mobile;
    private String rewriter;

    private String message;//申请下架理由

}
