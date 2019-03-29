package irille.shop.usr;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class MainCateInfoView implements BaseView {
	
	@JsonSerialize(using = I18NFieldSerializer.class)
	private  String  cateName;
	
}
