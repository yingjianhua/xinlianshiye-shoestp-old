package irille.Dao.RFQ.impl;

import java.util.List;

import irille.Dao.RFQ.RFQConsultGroupRelationDao;
import irille.Entity.RFQ.RFQConsultGroupRelation;
import irille.pub.bean.Query;

public class RFQConsultGroupRelationDaoImpl implements RFQConsultGroupRelationDao {

	@Override
	public void save(RFQConsultGroupRelation bean) {
		if(bean.getPkey() == null) {
			bean.ins();
		} else {
			bean.upd();
		}
	}

	@Override
	public RFQConsultGroupRelation findByConsult_PkeySupplier_Pkey(Integer consultPkey, Integer supplierPkey) {
		return Query.SELECT(RFQConsultGroupRelation.class).WHERE(RFQConsultGroupRelation.T.CONSULT, "=?", consultPkey).WHERE(RFQConsultGroupRelation.T.SUPPLIER, "=?", supplierPkey).query();
	}

	@Override
	public Integer countByGroup_pkey(Integer groupId) {
		return Query.SELECT(RFQConsultGroupRelation.class).WHERE(RFQConsultGroupRelation.T.CONSULT_GROUP, "=?", groupId).queryCount();
	}

	@Override
	public void deleteByGroup_pkey(Integer groupPkey) {
		findAllByGroup_Pkey(groupPkey).forEach(bean->{
			bean.del();
		});
	}
	
	@Override
	public List<RFQConsultGroupRelation> findAllByGroup_Pkey(Integer groupPkey) {
		return Query.SELECT(RFQConsultGroupRelation.class).WHERE(RFQConsultGroupRelation.T.CONSULT_GROUP, "=?", groupPkey).queryList();
	}

}
