package irille.Service.Eo;

import com.google.inject.ImplementedBy;
import irille.Service.Eo.impl.EasyOdrServiceimpl;
import irille.view.EO.easyodrView;

import java.util.List;

@ImplementedBy(EasyOdrServiceimpl.class)
public interface EasyOdrService {
    void generateOrder(Integer getPurchaseLineid, Integer Purchaseid, List<easyodrView> list) throws Exception;
}
