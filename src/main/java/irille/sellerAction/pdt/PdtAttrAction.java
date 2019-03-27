package irille.sellerAction.pdt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import irille.action.dataimport.util.StringUtil;
import irille.pub.Str;
import irille.pub.idu.Idu;
import irille.pub.idu.IduPage;
import irille.pub.validate.Regular;
import irille.sellerAction.SellerAction;
import irille.sellerAction.pdt.inf.IPdtAttrAction;
import irille.shop.pdt.PdtAttr;
import irille.shop.pdt.PdtAttrDAO;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.plt.PltConfigDAO;
import lombok.Getter;
import lombok.Setter;

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
      if (line.gtDeleted()) {
        continue;
      }
      List<PdtAttrLine> attrLineList = Idu.getLines(PdtAttrLine.T.MAIN.getFld(), line.getPkey());
      for (PdtAttrLine pdtAttrLine : attrLineList) {
        if (pdtAttrLine.gtDeleted()) {
          continue;
        }
        attrLineJson = crtJsonByBean(pdtAttrLine);
        attrLineJson.put(PdtAttrLine.T.PKEY.getFld().getCode(), pdtAttrLine.getPkey());
        attrLineJson.put(
            PdtAttrLine.T.NAME.getFld().getCode(),
            pdtAttrLine.getName(PltConfigDAO.manageLanguage()));
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

  @Getter @Setter private Integer cat;

  @Override
  public void AttrList() throws IOException {
    PdtAttrDAO.PageSelect pageSelect = new PdtAttrDAO.PageSelect();
    write(
        pageSelect.getAllAttr(
            PltConfigDAO.supplierLanguage(getSupplier()), getSupplier().getPkey(), cat));
  }

  @Setter @Getter private String attrValue;

  /**
   * -新增产品属性与产品属性明细
   *
   * @throws IOException
   */
  public void addAttr() throws IOException {
    if (!StringUtil.hasValue(getBean().getName()) || !StringUtil.hasValue(attrValue)) {
      writeErr(0, "属性信息不完整");
      return;
    }
    Pattern p = Pattern.compile(Regular.REGULAR_ENG);
    Matcher matcher = p.matcher(getBean().getName());
    Matcher matcher1 = p.matcher(attrValue);
    if (!matcher.matches() || !matcher1.matches()) {
      writeErr(-100, "请输入英文属性名称与内容!");
      return;
    }
    if (getBean().getName().length() > 20) {
      writeErr(-101, "属性名称过长");
      return;
    }
    if (attrValue.length() > 20) {
      writeErr(-101, "属性值过长");
      return;
    }
    Integer count = PdtAttrDAO.verifySupplierCount(getSupplier().getPkey());
    if (count >= 5) {
      writeErr(-101, "您已经添加5个属性了");
      return;
    }

    write(
        PdtAttrDAO.insAttrAndAttrLine(
            getSupplier().getPkey(),
            getBean().getName(),
            attrValue,
            PltConfigDAO.supplierLanguage(getSupplier())));
  }

  /** 删除 */
  public void del() throws IOException {
    if (getBean().getPkey() == null) {
      writeErr(0, "参数错误");
      return;
    }
    PdtAttrDAO.delAttrAndAttrLine(getBean().getPkey(), getSupplier().getPkey());
    write();
  }
}
