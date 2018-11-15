package irille.view.usr;


import irille.view.BaseView;
import lombok.Data;

@Data
public class UsrshopSettingView  implements BaseView{
	private String logo;//logo图片
	private String signBackGD;//店招背景
	private String adPhoto;//广告图/首页大海报
	private String adPhotoLink;//广告链接
	private String companyPhoto;//企业图片/首页企业大海报
	private String companyPhotoLink;//企业图片链接
	private String homePageDIY;//首页个性装修
	private boolean homePageOn;//首页个性装修开关
	private String companyIntroductionPageCustomDecoration;//公司介绍页自定义装修
	private boolean bottomproductdisplay;//首页底部产品展示
	private boolean homebigposter;//首页大海报开关
	private boolean homebusinessbigposter;//首页企业大海报开关
	private boolean aboutpagecustomdecoration;//公司介绍页自定义装修开关
}
