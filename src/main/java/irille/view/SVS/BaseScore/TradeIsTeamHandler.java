package irille.view.SVS.BaseScore;

public class TradeIsTeamHandler implements SVSHandler<Integer> {
  // 是否有独立团队基础分计算
  @Override
  public int getScore(Object v) {
    if (null == v) return 0;
    Integer value = (Integer) v;
    if (value == 1) return 3;
    else return 0;
  }
}
