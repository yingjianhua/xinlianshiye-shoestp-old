package irille.view.SVS.BaseScore;

public class ProductTestEquipmentHandler implements SVSHandler<Integer> {
  // 测试设备基础加分
  @Override
  public int getScore(Object v) {
    if (null == v) return 0;
    Integer value = (Integer) v;
    if (value == 1) return 5;
    else return 0;
  }
}
