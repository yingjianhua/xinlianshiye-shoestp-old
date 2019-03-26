package irille.view.SVS.BaseScore;

public class ResNumOfShoesHandler implements SVSHandler<Integer> {
  // 鞋款数量基础分值计算
  @Override
  public int getScore(Object v) {
    if (null == v) return 0;
    Integer value = (Integer) v;
    if (value > 1500) return 10;
    else if (value > 1000) return 6;
    else return 3;
  }
}
