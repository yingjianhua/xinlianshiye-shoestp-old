/** */
package irille.sellerAction.pdt.view;

import java.util.List;

import irille.sellerAction.usr.dto.UsrProductCategoryView;
import irille.shop.pdt.PdtSize;
import irille.view.BaseView;
import irille.view.pdt.PdtProductCatView;
import irille.view.pdt.PdtProductSaveView;
import lombok.Data;

/** @author liyichao */
@Data
public class EditProdView implements BaseView {
  private List<PdtProductCatView> cats;
  private List<UsrProductCategoryView> supCategory;

  private List attrList;

  private List<irille.view.pdt.PdtColorView> colors;

  private List<PdtSize> sizes;

  private PdtProductSaveView product;
}
