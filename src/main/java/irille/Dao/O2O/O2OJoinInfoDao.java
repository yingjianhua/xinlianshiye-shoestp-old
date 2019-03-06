package irille.Dao.O2O;

import irille.Entity.O2O.O2O_JoinInfo;
import irille.pub.bean.Query;

public class O2OJoinInfoDao {
    public O2O_JoinInfo findByActivityAndSupplier(Integer act,Integer supplier){
        return Query.SELECT(O2O_JoinInfo.class)
                    .WHERE(O2O_JoinInfo.T.ACTIVITY,"=?",act)
                    .WHERE(O2O_JoinInfo.T.SUPPLIER,"=?",supplier).query();
    }
}
