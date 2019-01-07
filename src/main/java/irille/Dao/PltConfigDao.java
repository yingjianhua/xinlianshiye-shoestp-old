package irille.Dao;

import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.plt.PltConfig;

import java.util.HashMap;
import java.util.Map;

public class PltConfigDao {
    public Map<Integer,Map<String,String>> getLanguageList() {
        SQL sql = new SQL();
        sql.SELECT(PltConfig.T.PKEY);
        sql.SELECT(PltConfig.T.VARIABLE);
        sql.SELECT(PltConfig.T.VALUE);
        sql.FROM(PltConfig.class);
        PltConfig pltConfig = Query.sql(sql).query(PltConfig.class);
        Map<String,String> map = new HashMap<>();
        Map<Integer,Map<String,String>> maps = new HashMap<>();
        if(pltConfig.getValue()!=null){
            map.put(pltConfig.getVariable(),pltConfig.getValue());
        }
        maps.put(pltConfig.getPkey(),map);
        return maps;
    }
}
