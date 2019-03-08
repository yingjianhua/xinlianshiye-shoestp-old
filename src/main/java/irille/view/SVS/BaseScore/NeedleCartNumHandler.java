package irille.view.SVS.BaseScore;

import java.io.Serializable;

public class NeedleCartNumHandler implements SVSHandler<Integer> {

	@Override
	public int getScore(Object v) {
		if (null == v)
			return 0;
		Integer value = (Integer) v;
		if (value < 50) {
			return 1;
		} else if (value < 70) {
			return 2;
		} else {
			return 3;
		}
	}

}
