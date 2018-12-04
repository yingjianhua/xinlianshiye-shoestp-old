package irille.Dao;

import irille.Entity.newInq.NewInquiry;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.newInq.newInqView;

import java.util.List;
import java.util.stream.Collectors;

public class newInqDao {
    public Page getList(Integer start, Integer limit, String name, Integer supplierId) {
        System.out.println(name);
        SQL sql = new SQL() {
            {
                SELECT(NewInquiry.class)
                        .SELECT(UsrSupplier.T.NAME, "supId")
                        .FROM(NewInquiry.class)
                        .WHERE(NewInquiry.T.SUPPLIERID, "=?", supplierId)
                        .LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, NewInquiry.T.SUPPLIERID);
                if (name != null) {
                    WHERE(NewInquiry.T.NAME, "like '%" + name + "%' ");
                }
            }
        };
        Integer count = Query.sql(sql).queryCount();
        List<newInqView> viewList = Query.sql(sql).queryMaps().stream().map(o -> {
            newInqView view = new newInqView();
            view.setSupplierName(String.valueOf(o.get(UsrSupplier.T.NAME.getFld().getCodeSqlField())));
            view.setName(String.valueOf(o.get(NewInquiry.T.NAME.getFld().getCodeSqlField())));
            view.setEmail(String.valueOf(o.get(NewInquiry.T.EMAIL.getFld().getCodeSqlField())));
            view.setDetail(String.valueOf(o.get(NewInquiry.T.DETAIL.getFld().getCodeSqlField())));
            return view;
        }).collect(Collectors.toList());
        return new Page(viewList, start, limit, count);
    }
}
