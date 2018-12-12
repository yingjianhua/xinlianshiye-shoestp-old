package irille.sellerAction.inq;

import irille.Dao.NewInqDao;
import irille.Entity.NewInquiry.NewInquiry;
import irille.sellerAction.SellerAction;
import irille.sellerAction.inq.inf.INewInqAction;

public class NewInqAction extends SellerAction<NewInquiry> implements INewInqAction {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public void getList() throws Exception {
        write(new NewInqDao().getList(getStart(), getLimit(), name, getSupplier().getPkey()));
    }
}
