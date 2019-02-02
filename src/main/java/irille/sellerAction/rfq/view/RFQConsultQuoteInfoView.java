package irille.sellerAction.rfq.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.view.BaseView;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/29
 * Time: 20:41
 */
@Data
public class RFQConsultQuoteInfoView implements BaseView {
    private int rfqId;  //询盘的ID
    private String  title;   //商品名称
    private String descriotion;   //商品详细描述
    private String images;  //商品图片 多图
    private int quantity;  //数量
    private int min_price;   //价格区间
    private int max_price;   //价格区间
    private int currency;   //币种
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date validity;  //有效值至
    private int payType;  //支付方式外键
    private int transitType;//运输方式 枚举
    private boolean sample; //是否提供样品
    private String companyDescribe; //公司简介
    private String throwaway; //宣传图片
    
    public static class Builder {
    	
    	public static RFQConsultQuoteInfoView toView(RFQConsultRelation bean) {
    		RFQConsultQuoteInfoView view = new RFQConsultQuoteInfoView();
    		view.setRfqId(bean.getConsult());
    		view.setTitle(bean.getTitle());
    		view.setDescriotion(bean.getDescription());
    		view.setImages(bean.getImage());
    		view.setQuantity(bean.getQuantity());
    		view.setMin_price(bean.getMinprice());
    		view.setMax_price(bean.getMaxprice());
    		view.setCurrency(bean.getCurrency());
    		view.setValidity(bean.getValidDate());
    		view.setPayType(bean.getPaytype());
    		view.setTransitType(bean.getTransittype());
    		view.setSample(bean.gtSample());
    		view.setCompanyDescribe(bean.getCompanydescribe());
    		view.setThrowaway(bean.getThrowaway());
    		return view;
    	}
    }
}
