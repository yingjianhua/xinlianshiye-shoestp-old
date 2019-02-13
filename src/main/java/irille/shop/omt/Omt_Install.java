package irille.shop.omt;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.core.sys.SysMenuDAO;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanBase;
import irille.pub.bean.InstallBase;
import irille.pub.svr.Env;
import irille.pub.svr.LoginUserMsg;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.*;
import irille.shop.plt.PltConfig.Variable;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Omt_Install extends InstallBase {

    public static final Omt_Install INST = new Omt_Install();

    @Override
    public void initMenu(SysMenuDAO m) {
        m.proc(OrderMeeting.TB, 20, null);
    }
}

