package irille.shop.odr;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.PackageBase;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.shop.plt.Plt_ConfPackage;

public class Odr extends PackageBase {
    private static final Log LOG = new Log(Odr.class);
    public static final Odr INST = new Odr();
    public static TbBase TB = new TbBase<Tb>(Odr.class, "订单管理");

    private Odr() {
    }

    @Override
    public SysModule initModule() {
        return iuModule(Odr.TB, "odr-订单管理-300");
    }

    @Override
    public void initTbMsg() {
        addTb(1, OdrOrder.class);
        addTb(2, OdrOrderLine.class);
        addTb(3, OdrHistory.class);
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

    public enum OdrState implements IEnumOpt {//@formatter:off
        WAIT(0, "待付款"), WAITCONFIRM(1, "等待确认付款"), ERROR(2, "付款错误"), WAITDELIVER(3, "等待发货"), DELIVER(4, "已发货"), COMPLETE(5, "完成订单"), CANCEL(6, "已取消订单"), DELETED(7, "已删除");
        public static final String NAME = "订单状态";
        public static final OdrState DEFAULT = WAIT; // 定义缺省值
        private EnumLine _line;

        private OdrState(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        @Override
        public EnumLine getLine() {
            // TODO Auto-generated method stub
            return _line;
        }

    }//@formatter:on

    public enum CancelType implements IEnumOpt {//@formatter:off
        NOSTOCK(0, "缺货"), NOEFFECTIVEODR(1, "不是有效订单"), BUYERREQ(2, "买家要求"), OTHER(3, "其他原因");
        public static final String NAME = "取消原因";
        public static final CancelType DEFAULT = NOSTOCK; // 定义缺省值
        private EnumLine _line;

        private CancelType(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        @Override
        public EnumLine getLine() {
            return _line;
        }

    }//@formatter:on

    public enum PayType implements IEnumOpt {//@formatter:off
        FST(0, "TT"), SEND(1, "支付宝"), THREE(2, "微信");
        public static final String NAME = "支付方式";
        public static final PayType DEFAULT = FST; // 定义缺省值
        private EnumLine _line;

        private PayType(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        @Override
        public EnumLine getLine() {
            // TODO Auto-generated method stub
            return _line;
        }

    }//@formatter:on


    public enum OdrType implements IEnumOpt {//@formatter:off
        STATETWO(0, "普通订单"), STATEONE(1, "联合采购订单");
        public static final String NAME = "订单类型";
        public static final OdrType DEFAULT = STATETWO; // 定义缺省值
        private EnumLine _line;

        private OdrType(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        @Override
        public EnumLine getLine() {
            // TODO Auto-generated method stub
            return _line;
        }
    }//@formatter:on

    public enum ErrMsgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索
        // @formatter:off
        dataWrong("数据错误");
        private String _msg;

        private ErrMsgs(String msg) {
            _msg = msg;
        }

        public String getMsg() {
            return _msg;
        }
    } // @formatter:on


}
