package irille.Service.Manage.RFQ.Imp;

import java.util.List;
import java.util.stream.Stream;

import com.google.inject.Inject;

import irille.Dao.RFQ.RFQConsultGroupDao;
import irille.Dao.RFQ.RFQConsultGroupRelationDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.Entity.RFQ.RFQConsultGroup;
import irille.Entity.RFQ.RFQConsultGroupRelation;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Service.Manage.RFQ.RFQConsultService;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.shop.usr.UsrSupplier;

public class RFQConsultServiceImpl implements RFQConsultService {
	
	@Inject
	private RFQConsultGroupDao rFQConsultGroupDao;
	@Inject
	private RFQConsultGroupRelationDao rFQConsultGroupRelationDao;
	@Inject
	private RFQConsultRelationDao rFQConsultRelationDao;

	@Override
	public void moveToGroup(UsrSupplier supplier, String consultPkeys, Integer groupPkey) {
		RFQConsultGroup group = rFQConsultGroupDao.findByPkeySupplier_Pkey(groupPkey, supplier.getPkey());
		if(group == null) {
			throw new WebMessageException(ReturnCode.service_gone, "请重新选择文件夹");
		}
		Stream.of(consultPkeys.split(",")).map(Integer::new).forEach(consultPkey->{
			RFQConsultGroupRelation relation = rFQConsultGroupRelationDao.findByConsult_PkeySupplier_Pkey(consultPkey, supplier.getPkey());
			if(relation == null) {
				relation = new RFQConsultGroupRelation();
				relation.setConsult(consultPkey);
				relation.setSupplier(supplier.getPkey());
				relation.setConsultGroup(groupPkey);
			} else {
				relation.setConsultGroup(groupPkey);
			}
			rFQConsultGroupRelationDao.save(relation);
		});
	}

	@Override
	public void moveToRecycled(UsrSupplier supplier, String consultPkeys, Boolean isToRecycled) {
		List<RFQConsultRelation> list = rFQConsultRelationDao.findByConsult_PkeySupplier_Pkey(consultPkeys, supplier.getPkey());
		if(isToRecycled) {
			//移动到回收站
			list.forEach(bean->{
				if(!bean.gtInRecycleBin()) {
					//尚未在回收站的
					bean.stInRecycleBin(true);
					rFQConsultRelationDao.save(bean);
				}
			});
		} else {
			//从回收站恢复
			list.forEach(bean->{
				if(bean.gtInRecycleBin()) {
					//已经在回收站的
					bean.stInRecycleBin(false);
					rFQConsultRelationDao.save(bean);
				}
			});
		}
	}

}
