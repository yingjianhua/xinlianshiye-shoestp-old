package irille.shop.o2o;

import irille.Entity.O2O.Enums.O2O_BuyerType;
import irille.Entity.O2O.Enums.O2O_ExhibitionCountry;
import irille.Entity.O2O.Enums.O2O_Marketing;
import irille.Entity.O2O.Enums.O2O_Sex;
import irille.Entity.O2O.O2oRegistration;
import irille.platform.o2o.View.O2o_RegistrationView;
import irille.pub.LogMessage;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtCatDAO;
import irille.view.Page;

import java.util.*;
import java.util.stream.Collectors;

public class O2O_RegistrationDao {

	public static final LogMessage LOG = new LogMessage(O2O_RegistrationDao.class);

	public  Page list(Integer gender,Integer marketing,Integer buyerType,Integer exhibitionCountry,Integer start,Integer limit) {
		SQL sql = new SQL();
		sql.SELECT(O2oRegistration.class);
		if(gender != null) {
			if(gender == 1)
				sql.WHERE(O2oRegistration.T.GENDER," =? " ,O2O_Sex.men.getLine().getKey());
			else
				sql.WHERE(O2oRegistration.T.GENDER," =? " ,O2O_Sex.women.getLine().getKey());
		}
		if(marketing != null) {
			sql.WHERE("( O2oRegistration.marketing = "+ marketing +" OR O2oRegistration.marketing LIKE '%," + marketing + "' OR O2oRegistration.marketing LIKE '%," + marketing + ",%' OR O2oRegistration.marketing LIKE '" + marketing + ",%'  )");
		}
		if(buyerType != null)
			sql.WHERE(O2oRegistration.T.BUYER_TYPE, " =? ",buyerType);
		if(exhibitionCountry != null) {
			sql.WHERE("( O2oRegistration.exhibition_country = "+ exhibitionCountry +" OR O2oRegistration.exhibition_country LIKE '%," + exhibitionCountry + "' OR O2oRegistration.exhibition_country LIKE '%," + exhibitionCountry + ",%' OR O2oRegistration.exhibition_country LIKE '" + exhibitionCountry + ",%'  )");
		}
		sql.FROM(O2oRegistration.class);
		Integer count = Query.sql(sql).queryCount();
		List<PdtCat> listAll = PdtCatDAO.listAll();
		List<O2oRegistration> queryList = Query.sql(sql.LIMIT(start, limit)).queryList(O2oRegistration.class);
		List<O2o_RegistrationView> listView = queryList.stream().map(bean -> new O2o_RegistrationView() {{
			setId(bean.getPkey());
			setFullName(bean.getFullName());
			setGender(bean.getGender()==1?"男":"女");
			setCountry(bean.gtCountry().getName());
			setEmail(bean.getEmail());
			setTelphone(bean.getTelphone());
			String[] split = bean.getFootwear().split(",");
			StringBuffer buff = new StringBuffer("");
			if(split != null && split.length > 0) {
				for(String str:split) {
					Integer i = Integer.parseInt(str);
					for(PdtCat item:listAll) {
						if(i.equals(item.getPkey())) {
							buff.append(item.getName());
							buff.append("&&&");
							break;
						}
					}
				}
			}
			setFootwear(buff.toString());
			setActivityName(bean.gtActivityId().getName());
			String[] marketing = bean.getMarketing().split(",");
			StringBuffer marketingBuff = new StringBuffer("");
			if(marketing != null && marketing.length > 0) {
				for(String item:marketing) {
					for(O2O_Marketing en:O2O_Marketing.values()) {
						if( Integer.valueOf(en.getLine().getKey()).equals(Integer.parseInt(item))) {
							marketingBuff.append(en.getLine().getName());
							marketingBuff.append(",");
						}
					}
				}
				if(marketingBuff.lastIndexOf(",")+1 == marketingBuff.length()) {
					marketingBuff.delete(marketingBuff.length()-1, marketingBuff.length());
				}
			}
			setMarketing(marketingBuff.toString());
			setBuyertype(O2O_BuyerType.values()[bean.getBuyerType()].toString());
			String[] exhibitionCountry = bean.getExhibitionCountry().split(",");
			StringBuffer exhibitionCountryBuff = new StringBuffer("");
			if(exhibitionCountry != null && exhibitionCountry.length > 0) {
				for(String item:exhibitionCountry) {
					for(O2O_ExhibitionCountry ex:O2O_ExhibitionCountry.values()) {
						if(Integer.parseInt(item) == ex.getLine().getKey()) {
							exhibitionCountryBuff.append(ex.getLine().getName());
							exhibitionCountryBuff.append(",");
						}
					}
				}
				if(exhibitionCountryBuff.lastIndexOf(",")+1 == exhibitionCountryBuff.length()) {
					exhibitionCountryBuff.delete(exhibitionCountryBuff.length()-1, exhibitionCountryBuff.length());
				}
			}
			setExhibitionCountry(exhibitionCountryBuff.toString());
			setRemarks(bean.getRemarks());
		}}).collect(Collectors.toList());
		return new Page(listView, start, limit, count);
	}

