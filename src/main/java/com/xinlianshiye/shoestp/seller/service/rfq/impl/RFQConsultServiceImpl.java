package com.xinlianshiye.shoestp.seller.service.rfq.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.seller.service.rfq.RFQConsultService;

import irille.Dao.RFQ.RFQConsultGroupDao;
import irille.Dao.RFQ.RFQConsultGroupRelationDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultGroup;
import irille.Entity.RFQ.RFQConsultGroupRelation;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.platform.rfq.view.CountryView;
import irille.platform.rfq.view.ProductView;
import irille.platform.rfq.view.PurchaseView;
import irille.platform.rfq.view.RFQConsultRelationView;
import irille.platform.rfq.view.RFQConsultView;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.GetValue;
import irille.sellerAction.rfq.view.RFQConsultQuoteInfoView;
import irille.sellerAction.rfq.view.RFQConsultRelationCountView;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

public class RFQConsultServiceImpl implements RFQConsultService {

  @Inject private RFQConsultGroupDao rFQConsultGroupDao;
  @Inject private RFQConsultGroupRelationDao rFQConsultGroupRelationDao;
  @Inject private RFQConsultRelationDao rFQConsultRelationDao;

  @Override
  public Page message(UsrSupplier supplier, UsrPurchase purchase, Integer start, Integer limit) {
    if (supplier != null) {
      return rFQConsultGroupRelationDao.message(supplier.getPkey(), true, start, limit);
    } else if (purchase != null) {
      return rFQConsultGroupRelationDao.message(purchase.getPkey(), false, start, limit);
    } else {
      return rFQConsultGroupRelationDao.message(null, false, start, limit);
    }
  }

  @Override
  public void moveToGroup(UsrSupplier supplier, String consultPkeys, Integer groupPkey) {
    RFQConsultGroup group =
        rFQConsultGroupDao.findByPkeySupplier_Pkey(groupPkey, supplier.getPkey());
    if (group == null) {
      throw new WebMessageException(ReturnCode.service_gone, "请重新选择文件夹");
    }
    Stream.of(consultPkeys.split(","))
        .map(Integer::new)
        .forEach(
            consultPkey -> {
              RFQConsultGroupRelation relation =
                  rFQConsultGroupRelationDao.findByConsult_PkeySupplier_Pkey(
                      consultPkey, supplier.getPkey());
              if (relation == null) {
                relation = new RFQConsultGroupRelation();
                relation.setConsult(consultPkey);
                relation.setSupplier(supplier.getPkey());
                relation.setConsultGroup(groupPkey);
              } else {
                relation.setConsultGroup(groupPkey);
              }
              rFQConsultGroupRelationDao.save(relation);
            });
  }

  @Override
  public void moveToRecycled(UsrSupplier supplier, String consultPkeys, Boolean isToRecycled) {
    List<RFQConsultRelation> list =
        rFQConsultRelationDao.findAllByConsult_PkeySupplier_Pkey(consultPkeys, supplier.getPkey());
    if (isToRecycled) {
      // 移动到回收站
      list.forEach(
          bean -> {
            if (!bean.gtInRecycleBin()) {
              // 尚未在回收站的
              bean.stInRecycleBin(true);
              rFQConsultRelationDao.save(bean);
            }
          });
    } else {
      // 从回收站恢复
      list.forEach(
          bean -> {
            if (bean.gtInRecycleBin()) {
              // 已经在回收站的
              bean.stInRecycleBin(false);
              rFQConsultRelationDao.save(bean);
            }
          });
    }
  }

  @Override
  public void stamp(UsrSupplier supplier, String consultPkeys, Boolean doStamp) {
    List<RFQConsultRelation> list =
        rFQConsultRelationDao.findAllByConsult_PkeySupplier_Pkey(consultPkeys, supplier.getPkey());
    list.forEach(
        relation -> {
          if (doStamp) {
            // 标记询盘
            if (!relation.gtFavorite()) {
              relation.stFavorite(true);
              rFQConsultRelationDao.save(relation);
            }
          } else {
            // 取消标记
            if (relation.gtFavorite()) {
              relation.stFavorite(false);
              rFQConsultRelationDao.save(relation);
            }
          }
        });
  }

  @Override
  public RFQConsultQuoteInfoView relationInfo(UsrSupplier supplier, Integer consultPkey) {
    RFQConsultRelation relation =
        rFQConsultRelationDao.findByConsult_PkeySupplier_Pkey(consultPkey, supplier.getPkey());
    if (relation == null) {
      throw new WebMessageException(ReturnCode.service_gone, "请重新选择询盘");
    }
    return RFQConsultQuoteInfoView.Builder.toView(relation);
  }

