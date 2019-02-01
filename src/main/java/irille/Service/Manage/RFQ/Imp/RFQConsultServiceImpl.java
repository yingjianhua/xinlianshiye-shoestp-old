package irille.Service.Manage.RFQ.Imp;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.google.inject.Inject;

import irille.Dao.RFQ.RFQConsultGroupDao;
import irille.Dao.RFQ.RFQConsultGroupRelationDao;
import irille.Dao.RFQ.RFQConsultRelationDao;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultGroup;
import irille.Entity.RFQ.RFQConsultGroupRelation;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Service.Manage.RFQ.RFQConsultService;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.shop.usr.UsrPurchase;
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
        List<RFQConsultRelation> list = rFQConsultRelationDao.findAllByConsult_PkeySupplier_Pkey(consultPkeys, supplier.getPkey());
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

	@Override
	public void page(UsrSupplier supplier, Integer start, Integer limit, String keyword, Integer groupId, Boolean isFavorite, Byte type,
			Byte readStatus, Boolean isDeleted, Date startDate, Date endDate) {
		if(start == null)
			start = 0;
		if(limit == null || limit == 0)
			limit = 10;
//		RFQConsultRelationView
		BeanQuery<RFQConsultRelation> query = Query.SELECT(RFQConsultRelation.T.PKEY)
		.FROM(RFQConsultRelation.class)
		.LEFT_JOIN(RFQConsult.class, RFQConsultRelation.T.CONSULT, RFQConsult.T.PKEY)
		.WHERE(startDate != null, RFQConsult.T.CREATE_TIME, ">?", startDate)
		.WHERE(endDate != null, RFQConsult.T.CREATE_TIME, "<?", endDate)
		.WHERE(type != null, RFQConsult.T.TYPE, "=?", type)
		.WHERE(isDeleted != null, RFQConsultRelation.T.IN_RECYCLE_BIN, "=?", BeanBase.booleanToByte(isDeleted))
		.WHERE(isFavorite, RFQConsultRelation.T.FAVORITE, "=?", BeanBase.booleanToByte(isFavorite));
		if(keyword != null && !keyword.isEmpty()) {
			query.LEFT_JOIN(UsrPurchase.class, RFQConsultRelation.T.PURCHASE_ID, UsrPurchase.T.PKEY);
			query.WHERE(RFQConsult.T.TITLE, "like ?", "%"+keyword+"%").OR().WHERE(UsrPurchase.T.NAME, "like ?", "%"+keyword+"%");
		}
		if(groupId != null) {
			query.LEFT_JOIN(RFQConsultGroupRelation.class, RFQConsult.T.PKEY, RFQConsultGroupRelation.T.CONSULT);
			query.WHERE(RFQConsultGroupRelation.T.CONSULT_GROUP, "=?", groupId);
		}
		
		query.limit(start, limit);
		// TODO Auto-generated method stub
		query.queryList();
	}

	public static void main(String[] args) {
		new RFQConsultServiceImpl().testDirector();
	}
	
	public void testDirector() {
		RFQConsultRelation.TB.getCode();
		RFQConsult.TB.getCode();
		UsrPurchase.TB.getCode();
		RFQConsultGroupRelation.TB.getCode();
		
		testPage();
	}
	
	public void testPage() {
		page(null, 0, 10, "", 1, true, (byte)1, (byte)1, false, new Date(), new Date());
	}
}
