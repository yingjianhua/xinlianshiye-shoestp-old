package irille.Dao.O2O;

import java.util.List;

import irille.Entity.O2O.O2O_Map;
import irille.core.sys.Sys;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;

public class O2OMapDao {

  public List<O2O_Map> list() {
    SQL sql = new SQL();
    sql.SELECT(O2O_Map.class)
        .FROM(O2O_Map.class)
        .WHERE(O2O_Map.T.IS_DELETE, "=?", Sys.OYn.NO.getLine().getKey());
    return Query.sql(sql).queryList(O2O_Map.class);
  }
}
