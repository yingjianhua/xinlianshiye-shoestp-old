package irille.sellerAction.plt;

import irille.pub.svr.ItpCheckPurchaseLogin;
import irille.sellerAction.SellerAction;
import irille.sellerAction.plt.inf.IPltPayAction;
import irille.shop.plt.PltPay;
import irille.shop.plt.PltPayDAO;
import irille.shop.plt.PltPaySettingEnum;
import irille.view.plt.PaySettingParamsPojo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;

public class PltPayAction extends SellerAction<PltPay> implements IPltPayAction {
    private static final PltPayDAO.Ins payIns = new PltPayDAO.Ins();
    private static final PltPayDAO.Upd payup = new PltPayDAO.Upd();
    private static final PltPayDAO.pageSelect pageSelect = new PltPayDAO.pageSelect();
//    private static final PltPayDAO.Ins payIns = new PltPayDAO.Ins();

    private String paySetting;
    private int v;

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public String getPaySetting() {
        return paySetting;
    }

    public void setPaySetting(String paySetting) {
        this.paySetting = paySetting;
    }

    @ItpCheckPurchaseLogin.NeedLogin
    public void savePaySetting() throws IOException, JSONException {
        JSONArray jsonArray = null;
        jsonArray = new JSONArray(paySetting);
        for (int i = 0; i < jsonArray.length(); i++) {
            PltPay pltPay = new PltPay();
            pltPay.setSupplier(getSupplier().getPkey());
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            pltPay.stEnabled(jsonObject.getBoolean("enabled"));
            switch (jsonObject.getInt(PltPay.T.MODE.getFld().getCodeSqlField())) {
                case 5:
                    pltPay.stMode(PltPay.OPay_Mode.OFFINE_PAY);
                    break;
                case 10:
                    pltPay.stMode(PltPay.OPay_Mode.TTPAY);
                    break;
            }
            pltPay.setPoundage(BigDecimal.valueOf(jsonObject.getDouble(PltPay.T.POUNDAGE.getFld().getCodeSqlField())));
            pltPay.setExtraCosts(BigDecimal.valueOf(jsonObject.getDouble(PltPay.T.EXTRA_COSTS.getFld().getCodeSqlField())));
            pltPay.stEnabled(jsonObject.getBoolean("enabled"));
            JSONObject save = new JSONObject();
            switch (pltPay.getMode()) {
                case 10: {
                    try {
                        if (v == 2) {
                            JSONArray js = jsonObject.getJSONArray("setting");
                            for (int i1 = 0; i1 < js.length(); i1++) {
                                JSONObject temp = js.getJSONObject(i1);
                                if (PltPaySettingEnum.TT_Pay.valueOf(temp.getString("name").toUpperCase()) != null) {
                                    save.put(temp.getString("name"), temp.getString("value"));
                                }
                            }
                        } else {
                            for (PltPaySettingEnum.TT_Pay pay : PltPaySettingEnum.TT_Pay.values()) {
                                save.put(pay.getFldName(), jsonObject.getString(pay.getFldName()));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case 5: {
                    try {
                        if (v == 2) {
                            JSONArray js = jsonObject.getJSONArray("setting");
                            for (int i1 = 0; i1 < js.length(); i1++) {
                                JSONObject temp = js.getJSONObject(i1);
                                if (PltPaySettingEnum.Ooffline_Pay.valueOf(temp.getString("name").toUpperCase()) != null) {
                                    save.put(temp.getString("name"), temp.getString("value"));
                                }
                            }
                        } else {
                            for (PltPaySettingEnum.Ooffline_Pay offline_pay : PltPaySettingEnum.Ooffline_Pay.values()) {
                                save.put(offline_pay.getFldName(), jsonObject.getString(offline_pay.getFldName()));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            pltPay.setPaysetting(save.toString());
            PaySettingParamsPojo TPkey = pageSelect.isNewSetting(pltPay.getMode(), pltPay.getSupplier());
            if (TPkey != null) {
                pltPay.setPkey(TPkey.getPkey());
                payup.setB(pltPay);
                payup.commit();
            } else {
                if (!pltPay.gtEnabled()) {
                    continue;
                }
                payIns.setB(pltPay);
                payIns.commit();
            }
        }
        writeErr(1, "success");
    }


    /**
     * @Description: 获取商家支付设置
     * @date 2018/10/30 10:00
     * @author lijie@shoestp.cn
     */
    @ItpCheckPurchaseLogin.NeedLogin
    public void getPaySettings() throws Exception {
        write(pageSelect.getPaySettingBySupplierId(getSupplier().getPkey(), getV()));
    }


}
