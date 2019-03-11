package irille.homeAction.temporary;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;

import irille.Dao.PdtProductDao;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_Activity.T;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.homeAction.HomeAction;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.util.GetValue;
import irille.pub.util.SEOUtils;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrFavorites;
import irille.view.v2.Pdt.PdtNewPdtInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 临时接口Action
 * @author liyichao
 */
public class TemporaryAction extends HomeAction<O2O_Product>{
	
	private PdtProductDao productDao = new PdtProductDao();
	
	/**
	 * 随机获取O2O商品
	 */
	public void randomO2oList() throws IOException {
		SQL sql = new SQL();
        SQL childrenQuery = new SQL();
		if (null != getPurchase()) {
			childrenQuery
            .SELECT(UsrFavorites.T.PKEY)
            .FROM(UsrFavorites.class)
            .WHERE(UsrFavorites.T.PURCHASE, "=?", getPurchase().getPkey())
            .WHERE(UsrFavorites.T.PRODUCT, "=", PdtProduct.T.PKEY)
            ;
		}
		
		sql.SELECT(PdtProduct.T.PKEY,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                O2O_Product.T.PRICE,
                O2O_Product.T.MIN_OQ);
		
		if (null != getPurchase()) {
			sql.SELECT(childrenQuery, "ismyfavorite");
		}
		sql.FROM(O2O_Product.class)
        .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, O2O_Product.T.PRODUCT_ID)
        .LEFT_JOIN(O2O_Activity.class, T.PKEY, O2O_Product.T.ACTIVITY_ID)
        //TODO  活动状态为根据时间进行变化，目前先根据时间进行状态选取
        .WHERE(T.START_DATE, "<?", new Date())
        .WHERE(T.END_DATE, ">?", new Date())
//            .WHERE(T.STATUS,"=?",O2O_ActivityStatus.ACTIVITY)
        .WHERE(O2O_Product.T.VERIFY_STATUS, "=?", O2O_ProductStatus.PASS.getLine().getKey())
        .WHERE( "(" + O2O_Product.class.getSimpleName() + "." + O2O_Product.T.STATUS.getFld().getCodeSqlField() + "=? OR "+O2O_Product.class.getSimpleName() + "." + O2O_Product.T.STATUS.getFld().getCodeSqlField()+" =? )", O2O_ProductStatus.ON,O2O_ProductStatus.Failed);
//        .WHERE(O2O_Product.T.STATUS, "=?", O2O_ProductStatus.ON.getLine().getKey());
		
		
		List<Map<String, Object>> o2os  = Query.sql(sql).queryMaps();
		Map<Integer,PdtNewPdtInfo> map = new HashMap<>(getLimit());
		for(int i=0;;i++) {
			if(o2os.size()==0) {
				break;
			}
			int rand = new Random().nextInt(o2os.size());
			Map<String, Object> pdt = o2os.get(rand);
			Integer pdtPkey = GetValue.get(pdt, PdtProduct.T.PKEY, Integer.class, -1);
			if(!map.containsKey(pdtPkey)) {
				PdtNewPdtInfo item = new PdtNewPdtInfo();
	            item.setFavorite(null != GetValue.get(pdt, "ismyfavorite", Integer.class, null));
	            item.setImage(GetValue.getFirstImage(GetValue.get(pdt, PdtProduct.T.PICTURE, String.class, null)));
	            item.setId(Long.valueOf(pdtPkey));
	            item.setMin_order(GetValue.get(pdt, O2O_Product.T.MIN_OQ, Integer.class, -1));
	            item.setPrice(GetValue.get(pdt, O2O_Product.T.PRICE, BigDecimal.class, BigDecimal.ZERO));
	            String name = GetValue.get(pdt, PdtProduct.T.NAME, String.class, "");
	            item.setTitle(name);
	            item.setRewrite(SEOUtils.getPdtProductTitle(Integer.valueOf(String.valueOf(pdtPkey)), name));
	            map.put(pdtPkey, item);
			}
			if(map.size() == getLimit() || i>100) {
				break;
			}
		}
		List<PdtNewPdtInfo> result = new ArrayList<>();
		for(Entry<Integer, PdtNewPdtInfo> entry:map.entrySet()) {
			result.add(entry.getValue());
		}
		write(result);
	}
	
	@Setter
	@Getter
	private Integer cat;
	
	
	/**
	 * 随机获取普通商品
	 * @throws IOException 
	 */
	public void generalList() throws IOException {
		SQL sql = new SQL();
        if (getPurchase() != null) {
            SQL childrenQuery = new SQL();
            childrenQuery
                    .SELECT(UsrFavorites.T.PKEY)
                    .FROM(UsrFavorites.class)
                    .WHERE(UsrFavorites.T.PURCHASE, "=?", getPurchase().getPkey())
                    .WHERE(UsrFavorites.T.PRODUCT, "=", PdtProduct.T.PKEY)
            ;
            sql.SELECT(
                    childrenQuery, "isFavorite"
            );
        }
        
        sql.
        SELECT(
                PdtProduct.T.PKEY,
                PdtProduct.T.NAME,
                PdtProduct.T.CUR_PRICE,
                PdtProduct.T.PICTURE,
                PdtProduct.T.MIN_OQ
        )
        .FROM(PdtProduct.class)
        .WHERE(PdtProduct.T.PRODUCT_TYPE, " =? ", Pdt.OProductType.GENERAL.getLine().getKey());
        
        
        if(null != cat) {
        	String pkeys = productDao.getCatsNodeByCatId(getCat()).stream().map(id->{
        		return String.valueOf(id);
        	}).collect(Collectors.joining(","));
            sql.WHERE(PdtProduct.T.PKEY, " in(" + pkeys + ") ");
        }
        
        List<Map<String, Object>> pdts = Query.sql(sql).queryMaps();
        
        Map<Integer,PdtNewPdtInfo> map = new HashMap<>(getLimit());
        
        for(int i=0;;i++) {
			if(pdts.size()==0) {
				break;
			}
			int rand = new Random().nextInt(pdts.size());
			Map<String, Object> pdt = pdts.get(rand);
			Integer pdtPkey = GetValue.get(pdt, PdtProduct.T.PKEY, Integer.class, -1);
			if(!map.containsKey(pdtPkey)) {
				PdtNewPdtInfo item = new PdtNewPdtInfo();
	            item.setFavorite(null != GetValue.get(pdt, "isFavorite", Integer.class, null));
	            item.setImage(GetValue.getFirstImage(GetValue.get(pdt, PdtProduct.T.PICTURE, String.class, null)));
	            item.setId(Long.valueOf(pdtPkey));
	            item.setMin_order(GetValue.get(pdt, PdtProduct.T.MIN_OQ, Integer.class, -1));
	            item.setPrice(GetValue.get(pdt, PdtProduct.T.CUR_PRICE, BigDecimal.class, BigDecimal.ZERO));
	            String name = GetValue.get(pdt, PdtProduct.T.NAME, String.class, "");
	            item.setTitle(name);
	            item.setRewrite(SEOUtils.getPdtProductTitle(Integer.valueOf(String.valueOf(pdtPkey)), name));
	            map.put(pdtPkey, item);
			}
			if(map.size() == getLimit() || i>100) {
				break;
			}
		}
        List<PdtNewPdtInfo> result = new ArrayList<>();
		for(Entry<Integer, PdtNewPdtInfo> entry:map.entrySet()) {
			result.add(entry.getValue());
		}
		write(result);
        
        
	}
	
}
