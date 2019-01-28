package irille.platform.pdt.view1;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

/**
 * 属性明细对象类
 *
 * @author lingjian
 * @date 2019/1/23 9:06
 */
@Data
public class PdtAttrLineView implements BaseView {
    private Integer id;
    //    @JsonSerialize(using = I18NFieldSerializer.class)
    private String name; //名称
    private Integer main; //产品类目
    private String createdBy;//建档员
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
    private Date createdTime;//建档时间
}
