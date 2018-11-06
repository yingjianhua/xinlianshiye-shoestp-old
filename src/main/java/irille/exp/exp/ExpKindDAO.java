package irille.exp.exp;

import irille.pub.Log;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;

public class ExpKindDAO {
	public static final Log LOG = new Log(ExpKindDAO.class);

	public static class Ins extends IduIns<Ins, ExpKind> {
		@Override
		public void before() {
			super.before();
			ExpKind model = ExpKind.chkUniqueName(false, getB().getName());
			if (model != null)
				throw LOG.err("nullErr", "【{0}】类型已存在，不可新增", getB().getName());
		}
	}

	public static class Upd extends IduUpd<Upd, ExpKind> {
		@Override
		public void before() {
			super.before();
			ExpKind model = ExpKind.chkUniqueName(false, getB().getName());
			if (model != null)
				throw LOG.err("nullErr", "【{0}】类型已存在，不可提交", getB().getName());
		}
	}

	public static class Del extends IduDel<Del, ExpKind> {
	}

}
