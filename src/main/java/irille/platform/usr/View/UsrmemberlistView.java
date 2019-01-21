package irille.platform.usr.View;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;
import sun.dc.pr.PRError;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UsrmemberlistView  implements BaseView {
    private  Integer    id;
    private String   name; // 名称
    private String icon;//图片
    private Boolean enabled;//启用
    private BigDecimal discount; //会员折扣
    private BigDecimal levelUp; // 升级条件
    private String  createdby;//创建人
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date createtime;//创建时间
    private String  updby;//修改人
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date updtime;//修改时间
}
