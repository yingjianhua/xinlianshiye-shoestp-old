package irille.sellerAction.rfq.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.view.BaseView;
import lombok.Data;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/29
 * Time: 20:41
 */
@Data
public class RFQConsultQuoteInfoView implements BaseView {
    private int rfqId;  //询盘的ID
    private String title;   //商品名称
    private String descriotion;   //商品详细描述
    private List images;  //商品图片 多图
    private int quantity;  //数量
    private int min_price;   //价格区间
    private int max_price;   //价格区间
    private int currency;   //币种
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validity;  //有效值至
    private int payType;  //支付方式外键
    private int transitType;//运输方式 枚举
    private boolean sample; //是否提供样品
    private String companyDescribe; //公司简介
    private List throwaway; //宣传图片

    public static class Builder {

        public static RFQConsultQuoteInfoView toView(RFQConsultRelation bean) {
            RFQConsultQuoteInfoView view = new RFQConsultQuoteInfoView();
            view.setRfqId(bean.getConsult());
            view.setTitle(bean.getTitle());
            view.setDescriotion(bean.getDescription());
            try {
                String s = bean.getImage();
                ObjectMapper objectMapper = new ObjectMapper();
                view.setImages(objectMapper.readValue(s, List.class));
                s = bean.getThrowaway();
                view.setThrowaway(objectMapper.readValue(s, List.class));
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            view.setQuantity(bean.getQuantity());
            view.setMin_price(bean.getMinprice());
            view.setMax_price(bean.getMaxprice());
            view.setCurrency(bean.getCurrency());
            view.setValidity(bean.getValidDate());
            view.setPayType(bean.getPaytype());
            view.setTransitType(bean.getTransittype());
            view.setSample(bean.gtSample());
            view.setCompanyDescribe(bean.getCompanydescribe());
            return view;
        }
    }
}
