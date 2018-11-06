package irille.shop.usr;

import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.query.BeanQuery;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import irille.shop.pdt.PdtSpec;
import irille.shop.prm.PrmGroupPurchase;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.usr.UsrCart.T;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UsrCartDAO {
    public static final LogMessage LOG = new LogMessage(UsrCartDAO.class);

    /**
     * @author yingjianhua
     */
    public static class Query extends IduOther<Query, UsrCart> {
        //根据主键查询采购商有多少件商品
        public static Integer countByPurchase(Integer purchase) {
            return irille.pub.bean.Query.SELECT(UsrCart.class).WHERE(T.PURCHASE, "=?", purchase).queryCount();
        }

        //查询当前采购商的所有购物车
        public List<UsrCart> listByPurchase(Integer pkey) {
            String where = UsrCart.T.PURCHASE.getFld().getCodeSqlField() + "=" + pkey;
            return BeanBase.list(UsrCart.class, where, false);
        }
    }

    public static class Ins extends IduIns<Ins, UsrCart> {
        @Override
        public void before() {
            super.before();
        }
    }

    public static class InsCart extends IduIns<InsCart, UsrCart> {
        private static final PdtProductDAO.pageSelect pdtselect = new PdtProductDAO.pageSelect();

        /**
         * 加入购物车
         * 没有规格记录,插入数据
         * 如果已有规格记录,update记录
         *
         * @author lijie@shoestp.cn
         * @Description: 加入购物车
         * @date 2018/8/8 10:54
         */
//        public int inserCart(long specId, int qty, int purId) {
//            BigDecimal qtys = BigDecimal.valueOf(qty);
//            PdtSpec spec = BeanBase.get(PdtSpec.class, specId);
//            FormaterSql sql = FormaterSql.build(this);
//            sql.select(UsrCart.T.PKEY, UsrCart.T.QTY, UsrCart.T.ROW_VERSION).eq(UsrCart.T.SPEC).and().eq(UsrCart.T.CART_TYPE);
//            List parmList = new ArrayList();
//            parmList.add(specId);
//            parmList.add(Usr.OJoinType.GENERAL.getLine().getKey());
//            Map result = sql.castMap((BeanBase.queryOneRowIsNull(sql.buildSql(), sql.getParms(parmList))));
//            if (qtys.compareTo(spec.getStoreCount()) < 1) {
//                if (result == null) {
//                    UsrCart usrCart = new UsrCart();
//                    usrCart.setPurchase(purId);
//                    usrCart.setSupplier(spec.gtProduct().gtSupplier().getPkey());
//                    usrCart.setSpec(spec.getPkey());
//                    usrCart.setQty(qty);
//                    usrCart.setAmtTotal(qtys.multiply(spec.getPrice()));
//                    usrCart.setCartType(Usr.OJoinType.GENERAL.getLine().getKey());
//                    usrCart.setRelatedGroup(0);
//                    setB(usrCart);
//                    commit();
//                } else {
//                    sql.clean().update(UsrCart.T.QTY, UsrCart.T.ROW_VERSION, UsrCart.T.AMT_TOTAL).eq(UsrCart.T.PKEY).and().eq(UsrCart.T.QTY).and().eq(UsrCart.T.ROW_VERSION);
//                    parmList.clear();
//                    parmList.add(qtys);
//                    parmList.add(Long.valueOf(String.valueOf(result.get(UsrCart.T.ROW_VERSION.getFld().getCodeSqlField()))) + 1);
//                    parmList.add(spec.getPrice().multiply(qtys));
//                    parmList.add(result.get(UsrCart.T.PKEY.getFld().getCodeSqlField()));
//                    parmList.add(result.get(UsrCart.T.QTY.getFld().getCodeSqlField()));
//                    parmList.add(result.get(UsrCart.T.ROW_VERSION.getFld().getCodeSqlField()));
//                    return BeanBase.executeUpdate(sql.buildSql(), sql.getParms(parmList));
//                }
//                return 1;
//            }
//            return -2;
//        }


    }

    /**
     * 商品加入到购物车(需判断联合采购商品还是普通商品)
     */
    public static class AddCartList extends IduOther<AddCartList, UsrCart> {
        private List<UsrCart> cartList;
        private int newCount;

        public int getNewCount() {
            return newCount;
        }

        public void setNewCount(int newCount) {
            this.newCount = newCount;
        }

        public List<UsrCart> getCartList() {
            return cartList;
        }

        public String pkeys = "";
        private Integer i = 0;

        public void setCartList(List<UsrCart> cartList) {
            this.cartList = cartList;
        }

        public void before() {
            if (cartList != null) {
                Iterator<UsrCart> cartIterator = cartList.iterator();
                while (cartIterator.hasNext()) {
                    UsrCart cart = cartIterator.next();
                    if (cart.getQty() == null || cart.getQty().intValue() == 0) {
                        cartIterator.remove();
                    }
                }
            }
        }

        public void valid() {
            //TODO
        }

        public void after() {
            setNewCount(0);
            if (cartList != null) {
                for (UsrCart c : cartList) {
                    UsrCart existsCart = UsrCart.chkUniqueSpec_purchase(false, c.getSpec(), HomeAction.getPurchase().getPkey());
                    if (existsCart == null) {
                        UsrCart cart = new UsrCart();
                        cart.setPurchase(HomeAction.getPurchase().getPkey());
                        cart.setSupplier(c.gtSpec().gtProduct().getSupplier());
                        cart.setSpec(c.getSpec());
                        cart.setQty(c.getQty());
                        cart.setAmtTotal(c.gtSpec().getPrice().multiply(new BigDecimal(c.getQty())));
                        cart.ins();
                        setNewCount(getNewCount() + 1);
                        if (i.intValue() == 0) {
                            pkeys += cart.getPkey();
                        } else {
                            pkeys += "," + cart.getPkey();
                        }
                    } else {
                        existsCart.setQty(existsCart.getQty() + c.getQty());
                        existsCart.setAmtTotal(existsCart.getAmtTotal().add(c.gtSpec().getPrice().multiply(new BigDecimal(c.getQty()))));
                        existsCart.upd();
                        pkeys += existsCart.getPkey();
                    }
                    i++;
                }
            } else {
                getB().ins();
                setNewCount(getNewCount() + 1);
                pkeys += getB().getPkey();
            }
        }

    }

    /**
     * xiayan
     * 增加购物车
     *
     * @author xinlian
     */
    public static class InsUsrCart extends IduIns<InsUsrCart, UsrCart> {
        @Override
        public void before() {
            // TODO Auto-generated method stub
            super.before();
            setB(getB());
        }
    }


    /**
     * 处理购物车中提交的数据
     *
     * @author liyichao
     * @updateTime 2018-8-16
     */
    public static class SubmitCart extends IduOther<SubmitCart, UsrCart> {
        private String cartPkeys;
        Map<PdtProduct, Integer> specMap = new HashMap<PdtProduct, Integer>();

        public Map<PdtProduct, Integer> getSpecMap() {
            return specMap;
        }

        public void setSpecMap(Map<PdtProduct, Integer> specMap) {
            this.specMap = specMap;
        }

        public String getCartPkeys() {
            return cartPkeys;
        }

        public void setCartPkeys(String cartPkeys) {
            this.cartPkeys = cartPkeys;
        }

        private List<UsrCart> cartList;

        public List<UsrCart> getCartList() {
            return cartList;
        }

        public void setCartList(List<UsrCart> cartList) {
            this.cartList = cartList;
        }

        public void before() {
            setCartList(BeanBase.list(UsrCart.class, UsrCart.T.PKEY.getFld().getCodeSqlField() + " in( " + cartPkeys + " ) ", false));
            for (UsrCart cart : cartList) {
                for (UsrCart cart2 : cartList) {
                    if (!cart.getPurchase().equals(cart2.getPurchase())) {
                        throw LOG.errTran("global%unknown_mistake", "未知错误");
                    }
                }
                if (specMap.get(cart.gtSpec().gtProduct()) == null) {
                    specMap.put(cart.gtSpec().gtProduct(), cart.getQty());
                } else {
                    Integer allQty = specMap.get(cart.gtSpec().gtProduct()) + cart.getQty();
                    specMap.put(cart.gtSpec().gtProduct(), allQty);
                }
            }
        }

        public void valid() {
            if (cartList == null || cartList.size() <= 0) {
                throw LOG.errTran("cart%checked_error", "请选择至少一个产品");
            }
            for (UsrCart cart : cartList) {
                PdtProduct product = cart.gtSpec().gtProduct();
                PdtSpec spec = cart.gtSpec();
                if (Integer.valueOf(product.getProductType()).equals(Integer.valueOf(Pdt.OProductType.GROUP.getLine().getKey()))) {
                    PrmGroupPurchaseLine groupLine = PrmGroupPurchaseLine.chkUniqueProduct(false, product.getPkey());
                    if (groupLine == null) {
                        throw LOG.errTran("global%unknown_mistake", "未知错误");
                    }
                    String name = null;
                    try {
                        name = product.getName(HomeAction.curLanguage());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    PrmGroupPurchase group = groupLine.gtMain();
                    if (group.getStartTime().getTime() > System.currentTimeMillis()) {
                        throw LOG.errTran("seckill%activity_belongs_begin", "所属活动即将开始", name);
                    }
                    if (group.getEndTime().getTime() < System.currentTimeMillis()) {
                        throw LOG.errTran("seckill%activity_belongs_end", "所属活动已结束", name);
                    }
                } else {
                    //判断普通商品库存
                    if (spec.getStoreCount() < 0) {
                        //和产品中的库存比较
                        try {
                            if (cart.getQty() > product.getMinOq() && cart.getQty() > product.getStock()) {
                                throw LOG.errTran("cart%prod_stock_error", "该商品已售罄", product.getName(HomeAction.curLanguage()));
                            }
                            if (product.getStock() == 0) {
                                throw LOG.errTran("cart%prod_stock_error", "该商品已售罄", product.getName(HomeAction.curLanguage()));
                            }
                            if (cart.getQty() > product.getStock()) {
                                throw LOG.errTran("cart%stock_error", "该商品库存不足", product.getName(HomeAction.curLanguage()));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //和规格中的库存比较
                        try {
                            if (cart.getQty() > product.getMinOq() && cart.getQty() > spec.getStoreCount()) {
                                throw LOG.errTran("cart%prod_stock_error", "该商品已售罄", product.getName(HomeAction.curLanguage()));
                            }
                            if (spec.getStoreCount() == 0) {
                                throw LOG.errTran("cart%prod_stock_error", "该商品已售罄", product.getName(HomeAction.curLanguage()));
                            }
                            if (cart.getQty() > spec.getStoreCount()) {
                                throw LOG.errTran("cart%stock_error", "该商品库存不足", product.getName(HomeAction.curLanguage()));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            for (PdtProduct product : specMap.keySet()) {
                product = translateUtil.getAutoTranslate(product, HomeAction.curLanguage());
                String name = product.getName();
                Integer qty = specMap.get(product);
                if (qty < product.getMinOq().intValue()) {
                    throw LOG.errTran("cart%tooLess", "购买数量过少", name, product.getMinOq());
                }
                if (Integer.valueOf(product.getState()).equals(Integer.valueOf(Pdt.OState.DELETE.getLine().getKey()))) {
                    throw LOG.errTran("products%product_deleted", "商品已被删除", name);
                }
                if (Integer.valueOf(product.getState()).equals(Integer.valueOf(Pdt.OState.OFF.getLine().getKey()))) {
                    throw LOG.errTran("products%no_up_carriage", "商品未上架", name);
                }
            }
        }
    }

    /**
     * 修改购物车中商品数量
     *
     * @author liyichao
     */
    public static class UpdateQty extends IduOther<UpdateQty, UsrCart> {
        private Integer qty;

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        public void before() {
            getB().setQty(getQty());
        }

        public void run() {
            UsrCart cart = loadThisBeanAndLock();
            cart.setAmtTotal(cart.gtSpec().getPrice().multiply(new BigDecimal(getQty())));
            PropertyUtils.copyProperties(cart, getB(), T.QTY);
            cart.upd();
        }

    }


    public static void judgeBuyNow(String str) throws JSONException {
        JSONObject json = new JSONObject(str);

        Integer qtyAll = 0;
        Iterator it = json.keys();
        PdtProduct product = null;
        UsrPurchase purchase = HomeAction.getPurchase();
        BeanQuery<UsrPurchaseLine> q = irille.pub.bean.Query
                .SELECT(UsrPurchaseLine.class)
                .WHERE(UsrPurchaseLine.T.PURCHASE, "=?", purchase.getPkey())
                .WHERE(UsrPurchaseLine.T.ADDRSSTYPE, "=?", Usr.OAddress.BILLED);
//		SQL sql = new SQL(){{
//			SELECT(UsrPurchaseLine.class);
//			FROM(UsrPurchaseLine.class);
//			WHERE(UsrPurchaseLine.T.PURCHASE,"=?").PARAM(purchase.getPkey());
//			WHERE(UsrPurchaseLine.T.ADDRSSTYPE, "=?").PARAM(Usr.OAddress.BILLED.getLine().getKey());
//		}};
//		SqlQuery q = irille.pub.bean.Query.sql(sql);
        Integer count = q.queryCount();
        if (count <= 0) {
            throw LOG.errTran("addressfrom%Please_Select_The_Billing_Address", "请先设置账单地址");
        }
        while (it.hasNext()) {
            String specId = String.valueOf(it.next());
            //得到value的值
            Integer qty = json.getInt(specId);
            qtyAll += qty;
            PdtSpec spec = BeanBase.load(PdtSpec.class, specId);
            if (product == null) {
                product = spec.gtProduct();
            }
            if (spec.getStoreCount() < 0) {
                //和产品中的库存比较
                try {
                    if (product.getStock() < product.getMinOq()) {
                        throw LOG.errTran("cart%prod_stock_error", "该商品已售罄", product.getName(HomeAction.curLanguage()));
                    }
                    if (product.getStock() == 0) {
                        throw LOG.errTran("cart%prod_stock_error", "该商品已售罄", product.getName(HomeAction.curLanguage()));
                    }
                    if (qty > product.getStock()) {
                        throw LOG.errTran("cart%stock_error", "该商品库存不足", product.getName(HomeAction.curLanguage()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                //和规格中的库存比较
                try {
                    if (spec.getStoreCount() == 0) {
                        throw LOG.errTran("cart%prod_stock_error", "该商品已售罄", product.getName(HomeAction.curLanguage()));
                    }

                    if (qty > spec.getStoreCount()) {
                        throw LOG.errTran("cart%stock_error", "该商品库存不足", product.getName(HomeAction.curLanguage()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        String name = product.getName(HomeAction.curLanguage());
        if (qtyAll < product.getMinOq()) {
            throw LOG.errTran("cart%tooLess", "购买数量过少", name, product.getMinOq());
        }

    }

    /**
     * 添加规格到购物车
     *
     * @author liyichao
     */
    public static class AddSpecToCart extends IduOther<AddSpecToCart, UsrCart> {
        private PdtSpec spec;
        private Integer qty;

        public void after() {
            UsrCart cart = UsrCart.chkUniqueSpec_purchase(false, spec.getPkey(), HomeAction.getPurchase().getPkey());
            if (cart != null) {
                if (getQty() == null || getQty().intValue() == 0) {
                    cart.setQty(cart.getQty() + 1);
                } else {
                    cart.setQty(cart.getQty() + getQty());
                }
                if (Integer.valueOf(cart.gtSpec().gtProduct().getProductType()).equals(Integer.valueOf(Pdt.OProductType.GROUP.getLine().getKey()))) {
                    cart.setAmtTotal(cart.getAmtTotal().add(cart.gtSpec().getPrice()));
                } else {
                    cart.setAmtTotal(cart.getAmtTotal().add(cart.gtSpec().getPrice()));
                }
                cart.upd();
            } else {
                UsrCart newCart = new UsrCart();
                newCart.setSpec(spec.getPkey());
                newCart.setPurchase(HomeAction.getPurchase().getPkey());
                if (getQty() != null && getQty().intValue() != 0) {
                    newCart.setQty(getQty());
                } else {
                    newCart.setQty(1);
                }
                newCart.setAmtTotal(spec.getPrice());
                newCart.setSupplier(spec.gtProduct().getSupplier());
                newCart.ins();
            }
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        public PdtSpec getSpec() {
            return spec;
        }

        public void setSpec(PdtSpec spec) {
            this.spec = spec;
        }
    }

    /**
     * 订单生成后删除购物车
     */
    public static void delCart(Integer spec) {
        UsrCart cart = UsrCart.chkUniqueSpec_purchase(false, spec, HomeAction.getPurchase().getPkey());
        cart.del();
    }
}
