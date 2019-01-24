package irille.platform.usr.View.UsrSupplierView;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SuppliersView implements BaseView {
    private Integer id;//pkey
    private String name;//企业名称
    private Byte status;//状态
    private String category;//供应商分类
    private Byte auth;//供应商认证
    private String webSite;//网址
    private Integer sort;//排序
}
