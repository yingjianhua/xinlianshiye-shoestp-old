package irille.homeAction.pdt.dto;

import java.util.List;

import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSize;
import irille.shop.pdt.PdtSpec;

public class CartProductAndSpceView {

  // 产品
  private PdtProduct pdtProduct;
  // 规格
  private List<PdtSpec> specList;
  // 颜色
  private List<PdtColor> colorList;
  // 尺寸
  private List<PdtSize> sizeList;

  public PdtProduct getPdtProduct() {
    return pdtProduct;
  }

  public void setPdtProduct(PdtProduct pdtProduct) {
    this.pdtProduct = pdtProduct;
  }

  public List<PdtSpec> getSpecList() {
    return specList;
  }

  public void setSpecList(List<PdtSpec> specList) {
    this.specList = specList;
  }

  public List<PdtColor> getColorList() {
    return colorList;
  }

  public void setColorList(List<PdtColor> colorList) {
    this.colorList = colorList;
  }

  public List<PdtSize> getSizeList() {
    return sizeList;
  }

  public void setSizeList(List<PdtSize> sizeList) {
    this.sizeList = sizeList;
  }
}
