package irille.shop.usr;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import irille.pub.Log;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrConsultRelation.T;
import irille.view.Page;
import irille.view.usr.ConsultMessageView;
import irille.view.usr.ConsultRelationView;
import irille.view.usr.ConsultView;


public class UsrConsultRelationDAO {
	public static final Log LOG = new Log(UsrConsultRelationDAO.class);
	
	public static List<UsrConsultRelation> listByConsult(Integer pkey) {
		return BeanBase.list(UsrConsultRelation.class, UsrConsultRelation.T.CONSULT + "=?", false, pkey);
	}
	
	/**
	 * 分页查询专属询盘列表
	 * @param start
	 * @param limit
	 * @return
	 * @author yingjianhua
	 * @throws JSONException 
	 */
	public static Page<ConsultView> pagePrivate(int start, int limit, int supplier, String countryName, String title, Language lang) throws JSONException {
		BeanQuery<UsrConsultRelation> q = Query
				.SELECT(UsrConsultRelation.class);
				if(!Str.isEmpty(countryName) || !Str.isEmpty(title)){
					q.LEFT_JOIN(UsrConsult.class, UsrConsult.T.PKEY, UsrConsultRelation.T.CONSULT);
				}
				if(!Str.isEmpty(countryName)) {
					q.LEFT_JOIN(PltCountry.class, UsrConsult.T.COUNTRY, PltCountry.T.PKEY)
					.WHERE(PltCountry.T.NAME, "like ?", "%"+countryName+"%");
				}
				q.WHERE(UsrConsultRelation.T.SUPPLIER, "=?", supplier);
				if(!Str.isEmpty(title)) {
					q.WHERE(UsrConsult.T.TITLE, "like ?", "%"+title+"%");
				}
		Integer totalCount = q.queryCount();
		List<UsrConsultRelation> list = q.limit(start, limit).queryList(UsrConsultRelation.class);
		
		List<ConsultView> views = new ArrayList<>();
		for(UsrConsultRelation bean: list) {
			UsrConsult consult = bean.gtConsult();
			PltCountry country = consult.gtCountry();
			ConsultView view = new ConsultView();
			view.setId(consult.getPkey());
			view.setTitle(consult.getTitle());
			view.setImage(consult.getImage());
			view.setCountry(country.getPkey());
			view.setCountryName(country.getName(lang));
			view.setCountryFlag(country.getNationalFlag());
			view.setName(consult.getName());
			view.setCount(consult.getCount());
			view.setSupplierCount(UsrConsultDAO.max_count-consult.getCount());
			view.setHaveNewMsg(bean.gtPToSNewMsg());
			view.setContent(consult.getContent());
			view.setCreateTime(consult.getCreateTime());
			view.setEmail(consult.gtPurchase().getEmail());
			views.add(view);
		}
		return new Page<>(views, start, limit, totalCount);
	}

	/**
	 * 统计询盘有多少供应商关联
	 * @param pkey
	 * @return
	 * @author yingjianhua
	 */
	public static int countByConsult(Integer pkey) {
		String sql = "SELECT count(*) FROM " + UsrConsultRelation.TB.getCodeSqlTb() + " WHERE " + UsrConsultRelation.T.CONSULT + "=?";
		return Query.sql(sql, pkey).queryCount();
		//return ((Number) BeanBase.queryOneRow(sql, pkey)[0]).intValue();
	}
	
	/**
	 * 供应商对询盘抢单报价,同时向采购商的询盘发送两条留言 一条为普通留言,一条为报价内容
	 * @author yingjianhua
	 *
	 */
	public static class Quote extends IduIns<Quote, UsrConsultRelation> {
		private Integer consultId;
		private UsrConsult consult;
		private Integer supplier;
		private String msg;
		private String quotedPrice;
		
		public Quote(Integer consultId, Integer supplier, String msg, String quotedPrice) {
			System.out.println("consultId:"+consultId);
			System.out.println("supplier:"+supplier);
			System.out.println("msg:"+msg);
			System.out.println("quotedPrice:"+quotedPrice);
			this.consultId = consultId;
			this.supplier = supplier;
			this.msg = msg;
			this.quotedPrice = quotedPrice;
		}

