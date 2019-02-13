package irille.platform.plt;

import irille.action.MgtAction;
import irille.platform.plt.View.TrantslateConditionView;
import irille.shop.plt.PltTrantslate;
import irille.shop.plt.PltTrantslateDAO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

public class PltTrantslateAction   extends MgtAction<PltTrantslate> {
    @Override
    public Class beanClazz() {
        return PltTrantslate.class;
    }

    public PltTrantslate getBean() {
        return _bean;
    }

    public void setBean(PltTrantslate bean) {
        this._bean = bean;
    }
    @Setter
    @Getter
    private List<TrantslateConditionView> tc;
    public void ListTrantslate() throws IOException {
        write( PltTrantslateDAO.ListTrantslate(tc,getStart(),getLimit()));
    }

    public void     deltran() throws  IOException {
        PltTrantslateDAO.deltran dt=new PltTrantslateDAO.deltran();
        dt.setBKey(getBean().getPkey());
        dt.commit();
        write();
    }
}
