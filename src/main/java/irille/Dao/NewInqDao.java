package irille.Dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.google.api.services.analyticsreporting.v4.model.OrderBy;

import irille.Entity.NewInquiry.NewInquiry;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.util.GetValue;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.newInq.newInqView;

public class NewInqDao {
  public Page getList(Integer start, Integer limit, String name, Integer supplierId) {
    SQL sql =
        new SQL() {
          {
            SELECT(NewInquiry.class)
                .SELECT(UsrSupplier.T.NAME, "supId")
                .FROM(NewInquiry.class)
                .WHERE(NewInquiry.T.SUPPLIERID, "=?", supplierId)
                .LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, NewInquiry.T.SUPPLIERID);
            if (name != null) {
              WHERE(NewInquiry.T.NAME, "like ?","%" + name + "%");
            }
            ORDER_BY(NewInquiry.T.CREATE_TIME,"desc");
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<newInqView> viewList =
        Query.sql(sql).queryMaps().stream()
            .map(
                o -> {
                  newInqView view = new newInqView();
                  view.setInqPkey(
                      Integer.valueOf(
                          String.valueOf(o.get(NewInquiry.T.PKEY.getFld().getCodeSqlField()))));
                  view.setSupplierName(String.valueOf(o.get("supId")));
                  view.setName(String.valueOf(o.get(NewInquiry.T.NAME.getFld().getCodeSqlField())));
                  view.setEmail(
                      String.valueOf(o.get(NewInquiry.T.EMAIL.getFld().getCodeSqlField())));
                  view.setDetail(
                      String.valueOf(o.get(NewInquiry.T.DETAIL.getFld().getCodeSqlField())));
                  view.setCreateTime(GetValue.get(o, NewInquiry.T.CREATE_TIME, Date.class, null));
                  return view;
                })
            .collect(Collectors.toList());
    return new Page(viewList, start, limit, count);
  }

  public void remove(Integer id) {
    SQL sql =
        new SQL() {
          {
            DELETE_FROM(NewInquiry.class).WHERE(NewInquiry.T.PKEY, "=?", id);
          }
        };
    Query.sql(sql).executeUpdate();
  }
}
