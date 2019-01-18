package irille.platform.pdt.View;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

//  产品颜色对象
@Data
public class PdtColorView implements BaseView {
    private Integer id;
//    @JsonSerialize(using = I18NFieldSerializer.class)
    private  String name; //名称
    private String createdby;//建档员
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date createdtime;//建档时间
}
