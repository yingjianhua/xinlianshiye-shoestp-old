package irille.action.usr;

import irille.action.MgtAction;
import irille.shop.usr.UsrSubscribe;

public class UsrSubscribeAction extends MgtAction<UsrSubscribe> {

  @Override
  public Class beanClazz() {
    return UsrSubscribe.class;
  }
}
