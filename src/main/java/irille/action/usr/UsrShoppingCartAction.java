package irille.action.usr;

import irille.action.MgtAction;
import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.usr.UsrCart;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsrShoppingCartAction extends MgtAction<UsrCart> {
	public UsrCart getBean(){
		return _bean;
	}
	
	public void setBean(UsrCart bean){
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return UsrCart.class;
	}

	@Override
	public void list() throws Exception {
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		// 目前过滤器的搜索，是肯定会带初始条件的
		String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
		IduPage page = newPage();
		page.setStart(getStart());
		page.setLimit(getLimit());
		page.setWhere(where);
		page.commit();
		List<UsrCart> list = page.getList();
		JSONObject lineJson = null;
		for (UsrCart line : list) {
			lineJson.put("picture", line.gtSpec().gtProduct().gtSupplier().getName().toString());
			
			if(line.gtSpec().getPics().toString()==null){
				lineJson.put(PdtProduct.T.PICTURE.getFld().getCode(), line.gtSpec().gtProduct().getPicture());
			}else{
				lineJson.put(PdtProduct.T.PICTURE.getFld().getCode(), line.gtSpec().getPics().toString());
			}
			
			lineJson.put(PdtProduct.T.NAME.getFld().getCode(), line.gtSpec().gtProduct().getName());
			lineJson.put(PdtProduct.T.PICTURE.getFld().getCode(), line.gtSpec().getPrice());
			lineJson.put(PdtProduct.T.SKU.getFld().getCode(), line.gtSpec().gtProduct().getSku());
			lineJson.put(PdtSpec.T.COLOR.getFld().getCode(), line.gtSpec().getColor());
			lineJson.put(PdtSpec.T.SIZE.getFld().getCode(), line.gtSpec().getSize());
			
			lineJson = crtJsonByBean(line);
			ja.put(lineJson);
		}
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, page.getCount());
		writerOrExport(json);
		
	}
	
	
	
	

}
