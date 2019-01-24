package irille.shop.prm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import irille.platform.prm.View.GroupPurchaseView.*;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduOther;
import irille.pub.svr.Env;
import irille.pub.validate.ValidNumber;
import irille.sellerAction.SellerAction;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderDAO;
import irille.shop.odr.OdrOrderLine;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtComment;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.prm.dto.GroupPdtView;
import irille.shop.prm.dto.StatisticsView;
import irille.shop.usr.UsrCart;
import irille.shop.usr.UsrConsult;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

public class PrmGroupPurchaseDAO {
    public static final Log LOG = new Log(PrmGroupPurchaseDAO.class);

    /**
     * 用于增加联合采购活动
     *
     * @author liyichao
     */
    public static class InsGroup extends IduOther<InsGroup, PrmGroupPurchase> {
        private Integer createBy;
        private List<GroupPdtView> lines;
        private List<PrmGroupPurchaseLine> groupLine = new ArrayList<PrmGroupPurchaseLine>();

        public List<PrmGroupPurchaseLine> getGroupLine() {
            return groupLine;
        }

        public void setGroupLine(List<PrmGroupPurchaseLine> groupLine) {
            this.groupLine = groupLine;
        }

        public Integer getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Integer createBy) {
            this.createBy = createBy;
        }

        public List<GroupPdtView> getLines() {
            return lines;
        }

        public void setLines(List<GroupPdtView> lines) {
            this.lines = lines;
        }

