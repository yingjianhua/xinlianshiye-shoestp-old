package irille.sellerAction.inq;

import irille.Dao.NewInqDao;
import irille.Entity.NewInquiry.NewInquiry;
import irille.sellerAction.SellerAction;
import irille.sellerAction.inq.inf.INewInqAction;
import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;

public class NewInqAction extends SellerAction<NewInquiry> implements INewInqAction {

    @Getter
    @Setter
    private String name;

    @Inject
    private NewInqDao newInqDao;


    @Override
    public void getList() throws Exception {
        write(newInqDao.getList(getStart(), getLimit(), name, getSupplier().getPkey()));
    }
}
