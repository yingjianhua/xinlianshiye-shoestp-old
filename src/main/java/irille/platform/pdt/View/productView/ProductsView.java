package irille.platform.pdt.View.productView;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductsView implements BaseView {
    private Integer pkey;//产品ID
    private String pdtImage;//产品图片
    private String pdtName;//产品名称
    private String pdtCode;//产品编号
    private String pdtCategory;//产品分类
    private BigDecimal mktPrice;//市场价
    private BigDecimal curPrice;//商城价
    private Integer stock;//库存
    private Integer minOq;//最小起订量
    private String supplierName;//店铺名称
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", timezone="GMT+8")
    private Date soldTimeB;//上架时间
    private Byte isVerify;//审核状态
    private Byte state;//商品状态
    private String isO2O;//是否是O2O商品
}
