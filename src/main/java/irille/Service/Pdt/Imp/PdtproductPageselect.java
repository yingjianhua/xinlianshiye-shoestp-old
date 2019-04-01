package irille.Service.Pdt.Imp;

import static irille.core.sys.Sys.OYn.YES;

import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import irille.Dao.PdtProductDao;
import irille.Service.Manage.O2O.IO2OMapServer;
import irille.Service.Pdt.IPdtProductService;
import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.pdt.dto.ProductInfoView;
import irille.homeAction.pdt.dto.SpecView;
import irille.pub.Exp;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduPage;
import irille.pub.util.AppConfig;
import irille.pub.util.SEOUtils;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtAttr;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtColorDAO;
import irille.shop.pdt.PdtCommentDAO;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.pdt.PdtSpecDAO;
import irille.shop.pdt.PdtTieredPricingDao;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrSupplier;
import irille.view.pdt.PdtProductSaveView;
import irille.view.pdt.PdtProductSpecSaveView;
import irille.view.pdt.PdtTieredPricingView;
import irille.view.pdt.PdtYouMayLikeView;

/**
 * l临时拉出来..带整合入Service Created by IntelliJ IDEA. User: lijie@shoestp.cn Date:
 * 2018/11/7 Time: 16:33
 */
public class PdtproductPageselect {
	private boolean Debug = true;
	/** * 最小库存限制，低于这个数据将不展示于首页 */
	private final int MinStockNumber = 0;

	@Inject
	PdtProductDao pdtProductDao;

	@Inject
	IPdtProductService iPdtProductServer;

	/**
	 * * 首页新品
	 *
	 * @author lijie@shoestp.cn
	 * @param
	 * @return
	 * @date 2018/7/23 14:38
	 */
	public Map getNewProducts(IduPage page) {
		FormaterSql sql = FormaterSql.build(Debug);
		sql.select(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.PICTURE, PdtProduct.T.CUR_PRICE)
				.gt(PdtProduct.T.STOCK, this.MinStockNumber)
				// .eqAutoAnd(T.IS_NEW, YES.getLine().getKey())
				.eqAutoAnd(PdtProduct.T.STATE, Pdt.OState.ON.getLine().getKey())
				.eqAutoAnd(PdtProduct.T.IS_VERIFY, YES.getLine().getKey()).page(page.getStart(), page.getLimit())
				.desc(PdtProduct.T.UPDATE_TIME).desc(PdtProduct.T.MY_ORDER);
		if (page.getWhere() != null)
			sql.eqAutoAnd(PdtProduct.T.CATEGORY, Integer.valueOf(page.getWhere()), number -> {
				return page.getWhere() != null && page.getWhere().length() > 0
						&& !page.getWhere().equalsIgnoreCase("-1");
			});
		Map result = new HashMap();
		List<Object[]> list = BeanBase.list(sql.buildSql(), sql.getParms());
		result.put("items", SetBeans.setList(list.stream().map(o -> {
			return sql.castMapAddFld(o);
		}).collect(Collectors.toList()), PdtYouMayLikeView.class));
		result.put("total", sql.castLong(BeanBase.queryOneRow(sql.buildCountSql(), sql.getParms())));
		return result;
	}

	//
	// /***
	// * 所有商品列表页
	// * 首页，根据产品分类查询产品列表的数据
	// *
	// * @author lijie@shoestp.cn
	// * @param page 分页参数 flds 排序参数 order true desc false asc category 产品分类
	// * @return
	// * @date 2018/7/23 11:31
	// */
	// public Map getProductsIndexByCategory(IduPage page, String[] flds, boolean
	// order, int
	// category, String spec, String onlyFld, String keyword) {
	// PdtProductView pdtProductView = new PdtProductView();
	// pdtProductView
	// .setPage(page)
	// .setFlds(flds)
	// .setOrder(order)
	// .setCategory(category)
	// .setSpec(spec)
	// .setKeyword(keyword)
	// ;
	// if (onlyFld != null) {
	// try {
	// pdtProductView.setOnlyFld(PdtProductView.onlyFld.valueOf(onlyFld));
	// } catch (Exception e) {
	//
	// }
	// }
	// return getProducts(pdtProductView);
	// }

