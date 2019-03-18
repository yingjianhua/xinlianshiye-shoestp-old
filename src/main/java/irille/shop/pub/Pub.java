// Created on 2005-10-24
package irille.shop.pub;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.shop.plt.Plt_ConfPackage;

public class Pub extends PackageBase {
  private static final Log LOG = new Log(Pub.class);
  public static final Pub INST = new Pub();
  public static TbBase TB = new TbBase<Tb>(Pub.class, "pub"); // 定义公共的Fld对象用

  private Pub() {}

  @Override
  public void initTbMsg() { // 初始化表信息
    addTb(1, PubTrantslate.class);
  }

  public void initTranData() { // 初始化PrvTranData表数据
  }

  @Override
  public SysModule initModule() {
    return iuModule(Pub.TB, "pub-pub管理-800");
  }

  @Override
  public int getPackageId() {
    return Plt_ConfPackage.INST.getPackageId(getClass());
  }

  /** 初始化，在运行期间仅执行一次 */
  public void initOnlyOne() { // 初始化方法，在每次启动时执行一次
    super.initOnlyOne();
  }
}