        public void before() {
            toValidLines(getLines());
            getB().setCreatedBy(createBy);
            getB().setCreatedTime(Env.getSystemTime());
            getB().setUpdatedTime(Env.getSystemTime());
            if (toPre(getB().getStartTime(), getB().getPreTime())) {
                getB().setStatus(Prm.OStatus.SOONSTART.getLine().getKey());
            } else {
                getB().setStatus(Prm.OStatus.NOTSTART.getLine().getKey());
            }

            for (GroupPdtView view : getLines()) {
                PrmGroupPurchaseLine line = new PrmGroupPurchaseLine();
                PdtProduct sourcePdt = BeanBase.load(PdtProduct.class, view.getProduct());
                PdtProduct actProduct = new PdtProduct();
                actProduct.setSourceProduct(sourcePdt.getPkey());
                actProduct.setProductType(Pdt.OProductType.GROUP.getLine().getKey());
                actProduct.setUpdateTime(Env.getSystemTime());
                actProduct.setCurPrice(view.getAmt());
                actProduct.stSupplier(SellerAction.getSupplier());
                actProduct.setMinOq(view.getMinOq());
                PropertyUtils.copyPropertiesWithout(actProduct, sourcePdt, PdtProduct.T.CATEGORY_DIY, PdtProduct.T.SOURCE_PRODUCT, PdtProduct.T.PRODUCT_TYPE, PdtProduct.T.UPDATE_TIME, PdtProduct.T.CUR_PRICE, PdtProduct.T.SUPPLIER, PdtProduct.T.MIN_OQ);
                actProduct.ins();
                List<PdtSpec> specList = new ArrayList<PdtSpec>();
                List<PdtSpec> sourceSpecList = BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + sourcePdt.getPkey(), false);
                for (PdtSpec spec : sourceSpecList) {
                    PdtSpec newSpec = new PdtSpec();
                    newSpec.setPrice(view.getAmt());
                    PropertyUtils.copyPropertiesWithout(newSpec, spec, PdtSpec.T.PRODUCT, PdtSpec.T.PRICE);
                    specList.add(newSpec);
                }
                Idu.insLine(actProduct, specList, PdtSpec.T.PRODUCT.getFld());
                line.setProduct(actProduct.getPkey());
                line.setCount((long) view.getQty());
                line.setBoughtCount((long) 0);
                line.setState(Prm.OSend.NOSEND.getLine().getKey());
                groupLine.add(line);
            }
        }

        public void valid() {
            if (getB().getStartTime().getTime() <= Env.getSystemTime().getTime()) {
                throw LOG.err(Prm.ErrMsgs.TIMEWRONG, PrmGroupPurchase.T.START_TIME.getFld().getName());
            }
            List<PrmGroupPurchase> groupPurchaseList = BeanBase.list(PrmGroupPurchase.class, PrmGroupPurchase.T.CREATED_BY.getFld().getCodeSqlField() + " = ? AND " + PrmGroupPurchase.T.STATUS.getFld().getCodeSqlField() + " <> ? ", false, getCreateBy(), Prm.OStatus.DELETE.getLine().getKey());
            for (PrmGroupPurchase act : groupPurchaseList) {
                boolean startFlag = belongCalendar(getB().getStartTime(), act.getStartTime(), act.getEndTime());
                boolean endFlag = belongCalendar(getB().getEndTime(), act.getStartTime(), act.getEndTime());
                boolean containFlag = getB().getStartTime().getTime() <= act.getStartTime().getTime() && getB().getEndTime().getTime() >= act.getEndTime().getTime();
                if (startFlag || endFlag || containFlag) {
                    throw LOG.err(Prm.ErrMsgs.EXISTS);
                }
            }
            toValid(getB());
        }

        public void run() {
            getB().ins();
            insLine(getB(), getGroupLine(), PrmGroupPurchaseLine.T.MAIN.getFld());
        }

    }

    private static void toValidLines(List<GroupPdtView> lineList) {
        for (GroupPdtView line : lineList) {
            if (line.getAmt().compareTo(BigDecimal.ZERO) <= 0) {
                throw LOG.err("inputAmt", "请输入正确的金额");
            }
            if (line.getQty() == null || line.getQty() < 0) {
                throw LOG.err("inputQty", "请输入正确的起发量");
            }
            if (line.getMinOq() == null || line.getMinOq() < 0) {
                throw LOG.err("inputMinOq", "请输入正确的最小购买量");
            }
        }
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    private static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用于修改联合采购活动
     *
     * @author liyichao
     */
    public static class Upd extends IduOther<Upd, PrmGroupPurchase> {
        private List<GroupPdtView> lines;

        public List<GroupPdtView> getLines() {
            return lines;
        }

        public void setLines(List<GroupPdtView> lines) {
            this.lines = lines;
        }

        public void before() {
            toValidLines(getLines());
            PrmGroupPurchase model = loadThisBeanAndLock();
            getB().setUpdatedTime(Env.getSystemTime());

            if (toPre(getB().getStartTime(), getB().getPreTime()) && model.getStatus().equals(Prm.OStatus.NOTSTART.getLine().getKey())) {
                getB().setStatus(Prm.OStatus.SOONSTART.getLine().getKey());
            } else {
                getB().setStatus(Prm.OStatus.SOONSTART.getLine().getKey());
            }
            PropertyUtils.copyPropertiesWithout(model, getB(), PrmGroupPurchase.T.CREATED_BY, PrmGroupPurchase.T.CREATED_TIME);
            setB(model);
        }

        public void valid() {
            super.valid();
            toValid(getB());
        }

        public void run() {
            List<PrmGroupPurchaseLine> addGroupLine = new ArrayList<PrmGroupPurchaseLine>();
            List<PrmGroupPurchaseLine> updGroupLine = new ArrayList<PrmGroupPurchaseLine>();
            List<PrmGroupPurchaseLine> delGroupLine = new ArrayList<PrmGroupPurchaseLine>();
            //先获取相应联合采购中所有的PrmGroupPurchaseLine
            List<PrmGroupPurchaseLine> existsLineList = BeanBase.list(PrmGroupPurchaseLine.class, PrmGroupPurchaseLine.T.MAIN.getFld().getCodeSqlField() + " = ? ", false, getB().getPkey());
            List<Integer> frontPkey = new ArrayList<Integer>();
            List<Integer> backPkey = new ArrayList<Integer>();
            for (GroupPdtView line : getLines()) {
                if (line.getGroupLinePkey() == null) {
                    PrmGroupPurchaseLine addLine = new PrmGroupPurchaseLine();
                    PdtProduct sourcePdt = BeanBase.load(PdtProduct.class, line.getProduct());
                    PdtProduct actProduct = new PdtProduct();
                    actProduct.setSourceProduct(sourcePdt.getPkey());
                    actProduct.setProductType(Pdt.OProductType.GROUP.getLine().getKey());
                    actProduct.setUpdateTime(Env.getSystemTime());
                    actProduct.setCurPrice(line.getAmt());
                    actProduct.stSupplier(SellerAction.getSupplier());
                    actProduct.setMinOq(line.getMinOq());
                    PropertyUtils.copyPropertiesWithout(actProduct, sourcePdt, PdtProduct.T.CATEGORY_DIY, PdtProduct.T.MIN_OQ, PdtProduct.T.SOURCE_PRODUCT, PdtProduct.T.PRODUCT_TYPE, PdtProduct.T.UPDATE_TIME, PdtProduct.T.CUR_PRICE, PdtProduct.T.SUPPLIER);
                    actProduct.ins();
                    List<PdtSpec> specList = new ArrayList<PdtSpec>();
                    List<PdtSpec> sourceSpecList = BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + sourcePdt.getPkey(), false);
                    for (PdtSpec spec : sourceSpecList) {
                        PdtSpec newSpec = new PdtSpec();
                        newSpec.setPrice(line.getAmt());
                        PropertyUtils.copyPropertiesWithout(newSpec, spec, PdtSpec.T.PRODUCT, PdtSpec.T.PRICE);
                        specList.add(newSpec);
                    }
                    Idu.insLine(actProduct, specList, PdtSpec.T.PRODUCT.getFld());
                    addLine.setProduct(actProduct.getPkey());
                    addLine.setCount((long) line.getQty());
                    addLine.setBoughtCount((long) 0);
                    addLine.setState(Prm.OSend.NOSEND.getLine().getKey());
                    addGroupLine.add(addLine);
                } else {
                    frontPkey.add(line.getGroupLinePkey());
                    PrmGroupPurchaseLine model = BeanBase.load(PrmGroupPurchaseLine.class, line.getGroupLinePkey());
                    PdtProduct product = model.gtProduct();
                    product.setCurPrice(line.getAmt());
                    product.setMinOq(line.getMinOq());
                    List<PdtSpec> specList = new ArrayList<PdtSpec>();
                    List<PdtSpec> existsSpecList = BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + product.getPkey(), false);
                    for (PdtSpec spec : existsSpecList) {
                        spec.setPrice(line.getAmt());
                        specList.add(spec);
                    }
                    product.upd();
                    Idu.updLine(product, specList, PdtSpec.T.PRODUCT.getFld());
                    model.setCount((long) line.getQty());
                    model.gtProduct().setWsPrice(line.getAmt());
                    model.gtProduct().upd();
                    updGroupLine.add(model);
                }
            }
            for (PrmGroupPurchaseLine p : existsLineList) {
                backPkey.add(p.getPkey());
            }
            Iterator<Integer> backIt = backPkey.iterator();
            while (backIt.hasNext()) {
                Integer bPkey = backIt.next();
                for (int i = 0; i < frontPkey.size(); i++) {
                    if (bPkey.equals(frontPkey.get(i))) {
                        backIt.remove();
                    }
                }
            }
            for (int i = 0; i < backPkey.size(); i++) {
                PrmGroupPurchaseLine delLine = BeanBase.get(PrmGroupPurchaseLine.class, backPkey.get(i));
                Idu.delLine(BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + delLine.getProduct(), false));
                delLine.gtProduct().del();
                delGroupLine.add(delLine);
            }
            getB().upd();
            delLine(delGroupLine);
            Idu.updLine(getB(), updGroupLine, PrmGroupPurchaseLine.T.MAIN.getFld());
            Idu.insLine(getB(), addGroupLine, PrmGroupPurchaseLine.T.MAIN.getFld());
        }

    }

    /**
     * 用于查询联合采购活动,并根据时间修改活动状态
     *
     * @author liyichao
     */
    public static class GroupList extends IduOther<GroupList, PrmGroupPurchase> {
        private List<PrmGroupPurchase> groupList;
        private Integer sellerPkey;
        private String state;
        private String title;
        private Integer pageNumber;
        private Integer falg = 0;

        public Integer getFalg() {
            return falg;
        }

        public void setFalg(Integer falg) {
            this.falg = falg;
        }

        public Integer getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(Integer pageNumber) {
            this.pageNumber = pageNumber;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Integer getSellerPkey() {
            return sellerPkey;
        }

        public void setSellerPkey(Integer sellerPkey) {
            this.sellerPkey = sellerPkey;
        }

        public List<PrmGroupPurchase> getGroupList() {
            return groupList;
        }

        public void setGroupList(List<PrmGroupPurchase> groupList) {
            this.groupList = groupList;
        }

        public void after() {
            Integer idx = null;
            if (pageNumber == null || pageNumber.intValue() == 1 || pageNumber.intValue() == 0) {
                idx = 0;
            } else {
                idx = (pageNumber - 1) * 10 - 1;
            }
            String where = " 1=1 ";
            if (sellerPkey != null) {
                where += " AND " + PrmGroupPurchase.T.CREATED_BY.getFld().getCodeSqlField() + " = " + sellerPkey + " AND " + PrmGroupPurchase.T.STATUS.getFld().getCodeSqlField() + " <> " + Prm.OStatus.DELETE.getLine().getKey();
            }
            if (getFalg() != 1) {
                where += " AND " + PrmGroupPurchase.T.STATUS.getFld().getCodeSqlField() + " <> " + Prm.OStatus.NOTSTART.getLine().getKey();
            }
            if (state != null && state.trim() != "") {
                where += " AND " + state;
            }
            if (title != null && title.trim() != "") {
                where += " AND " + PrmGroupPurchase.T.TITLE.getFld().getCodeSqlField() + " LIKE '%" + title + "%' ";
            }
            where += " ORDER BY start_time DESC";
            List<PrmGroupPurchase> allGroupData = BeanBase.list(PrmGroupPurchase.class, PrmGroupPurchase.T.STATUS.getFld().getCodeSqlField() + " <> ? ", false, Prm.OStatus.DELETE.getLine().getKey());
            for (int i = 0; i < allGroupData.size(); i++) {
                PrmGroupPurchase group = allGroupData.get(i);
                Date startDate = group.getStartTime();
                long preTime = group.getPreTime();
                Date endDate = group.getEndTime();
                byte state1 = judgeState(startDate, endDate, preTime);
                switch (state1) {
                    case 0:
                        group.setStatus(state1);
                        break;
                    case 1:
                        group.setStatus(state1);
                        break;
                    case 2:
                        group.setStatus(state1);
                        break;
                    case 3:
                        group.setStatus(state1);
                        break;
                    case 4:
                        group.setStatus(state1);
                        if (Env.getSystemTime().getTime() - group.getEndTime().getTime() >= 15 * 24 * 60 * 60 * 1000) {
                            group.setStatus(Prm.OStatus.DELETE.getLine().getKey());
                            List<PrmGroupPurchaseLine> lineList = Idu.getLines(PrmGroupPurchaseLine.T.MAIN, group.getPkey());
                            for (PrmGroupPurchaseLine line : lineList) {
                                PdtProduct pdt = line.gtProduct();
                                pdt.setState(Pdt.OState.OFF.getLine().getKey());
                                pdt.upd();
                            }
                        }
                        break;
                    default:
                        break;
                }
                group.upd();
            }
            groupList = BeanBase.list(PrmGroupPurchase.class, false, where, idx, 10);
        }

        /**
         * 判断活动状态
         *
         * @param startDate
         * @param endDate
         * @param preTime
         * @return
         */
        public static byte judgeState(Date startDate, Date endDate, long preTime) {
            long preTimeStamp = preTime * 24 * 60 * 60 * 1000;
            long startTimeStamp = startDate.getTime();
            long endTimeStamp = endDate.getTime();
            long nowTimeStamp = System.currentTimeMillis();
            if (startTimeStamp - preTimeStamp > nowTimeStamp) {
                return 0;
            } else if (startTimeStamp - preTimeStamp <= nowTimeStamp && startTimeStamp > nowTimeStamp) {
                return 1;
            } else if (startTimeStamp <= nowTimeStamp && endTimeStamp >= nowTimeStamp) {
                return 2;
            } else if (endTimeStamp - preTimeStamp < nowTimeStamp && endTimeStamp >= nowTimeStamp) {
                return 3;
            } else if (endTimeStamp < nowTimeStamp) {
                return 4;
            } else {
                return -1;
            }
        }
    }

    /**
     * 用于删除联合采购活动
     *
     * @author liyichao
     */
    public static class Del extends IduDel<Del, PrmGroupPurchase> {
        @Override
        public void before() {
            List<PrmGroupPurchaseLine> lineList = getLines(PrmGroupPurchaseLine.T.MAIN, getB().getPkey());
            for (PrmGroupPurchaseLine line : lineList) {
                List<PdtSpec> pdtList = BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + line.getProduct(), false);
                for (PdtSpec spec : pdtList) {
                    List<UsrCart> cartList = BeanBase.list(UsrCart.class, UsrCart.T.SPEC.getFld().getCodeSqlField() + " = ? ", false, spec.getPkey());
                    Idu.delLine(cartList);
                }
                List<PdtComment> commentList = BeanBase.list(PdtComment.class, PdtComment.T.PRODUCT.getFld().getCodeSqlField() + " = ? ", false, line.getProduct());
                List<UsrConsult> consultList = BeanBase.list(UsrConsult.class, UsrConsult.T.PRODUCT.getFld().getCodeSqlField() + " = ? ", false, line.getProduct());
                List<UsrFavorites> favoriteList = BeanBase.list(UsrFavorites.class, UsrFavorites.T.PRODUCT.getFld().getCodeSqlField() + " = ? ", false, line.getProduct());
                Idu.delLine(favoriteList);
                Idu.delLine(consultList);
                Idu.delLine(commentList);
                Idu.delLine(pdtList);
                line.gtProduct().del();

            }
            delLine(lineList);
        }
    }

    /**
     * 用于汇总各采购商在同一活动下的订单发送给相应供应商
     * 没有使用
     *
     * @author liyichao
     */
    public static class Send extends IduOther<Send, PrmGroupPurchase> {
        Info info;

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        /**
         * union -> 选中的活动
         * prmOrderList -> 对应活动下采购商的订单列表
         * staOrder -> 结构为<规格Pkey , 订单详情>的map
         * supOdrMap -> 结构为<供应商Pkey , 订单详情集合>的map
         * allSupProMap -> 结构为<供应商Pkey , 活动产品的集合>的map
         */
        @Override
        public void before() {
            //super.before();
            //活动涉及几家供应商的产品则生成几个大订单
            //so需要知道PrmUnion的子明细产品涉及几家供应商
            //然后按供应商的家数去生成订单
            //1.获得PrmUnion对象
            PrmGroupPurchase union = loadAndLock(getB().getPkey());//Main 采购商pkey-即联合采购活动创建人 PrmUnion下的createdBy
            //采购商订单处理
            //2.获得对应活动下的采购商订单列表
            List<PrmGroupOrder> prmOrderList = BeanBase.list(PrmGroupOrder.class, PrmGroupOrder.T.ACTIVITY.getFld().getCodeSqlField() + " in( ? )", false, union.getPkey());
            if (prmOrderList.size() <= 0) {
                throw LOG.err(Prm.ErrMsgs.EMPTYERR);
            }
            Map<Integer, StatisticsView> staOrder = new HashMap<Integer, StatisticsView>();
            //3.将各个采购商买的相同产品的数量进行相加
            for (PrmGroupOrder purOrder : prmOrderList) {
                List<PrmGroupOrderLine> purOrderLineList = BeanBase.list(PrmGroupOrderLine.class, PrmGroupOrderLine.T.GROUP_ORDER.getFld().getCodeSqlField() + " in( ? )", false, purOrder.getPkey());
                for (PrmGroupOrderLine singleSpecLine : purOrderLineList) {
                    Integer specPkey = singleSpecLine.getSpec();
                    if (staOrder.get(specPkey) == null) {
                        StatisticsView sta = new StatisticsView();
                        sta.setPkey(specPkey);
                        sta.setCount(singleSpecLine.getCount());
                        sta.setPrice(singleSpecLine.getAmt());
                        staOrder.put(specPkey, sta);
                    } else {
                        StatisticsView sta1 = staOrder.get(specPkey);
                        StatisticsView sta2 = new StatisticsView();
                        sta2.setPkey(specPkey);
                        sta2.setCount(singleSpecLine.getCount());
                        sta2.setPrice(singleSpecLine.getAmt());
                        staOrder.put(specPkey, getSta(sta1, sta2));
                    }
                }
            }
            //4.将上述得到的Map对象按照supplier:List存放到Map中,
            Map<Integer, List<StatisticsView>> supOdrMap = new HashMap<Integer, List<StatisticsView>>();
            List<StatisticsView> staList = null;
            for (Integer specKey : staOrder.keySet()) {
                PdtSpec spec = BeanBase.get(PdtSpec.class, specKey);
                Integer supKey = spec.gtProduct().getSupplier();

                if (supOdrMap.get(supKey) == null) {
                    staList = new ArrayList<StatisticsView>();
                    staList.add(staOrder.get(specKey));
                    supOdrMap.put(supKey, staList);
                } else {
                    supOdrMap.get(supKey).add(staOrder.get(specKey));
                }
            }
            //5.根据PrmUnion的Pkey去查找PrmUnionLine
            List<PrmGroupPurchaseLine> prmUnionLineList = BeanBase.list(PrmGroupPurchaseLine.class, PrmGroupPurchaseLine.T.MAIN.getFld().getCodeSqlField() + " in( ? )", false, union.getPkey());
            //6.获取prmUnionLineList中涉及到的商家
            //将活动中每个供应商的PrmUnionLine通过Map放到对应供应商的PrmUnionLine集合中
            Map<Integer, List<PrmGroupPurchaseLine>> allSupProMap = new HashMap<Integer, List<PrmGroupPurchaseLine>>();
            for (PrmGroupPurchaseLine prmUnionLine : prmUnionLineList) {
                Integer supplierPkey = prmUnionLine.gtProduct().getSupplier();
                if (allSupProMap.get(supplierPkey) == null) {
                    List<PrmGroupPurchaseLine> evrPULineList = new ArrayList<PrmGroupPurchaseLine>();
                    evrPULineList.add(prmUnionLine);
                    allSupProMap.put(supplierPkey, evrPULineList);
                } else {
                    allSupProMap.get(supplierPkey).add(prmUnionLine);
                }
            }
            //7.遍历上述Map的键长度
            OdrOrder order = null;
            for (Integer key : allSupProMap.keySet()) {
                order = new OdrOrder();
                //收货信息填写
                order.setAddress(info.getAddress());
                order.setPhone(info.getPhone());
                order.setPostalcode(info.getCode());
                order.setName(info.getName());
                //订单详情内容填写
                order.setTime(Env.getSystemTime());//OdrTime 下单时间 -即send方法执行时间
                order.setState((byte) 0);//OdrState 订单类型 -此处设置为0-联合采购类型
                order.setType((byte) 0);//OdrType 订单状态 -此处设置为0 -未发货状态
                order.setFreightPrice(BigDecimal.ZERO);//FtrightPrice 运费 -此处设置为BigDecimal.ZERO
                order.setInsurancePrice(BigDecimal.ZERO);//SafePrice 保险费 -此处设置为BigDecimal.ZERO
                //order.setPayType((byte)0);//PayType 支付类型 -此处设置为0 -TT支付
                //order.setSupplier();
                //获取每个供应商的Sta集合
                List<StatisticsView> evrSupStaList = supOdrMap.get(key);
                List<OdrOrderLine> orderLineList = new ArrayList<OdrOrderLine>();
                OdrOrderLine orderLine = null;
                for (StatisticsView st : evrSupStaList) {
                    orderLine = new OdrOrderLine();
                    orderLine.setSpec(st.getPkey());
                    orderLine.setQty(st.getCount());
                    orderLine.setSubtotal(st.getPrice());
                    orderLineList.add(orderLine);
                }
                OdrOrderDAO.Ins ins = new OdrOrderDAO.Ins();
                ins.setB(order);
                ins.setLines(orderLineList);
                ins.commit();
            }
        }
    }

    public static class LogicDelete extends IduOther<LogicDelete, PrmGroupPurchase> {
        public void before() {
            getB().setStatus(Prm.OStatus.DELETE.getLine().getKey());
            List<PrmGroupPurchaseLine> lineList = BeanBase.list(PrmGroupPurchaseLine.class, PrmGroupPurchaseLine.T.MAIN.getFld().getCodeSqlField() + " = ? ", false, getB().getPkey());
            for (PrmGroupPurchaseLine line : lineList) {
                line.gtProduct().setState(Pdt.OState.DELETE.getLine().getKey());
                line.gtProduct().upd();
            }
        }

        public void run() {
            getB().upd();
        }
    }

    /**
     * 获取所有联合采购活动
     *
     * @author liyichao
     */
    public static class GetAll extends IduOther<GetAll, PrmGroupPurchase> {
        private List<PrmGroupPurchase> unionList;

        public List<PrmGroupPurchase> getUnionList() {
            return unionList;
        }

        public void setUnionList(List<PrmGroupPurchase> unionList) {
            this.unionList = unionList;
        }

        @Override
        public void before() {
            String state = Prm.OStatus.SOONSTART.getLine().getKey() + " , " + Prm.OStatus.HAVEINHAND.getLine().getKey() + " , " + Prm.OStatus.SOONEND.getLine().getKey() + " , " + Prm.OStatus.END.getLine().getKey();
            unionList = BeanBase.list(PrmGroupPurchase.class, PrmGroupPurchase.T.CREATED_BY.getFld().getCodeSqlField() + " = ? AND " + PrmGroupPurchase.T.STATUS.getFld().getCodeSqlField() + " in ( ? ) ", false, getB().getPkey(), state);
        }
    }

    /**
     * 用于累加Statistics对象的数量及价格
     *
     * @param sta1 已经设置的Statistics对象
     * @param sta2 需要二次设置的Statistics对象
     * @return
     */
    private static StatisticsView getSta(StatisticsView sta1, StatisticsView sta2) {
        StatisticsView sta = new StatisticsView();
        sta.setPkey(sta1.getPkey());
        sta.setCount(sta1.getCount() + sta2.getCount());
        sta.setPrice(sta1.getPrice().add(sta2.getPrice()));
        return sta;
    }

    //判断活动开始时间是否在提前预告的范围内
    public static boolean toPre(Date startDate, long l) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, (int) (-l));
        return cal.getTimeInMillis() <= System.currentTimeMillis();
    }


    /**
     * 验证Bean中的数值
     *
     * @param bean
     */
    public static void toValid(PrmGroupPurchase bean) {
        ValidNumber vn = new ValidNumber(bean);
        vn.validDate1BeforeDate2(PrmGroupPurchase.T.START_TIME, PrmGroupPurchase.T.END_TIME);
    }

    public static void copyDetail(Integer supplier) {

        SQL sql = new SQL() {{
            SELECT(PdtProduct.class)
                    .FROM(PdtProduct.class)
                    .WHERE(PdtProduct.T.SUPPLIER, "=?", supplier)
                    .WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GROUP);
        }};
        List<PdtProduct> prodList = Query.sql(sql).queryList(PdtProduct.class);
        if (prodList.size() == 0) {
            throw LOG.err("noData", "未发布联合采购商品");
        }
        for (PdtProduct pdt : prodList) {
            PdtProduct source = pdt.gtSourceProduct();
            if (source == null) {
                throw LOG.err("dataException", "数据异常");
            }
            pdt.setBriefDescription(source.getBriefDescription());
            pdt.setDescription(source.getDescription());
            pdt.upd();
        }
    }

    /**
     * ————————————————分割线(新平台)—————————————————————————
     */
    public static Page getPrmGroupPurchases(Integer start, Integer limit, String title, Integer status, Integer supplier) {
        if (start == null) {
            start = 0;
        }
        if (limit == null) {
            limit = 15;
        }
        SQL sql = new SQL() {{
            SELECT(PrmGroupPurchase.class).FROM(PrmGroupPurchase.class);
            if (title != null) {
                WHERE(PrmGroupPurchase.T.TITLE, "like '%" + title + "%'");
            }
            if (status != null) {
                WHERE(PrmGroupPurchase.T.STATUS, "=?", status);
            }
            if (supplier != null) {
                WHERE(PrmGroupPurchase.T.CREATED_BY, "=?", supplier);
            }
        }};
        Integer count = Query.sql(sql).queryCount();
        List<PrmGroupPurchaseView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(o -> new PrmGroupPurchaseView() {{
            setId((Integer) o.get(PrmGroupPurchase.T.PKEY.getFld().getCodeSqlField()));
            setTitle((String) o.get(PrmGroupPurchase.T.TITLE.getFld().getCodeSqlField()));
            setStartTime((Date) o.get(PrmGroupPurchase.T.START_TIME.getFld().getCodeSqlField()));
            setEndTime((Date) o.get(PrmGroupPurchase.T.END_TIME.getFld().getCodeSqlField()));
            setStatus(Integer.valueOf(String.valueOf(o.get(PrmGroupPurchase.T.STATUS.getFld().getCodeSqlField()))));
            setPreTime(Integer.valueOf(String.valueOf(o.get(PrmGroupPurchase.T.PRE_TIME.getFld().getCodeSqlField()))));
            setSupplier(BeanBase.load(UsrSupplier.class, (Integer) o.get(PrmGroupPurchase.T.CREATED_BY.getFld().getCodeSqlField())).getName());
            setCreatedTime((Date) o.get(PrmGroupPurchase.T.CREATED_TIME.getFld().getCodeSqlField()));
            setUpdatedTime((Date) o.get(PrmGroupPurchase.T.UPDATED_TIME.getFld().getCodeSqlField()));
        }}).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    public static StatusAndTypeView getStatusAndType() {
        List<StatusView> statusViews = new ArrayList<>();
        for (Prm.OStatus value : Prm.OStatus.values()) {
            StatusView view = new StatusView();
            view.setId(value.getLine().getKey());
            view.setStatus(value.getLine().getName());
            statusViews.add(view);

        }
        List<TypeView> typeViews = new ArrayList<>();
        for (Prm.OPreTime value : Prm.OPreTime.values()) {
            TypeView view = new TypeView();
            view.setId(value.getLine().getKey());
            view.setType(value.getLine().getName());
            typeViews.add(view);
        }
        SQL sql = new SQL() {
            {
                SELECT(PrmGroupPurchase.T.CREATED_BY)
                        .FROM(PrmGroupPurchase.class)
                        .GROUP_BY(PrmGroupPurchase.T.CREATED_BY);
            }
        };
        List<SupplierView> suppliers = Query.sql(sql).queryMaps().stream().map(o -> new SupplierView() {{
            setId((Integer) o.get(PrmGroupPurchase.T.CREATED_BY.getFld().getCodeSqlField()));
            setName(BeanBase.load(UsrSupplier.class, getId()).getName());
        }}).collect(Collectors.toList());
        StatusAndTypeView view = new StatusAndTypeView();
        view.setStatus(statusViews);
        view.setTypes(typeViews);
        view.setSupplier(suppliers);
        return view;
    }

    /**—————————————————分割线(新平台)END—————————————————————————*/
}
