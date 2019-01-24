package irille.Entity.OdrerMeetings;

import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Omt_Install extends InstallBase {
    public static final Omt_Install INST = new Omt_Install();

    @Override
    public void initMenu(SysMenuDAO m) {
        m.proc(OrderMeetingAuditRelease.TB, 10, null);
        m.proc(OrderMeetingExhibition.TB, 20, null);
        m.proc(OrderMeeting.TB, 30, null);
        m.proc(OrderMeetingAudit.TB, 40, null);
        m.proc(OrderMeetingOrder.TB, 50, null);
    }
}

