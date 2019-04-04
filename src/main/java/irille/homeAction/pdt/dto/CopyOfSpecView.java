package irille.homeAction.pdt.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import irille.shop.pdt.PdtSpec;

public class CopyOfSpecView {
  private Integer id;
  private String img;
  private String color;
  private String size;
  private BigDecimal price;

  private CopyOfSpecView() {}

  public static CopyOfSpecView build(PdtSpec spec, String defaultImg) {
    CopyOfSpecView view = new CopyOfSpecView();
    view.setId(spec.getPkey());
    view.setColor(spec.gtColor().getName());
    view.setSize(spec.gtSize().getName());
    view.setPrice(spec.getPrice());
    String[] pics = spec.getPics().split(",");
    view.setImg(pics.length > 0 ? pics[0] : defaultImg);
    return view;
  }

  public static List<CopyOfSpecView> build(List<PdtSpec> specs, String defaultImg) {
    List<CopyOfSpecView> views = new ArrayList<CopyOfSpecView>();
    for (PdtSpec spec : specs) {
      views.add(build(spec, defaultImg));
    }
    return views;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
}
