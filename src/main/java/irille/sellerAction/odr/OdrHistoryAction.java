package irille.sellerAction.odr;

import irille.pub.Exp;
import irille.pub.bean.BeanBase;
import irille.sellerAction.SellerAction;
import irille.sellerAction.odr.inf.IOdrHistoryAction;
import irille.shop.odr.OdrHistory;
import irille.shop.odr.OdrHistoryDAO;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class OdrHistoryAction extends SellerAction<OdrHistory> implements IOdrHistoryAction{

	private String hisdescibe;
	private Byte histype;
	private String histmain;
	public String getHisdescibe() {
		return hisdescibe;
	}
	public void setHisdescibe(String hisdescibe) {
		this.hisdescibe = hisdescibe;
	}
	public Byte getHistype() {
		return histype;
	}
	public void setHistype(Byte histype) {
		this.histype = histype;
	}
	public String getHistmain() {
		return histmain;
	}
	public void setHistmain(String histmain) {
		this.histmain = histmain;
	}
	
	public void ins() throws Exception{
		OdrHistoryDAO.Ins ins=new OdrHistoryDAO.Ins();
		OdrHistory odr=new OdrHistory();
		ins.setB(odr);
		ins.setHisdescibe(hisdescibe);
		ins.setHistmain(histmain);
		ins.setHistype(histype);
		ins.setHisodrname(("(商家)"+SellerAction.getSupplier().getLoginName()));
		try{
			ins.commit();
			write();
		}catch(Exp e){
			writeErr(e.getLastMessage());
		}
	}
	
	private Integer odrorder;
	
	public Integer getOdrorder() {
		return odrorder;
	}
	public void setOdrorder(Integer odrorder) {
		this.odrorder = odrorder;
	}
	public void list() throws Exception{
		String sql=OdrHistory.T.ODRORDER.getFld().getCodeSqlField()+" = "+odrorder;
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		List<OdrHistory> listhit =BeanBase.list(OdrHistory.class, sql,false);
		JSONObject lineJson = null;
		for (OdrHistory  line: listhit) {
			lineJson=crtJsonByBean(line);
			lineJson.put("state",line.gtState().getLine().getName());
			ja.put(lineJson);
		}
		json.put(STORE_ROOT,ja);
		writerOrExport(json);
		
	}
	
}
