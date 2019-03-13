package irille.view.SVS.BaseScore;

public class ProductISOCertificateHandler implements SVSHandler<String> {

  @Override
  public int getScore(Object v) {
    if (null == v) return 0;
    String value = (String) v;
    if (value != null && "".equals(value)) return 5;
    else return 0;
  }
}
