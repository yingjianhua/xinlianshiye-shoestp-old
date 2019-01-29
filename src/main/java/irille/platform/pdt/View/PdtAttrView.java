package irille.platform.pdt.View;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

/**
 * 产品属性对象类
 *
 * @author lingjian
 * @date 2019/1/22 15:59
 */
@Data
public class PdtAttrView implements BaseView {
    private Integer id;
    //    @JsonSerialize(using = I18NFieldSerializer.class)
    private String name; //名称
    //    @JsonSerialize(using = I18NFieldSerializer.class)
    private String CATEGORY; //产品类目
    private String createdBy;//建档员
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
    private Date createdTime;//建档时间
}
