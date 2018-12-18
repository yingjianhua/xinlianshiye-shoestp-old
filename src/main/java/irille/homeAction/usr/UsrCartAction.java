package irille.homeAction.usr;

import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.odr.OdrOrderAction;
import irille.homeAction.pdt.dto.ProductAndSpceView;
import irille.homeAction.pdt.dto.ProductView;
import irille.homeAction.pdt.dto.SpecView;
import irille.homeAction.pdt.dto.SupplierView;
import irille.homeAction.plt.dto.PltPayView;
import irille.homeAction.prm.dto.SpecStaView;
import irille.homeAction.usr.dto.AddressView;
import irille.homeAction.usr.dto.CartView;
import irille.homeAction.usr.dto.ColorView;
import irille.pub.Exp;
import irille.pub.LogMessage;
import irille.pub.bean.BeanBase;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.odr.Odr.PayType;
import irille.shop.pdt.*;
import irille.shop.plt.*;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.usr.*;
import irille.shop.usr.Usr.OAddress;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


public class UsrCartAction extends HomeAction<UsrCart> {
	private static final LogMessage LOG = new LogMessage(UsrCartAction.class);

	private static final UsrCartDAO.Query query = new UsrCartDAO.Query();// 购物车
	private static final PdtProductDAO.Query productQuery = new PdtProductDAO.Query();// 产品
	private static final PdtSpecDAO.Query specQuery = new PdtSpecDAO.Query();// 产品规格
	private static final UsrSupplierDAO.Query supplierQuery = new UsrSupplierDAO.Query();// 商家
	private static final PdtCatDAO.Query catQuery = new PdtCatDAO.Query();

	// 返回给收货页面的数据
	public Map<String, Object> MapData;

	// 单个规格
	private String singleSpecNum;

	// 规格pkey集合
	private String strSpec;

	// 快递公司编号
	private String expressNum;

	// 商品数量
	private Integer qty;

	// 商家编号
	private String supplierNum;

	public String getCartStr() {
		return cartStr;
	}

	public void setCartStr(String cartStr) {
		this.cartStr = cartStr;
	}

	// 购物车字符串
	private String cartStr;

	private Map<String, Object> map = new HashMap<>();

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

  /**
     * 加入购物车
     *
     * @author lijie@shoestp.cn
     * @Description:加入购物车
     * @date 2018/8/8 10:56
     */
    private String _carts;

    public String getCarts() {
        return _carts;
    }

    public UsrCartAction setCarts(String carts) {
        this._carts = carts;
        return this;
    }

    /**
     * 放入购物车...
     * Json 形式   { specid:qty}这样的形式
     *
     * @author lijie@shoestp.cn
     * @Description:
     * @date 2018/8/8 15:46
     */
//    public void InsCart() throws Exception {
//        if (getCarts() == null || getCarts().length() < 1) {
//            writeErr(0, "无效参数");
//            return;
//        }
//        JSONObject jsonObject = new JSONObject(getCarts());
//        Iterator iterator = jsonObject.keys();
//        while (iterator.hasNext()) {
//            String key = String.valueOf(iterator.next());
//            int i = Integer.valueOf(key);
//            int qty = jsonObject.getInt(key);
//            if (qty > 0) {
//                inserCart.inserCart(i, jsonObject.getInt(key), getPurchase().getPkey());
//
//            }
//        }
//        writeSuccess();
//    }


	public void list() throws Exception {
		if (getPurchase() == null)
			return;
		List<UsrCart> carts = query.listByPurchase(getPurchase().getPkey());// 查询当前采购商下的所有购物车
		List<PdtProduct> products = productQuery.listBySpec(specQuery.listByCart(carts));// 购物车下的规格
		List<PdtSpec> specs = specQuery.listByProduct(products); // 该产品下的所有规格
		List<UsrSupplier> suppliers = supplierQuery.listByProduct(products); // 所有产品下对应的商家

		JSONObject json = new JSONObject();
		json.put("shops", new JSONArray(SupplierView.build(suppliers), false));
		json.put("products", new JSONArray(ProductView.build(products), false));
		json.put("specs", new JSONArray(SpecView.build(specs), false));
		json.put("carts", new JSONArray(CartView.build(carts), false));
		write(json.toString());

	}

	private String cartPkeys;

	public String getCartPkeys() {
		return cartPkeys;
	}
	public void setCartPkeys(String cartPkeys) {
		this.cartPkeys = cartPkeys;
	}

