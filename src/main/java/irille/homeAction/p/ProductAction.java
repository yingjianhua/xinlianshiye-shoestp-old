package irille.homeAction.p;

public class ProductAction extends AbstractPageAction implements IProductAction {

  @Override
  public String expo() {
    setResult("/home/goods-info-expo.jsp");
    return TRENDS;
  }
}
