package irille.shop.plt;

import java.util.List;

import irille.pub.Log;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduOther;
import irille.sellerAction.SellerAction;

public class PltCountryFreightDAO {
	private static final Log LOG = new Log(PltCountryFreightDAO.class);

	public static class Ins extends IduOther<Ins, PltCountryFreight> {
		private Integer section;
		private String pkeys;
		@Override
		public void run() {
			if(pkeys!=null && !pkeys.trim().equals("")) {
				for(String pkey:pkeys.split(",")) {
					PltCountryFreight bean = new PltCountryFreight().init();
					bean.setCountry(Integer.valueOf(pkey));
					bean.setRegion(section);
					bean.setSupplier(SellerAction.getSupplier().getPkey());
					bean.ins();
				}
			}
		}
		public Integer getSection() {
			return section;
		}
		public void setSection(Integer section) {
			this.section = section;
		}
		public String getPkeys() {
			return pkeys;
		}
		public void setPkeys(String pkeys) {
			this.pkeys = pkeys;
		}
	}
	
	public static class del extends IduOther<del, PltCountryFreight>  {
		private String pkeys;
		private Integer section;
		private Integer supplier;
		public Integer getSupplier() {
			return supplier;
		}
		public void setSupplier(Integer supplier) {
			this.supplier = supplier;
		}
		public Integer getSection() {
			return section;
		}
		public void setSection(Integer section) {
			this.section = section;
		}
		@Override
		public void run() {
			if(pkeys!=null && !pkeys.trim().equals("")) {
				for(String pkey:pkeys.split(",")) {
					SQL sql=new SQL() {{
						SELECT(PltCountryFreight.class);
						FROM(PltCountryFreight.class);
						WHERE(PltCountryFreight.T.COUNTRY," =?",pkey);
						WHERE(PltCountryFreight.T.SUPPLIER," =?",supplier);
						WHERE(PltCountryFreight.T.REGION," =?",section);
					}};
					List<PltCountryFreight> pcf=Query.sql(sql).queryList(PltCountryFreight.class);
					pcf.get(0).setPkey(Integer.valueOf(pcf.get(0).getPkey()));
					pcf.get(0).del();
				}
			}
		}
		public String getPkeys() {
			return pkeys;
		}
		public void setPkeys(String pkeys) {
			this.pkeys = pkeys;
		}
	}
}
