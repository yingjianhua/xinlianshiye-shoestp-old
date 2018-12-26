package irille.shop.usr;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Query;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.usr.UsrSupplierCategory.T;
import irille.view.usr.SupplierCategoryView;

public class UsrSupplierCategoryDAO {
	public static final Log LOG = new Log(UsrSupplierCategoryDAO.class);
	
	/**
	 * 列表供应商一级分类
	 * @author yingjianhua
	 */
	public static List<SupplierCategoryView> listViewIsTop(Language lang) {
		
			return Query
					.SELECT(lang, T.PKEY,T.SHOW_NAME)
					.FROM(UsrSupplierCategory.class)
					.queryList()
					.stream()
					.map(bean->new SupplierCategoryView() {{
							setId(bean.getPkey());
							setName(bean.getShowName());
						}})
					.collect(Collectors.toList());
		
	}
	/**
	 * 列表供应商分类
	 * @author liyichao
	 */
	public static List<irille.sellerAction.view.SupplierCategoryView> listViews() {
		return Query
				.SELECT(T.PKEY,T.SHOW_NAME)
				.FROM(UsrSupplierCategory.class)
				.queryList()
				.stream()
				.map(bean->new irille.sellerAction.view.SupplierCategoryView() {{
					setId(bean.getPkey());
					setShowname(bean.getShowName());
				}})
				.collect(Collectors.toList());

	}
	
	/**
	 * 列表供应商分类
	 * @author liyichao
	 */
	public static List<SupplierCategoryView> listView() {
		return Query
				.SELECT(T.PKEY,T.NAME)
				.FROM(UsrSupplierCategory.class)
				.queryList()
				.stream()
				.map(bean->new SupplierCategoryView() {{
						setId(bean.getPkey());
						setName(bean.getName());
					}})
				.collect(Collectors.toList());
	
}
	
	
	public static class Ins extends IduIns<Ins, UsrSupplierCategory> {
		@Override
		public void before() {
			super.before();
			getB().stCreateBy(getUser());
			getB().setCreateTime(Env.getTranBeginTime());
			setB(translateUtil.autoTranslate(getB()));
		}
	}

	public static class Upd extends IduUpd<Upd, UsrSupplierCategory> {
		@Override
		public void before() {
			super.before();
			setB(translateUtil.autoTranslateByManageLanguage(getB(),true));
			UsrSupplierCategory dbBean = loadThisBeanAndLock();
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), T.PKEY,T.CREATE_BY, T.CREATE_TIME);
			setB(dbBean);
		}
	}
	
	public static class Del extends IduDel<Del,UsrSupplierCategory>{
		public void valid() {
			super.valid();
			SQL sql = new SQL(){{
				SELECT(UsrSupplierCategory.class);
				FROM(UsrSupplierCategory.class);
				WHERE(T.CATEGORY_UP,"=?").PARAM(getB().getPkey());
			}};
			SqlQuery query = Query.sql(sql);
			Integer count = query.queryCount();
			if(count > 0){
				throw LOG.err("hasChild","存在下级分类,不可删除");
			}
		}
	}
	
	public static class  InsInit extends IduOther<InsInit, UsrSupplierCategory> {
		private Stream<String> stream = Stream.empty();

		public Stream<String> getStream() {
			return stream;
		}

		public void setStream(Stream<String> stream) {
			this.stream = stream;
		}
		@Override
		public void run() {
			super.run();
			stream.forEach((name)->{
				UsrSupplierCategory b = new UsrSupplierCategory().init();
				b.setName(name);
				b.setShowName(name);
				b.ins();
			});
		}
	}
}
