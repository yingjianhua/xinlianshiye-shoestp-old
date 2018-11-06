package irille.exp.exp;

import org.apache.commons.beanutils.PropertyUtils;

import irille.pub.Log;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;

public class ExpBaseDAO {
	public static final Log LOG = new Log(ExpBaseDAO.class);

	public static class Ins extends IduIns<Ins, ExpBase> {
		@Override
		public void before() {
			super.before();
//			getB().setEnabled(Sys.OEnabled.TRUE.getLine().getKey());//启停设置方法一
			getB().stEnabled(true);//启停设置方法二
//			getB().setOpt(Exp.OOpt.ONE.getLine().getKey());//选项设置方法一
			getB().stOpt(Exp.OOpt.ONE);//选项设置方法二
			getB().setCreateBy(getUser().getPkey());
			getB().setCreateTime(Env.getSystemTime());
		}
	}

	public static class Upd extends IduUpd<Upd, ExpBase> {
		@Override
		public void before() {
			super.before();
			ExpBase model = loadThisBeanAndLock();
			model.setName(getB().getName());
			model.setOpt(getB().getOpt());
			model.setKind(getB().getKind());
			setB(model);
		}
	}

	public static class Del extends IduDel<Del, ExpBase> {
	}
	
	public static class Todo extends IduOther<Todo, ExpBase> {
		@Override
		public void run() {
			super.run();
			getB().stOpt(Exp.OOpt.TWO);
			getB().upd();
		}
	}
	
	public static class Undo extends IduOther<Undo, ExpBase> {
		@Override
		public void run() {
			super.run();
			getB().stOpt(Exp.OOpt.THREE);
			getB().upd();
		}
	}

}
