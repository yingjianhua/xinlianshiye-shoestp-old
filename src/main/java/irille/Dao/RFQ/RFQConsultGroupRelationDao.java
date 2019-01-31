package irille.Dao.RFQ;

public interface RFQConsultGroupRelationDao {
	
	Integer countByGroup_pkey(Integer groupId);
	
	void deleteByGroup_pkey(Integer groupId);
}
