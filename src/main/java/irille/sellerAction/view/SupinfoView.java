package irille.sellerAction.view;

import java.util.Date;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SupinfoView implements BaseView {
  private String curlang; // 当前语言 /默认语言
  private Integer id;
  private String company; // 公司名称
  private String QQ;
  private String FAX; // 传真
  private String Telephone;
  private String Email;
  private String registered_Capital; // 注册资金
  private Date company_establish_time; // 成立时间
  private String operation_term; // 业务期限
  private String des; // 备注
  private String credit_code; // 信用代码
  private String entity; // 企业法人
  private String begintime; // 营业执照开始时间
  private String endtime; // 营业执照结束时间
  private Boolean issecular; // 长期
  private String taxpayer_Type; // 纳税人类型
  private String idcard; // 法人身份证号码
  private String idcardFrontPhoto; // 身份证正面
  private String idcardBackPhoto; // 身份证反面
  private String coutry; // 国家
  private String province; // 省份
  private String company_add; // 地址
  private String company_Type; // 企业类型
  private String company_nature; // 企业性质
  private String main_sale_area; // 主销售区
  private String prod_patiern; //
  private String main_prod; // 主要产品
  private Integer type; // 类型
}
