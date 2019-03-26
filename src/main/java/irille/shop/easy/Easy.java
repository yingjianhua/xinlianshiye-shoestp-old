package irille.shop.easy;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.shop.plt.Plt_ConfPackage;

public class Easy extends PackageBase {
  private static final Log LOG = new Log(Easy.class);
  public static final Easy INST = new Easy();
  public static TbBase TB = new TbBase<Tb>(Easy.class, "罗马尼亚订单");

  private Easy() {}

  @Override
  public SysModule initModule() {
    return iuModule(Easy.TB, "easy-罗马尼亚订单-1000");
  }

  @Override
  public void initTbMsg() {
    addTb(1, EasyOdr.class);
    addTb(2, EasyOdrline.class);
  }

  @Override
  public void initTranData() {}

  public int getPackageId() {
    return Plt_ConfPackage.INST.getPackageId(getClass());
  }

  /** 初始化，在运行期间仅执行一次 */
  public void initOnlyOne() { // 初始化方法，在每次启动时执行一次
    super.initOnlyOne();
  }
}
