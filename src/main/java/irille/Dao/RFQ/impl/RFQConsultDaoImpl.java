package irille.Dao.RFQ.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import irille.Dao.RFQ.RFQConsultDao;
import irille.Entity.RFQ.RFQConsult;
import irille.platform.rfq.view.CountryView;
import irille.platform.rfq.view.ProductView;
import irille.platform.rfq.view.PurchaseView;
import irille.platform.rfq.view.RFQConsultView;
import irille.platform.rfq.view.SupplierView;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierRole;
import irille.view.Page;

public class RFQConsultDaoImpl implements RFQConsultDao {
	
	/**
	 * @author Jianhua Ying
	 */
	@Override
	public Page<RFQConsultView> findAllView(Integer start, Integer limit, RFQConsultView condition) {
		BeanQuery<RFQConsult> query = createQuery();
		//询盘名称
		query.WHERE(condition.getTitle() != null, RFQConsult.T.TITLE, "like ?", "%"+condition.getTitle()+"%");
		//采购商名称
		if(condition.getPurchase()!=null&&condition.getPurchase().getName()!=null)
			query.WHERE(UsrPurchase.T.NAME, "like ?", "%"+condition.getPurchase().getName()+"%");
		//供应商名称
		if(condition.getSupplier() != null && condition.getSupplier().getName() != null)
			query.WHERE(UsrSupplier.T.NAME, "like ?", "%"+condition.getSupplier().getName()+"%");
		//产品名称
		if(condition.getProduct() != null && condition.getProduct().getName() != null)
			query.WHERE(PdtProduct.T.NAME, "like ?", "%"+condition.getProduct().getName()+"%");
		//国家
		if(condition.getCountry() != null && condition.getCountry().getName() != null)
			query.WHERE(PltCountry.T.NAME, "like ?", "%"+condition.getCountry().getName()+"%");
		//询盘类型
		if(condition.getType() != null) {
			query.WHERE(condition.getType() != null, RFQConsult.T.TYPE, "in ("+Stream.of(condition.getType().split(",")).map(i->"?").collect(Collectors.joining(","))+")", Stream.of(condition.getType().split(",")).map(Byte::new).toArray(Serializable[]::new));
		}
		//询盘的审核状态
		query.WHERE(condition.getVerifyStatus() != null, RFQConsult.T.VERIFY_STATUS, "= ?", condition.getVerifyStatus());
		query.limit(start, limit);
		return new Page<>(toView(query.queryMaps()), start, limit, query.queryCount());
	}
	
	/**
	 * @author Jianhua Ying
	 */
	@Override
	public RFQConsultView findViewById(Integer id) {
		BeanQuery<RFQConsult> query = createQuery();
		//查询额外的字段
		query.SELECT(RFQConsult.T.CREATE_TIME,
				RFQConsult.T.PRICE,
				RFQConsult.T.SHIPPING_TYPE,
				RFQConsult.T.PAY_TYPE,
				RFQConsult.T.EXTRA_DESCRIPTION,
				RFQConsult.T.IMAGE);
		Map<String, Object> map = query.queryMap();
		RFQConsultView view = toView(map);
		//添加额外的字段
		view.setCreateTime((Date)map.get(RFQConsult.T.CREATE_TIME.getFld().getCodeSqlField()));
		view.setPrice((String)map.get(RFQConsult.T.PRICE.getFld().getCodeSqlField()));
		view.setShippingType((Byte)map.get(RFQConsult.T.SHIPPING_TYPE.getFld().getCodeSqlField()));
		view.setPayType((Byte)map.get(RFQConsult.T.PAY_TYPE.getFld().getCodeSqlField()));
		view.setExtraDescription((String)map.get(RFQConsult.T.EXTRA_DESCRIPTION.getFld().getCodeSqlField()));
		view.setImage((String)map.get(RFQConsult.T.IMAGE.getFld().getCodeSqlField()));
		
		return view;
	}
	
	private BeanQuery<RFQConsult> createQuery() {
		return Query
		.SELECT(
				RFQConsult.T.PKEY,//主键
				RFQConsult.T.TITLE,//询盘标题
				RFQConsult.T.TYPE,//询盘类型
				RFQConsult.T.QUANTITY,//采购数量
				RFQConsult.T.UNIT,//询盘单位 如 双
				RFQConsult.T.TOTAL,//询盘抢单总次数
				RFQConsult.T.LEFT_COUNT,//询盘剩余抢单次数
				RFQConsult.T.VALID_DATE,//询盘过期时间
				RFQConsult.T.STATUS,//询盘状态
				RFQConsult.T.VERIFY_STATUS,//询盘审核状态
				RFQConsult.T.CREATE_TIME//发布时间
				)
		.SELECT(UsrSupplier.T.PKEY, "supplierPkey")//询盘供应商主键
		.SELECT(UsrSupplier.T.NAME, "supplierName")//询盘供应商名称
		.SELECT(UsrSupplierRole.T.PKEY, "supplierRolePkey")//询盘供应商等级主键
		.SELECT(UsrSupplierRole.T.NAME, "supplierRoleName")//询盘供应商等级名称
		.SELECT(PdtCat.T.PKEY, "productCatPkey")//询盘商品分类主键
		.SELECT(PdtCat.T.NAME, "productCatName")//询盘商品分类名称
		.SELECT(PdtProduct.T.PKEY, "productPkey")//询盘商品主键
		.SELECT(PdtProduct.T.NAME, "productName")//询盘商品名称
		.SELECT(UsrPurchase.T.PKEY, "purchasePkey")//询盘采购商主键
		.SELECT(UsrPurchase.T.NAME, "purchaseName")//询盘采购商名称
		.SELECT(PltCountry.T.PKEY, "countryPkey")//询盘国家主键
		.SELECT(PltCountry.T.NAME, "countryName")//询盘国家名称
		.FROM(RFQConsult.class)
		.LEFT_JOIN(UsrPurchase.class, RFQConsult.T.PURCHASE_ID, UsrPurchase.T.PKEY)
		.LEFT_JOIN(UsrSupplier.class, RFQConsult.T.SUPPLIER_ID, UsrSupplier.T.PKEY)
		.LEFT_JOIN(UsrSupplierRole.class, UsrSupplier.T.ROLE, UsrSupplierRole.T.PKEY)
		.LEFT_JOIN(PltCountry.class, RFQConsult.T.COUNTRY, PltCountry.T.PKEY)
		.LEFT_JOIN(PdtProduct.class, RFQConsult.T.PRODUCT, PdtProduct.T.PKEY)
		.LEFT_JOIN(PdtCat.class, PdtProduct.T.CATEGORY, PdtCat.T.PKEY);
	}
	
