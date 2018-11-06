package irille.sellerAction.plt;

import java.io.IOException;

import irille.pub.Exp;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduIns;
import irille.sellerAction.SellerAction;
import irille.shop.plt.PltCountryFreight;
import irille.shop.plt.PltFreightSellerLine;
import irille.shop.plt.PltFreightSellerLineDAO;

public class PltFreightSellerLineAction extends SellerAction<PltFreightSellerLine>  {

	public void del() throws Exception {
		PltFreightSellerLineDAO.Del del = new PltFreightSellerLineDAO.Del();
		del.setBKey(getPkey());
		BeanBase.executeUpdate("delete from "+PltCountryFreight.TB.getCodeSqlTb()+" where "+PltCountryFreight.T.REGION+"=?",getPkey());
		del.commit();
		writeSuccess();
	}
	
	public void ins() throws Exception {
		IduIns ins = newIns();
		ins.setB(getBean());
		try{
			ins.commit();
			insAfter();
			write();
		}catch(Exp e){
			writeErr(e.getLastMessage());
		}
	}
	public void upd() throws IOException {
		PltFreightSellerLineDAO.Upd upd=new PltFreightSellerLineDAO.Upd();
		upd.setB(getBean());
		upd.commit();
		updAfter();
		write();
	}
}
