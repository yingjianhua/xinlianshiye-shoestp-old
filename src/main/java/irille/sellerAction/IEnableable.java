package irille.sellerAction;

import irille.pub.svr.RequestMapping;

public interface IEnableable {
	
	
	@RequestMapping(alias="启用", sort=-2)
	public void doEnabled() throws Exception;
	
	@RequestMapping(alias="停用", sort=-1)
	public void unEnabled() throws Exception;
	
}
