package irille.shop.usr;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import irille.pub.Log;
import irille.pub.Str;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.validate.ValidForm;
import irille.pub.validate.ValidRegex;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrConsult.T;
import irille.view.Page;
import irille.view.usr.ConsultRelationView;
import irille.view.usr.ConsultView;

public class UsrConsultDAO {
	public static final Log LOG = new Log(UsrConsultDAO.class);
	//最大询盘抢单剩余次数
	public static final int max_count = 5;
	
	/**
	 * 统计该采购商当前发布了几个询盘
	 * @param purchase
	 * @return
	 * @author yingjianhua
	 */
	public static Integer countByPurchase(Integer purchase) {
		return Query.SELECT(UsrConsult.class).WHERE(T.PURCHASE, "=?", purchase).queryCount();
//		SQL sql = new SQL(){{
//			SELECT(UsrConsult.class);
//			FROM(UsrConsult.class);
//			WHERE(T.PURCHASE, "=?", purchase);
//		}};
//		return Query.sql(sql).queryCount();
	}
	/**
	 * 发布询盘
	 * @author yingjianhua
	 *
	 */
	public static class Publish extends IduIns<Publish, UsrConsult> {
		private ConsultView v;
		private int purchase;
		
		public Publish(ConsultView v, int purchase) {
			this.v = v;
			this.purchase = purchase;
		}
		
		@Override
		public void before() {
			UsrConsult bean = new UsrConsult();
			ValidForm vf = FormValid(bean);
			ValidRegex vr = RegexValid(bean);
			
			bean.setTitle(v.getTitle());
			bean.setImage(v.getImage());
			if(v.getProduct() != null) {
				vf.validExists(PdtProduct.TB, PdtProduct.T.PKEY, v.getProduct());
				bean.setProduct(v.getProduct());
				bean.setCount(0);
				bean.stIsPublic(false);
			} else if(v.getSupplierId()!=null) {
				vf.validExists(UsrSupplier.TB, UsrSupplier.T.PKEY, v.getSupplierId());
				bean.setCount(0);
				bean.stIsPublic(false);
			} else {
				bean.setCount(max_count);
				bean.stIsPublic(true);
			}
			bean.stHaveNewMsg(false);
			bean.setName(v.getName());
			bean.setEmail(v.getEmail());
			bean.setPurchase(purchase);
			bean.setCountry(v.getCountry());
			bean.setContent(v.getContent());
			bean.setQuantity(v.getQuantity());
			bean.setCreateTime(Env.getTranBeginTime());
			bean.setRowVersion((short)0);
			
			vf.validExists(PltCountry.TB, PltCountry.T.PKEY, v.getCountry());
			vf.validNotEmpty(T.TITLE, T.NAME, T.EMAIL, T.CONTENT, T.QUANTITY);
			vr.validEmail(T.EMAIL);
			
			setB(bean);
		}
		
		@Override
		public void after() {
			super.after();
			if(v.getProduct() != null) {
				new UsrConsultRelationDAO.Ins(getB().getPkey(), getB().gtProduct().getSupplier()).commit();
			} else if(v.getSupplierId() != null) {
				new UsrConsultRelationDAO.Ins(getB().getPkey(), v.getSupplierId()).commit();
			}
		}
	}
	