	private Map<String,Object> dataMap = new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}


	//购物车预结算页面 chenlili
	/**
	 * @author chenlili
	 * @updateBy liyichao
	 * @updateTime 2018-8-17
	 * @return
	 * @throws JSONException
	 */
	@NeedLogin
	public String showAdvCart() throws JSONException {
		UsrCartDAO.SubmitCart submitCart = new UsrCartDAO.SubmitCart();
		submitCart.setCartPkeys(getCartPkeys());
		submitCart.commit();
		List<UsrPurchaseLine> listUsrPurchaseLine = UsrPurchaseLineDAO.listByPurchaseAddrsstype(getPurchase().getPkey(), OAddress.COMMON);//BeanBase.list(UsrPurchaseLine.class, UsrPurchaseLine.T.PURCHASE.getFld().getCodeSqlField() + " =? AND " + UsrPurchaseLine.T.ADDRSSTYPE.getFld().getCodeSqlField() + " = ? ",false,HomeAction.getPurchase().getPkey(),Usr.OAddress.COMMON.getLine().getKey());
		List<AddressView> address = AddressView.buildByPurchaseLine(listUsrPurchaseLine);
		List<UsrPurchaseLine> billAddressList = UsrPurchaseLineDAO.listByPurchaseAddrsstype(getPurchase().getPkey(), OAddress.BILLED);//BeanBase.list(UsrPurchaseLine.class, UsrPurchaseLine.T.PURCHASE.getFld().getCodeSqlField() + " =? AND " + UsrPurchaseLine.T.ADDRSSTYPE.getFld().getCodeSqlField() + " = ? ",false,HomeAction.getPurchase().getPkey(),Usr.OAddress.BILLED.getLine().getKey());
		AddressView billAddress = null;
		if(billAddressList != null && billAddressList.size() > 0){
			billAddress = AddressView.buildByPurchaseLine(billAddressList.get(0));
		}
		List<PltCountry> listPltCountry=new  ArrayList<PltCountry>();
		List<PltProvince> listProvince=new  ArrayList<PltProvince>();
		 for (int i = 0; i < listUsrPurchaseLine.size(); i++) {
           listPltCountry.add(listUsrPurchaseLine.get(i).gtCountry());
           listProvince.add(listUsrPurchaseLine.get(i).gtRegion());
           if (Integer.valueOf(listUsrPurchaseLine.get(i).getIsdefault()).equals(1)) {
               listUsrPurchaseLine.add(0, listUsrPurchaseLine.remove(i));
               listPltCountry.add(0, listPltCountry.remove(i));
               listProvince.add(0,listProvince.remove(i));
           }
       }
		 translateUtil.getAutoTranslateList(listPltCountry, HomeAction.curLanguage());
		 translateUtil.getAutoTranslateList(listProvince, HomeAction.curLanguage());
		List<PltCountry> countries = BeanBase.list(PltCountry.class, " 1=1 ",false);//PltCountryDAO.list(curLanguage());//BeanBase.list(PltCountry.class, null,false);
		Map<Integer,List<PltProvince>> provinceMap = new HashMap<Integer,List<PltProvince>>();
		List<PltProvince> provinceList = BeanBase.list(PltProvince.class, " 1=1 " ,false);//PltProvinceDAO.list(curLanguage());//BeanBase.list(PltProvince.class, null ,false);
		for(PltProvince p : provinceList){
			if(provinceMap.get(p.getMain()) == null){
				List<PltProvince> proList = new ArrayList<PltProvince>();
				proList.add(p);
				provinceMap.put(p.getMain(), proList);
			}else{
				List<PltProvince> proList = provinceMap.get(p.getMain());
				proList.add(p);
			}
		}
		translateUtil.getAutoTranslateList(countries, HomeAction.curLanguage());
		translateUtil.getAutoTranslateMap(provinceMap, HomeAction.curLanguage());
		List<UsrCart> carts = BeanBase.list(UsrCart.class, UsrCart.T.PKEY.getFld().getCodeSqlField() + " in ( " + cartPkeys + " ) ",false);
		List<irille.homeAction.usr.dto.SupplierView> supplierList = irille.homeAction.usr.dto.SupplierView.buildByCart(carts);
		String supId = "";
		for(irille.homeAction.usr.dto.SupplierView view : supplierList){
			if(supId == ""){
				supId += view.getId();
			}else{
				supId += "," + view.getId();
			}
		}
		//UsrSupplier supplier = carts.get(0).gtSupplier();
		List<PltPay> payList = BeanBase.list(PltPay.class,PltPay.T.SUPPLIER.getFld().getCodeSqlField() + " in("+supId+") AND " + PltPay.T.ENABLED.getFld().getCodeSqlField() + " = ? ",false,Sys.OEnabled.TRUE.getLine().getKey());
		List<PltPayView> payViewList = PltPayView.build(payList);
		Integer totalQty = 0;
		BigDecimal totalAmt = BigDecimal.ZERO;
		for(UsrCart cart : carts){
			totalQty += cart.getQty();
			totalAmt = totalAmt.add(cart.getAmtTotal());
		}
		List<PltFreightSeller> expressList = BeanBase.list(PltFreightSeller.class,PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField() + " in("+supId+") AND " + PltFreightSeller.T.ENABLED.getFld().getCodeSqlField() + " = ?" ,false,Sys.OEnabled.TRUE.getLine().getKey());
		List<irille.homeAction.usr.dto.ProductView> pdtViews = irille.homeAction.usr.dto.ProductView.build(carts);
		Map<Integer,List<ColorView>> colorViews = ColorView.buildByCart(carts);
		Map<Integer,List<SpecStaView>> specViews = SpecStaView.buildByCart(carts);
		AddressView addressView = AddressView.build(listUsrPurchaseLine, curLanguage());
		dataMap.put("billAddress", billAddress);
		dataMap.put("express", expressList);
		dataMap.put("payMethod", payViewList);
		dataMap.put("supplierList", supplierList);
		dataMap.put("address", address);
		dataMap.put("addressView", addressView);
		dataMap.put("country", countries);
		dataMap.put("province", provinceMap);
		//dataMap.put("supplier", supplier);
		dataMap.put("product", pdtViews);
		dataMap.put("color", colorViews);
		dataMap.put("specViews", specViews);
		dataMap.put("totalQty", totalQty);
		dataMap.put("totalAmt", totalAmt);
		dataMap.put("myCountry", listPltCountry);
		dataMap.put("myProvince", listProvince);
		dataMap.put("state", 0);
//		List<PltCountry> countrys = PltCountryDAO.Query.list();
//        List<UsrSupplier> listUsrSupplier = new ArrayList<UsrSupplier>();
//        BigDecimal subTotal = BigDecimal.ZERO;
//        Integer sum = 0;
//        List<UsrPurchaseLine> listUsrPurchaseLine = Idu.getLines(UsrPurchaseLine.T.PURCHASE.getFld(), getPurchase().getPkey());
//        List<PltCountry> listPltCountry = new ArrayList<PltCountry>();
//        List<PltProvince> listProvince = new ArrayList<PltProvince>();
//        List<PdtSpec> listspecA = new ArrayList<PdtSpec>();
//        List<PdtSpec> listspecB = new ArrayList<PdtSpec>();
//        for (int i = 0; i < listUsrPurchaseLine.size(); i++) {
//            listPltCountry.add(listUsrPurchaseLine.get(i).gtCountry());
//            listProvince.add(listUsrPurchaseLine.get(i).gtRegion());
//            if (listUsrPurchaseLine.get(i).getIsdefault() == 1) {
//                listUsrPurchaseLine.add(0, listUsrPurchaseLine.remove(i));
//                listPltCountry.add(0, listPltCountry.remove(i));
//                listProvince.add(0,listProvince.remove(i));
//            }
//        }
//		List<UsrCart> listcart = BeanBase.list(UsrCart.class,
//				UsrCart.T.PKEY.getFld().getCodeSqlField() + " in(" + cartPkeys + ") ", false);
//		Map<String, Map<PdtProduct, Map<PdtColor, List<PdtSize>>>> cart = new HashMap<String, Map<PdtProduct, Map<PdtColor, List<PdtSize>>>>();
//		Map<PdtProduct, Map<PdtColor, List<PdtSize>>> product = new HashMap<PdtProduct, Map<PdtColor, List<PdtSize>>>();
//		//Map<PdtProduct, Map<PdtColor, List<PdtSize>>> productB = new HashMap<PdtProduct, Map<PdtColor, List<PdtSize>>>();
//        for (UsrCart usrcart : listcart) {
//            subTotal = subTotal.add(usrcart.getAmtTotal());
//            sum += usrcart.getQty();
//            listUsrSupplier.add(usrcart.gtSupplier());
        	//if (usrcart.getCartType()==0) {
//        		listspecA.add(usrcart.gtSpec());
//        		if (product.get(usrcart.gtSpec().gtProduct()) == null) {
//		              Map<PdtColor, List<PdtSize>> map = new HashMap<PdtColor, List<PdtSize>>();
//		              List<PdtSize> sizeList = new ArrayList<PdtSize>();
//		              PdtSize sz=usrcart.gtSpec().gtSize();
//		              sz.setPkey(usrcart.getQty());
//		              sz.setProductCategory(usrcart.getAmtTotal().intValue());
//		              sz.setCreateBy(usrcart.gtSpec().getPrice().intValue());
//		              sizeList.add(sz);
//		              map.put(usrcart.gtSpec().gtColor(), sizeList);
//		              product.put(usrcart.gtSpec().gtProduct(), map);
//		            } else {
//		              Map<PdtColor, List<PdtSize>> mapTest = product.get(usrcart.gtSpec().gtProduct());
//		              if (mapTest.get(usrcart.gtSpec().gtColor()) == null) {
//		                  List<PdtSize> sizeList = new ArrayList<PdtSize>();
//		                  PdtSize sz=usrcart.gtSpec().gtSize();
//			              sz.setPkey(usrcart.getQty());
//			              sz.setProductCategory(usrcart.getAmtTotal().intValue());
//			              sz.setCreateBy(usrcart.gtSpec().getPrice().intValue());
//		                  sizeList.add(sz);
//		                  mapTest.put(usrcart.gtSpec().gtColor(), sizeList);
//		              } else {
//		            	  PdtSize sz=usrcart.gtSpec().gtSize();
//			              sz.setPkey(usrcart.getQty());
//			              sz.setProductCategory(usrcart.getAmtTotal().intValue());
//			              sz.setCreateBy(usrcart.gtSpec().getPrice().intValue());
//		                  mapTest.get(usrcart.gtSpec().gtColor()).add(sz);
//		              }
//		              product.put(usrcart.gtSpec().gtProduct(), mapTest);
//
//				  }
//				cart.put("0", product);
//			}else {
//				listspecB.add(usrcart.gtSpec());
//				if (productB.get(usrcart.gtSpec().gtProduct()) == null) {
//		              Map<PdtColor, List<PdtSize>> map = new HashMap<PdtColor, List<PdtSize>>();
//		              List<PdtSize> sizeList = new ArrayList<PdtSize>();
//		              PdtSize sz=usrcart.gtSpec().gtSize();
//		              sz.setPkey(usrcart.getQty());
//		              sz.setProductCategory(usrcart.getAmtTotal().intValue());
//		              sz.setCreateBy(usrcart.gtSpec().getPrice().intValue());
//		              sizeList.add(sz);
//		              map.put(usrcart.gtSpec().gtColor(), sizeList);
//		              PdtProduct pct= usrcart.gtSpec().gtProduct();
//		              pct.setCurPrice(usrcart.gtRelatedGroup().getAmt());
//		              productB.put(pct, map);
//		            } else {
//		              Map<PdtColor, List<PdtSize>> mapTest = productB.get(usrcart.gtSpec().gtProduct());
//		              if (mapTest.get(usrcart.gtSpec().gtColor()) == null) {
//		                  List<PdtSize> sizeList = new ArrayList<PdtSize>();
//		                  PdtSize sz=usrcart.gtSpec().gtSize();
//			              sz.setPkey(usrcart.getQty());
//			              sz.setProductCategory(usrcart.getAmtTotal().intValue());
//			              sz.setCreateBy(usrcart.gtSpec().getPrice().intValue());
//			              sizeList.add(sz);
//		                  mapTest.put(usrcart.gtSpec().gtColor(), sizeList);
//		              } else {
//		            	  PdtSize sz=usrcart.gtSpec().gtSize();
//			              sz.setPkey(usrcart.getQty());
//			              sz.setProductCategory(usrcart.getAmtTotal().intValue());
//			              sz.setCreateBy(usrcart.gtSpec().getPrice().intValue());
//		                  mapTest.get(usrcart.gtSpec().gtColor()).add(sz);
//		              }
//		              PdtProduct pct= usrcart.gtSpec().gtProduct();
//		              pct.setCurPrice(usrcart.gtRelatedGroup().getAmt());
//		              productB.put(pct, mapTest);
//
//				  }
//				cart.put("1", productB);
//			}
//        }
//		        map.put("countrys", countrys);
//		        map.put("province", listProvince);
//		        map.put("mycountry", listPltCountry);
//		        map.put("address", listUsrPurchaseLine);
//		        map.put("allData", cart);
//		        map.put("supplier", listUsrSupplier);
//		        map.put("sum", sum);
//		        map.put("allspec", listcart);
//		        map.put("specA", listspecA);
//		        map.put("specB", listspecB);
		        setResult("/home/cartcheckout.jsp");
		        return TRENDS;
	}



	/**
	 * xiayan 验证购物车中选中的商品是否是同一商店
	 *
	 * @throws JSONException
	 * @throws IOException
	 */
	public void cartDateVerify() throws Exception {
		String[] strSpecList = strSpec.split(",");
		JSONObject json = new JSONObject();
		List<PdtSpec> pdtSpecList = new ArrayList<>();
		for (int i = 0; i < strSpecList.length; i++) {
			List<PdtSpec> specList = BeanBase.list(PdtSpec.class, PdtSpec.T.PKEY + " = " + strSpecList[i], false);
			PdtSpec spec = specList.get(0);
			pdtSpecList.add(spec);
		}
		if (pdtSpecList.size() == 1) {
			json.put("data", "1");
			json.put("item", strSpec);
			write(json.toString());
			return;
		}
		Integer productPkey = pdtSpecList.get(0).getProduct();
		for (int i = 0; i < pdtSpecList.size(); i++) {
			if (productPkey != pdtSpecList.get(i).getProduct()) {
				json.put("data", "-1");
				write(json.toString());
				return;
			}
		}
		json.put("data", "1");
		json.put("item", strSpec);
		write(json.toString());
		return;
	}
	public Map<String, Object> getMapData() {
		return MapData;
	}

	public void setMapData(Map<String, Object> mapData) {
		MapData = mapData;
	}

	public String getSingleSpecNum() {
		return singleSpecNum;
	}

	public void setSingleSpecNum(String singleSpecNum) {
		this.singleSpecNum = singleSpecNum;
	}

	public String getStrSpec() {
		return strSpec;
	}

	public void setStrSpec(String strSpec) {
		this.strSpec = strSpec;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getSupplierNum() {
		return supplierNum;
	}

	public void setSupplierNum(String supplierNum) {
		this.supplierNum = supplierNum;
	}

	/**
	 * xiayan app点击提交购物车 cartStr ：购物车pkey字符串 以逗号分隔
	 *
	 * @return
	 * @throws Exception
	 */
	public String cartToDeliveryInformation() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String[] cartArray = cartStr.split(",");
		List<UsrCart> pitchOnCart = new ArrayList<>();
		List<UsrCart> pitchOnCartPRM = new ArrayList<>();
		for (int i = 0; i < cartArray.length; i++) {
			UsrCart loadCart = UsrCart.load(UsrCart.class, Integer.parseInt(cartArray[i]));
			PdtProduct gtProduct = loadCart.gtSpec().gtProduct();
			List<PrmGroupPurchaseLine> list = BeanBase.list(PrmGroupPurchaseLine.class,
					PrmGroupPurchaseLine.T.PRODUCT + " = " + gtProduct.getPkey(), false);
			if (list == null || list.size() <= 0) {
				setResult("/");
				return TRENDS;
			}

			if (Integer.valueOf(loadCart.gtSpec().gtProduct().getProductType()).equals(Integer.valueOf(Pdt.OProductType.GROUP.getLine().getKey()))) {
				pitchOnCart.add(loadCart);
			} else {
				pitchOnCartPRM.add(loadCart);
			}
		}

		// String[] strSpecList = strSpec.split(",");
		// 获取用户id
		// Integer pkey = getPurchase().getPkey();
		// 查询购物车验证

		// List<UsrCart> listCart = query.listByPurchase(2);
		// 用户选中的商品
		// List<UsrCart> pitchOnCart = new ArrayList<>();
		/*
		 * for (int i = 0; i < strSpecList.length; i++) { for (int j = 0; j <
		 * listCart.size(); j++) { System.out.println(listCart.get(j).getSpec());
		 * System.out.println(strSpecList[i]); if
		 * (listCart.get(j).getSpec().toString().equals(strSpecList[i])) { UsrCart cart
		 * = listCart.get(j); pitchOnCart.add(cart); // break; } } }
		 */
		if ((pitchOnCart == null || pitchOnCart.size() == 0) && (pitchOnCartPRM == null || pitchOnCartPRM.size() == 0)) {

			setResult("/");
			return TRENDS;
		}
		data.put("UsrCart", pitchOnCart);
		data.put("UsrCartPRM", pitchOnCartPRM);
		// 查询规格
		List<PdtSpec> listSpes = new ArrayList<>();
		for (int i = 0; i < pitchOnCart.size(); i++) {
			PdtSpec pdtSpec = pitchOnCart.get(i).gtSpec();
			translateUtil.getAutoTranslate(pdtSpec, HomeAction.curLanguage());
			listSpes.add(pdtSpec);
		}
		// List<PdtSpec> listSpes = specQuery.listByCart(pitchOnCart);
		// 普通购物车
		List<ProductAndSpceView> psList = new ArrayList<>();
		for (int i = 0; i < listSpes.size(); i++) {
			ProductAndSpceView ps = new ProductAndSpceView();
			ps.setPkey(listSpes.get(i).getPkey());
			ps.setProduct(listSpes.get(i).getProduct());
			ps.setColor(listSpes.get(i).getColor());
			ps.setSize(listSpes.get(i).getSize());
			ps.setKeyName(listSpes.get(i).getKeyName());
			ps.setSku(listSpes.get(i).getSku());
			ps.setPrice(listSpes.get(i).getPrice());
			ps.setMarkup(listSpes.get(i).getMarkup());
			ps.setStoreCount(listSpes.get(i).getStoreCount());
			ps.setWeight(listSpes.get(i).getWeight());
			ps.setPics(listSpes.get(i).getPics());
			ps.setRowVersion(listSpes.get(i).getRowVersion());
			ps.setPdtProduct(translateUtil.getAutoTranslate(listSpes.get(i).gtProduct(),HomeAction.curLanguage()));
			ps.setPdtColor(translateUtil.getAutoTranslate(listSpes.get(i).gtColor(),HomeAction.curLanguage()));
			ps.setPdtSize(translateUtil.getAutoTranslate(listSpes.get(i).gtSize(),HomeAction.curLanguage()));
			psList.add(ps);
		}
		data.put("psList", psList);
		// 查询规格
		List<PdtSpec> listSpesPRM = new ArrayList<>();
		for (int i = 0; i < pitchOnCartPRM.size(); i++) {
			PdtSpec pdtSpec = pitchOnCartPRM.get(i).gtSpec();
			listSpesPRM.add(pdtSpec);
		}
		// 联合采购购物车
		List<ProductAndSpceView> psListPRM = new ArrayList<>();
		for (int i = 0; i < listSpesPRM.size(); i++) {
			ProductAndSpceView ps = new ProductAndSpceView();
			ps.setPkey(listSpesPRM.get(i).getPkey());
			ps.setProduct(listSpesPRM.get(i).getProduct());
			ps.setColor(listSpesPRM.get(i).getColor());
			ps.setSize(listSpesPRM.get(i).getSize());
			ps.setKeyName(listSpesPRM.get(i).getKeyName());
			ps.setSku(listSpesPRM.get(i).getSku());
			ps.setPrice(listSpesPRM.get(i).getPrice());
			ps.setMarkup(listSpesPRM.get(i).getMarkup());
			ps.setStoreCount(listSpesPRM.get(i).getStoreCount());
			ps.setWeight(listSpesPRM.get(i).getWeight());
			ps.setPics(listSpesPRM.get(i).getPics());
			ps.setRowVersion(listSpesPRM.get(i).getRowVersion());
			ps.setPdtProduct(translateUtil.getAutoTranslate(listSpesPRM.get(i).gtProduct(),HomeAction.curLanguage()));
			ps.setPdtColor(translateUtil.getAutoTranslate(listSpesPRM.get(i).gtColor(),HomeAction.curLanguage()));
			ps.setPdtSize(translateUtil.getAutoTranslate(listSpesPRM.get(i).gtSize(),HomeAction.curLanguage()));
			psListPRM.add(ps);
		}
		data.put("psListPRM", psListPRM);
		// 查询用户默认地址 查用户详情表
		List<UsrPurchaseLine> list = BeanBase.list(UsrPurchaseLine.class,
				UsrPurchaseLine.T.PURCHASE + " = " + 2 + "  and  " + UsrPurchaseLine.T.ISDEFAULT + " = 1 ", false);
		// List<UsrPurchase> list = page.getList();
		UsrPurchaseLine usr = new UsrPurchaseLine();
		if (list == null || list.size() <= 0) {
			data.put("usrAddress", usr);
		} else {
			usr = list.get(0);
			data.put("usrAddress", usr);
			// 查询国家
			PltCountry country = usr.gtCountry();
			data.put("Country", country);
		}
		// 查询送货方式
		List<PltFreight> freightList = BeanBase.list(PltFreight.class, "", false);
		data.put("freightList", freightList);
		int supplier =0 ;
		if(pitchOnCart == null || pitchOnCart.size() <=0) {
			supplier = pitchOnCartPRM.get(0).getSupplier();
		}else {
			supplier = pitchOnCart.get(0).getSupplier();
		}
		List<PltFreightSeller> freightSellerlist = BeanBase.list(PltFreightSeller.class,
				PltFreightSeller.T.SUPPLIER + " = " + supplier, false);

		data.put("freightSellerlist", freightSellerlist);
		for (int p = 0; p < freightList.size(); p++) {
			System.out.println(freightList.get(p).getCompany());
		}
		// System.out.println(PayType.FST.getLine().getName());
		List<String> payList = new ArrayList<>();
		// 查询支付方式
		String payType = PayType.FST.getLine().getName();
		payList.add(payType);
		data.put("payType", payType);
		MapData = data;
		setResult("/mobile/cart2.jsp");
		return TRENDS;
	}

	/**
	 * xiayan app查询相同产品的不同颜色
	 */
	public void getSpecColor() throws Exception {
		JSONObject json = new JSONObject();
		List<PdtSpec> Onelist = BeanBase.list(PdtSpec.class, PdtSpec.T.PKEY + " = " + Integer.parseInt(singleSpecNum),
				false);
		PdtSpec spec = Onelist.get(0);
		List<PdtSpec> list = BeanBase.list(PdtSpec.class,
				PdtSpec.T.PRODUCT + " = " + spec.getProduct() + " and " + PdtSpec.T.STORE_COUNT + " > 0 ", false);
		List<String> strList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getColor().equals(list.get(j).getColor())) {
					Integer s = i;
					strList.add(s.toString());
				}
			}
		}
		for (int i = 0; i < strList.size(); i++) {
			list.remove(Integer.parseInt(strList.get(i)));
			strList.remove(i);
			for (int j = 0; j < strList.size(); j++) {
				String lst = strList.get(j);
				Integer ls = Integer.parseInt(lst) + 1;
				strList.set(j, ls.toString());
			}
		}
		// list.add(spec);
		json.put("specColor", new JSONArray(list, false));
		write(json.toString());
	}

	/**
	 * xiayan app查询该商品相同颜色的所有尺寸
	 */
	public void getSpecSize() throws Exception {
		JSONObject json = new JSONObject();
		PdtSpec spec = BeanBase.load(PdtSpec.class, getSingleSpecNum());
		// 查商家
		UsrSupplier supplier = spec.gtProduct().gtSupplier();
		List<PdtSpec> list = BeanBase.list(PdtSpec.class,
				PdtSpec.T.PRODUCT + " = " + spec.getProduct() + " and "
						+ PdtSpec.T.STORE_COUNT.getFld().getCodeSqlField() + " > 0 and " + PdtSpec.T.COLOR + " = "
						+ spec.getColor() + " and " + PdtSpec.T.SIZE + " <> " + spec.getSize(),
				false);
		list.add(spec);
		json.put("specSize", new JSONArray(list, false));
		json.put("sup", crtJsonByBean(supplier));
		write(json.toString());
	}

	/**
	 * xiayan 删除购物规格车的数据
	 */
	public void deleteCart() throws Exception {
		JSONObject json = new JSONObject();
		/*
		 * List<UsrCart> cartList = BeanBase.list(UsrCart.class,UsrCart.T.PURCHASE +
		 * " = " + 2 + " and " + UsrCart.T.SPEC + " = " + singleSpecNum,false);
		 * if(cartList.size()<=0) { json.put("data", "-1"); write(json.toString()); }
		 * UsrCart usrCart = cartList.get(0); usrCart.del(); json.put("data", "1");
		 * write(json.toString());
		 */
	}

	/**
	 * xiayan 修改购物车的数据
	 *
	 * @throws JSONException
	 */
	public void updateCart() throws Exception {
		JSONObject json = new JSONObject();
		List<UsrCart> cartList = BeanBase.list(UsrCart.class,
				UsrCart.T.PURCHASE + " = " + 2 + " and " + UsrCart.T.SPEC + " = " + singleSpecNum, false);
		if (cartList.size() <= 0) {
			json.put("data", "-1");
			write(json.toString());
		}
		UsrCart usrCart = cartList.get(0);
		usrCart.setQty(qty);
		usrCart.upd();
		json.put("data", "1");
		write(json.toString());
	}

	/**
	 * xiayan APP增加购物车
	 */
	public void InsusrCart() throws Exception {
		JSONObject json = new JSONObject();
		List<UsrCart> cartList = BeanBase.list(UsrCart.class,
				UsrCart.T.PURCHASE + " = " + 2 + " and " + UsrCart.T.SPEC + " = " + singleSpecNum, false);
		if (cartList.size() > 0) {
			json.put("data", "-1");
			write(json.toString());
			return;
		}
		UsrCartDAO.InsUsrCart ins = new UsrCartDAO.InsUsrCart();
		UsrCart cart = new UsrCart();
		cart.setPurchase(2);
		cart.setSpec(Integer.parseInt(singleSpecNum));
		cart.setSupplier(Integer.parseInt(supplierNum));
		cart.setQty(qty);
		List<PdtSpec> specList = BeanBase.list(PdtSpec.class, PdtSpec.T.PKEY + " = " + Integer.parseInt(singleSpecNum),
				false);
		PdtSpec spec = specList.get(0);
		BigDecimal unitPrice = new BigDecimal(spec.getPrice().toBigInteger());
		BigDecimal Bigqty = new BigDecimal(qty.toString());
		BigDecimal amtTotal = unitPrice.multiply(Bigqty);
		cart.setAmtTotal(amtTotal);
		setBean(cart);
		ins.setB(getBean());
		ins.commit();
		json.put("data", "1");
		write(json.toString());
	}

	/**
	 * xiayan app进入购物车查询数据
	 */
