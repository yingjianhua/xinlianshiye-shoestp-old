package irille.platform.usr.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class UsrSUPSelectView implements BaseView {
    private  Integer pkey;
    private  String name;
    private String  role;//供应商角色
    private  String loginname;
    private  String category;//供应商分类
    private  String  entity;//企业法人
    private  String companytype; // 企业类型
    private  String companyNature; // 企业性质
}
