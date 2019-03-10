package irille.platform.usr.View;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

@Data
public class UsrMainView implements BaseView {
    private Integer pkey;
    private String email; //邮箱
    private String nickName; //昵称
    private String company; //公司名称
    private String country; //国家
    private String province; //省份
    private String city; //城市
    private String zone; //区域
    private String address; //详细地址
    private String contacts; //联系人
    private String telphone; //联系人手机号码
    private Byte identity; //买家还是卖家 0：买家 1：卖家
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date regTime;//注册时间
    private String facebookUserId; //facebookid
    private String googleUserId; //googleid
}
