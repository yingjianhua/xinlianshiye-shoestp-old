package irille.view.v3.usr;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

import java.util.List;
/*
 *   供应商中心列表供应商详情
 * @Author HuangHaoBin
 **/
@Data
public class UsrSupplierView implements BaseView {

    public UsrSupplierView(){
        this.RD =3;
        this.output = 3;
        this.scale = 1;
        this.isFavorite = true;
    }

    private Integer id; //供应商id
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String logo;    //供应商logo
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String showName;    //供应商
    private byte isAuth; //是否已认证
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String mainSalesArea; //主销售区
    private Integer RD;     //暂时写死
    private Integer output; //暂时写死
    private Integer scale;  //暂时写死
    private Boolean isFavorite;//暂时写死
    private List products;  //产品列表
}
