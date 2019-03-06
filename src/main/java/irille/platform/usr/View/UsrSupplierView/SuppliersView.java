package irille.platform.usr.View.UsrSupplierView;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

@Data
public class SuppliersView implements BaseView {
    private Integer id;//pkey

    private String name;//企业名称
    private String companyAddr;//所在地
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date applicationTime;//申请时间
    private Byte storeStatus;//店铺状态
    private Byte status;//审核状态

    private String contacts; //联系人

    private String category;//供应商分类
    private Byte auth;//供应商认证
    private String webSite;//网址
    private Integer sort;//排序
}
