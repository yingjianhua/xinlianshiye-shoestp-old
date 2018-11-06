package irille.shop.plt;

import com.fasterxml.jackson.core.JsonProcessingException;

import irille.core.sys.Sys;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.validate.ValidNumber;
import irille.shop.plt.PltPay.OPay_Mode;
import irille.shop.plt.PltPay.T;
import irille.shop.usr.UsrSupplier;
import irille.view.plt.PaySettingParamsPojo;
import irille.view.plt.PaySettingView;
import irille.view.plt.PayTypeView;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PltPayDAO {
    private static final PltPayDAO.Upd payup = new PltPayDAO.Upd();

    public static final Log LOG = new Log(PltPayDAO.class);

    public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
        priceErr("【{0}】不能低于或等于【{1}】"),
        ;
        private String _msg;

        private Msgs(String msg) {
            _msg = msg;
        }

        public String getMsg() {
            return _msg;
        }
    } //@formatter:on

    public static List<PayTypeView> listViewBySupplier(Integer supplier) {
        return Query
                .SELECT(PltPay.class)
                .WHERE(T.SUPPLIER, "=?", supplier)
                .queryList()
                .stream()
                .map(bean -> new PayTypeView() {{
                    setName(bean.gtMode());
                    setId(bean.getPkey());
                }})
                .collect(Collectors.toList());
    }

    public static class pageSelect extends IduOther<IduOther, PltPay> {

        public List getPaySettingBySupplierId(long id, int version) throws JSONException, JsonProcessingException {
            FormaterSql sql = FormaterSql.build();
            sql.select(
                    PltPay.T.PKEY,
                    PltPay.T.MODE,
                    PltPay.T.POUNDAGE,
                    PltPay.T.EXTRA_COSTS,
                    PltPay.T.ENABLED,
                    PltPay.T.PAYSETTING
            ).eq(PltPay.T.SUPPLIER);
            List parmList = new ArrayList();
            parmList.add(id);
            List<Map> resultList = sql.castListMap(BeanBase.list(sql.buildSql(), sql.getParms(parmList)), PltConfigDAO.supplierLanguage(Math.toIntExact(id)));
            if (version == 2) {
                List result = SetBeans.setList(resultList, PaySettingView.class);
                System.out.println(result);
                return result;
            } else {

                for (Map o : resultList) {
                    System.out.println(o.get(PltPay.T.PAYSETTING.getFld().getCodeSqlField()));
                    JSONObject jsonObject = new JSONObject(String.valueOf(o.get(PltPay.T.PAYSETTING.getFld().getCodeSqlField())));
                    Iterator iterator = jsonObject.keys();
                    while (iterator.hasNext()) {
                        String key = String.valueOf(iterator.next());
                        o.put(key, jsonObject.get(key));
                    }
                    o.remove(PltPay.T.PAYSETTING.getFld().getCodeSqlField());
                }
            }
            return resultList;
        }

        /**
         * @Description: 检测是不是新的设置  返回-1 表示没有 返回1 表示改设置 启用 返回2 表示改设置未启用
         * @author lijie@shoestp.cn
         * @date 2018/9/13 16:24
         */
        public PaySettingParamsPojo isNewSetting(int mode, int sup) {
            FormaterSql sql = FormaterSql.build();
            sql.select(T.PKEY, T.ENABLED)
                    .eqAutoAnd(T.MODE, mode)
                    .eqAutoAnd(T.SUPPLIER, sup).from(T.SUPPLIER);
            Object[] objects = PltPay.queryOneRowIsNull(sql.buildSql(), sql.getParms());
            PaySettingParamsPojo paySettingParamsView = new PaySettingParamsPojo();
            if (objects != null && objects.length == 2 && sql.castInt(objects) > 0) {
                paySettingParamsView.setPkey(sql.castInt(objects));
                if (Integer.valueOf(String.valueOf(objects[1])) == 1) {
                    paySettingParamsView.setEnabled(true);
                }
                paySettingParamsView.setEnabled(false);
                return paySettingParamsView;
            }
            return null;
        }
    }

    public static class Ins extends IduIns<Ins, PltPay> {
        @Override
        public void before() {
            super.before();
            BigDecimal a = new BigDecimal(0);
            getB().setPoundage(a);
            getB().setExtraCosts(a);
        }

        @Override
        public void valid() {
            ValidNumber vn = numberValid();
//	     vn.validBigDecimal1LessThanBigDecimal2(PltPay.T.MIN_COST, PltPay.T.MAX_COST);
//	     vn.validIntegerPositive(PltPay.T.MIN_COST, PltPay.T.MAX_COST, PltPay.T.EXTRA_COSTS, PltPay.T.POUNDAGE);
            super.valid();
        }

    }


    public static class Upd extends IduUpd<Upd, PltPay> {
        @Override
        public void before() {
            PltPay bean = loadThisBeanAndLock();
            PropertyUtils.copyPropertiesWithout(bean, getB(),
                    PltPay.T.PKEY,
                    PltPay.T.ROW_VERSION,
                    PltPay.T.SUPPLIER
//                    T.PAYSETTING,
//                    T.ENABLED
            );
            setB(bean);
//			model.setCreatedTime(Env.getTranBeginTime());
            super.before();
        }

        @Override
        public void valid() {
            super.valid();
        }
    }

    /**
     * 商家过审后,初始化商家的支付设置,默认给商家新增一个T/T的的支付方式
     *
     * @author yingjianhua
     */
    public static void init(UsrSupplier supplier) {
        BeanQuery<PltPay> q = Query.SELECT(PltPay.class).WHERE(T.SUPPLIER, "=?", supplier);
        if (q.queryCount() > 0)
            return;
        PltPay bean = new PltPay();
        bean.stMode(OPay_Mode.TTPAY);
        bean.stSupplier(supplier);
        bean.setPoundage(BigDecimal.ZERO);
        bean.setExtraCosts(BigDecimal.ZERO);
        bean.stEnabled(true);
        bean.setRowVersion((short) 0);
        //支付设置
        //bean.setPaysetting(paySetting4TT(supplier.getSettlementBank(), "", supplier.get, bankAddress, supplier.getBankAccount()));
        bean.ins();
    }

    private static final String paysetting_TT_beneficiaryBank = "beneficiaryBank";
    private static final String paysetting_TT_swiftCode = "swiftCode";
    private static final String paysetting_TT_beneficiary = "beneficiary";
    private static final String paysetting_TT_bankAddress = "bankAddress";
    private static final String paysetting_TT_beneficiaryAccount = "beneficiaryAccount";

    private static final JSONObject paySetting4TT(String beneficiaryBank, String swiftCode, String beneficiary, String bankAddress, String beneficiaryAccount) throws JSONException {
        JSONObject json = new JSONObject();
        json.put(paysetting_TT_beneficiaryBank, beneficiaryBank);
        json.put(paysetting_TT_swiftCode, swiftCode);
        json.put(paysetting_TT_beneficiary, beneficiary);
        json.put(paysetting_TT_bankAddress, bankAddress);
        json.put(paysetting_TT_beneficiaryAccount, beneficiaryAccount);
        return json;
    }

    /**
     * <<<<<<<<<<<<<<<<<<< ----------------  获取供应商所有启用的支付方式  ----------------->>>>>>>>>>>>>>>>>>>>>>
     **/
    public static List<PayTypeView> getPayMethodBySupplier(Integer supplier) {
        return Query
                .SELECT(T.PKEY, T.MODE)
                .FROM(PltPay.class)
                .WHERE(T.SUPPLIER, "=?", supplier)
                .WHERE(T.ENABLED, "=?", Sys.OEnabled.TRUE.getLine().getKey())
                .queryList()
                .stream()
                .map(bean -> new PayTypeView() {{
                    setId(bean.getPkey());
                    setMode(bean.gtMode());
                }})
                .collect(Collectors.toList());

    }
}
