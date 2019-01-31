package irille.view.pdt;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class PdtSearchView  implements BaseView{

	//------------产品-----------
	private Integer pdtId;
	@JsonSerialize(using=I18NFieldSerializer.class)
	private String pdtName; //产品名称
	private BigDecimal price; //价格
	private Integer minOrder; //起订量
	private Integer gender; //鞋用户 1:男 2:女 3:通用 
	private String inner; //内部材料
	private String season; //适合的季节
	private String sole; // 鞋底材料
	private String closed; //闭合方式
	private String upper; //表面材料
	private String picture; //产品图片
	@JsonSerialize(using=I18NFieldSerializer.class)
	private String originCountry;  //原产:国家
	@JsonSerialize(using=I18NFieldSerializer.class)
	private String originProvince;  //原产:省份
	private boolean eshrine; //是否收藏
	private Integer pdtType; //0:普通商品，4:o2o商品
	//------------商家-----------
	private Integer supId; //商家id
	@JsonSerialize(using=I18NFieldSerializer.class)
	private String supName; //商家名称
	private boolean attestation; //是否认证----暂无功能
	private Integer SVS; //未知----暂无功能
	private double RD; //未知----暂无功能
	private double outPut; //产量？----暂无功能
	private double scale; //规模？----暂无功能
	private String country; //国家?----暂无功能
	private Integer enter; //入驻时间
}
