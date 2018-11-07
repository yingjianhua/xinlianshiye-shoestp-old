package irille.shop.usr;

import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.svr.Env;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import irille.shop.usr.UsrFavorites.T;
import irille.view.Page;
import irille.view.usr.FavoriteView;

import java.util.ArrayList;
import java.util.List;

public class UsrFavoritesDAO {
    private static final Log LOG = new Log(UsrFavorites.class);


    public static Integer countByPurchase(Integer purchase) {
        return Query.SELECT(UsrFavorites.class).WHERE(UsrFavorites.T.PURCHASE, "=?", purchase).WHERE(T.SHOW_STATE, "=?", BeanBase.booleanToByte(true)).queryCount();
    }

    /**
     * @author yingjianhua
     */
    public static Page<FavoriteView> pageByPurchase(Integer purchase, Integer start, Integer limit) {
        BeanQuery<UsrFavorites> q = Query.SELECT(UsrFavorites.class).WHERE(UsrFavorites.T.PURCHASE, "=?", purchase).WHERE(T.SHOW_STATE, "=?", BeanBase.booleanToByte(true));
        List<FavoriteView> views = new ArrayList<>();
        for (UsrFavorites bean : q.limit(start, limit).queryList()) {
            FavoriteView view = new FavoriteView();
            PdtProduct product = bean.gtProduct();
            view.setId(bean.getPkey());
            view.setProductId(product.getPkey());
            view.setName(product.getName());
            view.setImage(product.getPicture().split(",")[0]);
            views.add(view);
        }
        return new Page<FavoriteView>(views, start, limit, q.queryCount());
    }


    public static class Ins extends IduIns<Ins, UsrFavorites> {
        private Integer productId;//获取前端传过来的产品编号
        private byte pdtType;

        public byte getPdtType() {
            return pdtType;
        }

        public void setPdtType(byte pdtType) {
            this.pdtType = pdtType;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

//        @Override
//        public void before() {
//            getB().stPurchase(HomeAction.getPurchase());
//            getB().setProduct(productId);
//            getB().setShowState(Sys.OYn.YES.getLine().getKey());
//            getB().setAddType(pdtType);
//        }

        /**
         * @author yingjianhua
         */
        public static class Query extends IduOther<Query, UsrFavorites> {
            public Integer countFavoriteByPurchase(Integer pkey) {
                return this.countWhere(UsrFavorites.T.PURCHASE.getFld().getCodeSqlField() + "=?", pkey);
            }

        }
    }

//	public static class Ins extends IduIns<Ins , UsrFavorites>{
//			private Integer productId;//获取前端传过来的产品编号
//			private byte pdtType;
//			public byte getPdtType() {
//				return pdtType;
//			}
//			public void setPdtType(byte pdtType) {
//				this.pdtType = pdtType;
//			}
//
//			public Integer getProductId() {
//				return productId;
//			}
//
//			public void setProductId(Integer productId) {
//				this.productId = productId;
//			}
//
//			@Override
//			public void before() {
//				getB().stPurchase(HomeAction.getPurchase());
//				getB().setProduct(productId);
//				getB().setShowState(Sys.OYn.YES.getLine().getKey());
//				getB().setAddType(pdtType);
//			}

			/*@Override


/*@Override
			public void valid() {
				super.valid();
				UsrFavorites favorites = UsrFavorites.chkUniquePurchase_product(false, HomeAction.getPurchase().getPkey(), productId);
				if(favorites != null){
					throw LOG.err(Usr.ErrMsgs.copyErr,UsrFavorites.T.PRODUCT.getFld().getName());
				}
			}*/

//	 }

    /**
     * 根据PKEY修改显示收藏信息状态
     *
     * @author guosong
     */
    public static class Upd extends IduOther<Upd, UsrFavorites> {
        @Override
        public void before() {
            UsrFavorites model = loadThisBeanAndLock();
            if (model.gtShowState() == true) {
                getB().stShowState(false);
                PropertyUtils.copyProperties(model, getB(), UsrFavorites.T.SHOW_STATE);
                model.upd();
            } else {
                model.del();
            }
        }

    }

    public static class AddFavorites extends IduOther<AddFavorites, UsrFavorites> {
        private Integer pdtPkey;

        public Integer getPdtPkey() {
            return pdtPkey;
        }

        public void setPdtPkey(Integer pdtPkey) {
            this.pdtPkey = pdtPkey;
        }

        public void run() {
            UsrFavorites newFavorite = new UsrFavorites();
            newFavorite.setAddTime(Env.getSystemTime());
            newFavorite.setProduct(pdtPkey);
            newFavorite.setShowState(Sys.OYn.YES.getLine().getKey());
            newFavorite.setPurchase(HomeAction.getPurchase().getPkey());
            newFavorite.ins();
        }

        @Override
        public void after() {
            PdtProductDAO.Upd upd = new PdtProductDAO.Upd();
            upd.updateFavorite(pdtPkey);
        }

    }

    /**
     * 逻辑删除,将收藏夹的显示状态改为0
     *
     * @author liyichao
     */
    public static class RecycleFavorite extends IduOther<RecycleFavorite, UsrFavorites> {
        private String bkeys;

        public String getBkeys() {
            return bkeys;
        }

        public void setBkeys(String bkeys) {
            this.bkeys = bkeys;
        }

        @Override
        public void run() {
            if (bkeys == null || bkeys.trim() == "") {
                getB().setShowState(Sys.OYn.NO.getLine().getKey());
                getB().upd();
                PdtProductDAO.Upd upd = new PdtProductDAO.Upd();
                upd.removeFavorite(getB().getProduct());
            } else {
                BeanBase.executeUpdate(" UPDATE " + UsrFavorites.TB.getCodeSqlTb() + " SET " + UsrFavorites.T.SHOW_STATE.getFld().getCodeSqlField() + " = " + Sys.OYn.NO.getLine().getKey() + " WHERE " + UsrFavorites.T.PKEY.getFld().getCodeSqlField() + " in ( " + bkeys + " ) ");
                List<UsrFavorites> favoriteList = BeanBase.list(UsrFavorites.class, UsrFavorites.T.PKEY.getFld().getCodeSqlField() + " in ( " + bkeys + " ) ", false);
                PdtProductDAO.Upd upd = new PdtProductDAO.Upd();
                for (UsrFavorites favorite : favoriteList) {
                    upd.removeFavorite(favorite.getProduct());
                }
            }
        }

    }

    /**
     * 真实删除,删除收藏夹数据
     *
     * @author liyichao
     */
    public static class DelFavorite extends IduOther<DelFavorite, UsrFavorites> {
        private String bkeys;

        public String getBkeys() {
            return bkeys;
        }

        public void setBkeys(String bkeys) {
            this.bkeys = bkeys;
        }

        @Override
        public void run() {
            if (bkeys == null || bkeys.trim() == "") {
                getB().del();
            } else {
                BeanBase.executeUpdate(" DELETE FROM " + UsrFavorites.TB.getCodeSqlTb() + " WHERE pkey in ( " + bkeys + " ) ");
            }
        }

    }

    /**
     * 回收站的商品到收藏夹(show_state由0到1)
     *
     * @author liyichao
     */
    public static class RestoreFavorite extends IduOther<RestoreFavorite, UsrFavorites> {
        @Override
        public void run() {
            getB().setShowState(Sys.OYn.YES.getLine().getKey());
            getB().upd();
        }

        @Override
        public void after() {
            // TODO Auto-generated method stub
            super.after();
            PdtProductDAO.Upd upd = new PdtProductDAO.Upd();
            upd.updateFavorite(getB().getProduct());
        }
    }


}
