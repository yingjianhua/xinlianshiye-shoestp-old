package irille.sellerAction.prm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import irille.homeAction.HomeAction;
import irille.pub.Exp;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.svr.Env;
import irille.sellerAction.SellerAction;
import irille.sellerAction.prm.inf.IPrmGroupPurchaseAction;
import irille.shop.odr.OdrOrderLine;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.prm.Prm;
import irille.shop.prm.PrmGroupPurchase;
import irille.shop.prm.PrmGroupPurchaseDAO;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.prm.dto.GroupPdtView;
import irille.shop.usr.Usr;

public class PrmGroupPurchaseAction extends SellerAction<PrmGroupPurchase> implements IPrmGroupPurchaseAction{
	
	private List<GroupPdtView> _listLine = new ArrayList<GroupPdtView>();
	
	public List<GroupPdtView> getListLine() {
		return _listLine;
	}

	public void setListLine(List<GroupPdtView> _listLine) {
		this._listLine = _listLine;
	}

	private String title;
	private Integer pageNumber;
	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * PrmUnion各个商家活动列表
	 * @author liyichao
	 */
	public void groupList() throws Exception {
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		// 目前过滤器的搜索，是肯定会带初始条件的
		PrmGroupPurchaseDAO.GroupList groupList = new PrmGroupPurchaseDAO.GroupList();//------->此处重写了PrmUnionDAO中的page->
		if(title != null && !title.trim().equals("")){
			groupList.setTitle(title);
		}
		if(pageNumber == null || pageNumber.intValue() == 0){
			pageNumber = 1;
		}
		groupList.setFalg(1);
		groupList.setPageNumber(pageNumber);
		groupList.setSellerPkey(SellerAction.getSupplier().getPkey());
		groupList.commit();
		List<PrmGroupPurchase> list = groupList.getGroupList();
		JSONObject lineJson = null;
		for (PrmGroupPurchase line : list) {
			long personCount = 0;
//			if(line.getStatus() == (byte)0){
//				continue;
//			}
			//获取每个活动下的总订购数及采购人数
			List<PrmGroupPurchaseLine> groupLineList = BeanBase.list(PrmGroupPurchaseLine.class, PrmGroupPurchaseLine.T.MAIN.getFld().getCodeSqlField() + " = ? ",false,line.getPkey());
			long boughtCount = 0;	//记录已订购数量
			for(PrmGroupPurchaseLine groupLine : groupLineList) {
				Integer pdtPkey = groupLine.getProduct();
				List<PdtSpec> specList = BeanBase.list(PdtSpec.class,PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = ? ",false,pdtPkey);
				String specPkey = "";
				for(PdtSpec spec : specList){
					if(specPkey.equals("")){
						specPkey += spec.getPkey();
					}else{
						specPkey += "," + spec.getPkey();
					}
				}
				List<OdrOrderLine> orderLines = BeanBase.list(OdrOrderLine.class,OdrOrderLine.T.SPEC.getFld().getCodeSqlField() + " in( ? )",false,specPkey);
				personCount += orderLines.size();
				boughtCount = boughtCount + groupLine.getBoughtCount();
			}
			lineJson = crtJsonByBean(line);
			lineJson.put("personCount", personCount);
			lineJson.put(PrmGroupPurchaseLine.T.BOUGHT_COUNT.getFld().getCode(), boughtCount);
			ja.put(lineJson);
		}
		json.put("supplier", SellerAction.getSupplier().getPkey());
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, Math.ceil((double)ja.length()/10));
		json.put("currentPage", pageNumber);
		writerOrExport(json);
	}
	
