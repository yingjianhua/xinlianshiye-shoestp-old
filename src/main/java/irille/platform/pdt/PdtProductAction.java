package irille.platform.pdt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import irille.action.MgtAction;
import irille.action.pdt.inf.IPdtProductAction;
import irille.platform.pdt.View.productView.SeartchView;
import irille.pub.Str;
import irille.pub.bean.Query;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import lombok.Getter;
import lombok.Setter;

@Setter
public class PdtProductAction extends MgtAction<PdtProduct> implements IPdtProductAction {
  @Override
  public Class beanClazz() {
    return PdtProduct.class;
  }

  public PdtProduct getBean() {
    return _bean;
  }

  public void setBean(PdtProduct bean) {
    this._bean = bean;
  }

  @Getter @Setter private Boolean verify;

  //  @Override
  //  public void verify() throws Exception {
  //    PdtProduct bean = PdtProductDAO.verify(verify, (Integer) getPkey());
  //    bean.setPicture(AppConfig.image_base_url + bean.getPicture());
  //    writeSuccess(bean);
  //  }

  /**
   * @Description: 产品管理页面 审核 为启用停用 停用会级联销售状态一起下架
   *
   * @date 2019/1/24 13:47
   * @anthor wilson zhang
   */
  //  public void isverify() throws Exception {
  //    PdtProduct bean = PdtProductDAO.verify(verify, (Integer) getPkey());
  //    write();
  //  }

  @Override
  public void list() throws Exception {
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    // 目前过滤器的搜索，是肯定会带初始条件的
    String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
    IduPage page = newPage();
    page.setStart(getStart());
    page.setLimit(getLimit());
    page.setWhere(where);
    page.commit();
    List<PdtProduct> list = page.getList();
    JSONObject lineJson = null;
    for (PdtProduct line : list) {
      if (line.getNormAttr() != null && !line.getNormAttr().trim().equals("")) {
        List<String> s =
            Query.SELECT(Language.zh_CN, PdtAttrLine.T.NAME)
                .FROM(PdtAttrLine.class)
                .WHERE(PdtAttrLine.T.PKEY, "in (" + line.getNormAttr() + ")")
                .queryList(String.class);
        String normalAttr = s.stream().collect(Collectors.joining(","));
        line.setNormAttr(normalAttr);
      }
      lineJson = crtJsonByBean(line);
      ja.put(lineJson);
    }
    json.put(STORE_ROOT, ja);
    json.put(STORE_TOTAL, page.getCount());
    writerOrExport(json);
  }

  @Override
  public void onSale(boolean onSale, int pkey) {
    // TODO Auto-generated method stub

  }

  @Override
  public void publish() {
    // TODO Auto-generated method stub

  }

  // 搜索字段
  @Getter @Setter private String fldvalue;
  // 搜索字段内容
  @Getter @Setter private String condition;

  /**
   * 查询采购商列表
   *
   * @throws Exception
   * @author lingjian
   * @date 2019/1/24 14:30
   */
  public void listselectpurchse() throws Exception {
    write(PdtProductDAO.listproduct(fldvalue, condition, getStart(), getLimit()));
  }

  @Getter @Setter private SeartchView seart;

  /**
   * 获取新产品列表
   *
   * @throws IOException
   * @author zjl
   * @date 2019/2/2 16:25
   */
  public void getProducts() throws IOException {
    write(PdtProductDAO.getProducts(getStart(), getLimit(), seart));
  }

  private Integer id;
  private Byte status;
  private String message;

  /**
   * 普通产品审核通过
   *
   * @throws IOException
   * @auther liyichao
   */
  public void pass() throws IOException {
    PdtProductDAO.review(id, Pdt.OAppr.PASS.getLine().getKey(), null);
    write();
  }

  public void failed() throws IOException {
    PdtProductDAO.review(id, Pdt.OAppr.Failed.getLine().getKey(), message);
    write();
  }

  /**
   * 审核产品(O2O/普通产品)
   *
   * @throws IOException
   * @author zjl
   * @date 2019/2/2 19:28
   */
  public void review() throws IOException {
    PdtProductDAO.review(id, status, message);
    write();
  }

  /**
   * 下架产品(O2O/普通产品)
   *
   * @throws IOException
   * @author zjl
   * @date 2019/2/2 19:28
   */
  public void lower() throws IOException {
    PdtProductDAO.lower(id, status, message);
    write();
  }
}
