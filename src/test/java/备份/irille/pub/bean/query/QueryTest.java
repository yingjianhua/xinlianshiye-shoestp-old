package 备份.irille.pub.bean.query;

import irille.pub.bean.query.BeanQuery;
import java.util.List;

import irille.pub.Str;
import irille.pub.bean.Query;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrProductCategory;

public class QueryTest {

	@SuppressWarnings({ "static-access", "rawtypes", "unchecked", "unused" })
	public static void main(String[] args) {
		PdtProduct.TB.getCode();
		BeanQuery.config(true);
		BeanQuery q = Query.SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.CUR_PRICE);
		q.SELECT(PdtCat.T.NAME, "category");
		q.LEFT_JOIN(PdtCat.class, PdtProduct.T.CATEGORY, PdtCat.T.PKEY);
		q.SELECT(UsrProductCategory.T.NAME, "categoryDiy");
		q.FROM(PdtProduct.class);
		q.LEFT_JOIN(UsrProductCategory.class, PdtProduct.T.CATEGORY_DIY, UsrProductCategory.T.PKEY);
		q.WHERE(!Str.isEmpty(getName()), PdtProduct.T.NAME, "like ?", "%" + getName() + "%");
		q.WHERE(PdtProduct.T.SUPPLIER, "=?", getSupplier());
		q.WHERE(PdtProduct.T.PRODUCT_TYPE, "=0");
		q.WHERE(PdtProduct.T.STATE, "<>2");
		Integer totalCount = q.queryCount();
		List list = q.limit((getStart() - 1 > -1 ? getStart() - 1 : 0) * getLimit(), getStart() * getLimit()).queryMaps();
		System.out.println();
	}

	public static Integer getStart() {
		return 1;
	}

	public static Integer getLimit() {
		return 5;
	}

	public static Integer getSupplier() {
		return 56;
	}

	public static String getName() {
		return "";
	}
}
