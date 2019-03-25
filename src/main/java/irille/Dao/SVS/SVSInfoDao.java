package irille.Dao.SVS;

import java.util.List;
import java.util.Map;

import com.google.inject.ImplementedBy;

import irille.Dao.SVS.impl.SVSInfoDaoImpl;
import irille.Entity.SVS.SVSInfo;

@ImplementedBy(SVSInfoDaoImpl.class)
public interface SVSInfoDao {

  SVSInfo save(SVSInfo bean);

  SVSInfo findSVSInfoBySupplier(Integer supplier);
  
  List<Map<String, Object>>  findSVSinfo(Integer start,Integer limit,String shopName,Byte status,Byte shopStatus,Byte grade);

  int  count();
}
