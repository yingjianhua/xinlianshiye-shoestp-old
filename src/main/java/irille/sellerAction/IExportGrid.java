package irille.sellerAction;

import irille.Filter.svr.RequestMapping;

public interface IExportGrid {

	@RequestMapping(alias="导出表格", sort=-3)
	public void exportGrid() throws Exception;

}