  @Override
  public RFQConsultRelationCountView count(UsrSupplier supplier, Boolean isDeleted) {
    Integer isDeletedCount = null;
    Integer notDeletedCount = null;
    if (isDeleted == null || isDeleted) {
      isDeletedCount =
          Query.SELECT(RFQConsultRelation.class)
              .WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplier.getPkey())
              .WHERE(RFQConsultRelation.T.IN_RECYCLE_BIN, "=?", BeanBase.booleanToByte(true))
              .queryCount();
    }
    if (isDeleted == null || !isDeleted) {
      notDeletedCount =
          Query.SELECT(RFQConsultRelation.class)
              .WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplier.getPkey())
              .WHERE(RFQConsultRelation.T.IN_RECYCLE_BIN, "=?", BeanBase.booleanToByte(false))
              .queryCount();
    }
    RFQConsultRelationCountView view = new RFQConsultRelationCountView();
    view.setIsDeleted(isDeletedCount);
    view.setNotDeleted(notDeletedCount);
    return view;
  }

  private SQL pageWhereSql(
      SQL sql,
      UsrSupplier supplier,
      Date startDate,
      Date endDate,
      Byte type,
      Boolean isDeleted,
      Boolean isFavorite,
      String keyword,
      Integer groupId) {
    sql.LEFT_JOIN(UsrPurchase.class, RFQConsultRelation.T.PURCHASE_ID, UsrPurchase.T.PKEY);
    sql.LEFT_JOIN(PdtProduct.class, RFQConsult.T.PRODUCT, PdtProduct.T.PKEY);
    sql.LEFT_JOIN(RFQConsult.class, RFQConsultRelation.T.CONSULT, RFQConsult.T.PKEY);
    // 供应商
    sql.WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplier.getPkey());
    // 询盘发起时间区间
    sql.WHERE(startDate != null, RFQConsult.T.CREATE_TIME, ">?", startDate);
    sql.WHERE(endDate != null, RFQConsult.T.CREATE_TIME, "<?", endDate);
    // 询盘类型 RFQ询盘,普通询盘,私人展厅询盘
    sql.WHERE(type != null, RFQConsult.T.TYPE, "=?", type);
    // 是否在回收站
    if (isDeleted != null) {
      sql.WHERE(RFQConsultRelation.T.IN_RECYCLE_BIN, "=?", BeanBase.booleanToByte(isDeleted));
    }
    // 商家是否给该询盘做了标记
    if (isFavorite != null) {
      sql.WHERE(RFQConsultRelation.T.FAVORITE, "=?", BeanBase.booleanToByte(isFavorite));
    }
    if (keyword != null && !keyword.isEmpty()) {
      // 根据关键字查询, 收件人或询盘标题
      sql.WHERE(RFQConsult.T.TITLE, "like ?", "%" + keyword + "%")
          .OR()
          .WHERE(UsrPurchase.T.NAME, "like ?", "%" + keyword + "%");
    }
    if (groupId != null) {
      // 根据询盘文件夹查询
      sql.LEFT_JOIN(
          RFQConsultGroupRelation.class, RFQConsult.T.PKEY, RFQConsultGroupRelation.T.CONSULT);
      sql.WHERE(RFQConsultGroupRelation.T.CONSULT_GROUP, "=?", groupId);
    }
    return sql;
  }

  @Override
  public Page<RFQConsultRelationView> page(
      UsrSupplier supplier,
      Integer start,
      Integer limit,
      String keyword,
      Integer groupId,
      Boolean isFavorite,
      Byte type,
      Byte readStatus,
      Boolean isDeleted,
      Date startDate,
      Date endDate,
      Byte orderType) {
    if (start == null) start = 0;
    if (limit == null || limit == 0) limit = 10;
    if (orderType == null) {
      orderType = 1;
    }
    SQL sql =
        new SQL()
            .SELECT(
                RFQConsultRelation.T.PKEY,
                RFQConsultRelation.T.FAVORITE,
                RFQConsultRelation.T.READ_STATUS,
                RFQConsult.T.TYPE,
                RFQConsult.T.TITLE,
                RFQConsult.T.IMAGE,
                RFQConsult.T.CREATE_TIME,
                PdtProduct.T.NAME)
            .SELECT(RFQConsult.T.PKEY, "consultPkey")
            .SELECT(PltCountry.T.PKEY, "countryPkey")
            .SELECT(PltCountry.T.NAME, "countryName")
            .SELECT(PltCountry.T.NATIONAL_FLAG, "countryFlag")
            .SELECT(UsrPurchase.T.PKEY, "purchasePkey")
            .SELECT(UsrPurchase.T.NAME, "purchaseName")
            .SELECT(PdtProduct.T.PKEY, "productPkey")
            .SELECT(PdtProduct.T.PICTURE, "productImages")
            .SELECT(PdtProduct.T.NAME, "productName")
            .FROM(RFQConsultRelation.class)
            .LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, RFQConsult.T.COUNTRY);

    pageWhereSql(sql, supplier, startDate, endDate, type, isDeleted, isFavorite, keyword, groupId);

    if (readStatus != null && readStatus != 0) {
      // 消息读取状态
      sql.WHERE(RFQConsultRelation.T.READ_STATUS, "=?", readStatus);
    }
    if (orderType == 1) {
      sql.ORDER_BY(RFQConsult.T.LAST_MESSAGE_SEND_TIME, "desc");
    } else if (orderType == 2) {
      sql.ORDER_BY(RFQConsult.T.LAST_MESSAGE_SEND_TIME, "asc");
    } else if (orderType == 3) {
      sql.ORDER_BY(RFQConsult.T.CREATE_TIME, "desc");
    } else if (orderType == 4) {
      sql.ORDER_BY(RFQConsult.T.CREATE_TIME, "asc");
    }
    SqlQuery query = Query.sql(sql);
    query.limit(start, limit);
    List<RFQConsultRelationView> result =
        query.queryMaps().stream()
            .map(
                map -> {
                  RFQConsultRelationView view = new RFQConsultRelationView();
                  view.setReadStatus(
                      GetValue.get(map, RFQConsultRelation.T.READ_STATUS, Byte.class, null));
                  view.setFavorite(
                      BeanBase.byteToBoolean(
                          (Byte)
                              map.get(RFQConsultRelation.T.FAVORITE.getFld().getCodeSqlField())));
                  view.setConsult(
                      new RFQConsultView() {
                        {
                          setPkey((Integer) map.get("consultPkey"));
                          setType(
                              "" + (Byte) map.get(RFQConsult.T.TYPE.getFld().getCodeSqlField()));
                          setTitle((String) map.get(RFQConsult.T.TITLE.getFld().getCodeSqlField()));
                          setCreateTime(
                              (Date) map.get(RFQConsult.T.CREATE_TIME.getFld().getCodeSqlField()));
                          setImage((String) map.get(RFQConsult.T.IMAGE.getFld().getCodeSqlField()));
                          if (map.containsKey("productPkey"))
                            setProduct(
                                new ProductView() {
                                  {
                                    setPkey((Integer) map.get("productPkey"));
                                    setName((String) map.get("productName"));
                                    setImage((String) map.get("productImages"));
                                  }
                                });
                          if (map.containsKey("purchasePkey"))
                            setPurchase(
                                new PurchaseView() {
                                  {
                                    setPkey((Integer) map.get("purchasePkey"));
                                    setName((String) map.get("purchaseName"));
                                  }
                                });
                          if (map.containsKey("countryPkey"))
                            setCountry(
                                new CountryView() {
                                  {
                                    setPkey((Integer) map.get("countryPkey"));
                                    setName((String) map.get("countryName"));
                                    setNationalFlag((String) map.get("countryFlag"));
                                  }
                                });
                        }
                      });
                  return view;
                })
            .collect(Collectors.toList());

    return new Page<RFQConsultRelationView>(result, start, limit, query.queryCount()) {
      private Map<Byte, Integer> statusCount = new HashMap<>();

      {
        SQL sql2 =
            new SQL()
                .SELECT(RFQConsultRelation.T.READ_STATUS)
                .SELECT("count(*) as count")
                .FROM(RFQConsultRelation.class);
        pageWhereSql(
            sql2, supplier, startDate, endDate, type, isDeleted, isFavorite, keyword, groupId);
        sql2.GROUP_BY(RFQConsultRelation.T.READ_STATUS);
        Query.sql(sql2).queryMaps().stream()
            .forEach(
                map -> {
                  statusCount.put(
                      GetValue.get(map, RFQConsultRelation.T.READ_STATUS, Byte.class, null),
                      GetValue.get(map, "count", Integer.class, 0));
                });
      }
    };
  }
}
