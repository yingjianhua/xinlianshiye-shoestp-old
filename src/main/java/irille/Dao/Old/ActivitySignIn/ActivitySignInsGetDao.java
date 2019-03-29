package irille.Dao.Old.ActivitySignIn;

import java.util.List;

import irille.Entity.As.PKContest;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.view.Page;

public class ActivitySignInsGetDao {
  public Page getPKContestList(Integer start, Integer limit) {
    if (start != null) {
      start = 0;
    }
    if (limit != null) {
      start = 0;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(PKContest.class).FROM(PKContest.class);
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<PKContest> pks = Query.sql(sql).queryList(PKContest.class);
    return new Page(pks, start, limit, count);
  }
}
