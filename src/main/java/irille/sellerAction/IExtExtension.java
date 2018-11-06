package irille.sellerAction;

import irille.pub.svr.RequestMapping;

public interface IExtExtension {
	
	@RequestMapping(alias="下拉列表", sort=-4)
	public void getComboTrigger() throws Exception;
	
}
