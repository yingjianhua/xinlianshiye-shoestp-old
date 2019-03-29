package irille.shop.cnt;

import irille.core.prv.PrvRoleAct;
import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.PackageBase;
import irille.pub.svr.StartInitServlet;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;

public class Cnt extends PackageBase {
  private static final Log LOG = new Log(Cnt.class);
  public static final Cnt INST = new Cnt();
  public static TbBase TB = new TbBase<Tb>(Cnt.class, "内容管理");

  private Cnt() {}

  public static void main(String[] args) {
    StartInitServlet.initBeanLoad();
    PrvRoleAct.TB.getCode();
    Cnt_ConfPackage.INST.install();
  }

  @Override
  public SysModule initModule() {
    return iuModule(Cnt.TB, "cnt-内容管理-200");
  }

  @Override
  public void initTbMsg() {
    addTb(1, CntAd.class);
    addTb(2, CntLink.class);
    addTb(3, CntAdLine.class);
    addTb(4, CntArticle.class);
    addTb(5, CntArticleCategory.class);
    addTb(7, CntSglPage.class);
    addTb(8, CntSglPageCategory.class);
    addTb(13, CntMagazine.class);
  }

  @Override
  public void initTranData() {}

  public int getPackageId() {
    return Cnt_ConfPackage.INST.getPackageId(getClass());
  }

  /** 初始化，在运行期间仅执行一次 */
  public void initOnlyOne() { // 初始化方法，在每次启动时执行一次
    super.initOnlyOne();
  }

  public enum OShowtype implements IEnumOpt { // @formatter:off
    ONE(0, "渐显"),
    TWO(1, "上滚动"),
    THREE(2, "下滚动");
    public static final String NAME = "显示方式";
    public static final OShowtype DEFAULT = ONE; // 定义缺省值
    private EnumLine _line;

    private OShowtype(int key, String name) {
      _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
      return _line;
    }
  } // @formatter:on

  public enum ErrMsgs implements IMsg { // 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
    uniqueErr("记录【{0}】已存在，不可操作！"),
    ;
    private String _msg;

    private ErrMsgs(String msg) {
      _msg = msg;
    }

    public String getMsg() {
      return _msg;
    }
  } // @formatter:on
}
