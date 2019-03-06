package irille.shop.plt;

import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.I18NSQL;
import irille.pub.bean.sql.SQL;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.PltProvince.T;
import irille.view.plt.ProvinceView;

import java.util.ArrayList;
import java.util.List;

public class PltProvinceDAO {
	
	public static List<PltProvince> list(Language lang) {
		SQL sql = new I18NSQL(lang){{
			SELECT(T.PKEY, T.NAME, T.SHORT_NAME, T.MAIN);
			FROM(PltProvince.class);
		}};
		return Query.sql(sql).queryList(PltProvince.class);
	}
	
	/**
	 * 根据国家查询所有省份信息
	 * @param country 国家id
	 * @author yingjianhua
	 */
	public static List<ProvinceView> listByCountry(Integer country) {
		List<ProvinceView> views = new ArrayList<>();
		for(PltProvince line:Query.SELECT(PltProvince.class).WHERE(T.MAIN, "=?", country).queryList()) {
			ProvinceView view = new ProvinceView();
			view.setId(line.getPkey());
			view.setName(line.getName());
			view.setShortName(line.getShortName());
			views.add(view);
		}
		return views;
	}
}
