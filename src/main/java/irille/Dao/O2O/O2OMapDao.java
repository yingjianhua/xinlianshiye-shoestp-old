package irille.Dao.O2O;

import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_Map;
import irille.Entity.O2O.O2O_Product;
import irille.core.sys.Sys;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;

import java.util.List;

public class O2OMapDao {

    public List<O2O_Map> list(){
        SQL sql = new SQL();
        sql.SELECT(O2O_Map.class).FROM(O2O_Map.class).WHERE(O2O_Map.T.IS_DELETE,"=?", Sys.OYn.NO.getLine().getKey());
        return Query.sql(sql).queryList(O2O_Map.class);
    }
}
