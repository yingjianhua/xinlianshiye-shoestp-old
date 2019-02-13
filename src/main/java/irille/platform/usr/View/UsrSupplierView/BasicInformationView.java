package irille.platform.usr.View.UsrSupplierView;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

@Data
public class BasicInformationView implements BaseView {
    private String name;//名称
    private Integer category;//供应商分类
    private Byte auth;//供应商认证
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", timezone="GMT+8")
    private Date authTime;//认证时间
    private String entity;//企业法人
    private String creditCode;//信用代码
    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss", timezone="GMT+8")
    private Date companyEstablishTime;//成立时间
    private String operationTerm;//业务期限
    private String des;//描述
    private String contacts;//联系人
    private String email;//Email
    private String phone;//手机
    private String telephone;//电话
    private String fax;//传真
    private String qq;//QQ
    private String seoTitle;//店铺关键字
    private String seoContent;//搜索引擎说明
    private String showName;//网站显示名称
    private String companyNature;//企业性质
    private String companyType;//企业类型
    private String mainSalesArea;//主销售区
    private String mainProd;//主要产品
    private String prodPattern;//生产模式
    private String companyAddr;//地址
    private String certPhoto;//资质证书
    private String idCardFrontPhoto;//身份证正面
    private String idCardBackPhoto;//身份证反面
    private String coopCertPhoto;//合作凭证
    private Integer sort;//排序号
}
