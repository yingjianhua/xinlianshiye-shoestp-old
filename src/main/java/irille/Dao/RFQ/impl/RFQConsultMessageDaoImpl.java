package irille.Dao.RFQ.impl;

import java.util.List;

import irille.Dao.RFQ.RFQConsultMessageDao;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;

public class RFQConsultMessageDaoImpl implements RFQConsultMessageDao {

	@Override
	public List<RFQConsultMessage> findAll(Integer start, Integer limit, Integer consultPkey, Integer supplierPkey) {
		if(start == null)
			start = 0;
		if(limit == null || limit == 0)
			limit = 10;
		BeanQuery<RFQConsultMessage> query = Query.SELECT(RFQConsultMessage.class)
		.LEFT_JOIN(RFQConsultRelation.class, RFQConsultMessage.T.RELATION, RFQConsultRelation.T.PKEY)
		.WHERE(RFQConsultRelation.T.CONSULT, "=?", consultPkey)
		.WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplierPkey)
		.ORDER_BY(RFQConsultMessage.T.SEND_TIME, "desc");
		return query.limit(start, limit).queryList();
	}

	@Override
	public void save(RFQConsultMessage bean) {
		if(bean.getPkey() == null) {
			bean.ins();
		} else {
			bean.upd();
		}
	}

}
