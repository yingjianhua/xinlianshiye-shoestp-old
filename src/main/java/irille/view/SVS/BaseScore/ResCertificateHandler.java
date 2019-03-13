package irille.view.SVS.BaseScore;

public class ResCertificateHandler implements SVSHandler<String> {
  // 专利证书基础分值计算
  @Override
  public int getScore(Object v) {
    if (null == v) return 0;
    String value = (String) v;
    String[] array = value.split(",");
    if (array.length == 1) return 1;
    else if (array.length > 1 && array.length <= 3) return 3;
    else if (array.length > 3) return 6;
    else return 0;
  }
}
