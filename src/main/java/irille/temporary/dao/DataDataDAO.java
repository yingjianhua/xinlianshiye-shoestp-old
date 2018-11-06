package irille.temporary.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.exp.exp.ExpBase;
import irille.exp.exp.ExpBaseDAO.Del;
import irille.exp.exp.ExpBaseDAO.Ins;
import irille.exp.exp.ExpBaseDAO.Todo;
import irille.exp.exp.ExpBaseDAO.Undo;
import irille.exp.exp.ExpBaseDAO.Upd;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.ColorView;
import irille.homeAction.usr.dto.OdrView;
import irille.homeAction.usr.dto.ProductView;
import irille.homeAction.usr.dto.SpecView;
import irille.pub.Exp;
import irille.pub.LogMessage;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.query.BeanQuery;
import irille.pub.i18n.I18NUtil;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.idu.IduUpdLines;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.util.excel.BaseExcel;
import irille.sellerAction.odr.OdrExportExcelAction;
import irille.shop.odr.Odr.OdrState;
import irille.shop.odr.OdrOrder.T;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.pdt.PdtSpecDAO;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;
import irille.shop.plt.PltFreightSeller;
import irille.shop.plt.PltFreightSellerDAO;
import irille.shop.plt.PltPay;
import irille.shop.plt.PltPayDAO;
import irille.shop.prm.Prm;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.usr.Usr;
import irille.shop.usr.Usr.OAddress;
import irille.shop.usr.UsrCart;
import irille.shop.usr.UsrCartDAO;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrPurchaseLine;
import irille.shop.usr.UsrPurchaseLineDAO;
import irille.shop.usr.UsrSupplier;
import irille.temporary.entity.DataData;
import irille.view.Page;
import irille.view.odr.HistoryView;
import irille.view.odr.OrderLineView;
import irille.view.odr.OrderSearchView;
import irille.view.odr.OrderView;
import irille.view.plt.FreightSellerlistView;
import irille.view.plt.PayTypeView;
import irille.view.usr.AddressView;
import irille.view.usr.SupplierView;

public class DataDataDAO {

    private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final LogMessage LOG = new LogMessage(DataDataDAO.class);
    
	public static class Ins extends IduIns<Ins, DataData> {
		@Override
		public void before() {
			super.before();
//		
		}
	}

	public static class Upd extends IduUpd<Upd, DataData> {
		@Override
		public void before() {
			super.before();
			DataData model = loadThisBeanAndLock();
		
			setB(model);
		}
	}

	public static class Del extends IduDel<Del, DataData> {
	
	}
}
