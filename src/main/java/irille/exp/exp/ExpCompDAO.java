package irille.exp.exp;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduUpdLines;

public class ExpCompDAO  {
	public static final Log LOG = new Log(ExpCompDAO.class);

	public static class Ins extends IduInsLines<Ins, ExpComp, ExpCompLine> {

		@Override
		public void after() {
			insLine(getB(), getLines(), ExpCompLine.T.MAIN.getFld());
			super.after();
		}
	}

	public static class Upd extends IduUpdLines<Upd, ExpComp, ExpCompLine> {
		@Override
		public void before() {
			ExpComp dbBean = loadThisBeanAndLock();
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), ExpComp.T.CODE);
			setB(dbBean);
			updLine(getB(), getLines(), ExpCompLine.T.MAIN.getFld());
		}
	}

	public static class Del extends IduDel<Del, ExpComp> {
		@Override
		public void before() {
			super.before();
			delLine(getLines(ExpCompLine.T.MAIN, getB().getPkey()));
		}
	}
	

}