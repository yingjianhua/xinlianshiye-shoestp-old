package com.xinlianshiye.shoestp.shop.service.rfq.impl;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultService;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultRelationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQCountryView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQCurrencyView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationImageView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationThrowawayView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQSupplierView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQUnreadCountView;
import com.xinlianshiye.shoestp.shop.view.rfq.supplierConsult.RFQConsultProductView;

import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.Enums.RFQConsultPayType;
import irille.Entity.RFQ.Enums.RFQConsultRecommend;
import irille.Entity.RFQ.Enums.RFQConsultStatus;
import irille.Entity.RFQ.Enums.RFQConsultType;
import irille.Entity.RFQ.Enums.RFQConsultUnit;
import irille.Entity.RFQ.Enums.RFQConsultVerifyStatus;
import irille.homeAction.rfq.view.RFQDetailsView;
import irille.homeAction.rfq.view.RFQListView;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.GetValue;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltErate;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RFQConsultServiceImpl implements RFQConsultService {

    @Inject
    private ObjectMapper om;

    @Override
    public Page<RFQConsultView> pageMine(UsrPurchase purchase, Byte type, String keyword, Boolean unread, Integer start, Integer limit) {
        BeanQuery<?> query = Query.SELECT(RFQConsult.T.PKEY);
        query.SELECT(RFQConsult.T.TYPE);
        query.SELECT(RFQConsult.T.TITLE);
        query.SELECT(RFQConsult.T.IMAGE);
        query.SELECT(RFQConsult.T.TYPE);
        query.FROM(RFQConsult.class);
        query.LEFT_JOIN(RFQConsultRelation.class, RFQConsult.T.PKEY, RFQConsultRelation.T.CONSULT);
        if (keyword != null && !keyword.isEmpty()) {
            query.LEFT_JOIN(UsrSupplier.class, RFQConsultRelation.T.SUPPLIER_ID, UsrSupplier.T.PKEY);
            //关键字匹配询盘标题和报价供应商名称
            query
                    .WHERE(RFQConsult.T.TITLE, "like ?", "%" + keyword + "%")
                    .orWhere(UsrSupplier.T.NAME, "like ?", "%" + keyword + "%");
        }
        query.AND().WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey());
        query.WHERE(RFQConsult.T.IS_DELETED, "=?", false);
        if (type != null) {
            //询盘类型
            query.WHERE(RFQConsult.T.TYPE, "=?", type);
        }
        if (unread != null) {
            //是否有新消息
        	if(unread) {
        		query.WHERE(RFQConsultRelation.T.IS_NEW, "=?", true).orWhere(RFQConsultRelation.T.HAD_READ_PURCHASE, "=?", false);
        	}
        }
        query.GROUP_BY(RFQConsult.T.PKEY);
        query.ORDER_BY(RFQConsult.T.CREATE_TIME, "desc");
        query.limit(start, limit);
        List<RFQConsultView> result = query.queryMaps().stream().map(map -> {
            RFQConsultView consult = new RFQConsultView();
            consult.setPkey(GetValue.get(map, RFQConsult.T.PKEY, Integer.class, null));
            consult.setTitle(GetValue.get(map, RFQConsult.T.TITLE, String.class, null));
            consult.setType(GetValue.get(map, RFQConsult.T.TYPE, Byte.class, null));
            String image = GetValue.get(map, RFQConsult.T.IMAGE, String.class, null);
            consult.setImages(image == null ? new ArrayList<>() : Arrays.asList(image.split(",")));
            consult.setRelations(listRelation(consult.getPkey()));
            return consult;
        }).collect(Collectors.toList());
        Integer totalCount = query.queryCount();
        return new Page<>(result, start, limit, totalCount);
    }

    @Override
    public List<RFQConsultRelationView> listRelation(Integer consultPkey) {
        BeanQuery<?> query = Query.SELECT(RFQConsultRelation.T.PKEY);
        query.SELECT(RFQConsultRelation.T.TITLE);
        query.SELECT(RFQConsultRelation.T.IMAGE);
        query.SELECT(RFQConsultRelation.T.MINPRICE);
        query.SELECT(RFQConsultRelation.T.MAXPRICE);
        query.SELECT(RFQConsultRelation.T.CURRENCY);
        query.SELECT(RFQConsultRelation.T.SAMPLE);
        query.SELECT(RFQConsultRelation.T.CREATE_DATE);
        query.SELECT(RFQConsultRelation.T.IS_NEW);
        query.SELECT(RFQConsultRelation.T.HAD_READ_PURCHASE);
        query.SELECT(UsrSupplier.T.PKEY, "supplierPkey");
        query.SELECT(UsrSupplier.T.NAME, "supplierName");
        query.SELECT(UsrSupplier.T.COUNTRY, "supplierCountry");
        query.SELECT(PltCountry.T.SHORT_NAME, "countryShortName");
        query.SELECT(PltCountry.T.NATIONAL_FLAG, "countryFlag");
        query.SELECT(PltErate.T.PKEY, "currencyPkey");
        query.SELECT(PltErate.T.CUR_NAME, "currencyShortName");
        query.FROM(RFQConsultRelation.class);
        query.LEFT_JOIN(UsrSupplier.class, RFQConsultRelation.T.SUPPLIER_ID, UsrSupplier.T.PKEY);
        query.LEFT_JOIN(PltCountry.class, UsrSupplier.T.COUNTRY, PltCountry.T.PKEY);
        query.LEFT_JOIN(PltErate.class, RFQConsultRelation.T.CURRENCY, PltErate.T.PKEY);
        query.WHERE(RFQConsultRelation.T.CONSULT, "=?", consultPkey);
        query.WHERE(RFQConsultRelation.T.IS_DELETED_PURCHASE, "=?", false);

        List<RFQConsultRelationView> result = query.queryMaps().stream().map(map -> {
            RFQConsultRelationView relation = new RFQConsultRelationView();
            RFQQuotationView quotation = new RFQQuotationView();
            quotation.setPkey(GetValue.get(map, RFQConsultRelation.T.PKEY, Integer.class, null));
            quotation.setTitle(GetValue.get(map, RFQConsultRelation.T.TITLE, String.class, null));
            quotation.setDescription(GetValue.get(map, RFQConsultRelation.T.DESCRIPTION, String.class, null));
            try {
            	String image = GetValue.get(map, RFQConsultRelation.T.IMAGE, String.class, null);
            	if(image != null && !image.isEmpty()) {
            		quotation.setImages(om.readValue(image, new TypeReference<List<RFQQuotationImageView>>() { }));
            	}
			} catch (IOException e) {
				log.warn("RFQConsultRelation主键为{}的记录的image字段格式有问题", quotation.getPkey());
			}
            quotation.setMinPrice(GetValue.get(map, RFQConsultRelation.T.MINPRICE, Integer.class, null));
            quotation.setMaxPrice(GetValue.get(map, RFQConsultRelation.T.MAXPRICE, Integer.class, null));
            RFQCurrencyView currency = new RFQCurrencyView();
            currency.setPkey(GetValue.get(map, "currencyPkey", Integer.class, null));
            currency.setShortName(GetValue.get(map, "currencyShortName", String.class, null));
            quotation.setCurrency(currency);
            quotation.setCreateDate(GetValue.get(map, RFQConsultRelation.T.CREATE_DATE, Date.class, null));
            quotation.setSample(BeanBase.byteToBoolean(GetValue.get(map, RFQConsultRelation.T.SAMPLE, Byte.class, null)));
            quotation.setIsNew(BeanBase.byteToBoolean(GetValue.get(map, RFQConsultRelation.T.IS_NEW, Byte.class, null)));
            RFQSupplierView supplier = new RFQSupplierView();
            supplier.setPkey(GetValue.get(map, "supplierPkey", Integer.class, null));
            supplier.setName(GetValue.get(map, "supplierName", String.class, null));
            RFQCountryView country = new RFQCountryView();
            country.setPkey(GetValue.get(map, "supplierCountry", Integer.class, null));
            country.setShortName(GetValue.get(map, "countryShortName", String.class, null));
            country.setFlag(GetValue.get(map, "countryFlag", String.class, null));
            supplier.setCountry(country);
            relation.setUnread(BeanBase.byteToBoolean(GetValue.get(map, RFQConsultRelation.T.HAD_READ_PURCHASE, Byte.class, null)));
            relation.setQuotation(quotation);
            relation.setSupplier(supplier);
            return relation;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public RFQQuotationView getQuotation(UsrPurchase purchase, Integer relationPkey) {
        BeanQuery<RFQConsultRelation> query = Query
                .selectFrom(RFQConsultRelation.class)
                .WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchase.getPkey())
                .WHERE(RFQConsultRelation.T.IS_DELETED_PURCHASE, "=?", false)
                .WHERE(RFQConsultRelation.T.PKEY, "=?", relationPkey);
        RFQConsultRelation relation = query.query();
        RFQQuotationView quotation = new RFQQuotationView();
        quotation.setPkey(relation.getPkey());
        quotation.setTitle(relation.getTitle());
        try {
        	if(relation.getImage() != null && !relation.getImage().isEmpty()) { 
        		quotation.setImages(om.readValue(relation.getImage(), new TypeReference<List<RFQQuotationImageView>>() { }));
        	}
		} catch (IOException e) {
			log.warn("RFQConsultRelation主键为{}的记录的image字段格式有问题", relationPkey);
		}
        quotation.setDescription(relation.getDescription());
        quotation.setQuantity(relation.getQuantity());
        quotation.setUnit(relation.gtUnit().getLine().getName());
        quotation.setMinPrice(relation.getMinprice());
        quotation.setMaxPrice(relation.getMaxprice());
        PltErate erate = relation.gtCurrency();
        RFQCurrencyView currency = new RFQCurrencyView();
        currency.setPkey(erate.getPkey());
        currency.setShortName(erate.getCurName());
        quotation.setCurrency(currency);
        quotation.setValidDate(relation.getValidDate());
        quotation.setPaymentTerms(relation.gtPaytype().getLine().getName());
        quotation.setShippingTerms(relation.gtTransittype().getLine().getName());
        quotation.setSample(relation.gtSample());
        quotation.setCompanyProfile(relation.getCompanydescribe());
        quotation.setCreateDate(relation.getCreateDate());
        try {
            quotation.setThrowaways(om.readValue(relation.getThrowaway(), new TypeReference<List<RFQQuotationThrowawayView>>() {
            }));
        } catch (IOException e) {
            log.warn("RFQConsultRelation表主键为{}的记录 字段throwaway格式错误", relation.getPkey());
        }
        return quotation;
    }

    @Override
    public List<RFQListView> getRFQList() {
        SQL sql = new SQL() {
            {
                SELECT(RFQConsult.class)
                        .SELECT(PltCountry.T.NAME)
                        .SELECT(PltCountry.T.NATIONAL_FLAG)
                        .FROM(RFQConsult.class)
                        .WHERE(RFQConsult.T.TYPE, "=?", RFQConsultType.RFQ)
                        .WHERE(RFQConsult.T.STATUS, "=?", RFQConsultStatus.runing)
                        .WHERE(RFQConsult.T.VERIFY_STATUS, "=?", RFQConsultVerifyStatus.PASS)
                        .WHERE(RFQConsult.T.VALID_DATE, ">?", LocalDateTime.now())
                        .WHERE(RFQConsult.T.RECOMMEND, "=?", RFQConsultRecommend.RECOMMENDED)
                        .LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, RFQConsult.T.COUNTRY)
                        .ORDER_BY(RFQConsult.T.CREATE_TIME, "desc")
                        .LIMIT(0, 6);
            }
        };
        List<RFQListView> list = Query.sql(sql).queryMaps().stream().map(o -> new RFQListView() {
            {
                setPkey((Integer) o.get(RFQConsult.T.PKEY.getFld().getCodeSqlField()));
                setCountry((String) o.get(PltCountry.T.NAME.getFld().getCodeSqlField()));
                setCountryLogo((String) o.get(PltCountry.T.NATIONAL_FLAG.getFld().getCodeSqlField()));
                setTitle((String) o.get(RFQConsult.T.TITLE.getFld().getCodeSqlField()));
                setTime((Date) o.get(RFQConsult.T.VALID_DATE.getFld().getCodeSqlField()));
                setQuantity((Integer) o.get(RFQConsult.T.QUANTITY.getFld().getCodeSqlField()));
                for (RFQConsultUnit value : RFQConsultUnit.values()) {
                    if (value.getLine().getKey() == (Byte) o.get(RFQConsult.T.UNIT.getFld().getCodeSqlField())) {
                        setCurrency(value.getLine().getName());
                    }
                }
            }
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public RFQDetailsView getRFQDetails(Integer pkey) {
        SQL userSql = new SQL() {{
            SELECT(UsrPurchase.class)
                    .SELECT(PltCountry.T.NAME, "country")
                    .SELECT(PltCountry.T.NATIONAL_FLAG)
                    .FROM(UsrPurchase.class)
                    .LEFT_JOIN(RFQConsult.class, RFQConsult.T.PURCHASE_ID, UsrPurchase.T.PKEY)
                    .LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, UsrPurchase.T.COUNTRY)
                    .WHERE(RFQConsult.T.PKEY, "=?", pkey);
        }};
        SQL rfqSql = new SQL() {{
            SELECT(RFQConsult.class)
                    .SELECT(PltCountry.T.NAME)
                    .FROM(RFQConsult.class)
                    .LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, RFQConsult.T.COUNTRY)
                    .WHERE(RFQConsult.T.PKEY, "=?", pkey);
        }};
        RFQDetailsView view = new RFQDetailsView();
        Map<String, Object> userMap = Query.sql(userSql).queryMap();
        view.setUserName((String) userMap.get(UsrPurchase.T.NAME.getFld().getCodeSqlField()));
        view.setUserCountry((String) userMap.get("country"));
        view.setUserCountryImage((String) userMap.get(PltCountry.T.NATIONAL_FLAG.getFld().getCodeSqlField()));
        Map<String, Object> rfqMap = Query.sql(rfqSql).queryMap();
        view.setPkey((Integer) rfqMap.get(RFQConsult.T.PKEY.getFld().getCodeSqlField()));
        view.setTitle((String) rfqMap.get(RFQConsult.T.TITLE.getFld().getCodeSqlField()));
        view.setTime((Date) rfqMap.get(RFQConsult.T.CREATE_TIME.getFld().getCodeSqlField()));
        view.setRfqCountry((String) rfqMap.get(PltCountry.T.NAME.getFld().getCodeSqlField()));
        view.setPdtImages((String) rfqMap.get(RFQConsult.T.IMAGE.getFld().getCodeSqlField()));
        view.setPdtDetails((String) rfqMap.get(RFQConsult.T.CONTENT.getFld().getCodeSqlField()));
        view.setQuantitys((Integer) rfqMap.get(RFQConsult.T.QUANTITY.getFld().getCodeSqlField()));
        for (RFQConsultPayType value : RFQConsultPayType.values()) {
            if (value.getLine().getKey() == (Byte) rfqMap.get(RFQConsult.T.PAY_TYPE.getFld().getCodeSqlField())) {
                view.setPayMethod(value.getLine().getName());
            }
        }
        return view;
    }

    @Override
    public void deleteQuotation(UsrPurchase purchase, Integer relationPkey) {
        BeanQuery<RFQConsultRelation> query = Query.selectFrom(RFQConsultRelation.class);
        query.WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchase.getPkey());
        query.WHERE(RFQConsultRelation.T.PKEY, "=?", relationPkey);
        RFQConsultRelation relation = query.query();
        if(relation == null || relation.gtIsDeletedPurchase())
        	return;
        RFQConsult consult = relation.gtConsult();
        if(consult.gtType() == RFQConsultType.RFQ) {
        	//RFQ 只需要将当前relation标记为已删除就行了, 不需要对consult做修改
	        relation.stIsDeletedPurchase(true);
	        relation.upd();
        } else {
        	//其它类型的询盘, 因为询盘和relation是一对一的关系, 所以在删除relation的同时 也删除询盘
        	consult.stIsDeleted(true);
        	consult.upd();
        }
    }

    @Override
    public RFQConsultView getDetail(UsrPurchase purchase, Integer consultPkey) {
        BeanQuery<RFQConsult> query = Query.selectFrom(RFQConsult.class);
        query.WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey());
        query.WHERE(RFQConsult.T.PKEY, "=?", consultPkey);
        RFQConsult consult = query.query();
        if (consult == null) {
            throw new WebMessageException(ReturnCode.service_gone, "询盘不存在");
        }	
        RFQConsultView view = new RFQConsultView();
        view.setPkey(consult.getPkey());
        view.setTitle(consult.getTitle());
        view.setType(consult.getType());
        view.setDetail(consult.getContent());
        view.setQuantity(consult.getQuantity());
        view.setPrice(consult.getPrice());
        view.setUnit(consult.getUnit() == null? null : consult.gtUnit().getLine().getName());
        view.setType(consult.getType());
        view.setValieDate(consult.getValidDate());
        view.setPaymentTerms(consult.getPayType() == null? null : consult.gtPayType().getLine().getName());
        view.setShippingTerms(consult.gtShippingType() == null? null : consult.gtShippingType().getLine().getName());
        view.setVerifyStatus(consult.getVerifyStatus());
        view.setStatus(consult.getStatus());
        if (consult.gtType() == RFQConsultType.supplier_INQUIRY) {
            view.setExtraRequest(consult.getExtraRequest());
            try {
                if (consult.getProductRequest() != null && !consult.getProductRequest().isEmpty()) {
                    view.setProductRequest(om.readValue(consult.getProductRequest(), new TypeReference<List<RFQConsultProductView>>() {
                    }));
                }
            } catch (IOException e) {
                log.warn("RFQConsult表主键为{}的记录 字段productRequest格式错误", consult.getPkey());
            }
        }
        view.setImages(consult.getImage() == null ? new ArrayList<>() : Arrays.asList(consult.getImage().split(",")));
        return view;
    }

    @Override
    public void addMoreInformation(UsrPurchase purchase, Integer consultPkey, String information, Date validDate) {
        RFQConsult consult = Query.selectFrom(RFQConsult.class).WHERE(RFQConsult.T.PKEY, "=?", consultPkey).WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey()).query();
        if (consult == null) {
            throw new WebMessageException(ReturnCode.service_gone, "数据不存在");
        }
        if (consult.gtType() != RFQConsultType.RFQ) {
            //只有RFQ询盘能添加额外信息
            throw new WebMessageException(ReturnCode.service_state_error, "数据错误");
        }
        if (consult.gtStatus() != RFQConsultStatus.ready && consult.gtStatus() != RFQConsultStatus.runing) {
            //只有待发布和进行中能添加额外信息
            throw new WebMessageException(ReturnCode.service_state_error, "状态错误");
        }
        if (consult.getChangeCount() >= 3) {
            //最多修改三次
            throw new WebMessageException(ReturnCode.service_state_error, "最多修改三次");
        }
        if (consult.getValidDate().before(new Date())) {
            //已经过期
            throw new WebMessageException(ReturnCode.service_state_error, "已过期");
        }
        if (Query.selectFrom(RFQConsultRelation.class)
                .WHERE(RFQConsultRelation.T.CONSULT, "=?", consultPkey)
                .WHERE(RFQConsultRelation.T.IS_DELETED_PURCHASE, "=?", false)
                .WHERE(RFQConsultRelation.T.IS_DELETED_SUPPLIER, "=?", false)
                .exists()) {
            //已经有报价 不能添加额外信息
            throw new WebMessageException(ReturnCode.service_state_error, "已有报价");
        }
        if (validDate.before(new Date())) {
            throw new WebMessageException(ReturnCode.valid_illegal, "有效时间不合法");
        }
        consult.setExtraDescription(information);
        consult.setValidDate(validDate);
        consult.upd();
    }

    @Override
    public void close(UsrPurchase purchase, Integer consultPkey) {
        RFQConsult consult = Query.selectFrom(RFQConsult.class).WHERE(RFQConsult.T.PKEY, "=?", consultPkey).WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey()).query();
        if (consult == null) {
            throw new WebMessageException(ReturnCode.service_gone, "数据不存在");
        }
        if (consult.gtStatus() == RFQConsultStatus.close) {
            throw new WebMessageException(ReturnCode.service_state_error, "已关闭,不能进行操作");
        }
        consult.stStatus(RFQConsultStatus.close);
        consult.upd();
    }

    @Override
    public void addImage(UsrPurchase purchase, Integer consultPkey, String images) {
        RFQConsult consult = Query.selectFrom(RFQConsult.class).WHERE(RFQConsult.T.PKEY, "=?", consultPkey).WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey()).query();
        if (consult == null) {
            throw new WebMessageException(ReturnCode.service_gone, "数据不存在");
        }
        if (consult.gtType() != RFQConsultType.supplier_INQUIRY) {
            throw new WebMessageException(ReturnCode.service_state_error, "数据异常");
        }
        final Integer limit = 5;//图片数量上限
        Integer leftCount = 0;
        if (consult.getImage() == null || consult.getImage().isEmpty()) {
            leftCount = limit;
        } else {
            leftCount = limit - consult.getImage().split(",").length;
        }
        if (images == null || images.isEmpty()) {
            throw new WebMessageException(ReturnCode.valid_notempty, "请选择图片");
        }
        Integer length = images.split(",").length;
        if (length > leftCount) {
            //上传图片超出限制了
            throw new WebMessageException(ReturnCode.valid_notempty, "图片数量超出上限");
        }
        List<String> list = new ArrayList<>(Arrays.asList(consult.getImage().split(",")));
        list.addAll(Arrays.asList(images.split(",")));
        consult.setImage(list.stream().collect(Collectors.joining(",")));
        consult.upd();
    }

    @Override
    public void addProductRequest(UsrPurchase purchase, Integer consultPkey, String products) {
        RFQConsult consult = Query.selectFrom(RFQConsult.class).WHERE(RFQConsult.T.PKEY, "=?", consultPkey).WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey()).query();
        if (consult == null) {
            throw new WebMessageException(ReturnCode.service_gone, "数据不存在");
        }
        if (consult.gtType() != RFQConsultType.supplier_INQUIRY) {
            throw new WebMessageException(ReturnCode.service_state_error, "数据异常");
        }
        RFQConsultRelation relation = Query.selectFrom(RFQConsultRelation.class).WHERE(RFQConsultRelation.T.CONSULT, "=?", consultPkey).query();
        if (relation == null) {
            log.warn("数据异常: RFQConsult表主键为{} 的记录 缺少一个对应的RFQConsultRelation记录", consultPkey);
            throw new WebMessageException(ReturnCode.service_wrong_data, "数据异常");
        }
        if (products == null || products.isEmpty()) {
            throw new WebMessageException(ReturnCode.valid_notempty, "请选择产品");
        }

        final Integer limit = 50;

        String productRequest = consult.getProductRequest();
        Set<RFQConsultProductView> productRequestSet;
        if (productRequest == null || productRequest.isEmpty()) {
            productRequestSet = new HashSet<>();
        } else {
            try {
                productRequestSet = om.readValue(consult.getProductRequest(), new TypeReference<Set<RFQConsultProductView>>() {
                });
            } catch (IOException e) {
                productRequestSet = new HashSet<>();
            }
        }
        String[] params = products.split(",");
        if (productRequestSet.size() + params.length > limit) {
            throw new WebMessageException(ReturnCode.valid_notempty, "产品数量超出上限");
        }
        BeanQuery<PdtProduct> query = Query
                .SELECT(PdtProduct.T.PKEY)
                .SELECT(PdtProduct.T.NAME)
                .SELECT(PdtProduct.T.PICTURE)
                .FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.PKEY, SQL.getInSql(params.length), Arrays.stream(params).map(Integer::valueOf).toArray(Serializable[]::new))
                .WHERE(PdtProduct.T.SUPPLIER, "=?", relation.getSupplierId()).limit(0, 50);
        for (PdtProduct product : query.queryList()) {
            System.out.println("11111111111111");
            RFQConsultProductView view = new RFQConsultProductView();
            view.setPkey(product.getPkey());
            view.setName(product.getName());
            view.setImage(product.getPicture());
            productRequestSet.add(view);
        }
        try {
            consult.setProductRequest(om.writeValueAsString(productRequestSet));
            System.out.println(om.writeValueAsString(productRequestSet));
            consult.upd();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public RFQUnreadCountView countUnread(UsrPurchase purchase) {
        RFQUnreadCountView view = new RFQUnreadCountView();
        Query.sql(new SQL().
                SELECT("count(1)")
                .SELECT(RFQConsult.T.TYPE).FROM(RFQConsult.class)
                .LEFT_JOIN(RFQConsultRelation.class, RFQConsultRelation.T.CONSULT, RFQConsult.T.PKEY)
                .WHERE(RFQConsult.T.PURCHASE_ID, "=?", purchase.getPkey())
                .WHERE(RFQConsultRelation.T.HAD_READ_PURCHASE, "=?", false)
                .GROUP_BY(RFQConsult.T.TYPE))
                .queryMaps()
                .stream().forEach(map -> {
            Integer count = GetValue.get(map, "count(1)", Integer.class, null);
            Byte type = GetValue.get(map, RFQConsult.T.TYPE, Byte.class, null);
            switch (type) {
                case (byte) 1:
                    view.setT1(count);
                    break;
                case (byte) 2:
                    view.setT2(count);
                    break;
                case (byte) 3:
                    view.setT3(count);
                    break;
                case (byte) 4:
                    view.setT4(count);
                    break;
                default:
                    break;
            }
            view.setAll(view.getAll() + count);
        });
        return view;
    }

}
