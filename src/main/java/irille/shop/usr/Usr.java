//Created on 2005-10-24
package irille.shop.usr;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.PackageBase;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.shop.plt.Plt_ConfPackage;

public class Usr extends PackageBase {
	private static final Log LOG = new Log(Usr.class);
	public static final Usr INST = new Usr();
	public static TbBase TB = new TbBase<Tb>(Usr.class, "用户"); // 定义公共的Fld对象用

	private Usr() {
	}

	@Override
	public void initTbMsg() { // 初始化表信息
		addTb(1, UsrSupplierCategory.class);
		addTb(2, UsrSupplier.class);
		addTb(3, UsrProductCategory.class);
		addTb(5, UsrMemberLevel.class);
		addTb(6, UsrPurchase.class);
		addTb(7, UsrSupplierRole.class);
		addTb(8, UsrConsult.class);
		addTb(9, UsrConsultMessage.class);
		addTb(10, UsrConsultRelation.class);
		addTb(11, UsrMessages.class);
		addTb(12, UsrAccess.class);
		addTb(13, UsrCart.class);
		addTb(14, UsrFavorites.class);
		addTb(15, UsrPurchaseLine.class);
		addTb(16, UsrSubscribe.class);
		addTb(17, UsrSupIm.class);
		addTb(18, UsrSupplierRoleAct.class);

	}

	public void initTranData() { //初始化PrvTranData表数据
	}

	@Override
	public SysModule initModule() {
		return iuModule(Usr.TB, "usr-用户管理-400");
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
	 * 供应商是否已认证
	 */
	public enum OIsAuth implements IEnumOpt {
		NO(0,"未认证"),YES(1,"已认证");
		public static final String NAME="供应商认证";
		public static final OIsAuth DEFAULT = NO;
		private EnumLine _line;
		private OIsAuth(int key, String name) {_line=new EnumLine(this,key,name);}
		public EnumLine getLine(){return _line;	}
	}

	/*
	 * 供应商审核
	 * createBy liyichao
	 */
	public enum OStatus implements IEnumOpt {//@formatter:off
		INIT(0,"未审核"),APPR(1,"审核通过"),FAIL(2,"审核不通过");
		public static final String NAME="审核状态";
		public static final OStatus DEFAULT = INIT; // 定义缺省值
		private EnumLine _line;
		private OStatus(int key, String name) {_line=new EnumLine(this,key,name);	}
		public EnumLine getLine(){return _line;	}
	}		//@formatter:on

	/*
	 * 店铺审核
	 * @author: lingjian
	 * @Date: 2019/3/4 15:51
	 */
	public enum SStatus implements IEnumOpt {//@formatter:off
		DOWN(0,"关闭"),OPEN(1,"开启");
		public static final String NAME="店铺状态";
		public static final SStatus DEFAULT = DOWN; // 定义缺省值
		private EnumLine _line;
		private SStatus(int key, String name) {_line=new EnumLine(this,key,name);	}
		public EnumLine getLine(){return _line;	}
	}

	/**
	 * 我的收藏加入的类型
	 * @author liyichao
	 */
	public enum OJoinType implements IEnumOpt{//@formatter:off
		GENERAL(0,"普通商品"),GROUP(1,"联合采购商品"),;
		public static final String NAME = "类型";
		public static final OJoinType DEFAULT = GENERAL;
		private EnumLine _line;
		private OJoinType(int key, String name) {_line=new EnumLine(this,key,name);	}
		public EnumLine getLine(){return _line;	}
	}//@formatter:on

	public enum ErrMsgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索
		// @formatter:off
		emptyErr("记录{0}不存在，不可操作！"),
		copyErr("{0}已存在"),
		wrongErr("请输入营业执照时间"),
		noSubmit("请选择商品"),
		tooLess("{0}购买数量过少,需大于{1}"),
		wrongFormat("请输入正确的数量"),
		noLogin("未登录"),
		noAccess("提交数据有误"),
		differenctSup("商品不属于同一家"),
		dataWrong("上级分类未上架"),
		hasChild("该分类有下级分类,不可删除"),
		noUpCarriage("{0}未上架"),
		noChoose("请勾选商品"),
		soldOut("商品已售完");
		private String _msg;

		private ErrMsgs(String msg) {
		_msg = msg;
		}

		public String getMsg() {
		return _msg;
		}
	} // @formatter:on

	/**
	 * xiayan
	 * 用户地址簿地址类型
	 * @author xinlian
	 */
	public enum OAddress implements IEnumOpt{
		COMMON(0,"收货地址"),BILLED(1,"账单地址");
		public static final String NAME="收货地址类型";
		public static final OAddress DEFAULT = COMMON; // 定义缺省值
		private EnumLine _line;
		private OAddress(int key, String name) {_line=new EnumLine(this,key,name);}
		@Override
		public EnumLine getLine() {
			// TODO Auto-generated method stub
			return _line;
		}
	}

	/**
	 * @author liyichao
	 * IM代码使用类型
	 */
	public enum OIMType implements IEnumOpt{
		PHONE(0,"手机"),COMPUTER(1,"电脑"),DEVICE(2,"手机及电脑");
		public static final String NAME="IM类型";
		public static final OIMType DEFAULT = DEVICE; // 定义缺省值
		private EnumLine _line;
		private OIMType(int key, String name) {_line=new EnumLine(this,key,name);}
		@Override
		public EnumLine getLine() {
			return _line;
		}
	}

	/**
	 * @author chen
	 * 身份
	 */
	public enum IDType implements IEnumOpt{
		BUYNER(0,"买家"),SELLER(1,"卖家");
		public static final String NAME="身份类型";
		public static final IDType DEFAULT = BUYNER; // 定义缺省值
		private EnumLine _line;
		private IDType(int key, String name) {_line=new EnumLine(this,key,name);}
		@Override
		public EnumLine getLine() {
			return _line;
		}
	}


}
