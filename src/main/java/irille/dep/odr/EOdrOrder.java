package irille.dep.odr;

import irille.pub.html.EMCrt;
import irille.pub.html.EMCrtComp;
import irille.pub.html.EMCrtTrigger;
import irille.pub.view.VFlds;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderLine;
import irille.shop.pdt.PdtSpec;

public class EOdrOrder extends OdrOrder {
	public static void main(String[] args) {
		new EOdrOrder().crtExt().crtFiles();
		//页面删除小计
	}
	public EMCrt crtExt(){
		OdrOrderLine.TB.getCode();
		VFlds[] vflds =new VFlds[]{  new VFlds(TB)};
		VFlds[] mflds = new VFlds[] { new VFlds( T.ORDER_NUM,T.STATE) };
		VFlds[] searchVflds =new VFlds[]{ new VFlds(T.ORDER_NUM, T.STATE)};
		EMCrtComp ext = new EMCrtComp(TB, vflds,mflds ,searchVflds,
				new VFlds[] { new VFlds(OdrOrderLine.T.MAIN)});
		VFlds vfl = ext.getVfldsForm();
		vfl.del(T.ORDER_NUM);//删除订单号
		vfl.del(T.TIME);//订单时间
		vfl.del(T.PRICE_TOTAL);//订单总价
		vfl.del(T.PROD_PRICE);//产品总价
		ext.newExts().init();
		return ext;
	}
}