	/**
	 * 展示用户点击的联合采购相关的产品
	 * @author liyichao
	 * @throws Exception
	 */
	public void showUnionPro() throws Exception{
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		List<PrmGroupPurchaseLine> listLine = Idu.getLines(PrmGroupPurchaseLine.T.MAIN.getFld(), getPkey());
		JSONObject listLineJSON = null;
		for(PrmGroupPurchaseLine lineList : listLine){
			Integer pdtPkey = lineList.getProduct();
			List<PdtSpec> specList = BeanBase.list(PdtSpec.class,PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = ? ",false,pdtPkey);
			String specPkey = "";
			for(PdtSpec spec : specList){
				if(specPkey.equals("")){
					specPkey += spec.getPkey();
				}else{
					specPkey += "," + spec.getPkey();
				}
			}
			int flag = 0;
			PdtProduct lineProduct = lineList.gtProduct();
			if(lineProduct.getSupplier().equals(lineProduct.gtSourceProduct().getSupplier())){
				flag = 1;
			}
			List<OdrOrderLine> orderLines = BeanBase.list(OdrOrderLine.class,OdrOrderLine.T.SPEC.getFld().getCodeSqlField() + " in( ? )",false,specPkey);
			listLineJSON = crtJsonByBean(lineList);
			listLineJSON.put("flag", flag);
			JSONObject proJSON = crtJsonByBean(lineList.gtProduct());
			listLineJSON.put(PrmGroupPurchaseLine.T.PRODUCT.getFld().getCode(), proJSON);
			listLineJSON.put("personCount", orderLines.size());
			ja.put(listLineJSON);
		}
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, listLine.size());
		writerOrExport(json);
		
	}
	
