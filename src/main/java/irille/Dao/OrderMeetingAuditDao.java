package irille.Dao;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.OrderMeetingAudit;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.shop.plt.PltCountryFreightDAO;

public class OrderMeetingAuditDao {
    private static final Log LOG = new Log(OrderMeetingAuditDao.class);
    /**
     *@Description:  商家申请 参加订购会
     *@date 2018/11/19 20:09
     *@anthor wilson zhang
     */
    public static class insertjoinOdr extends IduIns<insertjoinOdr, OrderMeetingAudit> {
        @Override
        public void before() {
            getB().setCreatedTime(Env.getTranBeginTime());
            getB().setStatus(OrderMeetingAuditStatus.PENDING.getLine().getKey());
            super.before();
        }
    }

    /**
    *@Description:  逻辑删除 参加订购会合作商
    *@date 2018/11/22 11:14
    *@anthor wilson zhang
    */
    public static class deletejoinOdr extends IduUpd<deletejoinOdr,OrderMeetingAudit> {
        @Override
        public void before() {
            OrderMeetingAudit dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(),OrderMeetingAudit.T.STATUS);
            setB(dbBean);
            OrderMeetingProduct omp=new OrderMeetingProduct();
            omp.setPkey(getB().getPkey());
            omp.setSupplierid(getB().getSupplierid());
            OdrMeetingProductDao.deletejoinOdr(omp);
            super.before();
        }
    }
}
