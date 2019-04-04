package irille.view.SVS.BaseScore;

public class RealEmployeesNumHandler implements SVSHandler<Integer> {
  // 员工人数基础分计算
  @Override
  public int getScore(Object v) {
    if (null == v) return 0;
    Integer value = (Integer) v;
    if (value > 300) return 8;
    else if (value > 200) return 3;
    else return 1;
  }
}
