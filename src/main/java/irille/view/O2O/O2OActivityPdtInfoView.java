package irille.view.O2O;

import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/28
 * Time: 11:34
 */
@Data
public class O2OActivityPdtInfoView implements BaseView {
    private int id;
    private String name;
    private String picture;
    private String code;
    private Byte status;
    private Byte appr;
    private int sku;
    private String rewrite;
    private BigDecimal price;
    private int min_oq;
    private String remark;
    private String supplier;
    private String message;
    private byte isO2o;
}
