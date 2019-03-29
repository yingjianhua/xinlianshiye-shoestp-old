package irille.homeAction.cnt;

import irille.homeAction.HomeAction;
import irille.pub.bean.BeanBase;
import irille.shop.cnt.CntSglPage;
import irille.view.cnt.CntSglPageView;

public class CntSglPageAction extends HomeAction<CntSglPage> {

  public void findpage() throws Exception {
    System.out.println(getBean().getPkey());

    CntSglPage cspc = BeanBase.load(CntSglPage.class, getBean().getPkey());
    CntSglPageView view = new CntSglPageView();
    view.setTitle(cspc.getTitle(HomeAction.curLanguage()));
    view.setDescrip(cspc.getDescrip(HomeAction.curLanguage()));
    view.setType(cspc.gtPageType().getPkey());
    write(view);
  }
}
