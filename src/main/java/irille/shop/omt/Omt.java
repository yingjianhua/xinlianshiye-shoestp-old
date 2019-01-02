package irille.shop.omt;

import irille.core.prv.PrvRoleAct;
import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.svr.StartInitServlet;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.shop.plt.Plt_ConfPackage;


public class Omt extends PackageBase {
    private static final Log LOG = new Log(Omt.class);
    public static final Omt INST= new Omt();
    public static TbBase TB = new TbBase<Tb>(Omt.class,"平台管理");
    private Omt(){

    }
    public static void main(String[] args) {
        StartInitServlet.initBeanLoad();
        PrvRoleAct.TB.getCode();
        Plt_ConfPackage.INST.install();
    }
    @Override
    public void initTbMsg() {

    }

    @Override
    public void initTranData() {

    }

    @Override
    public SysModule initModule() {
        return iuModule(Omt.TB, "plt-平台管理-700");
    }
}
