package irille.sellerAction.plt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import irille.pub.Exp;
import irille.pub.bean.BeanBase;
import irille.sellerAction.SellerAction;
import irille.sellerAction.plt.inf.IPltFreightSellerAction;
import irille.shop.plt.PltCountryFreight;
import irille.shop.plt.PltFreightSeller;
import irille.shop.plt.PltFreightSellerDAO;
import irille.shop.plt.PltFreightSellerLine;
import irille.view.Page;
import irille.view.plt.FreightSellerView;
import irille.view.plt.FreightSellerlistView;
import org.json.JSONArray;
import org.json.JSONObject;

public class PltFreightSellerAction extends SellerAction<PltFreightSeller>
    implements IPltFreightSellerAction {
  private List<PltFreightSellerLine> _listLine = new ArrayList<PltFreightSellerLine>();

  public List<PltFreightSellerLine> getListLine() {
    return _listLine;
  }

  public void setListLine(List<PltFreightSellerLine> _listLine) {
    this._listLine = _listLine;
  }

  @Override
  public void ins() throws Exception {
    PltFreightSellerDAO.Ins insDao = new PltFreightSellerDAO.Ins();
    insDao.setB(getBean());
    PltFreightSellerLine line = new PltFreightSellerLine().init();
    line.setSection("全区");
    _listLine.add(line);
    insDao.setLines(getListLine());
    try {
      insDao.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  @Override
  public void upd() throws Exception {

    PltFreightSellerDAO.Upd upd = new PltFreightSellerDAO.Upd();
    upd.setB(getBean());
    upd.setLines(getListLine());
    upd.commit();
    writeSuccess();
  }

  private String selectcompany;

  public String getSelectcompany() {
    return selectcompany;
  }

  public void setSelectcompany(String selectcompany) {
    this.selectcompany = selectcompany;
  }

  /**
   * 查询当前供应商下的模糊查询的快递公司
   *
   * @throws Exception
   */
  public void listfreightlike() throws Exception {
    Page<FreightSellerlistView> freight =
        PltFreightSellerDAO.listfreightlike(
            getSupplier().getPkey(), selectcompany, getStart(), getLimit());
    write(freight);
  }

  /**
   * 查询当前供应商下的所有运费公司
   *
   * @throws Exception
   */
  public void listfreight() throws Exception {
    List<FreightSellerView> freight = PltFreightSellerDAO.listfreight(getSupplier().getPkey());
    JSONObject json = new JSONObject();
    JSONArray array = new JSONArray(freight, false);
    json.put("data", array);
    writerOrExport(json);
  }

  /**
   * 删除快递公司
   *
   * @throws Exception
   * @author zw
   */
  public void del() throws Exception {
    PltFreightSellerDAO.Del del = new PltFreightSellerDAO.Del();
    del.setBKey(getPkey());
    try {
      del.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 删除商家所有快递公司并恢复系统默认快递公司
   *
   * @throws IOException
   * @author zw
   */
  public void recovery() throws Exception {
    String sql =
        PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField()
            + "="
            + SellerAction.getSupplier().getPkey();
    List<PltFreightSeller> list = BeanBase.list(PltFreightSeller.class, sql, false);
    for (PltFreightSeller pfs : list) {
      BeanBase.executeUpdate(
          "delete from "
              + PltFreightSellerLine.TB.getCodeSqlTb()
              + " where "
              + PltFreightSellerLine.T.MAIN
              + "=?",
          pfs.getPkey());
    }
    BeanBase.executeUpdate(
        "delete from "
            + PltFreightSeller.TB.getCodeSqlTb()
            + " where "
            + PltFreightSeller.T.SUPPLIER
            + "=?",
        SellerAction.getSupplier().getPkey());
    BeanBase.executeUpdate(
        "delete from "
            + PltCountryFreight.TB.getCodeSqlTb()
            + " where "
            + PltFreightSeller.T.SUPPLIER
            + "=?",
        SellerAction.getSupplier().getPkey());
    try {
      PltFreightSellerDAO.init(getSupplier().getPkey(), true);
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }
}
