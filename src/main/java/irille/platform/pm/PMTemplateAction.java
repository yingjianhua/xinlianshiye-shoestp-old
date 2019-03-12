package irille.platform.pm;

import java.io.IOException;

import javax.inject.Inject;

import org.json.JSONException;

import com.xinlianshiye.shoestp.plat.service.pm.IPMTemplateService;
import com.xinlianshiye.shoestp.plat.view.pm.TemplateView;

import irille.Entity.pm.PMTemplate;
import irille.action.MgtAction;
import lombok.Data;

@Data
public class PMTemplateAction extends MgtAction<PMTemplate>{

	@Override
	public Class beanClazz() {
		return PMTemplate.class;
	}
	
	private TemplateView view;
	
	@Inject
	private IPMTemplateService templateService;
	
	//模板列表
	public void list() throws IOException {
		write(templateService.list());
	}
	
	//修改模板
	public void upd() throws IOException, JSONException {
		templateService.upd(view);
		writeSuccess();
	}
	
	//启用模板
	public void enable() throws IOException, JSONException {
		templateService.enable(view);
		writeSuccess();
	}

}
