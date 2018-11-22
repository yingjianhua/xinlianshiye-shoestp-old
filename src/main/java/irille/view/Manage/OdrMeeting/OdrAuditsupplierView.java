package irille.view.Manage.OdrMeeting;

import lombok.Data;

/**
*@Description:   订购会合作商列表
*@date 2018/11/22 15:04
*@anthor wilson zhang
*/
@Data
public class OdrAuditsupplierView {
    private Integer id;//参加订购会pkey
    private String   companyname;//公司名称
    private String name ;//联系人
    private String email ;//邮箱
    private Integer isauth ;//联系人
    private String image ;//认证图片
    private Integer shopnum;//订购会商品数量
    private Integer status;//审核状态
    private String address;//样品地址
}
