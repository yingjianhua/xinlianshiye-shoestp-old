package irille.view.usr;

import java.util.Map;

import org.json.JSONObject;

import com.google.gson.JsonObject;

import irille.view.BaseView;
import lombok.Data;

@Data
public class UsrshopSettingView  implements BaseView{
	private String logo;//logo图片
	private String signBackGD;//店招背景
	private Map<String,String[]> adPhoto;//广告图/首页大海报
	private Map<String,String[]> adPhotoLink;//广告链接
	private Map<String,String[]> companyPhoto;//企业图片/首页企业大海报
	private Map<String,String[]> companyPhotoLink;//企业图片链接
	private String homePageDIY;//首页个性装修
	private Byte homePageOn;//首页个性装修开关
}
