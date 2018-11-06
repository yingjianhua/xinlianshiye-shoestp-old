package irille.sellerAction.pdt;

import irille.pub.Str;
import irille.pub.idu.Idu;
import irille.pub.idu.IduPage;
import irille.sellerAction.SellerAction;
import irille.sellerAction.pdt.inf.IPdtAttrAction;
import irille.shop.pdt.PdtAttr;
import irille.shop.pdt.PdtAttrDAO;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.plt.PltConfigDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PdtAttrAction extends SellerAction<PdtAttr> implements IPdtAttrAction {
    private List<PdtAttrLine> _listLine = new ArrayList<PdtAttrLine>();

    public List<PdtAttrLine> getListLine() {
        return _listLine;
    }

    public void setListLine(List<PdtAttrLine> listLine) {
        _listLine = listLine;
    }

    @Override
    public PdtAttr insRun() throws Exception {
        PdtAttrDAO.Ins ins = new PdtAttrDAO.Ins();
        ins.setB(getBean());
        ins.setLines(getListLine());
        ins.commit();
        return ins.getB();
    }

    @Override
    public PdtAttr updRun() throws Exception {
        PdtAttrDAO.Upd upd = new PdtAttrDAO.Upd();
        upd.setB(getBean());
        upd.setLines(getListLine());
        upd.commit();
        return upd.getB();
    }

    /**
     * 查询所有包括子明细
     *
     * @throws Exception
     */
    public void AttrLinelist() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        // 目前过滤器的搜索，是肯定会带初始条件的
        String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
        IduPage page = newPage();
        page.setStart(getStart());
        page.setLimit(getLimit());
        page.setWhere(where);
        page.commit();
        List<PdtAttr> list = page.getList();
        JSONObject lineJson = null;
        for (PdtAttr line : list) {
            JSONObject attrLineJson = new JSONObject();
            JSONArray attrLineArray = new JSONArray();
            if(line.gtDeleted()) {
            	continue;
            }
            List<PdtAttrLine> attrLineList = Idu.getLines(PdtAttrLine.T.MAIN.getFld(), line.getPkey());
            for (PdtAttrLine pdtAttrLine : attrLineList) {
            	if(pdtAttrLine.gtDeleted()) {
            		continue;
            	}
                attrLineJson = crtJsonByBean(pdtAttrLine);
                attrLineJson.put(PdtAttrLine.T.PKEY.getFld().getCode(), pdtAttrLine.getPkey());
                attrLineJson.put(PdtAttrLine.T.NAME.getFld().getCode(), pdtAttrLine.getName(PltConfigDAO.manageLanguage()));
                attrLineArray.put(attrLineJson);
            }
            lineJson = crtJsonByBean(line);
            lineJson.put("attrLine", attrLineArray);
            ja.put(lineJson);
        }
        json.put(STORE_ROOT, ja);
        json.put(STORE_TOTAL, page.getCount());
        writerOrExport(json);

    }

    @Override
    public void AttrList() throws IOException {
        PdtAttrDAO.PageSelect pageSelect = new PdtAttrDAO.PageSelect();
        write(pageSelect.getAllAttr(PltConfigDAO.supplierLanguage(getSupplier())));
    }


}