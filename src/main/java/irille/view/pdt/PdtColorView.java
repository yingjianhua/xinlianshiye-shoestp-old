package irille.view.pdt;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PdtColorView {

	/*{id:this.colorList[i].id,name:this.colorList[i].name,url:this.colorList[i].img,type:this.colorList[i].type};*/
	private Integer id;
	private String name;
	private String url;
	private Integer type;
	@Override
	public String toString() {
		return "PdtColorView {id=" + id + ", name=" + name + ", url=" + url + ", type=" + type + "}";
	}
	
	
}