	private RFQConsultView toView(Map<String, Object> map) {
		RFQConsultView view = new RFQConsultView();
		view.setPkey((Integer)map.get(RFQConsult.T.PKEY.getFld().getCodeSqlField()));
		view.setTitle((String)map.get(RFQConsult.T.TITLE.getFld().getCodeSqlField()));
		view.setType(""+map.get(RFQConsult.T.TYPE.getFld().getCodeSqlField()));
		if(map.containsKey("supplierPkey")) {
			view.setSupplier(new SupplierView() {{
				setPkey((Integer)map.get("supplierPkey"));
				setName((String)map.get("supplierName"));
				if(map.containsKey("supplierRoleName")) {
					setRoleName((String)map.get("supplierRoleName"));
				}
			}});
		}
		if(map.containsKey("productPkey")) {
			view.setProduct(new ProductView() {{
				setPkey((Integer)map.get("productPkey"));
				setName((String)map.get("productName"));
				if(map.containsKey("productCatName")) {
					setCatName((String)map.get("productCatName"));
				}
			}});
		}
		if(map.containsKey("purchasePkey")) {
			view.setPurchase(new PurchaseView() {{
				setPkey((Integer)map.get("purchasePkey"));
				setName((String)map.get("purchaseName"));
			}});
		}
		if(map.containsKey("countryPkey")) {
			view.setCountry(new CountryView() {{
				setPkey((Integer)map.get("countryPkey"));
				setName((String)map.get("countryName"));
			}});
		}
		view.setQuantity((Integer)map.get(RFQConsult.T.QUANTITY.getFld().getCodeSqlField()));
		view.setUnit((Byte)map.get(RFQConsult.T.UNIT.getFld().getCodeSqlField()));
		view.setTotal((Integer)map.get(RFQConsult.T.TOTAL.getFld().getCodeSqlField()));
		view.setLeftCount((Integer)map.get(RFQConsult.T.LEFT_COUNT.getFld().getCodeSqlField()));
		view.setValidDate((Date)map.get(RFQConsult.T.VALID_DATE.getFld().getCodeSqlField()));
		view.setStatus((Byte)map.get(RFQConsult.T.STATUS.getFld().getCodeSqlField()));
		view.setVerifyStatus((Byte)map.get(RFQConsult.T.VERIFY_STATUS.getFld().getCodeSqlField()));
		view.setCreateTime((Date)map.get(RFQConsult.T.CREATE_TIME.getFld().getCodeSqlField()));
		return view;
	}
	
	private List<RFQConsultView> toView(List<Map<String, Object>> result) {
		return result.stream().map(map->{
			return toView(map);
		}).collect(Collectors.toList());
	}

	/**
	 * @author Jianhua Ying
	 */
	public static void main(String[] args) {
		RFQConsultDaoImpl dao = new RFQConsultDaoImpl();
		dao.testDirector();
	}
	/**
	 * @author Jianhua Ying
	 */
	@Test
	public void testDirector() {
		RFQConsult.TB.getCode();
		UsrPurchase.TB.getCode();
		UsrSupplier.TB.getCode();
		UsrSupplierRole.TB.getCode();
		PltCountry.TB.getCode();
		PdtProduct.TB.getCode();
		PdtCat.TB.getCode();
		
		System.out.println("==========================================================");
		testFindAll("n", "有限公司", "chen", "童鞋", "中国", "1", (byte)2);
		System.out.println("==========================================================");
		testFindById(1);
		System.out.println("==========================================================");
	}
	
	private void testFindById(Integer id) {
		System.out.println(findViewById(id));;
	}
	
	/**
	 * @author Jianhua Ying
	 */
	private void testFindAll(String title, String supplierName, String purchaseName, String productName, String countryName, String type, Byte verifyStatus) {
		RFQConsultView condition = new RFQConsultView();
		condition.setTitle(title);
		condition.setPurchase(new PurchaseView() {{
			setName(purchaseName);
		}});
		condition.setSupplier(new SupplierView() {{
			setName(supplierName);
		}});
		condition.setProduct(new ProductView() {{
			setName(productName);
		}});
		condition.setCountry(new CountryView() {{
			setName(countryName);
		}});
		
		condition.setType(type);
		condition.setVerifyStatus(verifyStatus);
		
		Page<RFQConsultView> page = findAllView(0, 10, condition);
		System.out.println("total: "+ page.getTotalCount());
		page.getItems().forEach(view->{
			System.out.println(view);
		});
	}
	
}
