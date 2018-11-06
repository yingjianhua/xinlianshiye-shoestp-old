//Created on 2005-10-24
package irille.exp.exp;

import irille.core.prv.PrvRoleAct;
import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.svr.StartInitServlet;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;

public class Exp extends PackageBase {
	private static final Log LOG = new Log(Exp.class);
	public static final Exp INST = new Exp();
	public static TbBase TB = new TbBase<Tb>(Exp.class, "测试模块"); // 定义公共的Fld对象用

	private Exp() {
	}

	public static void main(String[] args) {
		StartInitServlet.initBeanLoad();
		PrvRoleAct.TB.getCode();
//		Exp_ConfPackage.INST.install();
	}

	@Override
	public void initTbMsg() { // 初始化表信息
//		addTb(1, ExpBase.class);
//		addTb(2, ExpKind.class);
//		addTb(3, ExpComp.class);
	}

	public void initTranData() { //初始化PrvTranData表数据
	}

	@Override
	public SysModule initModule() {
		return iuModule(Exp.TB, "exp-测试管理-800");
	}
	
	@Override
	public int getPackageId() {
		return Exp_ConfPackage.INST.getPackageId(getClass());
	}

	/**
	 * 初始化，在运行期间仅执行一次
	 */
	public void initOnlyOne() { // 初始化方法，在每次启动时执行一次
		super.initOnlyOne();
	}
	
	public enum OOpt implements IEnumOpt {//@formatter:off
		ONE(0,"选项一1"),TWO(1,"选项二"),THREE(2,"选项三");
		public static final String NAME="测试选项";
		public static final OOpt DEFAULT = ONE; // 定义缺省值
		private EnumLine _line;
		private OOpt(int key, String name) {_line=new EnumLine(this,key,name);	}
		public EnumLine getLine(){return _line;	}
	}		//@formatter:on

}