//	public String getCartDate() {
//		Map<String, Object> data = new HashMap<String, Object>();
//		// 查询购物车
//		List<UsrCart> listCart = query.listByPurchase(getPurchase().getPkey());
//		data.put("listCart", listCart);
//		// 查询规格
//		List<PdtSpec> listSpes = new ArrayList<>();
//		for (int i = 0; i < listCart.size(); i++) {
//			PdtSpec pdtSpec = listCart.get(i).gtSpec();
//			listSpes.add(pdtSpec);
//		}
//		List<CartProductAndSpceView> productList = new ArrayList<>();
//		for (int i = 0; i < listSpes.size(); i++) {
//			List<PdtProduct> list = BeanBase.list(PdtProduct.class,
//					PdtProduct.T.PKEY + " = " + listSpes.get(i).getProduct(), false);
//			List<PdtColor> pdtColor = BeanBase.list(PdtColor.class,
//					PdtColor.T.PKEY + " = " + listSpes.get(i).getColor(), false);
//			List<PdtSize> pdtSize = BeanBase.list(PdtSize.class, PdtSize.T.PKEY + " = " + listSpes.get(i).getSize(),
//					false);
//			CartProductAndSpceView cp = new CartProductAndSpceView();
//			if (i != 0) {
//				boolean status = false;
//				for (int j = 0; j < productList.size(); j++) {
//					if (list.get(0).getPkey() == productList.get(j).getPdtProduct().getPkey()) {
//						productList.get(j).getSpecList().add(listSpes.get(i));
//						for (int c = 0; c < productList.get(j).getColorList().size(); c++) {
//							if (listSpes.get(i).getColor() != productList.get(j).getColorList().get(c).getPkey()) {
//								productList.get(j).getColorList().add(pdtColor.get(0));
//								break;
//							}
//						}
//						for (int s = 0; s < productList.get(j).getColorList().size(); s++) {
//							if (listSpes.get(i).getSize() != productList.get(j).getSizeList().get(s).getPkey()) {
//								productList.get(j).getSizeList().add(pdtSize.get(0));
//							} else {
//								break;
//							}
//						}
//						status = true;
//						break;
//					}
//				}
//				if (status) {
//					continue;
//				}
//			}
//			cp.setPdtProduct(list.get(0));
//			List<PdtSpec> newListSpes = new ArrayList<>();
//			newListSpes.add(listSpes.get(i));
//			cp.setSpecList(newListSpes);
//			cp.setColorList(pdtColor);
//			cp.setSizeList(pdtSize);
//			productList.add(cp);
//		}
//		List<PdtColor> pdtColorAll = BeanBase.list(PdtColor.class, "", false);
//		List<PdtSize> pdtSizeAll = BeanBase.list(PdtSize.class, "", false);
//		data.put("pdtColorAll", pdtColorAll);
//		data.put("pdtSizeAll", pdtSizeAll);
//		data.put("productList", productList);
//		MapData = data;
//		setResult("/mobile/joint-procurement-cart.jsp");
//		return TRENDS;
//	}


	private List<PdtCat> catList;
	/**
	 * 返回购物车页面
	 * @author liyichao
	 * @return
	 */
	@NeedLogin
	public String cartshopping(){
		catList = catQuery.listTopCat();
		for (PdtCat pdtCat : catList) {
            translateUtil.getAutoTranslate(pdtCat, curLanguage());
		}
		setResult("/home/groupCart.jsp");
		return HomeAction.TRENDS;
	}
	public List<PdtCat> getCatList() {
		return catList;
	}

	public void setCatList(List<PdtCat> catList) {
		this.catList = catList;
	}

	/**
	 * 购物车数据
	 * @author liyichao
	 * @updateTime 2018-8-16
	 * @throws Exception
	 */
	public void getCartData() throws Exception{
		List<UsrCart> cartListNoTranslate = query.listByPurchase(getPurchase().getPkey());
		List<UsrCart> cartList = new ArrayList<UsrCart>();
		for(UsrCart cart : cartListNoTranslate){
            translateUtil.getAutoTranslate(cart, curLanguage());
			cartList.add(cart);
		}
		JSONObject json = new JSONObject();
		json.put("suppliers",new JSONArray(irille.homeAction.usr.dto.SupplierView.build(cartList),false));
		json.put("products", new JSONArray(irille.homeAction.usr.dto.ProductView.build(cartList),false));
		json.put("color", new JSONArray(ColorView.build(cartList),false));
		json.put("spec", new JSONArray(irille.homeAction.usr.dto.SpecView.build(cartList),false));
		writerOrExport(json);
	}


	/**
	 * 处理购物车中提交的数据
	 * @author liyichao
	 * @throws Exception
	 */
	public void cartSubmit() throws Exception{
		if(getPurchase() == null){
			writeTimeout();
			return;
		}
		JSONObject json = new JSONObject();
		UsrCartDAO.SubmitCart cartDAO = new UsrCartDAO.SubmitCart();
		cartDAO.setCartPkeys(cartPkeys);
		cartDAO.commit();
		json.put("success", true);
		OdrOrderAction.getConfirmOrderMap().put(getPurchase().getPkey(), cartPkeys);
		writerOrExport(json);
	}

	private Integer pdtPkey;
	/**
	 * 查询该商品下对应的规格
	 * @throws Exception
	 */
	public void getSpecByProduct() throws Exception{
		PdtProduct pdt = BeanBase.load(PdtProduct.class, pdtPkey);
		String[] colorArr = pdt.getColorAttr().split(",");
		String[] sizeArr = pdt.getSizeAttr().split(",");

		JSONObject json = new JSONObject();
		json.put("color", new JSONArray(ColorView.buildColor(pdtPkey,colorArr),false));
		json.put("size", new JSONArray(irille.homeAction.usr.dto.SpecView.buildSize(sizeArr),false));

		writerOrExport(json);
	}

	/**
	 * @throws Exception
	 */
	public void getColorAndSize() throws Exception{
		JSONObject json = new JSONObject();
		PdtProduct pdt = BeanBase.load(PdtProduct.class, getPdtPkey());
		String colors = pdt.getColorAttr();
		String[] colorArr = colors.split(",");
		JSONObject sizeMap = null;
		List<PdtSpec> specList = BeanBase.list(PdtSpec.class,PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + pdtPkey + " AND " + PdtSpec.T.COLOR.getFld().getCodeSqlField() + " in (" + colors + ") ",false);
		sizeMap = irille.homeAction.usr.dto.SpecView.buildSize(specList);
		json.put("color", new JSONArray(ColorView.buildColor(getPdtPkey(),colorArr),false));
		json.put("size", sizeMap);

		writerOrExport(json);
	}

	private String pdtPkeys;
	public String getPdtPkeys() {
		return pdtPkeys;
	}
	public void setPdtPkeys(String pdtPkeys) {
		this.pdtPkeys = pdtPkeys;
	}

	/**
	  * 通过产品pkey删除购物车记录
	  * @author liyichao
	  * @throws IOException
	  * @throws JSONException
	  */
	public void delCartByPdt() throws IOException, JSONException{
		if((getPdtPkeys() == null || getPdtPkeys().trim().equals("")) && (getCartPkeys() == null || getCartPkeys().equals(""))){
			throw LOG.errTran("cart%checked_error","请选择至少一个产品");
		}
		List<PdtSpec> specList1 = new ArrayList<PdtSpec>();
		if(getPdtPkeys() != null && !getPdtPkeys().trim().equals("")) {
			specList1 = BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " in( "+getPdtPkeys()+" )",false);
			if(specList1 == null || specList1.size() <= 0){
				throw LOG.errTran("global%unknown_mistake","未知错误");
			}
			String specArr = "";
			for(PdtSpec spec : specList1){
				if(specArr == ""){
					specArr += spec.getPkey();
				}else{
					specArr += "," + spec.getPkey();
				}
			}
			BeanBase.executeUpdate(" DELETE FROM " + UsrCart.TB.getCodeSqlTb() + " WHERE " + UsrCart.T.SPEC.getFld().getCodeSqlField() +" in ("+specArr+") ");
		}
		if(getCartPkeys() != null && !getCartPkeys().trim().equals("")) {
			BeanBase.executeUpdate(" DELETE FROM " + UsrCart.TB.getCodeSqlTb() + " WHERE " + UsrCart.T.PKEY.getFld().getCodeSqlField() +" in ("+getCartPkeys()+") ");
		}
		write();
	}

	public void delCartBySpec() throws Exception{
		UsrCart cart = BeanBase.chk(UsrCart.class, getPkey());
		if(cart != null){
			cart.del();
		}else{
			writeErr("此商品不存在");
		}
		write();
	}

	private Integer sizePkey;
	private Integer colorPkey;
	private Integer groupPkey;
	/**
	 * 添加规格到购物车
	 * @author liyichao
	 * @throws Exception
	 */
	@NeedLogin
	public void addSpecToCart() throws Exception{
		PdtSpec spec = PdtSpec.chkUniquePdt_color_size(false, pdtPkey, colorPkey, sizePkey);
		if(spec == null){
			writeErr("没有此规格");
		}
		UsrCartDAO.AddSpecToCart addSpec = new UsrCartDAO.AddSpecToCart();
		addSpec.setSpec(spec);
		if(getQty() != null && getQty() != 0){
			addSpec.setQty(getQty());
		}
		addSpec.commit();
		write();
	}

	private List<UsrCart> specList;
	/**
	 * 将商品加入购物车
	 * 使用:传入产品pkey ->  属性名:pdtPkey
	 * @author liyichao
	 * @updatetime 2018-8-16
	 * @throws Exception
	 */
	@NeedLogin
	public void boughtPro() throws Exception {
		JSONObject json = new JSONObject();
		//联合采购商品加入购物车需要传入商品规格pkey,数量
		UsrCartDAO.AddCartList addCart = new UsrCartDAO.AddCartList();
		if(specList == null || specList.size() <= 0) {
			PdtProduct cartPdt = BeanBase.load(PdtProduct.class, getPdtPkey());
			List<PdtSpec> specList = BeanBase.list(PdtSpec.class,PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = ? " ,false,cartPdt.getPkey());
			if(specList == null || specList.size() <= 0) {
				throw LOG.errTran("goods_info%No_Spec","该商品尚未发布规格");
			}
			UsrCart existsCart = UsrCart.chkUniqueSpec_purchase(false, specList.get(0).getPkey(), getPurchase().getPkey());
			if(existsCart == null){
				UsrCart cart = new UsrCart();
				cart.setSpec(specList.get(0).getPkey());
				cart.setQty(cartPdt.getMinOq().intValue());
				cart.setSupplier(cartPdt.getSupplier());
				cart.setPurchase(getPurchase().getPkey());
				cart.setAmtTotal(specList.get(0).getPrice().multiply(new BigDecimal(cart.getQty())));
				addCart.setB(cart);
				addCart.commit();
			}else{
				existsCart.setQty(existsCart.getQty()+cartPdt.getMinOq().intValue());
				existsCart.setAmtTotal(existsCart.getAmtTotal().add(specList.get(0).getPrice().multiply(BigDecimal.valueOf(cartPdt.getMinOq()))));
				existsCart.upd();
			}
		}else {
			addCart.setCartList(specList);
			addCart.commit();
		}
		json.put("newQty", addCart.getNewCount());
		json.put("success",true);
		json.put("catPkeys", addCart.pkeys);
		writeSuccess(json);
	}


	/**
	 * 修改购物车中商品数量
	 * @return
	 */
	public void updateQty(){
		UsrCartDAO.UpdateQty updateQty = new UsrCartDAO.UpdateQty();
		updateQty.setQty(getQty());
		updateQty.setBKey(getPkey());
		updateQty.commit();
	}
	private String jsonCarts;
	private String pid;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getJsonCarts() {
		return jsonCarts;
	}

	public void setJsonCarts(String jsonCarts) {
		this.jsonCarts = jsonCarts;
	}

	@NeedLogin
	public void judgeBuyNow() throws IOException{
		try {
			UsrCartDAO.judgeBuyNow(jsonCarts);
			OdrOrderAction.getConfirmOrderMap().put(getPurchase().getPkey(), jsonCarts);
			write();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exp e){
			writeErr(e.getLastMessage());
		}
	}
	/**
	 * 立即购买
	 * chenlili
	 * @return
	 * @throws JSONException
	 */
	public String buyNow() throws JSONException {
		if(getPurchase() == null){
			setResult("/home/usr_UsrPurchase_sign?&jumpUrl=/home/pdt_PdtProduct_gtProductsInfo?id=" + pid, false);
			return RTRENDS;
		}
		List<UsrPurchaseLine> listUsrPurchaseLine = BeanBase.list(UsrPurchaseLine.class,UsrPurchaseLine.T.PURCHASE.getFld().getCodeSqlField()+"=? AND " + UsrPurchaseLine.T.ADDRSSTYPE.getFld().getCodeSqlField()+" = ?",false,getPurchase().getPkey(),Usr.OAddress.COMMON.getLine().getKey());
		List<PltCountry> listPltCountry=new  ArrayList<PltCountry>();
		List<PltProvince> listProvince=new  ArrayList<PltProvince>();
		 for (int i = 0; i < listUsrPurchaseLine.size(); i++) {
           listPltCountry.add(listUsrPurchaseLine.get(i).gtCountry());
           listProvince.add(listUsrPurchaseLine.get(i).gtRegion());
           if (listUsrPurchaseLine.get(i).getIsdefault().intValue() == 1) {
               listUsrPurchaseLine.add(0, listUsrPurchaseLine.remove(i));
               listPltCountry.add(0, listPltCountry.remove(i));
               listProvince.add(0,listProvince.remove(i));
           }
       }
		 List<UsrPurchaseLine> billAddressList = UsrPurchaseLineDAO.listByPurchaseAddrsstype(getPurchase().getPkey(), OAddress.BILLED);//BeanBase.list(UsrPurchaseLine.class, UsrPurchaseLine.T.PURCHASE.getFld().getCodeSqlField() + " =? AND " + UsrPurchaseLine.T.ADDRSSTYPE.getFld().getCodeSqlField() + " = ? ",false,HomeAction.getPurchase().getPkey(),Usr.OAddress.BILLED.getLine().getKey());
			AddressView billAddress = null;
			if(billAddressList != null && billAddressList.size() > 0){
				billAddress = AddressView.buildByPurchaseLine(billAddressList.get(0));
			}
		 translateUtil.getAutoTranslateList(listPltCountry, HomeAction.curLanguage());
		 translateUtil.getAutoTranslateList(listProvince, HomeAction.curLanguage());
		List<PltCountry> countries = BeanBase.list(PltCountry.class, null,false);
		Map<Integer,List<PltProvince>> provinceMap = new HashMap<Integer,List<PltProvince>>();
		List<PltProvince> provinceList = BeanBase.list(PltProvince.class, null ,false);
		for(PltProvince p : provinceList){
			if(provinceMap.get(p.getMain()) == null){
				List<PltProvince> proList = new ArrayList<PltProvince>();
				proList.add(p);
				provinceMap.put(p.getMain(), proList);
			}else{
				List<PltProvince> proList = provinceMap.get(p.getMain());
				proList.add(p);
			}
		}
		translateUtil.getAutoTranslateList(countries, HomeAction.curLanguage());
		translateUtil.getAutoTranslateMap(provinceMap, HomeAction.curLanguage());
		List<UsrCart> carts = new ArrayList<UsrCart>();
		JSONObject js=new JSONObject(jsonCarts);
		Iterator it=js.keys();
		while (it.hasNext()) {
			 //获取map的key
            String key = (String) it.next();
            //得到value的值
            Integer value = (Integer) js.get(key);
            UsrCart uc=new UsrCart();
			uc.setSpec(Integer.valueOf(key));
			uc.setQty(value);
			uc.setSupplier(uc.gtSpec().gtProduct().getSupplier());
			uc.setAmtTotal(uc.gtSpec().getPrice().multiply(new BigDecimal(uc.getQty())));
			carts.add(uc);
		}
		UsrSupplier supplier = carts.get(0).gtSupplier();
		List<PltPay> payList = BeanBase.list(PltPay.class,PltPay.T.SUPPLIER.getFld().getCodeSqlField() + " = ? AND " + PltPay.T.ENABLED.getFld().getCodeSqlField() + " = ? ",false,supplier.getPkey(),Sys.OEnabled.TRUE.getLine().getKey());
		List<PltPayView> payViewList = PltPayView.build(payList);
		Integer totalQty = 0;
		BigDecimal totalAmt = BigDecimal.ZERO;
		for(UsrCart cart : carts){
			for(UsrCart cart2 : carts){
				if(cart.getPurchase() != cart2.getPurchase()){
					throw LOG.errTran("global%unknown_mistake","未知错误");
				}
			}
			totalQty += cart.getQty();
			totalAmt = totalAmt.add(cart.getAmtTotal());
		}
		List<PltFreightSeller> expressList = BeanBase.list(PltFreightSeller.class,PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField() + " =? AND " + PltFreightSeller.T.ENABLED.getFld().getCodeSqlField() + " = ? ",false,supplier.getPkey(),Sys.OEnabled.TRUE.getLine().getKey());
		List<irille.homeAction.usr.dto.ProductView> pdtViews = irille.homeAction.usr.dto.ProductView.build(carts);
		Map<Integer,List<ColorView>> colorViews = ColorView.buildByCart(carts);
		Map<Integer,List<SpecStaView>> specViews = SpecStaView.buildByCart(carts);
		dataMap.put("express", expressList);
		dataMap.put("billAddress", billAddress);
		dataMap.put("payMethod", payViewList);
		dataMap.put("express", expressList);
		dataMap.put("address", listUsrPurchaseLine);
		dataMap.put("country", countries);
		dataMap.put("province", provinceMap);
		dataMap.put("supplier", supplier);
		dataMap.put("product", pdtViews);
		dataMap.put("color", colorViews);
		dataMap.put("specViews", specViews);
		dataMap.put("totalQty", totalQty);
		dataMap.put("totalAmt", totalAmt);
		dataMap.put("myCountry", listPltCountry);
		dataMap.put("myProvince", listProvince);
        dataMap.put("state",1);
        dataMap.put("qtyAndSpec", jsonCarts);
        setResult("/home/buyNow.jsp");
        return TRENDS;
	}

	public Integer getGroupPkey() {
		return groupPkey;
	}

	public void setGroupPkey(Integer groupPkey) {
		this.groupPkey = groupPkey;
	}

	public Integer getSizePkey() {
		return sizePkey;
	}

	public void setSizePkey(Integer sizePkey) {
		this.sizePkey = sizePkey;
	}

	public Integer getColorPkey() {
		return colorPkey;
	}

	public void setColorPkey(Integer colorPkey) {
		this.colorPkey = colorPkey;
	}


	public Integer getPdtPkey() {
		return pdtPkey;
	}

	public void setPdtPkey(Integer pdtPkey) {
		this.pdtPkey = pdtPkey;
	}

	public List<UsrCart> getSpecList() {
		return specList;
	}

	public void setSpecList(List<UsrCart> specList) {
		this.specList = specList;
	}
}
