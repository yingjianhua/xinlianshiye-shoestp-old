package irille.Service.Eo;

import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Service.Eo.impl.EasyOdrServiceimpl;
import irille.pub.tb.FldLanguage.Language;
import irille.view.Easy.EasyodrView;

@ImplementedBy(EasyOdrServiceimpl.class)
public interface EasyOdrService {
  void generateOrder(
      Integer getPurchaseLineid, Integer Purchaseid, List<EasyodrView> list, Language language)
      throws Exception;
}
