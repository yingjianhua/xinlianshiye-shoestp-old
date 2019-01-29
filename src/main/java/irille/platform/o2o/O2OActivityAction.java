package irille.platform.o2o;

import java.io.IOException;

import javax.inject.Inject;

import irille.Entity.O2O.O2O_Activity;
import irille.Service.Manage.O2O.O2OActivityService;
import irille.action.ActionBase;
import irille.view.O2O.O2OActivityView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O2OActivityAction extends ActionBase<O2O_Activity> implements IO2OActivityAction {

	private static final long serialVersionUID = 1L;

	@Inject
	private O2OActivityService o2OActivityService;

	private O2OActivityView activity;

	@Override
	public Class<O2O_Activity> beanClazz() {
		return O2O_Activity.class;
	}

	@Override
	public void list() throws IOException {
		write(o2OActivityService.list(getStart(), getLimit(), activity));
	}

	@Override
	public void cancel() throws IOException {
		o2OActivityService.cancel(activity);
		write();
	}

	@Override
	public void deploy() throws IOException {
		write(o2OActivityService.deploy(activity));
	}

	@Override
	public void edit() throws IOException {
		write(o2OActivityService.edit(activity));
	}

}
