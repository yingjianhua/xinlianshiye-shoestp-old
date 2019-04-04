package irille.homeAction.pdt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import irille.homeAction.HomeAction;
import irille.shop.pdt.PdtAttr;
import irille.shop.pdt.PdtAttrDAO;
import irille.shop.pdt.PdtAttrLine;

public class PdtAttrAction extends HomeAction<PdtAttr> {
  private List<PdtAttrLine> _listLine = new ArrayList<PdtAttrLine>();
  private PdtAttrDAO.PageSelect pageSelect = new PdtAttrDAO.PageSelect();

  public List<PdtAttrLine> getListLine() {
    return _listLine;
  }

  public void setListLine(List<PdtAttrLine> listLine) {
    _listLine = listLine;
  }

  public void AttrList() throws IOException {
    write(pageSelect.getAllAttr(curLanguage()));
  }
}