	/**
	 * @Description: 获取单个商品简介, 返回字段与你可能喜欢一致, 暂用这个
	 *
	 * @author lijie@shoestp.cn
	 * @date 2018/8/16 10:55
	 */
	public PdtYouMayLikeView getProduct(int id) {
		FormaterSql sql = FormaterSql.build(this);
		sql.select(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.CUR_PRICE, PdtProduct.T.PICTURE)
				.from(PdtProduct.T.NAME).eqAutoAnd(PdtProduct.T.PKEY, id)
				.eqAutoAnd(PdtProduct.T.IS_VERIFY, Sys.OYn.YES.getLine().getKey())
				.neq(PdtProduct.T.PRODUCT_TYPE, Pdt.OProductType.GROUP);
		Object[] objects = BeanBase.queryOneRowIsNull(sql.buildSql(), sql.getParms());
		if (objects != null) {
			Map map = sql.castMapAddFld(objects);
			PdtYouMayLikeView view = SetBeans.set(map, PdtYouMayLikeView.class);
			return view;
		}
		return new PdtYouMayLikeView();
	}

	/**
	 * @Description: 返回商品平均分 AVG严重拖累性能,后期缓存
	 *
	 * @author lijie@shoestp.cn
	 * @date 2018/8/16 14:30
	 */
	public long getProductAvgById(long id) {
		return FormaterSql.castLong(
				BeanBase.queryOneRowIsNull("SELECT avg(product_satisfaction) FROM `pdt_comment` where product=?", id));
	}

	/**
	 * 获取板块名称
	 *
	 * @author lijie@shoestp.cn @Description:
	 * @date 2018/8/6 19:25
	 */
	public String getCategorieNameById(long id) {
		FormaterSql sql = FormaterSql.build(Debug);
		sql.select(PdtCat.T.NAME).eq(PdtCat.T.PKEY);
		return translateUtil.getAutoTranslate(sql.castString(BeanBase.queryOneRowIsNull(sql.buildSql(), id)),
				HomeAction.curLanguage());
	}

	/**
	 * @Description: 你可能喜欢功能 ,根据商品Id返回
	 *
	 * @author lijie@shoestp.cn
	 * @date 2018/8/14 10:57
	 */
	public List getYouMayLikeByPdtId(int id) {
		String sql = "SELECT\n" + "\tt1.pkey,t1.`name`,t1.cur_price,t1.picture \n" + "FROM\n" + "\tpdt_product AS t1\n"
				+ "\tJOIN (\n" + "SELECT\n" + "\tROUND(\n"
				+ "\tRAND( ) * ( ( SELECT MAX( pkey ) FROM `pdt_product` ) - ( SELECT MIN( pkey ) FROM `pdt_product` ) ) + ( SELECT MIN( pkey ) FROM `pdt_product` ) \n"
				+ "\t) AS pkey \n" + "\t) AS t2 \n" + "WHERE\n"
				+ "\tt1.pkey >= t2.pkey and t1.category=(select category FROM pdt_product as t3  where t3.pkey=?)\n"
				+ "         And t1.is_verify=1\n" + "ORDER BY\n" + "\tt1.pkey \n" + "\tLIMIT 5;";
		FormaterSql formaterSql = FormaterSql.build(this);
		formaterSql.select(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.CUR_PRICE, PdtProduct.T.PICTURE);
		List result = new ArrayList();
		List<Map> list = BeanBase.list(sql, id).stream().map(objects -> {
			return formaterSql.castMapAddFld(objects);
		}).collect(Collectors.toList());
		for (Map map : list) {
			result.add(SetBeans.set(map, PdtYouMayLikeView.class));
		}
		return result;
	}

