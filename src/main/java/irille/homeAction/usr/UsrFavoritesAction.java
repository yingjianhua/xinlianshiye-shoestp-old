package irille.homeAction.usr;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.FavoritesView;
import irille.pub.Exp;
import irille.pub.LogMessage;
import irille.pub.bean.BeanBase;
import irille.pub.i18n.I18NUtil;
import irille.pub.idu.IduPage;
import irille.pub.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtCatDAO;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import irille.shop.pdt.PdtSpec;
import irille.shop.usr.Usr;
import irille.shop.usr.UsrCart;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrFavoritesDAO;

/**
 * 采购商action
 *
 * @author yjh
 */
public class UsrFavoritesAction extends HomeAction<UsrFavorites> {
	private static final LogMessage LOG = new LogMessage(UsrFavoritesAction.class);
    private static final PdtCatDAO.Query CATQUERY = new PdtCatDAO.Query();
    private static final UsrFavoritesDAO.PageSelect pageSelect = new UsrFavoritesDAO.PageSelect();

    private static final String timeout_err = "timeout.";
    private byte code;
    private String type;
    private String pkeyList;
    private String rowVersionList;
    private int catPkey;

    public int getCatPkey() {
        return catPkey;
    }

    public void setCatPkey(int catPkey) {
        this.catPkey = catPkey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRowVersionList() {
        return rowVersionList;
    }

    public void setRowVersionList(String rowVersionList) {
        this.rowVersionList = rowVersionList;
    }

    public String getPkeyList() {
        return pkeyList;
    }

    public void setPkeyList(String pkeyList) {
        this.pkeyList = pkeyList;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    private List<PdtCat> catList;

    public List<PdtCat> getCatList() {
        return catList;
    }

    public void setCatList(List<PdtCat> catList) {
        this.catList = catList;
    }


    private int pageNumber=1;
    private int category;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    private List<FavoritesView> favoritesViews;
    private int pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<FavoritesView> getFavoritesViews() {
        return favoritesViews;
    }

    public void setFavoritesViews(List<FavoritesView> favoritesViews) {
        this.favoritesViews = favoritesViews;
    }

    /**
     * 返回回收站页面
     *
     * @author liyichao
     */
    public String myRecycle() {
        if (getPurchase() == null) {
            setResult("/home/usr_UsrPurchase_sign?&jumpUrl=/home/usr_UsrFavorites_myRecycle");
            return RTRENDS;
        }
        IduPage page = new IduPage();
        page.setStart(pageNumber);
        page.setLimit(8);

        UsrFavoritesDAO.PageSelect pageSelect = new UsrFavoritesDAO.PageSelect();
        List pageFavorites = pageSelect.getFavoritesListByCat(page, -1, getPurchase().getPkey(), Sys.OYn.NO);
        setFavoritesViews(pageFavorites);
        pageCount = (int) Math.ceil((double) pageSelect.getFavoritesCountByCat(page, category, getPurchase().getPkey(), Sys.OYn.NO) / 8);
        setResult("/home/recyclebin.jsp");
        return HomeAction.TRENDS;
    }

    public void myRecycleAjax() throws IOException {
        if (getPurchase() == null) {
            writeErr(-1, "未登录");
        }
        IduPage page = new IduPage();
        page.setStart(pageNumber);
        page.setLimit(8);

        UsrFavoritesDAO.PageSelect pageSelect = new UsrFavoritesDAO.PageSelect();
        List pageFavorites = pageSelect.getFavoritesListByCat(page, -1, getPurchase().getPkey(), Sys.OYn.NO);
        setFavoritesViews(pageFavorites);
//        pageCount = (int) Math.ceil((double) pageSelect.getFavoritesCountByCat(page, category, getPurchase().getPkey(), Sys.OYn.NO) / 8);
        write(pageFavorites);
    }

    /**
     * @Description:
     * @author lijie@shoestp.cn
     * @date 2018/8/21 18:10
     */
    @NeedLogin
    public String myfavorite() {
        catList = CATQUERY.listTopCat();
        for (PdtCat cat : catList) {
            translateUtil.getAutoTranslate(cat, HomeAction.curLanguage());
        }
        IduPage page = new IduPage();
        page.setStart(pageNumber);
        page.setLimit(8);
        List pageFavorites = pageSelect.getFavoritesListByCat(page, category, getPurchase().getPkey(), Sys.OYn.YES);
        setFavoritesViews(pageFavorites);
        pageCount = (int) Math.ceil((double) pageSelect.getFavoritesCountByCat(page, category, getPurchase().getPkey(), Sys.OYn.YES) / 8);
        setResult("/home/my-favorite.jsp");
        return HomeAction.TRENDS;
    }

    public void myfavoriteAjax() throws IOException {
        if (getPurchase() == null) {
            writeErr(-1, "未登录");
        }
        IduPage page = new IduPage();
        page.setStart(pageNumber);
        List pageFavorites = pageSelect.getFavoritesListByCat(page, category, getPurchase().getPkey(), Sys.OYn.YES);
        write(pageFavorites);
    }


    /**
     * 将收藏夹中的数据进行逻辑删除
     *
     * @throws IOException
     * @throws JSONException
     * @author liyichao
     */
    public void recycleFavorite() throws IOException, JSONException {
        UsrFavoritesDAO.RecycleFavorite recycle = new UsrFavoritesDAO.RecycleFavorite();
        if (getPkeys() != null && getPkeys().trim() != "") {
            recycle.setBkeys(getPkeys());
        } else {
            recycle.setBKey(getPkey());
        }
        try{
        	recycle.commit();
        	write();
        }catch(Exp e){
        	writeErr(e.getLastMessage());
        }
    }


//	private String catViewList;
    /**
     * 将收藏夹中的商品批量加入到购物车
     *
     * @author liyichao
     * @throws Exception
     */
//	public void favoriteToCart() throws Exception{
//		JSONObject json = new JSONObject();
//		JSONArray jo = new JSONArray(getCatViewList());
//		List<UsrCart> cartList = new ArrayList<UsrCart>();
//		int errCount = 0;
//		for(int i=0;i<jo.length();i++){
//			JSONObject frontJSON = jo.getJSONObject(i);
//			PdtSpec spec = null;
//			try{
//				spec = BeanBase.list(PdtSpec.class,PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + frontJSON.get("product"),false).get(0);
//				//需要获得规格/采购商/类型
//				Integer test = frontJSON.getInt("joinType");
//				UsrCart existsCart = UsrCart.chkUniqueSpec_purchase(false, spec.getPkey(), HomeAction.getPurchase().getPkey());
//				if(existsCart == null){
//					UsrCart cart = new UsrCart();
//					cart.setPurchase(HomeAction.getPurchase().getPkey());
//					cart.setSpec(spec.getPkey());
//					cart.setQty(frontJSON.getInt("qty"));
//					cart.setAmtTotal(new BigDecimal(cart.getQty()).multiply(new BigDecimal(frontJSON.getDouble("amtTotal"))));
//					cart.setSupplier(frontJSON.getInt("supplier"));
//					cartList.add(cart);
//				}else{
//					existsCart.setQty(existsCart.getQty()+frontJSON.getInt("qty"));
//					existsCart.setAmtTotal(existsCart.getAmtTotal().add(new BigDecimal(frontJSON.getInt("qty")).multiply(new BigDecimal(frontJSON.getDouble("amtTotal")))));
//					existsCart.upd();
//				}
//			}catch(ArrayIndexOutOfBoundsException e){
//				errCount++;
//				throw LOG.err(Usr.ErrMsgs.emptyErr,PdtSpec.TB.getName());
//			}finally{
//				continue;
//			}
//		}
//		
//		UsrCartDAO.AddCartList cartDAO = new UsrCartDAO.AddCartList();
//		cartDAO.setCartList(cartList);
//		cartDAO.commit();
//		json.put("errCount",errCount);
//		json.put("success",true);
//		writeSuccess(json);
//		
//	}


    private Integer pdtPkey;
    private String favoritesPkeys;

    /**
     * 将收藏夹中的单个商品加入购物车
     *
     * @return
     * @throws Exception
     * @author liyichao
     */
    public void addSinglePdt() throws Exception {
        if (getFavoritesPkeys() == null || getFavoritesPkeys().trim() == "") {
//            throw LOG.err(Usr.ErrMsgs.noSubmit);
            throw LOG.err("没有选择产品", I18NUtil.getBundle("PleaseSelect"));
        }
        String[] favoritePkeyArr = getFavoritesPkeys().split(",");
        int errCount = 0;
        for (String favorite : favoritePkeyArr) {
            try {
                UsrFavorites fav = BeanBase.load(UsrFavorites.class, favorite);
                Integer favPkey = fav.getProduct();

                PdtSpec spec = BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + favPkey, false).get(0);
                UsrCart existsCart = UsrCart.chkUniqueSpec_purchase(false, spec.getPkey(), HomeAction.getPurchase().getPkey());
                if (existsCart == null) {
                    UsrCart singlePdt = new UsrCart();
                    singlePdt.setSpec(spec.getPkey());
                    singlePdt.stPurchase(HomeAction.getPurchase());
                    singlePdt.setSupplier(spec.gtProduct().getSupplier());
                    singlePdt.setQty(1);
                    singlePdt.setAmtTotal(spec.getPrice().multiply(BigDecimal.ONE));
                    fav.gtProduct().setFavoriteCount(fav.gtProduct().getFavoriteCount() + 1);
                    fav.gtProduct().upd();
                    singlePdt.ins();
                } else {
                    existsCart.setQty(existsCart.getQty() + 1);
                    existsCart.setAmtTotal(existsCart.getAmtTotal().add(spec.getPrice().multiply(BigDecimal.ONE)));
                    existsCart.upd();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                errCount++;
                throw LOG.errTran("goods_info%No_Spec","该产品尚未发布规格");
            } finally {
                continue;
            }
        }
        JSONObject json = new JSONObject();
        json.put("errCount", errCount);
        writeSuccess(json);

    }

    /**
     * 回收站的商品到收藏夹
     *
     * @throws JSONException
     * @throws IOException
     * @author liyichao
     */
    public void restoreFavorite() throws IOException, JSONException {
        UsrFavoritesDAO.RestoreFavorite restore = new UsrFavoritesDAO.RestoreFavorite();
        restore.setBKey(getPkey());
        try{
        	restore.commit();
        	write();
        }catch(Exp e){
        	writeErr(e.getLastMessage());
        }
    }


    /**
     * 删除收藏夹商品
     *
     * @return
     * @throws JSONException
     * @throws IOException
     * @author liyichao
     */
    public void delFavorite() throws IOException, JSONException {
        UsrFavoritesDAO.DelFavorite delFavorite = new UsrFavoritesDAO.DelFavorite();
        if (getPkeys() != null && getPkeys().trim() != "") {
            delFavorite.setBkeys(getPkeys());
        } else {
            delFavorite.setBKey(getPkey());
        }
        try{
        	delFavorite.commit();
        	write();
        }catch(Exp e){
        	writeErr(e.getLastMessage());
        }
    }

    /**
     * xiayan
     * 查询所有收藏
     */
    @NeedLogin
    public void getAllFavorite() throws Exception {
        JSONObject json = new JSONObject();
        /**
         * @Description: 添加堆show_state的判断
         * @author lijie@shoestp.cn
         * @date 2018/8/25 14:37
         */
        String sql = UsrFavorites.T.PURCHASE.getFld().getCodeSqlField() + "=" + HomeAction.getPurchase().getPkey() + " AND ";
        sql += UsrFavorites.T.SHOW_STATE.getFld().getCodeSqlField() + "=" + Sys.OYn.YES.getLine().getKey();
        List<UsrFavorites> list = BeanBase.list(UsrFavorites.class, sql, false);
        json.put("fa", new JSONArray(list, false));
        write(json.toString());
    }

    /**
     * 将商品加入到收藏夹
     * 使用:传入产品pkey -> pdtPkey
     *
     * @throws Exception
     * @author liyichao
     * @updatetime 2018-8-16
     */
    @NeedLogin
    public void addFavorite() throws Exception {
        JSONObject json = new JSONObject();
        if (getPurchase() == null) {
            json.put("data", "-1");
            write(json.toString());
            return;
        }
        int type = -1;
        int number = 0;
        UsrFavorites favorite = UsrFavorites.chkUniquePurchase_product(false, getPurchase().getPkey(), pdtPkey);
        if (favorite == null) {
            UsrFavoritesDAO.AddFavorites ins = new UsrFavoritesDAO.AddFavorites();
            ins.setPdtPkey(pdtPkey);
            ins.commit();
            PdtProduct pro = PdtProduct.load(PdtProduct.class, pdtPkey);
            number = pro.getFavoriteCount();
            type = 0;
        }
        if (favorite != null) {
            if (Integer.valueOf(favorite.getShowState()).equals(Integer.valueOf(Sys.OYn.NO.getLine().getKey()))) {
                favorite.setShowState(Sys.OYn.YES.getLine().getKey());
                favorite.upd();
                PdtProductDAO.Upd upd = new PdtProductDAO.Upd();
                upd.updateFavorite(favorite.getProduct());
                number = favorite.gtProduct().getFavoriteCount();
                type = 0;
            } else {
                PdtProduct pro = favorite.gtProduct();
                if (pro.getFavoriteCount() <= 0) {
                    pro.setFavoriteCount(0);
                } else {
                    pro.setFavoriteCount(pro.getFavoriteCount() - 1);
                }
                number = pro.getFavoriteCount();
                pro.upd();
                favorite.del();
                type = 1;
            }
        }
        json.put("number", number);
        json.put("type", type);
        json.put("ret", "1");
        write(json.toString());
    }

    public String getFavoritesPkeys() {
        return favoritesPkeys;
    }

    public void setFavoritesPkeys(String favoritesPkeys) {
        this.favoritesPkeys = favoritesPkeys;
    }

    public Integer getPdtPkey() {
        return pdtPkey;
    }

    public void setPdtPkey(Integer pdtPkey) {
        this.pdtPkey = pdtPkey;
    }
//	public String getCatViewList() {
//		return catViewList;
//	}
//	public void setCatViewList(String catViewList) {
//		this.catViewList = catViewList;
//	}
}