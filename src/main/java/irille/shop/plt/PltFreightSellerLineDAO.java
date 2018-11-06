package irille.shop.plt;



import irille.pub.Log;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.html.Nodes.Select;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.validate.ValidNumber;
import irille.shop.plt.PltFreightSellerLine.T;

import java.math.BigDecimal;
import java.util.List;


public class PltFreightSellerLineDAO {
	private static final Log LOG = new Log(PltFreightSellerLineDAO.class);
	public static class Del extends IduDel<Del,PltFreightSellerLine>{
		@Override
		public void before() {
			super.before();
			SQL sql=new SQL() {{
				SELECT(PltFreightSellerLine.T.MAIN);
				FROM(PltFreightSellerLine.class);
				WHERE(PltFreightSellerLine.T.PKEY," =?",getB().getPkey());
			}};
			PltFreightSellerLine pf=Query.sql(sql).query(PltFreightSellerLine.class);
			
			SQL sql1=new SQL() {{
				SELECT(PltFreightSellerLine.class);
				FROM(PltFreightSellerLine.class);
				WHERE(PltFreightSellerLine.T.MAIN," =?",pf.getMain());
			}};
			Integer num= Query.sql(sql1).queryCount();
			if(num<=1) {
				throw LOG.err("CANNOT DELETE","最后一条不能进行删除");
			}
		}
	}
	public static class ins extends IduIns<ins,PltFreightSellerLine>{
		@Override
		public void before() {
			super.before();
		}
		
		 @Override
	        public void valid() {
			 FormValid().validNotEmpty(T.FREE_PRICE,T.SECTION,T.FREE,T.EXTRA_PRICE,T.AGGRAVATE_PRICE,T.WEIGHT_PRICE,T.WEIGHT_SECTION,T.AGGRAVATE_SECTION);
			 numberValid().validIntegerPositive(T.WEIGHT_SECTION ,T.AGGRAVATE_SECTION);
			 numberValid().validBigDecimalPositive(T.FREE_PRICE
					 ,T.EXTRA_PRICE
					 ,T.WEIGHT_PRICE
					 ,T.AGGRAVATE_PRICE
					);
	            super.valid();
	        }
		
	}
	public static class Upd extends IduUpd<Upd,PltFreightSellerLine>{
		@Override
		public void before() {
			super.before();
		}
		
		 @Override
	        public void valid() {
			 FormValid().validNotEmpty(T.FREE_PRICE,T.SECTION,T.FREE,T.EXTRA_PRICE,T.AGGRAVATE_PRICE,T.WEIGHT_PRICE,T.WEIGHT_SECTION,T.AGGRAVATE_SECTION);
			 numberValid().validIntegerPositive(T.WEIGHT_SECTION ,T.AGGRAVATE_SECTION);
			 numberValid().validBigDecimalPositive(T.FREE_PRICE
					 ,T.EXTRA_PRICE
					 ,T.WEIGHT_PRICE
					 ,T.AGGRAVATE_PRICE
					);
	            super.valid();
	        }
		
	}
	
	/**
	 * 商家过审后,自动将运费配送区域模板初始化给商家
	 * @param supplier
	 */
	public static void init(PltFreight freight, PltFreightSeller main) {
		List<PltFreightLine> list = PltFreightLineDAO.listByFreight(freight.getPkey());
		for(PltFreightLine line:list) {
			PltFreightSellerLine bean = new PltFreightSellerLine();
			bean.stMain(main);
			bean.setSection(line.getSection());
			bean.setBrief(line.getBrief());
			bean.setFree(line.getFree());
			bean.setFreePrice(line.getFreePrice());
			bean.setExtraPrice(line.getExtraPrice());
			bean.setWeightSection(line.getWeightSection());
			bean.setWeightPrice(line.getWeightPrice());
			bean.setAggravateSection(line.getAggravateSection());
			bean.setAggravatePrice(line.getAggravatePrice());
			bean.setRowVersion((short)0);
			bean.ins();
		}
	}
	
}

