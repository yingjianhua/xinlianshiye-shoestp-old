package irille.view.O2O;

import java.math.BigDecimal;

import irille.view.BaseView;
import lombok.Data;

@Data
public class O2OProductView implements BaseView {

    private Integer pkey;
    private Byte status;
    private String actName;
    private String actAddress;
    private String productName;
    private String productCat;
    private BigDecimal price;
    private Integer minOq;
    private String supplierName;
    private String supplierLevel;
    private String contact;
    private String mobile;


}
