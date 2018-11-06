package irille.homeAction.pdt.dto;

import java.math.BigDecimal;

import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSize;

public class ProductAndSpceView {

	  private Integer pkey;	// 编号  INT
	  private Integer product;	// 产品 <表主键:PdtProduct>  INT
	  private Integer color;	// 产品顏色 <表主键:PdtColor>  INT
	  private Integer size;	// 产品尺寸 <表主键:PdtSize>  INT
	  private String keyName;	// 组合属性  STR(100)
	  private String sku;	// SKU（出厂编码）  STR(100)<null>
	  private BigDecimal price;	// 价格  DEC(16,2)
	  private BigDecimal markup;	// 加价  DEC(16,2)
	  private Integer storeCount;	// 库存数量  DEC(14,4)
	  private BigDecimal weight;	// 单价  DEC(14,4)
	  private String pics;	// 产品颜色关联图片  STR(1000)<null>	 
	  private Short rowVersion;	// 版本  SHORT
	  private PdtProduct pdtProduct;
	  private PdtColor pdtColor;
	  private PdtSize pdtSize;
	  public PdtColor getPdtColor() {
		return pdtColor;
	}
	public void setPdtColor(PdtColor pdtColor) {
		this.pdtColor = pdtColor;
	}
	public PdtSize getPdtSize() {
		return pdtSize;
	}
	public void setPdtSize(PdtSize pdtSize) {
		this.pdtSize = pdtSize;
	}
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public Integer getProduct() {
		return product;
	}
	public void setProduct(Integer product) {
		this.product = product;
	}
	public Integer getColor() {
		return color;
	}
	public void setColor(Integer color) {
		this.color = color;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getMarkup() {
		return markup;
	}
	public void setMarkup(BigDecimal markup) {
		this.markup = markup;
	}
	public Integer getStoreCount() {
		return storeCount;
	}
	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public Short getRowVersion() {
		return rowVersion;
	}
	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}
	public PdtProduct getPdtProduct() {
		return pdtProduct;
	}
	public void setPdtProduct(PdtProduct pdtProduct) {
		this.pdtProduct = pdtProduct;
	}
	  
	
}
