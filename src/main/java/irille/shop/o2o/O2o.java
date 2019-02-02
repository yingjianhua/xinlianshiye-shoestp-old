package irille.shop.o2o;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.shop.plt.Plt_ConfPackage;

public class O2o extends PackageBase {
    private static final Log LOG = new Log(O2o.class);
    public static final O2o INST = new O2o();
    public static TbBase TB = new TbBase<Tb>(O2o.class, "O2O");

    private O2o() {
    }

    @Override
    public SysModule initModule() {
        return iuModule(O2o.TB, "o2o-O2O商品-1000");
    }

    @Override
    public void initTbMsg() {

    }

    @Override
    public void initTranData() {
    }

    public int getPackageId() {
        return Plt_ConfPackage.INST.getPackageId(getClass());
    }

    /**
     * 初始化，在运行期间仅执行一次
     */
    public void initOnlyOne() { // 初始化方法，在每次启动时执行一次
        super.initOnlyOne();
    }

}
