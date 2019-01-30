package irille.platform.rfq;

import java.io.IOException;

import javax.inject.Inject;

import irille.Entity.RFQ.RFQConsult;
import irille.Service.RFQ.RFQConsultService;
import irille.action.ActionBase;
import irille.platform.rfq.view.RFQConsultView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RFQConsultAction extends ActionBase<RFQConsult> implements IRFQConsultAction {

	private static final long serialVersionUID = 1L;

	@Inject
	private RFQConsultService rFQConsultService;

	private RFQConsultView consult;

	@Override
	public Class<RFQConsult> beanClazz() {
		return RFQConsult.class;
	}

	@Override
	public void list() throws IOException {
		write(rFQConsultService.page(getStart(), getLimit(), consult));
	}

	@Override
	public void detail() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
