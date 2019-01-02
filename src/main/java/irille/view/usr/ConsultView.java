package irille.view.usr;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ConsultView implements BaseView {
	private Integer id;
	private String title;
	private String image;
	private Integer product;
	private String productNum;
	private String name;
	private String email;
	private Integer country;
	@JsonSerialize(using=I18NFieldSerializer.class)
	private String countryName;
	private String countryFlag;
	private String content;
	private Integer quantity; //鞋子的数量
	private Boolean haveNewMsg;//是否有新消息
	private Integer supplierCount;//接单的供应商数量
	private Integer supplierId;//若为专属询盘,则该字段表示该询盘属于哪个供应商
	private Integer count;//剩余抢单次数
	@JsonFormat(pattern="yyyy.MM.dd", timezone="GMT+8")
	private Date createTime;
	private List<ConsultRelationView> relations;//询盘关联供应商,包含留言记录
	private String purchaseimage;//采购商头像
	private String supplierimage;//供应商头像
	private String supplieremail;//供应商邮箱
	
	public String getSupplieremail() {
		return supplieremail;
	}
	public void setSupplieremail(String supplieremail) {
		this.supplieremail = supplieremail;
	}
	public String getPurchaseimage() {
		return purchaseimage;
	}
	public void setPurchaseimage(String purchaseimage) {
		this.purchaseimage = purchaseimage;
	}
	public String getSupplierimage() {
		return supplierimage;
	}
	public void setSupplierimage(String supplierimage) {
		this.supplierimage = supplierimage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getProduct() {
		return product;
	}
	public void setProduct(Integer product) {
		this.product = product;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryFlag() {
		return countryFlag;
	}
	public void setCountryFlag(String countryFlag) {
		this.countryFlag = countryFlag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Boolean isHaveNewMsg() {
		return haveNewMsg;
	}
	public void setHaveNewMsg(Boolean haveNewMsg) {
		this.haveNewMsg = haveNewMsg;
	}
	public Integer getSupplierCount() {
		return supplierCount;
	}
	public void setSupplierCount(Integer supplierCount) {
		this.supplierCount = supplierCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId==-1?null:supplierId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<ConsultRelationView> getRelations() {
		return relations;
	}
	public void setRelations(List<ConsultRelationView> relations) {
		this.relations = relations;
	}

}
