package irille.view.SVS.BaseScore;

public class ProductionExportVolumeHandler implements SVSHandler<Integer> {
  // 年出口额基础分计算
  @Override
  public int getScore(Object v) {
    if (null == v) return 0;
    Integer value = (Integer) v;
    if (value > 500) return 6;
    else if (value > 200) return 3;
    else return 1;
  }
}
