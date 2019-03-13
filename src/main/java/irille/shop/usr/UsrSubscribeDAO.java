package irille.shop.usr;

import java.util.List;
import java.util.stream.Collectors;

import irille.platform.usr.View.UsrSubscribeActionView.UsrSubscribeView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.view.Page;

public class UsrSubscribeDAO {
  public static final Log LOG = new Log(UsrSubscribe.class);

  public static class Ins extends IduIns<Ins, UsrPurchaseLine> {
    public void before() {
      super.before();
    }
  }

  /** ———————————————————分割线(新平台)————————————————————————— */
  public static Page getSubscribeList(Integer start, Integer limit, String email) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(UsrSubscribe.class).FROM(UsrSubscribe.class);
            if (email != null) {
              WHERE(UsrSubscribe.T.EMAIL, "like '%" + email + "%'");
            }
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<UsrSubscribeView> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                o ->
                    new UsrSubscribeView() {
                      {
                        setId((Integer) o.get(UsrSubscribe.T.PKEY.getFld().getCodeSqlField()));
                        setEmail((String) o.get(UsrSubscribe.T.EMAIL.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  public static class deletes extends IduDel<deletes, UsrSubscribe> {
    @Override
    public void before() {
      super.before();
    }
  }

  public static void ins(String email) {
    UsrSubscribe usrSubscribe = new UsrSubscribe();
    usrSubscribe.setEmail(email);
    usrSubscribe.ins();
  }

  public static class upd extends IduOther<upd, UsrSubscribe> {
    @Override
    public void before() {}

    @Override
    public void valid() {}

    @Override
    public void run() {
      UsrSubscribe dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), UsrSubscribe.T.EMAIL);
      dbBean.upd();
      super.run();
    }
  }

  /** ———————————————————分割线(新平台)END————————————————————————— */
}
