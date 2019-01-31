package irille.Service.Manage.RFQ.Imp;

import com.google.inject.Inject;

import irille.Dao.RFQ.RFQConsultGroupDao;
import irille.Dao.RFQ.RFQConsultGroupRelationDao;
import irille.Entity.RFQ.RFQConsultGroup;
import irille.Service.Manage.RFQ.RFQConsultGroupService;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.sellerAction.rfq.view.RFQConsultGroupView;
import irille.shop.usr.UsrSupplier;

/**
 * 商家端询盘分组逻辑层
 * 
 * @author Jianhua Ying
 *
 */
public class RFQConsultGroupServiceImpl implements RFQConsultGroupService {
	
	@Inject
	private RFQConsultGroupDao rFQConsultGroupDao;
	@Inject
	private RFQConsultGroupRelationDao rFQConsultGroupRelationDao;

	@Override
	public RFQConsultGroupView create(UsrSupplier supplier, RFQConsultGroupView view) {
		if(view.getName() == null || view.getName().trim().equals("")) {
			throw new WebMessageException(ReturnCode.valid_notblank, "请输入文件夹名称");
		}
		if(view.getName().length() > 20) {
			throw new WebMessageException(ReturnCode.valid_toolong, "文件夹名称请小于20个字符");
		}
		RFQConsultGroup bean = new RFQConsultGroup();
		bean.setName(view.getName());
		bean.setSupplier(supplier.getPkey());
		if(rFQConsultGroupDao.countBySupplier_PkeyName(supplier.getPkey(), view.getName()) > 0) {
			throw new WebMessageException(ReturnCode.valid_toolong, "文件夹已存在");
		}
		rFQConsultGroupDao.save(bean);
		return RFQConsultGroupView.Builder.toView(bean);
	}

	@Override
	public RFQConsultGroupView edit(UsrSupplier supplier, RFQConsultGroupView view) {
		if(view.getName() == null || view.getName().trim().equals("")) {
			throw new WebMessageException(ReturnCode.valid_notblank, "请输入文件夹名称");
		}
		if(view.getName().length() > 20) {
			throw new WebMessageException(ReturnCode.valid_toolong, "文件夹名称请小于20个字符");
		}
		if(view.getPkey() == null) {
			throw new WebMessageException(ReturnCode.valid_toolong, "请选择文件夹");
		}
		RFQConsultGroup bean = rFQConsultGroupDao.findByPkeySupplier_Pkey(view.getPkey(), supplier.getPkey());
		if(bean == null) {
			throw new WebMessageException(ReturnCode.valid_toolong, "请选择文件夹");
		}
		if(view.getName().equals(bean.getName())) {
			throw new WebMessageException(ReturnCode.valid_toolong, "文件夹已存在");
		}
		if(rFQConsultGroupDao.countBySupplier_PkeyName(supplier.getPkey(), view.getName()) > 0) {
			throw new WebMessageException(ReturnCode.valid_toolong, "文件夹已存在");
		}
		bean.setName(view.getName());
		rFQConsultGroupDao.save(bean);
		return RFQConsultGroupView.Builder.toView(bean);
	}

	@Override
	public void delete(UsrSupplier supplier, RFQConsultGroupView view) {
		if(view.getPkey() == null) {
			throw new WebMessageException(ReturnCode.valid_toolong, "请选择文件夹");
		}
		RFQConsultGroup group = rFQConsultGroupDao.findByPkeySupplier_Pkey(view.getPkey(), supplier.getPkey());
		if(group == null) {
			throw new WebMessageException(ReturnCode.valid_toolong, "请选择文件夹");
		}
		rFQConsultGroupRelationDao.deleteByGroup_pkey(group.getPkey());
		rFQConsultGroupDao.delete(group.getPkey());
	}

}
