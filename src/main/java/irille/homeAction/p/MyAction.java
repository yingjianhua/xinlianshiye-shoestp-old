package irille.homeAction.p;

public class MyAction extends AbstractPageAction implements IMyAction {

  @Override
  public String home() {
    setResult("/home/my-home.jsp");
    return TRENDS;
  }

  @Override
  public String messageCenter() {
    setResult("/home/my-messageCenter.jsp");
    return TRENDS;
  }

  @Override
  public String contacts() {
    setResult("/home/my-contacts.jsp");
    return TRENDS;
  }

  @Override
  public String favorites() {
    setResult("/home/my-favorites.jsp");
    return TRENDS;
  }

  @Override
  public String accountSettings() {
    setResult("/home/my-accountSettings.jsp");
    return TRENDS;
  }
}