	/**
	 * @Description: 获取指定分类id下面的子类
	 *
	 * @author lijie@shoestp.cn
	 * @date 2018/7/23 14:10
	 */
	public List getProductsCategorieNodesByCategory(IduPage iduPage, long category) throws JSONException {
		FormaterSql sql = FormaterSql.build(Debug);
		// 查询大类
		sql.select(PdtCat.T.PKEY, PdtCat.T.NAME).eq(PdtCat.T.CATEGORY_UP).page(iduPage);
		return sql.castListMap(BeanBase.list(sql.buildSql(), category));
	}

	/**
	 * 获取具体规格库存及版本号
	 *
	 * @author lijie@shoestp.cn @Description:获取具体规格库存及版本号
	 * @date 2018/8/8 13:50
	 */
	public Map getProductsStocksBySpecId(long pkey) {
		FormaterSql sql = FormaterSql.build(true);
		sql.select(PdtSpec.T.STORE_COUNT, PdtSpec.T.ROW_VERSION).eq(PdtSpec.T.PKEY);
		return sql.castMap(BeanBase.queryOneRow(sql.buildSql(), pkey));
	}

	/**
	 * * 获取指定分类的产品总数
	 *
	 * @return
	 */
	public Long getCatPdtCount(int category) {
		FormaterSql sql = FormaterSql.build(Debug);
		List parmList = new ArrayList();
		sql.from(PdtProduct.T.PKEY);
		if (category > -1) {
			sql.eq(PdtProduct.T.CATEGORY);
			parmList.add(category);
		}
		return sql.castLong(BeanBase.queryOneRowIsNull(sql.buildCountSql(), sql.getParms(parmList)));
	}

	/**
	 * @Description: 判断用户是否拥有该商品
	 *
	 * @author lijie@shoestp.cn
	 * @date 2018/8/18 11:59
	 */
	private boolean isFavorite(int purid, int pdtid) {
		if (purid < 1) {
			return false;
		}
		FormaterSql sql = FormaterSql.build();
		sql.from(UsrFavorites.T.PKEY).eqAutoAnd(UsrFavorites.T.PURCHASE, purid, s -> {
			return s.intValue() > 0 ? true : false;
		}).eqAutoAnd(UsrFavorites.T.PRODUCT, pdtid).eqAutoAnd(UsrFavorites.T.SHOW_STATE, 1);
		return sql.castLong(BeanBase.queryOneRowIsNull(sql.buildCountSql(), sql.getParms())) > 0 ? true : false;
	}

