package irille.homeAction.pdt.dto;

import irille.homeAction.HomeAction;
import irille.pub.util.DataFilters;
import irille.shop.pdt.PdtSpec;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;
import org.json.JSONException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class SpecView {
    private Integer id;
    private Integer productId;
    private String img;
    private String color;
    private String size;
    private BigDecimal price;
    //数量
    private Integer qty;
    //重量
    private BigDecimal weight;
    //sku
    private String sku;
    //颜色Id
    private int colorId;
    private int sizeId;
    /**
     * @Description: 规格多图
     * @author lijie@shoestp.cn
     * @date 2018/8/22 11:07
     */
    private List<String> imgList;

    private SpecView() {
    }

    public static SpecView build(PdtSpec spec) throws JSONException {
        SpecView view = new SpecView();
        view.setId(spec.getPkey());
        view.setProductId(spec.getProduct());
        view.setColorId(spec.getColor());
        view.setColor(spec.gtColor().getName(HomeAction.curLanguage()));
        view.setSizeId(spec.getSize());
        view.setSize(spec.gtSize().getName());
        view.setPrice(spec.getPrice());
        String[] pics = spec.getPics().split(",");
        view.setImg(pics.length > 0 ? pics[0] : "");
        return view;
    }

    public static SpecView build_GoodsInfoView(PdtSpec spec, PltErate bigDecimal) throws JSONException {
        SpecView view = new SpecView();
        view.setSku(spec.getSku());
        view.setWeight(spec.getWeight());
        view.setQty(spec.getStoreCount());
        view.setSku(spec.getSku());
        view.setId(spec.getPkey());
        view.setProductId(spec.getProduct());
        view.setColor(spec.gtColor().getName(HomeAction.curLanguage()));
        view.setSize(spec.gtSize().getName(HomeAction.curLanguage()));
        view.setPrice(PltErateDAO.Query.getTargetPrice(spec.getPrice(), bigDecimal.getRate()).setScale(2, RoundingMode.HALF_UP));

        view.setColorId(spec.gtColor().getPkey());
        if (spec.getPics() != null) {
            if (spec.getPics().length() > 0) {
                String[] pics = spec.getPics().split(",");
                if (pics.length > 0) {
                    for (String pic : pics) {
                        if (pic.length() > 0) {
                            view.setImg(DataFilters.ImageUrl_NoHost_String(pic));
                            break;
                        }
                    }
                    view.setImgList(DataFilters.ImageUrl_NoHost_List(pics));
                }
            }
        }
        return view;
    }

    public static List<SpecView> build(List<PdtSpec> specs) throws JSONException {
        List<SpecView> views = new ArrayList<SpecView>();
        for (PdtSpec spec : specs) {
            views.add(build(spec));
        }
        return views;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    @Override
    public String toString() {
        return "SpecView{" +
                "id=" + id +
                ", productId=" + productId +
                ", img='" + img + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", weight=" + weight +
                ", sku='" + sku + '\'' +
                ", colorId=" + colorId +
                ", sizeId=" + sizeId +
                ", imgList=" + imgList +
                '}';
    }
}
