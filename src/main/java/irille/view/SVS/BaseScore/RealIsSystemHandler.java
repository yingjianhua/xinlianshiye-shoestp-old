package irille.view.SVS.BaseScore;

public class RealIsSystemHandler implements SVSHandler<Integer> {
  // 真实工厂
  @Override
  public int getScore(Object v) {
    if (v == null) return 0;
    Integer value = (Integer) v;
    if (value == 1) return 4;
    else return 0;
  }
}
