package irille.shop.plt;

import java.util.List;
import java.util.stream.Collectors;

import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.plt.PltProvinces.T;
import irille.view.plt.ProvincesView;
/**
 * 
 * @author chen
 *
 */
public class PltProvincesDao {
     
	public static List<ProvincesView> list() {
		SQL sql = new SQL() {{
			SELECT(T.PKEY, T.NAME);
			FROM(PltProvinces.class);
		}};
			
		
		List<PltProvinces> list=Query.sql(sql).queryList(PltProvinces.class);
		List<ProvincesView> views=list.stream().map(pro->{
			ProvincesView pv=new ProvincesView();
			pv.setId(pro.getPkey());
			pv.setName(pro.getName());
			return pv;
		}).collect(Collectors.toList());
		return views;
	}
}
