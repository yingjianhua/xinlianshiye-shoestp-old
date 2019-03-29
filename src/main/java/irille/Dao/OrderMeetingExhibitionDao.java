package irille.Dao;

import java.util.List;
import java.util.stream.Collectors;

import irille.Entity.OdrerMeetings.OrderMeetingExhibition;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.view.Manage.OdrMeeting.OrderMeetingExhibitionView;

public class OrderMeetingExhibitionDao {
  /**
   * @Description: 商家 展廳列表
   *
   * @date 2018/11/19 20:09
   * @anthor wilson zhang
   */
  public static List<OrderMeetingExhibitionView> listomev() {
    SQL sql =
        new SQL() {
          {
            SELECT(
                    OrderMeetingExhibition.T.PKEY,
                    OrderMeetingExhibition.T.ADDRESS,
                    OrderMeetingExhibition.T.NAME,
                    OrderMeetingExhibition.T.COUNTRY)
                .FROM(OrderMeetingExhibition.class);
          }
        };
    List<OrderMeetingExhibitionView> list =
        Query.sql(sql).queryMaps().stream()
            .map(
                o -> {
                  OrderMeetingExhibitionView ome = new OrderMeetingExhibitionView();
                  ome.setId(
                      (Integer) o.get(OrderMeetingExhibition.T.PKEY.getFld().getCodeSqlField()));
                  ome.setAddress(
                      (String) o.get(OrderMeetingExhibition.T.ADDRESS.getFld().getCodeSqlField()));
                  ome.setName(
                      (String) o.get(OrderMeetingExhibition.T.NAME.getFld().getCodeSqlField()));
                  ome.setCountryid(
                      (Integer) o.get(OrderMeetingExhibition.T.COUNTRY.getFld().getCodeSqlField()));
                  return ome;
                })
            .collect(Collectors.toList());
    return list;
  }
}
