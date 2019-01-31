package irille.Dao.RFQ.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.platform.rfq.view.RFQConsultRelationView;
import irille.platform.rfq.view.SupplierView;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.shop.usr.UsrSupplier;

public class RFQConsultRelationDaoImpl implements RFQConsultRelationDao {

	@Override
	public List<RFQConsultRelationView> findAllViewByConsultId(Integer id) {
		BeanQuery<RFQConsultRelation> query = Query.SELECT(
				RFQConsultRelation.T.PKEY,
				RFQConsultRelation.T.CONSULT,
				RFQConsultRelation.T.SUPPLIER_ID,
				RFQConsultRelation.T.PURCHASE_ID,
				RFQConsultRelation.T.FAVORITE,
				RFQConsultRelation.T.ROW_VERSION
				)
		.SELECT(UsrSupplier.T.PKEY, "supplierPkey")
		.SELECT(UsrSupplier.T.NAME, "supplierName")
		.FROM(RFQConsultRelation.class)
		.LEFT_JOIN(UsrSupplier.class, RFQConsultRelation.T.SUPPLIER_ID, UsrSupplier.T.PKEY)
		.WHERE(RFQConsultRelation.T.CONSULT, "=?", id);
		
		return query.queryMaps().stream().map(map->{
			RFQConsultRelationView view = new RFQConsultRelationView();
			view.setPkey((Integer)map.get(RFQConsultRelation.T.PKEY.getFld().getCodeSqlField()));
			view.setConsult((Integer)map.get(RFQConsultRelation.T.CONSULT.getFld().getCodeSqlField()));
			if(map.containsKey("supplierPkey")) {
				view.setSupplier(new SupplierView() {{
					setPkey((Integer)map.get("supplierPkey"));
					setName((String)map.get("supplierName"));
				}});
			}
			view.setPurchaseId((Integer)map.get(RFQConsultRelation.T.PURCHASE_ID.getFld().getCodeSqlField()));
			view.setFavorite((Byte)map.get(RFQConsultRelation.T.FAVORITE.getFld().getCodeSqlField()));
			view.setRowVersion((Short)map.get(RFQConsultRelation.T.ROW_VERSION.getFld().getCodeSqlField()));
			return view;
		}).collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		new RFQConsultRelationDaoImpl().testDirector();
	}
	
	@Test
	private void testDirector() {
		UsrSupplier.TB.getCode();
		RFQConsultRelation.TB.getCode();
		
		testFindAllViewByConsultId(1);
	}

	private void testFindAllViewByConsultId(Integer id) {
		List<RFQConsultRelationView> list = findAllViewByConsultId(id);
		System.out.println("total: "+list.size());
		list.forEach(view->{
			System.out.println(view);
		});
	}

	@Override
	public List<RFQConsultRelation> findAllByConsult_PkeySupplier_Pkey(String consultPkeys, Integer supplierPkey) {
		return Query.SELECT(RFQConsultRelation.class)
				.WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplierPkey)
				.WHERE(RFQConsultRelation.T.PKEY, "in (" + Stream.of(consultPkeys.split(",")).map(i -> "?").collect(Collectors.joining(",")) + ")", Stream.of(consultPkeys.split(",")).map(Integer::new).toArray(Serializable[]::new))
				.queryList();
	}

	@Override
	public void save(RFQConsultRelation bean) {
		if(bean.getPkey() == null) {
			bean.ins();
		} else {
			bean.upd();
		}
	}

	@Override
	public Integer countByConsult_PkeySupplier_Pkey(Integer consultPkey, Integer supplierPkey) {
		return Query.SELECT(RFQConsultRelation.class).WHERE(RFQConsultRelation.T.CONSULT, "=?", consultPkey).WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplierPkey).queryCount();
	}
}