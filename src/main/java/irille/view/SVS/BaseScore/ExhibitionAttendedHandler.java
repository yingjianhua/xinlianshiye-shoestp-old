package irille.view.SVS.BaseScore;

import java.util.List;

import irille.view.SVS.SVSExhibitionView;

public class ExhibitionAttendedHandler implements SVSHandler<List<SVSExhibitionView>> {
  // 展会基础分值计算
  @Override
  public int getScore(Object v) {
    if (null == v) return 0;
    List<SVSExhibitionView> value = (List<SVSExhibitionView>) v;
    if (value.size() > 0) return 5;
    else return 0;
  }
}
