package irille.Dao.RFQ.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	public Page<RFQConsultView> findAll(Integer start, Integer limit, RFQConsultView condition) {
		BeanQuery<RFQConsult> query = 
		Query
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
				RFQConsult.T.VERIFY_STATUS//询盘审核状态
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
		.LEFT_JOIN(PdtCat.class, PdtProduct.T.CATEGORY, PdtCat.T.PKEY)
		
		//询盘名称
		.WHERE(condition.getTitle() != null, RFQConsult.T.TITLE, "like ?", "%"+condition.getTitle()+"%");
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
		query.WHERE(condition.getType() != null, RFQConsult.T.TYPE, "= ?", condition.getType())
		//询盘的审核状态
		.WHERE(condition.getVerifyStatus() != null, RFQConsult.T.VERIFY_STATUS, "= ?", condition.getVerifyStatus());
		
		List<RFQConsultView> result = query.queryMaps().stream().map(map->{
			RFQConsultView view = new RFQConsultView();
			view.setPkey((Integer)map.get(RFQConsult.T.PKEY.getFld().getCodeSqlField()));
			view.setTitle((String)map.get(RFQConsult.T.TITLE.getFld().getCodeSqlField()));
			view.setType((Byte)map.get(RFQConsult.T.TYPE.getFld().getCodeSqlField()));
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
			return view;
		}).collect(Collectors.toList());
		return new Page<>(result, start, limit, query.queryCount());
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
		
		testFindAll("n", "有限公司", "chen", "童鞋", "中国", (byte)1, (byte)2);
	}
	
	/**
	 * @author Jianhua Ying
	 */
	private void testFindAll(String title, String supplierName, String purchaseName, String productName, String countryName, Byte type, Byte verifyStatus) {
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
		
		Page<RFQConsultView> page = findAll(0, 10, condition);
		System.out.println("total: "+ page.getTotalCount());
		page.getItems().forEach(view->{
			System.out.println(view);
		});
	}
	
}
