package irille.sellerAction.inq;

import java.io.IOException;

import javax.inject.Inject;

import irille.Dao.NewInqDao;
import irille.Entity.NewInquiry.NewInquiry;
import irille.sellerAction.SellerAction;
import irille.sellerAction.inq.inf.INewInqAction;
import lombok.Getter;
import lombok.Setter;

public class NewInqAction extends SellerAction<NewInquiry> implements INewInqAction {

  @Getter @Setter private String name;
  @Getter @Setter private Integer id;

  @Inject private NewInqDao newInqDao;

  @Override
  public void getList() throws Exception {
    write(newInqDao.getList(getStart(), getLimit(), name, getSupplier().getPkey()));
  }

  public void remove() throws IOException {
    newInqDao.remove(id);
    write();
  }
}
