package irille.homeAction.usr.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import irille.homeAction.HomeAction;
import irille.pub.bean.BeanBase;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSize;
import irille.shop.pdt.PdtSpec;
import irille.shop.usr.UsrCart;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SpecView {
  private Integer id;
  private Integer cartId;
  private String color;
  private String size;
  private String img;
  private BigDecimal price;
  private Integer qty;
  private Integer productId;
  private Integer colorId;
  private Integer sizeId;
  private Integer storeCount;
  private Integer sourceStock;
  private Integer maxoq;
  private Integer minoq;

  public SpecView(
      Integer id, String size, BigDecimal price, Integer qty, Integer productId, Integer colorId) {
    this.id = id;
    this.size = size;
    this.price = price;
    this.qty = qty;
    this.productId = productId;
    this.colorId = colorId;
  }

  public SpecView() {}

  public static SpecView buildBySpec(PdtSpec spec) {
    SpecView view = new SpecView();
    try {
      view.setId(spec.getPkey());
      view.setSize(spec.gtSize().getName(HomeAction.curLanguage()));
      view.setSizeId(spec.getSize());
      view.setPrice(spec.getPrice());
      /*view.setStoreCount(spec.getStoreCount());
      PdtProduct product = spec.gtProduct();
      if(Integer.valueOf(product.getProductType()).equals(Integer.valueOf(Pdt.OProductType.GROUP.getLine().getKey()))){
      	PdtSpec sourceSpec = PdtSpec.chkUniquePdt_color_size(false, product.getSourceProduct(), spec.getColor(), spec.getSize());
      	view.setSourceStock(sourceSpec.getStoreCount());
      }else{
      	view.setSourceStock(spec.getStoreCount());
      }*/
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return view;
  }

  public static List<SpecView> buildBySpec(List<PdtSpec> specList) {
    List<SpecView> views = new ArrayList<SpecView>();
    for (PdtSpec spec : specList) {
      views.add(buildBySpec(spec));
    }
    return views;
  }

  public static SpecView buildSize(String size) {
    SpecView view = new SpecView();
    view.setSize(BeanBase.load(PdtSize.class, size).getName());
    view.setSizeId(Integer.valueOf(size));
    return view;
  }

  public static List<SpecView> buildSize(String[] sizes) {
    List<SpecView> views = new ArrayList<SpecView>();
    for (String size : sizes) {
      views.add(buildSize(size));
    }
    return views;
  }

  public static SpecView buildSize(PdtSpec spec) {
    SpecView view = new SpecView();
    try {
      view.setSize(spec.gtSize().getName(HomeAction.curLanguage()));
      view.setSizeId(spec.getSize());
      view.setColorId(spec.getColor());
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return view;
  }

  public static JSONObject buildSize(List<PdtSpec> specList) throws JSONException {
    Map<Integer, List<SpecView>> mapViews = new HashMap<Integer, List<SpecView>>();
    for (PdtSpec spec : specList) {
      if (mapViews.get(spec.getColor()) == null) {
        List<SpecView> views = new ArrayList<SpecView>();
        views.add(buildSize(spec));
        mapViews.put(spec.getColor(), views);
      } else {
        List<SpecView> views = mapViews.get(spec.getColor());
        views.add(buildSize(spec));
      }
    }

    JSONObject color2Spec = new JSONObject();
    for (Integer key : mapViews.keySet()) {
      JSONArray json = new JSONArray(mapViews.get(key), false);
      color2Spec.put(String.valueOf(key), json);
    }
    return color2Spec;
  }

  public static SpecView build(UsrCart cart) {
    SpecView view = new SpecView();
    try {
      PdtSpec spec = cart.gtSpec();
      PdtProduct product = spec.gtProduct();
      view.setId(spec.getPkey());
      view.setCartId(cart.getPkey());
      view.setColor(spec.gtColor().getName(HomeAction.curLanguage()));
      view.setSize(spec.gtSize().getName(HomeAction.curLanguage()));
      if (spec.getPics() != null) {
        String[] pics = spec.getPics().split(",");
        view.setImg(pics.length > 0 ? pics[0] : "");
      }
      view.setPrice(spec.getPrice());
      view.setProductId(spec.getProduct());
      view.setQty(cart.getQty());
      view.setColorId(spec.getColor());
      view.setSizeId(spec.getSize());
      view.setStoreCount(product.getMaxOq());
      view.setMaxoq(product.getMaxOq().intValue());
      view.setMinoq(product.getMinOq().intValue());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return view;
  }

  public static List<SpecView> build(List<UsrCart> carts) {
    List<SpecView> views = new ArrayList<SpecView>();
    for (UsrCart cart : carts) {
      boolean flag = false;
      for (SpecView view : views) {
        if (view.getId().equals(cart.getSpec())) {
          flag = true;
          break;
        }
      }
      if (flag == false) {
        views.add(build(cart));
      }
    }
    return views;
  }

  public Integer getMaxoq() {
    return maxoq;
  }

  public void setMaxoq(Integer maxoq) {
    this.maxoq = maxoq;
  }

  public Integer getMinoq() {
    return minoq;
  }

  public void setMinoq(Integer minoq) {
    this.minoq = minoq;
  }

  public Integer getStoreCount() {
    return storeCount;
  }

  public void setStoreCount(Integer storeCount) {
    this.storeCount = storeCount;
  }

  public Integer getCartId() {
    return cartId;
  }

  public void setCartId(Integer cartId) {
    this.cartId = cartId;
  }

  public Integer getSourceStock() {
    return sourceStock;
  }

  public void setSourceStock(Integer sourceStock) {
    this.sourceStock = sourceStock;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getQty() {
    return qty;
  }

  public void setQty(Integer qty) {
    this.qty = qty;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Integer getColorId() {
    return colorId;
  }

  public void setColorId(Integer colorId) {
    this.colorId = colorId;
  }

  public Integer getSizeId() {
    return sizeId;
  }

  public void setSizeId(Integer sizeId) {
    this.sizeId = sizeId;
  }
}
