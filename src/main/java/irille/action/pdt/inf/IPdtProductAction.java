package irille.action.pdt.inf;

import java.io.IOException;

import org.json.JSONException;

public interface IPdtProductAction {

	//平台端功能
	//审核，取消审核，取消审核后产品自动下架
	public void verify() throws Exception ;
	
	//商家端功能
	//上架下架，上架只能上架已审核的产品
	public void onSale(boolean onSale, int pkey);
	
	//商家发布产品
	public void publish();
}
