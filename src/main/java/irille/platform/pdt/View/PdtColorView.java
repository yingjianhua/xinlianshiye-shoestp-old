package irille.platform.pdt.View;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Data;

//  产品颜色对象
@Data
public class PdtColorView implements BaseView {
    private Integer id;
    //    @JsonSerialize(using = I18NFieldSerializer.class)
    private String name; //名称
    private String createdby;//建档员
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
    private Date createdtime;//建档时间
    private String picture; //图片地址
    private Integer supplier;
    private Integer supName;
    private byte type;
}
