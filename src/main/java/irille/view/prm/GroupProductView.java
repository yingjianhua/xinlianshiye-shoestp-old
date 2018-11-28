package irille.view.prm;

import java.math.BigDecimal;

import irille.pub.util.SEOUtils;
import irille.view.BaseView;

public class GroupProductView implements BaseView{
	private Integer id;
	private String image;
	private String name;
	private long count;
	private BigDecimal sourcePrice;
	private BigDecimal curPrice;
	private Integer reviewRating;
	private Integer reviewCount;
	private String rewrite;
	private Integer productId;
	private Boolean ismyfavorite;

	public Boolean getIsmyfavorite() {
		return ismyfavorite;
	}
	public void setIsmyfavorite(Boolean ismyfavorite) {
		this.ismyfavorite = ismyfavorite;
	}
	public Integer getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public void setRewrite(int id ,String name) {
		this.rewrite = SEOUtils.getPdtProductTitle(id, name);;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public BigDecimal getSourcePrice() {
		return sourcePrice;
	}
	public void setSourcePrice(BigDecimal sourcePrice) {
		this.sourcePrice = sourcePrice;
	}
	public BigDecimal getCurPrice() {
		return curPrice;
	}
	public void setCurPrice(BigDecimal curPrice) {
		this.curPrice = curPrice;
	}
	public Integer getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(Integer reviewRating) {
		this.reviewRating = reviewRating;
	}
	
	
}