		@Override
		public void before() {
			consult = UsrConsult.load(UsrConsult.class, consultId);
			if(consult.getCount()<=0) {
				throw LOG.err("countErr", "the consult has no more count");
			}
			if(UsrConsultRelation.chkUniqueConsult_supplier(false, consultId, supplier) != null) {
				throw LOG.err("existsErr", "you are already an owner");
			}
			
			UsrConsultRelation bean = new UsrConsultRelation().init();
			bean.setConsult(consultId);
			bean.setSupplier(supplier);
			bean.stSToPNewMsg(true);
			
			setB(bean);
		}
		@Override
		public void after() {
			consult.setCount(consult.getCount()-1);
			consult.stHaveNewMsg(true);
			consult.upd();
			
			new UsrConsultMessageDAO.Send(false, getB().getPkey(), supplier, msg).commit();
			new UsrConsultMessageDAO.Send(false, getB().getPkey(), supplier, "报价:\t\n"+ quotedPrice).commit();
		}
	}
	/**
	 * 发布询盘时若指定了产品,则添加一条询盘和供应商的关联记录
	 * @author yingjianhua
	 *
	 */
	public static class Ins extends IduIns<Ins, UsrConsultRelation> {
		public Ins(Integer consultPkey, Integer supplierPkey) {
			UsrConsultRelation bean = new UsrConsultRelation().init();
			bean.setConsult(consultPkey);
			bean.setSupplier(supplierPkey);
			bean.stPToSNewMsg(true);
			bean.stSToPNewMsg(false);
			setB(bean);
		}
	}
	
	/**
	 * 表示该供应商是否已经拥有该询盘
	 * @param consult
	 * @param supplier
	 * @return
	 * @author yingjianhua
	 */
	public static boolean isOwner(int consult, int supplier) {
		return UsrConsultRelation.chkUniqueConsult_supplier(false, consult, supplier)==null?false:true;
	}
	
	/**
	 * 获取供应商专属询盘详情信息 包括聊天记录 
	 * @param pkey
	 * @return
	 * @author yingjianhua
	 * @throws JSONException 
	 */
	public static ConsultView load(Integer consult, Integer supplier, Language lang) throws JSONException {
		UsrConsultRelation relation = UsrConsultRelation.chkUniqueConsult_supplier(false, consult, supplier);
		if(relation == null) {
			return null;
		}
		/**
		 * 供应商查看过聊天记录后,将是否有新消息设置为否
		 */
		relation.stPToSNewMsg(false);
		relation.upd();
		
		UsrConsult bean = relation.gtConsult();
		PltCountry country = bean.gtCountry();
		
		ConsultView view = new ConsultView();
		view.setQuantity(bean.getQuantity());
		if(bean.getProduct()!=null) {
			view.setProduct(bean.getProduct());
			view.setProductNum(bean.gtProduct().getCode());
		}
		view.setTitle(bean.getTitle());
		view.setImage(bean.getImage());
		view.setCountryName(country.getName(lang));
		view.setName(bean.getName());
		view.setCreateTime(bean.getCreateTime());
		view.setContent(bean.getContent());
		view.setRelations(new ArrayList<>());
		
		ConsultRelationView rv = new ConsultRelationView();
		rv.setId(relation.getPkey());
		//rv.setHaveNewMsg(relation.gtHaveNewMsg());
		rv.setMsgs(new ArrayList<>());
		
		for(UsrConsultMessage m:UsrConsultMessageDAO.listByRelation(relation.getPkey())) {
			ConsultMessageView mv = new ConsultMessageView();
			mv.setContent(m.getContent());
			mv.setP2S(m.gtP2S());
			mv.setSendTime(m.getSendTime());
			rv.getMsgs().add(mv);
		}
		view.getRelations().add(rv);
		
		return view;
	}
	
	public static void deleteByConsult(Integer consult) {
		
		//级联删除留言记录
		UsrConsultMessageDAO.deleteByConsult(consult);
		
		//删除供应商关联
		SQL sql = new SQL(){{
			DELETE_FROM(UsrConsultRelation.class);
			WHERE(UsrConsultRelation.T.CONSULT, "=?", consult);
		}};
		Query.sql(sql).executeUpdate();
	}
	
	/**
	 * 统计询盘下有个几个供应商有新消息未读
	 * @param pkey
	 * @return
	 */
	public static Integer countPurchaseNewMsg(Integer pkey) {
		return Query.SELECT(UsrConsultRelation.class).WHERE(T.S_TO_P_NEW_MSG, "=?", BeanBase.booleanToByte(true)).queryCount();
	}
	
}
