package irille.Dao;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeetingAuditRelease;
import irille.Entity.OdrerMeetings.OrderMeetingExhibition;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.svr.Env;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrSupplier;
import irille.platform.omt.View.ApplicationsView;
import irille.view.Page;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMeetingAuditReleaseDao {
    /**
     * @Description: 商家申请 参加订购会
     * @date 2018/11/19 20:09
     * @anthor wilson zhang
     */
    public static class insertOdr extends IduIns<insertOdr, OrderMeetingAuditRelease> {
        @Override
        public void before() {
            getB().setCreatedTime(Env.getTranBeginTime());
            getB().setStatus(OrderMeetingAuditStatus.PENDING.getLine().getKey());
            super.before();
        }
    }

    /**
     * @Description: (平台)获取发布订购会审核列表
     * *@date 2019/1/3 10:13
     * *@anthor zjl
     */
    public Page getApplications(Integer start, Integer limit, String omtName, Integer status) {
        OrderMeetingAuditRelease.TB.getCode();
        SQL sql = new SQL();
        sql.SELECT(OrderMeetingAuditRelease.T.PKEY,"pkey");//审核pkey
        sql.SELECT(OrderMeeting.T.PKEY,"omtpkey");//omtpkey
        sql.SELECT(UsrSupplier.T.NAME,"company");//公司名称
        sql.SELECT(PltCountry.T.NAME,"country");//所在国家
        sql.SELECT(OrderMeetingExhibition.T.NAME,"omeName");//订购会展厅
        sql.SELECT(UsrSupplier.T.CONTACTS);//联系人
        sql.SELECT(UsrSupplier.T.EMAIL);//联系方式(邮箱)
        sql.SELECT(UsrSupplier.T.IS_AUTH);//是否认证
        sql.SELECT(UsrSupplier.T.CERT_PHOTO);//营业执照(资质证书)
        sql.SELECT(OrderMeetingAuditRelease.T.STATUS);//审核状态
        sql.FROM(OrderMeetingAuditRelease.class);
        sql.LEFT_JOIN(OrderMeeting.class, OrderMeeting.T.PKEY, OrderMeetingAuditRelease.T.ODRMEETING);
        sql.LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, OrderMeeting.T.SUPPLIERID);
        sql.LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, UsrSupplier.T.COUNTRY);
        sql.LEFT_JOIN(OrderMeetingExhibition.class, OrderMeetingExhibition.T.PKEY, OrderMeeting.T.EXHIBITION);
        if (omtName != null) {
            sql.WHERE(OrderMeeting.T.NAME, "=?", omtName);
        }
        if (status != null) {
            sql.WHERE(OrderMeetingAuditRelease.T.STATUS, "=?", status);
        }
        Integer count = Query.sql(sql).queryCount();
        List<ApplicationsView> list = Query.sql(sql).queryMaps().stream().map(o -> {
            ApplicationsView view = new ApplicationsView();
            view.setPkey(Integer.valueOf(String.valueOf(o.get("pkey"))));
            view.setOmtpkey(Integer.valueOf(String.valueOf(o.get("omtpkey"))));
            view.setCompanyName(String.valueOf(o.get("company")));
            view.setCountry(String.valueOf(o.get("country")));
            view.setOmtShowroom(String.valueOf(o.get("omeName")));
            view.setContact(String.valueOf(o.get(UsrSupplier.T.CONTACTS.getFld().getCodeSqlField())));
            view.setContactInformation(String.valueOf(o.get(UsrSupplier.T.EMAIL.getFld().getCodeSqlField())));
            view.setWhetherCertification(Byte.valueOf(String.valueOf(o.get(UsrSupplier.T.IS_AUTH.getFld().getCodeSqlField()))));
            view.setImage(String.valueOf(o.get(UsrSupplier.T.CERT_PHOTO.getFld().getCodeSqlField())));
            view.setStatus(Byte.valueOf(String.valueOf(o.get(OrderMeetingAuditRelease.T.STATUS.getFld().getCodeSqlField()))));
            return view;
        }).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }
}
