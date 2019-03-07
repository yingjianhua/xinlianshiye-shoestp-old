package irille.Dao;

import irille.platform.plt.View.DeliveryAreaView;
import irille.platform.plt.View.FreightManagementView;
import irille.platform.plt.View.ShippingSettingView;
import irille.pub.Log;
import irille.pub.PubInfs;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduInsLines;
import irille.pub.svr.Env;
import irille.shop.plt.*;
import irille.view.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public class PltFreightDao {
    private static final Log LOG = new Log(PltFreightDAO.class);
    public enum Msgs implements PubInfs.IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
        priceErr("【{0}】不能低于或等于【{1}】"),
        priceMinErr("【{0}】不能低于0"),
        ;
        private String _msg;
        private Msgs(String msg) { _msg=msg; }
        public String getMsg() {return _msg; }
    } //@formatter:on
    // 快递公司列表
    public Page getShippingList(String selectcompany, Integer start, Integer limit) {
        SQL sql = new SQL();
        sql.SELECT(PltFreight.T.PKEY);
        sql.SELECT(PltFreight.T.COMPANY);
        sql.SELECT(PltFreight.T.LOGO);
        sql.SELECT(PltFreight.T.ENABLED);
        sql.SELECT(PltFreight.T.TYPE);
        sql.FROM(PltFreight.class);
        if (selectcompany != null && !"".equals(selectcompany)) {
            sql.WHERE(PltFreight.T.COMPANY," like '%"+selectcompany+"%'");
        }
        Integer count = Query.sql(sql).queryCount();
        List<ShippingSettingView> list = Query.sql(sql).queryMaps().stream().map(o -> {
            ShippingSettingView view = new ShippingSettingView();
            view.setPkey((Integer) o.get(PltFreight.T.PKEY.getFld().getCodeSqlField()));
            view.setCompany(String.valueOf(o.get(PltFreight.T.COMPANY.getFld().getCodeSqlField())));
            view.setLogo(String.valueOf(o.get(PltFreight.T.LOGO.getFld().getCodeSqlField())));
            view.setEnable(Byte.valueOf(String.valueOf(o.get(PltFreight.T.ENABLED.getFld().getCodeSqlField()))));
            view.setType(Byte.valueOf(String.valueOf(o.get(PltFreight.T.TYPE.getFld().getCodeSqlField()))));
            return view;
        }).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }
    //区间列表
    public List<DeliveryAreaView> getDeliveryAreaList(Integer id) {
        SQL sql = new SQL();
        sql.SELECT(PltFreightLine.T.PKEY);
        sql.SELECT(PltFreightLine.T.SECTION);
        sql.FROM(PltFreightLine.class);
        sql.LEFT_JOIN(PltFreight.class, PltFreight.T.PKEY, PltFreightLine.T.MAIN);
        sql.WHERE(PltFreightLine.T.MAIN, "=?", id);
        List<DeliveryAreaView> list = Query.sql(sql).queryMaps().stream().map(o -> {
            DeliveryAreaView view = new DeliveryAreaView();
            view.setPkey((Integer)o.get( PltFreightLine.T.PKEY.getFld().getCodeSqlField()));
            view.setInterval(String.valueOf(o.get(PltFreightLine.T.SECTION.getFld().getCodeSqlField())));
            return view;
        }).collect(Collectors.toList());
        return list;
    }
    //插入快递公司
    public void insShipping(FreightManagementView view) {
        PltFreight pltFreight = new PltFreight();
        pltFreight.setCompany(view.getCompany());
        pltFreight.setLogo(view.getLogo());
        pltFreight.setEnabled(view.getEnabled());
        pltFreight.setExpressUrl(view.getUrl());
        pltFreight.setSort(view.getSort());
        pltFreight.setUseInterface(view.getUsingInterface());
        pltFreight.setWeightMin(view.getMinWeight());
        pltFreight.setWeightMax(view.getMaxWeight());
        pltFreight.setType(view.getType());
        pltFreight.setCreatedBy(view.getCreatedby());
        pltFreight.setUserSys(view.getCreatedby());
        pltFreight.setCreatedTime(Env.getTranBeginTime());
        if(pltFreight.getWeightMin().compareTo(pltFreight.getWeightMax())>=0)
            throw LOG.err(Msgs.priceErr, PltFreight.T.WEIGHT_MAX.getFld().getName(), PltFreight.T.WEIGHT_MIN.getFld().getName());
        if(pltFreight.getWeightMin()<0)
            throw LOG.err(Msgs.priceMinErr, PltFreight.T.WEIGHT_MIN.getFld().getName());
        if(pltFreight.getWeightMax()<0)
            throw LOG.err(Msgs.priceMinErr,PltFreight.T.WEIGHT_MAX.getFld().getName());
        PltFreight freight = PltFreight.chkUniqueCompany(false, pltFreight.getCompany());
        if( freight != null && !freight.getPkey().equals(view.getPkey())) {
            throw LOG.err(Plt.ErrMsgs.uniqueErr,"快递公司:" +pltFreight.getCompany());
        }
        if(pltFreight.getSort()!= null) {
            if(pltFreight.getSort()<0)
                throw LOG.err(Plt.ErrMsgs.lowPriceErr,PltFreight.T.SORT.getFld().getName(),0);
        }
        pltFreight.ins();
    }
    //插入区间
    public void insDeliveryArea(PltFreightLine view) {
        PltFreightLine pltFreightLine = view;
        pltFreightLine.ins();
    }
    //获取快递公司详情
    public FreightManagementView getShipping(Integer id) {
        PltFreight pltFreight = BeanBase.load(PltFreight.class,id);
        FreightManagementView view = new FreightManagementView();
        view.setPkey(pltFreight.getPkey());
        view.setCompany(pltFreight.getCompany());
        view.setEnabled(pltFreight.getEnabled());
        view.setLogo(pltFreight.getLogo());
        view.setUrl(pltFreight.getExpressUrl());
        view.setSort(pltFreight.getSort());
        view.setUsingInterface(pltFreight.getUseInterface());
        view.setMinWeight(pltFreight.getWeightMin());
        view.setMaxWeight(pltFreight.getWeightMax());
        view.setType(pltFreight.getType());
        return view;
    }
    //修改快递公司
    public void updShipping(FreightManagementView view) {
        PltFreight pltFreight = BeanBase.load(PltFreight.class,view.getPkey());
        pltFreight.setCompany(view.getCompany());
        pltFreight.setLogo(view.getLogo());
        pltFreight.setExpressUrl(view.getUrl());
        pltFreight.setSort(view.getSort());
        pltFreight.setUseInterface(view.getUsingInterface());
        pltFreight.setWeightMin(view.getMinWeight());
        pltFreight.setWeightMax(view.getMaxWeight());
        pltFreight.setType(view.getType());
        pltFreight.setCreatedBy(view.getCreatedby());
        pltFreight.setCreatedTime(Env.getTranBeginTime());
        if(pltFreight.getWeightMin().compareTo(pltFreight.getWeightMax())>=0) {
            throw LOG.err(Msgs.priceErr, PltFreight.T.WEIGHT_MAX.getFld().getName(), PltFreight.T.WEIGHT_MIN.getFld().getName());
        }
        if(pltFreight.getWeightMin()<0) {
            throw LOG.err(Msgs.priceMinErr, PltFreight.T.WEIGHT_MIN.getFld().getName());
        }
        if(pltFreight.getWeightMax()<0) {
            throw LOG.err(Msgs.priceMinErr, PltFreight.T.WEIGHT_MAX.getFld().getName());
        }
        PltFreight freight = PltFreight.chkUniqueCompany(false, pltFreight.getCompany());
        if( freight != null && !freight.getPkey().equals(view.getPkey())) {
            throw LOG.err(Plt.ErrMsgs.uniqueErr,"快递公司:" +pltFreight.getCompany());
        }
        if(pltFreight.getSort()!= null) {
            if(pltFreight.getSort()<0) {
                throw LOG.err(Plt.ErrMsgs.lowPriceErr, PltFreight.T.SORT.getFld().getName(),0);
            }
        }
        pltFreight.upd();
    }
    //区间详情
    public DeliveryAreaView getDeliveryArea(Integer id) {
        PltFreightLine pltFreightLine = BeanBase.load(PltFreightLine.class,id);
        DeliveryAreaView deliveryAreaView = new DeliveryAreaView();
        deliveryAreaView.setPkey(pltFreightLine.getPkey());
        deliveryAreaView.setInterval(pltFreightLine.getSection());
        deliveryAreaView.setIntroduction(pltFreightLine.getBrief());
        deliveryAreaView.setFreeShipping(pltFreightLine.getFree());
        deliveryAreaView.setFreeShippingPrice(pltFreightLine.getFreePrice());
        deliveryAreaView.setAdditionalFees(pltFreightLine.getExtraPrice());
        deliveryAreaView.setFirstInterval(pltFreightLine.getWeightSection());
        deliveryAreaView.setContinuedInterval(pltFreightLine.getAggravateSection());
        deliveryAreaView.setFirstPrice(pltFreightLine.getWeightPrice());
        deliveryAreaView.setAggravatingPrice(pltFreightLine.getAggravatePrice());
        return deliveryAreaView;
    }
    //修改区间
    public void updDeliveryArea(PltFreightLine view) {
        PltFreightLine pltFreightLine = BeanBase.load(PltFreightLine.class, view.getPkey());
        pltFreightLine.setSection(view.getSection());
        pltFreightLine.setBrief(view.getBrief());
        pltFreightLine.setFree(view.getFree());
        pltFreightLine.setFreePrice(view.getFreePrice());
        pltFreightLine.setExtraPrice(view.getExtraPrice());
        pltFreightLine.setWeightSection(view.getWeightSection());
        pltFreightLine.setAggravateSection(view.getAggravateSection());
        pltFreightLine.setWeightPrice(view.getWeightPrice());
        pltFreightLine.setAggravatePrice(view.getAggravatePrice());
        System.out.println(pltFreightLine.getWeightPrice());
        if(pltFreightLine.getWeightPrice().compareTo(BigDecimal.ZERO)<0) {
            throw LOG.err(Msgs.priceMinErr, PltFreightLine.T.WEIGHT_PRICE.getFld().getName());
        }
        if(pltFreightLine.getExtraPrice().compareTo(BigDecimal.ZERO)<0) {
            throw LOG.err(Msgs.priceMinErr, PltFreightLine.T.EXTRA_PRICE.getFld().getName());
        }
        if(pltFreightLine.getFreePrice().compareTo(BigDecimal.ZERO)<0) {
            throw LOG.err(Msgs.priceMinErr, PltFreightLine.T.FREE_PRICE.getFld().getName());
        }
        if(pltFreightLine.getAggravatePrice().compareTo(BigDecimal.ZERO)<0) {
            throw LOG.err(Msgs.priceMinErr, PltFreightLine.T.AGGRAVATE_PRICE.getFld().getName());
        }
        if(pltFreightLine.getWeightSection()<0) {
            throw LOG.err(Msgs.priceMinErr, PltFreightLine.T.WEIGHT_SECTION.getFld().getName());
        }
        if(pltFreightLine.getAggravateSection()<0) {
            throw LOG.err(Msgs.priceMinErr, PltFreightLine.T.AGGRAVATE_SECTION.getFld().getName());
        }
        pltFreightLine.upd();
    }
    //删除快递公司
    public void delFreight(Integer pkey,Integer user ){
        PltFreight pltFreight = BeanBase.load(PltFreight.class,pkey);
        pltFreight.del();
        if(BeanBase.list(PltFreight.class, PltFreight.T.USER_SYS.getFld().getCodeSqlField()+" ="+user,false).size()==0){
            throw LOG.err(Plt.ErrMsgs.last,"区间");
        }
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("5.55");
        System.out.println(BigDecimal.ZERO);
        System.out.println(a.compareTo(BigDecimal.ZERO)==1);
    }
}
