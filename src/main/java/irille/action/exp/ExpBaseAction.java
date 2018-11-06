package irille.action.exp;

import irille.action.MgtAction;
import irille.exp.exp.ExpBase;
import irille.exp.exp.ExpBaseDAO.Todo;
import irille.exp.exp.ExpBaseDAO.Undo;

public class ExpBaseAction extends MgtAction<ExpBase> {
	public ExpBase getBean() {
		return _bean;
	}

	public void setBean(ExpBase bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return ExpBase.class;
	}
	
	public void todo() throws Exception {
		Todo act = new Todo();
		act.setBKey(getPkey());
		act.commit();
		writeSuccess(act.getB());
	}

	public void undo() throws Exception {
		Undo act = new Undo();
		act.setBKey(getPkey());
		act.commit();
		writeSuccess(act.getB());
	}

	/**
	 * 展现到页面
	 * @return
	 * @throws Exception
	 */
	public String showWeb() throws Exception {
		ExpBase model = ExpBase.load(ExpBase.class, 1);
		setBean(model);
		setResult("/exp/exp_base.jsp");
		return TRENDS;
	}
}