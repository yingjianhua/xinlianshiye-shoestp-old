package irille.view.pdt;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import irille.view.BaseView;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/24
 * Time: 11:25
 */
public class PdtProductSaveView implements BaseView {

    private int id;
    private Map pdtName;
    private int productCat;
    private Integer supplierCat;
    private String number_left;
    private String number_right;
    private String sku;
    private List<Integer> attr;
    private Map<String, String> pdtPics;
    private Set<Integer> specSize;
    private Set<Integer> specColor;
    private List<PdtProductSpecSaveView> spec;
    private double price;
    private double mktPrice;
    private double purPrice;
    private int min_oq;
    private int max_oq;
//    private int stock;
    private int warnStock;
    //true 上架
    private boolean state;
    //定时上架  true
    private boolean soldInStatus;
    private List<Date> soldInTime;
    private int weight;
    private int height;
    private int width;
    private int length;
    private String briefDescription;
    //true 免邮
    private boolean freeShipping;
    private Map description;
    private int from = -1;
    private Integer radio;//私人展厅商品/普通商品      0：普通商品 1：私人展厅商品
    private Integer warehouse;//保存到仓库和发布的区分 0：保存到仓库 1：发布
    private List<NewSpceView> newSpec;
    private List<PdtTieredPricingView> tieredPricing;
    private List<PdtColorView> color;
    private Date putawayDate;
    
    public Date getPutawayDate() {
		return putawayDate;
	}

	public void setPutawayDate(Date putawayDate) {
		this.putawayDate = putawayDate;
	}

    public List<NewSpceView> getNewSpec() {
		return newSpec;
	}

	public List<PdtColorView> getColor() {
		return color;
	}

	public void setColor(List<PdtColorView> color) {
		this.color = color;
	}

	public void setNewSpec(List<NewSpceView> newSpec) {
		this.newSpec = newSpec;
	}

	public List<PdtTieredPricingView> getTieredPricing() {
		return tieredPricing;
	}

	public void setTieredPricing(List<PdtTieredPricingView> tieredPricing) {
		this.tieredPricing = tieredPricing;
	}

    public Integer getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Integer warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getRadio() {
        return radio;
    }

    public void setRadio(Integer radio) {
        this.radio = radio;
    }

    public Map getPdtName() {
        return pdtName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPdtName(Map pdtName) {
        this.pdtName = pdtName;
    }

    public int getProductCat() {
        return productCat;
    }

    public void setProductCat(int productCat) {
        this.productCat = productCat;
    }

    public Integer getSupplierCat() {
        return supplierCat;
    }

    public void setSupplierCat(Integer supplierCat) {
        this.supplierCat = supplierCat;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getNumber_left() {
        return number_left;
    }

    public void setNumber_left(String number_left) {
        this.number_left = number_left;
    }

    public String getNumber_right() {
        return number_right;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setNumber_right(String number_right) {
        this.number_right = number_right;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<Integer> getAttr() {
        return attr;
    }

    public void setAttr(List<Integer> attr) {
        this.attr = attr;
    }

    public Map<String, String> getPdtPics() {
        return pdtPics;
    }

    public void setPdtPics(Map<String, String> pdtPics) {
        this.pdtPics = pdtPics;
    }

    public Set<Integer> getSpecSize() {
        return specSize;
    }

    public void setSpecSize(Set<Integer> specSize) {
        this.specSize = specSize;
    }

    public Set<Integer> getSpecColor() {
        return specColor;
    }

    public void setSpecColor(Set<Integer> specColor) {
        this.specColor = specColor;
    }

    public List<PdtProductSpecSaveView> getSpec() {
        return spec;
    }

    public void setSpec(List<PdtProductSpecSaveView> spec) {
        this.spec = spec;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMktPrice() {
        return mktPrice;
    }

    public void setMktPrice(double mktPrice) {
        this.mktPrice = mktPrice;
    }

    public double getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(double purPrice) {
        this.purPrice = purPrice;
    }

    public int getMin_oq() {
        return min_oq;
    }

    public void setMin_oq(int min_oq) {
        this.min_oq = min_oq;
    }

    public int getMax_oq() {
        return max_oq;
    }

    public void setMax_oq(int max_oq) {
        this.max_oq = max_oq;
    }

//    public int getStock() {
//        return stock;
//    }
//
//    public void setStock(int stock) {
//        this.stock = stock;
//    }

    public int getWarnStock() {
        return warnStock;
    }

    public void setWarnStock(int warnStock) {
        this.warnStock = warnStock;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isSoldInStatus() {
        return soldInStatus;
    }

    public void setSoldInStatus(boolean soldInStatus) {
        this.soldInStatus = soldInStatus;
    }

    public List<Date> getSoldInTime() {
        return soldInTime;
    }

    public void setSoldInTime(List<Date> soldInTime) {
        this.soldInTime = soldInTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public Map getDescription() {
        return description;
    }

    public void setDescription(Map description) {
        this.description = description;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "PdtProductSaveView{" +
                "id=" + id +
                ", pdtName=" + pdtName +
                ", productCat=" + productCat +
                ", supplierCat=" + supplierCat +
                ", number_left='" + number_left + '\'' +
                ", number_right='" + number_right + '\'' +
                ", sku='" + sku + '\'' +
                ", attr=" + attr +
                ", pdtPics=" + pdtPics +
                ", specSize=" + specSize +
                ", specColor=" + specColor +
                ", spec=" + spec +
                ", price=" + price +
                ", min_oq=" + min_oq +
                ", max_oq=" + max_oq +
                ", warnStock=" + warnStock +
                ", state=" + state +
                ", soldInStatus=" + soldInStatus +
                ", soldInTime=" + soldInTime +
                ", weight=" + weight +
                ", height=" + height +
                ", width=" + width +
                ", length=" + length +
                ", briefDescription='" + briefDescription + '\'' +
                ", freeShipping=" + freeShipping +
                ", description='" + description + '\'' +
                '}';
    }
}
