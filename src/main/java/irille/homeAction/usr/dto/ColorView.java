package irille.homeAction.usr.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import irille.homeAction.HomeAction;
import irille.pub.bean.BeanBase;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderLine;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtSpec;
import irille.shop.usr.UsrCart;
import org.json.JSONException;

public class ColorView {
  private Integer id;
  private String color;
  private String img;
  private String name;
  private Integer proId;
  private long qty;
  private BigDecimal totalPrice;

  public ColorView(
      String name,
      Integer id,
      String color,
      String img,
      Integer proId,
      long qty,
      BigDecimal totalPrice) {
    this.name = name;
    this.id = id;
    this.color = color;
    this.img = img;
    this.proId = proId;
    this.qty = qty;
    this.totalPrice = totalPrice;
  }

  public ColorView() {}

  public static ColorView buildColor2(OdrOrderLine orderLine) {
    ColorView view = new ColorView();
    try {
      String[] pics = orderLine.gtSpec().getPics().split(",");
      view.setImg(pics.length > 0 ? pics[0] : "");
      view.setColor(orderLine.gtSpec().gtColor().getName(HomeAction.curLanguage()));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return view;
  }

  public static List<ColorView> buildColor2(List<OdrOrder> orderList) {
    List<ColorView> views = new ArrayList<ColorView>();
    for (OdrOrder order : orderList) {
      List<OdrOrderLine> orderLineList =
          BeanBase.list(
              OdrOrderLine.class,
              OdrOrderLine.T.MAIN.getFld().getCodeSqlField() + " = " + order.getPkey(),
              false);
      for (OdrOrderLine orderLine : orderLineList) {
        if (views != null && views.size() > 0) {
          boolean flag = false;
          for (ColorView view : views) {
            String colorName = null;
            try {
              colorName = orderLine.gtSpec().gtColor().getName(HomeAction.curLanguage());
            } catch (JSONException e) {
              e.printStackTrace();
            }
            if (view.getColor().equals(colorName)) {
              flag = true;
              break;
            }
          }
          if (flag == false) {
            views.add(buildColor2(orderLine));
          }

        } else {
          views.add(buildColor2(orderLine));
        }
      }
    }
    return views;
  }

  public static ColorView buildColor(Integer pdtPkey, String color) {
    ColorView view = new ColorView();
    try {
      view.setColor(BeanBase.load(PdtColor.class, color).getName(HomeAction.curLanguage()));
      view.setId(Integer.valueOf(color));
      PdtSpec spec =
          BeanBase.list(
                  PdtSpec.class,
                  PdtSpec.T.PRODUCT.getFld().getCodeSqlField()
                      + " = "
                      + pdtPkey
                      + " AND "
                      + PdtSpec.T.COLOR.getFld().getCodeSqlField()
                      + " = "
                      + color,
                  false)
              .get(0);
      if (spec.getPics() != null) {
        String[] pics = spec.getPics().split(",");
        view.setImg(pics.length > 0 ? pics[0] : "");
      }
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return view;
  }

  public static List<ColorView> buildColor(Integer pdtPkey, String[] colorArr) {
    List<ColorView> views = new ArrayList<ColorView>();
    for (String color : colorArr) {
      views.add(buildColor(pdtPkey, color));
    }
    return views;
  }

  public static Map<Integer, List<ColorView>> buildByCart(List<UsrCart> carts) {
    Map<Integer, List<ColorView>> views = new HashMap<Integer, List<ColorView>>();
    for (UsrCart cart : carts) {
      if (views.get(cart.gtSpec().getProduct()) == null) {
        List<ColorView> view = new ArrayList<ColorView>();
        view.add(build(cart));
        views.put(cart.gtSpec().getProduct(), view);
      } else {
        List<ColorView> view = views.get(cart.gtSpec().getProduct());
        if (view == null || view.size() <= 0) {
          view.add(build(cart));
        } else {
          boolean flag = false;
          for (ColorView color : view) {
            try {
              String name = cart.gtSpec().gtColor().getName(HomeAction.curLanguage());
              if (color.getColor().equals(name)) {
                flag = true;
                color.setQty(color.getQty() + cart.getQty());
                if (color.getTotalPrice() == null) {
                  color.setTotalPrice(cart.getAmtTotal());
                } else {
                  color.setTotalPrice(color.getTotalPrice().add(cart.getAmtTotal()));
                }
                break;
              }
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
          if (flag == false) {
            view.add(build(cart));
          }
        }
      }
    }
    return views;
  }

  public static ColorView build(UsrCart cart) {
    ColorView view = new ColorView();
    try {
      PdtSpec spec = cart.gtSpec();
      view.setColor(spec.gtColor().getName(HomeAction.curLanguage()));
      if (spec.getPics() != null) {
        String[] pics = spec.getPics().split(",");
        view.setImg(pics.length > 0 ? pics[0] : "");
      }
      view.setProId(spec.getProduct());
      view.setId(spec.getColor());
      view.setName(spec.gtProduct().getName(HomeAction.curLanguage()));
      view.setQty(cart.getQty());
      view.setTotalPrice(cart.getAmtTotal());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return view;
  }

  public static List<ColorView> build(List<UsrCart> carts) {
    List<ColorView> views = new ArrayList<ColorView>();
    for (UsrCart cart : carts) {
      boolean flag = false;
      for (ColorView view : views) {
        try {
          PdtSpec spec = cart.gtSpec();
          String name = spec.gtColor().getName(HomeAction.curLanguage());
          if (view.getProId().equals(cart.gtSpec().getProduct()) && view.getColor().equals(name)) {
            flag = true;
            break;
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
      if (flag == false) {
        views.add(build(cart));
      }
    }
    return views;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public long getQty() {
    return qty;
  }

  public void setQty(long qty) {
    this.qty = qty;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getProId() {
    return proId;
  }

  public void setProId(Integer proId) {
    this.proId = proId;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }
}
