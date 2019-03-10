package irille.pub.dynamicScore;

import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.SVS.Enums.SVSAuthenticationStatus;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.Entity.SVS.SVSInfo;
import irille.Entity.SVS.SVSNewestPdt;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.svr.Env;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SVSNewestPdtServiceImpl implements SVSNewestPdtService {
    @Override
    public Date getDate() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, -1);
        return now.getTime();
    }

    @Override
    public Date getMonth() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -30);
        System.out.println(now.getTime());
        System.out.println(Env.getTranBeginTime());
        return now.getTime();
    }

    @Override
    public Boolean isOpenThirty(Integer supId) {
        /**
         * 缺少一个判断开店时间是否超过30天
         * 超过30天统计30天之前的
         */
        return true;
    }

    @Override
    public SQL getPdts(Integer supId) {
        SQL sql = new SQL() {{
            SELECT(PdtProduct.class)
                    .FROM(PdtProduct.class)
                    .WHERE(PdtProduct.T.SUPPLIER, "=?", supId);
        }};
        return sql;
    }

    @Override
    public SQL getPdtFixedFraction(Integer supId) {
        SQL sql = new SQL() {
            {
                SELECT(SVSNewestPdt.class)
                        .FROM(SVSNewestPdt.class)
                        .WHERE(SVSNewestPdt.T.SUPPLIER_ID, "=?", supId);
            }
        };
        return sql;
    }

    @Override
    public SQL getNewPdtFraction(Integer supId) {
        SQL sql = getPdtFixedFraction(supId);
        if (isOpenThirty(supId)) {
            sql.WHERE(SVSNewestPdt.T.ADDED_TIME, ">=?", getMonth());
        }
        sql.WHERE(SVSNewestPdt.T.ADDED_TIME, "<?", Env.getTranBeginTime());
        return sql;
    }


    @Override
    public SQL getPrivatePdt(Integer supId) {
        SQL sql = getPdts(supId);
        sql.WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.PrivateExpo);
        sql.WHERE(O2O_PrivateExpoPdt.T.STATUS, "=?", O2O_PrivateExpoPdtStatus.ON);
        sql.WHERE(O2O_PrivateExpoPdt.T.VERIFY_STATUS, "=?", O2O_PrivateExpoPdtStatus.PASS);
        sql.LEFT_JOIN(O2O_PrivateExpoPdt.class, O2O_PrivateExpoPdt.T.PDT_ID, PdtProduct.T.PKEY);
        return sql;
    }

    @Override
    public SQL getO2OPdt(Integer supId) {
        SQL sql = getPdts(supId);
        sql.SELECT(O2O_Product.class);
        sql.LEFT_JOIN(O2O_Product.class, O2O_Product.T.PRODUCT_ID, PdtProduct.T.PKEY);
        sql.LEFT_JOIN(O2O_Activity.class, O2O_Activity.T.PKEY, O2O_Product.T.ACTIVITY_ID);
        sql.WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.O2O);
        sql.WHERE(O2O_Product.T.STATUS, "=?", O2O_ProductStatus.ON);
        sql.WHERE(O2O_Product.T.VERIFY_STATUS, "=?", O2O_ProductStatus.PASS);
        return sql;
    }

    @Override
    public Integer getInqReply(Integer supId) {
        SQL sql = new SQL() {{
            SELECT(RFQConsultMessage.class)
                    .FROM(RFQConsultMessage.class)
                    .LEFT_JOIN(RFQConsultRelation.class, RFQConsultRelation.T.PKEY, RFQConsultMessage.T.RELATION)
                    .WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supId);
            if (isOpenThirty(supId)) {
                WHERE(RFQConsultRelation.T.CREATE_DATE, ">=?", getMonth());
            }
            WHERE(RFQConsultRelation.T.CREATE_DATE, "<?", Env.getTranBeginTime())
                    .ORDER_BY(RFQConsultMessage.T.SEND_TIME, "DESC");
        }};
        Integer num = 0;
        List<Map<String, Object>> list = Query.sql(sql).queryMaps();
        Integer listSize = list.size();
        for (int i = 0; i < listSize && i + 1 != listSize; i++) {
            Map<String, Object> map = list.get(i);
            Map<String, Object> maps = list.get(i + 1);
            if ((Byte) map.get(RFQConsultMessage.T.P2S.getFld().getCodeSqlField()) == 1) {
                if ((Byte) maps.get(RFQConsultMessage.T.P2S.getFld().getCodeSqlField()) == 0) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime((Date) map.get(RFQConsultMessage.T.SEND_TIME.getFld().getCodeSqlField()));
                    calendar.add(Calendar.DATE, +1);
                    if (calendar.getTime().getTime() >= ((Date) maps.get(RFQConsultMessage.T.SEND_TIME.getFld().getCodeSqlField())).getTime()) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    @Override
    public SQL getRFQQuote(Integer supId) {
        SQL sql = new SQL() {
            {
                SELECT(RFQConsultRelation.class)
                        .FROM(RFQConsultRelation.class)
                        .WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supId);
                if (isOpenThirty(supId)) {
                    WHERE(RFQConsultRelation.T.CREATE_DATE, ">=?", getMonth());
                }
                WHERE(RFQConsultRelation.T.CREATE_DATE, "<?", Env.getTranBeginTime());
                WHERE(RFQConsultRelation.T.VALID_DATE, ">?", Env.getTranBeginTime());

            }
        };
        return sql;
    }

    @Override
    public List<Integer> getSupDiamondsAndGold() {
        SQL sql = new SQL() {{
            SELECT(SVSInfo.class)
                    .FROM(SVSInfo.class)
                    .WHERE(SVSInfo.T.STATUS, "=?", SVSAuthenticationStatus.SUCCESS)
                    .WHERE(SVSInfo.T.GRADE, "=?", SVSGradeType.GOLD)
                    .OR()
                    .WHERE(SVSInfo.T.GRADE, "=?", SVSGradeType.DIAMONDS)
                    .WHERE(SVSInfo.T.STATUS, "=?", SVSAuthenticationStatus.SUCCESS);
        }};
        List<Integer> supIds = Query.sql(sql).queryMaps().stream().map(o -> {
            return ((Integer) o.get(SVSInfo.T.SUPPLIER.getFld().getCodeSqlField()));
        }).collect(Collectors.toList());
        return supIds;
    }

    @Override
    public SQL getSVSInfo(Integer supId) {
        SQL sql = new SQL(){{
            SELECT(SVSInfo.class)
                    .FROM(SVSInfo.class)
                    .WHERE(SVSInfo.T.SUPPLIER,"=?",supId);
        }};
        return sql;
    }
}
