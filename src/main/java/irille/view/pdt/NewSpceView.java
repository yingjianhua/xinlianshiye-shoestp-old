package irille.view.pdt;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewSpceView {

	
	/*this.newSpec.push({id:this.globalId,size:this.sizeList[s1].pkey,sizeName:this.sizeList[s1].name,sizeType:this.sizeList[s1].type,
		color:this.colorFileList[j].id,colorName:this.colorFileList[j].name,colorImg:this.colorFileList[j].img,sku:this.colorFileList[j].name+'-'+this.sizeList[s1].name,count:null});*/
	private Integer id;
	private Integer size;
	private String sizeName;
	private Integer sizeType;
	private Integer color;
	private String colorName;
	private String colorImg;
	private Integer colorType;
	private String sku;
	private Integer count;
	@Override
	public String toString() {
		return "NewSpceView {id=" + id + ", size=" + size + ", sizeName=" + sizeName + ", sizeType=" + sizeType
				+ ", color=" + color + ", colorName=" + colorName + ", colorImg=" + colorImg + ", colorType="
				+ colorType + ", sku=" + sku + ", count=" + count + "}";
	}
	
}