	public  void ins(O2o_RegistrationView view) {
		O2oRegistration bean = new O2oRegistration();
		bean.setActivityId(view.getActivityId());
		bean.setFullName(view.getFullName());
		bean.setGender((byte)Integer.parseInt(view.getGender()));
		bean.setCountry(Integer.parseInt(view.getCountry()));
		bean.setEmail(view.getEmail());
		bean.setTelphone(view.getTelphone());
		bean.setFootwear(view.getFootwear());
		bean.setMarketing(view.getMarketing());
		bean.setBuyerType((byte)Integer.parseInt(view.getBuyertype()));
		bean.setExhibitionCountry(view.getExhibitionCountry());
		bean.setRemarks(view.getRemarks());
		bean.setCreateTime(new Date());
		bean.ins();
	}

	public  O2o_RegistrationView info(Integer pkey) {
		List<PdtCat> listAll = PdtCatDAO.listAll();
		SQL sql = new SQL();
		sql.SELECT(O2oRegistration.class);
		sql.FROM(O2oRegistration.class);
		sql.WHERE(O2oRegistration.T.PKEY," =? ",pkey);
		O2oRegistration bean = Query.sql(sql).query(O2oRegistration.class);
		O2o_RegistrationView o2oView = new O2o_RegistrationView() {{
			setId(bean.getPkey());
			setFullName(bean.getFullName());
			setGender(bean.getGender()==1?"男":"女");
			setCountry(bean.gtCountry().getName());
			setEmail(bean.getEmail());
			setTelphone(bean.getTelphone());
			String[] split = bean.getFootwear().split(",");
			StringBuffer buff = new StringBuffer("");
			if(split != null && split.length > 0) {
				for(String str:split) {
					Integer i = Integer.parseInt(str);
					for(PdtCat item:listAll) {
						if(i == item.getPkey()) {
							buff.append(item.getName());
							buff.append("&&&");
							break;
						}
					}
				}
			}
			setFootwear(buff.toString());
			setActivityName(bean.gtActivityId().getName());
			String[] marketing = bean.getMarketing().split(",");
			StringBuffer marketingBuff = new StringBuffer("");
			if(marketing != null && marketing.length > 0) {
				for(String item:marketing) {
					for(O2O_Marketing en:O2O_Marketing.values()) {
						if(Integer.parseInt(item) == en.getLine().getKey()) {
							marketingBuff.append(en.getLine().getName());
							marketingBuff.append(",");
						}
					}
				}
				if(marketingBuff.lastIndexOf(",")+1 == marketingBuff.length()) {
					marketingBuff.delete(marketingBuff.length()-1, marketingBuff.length());
				}
			}
			setMarketing(marketingBuff.toString());
			setBuyertype(O2O_BuyerType.values()[bean.getBuyerType()].toString());
			String[] exhibitionCountry = bean.getExhibitionCountry().split(",");
			StringBuffer exhibitionCountryBuff = new StringBuffer("");
			if(exhibitionCountry != null && exhibitionCountry.length > 0) {
				for(String item:exhibitionCountry) {
					for(O2O_ExhibitionCountry ex:O2O_ExhibitionCountry.values()) {
						if(Integer.parseInt(item) == ex.getLine().getKey()) {
							exhibitionCountryBuff.append(ex.getLine().getName());
							exhibitionCountryBuff.append(",");
						}
					}
				}
				if(exhibitionCountryBuff.lastIndexOf(",")+1 == exhibitionCountryBuff.length()) {
					exhibitionCountryBuff.delete(exhibitionCountryBuff.length()-1, exhibitionCountryBuff.length());
				}
			}
			setExhibitionCountry(exhibitionCountryBuff.toString());
			setRemarks(bean.getRemarks());
		}};
		return o2oView;
	}

	public  Map<String,Object> getData(){
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> sex = new ArrayList<>();
		List<Map<String,Object>> marketing = new ArrayList<>();
		List<Map<String,Object>> buyerType = new ArrayList<>();
		List<Map<String,Object>> exhibitionCountry = new ArrayList<>();
		for(O2O_Sex item:O2O_Sex.values()) {
			Map<String,Object> sexline = new HashMap<>();
			sexline.put("id", (int)item.getLine().getKey());
			sexline.put("name",item.toString());
			sex.add(sexline);
		}
		for(O2O_Marketing item:O2O_Marketing.values()) {
			Map<String,Object> marketingline = new HashMap<>();
			marketingline.put("id", (int)item.getLine().getKey());
			marketingline.put("name",item.toString());
			marketing.add(marketingline);
		}
		for(O2O_BuyerType item:O2O_BuyerType.values()) {
			Map<String,Object> buyerTypeline = new HashMap<>();
			buyerTypeline.put("id", (int)item.getLine().getKey());
			buyerTypeline.put("name",item.toString());
			buyerType.add(buyerTypeline);
		}
		for(O2O_ExhibitionCountry item:O2O_ExhibitionCountry.values()) {
			Map<String,Object> exhibitionCountryline = new HashMap<>();
			exhibitionCountryline.put("id", (int)item.getLine().getKey());
			exhibitionCountryline.put("name",item.toString());
			exhibitionCountry.add(exhibitionCountryline);
		}
		map.put("sex", sex);
		map.put("marketing", marketing);
		map.put("buyerType", buyerType);
		map.put("exhibitionCountry", exhibitionCountry);
		return map;
	}
}
