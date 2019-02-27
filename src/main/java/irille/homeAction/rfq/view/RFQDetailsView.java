package irille.homeAction.rfq.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

@Data
public class RFQDetailsView implements BaseView {
    private Integer pkey;//RFQpkey
    private String title;//RFQ标题
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date time;//RFQ发布时间
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String rfqCountry;//RFQ国家
    private String pdtImages;//RFQ产品图片
    private String pdtDetails;//RFQ产品详情
    private Integer quantitys;//RFQ产品数量
    private String userName;//采购商名称
    @JsonSerialize(using = I18NFieldSerializer.class)
    private String userCountry;//采购商国家
    private String userCountryImage;//采购商国家国旗
    private String payMethod;//首选支付方式

}
