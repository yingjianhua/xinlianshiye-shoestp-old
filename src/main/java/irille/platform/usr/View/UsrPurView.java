package irille.platform.usr.View;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

@Data
public class UsrPurView implements BaseView {
    private Integer id;
    private String email; // 邮箱
    private String name; // 昵称
    private String phone; // 手机
    private String country; // 国家
    @JsonFormat(pattern = "yyyy-MM-dd : hh:mm:ss", timezone = "GMT+8")
    private Date time; // 注册时间
}
