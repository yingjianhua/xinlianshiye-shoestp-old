package irille.sellerAction.pdt;

import java.io.IOException;

import irille.action.dataimport.util.StringUtil;
import irille.pub.Exp;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.sellerAction.SellerAction;
import irille.sellerAction.pdt.inf.IPdtColorAction;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtColorDAO;
import irille.shop.plt.PltConfigDAO;
import org.json.JSONArray;
import org.json.JSONObject;

public class PdtColorAction extends SellerAction<PdtColor> implements IPdtColorAction {

  @Override
  public void getColorList() throws IOException {
    PdtColorDAO.PageSelect pageSelect = new PdtColorDAO.PageSelect();
    pageSelect.setType(1);
    write(pageSelect.getAllColorList(PltConfigDAO.supplierLanguage(getSupplier())));
  }

  /**
   * 供应商添加颜色
   *
   * @throws IOException
   */
  public void insColorBySup() throws Exception {
    PdtColorDAO.InsColorBySup ins = new PdtColorDAO.InsColorBySup();
    ins.setB(getBean());
    try {
      ins.commit();
      PdtColor color = ins.getB();
      writerOrExport(new JSONObject(color));
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 供应商删除颜色
   *
   * @throws IOException
   */
  public void delColorBySup() throws Exception {
    try {
      Integer colorPkey = PdtColorDAO.delColorBySup(getPkey());
      JSONObject json = new JSONObject();
      json.put(STORE_ROOT, colorPkey);
      writerOrExport(json);
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * xy 供应商修改颜色
   *
   * @throws IOException
   */
  public void updColorBySup() throws Exception {
    PdtColorDAO.Upd upd = new PdtColorDAO.Upd();
    upd.setB(getBean());
    try {
      upd.commit();
      PdtColor color =
          translateUtil.getAutoTranslate(upd.getB(), PltConfigDAO.supplierLanguage(getSupplier()));
      JSONObject json = new JSONObject(color);
      writerOrExport(json);
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * xy -供应商修改颜色
   *
   * @throws Exception
   */
  public void updColor() throws Exception {
    if (getBean().getPkey() == null || getBean().getPkey() <= 0) {
      writeErr("参数错误");
      return;
    }
    if (!StringUtil.hasValue(getBean().getName()) && !StringUtil.hasValue(getBean().getPicture())) {
      writeErr("参数错误");
      return;
    }
    PdtColor updColor =
        PdtColorDAO.updColor(
            getBean().getPkey(),
            getSupplier().getPkey(),
            getBean().getName(),
            getBean().getPicture());

    JSONObject json = new JSONObject(updColor);
    writerOrExport(json);
  }

  /**
   * 新增颜色
   *
   * @throws Exception
   */
  public void insColor() throws Exception {
    if (!StringUtil.hasValue(getBean().getName()) || !StringUtil.hasValue(getBean().getPicture())) {
      writeErr("参数错误");
      return;
    }

    JSONObject json = new JSONObject(PdtColorDAO.insColor(getBean(), getSupplier().getPkey()));
    writerOrExport(json);
  }

  /**
   * 查询新颜色数据的系统默认颜色
   *
   * @throws IOException
   */
  public void getList() throws IOException {
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    ja = new JSONArray(PdtColorDAO.getList(), true);
    try {
      json.put(STORE_ROOT, ja);
      writerOrExport(json);
    } catch (Exception e) {
      writeErr("获取系统默认颜色失败");
      return;
    }
  }
}
