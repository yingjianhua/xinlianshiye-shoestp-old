package irille.sellerAction.pdt;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import irille.pub.Exp;
import irille.pub.tb.FldLanguage;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.sellerAction.SellerAction;
import irille.sellerAction.pdt.inf.IPdtSizeAction;
import irille.shop.pdt.PdtSize;
import irille.shop.pdt.PdtSizeDAO;
import irille.shop.plt.PltConfigDAO;

public class PdtSizeAction extends SellerAction<PdtSize> implements IPdtSizeAction {

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    private String productcategory;


    /**
     * 根据条件查询
     *
     * @throws Exception
     */
    public void listAll() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        ja = new JSONArray(PdtSizeDAO.listSummary(PltConfigDAO.supplierLanguage(getSupplier()), 1), false);
        json.put(STORE_ROOT, ja);
        writerOrExport(json);
    }

    /**
     * 供应商添加尺寸
     *
     * @throws Exception
     */
    public void insSizeBySup() throws Exception {
        PdtSizeDAO.InsSizeBySup ins = new PdtSizeDAO.InsSizeBySup();
        getBean().setName(translateUtil.valuetoMultilanguageJson(getBean().getName()).toString());
        ins.setB(getBean());
        try {
            ins.commit();
            PdtSize size = ins.getB();
            JSONObject json = new JSONObject(size);
            writerOrExport(json);
        } catch (Exp e) {
            writeErr(e.getLastMessage());
        }
    }

    /**
     * 供应商删除尺寸
     *
     * @throws IOException
     */
    public void delSizeBySup() throws Exception {
        try {
            Integer sizePkey = PdtSizeDAO.delSizeBySup(getPkey());
            JSONObject json = new JSONObject();
            json.put(STORE_ROOT, sizePkey);
            writerOrExport(json);
        } catch (Exp e) {
            writeErr(e.getLastMessage());
        }
    }

    /**
     * 供应商修改尺寸
     *
     * @throws IOException
     */
    public void updSizeBySup() throws Exception {
        PdtSizeDAO.Upd upd = new PdtSizeDAO.Upd();
        getBean().setName(translateUtil.translate(getBean().getName(), FldLanguage.Language.values()).toString());
        upd.setB(getBean());
        try {
            upd.commit();
            PdtSize size = translateUtil.getAutoTranslate(upd.getB(), PltConfigDAO.supplierLanguage(getSupplier()));
            JSONObject json = new JSONObject(size);
            writerOrExport(json);
        } catch (Exp e) {
            writeErr(e.getLastMessage());
        }
    }

}
