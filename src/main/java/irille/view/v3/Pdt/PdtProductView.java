package irille.view.v3.Pdt;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;
/*
 *   供应商中心列表产品
 * @Author HuangHaoBin
 **/
@Data
public class PdtProductView implements BaseView {
    private Integer id;
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String name;
    private String picture;
}
