package irille.view.cnt;

import irille.view.BaseView;

public class CntSglPageView implements BaseView {
	
	private Integer id;
	private String title;	// 标题  JSONOBJECT
	private String descrip;	// 详细描述  JSONOBJECT
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	  
}
