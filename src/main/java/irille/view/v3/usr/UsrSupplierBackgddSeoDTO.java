package irille.view.v3.usr;

import irille.view.BaseView;
import lombok.Data;

@Data
public class UsrSupplierBackgddSeoDTO implements BaseView {
    private Integer pkey;//供应商
    private String background;//供应商店招背景
    private String seoTitle;//SEO标题
    private String seoKeyWord;//SEO关键词
    private String seoContent;//SEO简述
}
