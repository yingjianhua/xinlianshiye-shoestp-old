package irille.platform.rfq.view;

import java.util.Date;

import lombok.Data;

@Data
public class PurchaseView {

	private Integer pkey; // 编号 INT
	private String name; // 名字 STR(100)<null>
	private String surname; // 姓氏 STR(100)<null>
	private Byte sex; // 性别 <OSex> BYTE
	// UNKNOW:0,未知
	// MALE:1,男
	// FEMALE:2,女
	private String icon; // 头像 STR(200)<null>
	private String email; // 邮箱 STR(100)<null>
	private Date regTime; // 注册时间 TIME<null>
	private String loginName; // 登录账号 STR(40)
	private String password; // 密码 STR(40)<null>
	private Integer currency; // 默认货币 <表主键:PltErate> INT
	private String regIp; // 注册IP STR(30)<null>
	private String telphone; // 手机号码 STR(200)<null>
	private String company; // 公司名称 STR(100)<null>
	private String address; // 地址 STR(200)<null>
	private Date lastLoginTime; // 最后登录时间 TIME<null>
	private Integer country; // 国家 <表主键:PltCountry> INT<null>
	private String facebookUserId; // FACEBOOK用户ID STR(100)<null>
	private String googleUserId; // 谷歌用户ID STR(100)<null>
	private Integer memberLevel; // 会员等级 <表主键:UsrMemberLevel> INT<null>
	private Short rowVersion; // 版本 SHORT
}
