package irille.Dao.SVS;

import com.google.inject.ImplementedBy;
import irille.Dao.SVS.impl.SVSInfoDaoImpl;
import irille.Entity.SVS.SVSInfo;

@ImplementedBy(SVSInfoDaoImpl.class)
public interface SVSInfoDao {

	SVSInfo save(SVSInfo bean);
	
	SVSInfo  findSVSInfoBySupplier(Integer supplier);

}
