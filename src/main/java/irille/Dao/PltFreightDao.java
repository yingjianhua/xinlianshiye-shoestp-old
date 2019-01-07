package irille.Dao;

import irille.platform.plt.View.DeliveryAreaView;
import irille.platform.plt.View.FreightManagementView;
import irille.platform.plt.View.ShippingSettingView;
import irille.core.sys.Sys;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.plt.PltFreight;
import irille.shop.plt.PltFreightLine;
import irille.view.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public class PltFreightDao {
    public Page getShippingList(Integer start, Integer limit) {
        SQL sql = new SQL();
        sql.SELECT(PltFreight.T.PKEY);
        sql.SELECT(PltFreight.T.COMPANY);
        sql.SELECT(PltFreight.T.LOGO);
        sql.SELECT(PltFreight.T.ENABLED);
        sql.SELECT(PltFreight.T.TYPE);
        sql.FROM(PltFreight.class);
        Integer count = Query.sql(sql).queryCount();
        List<ShippingSettingView> list = Query.sql(sql).queryMaps().stream().map(o -> {
            ShippingSettingView view = new ShippingSettingView();
            view.setPkey(Integer.valueOf(String.valueOf(o.get(PltFreight.T.PKEY.getFld().getCodeSqlField()))));
            view.setCourierCompany(String.valueOf(o.get(PltFreight.T.COMPANY.getFld().getCodeSqlField())));
            view.setLogo(String.valueOf(o.get(PltFreight.T.LOGO.getFld().getCodeSqlField())));
            view.setEnable(Byte.valueOf(String.valueOf(o.get(PltFreight.T.ENABLED.getFld().getCodeSqlField()))));
            view.setWeightSelectionMethod(Byte.valueOf(String.valueOf(o.get(PltFreight.T.TYPE.getFld().getCodeSqlField()))));
            return view;
        }).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    public Page getDeliveryAreaList(Integer id, String name, Integer start, Integer limit) {
        SQL sql = new SQL();
        sql.SELECT(PltFreightLine.T.PKEY);
        sql.SELECT(PltFreightLine.T.SECTION);
        sql.SELECT(PltFreightLine.T.BRIEF);
        sql.SELECT(PltFreightLine.T.FREE);
        sql.SELECT(PltFreightLine.T.FREE_PRICE);
        sql.SELECT(PltFreightLine.T.EXTRA_PRICE);
        sql.SELECT(PltFreightLine.T.WEIGHT_SECTION);
        sql.SELECT(PltFreightLine.T.AGGRAVATE_SECTION);
        sql.SELECT(PltFreightLine.T.WEIGHT_PRICE);
        sql.SELECT(PltFreightLine.T.AGGRAVATE_PRICE);
        sql.FROM(PltFreightLine.class);
        if (name != null) {
            sql.LEFT_JOIN(PltFreight.class, PltFreight.T.PKEY, PltFreightLine.T.MAIN);
            sql.WHERE(PltFreight.T.COMPANY, "like'%" + name + "%'");
        }
        sql.WHERE(PltFreightLine.T.MAIN, "=?", id);

        Integer count = Query.sql(sql).queryCount();
        List<DeliveryAreaView> list = Query.sql(sql).queryMaps().stream().map(o -> {
            DeliveryAreaView view = new DeliveryAreaView();
            view.setPkey(Integer.valueOf(String.valueOf(PltFreightLine.T.PKEY.getFld().getCodeSqlField())));
            view.setInterval(String.valueOf(o.get(PltFreightLine.T.SECTION.getFld().getCodeSqlField())));
            view.setIntroduction(String.valueOf(o.get(PltFreightLine.T.BRIEF.getFld().getCodeSqlField())));
            view.setFreeShipping(Byte.valueOf(String.valueOf(o.get(PltFreightLine.T.FREE.getFld().getCodeSqlField()))));
            view.setFreeShippingPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(o.get(PltFreightLine.T.FREE_PRICE.getFld().getCodeSqlField())))));
            view.setAdditionalFees(BigDecimal.valueOf(Double.valueOf(String.valueOf(o.get(PltFreightLine.T.EXTRA_PRICE.getFld().getCodeSqlField())))));
            view.setFirstInterval(Integer.valueOf(String.valueOf(o.get(PltFreightLine.T.WEIGHT_SECTION.getFld().getCodeSqlField()))));
            view.setContinuedInterval(Integer.valueOf(String.valueOf(o.get(PltFreightLine.T.AGGRAVATE_SECTION.getFld().getCodeSqlField()))));
            view.setFirstPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(o.get(PltFreightLine.T.WEIGHT_PRICE.getFld().getCodeSqlField())))));
            view.setAggravatingPrice(BigDecimal.valueOf(Double.valueOf(String.valueOf(o.get(PltFreightLine.T.AGGRAVATE_PRICE.getFld().getCodeSqlField())))));
            return view;
        }).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    public void insShipping(FreightManagementView view) {
        PltFreight pltFreight = new PltFreight();
        pltFreight.setCompany(view.getCourierCompany());
        pltFreight.setLogo(view.getLogo());
        pltFreight.setEnabled(Sys.OEnabled.TRUE.getLine().getKey());
        pltFreight.setExpressUrl(view.getExpressOrderNumberInquiryAddress());
        pltFreight.setSort(view.getSort());
        pltFreight.setUseInterface(view.getUsingInterface());
        pltFreight.setWeightMin(view.getMinimumWeight());
        pltFreight.setWeightMax(view.getMaximumWeight());
        pltFreight.setType(view.getWeightCalculation());
        pltFreight.ins();
    }

    public void insDeliveryArea(DeliveryAreaView view) {
        PltFreightLine pltFreightLine = new PltFreightLine();
        pltFreightLine.setMain(view.getPkey());
        pltFreightLine.setSection(view.getInterval());
        pltFreightLine.setBrief(view.getIntroduction());
        pltFreightLine.setFree(view.getFreeShipping());
        pltFreightLine.setFreePrice(view.getFreeShippingPrice());
        pltFreightLine.setExtraPrice(view.getAdditionalFees());
        pltFreightLine.setWeightSection(view.getFirstInterval());
        pltFreightLine.setAggravateSection(view.getContinuedInterval());
        pltFreightLine.setWeightPrice(view.getFirstPrice());
        pltFreightLine.setAggravatePrice(view.getAggravatingPrice());
        pltFreightLine.ins();
    }

    public FreightManagementView getShipping(Integer id) {
        PltFreight pltFreight = BeanBase.load(PltFreight.class, PltFreight.T.PKEY, "=?", id);
        FreightManagementView view = new FreightManagementView();
        view.setPkey(pltFreight.getPkey());
        view.setCourierCompany(pltFreight.getCompany());
        view.setLogo(pltFreight.getLogo());
        view.setExpressOrderNumberInquiryAddress(pltFreight.getExpressUrl());
        view.setSort(pltFreight.getSort());
        view.setUsingInterface(pltFreight.getUseInterface());
        view.setMinimumWeight(pltFreight.getWeightMin());
        view.setMaximumWeight(pltFreight.getWeightMax());
        view.setWeightCalculation(pltFreight.getType());
        return view;
    }

    public void updShipping(FreightManagementView view) {
        PltFreight pltFreight = BeanBase.load(PltFreight.class, PltFreight.T.PKEY, "=?", view.getPkey());
        pltFreight.setCompany(view.getCourierCompany());
        pltFreight.setLogo(view.getLogo());
        pltFreight.setExpressUrl(view.getExpressOrderNumberInquiryAddress());
        pltFreight.setSort(view.getSort());
        pltFreight.setUseInterface(view.getUsingInterface());
        pltFreight.setWeightMin(view.getMinimumWeight());
        pltFreight.setWeightMax(view.getMaximumWeight());
        pltFreight.setType(view.getWeightCalculation());
        pltFreight.upd();
    }

    public DeliveryAreaView getDeliveryArea(Integer id) {
        PltFreightLine pltFreightLine = BeanBase.load(PltFreightLine.class, PltFreightLine.T.PKEY, "=?", id);
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
    public void updDeliveryArea(DeliveryAreaView view) {
        PltFreightLine pltFreightLine = BeanBase.load(PltFreightLine.class, PltFreightLine.T.PKEY, "=?", view.getPkey());
        pltFreightLine.setSection(view.getInterval());
        pltFreightLine.setBrief(view.getIntroduction());
        pltFreightLine.setFree(view.getFreeShipping());
        pltFreightLine.setFreePrice(view.getFreeShippingPrice());
        pltFreightLine.setExtraPrice(view.getAdditionalFees());
        pltFreightLine.setWeightSection(view.getFirstInterval());
        pltFreightLine.setAggravateSection(view.getContinuedInterval());
        pltFreightLine.setWeightPrice(view.getFirstPrice());
        pltFreightLine.setAggravatePrice(view.getAggravatingPrice());
        pltFreightLine.upd();
    }
}
