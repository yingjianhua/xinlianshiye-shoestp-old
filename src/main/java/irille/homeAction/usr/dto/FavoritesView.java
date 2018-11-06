package irille.homeAction.usr.dto;

import irille.homeAction.HomeAction;
import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import irille.shop.pdt.Pdt;
import irille.shop.plt.PltErateDAO;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.usr.UsrFavorites;
import org.json.JSONException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class FavoritesView {
    @SetBean(OriginalField = "picture")
    private String img;
    private String name;
    //价格
    @SetBean(OriginalField = "cur_price")
    private BigDecimal amt;
    @SetBean(OriginalField = "product_type")
    private Integer groupLine;
    //产品ID
    @SetBean(OriginalField = "product")
    private Integer pdtPkey;
    //购物车Id
    @SetBean(OriginalField = "pkey")
    private Integer id;

    public static FavoritesView buildByFavorite(UsrFavorites favorite) {
        FavoritesView view = new FavoritesView();
        try {
            String[] pics = favorite.gtProduct().getPicture().split(",");
            view.setImg(pics.length > 0 ? pics[0] : "");
            if (Integer.valueOf(favorite.gtProduct().getProductType()).equals(Integer.valueOf(Pdt.OProductType.GROUP.getLine().getKey()))) {
                view.setAmt(favorite.gtProduct().getCurPrice());
                PrmGroupPurchaseLine groupLine = PrmGroupPurchaseLine.chkUniqueProduct(false, favorite.getProduct());
                view.setGroupLine(groupLine.getPkey());
            } else {
                view.setAmt(favorite.gtProduct().getCurPrice());
            }
            view.setPdtPkey(favorite.getProduct());
            view.setId(favorite.getPkey());
            view.setName(favorite.gtProduct().getName(HomeAction.curLanguage()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }


    public static List<FavoritesView> buildByFavorite(List<UsrFavorites> favorites) {
        List<FavoritesView> views = new ArrayList<FavoritesView>();
        for (UsrFavorites favorite : favorites) {
            views.add(buildByFavorite(favorite));
        }
        return views;

    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        if (img != null) {
            String[] strings = img.split(",");
            if (strings.length > 0) {
                this.img = strings[0];
                return;
            }
        }
        this.img = img;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = PltErateDAO.Query.getTargetPrice(amt, HomeAction.curCurrency().getNowrate()).setScale(2, RoundingMode.HALF_UP);
    }

    public Integer getGroupLine() {
        return groupLine;
    }

    public void setGroupLine(Integer groupLine) {
        this.groupLine = groupLine;
    }

    public Integer getPdtPkey() {
        return pdtPkey;
    }

    public void setPdtPkey(Integer pdtPkey) {
        this.pdtPkey = pdtPkey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"img\":\"" + img + '\"' +
                ", \"name\":\"" + name + '\"' +
                ", \"amt\":" + amt +
                ", \"groupLine\":" + groupLine +
                ", \"pdtPkey\":" + pdtPkey +
                ", \"id\":" + id +
                '}';
    }
}
