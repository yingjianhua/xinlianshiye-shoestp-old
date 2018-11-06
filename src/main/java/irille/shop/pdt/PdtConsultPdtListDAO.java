package irille.shop.pdt;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import irille.homeAction.HomeAction;
import irille.pub.Log;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.pdt.PdtConsultPdtList.T;
import irille.view.pdt.ConsultPdtView;
import irille.view.pdt.ConsultProductView;

public class PdtConsultPdtListDAO {
	public static final Log LOG = new Log(PdtConsultPdtListDAO.class);
	
	public static class Ins extends IduIns<Ins,PdtConsultPdtList> {
		@Override
		public void before(){
			getB().setPurchase(HomeAction.getPurchase().getPkey());
			super.before();
		}
	}
	
	/**
	 * 将产品添加到采购商的询盘产品列表中
	 * @param product
	 * @param purchase
	 * @author yingjianhua
	 */
	public static ConsultProductView add(Integer product, Integer purchase) {
		PdtConsultPdtList bean = Query.SELECT(PdtConsultPdtList.class).WHERE(T.PURCHASE, "=?", purchase).WHERE(T.PRODUCT, "=?", product).query();
		if(bean == null) {
			bean = new PdtConsultPdtList().init();
			bean.setProduct(product);
			bean.setPurchase(purchase);
			bean.ins();
		}
		ConsultProductView view = new ConsultProductView();
		view.setId(bean.getPkey());
		return view;
	}
	
//	/**
//	 * @param pkey
//	 * @return
//	 * @author yingjianhua
//	 */
//	public static List<PdtConsultPdtList> listByPurchase(Integer pkey) {
//		return BeanBase.list(PdtConsultPdtList.class, PdtConsultPdtList.T.PURCHASE + "=?", false, pkey);
//	}
//	
	/**
	 * @param purchase 询盘产品所属的采购商, 不能为空
	 * @param supplier 询盘产品的产品所属的供应商的pkey, 可以为空 
	 * @return
	 * @author yingjianhua
	 */
	public static List<PdtConsultPdtList> listByPurchaseSupplier(Integer purchase, Integer supplier) {
		BeanQuery<PdtConsultPdtList> q = Query
		.SELECT(PdtConsultPdtList.class);
		if(supplier!=null)
			q.LEFT_JOIN(PdtProduct.class, T.PRODUCT, PdtProduct.T.PKEY)
			.WHERE(PdtProduct.T.SUPPLIER, "=?", supplier);
		q.WHERE(T.PURCHASE, "=?", purchase);
		return q.queryList();
	}
	
	/**
	 * @param purchase 询盘产品所属的采购商, 不能为空
	 * @param supplier 询盘产品的产品所属的供应商的pkey, 可以为空 
	 * @return
	 * @author yingjianhua
	 * @throws JSONException 
	 */
	public static List<ConsultPdtView> listViewByPurchase(Integer purchase, Integer supplier, Language lang) throws JSONException {
		List<PdtConsultPdtList> list = listByPurchaseSupplier(purchase, supplier);
		List<ConsultPdtView> views = new ArrayList<>();
		for(PdtConsultPdtList line:list) {
			PdtProduct product = line.gtProduct();
			ConsultPdtView view = new ConsultPdtView();
			view.setId(line.getPkey());
			view.setProductId(product.getPkey());
			view.setCode(product.getCode());
			view.setName(product.getName());
			view.setImgs(product.getPicture());
			views.add(view);
		}
		return views;
	}
	
	public static boolean isOwner(Integer pkey, Integer purchase) {
		SQL sql = new SQL(){{
			SELECT(PdtConsultPdtList.class);
			FROM(PdtConsultPdtList.class);
			WHERE(PdtConsultPdtList.T.PKEY, "=?", pkey);
			WHERE(PdtConsultPdtList.T.PURCHASE, "=?", purchase);
		}};
		return Query.sql(sql).queryCount()>0;
	}
	
	public static void delete(Integer pkey) {
		SQL sql = new SQL(){{
			DELETE_FROM(PdtConsultPdtList.class);
			WHERE(PdtConsultPdtList.T.PKEY, "=?", pkey);
		}};
		Query.sql(sql).executeUpdate();
	}
}