	/**
	 * * JSp 产品详情页 根据产品Id获取产品信息
	 *
	 * @author lijie@shoestp.cn
	 * @param
	 * @return
	 * @throws JSONException
	 * @date 2018/7/26 10:00
	 */
	public ProductInfoView getProductsById(int id, Sys.OYn is_verify, Pdt.OState state, int purId, PltErate curCurrency)
			throws JSONException {
		FormaterSql sql = FormaterSql.build(Debug);
		sql.eq(PdtProduct.T.IS_VERIFY).and().eq(PdtProduct.T.STATE).and().eq(PdtProduct.T.PKEY);
		List parmList = new ArrayList();
		parmList.add(is_verify.getLine().getKey());
		parmList.add(state.getLine().getKey());
		parmList.add(id);
		List<PdtProduct> list = BeanBase.list(PdtProduct.class, sql.toWhereString(), false, sql.getParms(parmList));
		if (!list.isEmpty()) {
			PdtProduct pdtProduct = list.get(0);
			ProductInfoView productInfoView = new ProductInfoView();
			UsrSupplier supplier = pdtProduct.gtSupplier();
			productInfoView.setSupId(supplier.getPkey());
			productInfoView.setSupName(SEOUtils
					.firstUpperCase(translateUtil.getLanguage(supplier.getShowName(), HomeAction.curLanguage())));
			productInfoView.setRewrite(SEOUtils.getPdtProductTitle(pdtProduct.getPkey(), pdtProduct.getName()));
			productInfoView.setLogo(supplier.getLogo());
			productInfoView.setStock(pdtProduct.getStock().intValue());
			// productInfoView.setQty_num(pdtProduct.getStock().intValue());
			productInfoView.setAd(supplier.getAdPhoto());
			if (supplier.getAuthTime() != null) {
				productInfoView.setAuthTime(getAuthTime(supplier));
			}
			productInfoView.setPdtId(pdtProduct.getPkey());
			productInfoView.setType(pdtProduct.getProductType());
			productInfoView.setMin_oq(pdtProduct.getMinOq());
			productInfoView.setMax_oq(pdtProduct.getMaxOq());
			productInfoView.setItemCode(pdtProduct.getCode());
			productInfoView.setPdtName(translateUtil.getLanguage(pdtProduct.getName(), HomeAction.curLanguage()));
			productInfoView.setCurrency_symbol(curCurrency.getSymbol());
			productInfoView.setCurrency_unit(curCurrency.getCurName());
			productInfoView
					.setPrice(PltErateDAO.Query.getTargetPrice(pdtProduct.getCurPrice(), curCurrency.getNowrate())
							.setScale(2, RoundingMode.HALF_UP));
			productInfoView.setHeight(pdtProduct.getHeight());
			productInfoView.setWidth(pdtProduct.getWidth());
			productInfoView.setLength(pdtProduct.getLength());
			productInfoView.setWeight(pdtProduct.getWeight());
			productInfoView
					.setDescription(translateUtil.getLanguage(pdtProduct.getDescription(), HomeAction.curLanguage())); // 转
			productInfoView.setPdtImg(pdtProduct.getPicture());
			productInfoView.setFavorite_count(pdtProduct.getFavoriteCount());
			productInfoView.setFavorite(isFavorite(purId, id));
			/** ===============O2O INFO START===============* */
			ProductInfoView o2oPdt = initO2O(pdtProduct);
			if (null != o2oPdt) {
				productInfoView.setMap(o2oPdt.getMap());
				productInfoView.setPrice(PltErateDAO.Query.getTargetPrice(o2oPdt.getPrice(), curCurrency.getNowrate())
						.setScale(2, RoundingMode.HALF_UP));
				productInfoView.setMin_oq(o2oPdt.getMin_oq());
			}
			/** ===============O2O INFO END===============* */
			IduPage page = new IduPage();
			page.setStart(1);
			page.setLimit(5);
			productInfoView.setYouMayLike(iPdtProductServer.getYouMayLike(page, pdtProduct.getCategory()));
			// 面包屑导航
			productInfoView.setBreadcrumbNav(pdtProductDao.getBreadcrumbNav(pdtProduct.getCategory()));
			String wheresql = sql.clean().eqAutoAnd(PdtSpec.T.PRODUCT, id).toWhereString();
			List<PdtSpec> pdtSpecList = BeanBase.list(PdtSpec.class, wheresql, false, sql.getParms());
			Map<String, List<SpecView>> colorspecMap = new HashMap();
			Map<String, String[]> colorImageMap = new HashMap();
			// 规格总库存
			for (PdtSpec pdtSpec : pdtSpecList) {
				try {
					List o = colorspecMap
							.get(translateUtil.getLanguage(pdtSpec.gtColor().getName(), HomeAction.curLanguage()));
					if (pdtSpec.getPrice().intValue() < 0) {
						pdtSpec.setPrice(pdtProduct.getCurPrice());
					}
					if (o == null) {
						List l = new ArrayList();
						l.add(SpecView.build_GoodsInfoView(pdtSpec, curCurrency));
						colorspecMap.put(
								translateUtil.getLanguage(pdtSpec.gtColor().getName(), HomeAction.curLanguage()), l);
						colorImageMap.put(
								translateUtil.getLanguage(pdtSpec.gtColor().getName(), HomeAction.curLanguage()),
								pdtSpec.getPics() != null
										? Arrays.asList(pdtSpec.getPics().split(",")).stream().filter(s -> {
											if (s.length() > 0)
												return true;
											return false;
										}).toArray(String[]::new)
										: new String[0]);
					} else {
						SpecView specView = SpecView.build_GoodsInfoView(pdtSpec, curCurrency);
						if (specView.getImg() != null && specView.getImg().length() > 1) {
							o.add(0, specView);
						} else {
							o.add(specView);
						}
					}
				} catch (Exp exp) {
					exp.printLastMessage();
				}
			}
			productInfoView.setStock(pdtProduct.getStock().intValue());

			for (Entry<String, List<SpecView>> entry : colorspecMap.entrySet()) {
				List<SpecView> specs = entry.getValue();
				specs = specs.stream().sorted(Comparator.comparing(SpecView::getSize, (x, y) -> {
					try {
						Integer enSizeX = Integer.valueOf(x);
						Integer enSizeY = Integer.valueOf(y);
						return enSizeX.compareTo(enSizeY);
					} catch (NumberFormatException e) {
						return 1;
					}
				})).collect(Collectors.toList());
				entry.setValue(specs);
			}

			productInfoView.setSpec(colorspecMap);
			try {
				productInfoView.setSpecJson(new ObjectMapper().writeValueAsString(colorspecMap));
				productInfoView.setColorImageJson(AppConfig.objectMapper.writeValueAsString(colorImageMap));
				productInfoView.setProductImageJson(AppConfig.objectMapper.writeValueAsString(pdtProduct.getPicture()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			Map<String, List> map = new HashMap();
			if (pdtProduct.getNormAttr() != null) {
				for (String s : pdtProduct.getNormAttr().split(",")) {
					if (s.length() > 0) {
						if (!s.equalsIgnoreCase("null")) {
							if (Integer.parseInt(s) > -1) {
								try {
									PdtAttrLine pdtAttrLine = BeanBase.chk(PdtAttrLine.class, s);
									if (pdtAttrLine == null) {
										continue;
									}
									PdtAttr attr = pdtAttrLine.gtMain();
									List o = map.get(attr.getName(HomeAction.curLanguage()));
									if (o != null) {
										o.add(pdtAttrLine.getName(HomeAction.curLanguage()));
									} else {
										List attrlineList = new ArrayList();

										attrlineList.add(translateUtil.getLanguage(pdtAttrLine.getName(),
												HomeAction.curLanguage()));
										map.put(translateUtil.getLanguage(attr.getName(), HomeAction.curLanguage()),
												attrlineList);
									}
								} catch (Exp e) {
									e.printLastMessage();
								}
							}
						}
					}
				}
			}
			productInfoView.setSpecifications(map);
			// 获取评价
			PdtCommentDAO.pageSelect pageSelect = new PdtCommentDAO.pageSelect();
			productInfoView.setCommentTotal(pageSelect.getCommentCountByProId(id));
			productInfoView.setSatisfaction(getProductAvgById(id));
			page = new IduPage();
			page.setStart(1);
			page.setLimit(10);
			productInfoView.setComment(pageSelect.getCommentListByProId(page, pdtProduct.getPkey()));
			productInfoView.setSeoTitle(pdtProduct.getSeoTitle());
			productInfoView.setSeoKeywords(pdtProduct.getSeoKeyword());
			productInfoView.setSeoDescription(pdtProduct.getSeoDescription());
			// 获取阶梯价
			List<PdtTieredPricingView> tpView = PdtTieredPricingDao.getList(pdtProduct.getPkey());
			if (tpView == null || tpView.isEmpty()) {
				productInfoView.setTpView(null);
			} else {
				productInfoView.setTpView(tpView);
			}
			// 获取3个描述模块
			List<String> desModule = new ArrayList<>();
			if (pdtProduct.getDescribeModule1() != null) {
				desModule.add(pdtProduct.getDescribeModule1(HomeAction.curLanguage()));
			}
			if (pdtProduct.getDescribeModule2() != null) {
				desModule.add(pdtProduct.getDescribeModule2(HomeAction.curLanguage()));
			}
			if (pdtProduct.getDescribeModule3() != null) {
				desModule.add(pdtProduct.getDescribeModule3(HomeAction.curLanguage()));
			}
			productInfoView.setSku(pdtProduct.getSku());
			productInfoView.setDesModule(desModule);
			//
			// productInfoView.setSeoKeywords(SEOUtils.firstUpperCase(productInfoView.getPdtName()));
			// List list1 = (List) productInfoView.getBreadcrumbNav().stream().map(o -> {
			// return ((HashMap) o).get(PdtCat.T.NAME.getFld().getCode());
			// }).collect(Collectors.toList());
			//
			// productInfoView.setSeoDescription(SEOUtils.firstUpperCase(productInfoView.getPdtName())
			// +
			// "," + String.join(",", list1));
			return productInfoView;
		}
		return null;
	}

	/** ===============O2O INFO START===============* */
	@Inject
	private IO2OMapServer io2OMapServer;

	public ProductInfoView initO2O(PdtProduct product) {
		if (product.getProductType().equals(Pdt.OProductType.O2O.getLine().getKey())) {
			return io2OMapServer.findByEarliestPdt_PkeyAnd(HomeAction.curLanguage(), product.getPkey());
		}
		return null;
	}

	/** ===============O2O INFO END===============* */
	public int getAuthTime(UsrSupplier supplier) {
		int time1 = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date authTime2 = supplier.getAuthTime();
		if (authTime2 == null)
			return 1;
		String time = formatter.format(authTime2);
		Calendar c = Calendar.getInstance();
		int y1 = Integer.parseInt(time);
		c.setTime(new Date());
		int y2 = c.get(Calendar.YEAR);
		time1 = y2 - y1 == 0 ? 1 : y2 - y1;
		return time1;
	}

	/**
	 * * 根据产品Id获取所有所属规格
	 *
	 * @author lijie@shoestp.cn
	 * @param
	 * @return
	 * @date 2018/7/26 10:19
	 */
	public List<PdtSpec> getProductsSpecByPdtId(long id) {
		FormaterSql sql = FormaterSql.build(Debug);
		sql.eq(PdtSpec.T.PRODUCT);
		return BeanBase.list(PdtSpec.class, sql.toWhereString(), false, id);
	}

	/**
	 * @Description: 面包屑导航 根据商品id
	 *
	 * @author lijie@shoestp.cn
	 * @date 2018/8/20 9:56
	 */
	public List getBreadcrumbNavByPdtId(Integer id) {
		FormaterSql sql = FormaterSql.build();
		sql.select(PdtProduct.T.CATEGORY).eqAutoAnd(PdtProduct.T.PKEY, id);
		return pdtProductDao.getBreadcrumbNav(sql.castLong(BeanBase.queryOneRowIsNull(sql.buildSql(), sql.getParms())));
	}

	/**
	 * @Description: 编辑商品, 获取商品详情
	 *
	 * @author lijie@shoestp.cn
	 * @date 2018/9/25 9:59
	 */
	public PdtProductSaveView sellerGetProductById(int id, int supId) throws JSONException {
		return sellerGetProductById(id, supId, pdtSpec -> {
			PdtProductSpecSaveView saveView = new PdtProductSpecSaveView();
			saveView.setId(pdtSpec.getPkey());
			translateUtil.getAutoTranslate(pdtSpec, PltConfigDAO.supplierLanguage(supId));
			saveView.setName(pdtSpec.getKeyName());
			saveView.setSku(pdtSpec.getSku());
			Map map1 = Maps.newLinkedHashMap();
			if (pdtSpec.getPics() != null)
				for (String s : pdtSpec.getPics().split(",")) {
					map1.put(s, s);
				}
			saveView.setPic(map1);
			saveView.setPrice(pdtSpec.getPrice().doubleValue() == -1 ? null : pdtSpec.getPrice().doubleValue());
			saveView.setStock(pdtSpec.getStoreCount() > 0 ? pdtSpec.getStoreCount() : null);
			saveView.setWeight(pdtSpec.getWeight().intValue() == -1 ? null : pdtSpec.getWeight().intValue());
			// saveView.setStock(pdtSpec.getStoreCount() > 0 ? pdtSpec.getStoreCount()
			// : 0);
			// saveView.setWeight(pdtSpec.getWeight().intValue());
			saveView.setColor(pdtSpec.getColor());
			saveView.setSize(pdtSpec.getSize());
			return saveView;
		});
	}

	/**
	 * @Description: 编辑商品, 获取商品详情 基础方法
	 *
	 * @author lijie@shoestp.cn
	 * @date 2018/9/25 9:59
	 */
	public PdtProductSaveView sellerGetProductById(int id, int supId,
			Function<PdtSpec, PdtProductSpecSaveView> function) throws JSONException {
		PdtProductSaveView view = new PdtProductSaveView();
		PdtProduct pdtProduct = PdtProduct.load(PdtProduct.class, id);
		// ToDo 采集功能权限检查
		if (supId != pdtProduct.getSupplier()) {
			view.setFrom(pdtProduct.getPkey());
			return null;
		}
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(pdtProduct.getName());
		JsonObject description = (JsonObject) new JsonParser().parse(pdtProduct.getDescription());
		String[] desModule = new String[3];
		if (pdtProduct.getDescribeModule1() != null) {
			desModule[0] = pdtProduct.getDescribeModule1();
		}
		if (pdtProduct.getDescribeModule2() != null) {
			desModule[1] = pdtProduct.getDescribeModule2();
		}
		if (pdtProduct.getDescribeModule3() != null) {
			desModule[2] = pdtProduct.getDescribeModule3();
		}
		view.setDesModule(desModule);
		translateUtil.getAutoTranslate(pdtProduct, PltConfigDAO.supplierLanguage(supId));
		Map map = new HashMap();
		for (Map.Entry<String, JsonElement> elementEntry : jsonObject.entrySet()) {
			map.put(elementEntry.getKey(), elementEntry.getValue().getAsString());
		}
		view.setPdtName(map);
		map = new HashMap();
		for (Map.Entry<String, JsonElement> elementEntry : description.entrySet()) {
			map.put(elementEntry.getKey(), elementEntry.getValue().getAsString());
		}
		view.setDescription(map);
		view.setId(pdtProduct.getPkey());
		view.setAttr(pdtProduct.getNormAttr() == null ? new ArrayList<>()
				: Stream.of(pdtProduct.getNormAttr().split(",")).map(s -> {
					if (s != null) {
						if (!s.equalsIgnoreCase("null") && s.length() > 0)
							return Integer.parseInt(s);
					}
					return -1;
				}).collect(Collectors.toList()));
		view.setProductCat(pdtProduct.getCategory());
		if (pdtProduct.gtCategoryDiy() != null && pdtProduct.gtCategoryDiy().gtEnabled()) {
			view.setSupplierCat(pdtProduct.getCategoryDiy());
		} else {
			view.setSupplierCat(-1);
		}

		String[] strings = pdtProduct.getCode().split("-");
		if (strings.length > 1) {
			view.setNumber_left(strings[0]);
			view.setNumber_right(strings[1]);
		} else {
			view.setNumber_left("TOP");
			view.setNumber_right(pdtProduct.getCode());
		}
		view.setSku(pdtProduct.getSku());
		Map pic = Maps.newLinkedHashMap();
		for (String s : pdtProduct.getPicture().split(",")) {
			pic.put(s, s);
		}
		view.setPdtPics(pic);
		view.setPrice(pdtProduct.getCurPrice().doubleValue());
		view.setMktPrice(pdtProduct.getMktPrice() == null ? 0 : pdtProduct.getMktPrice().doubleValue());
		view.setPurPrice(pdtProduct.getPurPrice() == null ? 0 : pdtProduct.getPurPrice().doubleValue());
		view.setMax_oq(pdtProduct.getMaxOq().intValue());
		view.setMin_oq(pdtProduct.getMinOq().intValue());
		view.setSpecColor(Arrays.asList(pdtProduct.getColorAttr().split(",")).stream().map(s -> {
			return Integer.valueOf(s);
		}).collect(Collectors.toSet()));
		view.setSpecSize(Arrays.asList(pdtProduct.getSizeAttr().split(",")).stream().map(s -> {
			if (s == null) {
				return null;
			}
			if (s.length() < 1)
				return -1;
			if (s.equalsIgnoreCase("null")) {
				return -1;
			}
			return Integer.valueOf(s);
		}).collect(Collectors.toSet()));
		view.setRadio(0);
		if (pdtProduct.getProductType() == Pdt.OProductType.PrivateExpo.getLine().getKey()) {
			view.setRadio(1);
		}
		view.setWarnStock(pdtProduct.getWarnStock().intValue());
		view.setState(pdtProduct.getState() == Sys.OYn.YES.getLine().getKey() ? true : false);
		view.setSoldInStatus(pdtProduct.gtSoldInTime());
		view.setSoldInTime(Arrays.asList(pdtProduct.getSoldTimeE(), pdtProduct.getSoldTimeB()));
		view.setFreeShipping(pdtProduct.gtIsFreeShipping());
		view.setWeight(pdtProduct.getWeight().intValue());
		view.setHeight(pdtProduct.getHeight().intValue());
		view.setWidth(pdtProduct.getWidth().intValue());
		view.setLength(pdtProduct.getLength().intValue());
		view.setBriefDescription(pdtProduct.getBriefDescription());
		view.setSpec(getSpec(pdtProduct.getPkey(), function));
		view.setColor(PdtColorDAO.getPdtColorList(pdtProduct.getColorAttr()));
		view.setNewSpec(PdtSpecDAO.getList(pdtProduct.getPkey()));
		List<PdtTieredPricingView> TPlist = PdtTieredPricingDao.getList(pdtProduct.getPkey());
		TPlist = TPlist.stream().sorted((t1, t2) -> {
			return t1.getCount().compareTo(t2.getCount());
		}).collect(Collectors.toList());
		view.setTieredPricing(TPlist);
		view.setSoldInStatus(pdtProduct.gtSoldInTime());
		view.setPutawayDate(pdtProduct.getSoldTimeB());
		view.setTargetedMarket(pdtProduct.getTargetedMarket());
		return view;
	}

	private List<PdtProductSpecSaveView> getSpec(int pdtId, Function<PdtSpec, PdtProductSpecSaveView> function) {
		FormaterSql sql = FormaterSql.build();
		sql.eq(PdtSpec.T.PRODUCT);
		return PdtSpec.list(PdtSpec.class, sql.toWhereString(), false, pdtId).stream().map(function)
				.collect(Collectors.toList());
	}

	/**
	 * @Description: 复制商品, 获取商品详情
	 *
	 * @author lijie@shoestp.cn
	 * @date 2018/9/25 10:00
	 */
	public PdtProductSaveView sellerCopyProductById(int id, int supId) throws JSONException {
		PdtProductSaveView result = sellerGetProductById(id, supId, pdtSpec -> {
			PdtProductSpecSaveView saveView = new PdtProductSpecSaveView();
			translateUtil.getAutoTranslate(pdtSpec, PltConfigDAO.supplierLanguage(supId));
			saveView.setName(pdtSpec.getKeyName());
			saveView.setSku(pdtSpec.getSku());
			Map map1 = Maps.newLinkedHashMap();
			if (pdtSpec.getPics() != null)
				for (String s : pdtSpec.getPics().split(",")) {
					map1.put(s, s);
				}
			saveView.setId(-1);
			saveView.setPic(map1);
			saveView.setPrice(pdtSpec.getPrice().doubleValue() == -1 ? null : pdtSpec.getPrice().doubleValue());
			saveView.setStock(pdtSpec.getStoreCount() > 0 ? pdtSpec.getStoreCount() : null);
			saveView.setWeight(pdtSpec.getWeight().intValue() == -1 ? null : pdtSpec.getWeight().intValue());
			saveView.setColor(pdtSpec.getColor());
			saveView.setSize(pdtSpec.getSize());
			return saveView;
		});
		result.setNumber_right("");
		result.setId(-1);
		result.setNumber_left("Top");

		return result;
	}
}
