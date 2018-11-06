package irille.shop.odr;

import irille.pub.bean.Query;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;
import irille.shop.odr.Odr.OdrState;
import irille.shop.odr.OdrHistory.T;

import java.util.List;

public class OdrHistoryDAO {
    
    /**
     * 列表订单历史
     * @param order 订单id
     * @author yingjianhua
     */
    public static List<OdrHistory> listByOrder(Long order) {
    	return Query.SELECT(OdrHistory.class).WHERE(T.ODRORDER, "=?", order).ORDER_BY(T.TIME, "asc").queryList();
    }
    
    public static void add(Long orderId, String descrip, String operator, OdrState status) {
    	OdrHistory bean = new OdrHistory().init();
    	bean.setOdrorder(orderId);
    	bean.setOperator(operator);
    	bean.setDescrip(descrip);
    	bean.stState(status);
    	bean.ins();
    }

    public static class Ins extends IduIns<Ins, OdrHistory> {
        private String hisdescibe;
        private Byte histype;
        private String histmain;
        private String hisodrname;
    	
    	public String getHisodrname() {
    		return hisodrname;
    	}
    	public void setHisodrname(String hisodrname) {
    		this.hisodrname = hisodrname;
    	}
        public String getHisdescibe() {
            return hisdescibe;
        }

        public void setHisdescibe(String hisdescibe) {
            this.hisdescibe = hisdescibe;
        }

        public Byte getHistype() {
            return histype;
        }

        public void setHistype(Byte histype) {
            this.histype = histype;
        }

        public String getHistmain() {
            return histmain;
        }

        public void setHistmain(String histmain) {
            this.histmain = histmain;
        }

        @Override
        public void before() {
        	  OdrOrder dbbeans = OdrOrder.loadUniqueOrderNum(true,histmain);
        	getB().setDescrip(hisdescibe);
            getB().setState(histype);
            getB().setOperator(hisodrname);
            getB().setOdrorder(dbbeans.getPkey());
            getB().setTime(Env.getTranBeginTime());
            super.before();
        }

    }

}
