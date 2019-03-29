package irille.view.SVS.BaseScore;

public class ProductQualityCertificateHandler implements SVSHandler<String> {
  // 第三方认证基础分计算
  @Override
  public int getScore(Object v) {
    if (null == v) return 0;
    String value = (String) v;
    if (value != null && !"".equals(value)) return 5;
    else return 0;
  }
}
