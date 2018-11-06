package irille.shop.plt;

import irille.pub.bean.Query;
import irille.shop.plt.PltFreightLine.T;

import java.util.List;

public class PltFreightLineDAO {

	/**
	 * 根据运费模板查询配送区域列表
	 * @author yingjianhua
	 */
	public static List<PltFreightLine> listByFreight(Integer freight) {
		return Query.SELECT(PltFreightLine.class).WHERE(T.MAIN, "=?", freight).queryList();
	}
}
