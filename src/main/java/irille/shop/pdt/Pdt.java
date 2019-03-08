//Created on 2005-10-24
package irille.shop.pdt;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.shop.plt.Plt_ConfPackage;

public class Pdt extends PackageBase {
    private static final Log LOG = new Log(Pdt.class);
    public static final Pdt INST = new Pdt();
    public static TbBase TB = new TbBase<Tb>(Pdt.class, "产品"); // 定义公共的Fld对象用

    private Pdt() {
    }

    @Override
    public void initTbMsg() { // 初始化表信息
        addTb(1, PdtProduct.class);
        addTb(2, PdtCat.class);
        addTb(3, PdtAttrCat.class);
        addTb(4, PdtAttr.class);
        addTb(5, PdtColor.class);
        addTb(6, PdtSize.class);
        addTb(7, PdtComment.class);
        addTb(8, PdtAttrLine.class);
        addTb(9, PdtConsultPdtList.class);
        addTb(10, PdtSpec.class);
    }

    public void initTranData() { //初始化PrvTranData表数据
    }

    @Override
    public SysModule initModule() {
        return iuModule(Pdt.TB, "pdt-产品管理-500");
    }

    @Override
    public int getPackageId() {
        return Plt_ConfPackage.INST.getPackageId(getClass());
    }

    /**
     * 初始化，在运行期间仅执行一次
     */
    public void initOnlyOne() { // 初始化方法，在每次启动时执行一次
        super.initOnlyOne();
    }

    /*
     * 产品属性的录入方式
     */
    public enum OTypingType implements IEnumOpt {
        HANDLE(0, "手工录入"), SELECT(1, "列表选择");
        public static final String NAME = "录入方式";
        public static final OTypingType DEFAULT = HANDLE;
        private EnumLine _line;

        private OTypingType(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        public EnumLine getLine() {
            return _line;
        }
    }

    /*
     * 脱销状态
     */
    public enum OStockOut implements IEnumOpt {
        SOLDOUT(0, "脱销"), NOTICE(1, "到货通知");
        public static final String NAME = "脱销状态";
        public static final OStockOut DEFAULT = SOLDOUT;
        private EnumLine _line;

        private OStockOut(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        public EnumLine getLine() {
            return _line;
        }
    }

    /*
     * 销售状态
     */
    public enum OState implements IEnumOpt {
        ON(1, "上架"), OFF(0, "下架"), DELETE(2, "删除"),MERCHANTDEL(3,"商家删除");
        public static final String NAME = "销售状态";
        public static final OState DEFAULT = ON;
        private EnumLine _line;

        private OState(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        public EnumLine getLine() {
            return _line;
        }
    }

    public enum OSatisfaction implements IEnumOpt {
        ONE(1, "一星评级"), TWO(2, "二星评级"), THREE(3, "三星评级"), FOUR(4, "四星评级"), FIVE(5, "五星评级");
        public static final String NAME = "满意度";
        public static final OSatisfaction DEFAULT = THREE;
        private EnumLine _line;

        private OSatisfaction(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        public EnumLine getLine() {
            return _line;
        }
    }

    /*
     * 产品类型
     */
    public enum OProductType implements IEnumOpt {
        GENERAL(0, "普通产品"),
        GROUP(1, "联合采购产品"),
        GATHER(2, "采集商品"),
        PrivateExpo(3, "私人展会商品"),
        O2O(4, "O2O商品"),;
        public static final String NAME = "产品种类";
        public static final OProductType DEFAULT = GENERAL;
        private EnumLine _line;

        private OProductType(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        public EnumLine getLine() {
            return _line;
        }
    }

    public enum OVer implements IEnumOpt {
    	 ELSE(0,"其他"),NEW_1(1,"新版本");
    	 public static final String NAME = "类型";
         public static final OVer DEFAULT = ELSE;
         private EnumLine _line;

         private OVer(int key, String name) {
             _line = new EnumLine(this, key, name);
         }

         public EnumLine getLine() {
             return _line;
         }
    }
    
    public enum OSizeType implements IEnumOpt {
    	USA(1,"美码"),EU(2,"欧码");
    	public static final String NAME = "类型";
        public static final OSizeType DEFAULT = EU;
        private EnumLine _line;

        private OSizeType(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        public EnumLine getLine() {
            return _line;
        }
    }
    
}
