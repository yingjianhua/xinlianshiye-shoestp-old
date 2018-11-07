package irille.view.pdt;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.homeAction.HomeAction;
import irille.pub.i18n.I18NFieldSerializer;
import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import irille.shop.plt.PltErateDAO;
import irille.view.BaseView;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 商品基础Dto  字段  id 名称 图片 价格 是否最爱
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/7
 * Time: 14:52
 */
@Data
public class PdtProductBaseInfoView implements BaseView {
    @SetBean(OriginalField = "pkey")
    private int id;
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String name;

    @SetBean(OriginalField = "cur_price")
    private BigDecimal price;
    @SetBean(OriginalField = "picture")
    private String image;

    private Integer favorite_count;

    private boolean isFav;

    private String rewrite;


    public void setImage(String image) {
        if (image != null) {
            String[] strings = image.split(",");
            this.image = strings.length > 0 ? strings[0] : image;
        }
    }


    public void setRewrite(String rewrite) {
        this.rewrite = rewrite;
    }

    public void setPrice(BigDecimal price) {
        this.price = PltErateDAO.Query.getTargetPrice(price, HomeAction.curCurrency().getNowrate()).setScale(2, RoundingMode.HALF_UP);
        ;
    }

}
