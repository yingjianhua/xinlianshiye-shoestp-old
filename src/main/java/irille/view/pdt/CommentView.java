package irille.view.pdt;

import java.util.Date;

import irille.view.BaseView;

public class CommentView implements BaseView{

	private Integer id;//id
	private String image;//产品图片
	private Byte star;//评分星级，1,2,3,4,5
	private Date time;//评论时间
	private String content;//评论内容
	private String reply;//商家回复
	private String name;//产品名称
	private String spec;//产品规格
	
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
	public Byte getStar() {
		return star;
	}
	public void setStar(Byte star) {
		this.star = star;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	
}
