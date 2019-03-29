package irille.shop.prm;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.PackageBase;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.shop.plt.Plt_ConfPackage;

/** @author liyichao */
public class Prm extends PackageBase {
  private static final Log LOG = new Log(Prm.class);
  public static final Prm INST = new Prm();
  public static TbBase TB = new TbBase<Tb>(Prm.class, "联合采购");

  @Override
  public void initTbMsg() { // 初始化表信息
    addTb(1, PrmGroupPurchase.class);
    addTb(2, PrmGroupOrder.class);
    addTb(3, PrmGroupOrderLine.class);
    addTb(4, PrmGroupPurchaseLine.class);
    // addTb(2, PrmGroupOrder.class);
  }

  @Override
  public void initTranData() {}

  @Override
  public SysModule initModule() {
    return iuModule(Prm.TB, "prm-联合采购-600");
  }

  public int getPackageId() {
    return Plt_ConfPackage.INST.getPackageId(getClass());
  }

  /** 初始化，在运行期间仅执行一次 */
  public void initOnlyOne() { // 初始化方法，在每次启动时执行一次
    super.initOnlyOne();
  }

  public enum OStatus implements IEnumOpt {
    NOTSTART(0, "未开始"),
    SOONSTART(1, "即将开始"),
    HAVEINHAND(2, "进行中"),
    SOONEND(3, "即将结束"),
    END(4, "已结束"),
    DELETE(5, "逻辑删除");
    public static final String NAME = "活动状态";
    public static final OStatus DEFAULT = NOTSTART;
    private EnumLine _line;

    private OStatus(int key, String name) {
      _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
      return _line;
    }
  }

  public enum OSend implements IEnumOpt {
    NOSEND(0, "未发送"),
    ALSEND(1, "已发送");
    public static final String NAME = "明细状态";
    public static final OSend DEFAULT = NOSEND;
    private EnumLine _line;

    private OSend(int key, String name) {
      _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
      return _line;
    }
  }

  public enum OPreTime implements IEnumOpt {
    THREEDAY(3, "提前三天"),
    SEVENDAY(7, "提前七天"),
    FIFTEENDAY(15, "提前十五天"),
    MONTH(30, "提前一个月");

    public static final String NAME = "提前预告";
    public static final OPreTime DEFAULT = MONTH;
    private EnumLine _line;

    private OPreTime(int key, String name) {
      _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
      return _line;
    }
  }

  public enum ErrMsgs implements IMsg {
    // @formatter:off
    EMPTYERR("该活动未产生订单"),
    EXISTS("该时间段已有活动进行,不可再添加"),
    NOSPEC("该商品未发布规格"),
    TIMEWRONG("【{0}】不能早于系统时间"),
    DATAWRONG("数据错误"),
    NOTBEGIN("【{0}】所属活动【{1}】"),
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