	/**
	 * 展示用户点击的产品的规格(目前没用到)
	 * @throws Exception
	 */
	public void showProSpec() throws Exception{
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		List<PdtSpec> pdtProductSpecList = BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = ? ", false,getPkey());
		PdtProduct pro = BeanBase.get(PdtProduct.class, getPkey());
		JSONObject proJSON = crtJsonByBean(pro);
		JSONObject listLineJSON = null;
		for(PdtSpec pdt : pdtProductSpecList){
			listLineJSON = crtJsonByBean(pdt);
			JSONObject colorJSON = crtJsonByBean(BeanBase.get(PdtColor.class, pdt.getColor()));
			//找到同一颜色的尺寸
			JSONArray pdtSize = new JSONArray();
			for(PdtSpec pdtS : pdtProductSpecList){
				if(pdt.getColor().equals(pdtS.getColor())){
					JSONObject sizeBeanJSON = crtJsonByBean(pdtS.gtSize());
					pdtSize.put(sizeBeanJSON);
				}
			}
			JSONObject picJSON = new JSONObject(pdt.getPics());
			listLineJSON.put(PdtSpec.T.PICS.getFld().getCode(), picJSON);
			listLineJSON.put(PdtSpec.T.SIZE.getFld().getCode(), pdtSize);
			listLineJSON.put(PdtSpec.T.COLOR.getFld().getCode(), colorJSON);
			ja.put(listLineJSON);
		}
		json.put("product", proJSON);
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, pdtProductSpecList.size());
		writerOrExport(json);
	}
	
	/**
	 * PrmGroupPurchase团购活动插入操作
	 * @author liyichao
	 */
	public void ins() throws Exception {
		PrmGroupPurchaseDAO.InsGroup ins = new PrmGroupPurchaseDAO.InsGroup();
		insBefore();
		ins.setB(getBean());
		ins.setCreateBy(getSupplier().getPkey());
		ins.setLines(getListLine());
		ins.commit();
		insAfter();
		writeSuccess();
	}
	
	/**
	 * 加载单一联合采购活动详情
	 * @author liyichao
	 */
	public void load() throws Exception {
		PrmGroupPurchase union = BeanBase.get(PrmGroupPurchase.class, getPkey());
		List<PrmGroupPurchaseLine> unionLine = Idu.getLines(PrmGroupPurchaseLine.T.MAIN.getFld(), getPkey());
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		jo.put("union", crtJsonByBean(union));
		for(PrmGroupPurchaseLine p : unionLine){
			JSONObject jsonLine = crtJsonByBean(p);
			PdtProduct pro = p.gtProduct();
			PdtProduct sourcePdt = pro.gtSourceProduct();
			jsonLine.put("pro", crtJsonByBean(pro));
			jsonLine.put("sourcePro", crtJsonByBean(sourcePdt));
			ja.put(jsonLine);
		}
		jo.put("unionLine", ja);
		writerOrExport(jo);
	}

	/**
	 * PrmUnion团购活动修改操作
	 */
	public void upd() throws Exception {
		PrmGroupPurchaseDAO.Upd upd = new PrmGroupPurchaseDAO.Upd();
		upd.setB(getBean());
		upd.setLines(getListLine());
		upd.commit();
		writeSuccess();
	}
	private byte state;
	/**
	 * 下架/上架活动商品
	 * @throws IOException 
	 */
	public void underCarriage() throws IOException{
		PrmGroupPurchaseLine groupLine = BeanBase.loadAndLock(PrmGroupPurchaseLine.class, getPkey());
		PdtProduct product = groupLine.gtProduct();
		product.setState(getState());
		if(Integer.valueOf(getState()).equals(Integer.valueOf(Pdt.OState.ON.getLine().getKey()))){
			product.setSoldTimeB(Env.getSystemTime());
		}else{
			product.setSoldTimeE(Env.getSystemTime());
		}
		try {
			product.upd();
			write();
		} catch (Exp e) {
			writeErr(e.getLastMessage());
		}
	}
	
	public void logicDelete() throws IOException{
		PrmGroupPurchaseDAO.LogicDelete logic = new PrmGroupPurchaseDAO.LogicDelete();
		logic.setBKey(getPkey());
		try{
			logic.commit();
			write();
		}catch(Exp e){
			writeErr(e.getLastMessage());
		}
		
		
	}
	
	private Integer linePkey;
	public void send()throws Exception{
		PrmGroupPurchaseLine groupLine = BeanBase.load(PrmGroupPurchaseLine.class, getLinePkey());
		if(groupLine.gtProduct().getSupplier().equals(groupLine.gtProduct().gtSourceProduct().getSupplier())){
			throw LOG.err("err","无法为自己发送订单");
		}
		PdtProduct product = groupLine.gtProduct();
		List<PdtSpec> specList = BeanBase.list(PdtSpec.class,PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + product.getPkey(),false);
		String specIds = "";
		for(PdtSpec spec : specList){
			if(specIds == ""){
				specIds += spec.getPkey();
			}else{
				specIds += "," + spec.getPkey();
			}
		}
		List<OdrOrderLine> orderLineList = BeanBase.list(OdrOrderLine.class,OdrOrderLine.T.SPEC.getFld().getCodeSqlField() + " in (" + specIds + ") ",false);
		Map<PdtSpec,Integer> sta = new HashMap<PdtSpec,Integer>();
		if(null != orderLineList) {
			for(OdrOrderLine orderLine : orderLineList){
				if(sta.get(orderLine.gtSpec()) == null){
					sta.put(orderLine.gtSpec(), orderLine.getQty());
				}else{
					Integer qty = sta.get(orderLine.gtSpec());
					sta.put(orderLine.gtSpec(), qty + orderLine.getQty());
				}
			}
		}
		JSONObject jsonCarts = new JSONObject();
		for(PdtSpec key : sta.keySet()){
			if(key.getStoreCount() < 0){
				PdtProduct pro = key.gtProduct();
				if(pro.getStock().equals(0)){
					throw LOG.err(Usr.ErrMsgs.soldOut);
				}
				if(pro.getStock() < sta.get(key)){
					throw LOG.err("notEnough","商品库存不足");
				}
			}else{
				Integer stock = key.getStoreCount();
				if(stock.equals(0)){
					throw LOG.err(Usr.ErrMsgs.soldOut);
				}
				if(stock < sta.get(key)){
					throw LOG.err("notEnough","商品库存不足");
				}
			}
			jsonCarts.put(String.valueOf(key.getPkey()),sta.get(key));
		}
		JSONObject json = new JSONObject();
		if(orderLineList == null || orderLineList.size() <= 0){
			json.put("success", false);
			json.put("msg", Prm.ErrMsgs.EMPTYERR.getMsg());
		}else{
			json.put("success", true);
			json.put("supplier", SellerAction.getSupplier().getLoginName());
			json.put("jsonCarts",jsonCarts);
		}
		irille.homeAction.odr.OdrOrderAction.getConfirmOrderMap().put(HomeAction.getPurchase().getPkey(), jsonCarts.toString());
		writerOrExport(json);
		
	}
	public byte getState() {
		return state;
	}
	public void setState(byte state) {
		this.state = state;
	}
	public Integer getLinePkey() {
		return linePkey;
	}
	public void setLinePkey(Integer linePkey) {
		this.linePkey = linePkey;
	}
	
	public void copyDetail() throws IOException{
		try{
			PrmGroupPurchaseDAO.copyDetail(getSupplier().getPkey());
			write();
		}catch(Exp e){
			writeErr(e.getLastMessage());
		}
		
	}

}
