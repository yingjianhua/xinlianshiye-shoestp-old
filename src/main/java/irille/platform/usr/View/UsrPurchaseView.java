package irille.platform.usr.View;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

@Data
public class UsrPurchaseView implements BaseView {
    private Integer pkey;
    private String name; //名字
    private String surname; //姓氏
    private Byte sex; //性别
    private String icon; //头像
    private String email; //邮箱
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date regTime;//注册时间
    private String loginName; //登录账号
    private String password; //登录密码

    private String telphone; //手机号码
    private String company; //公司名称
    private String address; //地址
    private String country; //国家
    private String facebookID; //facebookid
    private String googleID; //googleid

    @Override
    public String toString() {
        return "UsrPurchaseView{" +
                "pkey=" + pkey +
                ", loginName='" + loginName + '\'' +
                ", facebookID='" + facebookID + '\'' +
                ", googleID='" + googleID + '\'' +
                '}';
    }
}
