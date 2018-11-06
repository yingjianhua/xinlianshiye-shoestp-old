package irille.view.cnt;

import java.util.List;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdView implements BaseView {

	private List<AdLineView> items;
	//产品分类, 仅当广告类型为产品分类广告时有效
	private Integer category; 

}
