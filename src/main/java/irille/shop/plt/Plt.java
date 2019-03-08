package irille.shop.plt;

import irille.Entity.Activity.ActivityInfo;
import irille.Entity.Activity.SupGoogleView;
import irille.Entity.Contact.Contact;
import irille.Entity.Contact.ContactGroup;
import irille.Entity.NewInquiry.NewInquiry;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_JoinInfo;
import irille.Entity.O2O.O2O_PrivateExpoPdt;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.Pk.PkCompetitionData;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultGroup;
import irille.Entity.RFQ.RFQConsultGroupRelation;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.RFQPurchaseContact;
import irille.Entity.RFQ.RFQPurchaseContactGroup;
import irille.Entity.pm.PMMessage;
import irille.Entity.pm.PMRead;
import irille.Entity.pm.PMTemplate;
import irille.Entity.SVS.SVSInfo;
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

public class Plt extends PackageBase {
    private static final Log LOG = new Log(Plt.class);
    public static final Plt INST = new Plt();
    public static TbBase TB = new TbBase<Tb>(Plt.class, "平台管理"); // 定义公共的Fld对象用

    private Plt() {
    }

    public static void main(String[] args) {
        StartInitServlet.initBeanLoad();
        PrvRoleAct.TB.getCode();
        Plt_ConfPackage.INST.install();
    }

    @Override
    public SysModule initModule() {
        return iuModule(Plt.TB, "plt-平台管理-100");
    }

    @Override
    public void initTbMsg() {
        addTb(1, PltPay.class);
        addTb(2, PltErate.class);
        addTb(3, PltConfig.class);
        addTb(10, PltCountry.class);
        addTb(11, PltProvince.class);
        addTb(12, PltCountryFreight.class);
        addTb(20, PltFreight.class);
        addTb(21, PltFreightLine.class);
        addTb(30, PltTrantslate.class);
        addTb(40, PltFreightSeller.class);
        addTb(41, PltFreightSellerLine.class);
        addTb(43, ActivityInfo.class);
        addTb(44, SupGoogleView.class);
        addTb(46, PkCompetitionData.class);
        addTb(47, NewInquiry.class);
        addTb(48, O2O_Activity.class);
        addTb(49, O2O_Product.class);
        addTb(50, O2O_PrivateExpoPdt.class);
        addTb(51, O2O_JoinInfo.class);
        addTb(60, RFQConsult.class);
        addTb(61, RFQConsultMessage.class);
        addTb(62, RFQConsultRelation.class);
        addTb(63, Contact.class);
        addTb(64, ContactGroup.class);
        addTb(65, RFQPurchaseContact.class);
        addTb(66, RFQPurchaseContactGroup.class);
        addTb(67, RFQConsultGroup.class);
        addTb(68, RFQConsultGroupRelation.class);
        addTb(69, PMMessage.class);
        addTb(70, PMRead.class);
        addTb(71, PMTemplate.class);
        addTb(72, SVSInfo.class);
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

    public enum OOpt implements IEnumOpt {// @formatter:off
        ONE(0, "是"), TWO(1, "否");
        public static final String NAME = "网站默认货币";
        public static final OOpt DEFAULT = TWO; // 定义缺省值
        private EnumLine _line;

        private OOpt(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        public EnumLine getLine() {
            return _line;
        }
    } // @formatter:on

    public enum OOpt2 implements IEnumOpt {// @formatter:off
        ONE(0, "是"), TWO(1, "否");
        public static final String NAME = "后台默认货币";
        public static final OOpt2 DEFAULT = TWO; // 定义缺省值
        private EnumLine _line;

        private OOpt2(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        public EnumLine getLine() {
            return _line;
        }
    } // @formatter:on

    public enum WeightType implements IEnumOpt {// @formatter:off
        WEIGHT(0, "重量"), VOLUME(1, "体积"), QUANTITY(2, "件数");
        public static final String NAME = "重量计算方式选择";
        public static final WeightType DEFAULT = WEIGHT; // 定义缺省值
        private EnumLine _line;

        private WeightType(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        public EnumLine getLine() {
            // TODO Auto-generated method stub
            return _line;
        }
    }// @formatter:on

    public enum ErrMsgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索
        // @formatter:off
        uniqueErr("记录【{0}】已存在，不可操作！"), lowPriceErr("【{0}】不能低于【{1}】"), notNull(
                "【{0}】不能为空"), last("最后一条【{0}】不可删除");
        private String _msg;

        private ErrMsgs(String msg) {
            _msg = msg;
        }

        public String getMsg() {
            return _msg;
        }
    } // @formatter:on

}