	/**
	 * 分页查询我的询盘列表
	 * @param start
	 * @param limit
	 * @return
	 * @author yingjianhua
	 */
	public static Page<ConsultView> pagePrivate(int start, int limit, int purchase) {
		BeanQuery<UsrConsult> q = Query.SELECT(UsrConsult.class).WHERE(T.PURCHASE, "=?", purchase);
		Integer totalCount = q.queryCount();
		List<UsrConsult> list = q.limit(start, limit).queryList();
//		SQL sql = new SQL(){{
//			SELECT(UsrConsult.class);
//			FROM(UsrConsult.class);
//			WHERE(UsrConsult.T.PURCHASE, "=?").PARAM(purchase);
//		}};
//		SqlQuery q = Query.sql(sql);
//		Integer totalCount = q.queryCount();
//		List<UsrConsult> list = q.limit(start, limit).queryList(UsrConsult.class);
		List<ConsultView> views = new ArrayList<>();
		for(UsrConsult bean: list) {
			List<ConsultRelationView> rvs = new ArrayList<>();
			boolean haveNewMsg = false;
			for(UsrConsultRelation r:UsrConsultRelationDAO.listByConsult(bean.getPkey())) {
				if(r.gtSToPNewMsg())
					haveNewMsg = true;
				ConsultRelationView rv = new ConsultRelationView();
				rv.setSupplierName(r.gtSupplier().getName());
				rvs.add(rv);
			}
			ConsultView view = new ConsultView();
			view.setId(bean.getPkey());
			view.setTitle(bean.getTitle());
			view.setImage(bean.getImage());
			view.setCreateTime(bean.getCreateTime());
			view.setHaveNewMsg(haveNewMsg);
			view.setRelations(rvs);
			view.setCount(bean.getCount());
			view.setQuantity(bean.getQuantity());
			view.setSupplierCount(UsrConsultRelationDAO.countByConsult(bean.getPkey()));
			views.add(view);
		}
		return new Page<>(views, start, limit, totalCount);
	}
	/**
	 * 分页查询公共询盘列表
	 * @param start
	 * @param limit
	 * @return
	 * @author yingjianhua
	 * @throws JSONException 
	 */
	public static Page<ConsultView> pagePublic(int start, int limit, String countryName, String title,String qdvalue,Integer supplier, Language lang) throws JSONException {
//		//String where = T.COUNT+">0"; 已经抢单完毕的询盘也显示
//		List<Serializable> params = new ArrayList<>();
//		String sql = "select a.* from "+UsrConsult.TB.getCodeSqlTb()+" a";
//		if(!Str.isEmpty(countryName)) {
//			sql += " left join "+PltCountry.TB.getCodeSqlTb()+" b on a."+T.COUNTRY+"=b."+PltCountry.T.PKEY+" where b."+PltCountry.T.NAME+"=?";
//			params.add(countryName);
//		} else {
//			sql += " where 1=1";
//		}
//		if(!Str.isEmpty(title)) {
//			sql += (" AND "+T.TITLE+" like ?");
//			params.add("%"+title+"%");
//		}
//		sql += (" order by "+T.CREATE_TIME+" desc");
//		SqlQuery q = Query.sql(sql, params);
//		Integer totalCount = q.queryCount();
//		List<UsrConsult> list = q.limit(start, limit).queryList(UsrConsult.class);
		
		BeanQuery<UsrConsult> q = Query.SELECT(UsrConsult.class);
		q.WHERE(T.IS_PUBLIC, "=?", true);
		if(!Str.isEmpty(countryName)) {
			q.LEFT_JOIN(PltCountry.class, T.COUNTRY, PltCountry.T.PKEY);
			q.WHERE(PltCountry.T.NAME, " like ? ","%"+countryName+"%" );
		}
		if(!Str.isEmpty(qdvalue)) {
			q.LEFT_JOIN(UsrConsultRelation.class, T.PKEY, UsrConsultRelation.T.CONSULT);
			if(qdvalue.equals("0")) {
				q.WHERE(UsrConsultRelation.T.SUPPLIER," is null");
			}
			if(qdvalue.equals("1")) {
				q.WHERE(UsrConsultRelation.T.SUPPLIER," = ? ",supplier);
			}
		}
		if(!Str.isEmpty(title))
			q.WHERE(T.TITLE, "like ?", "%"+title+"%");
		q.ORDER_BY(T.CREATE_TIME, "desc");
		Integer totalCount = q.queryCount();
		List<UsrConsult> list = q.limit(start, limit).queryList();
		List<ConsultView> views = new ArrayList<>();
		for(UsrConsult bean: list) {
			//为私有询盘,不显示在公共询盘列表中
			//私有询盘为,剩余抢单次数为0,并且询盘关联表有一条数据的
			//公共询盘为,剩余抢单次数不为0,或者询盘关联表数据不止一条
			PltCountry country = bean.gtCountry();
			ConsultView view = new ConsultView();
			view.setId(bean.getPkey());
			view.setTitle(bean.getTitle());
			view.setImage(bean.getImage());
			view.setCountry(country.getPkey());
			view.setCountryName(country.getName(lang));
			view.setCountryFlag(country.getNationalFlag());
			view.setName(bean.getName());
			view.setCount(bean.getCount());
			view.setSupplierCount(UsrConsultRelationDAO.countByConsult(bean.getPkey()));
			//view.setEmail(bean.getEmail());
			view.setContent(bean.getContent());
			view.setCreateTime(bean.getCreateTime());
			views.add(view);
		}
		return new Page<>(views, start, limit, totalCount);
	}
	
	/**
	 * 判断采购商是否是该询盘的拥有着
	 * @param pkey
	 * @param purchase
	 */
	public static boolean isOwner(Integer pkey, Integer purchase) {
		SQL sql = new SQL() {{
			SELECT(UsrConsult.class);
			FROM(UsrConsult.class);
			WHERE(T.PKEY, "=?", pkey);
			WHERE(T.PURCHASE, "=?", purchase);
		}};
		Integer c = Query.sql(sql).queryCount();
		return c>0;
	}
	
	public static void delete(Integer pkey) {
		//级联删除供应商关联
		UsrConsultRelationDAO.deleteByConsult(pkey);
		
		//删除询盘
		SQL sql = new SQL(){{
			DELETE_FROM(UsrConsult.class);
			WHERE(T.PKEY, "=?", pkey);
		}};
		Query.sql(sql).executeUpdate();
	}
	
	/**
	 * 获取公共询盘详情信息 
	 * @param pkey
	 * @return
	 * @author yingjianhua
	 * @throws JSONException 
	 */
	public static ConsultView load(Integer pkey, Language lang) throws JSONException {
		UsrConsult bean = UsrConsult.chk(UsrConsult.class, pkey);
		if(bean == null)
			return null;
		else {
			PltCountry country = bean.gtCountry();
			ConsultView view = new ConsultView();
			view.setTitle(bean.getTitle());
			view.setImage(bean.getImage());
			view.setQuantity(bean.getQuantity());
			if(bean.getProduct()!=null) {
				view.setProduct(bean.getProduct());
				view.setProductNum(bean.gtProduct().getCode());
			}
			view.setQuantity(bean.getQuantity());
			view.setCountry(country.getPkey());
			view.setCountryName(country.getName(lang));
			view.setCountryFlag(country.getNationalFlag());
			view.setName(bean.getName());
			view.setSupplierCount(UsrConsultRelationDAO.countByConsult(bean.getPkey()));
			view.setCount(bean.getCount());
			view.setHaveNewMsg(UsrConsultRelationDAO.countPurchaseNewMsg(pkey)>0?true:false);
			view.setContent(bean.getContent());
			view.setCreateTime(bean.getCreateTime());
			return view;
		}
	}
}
