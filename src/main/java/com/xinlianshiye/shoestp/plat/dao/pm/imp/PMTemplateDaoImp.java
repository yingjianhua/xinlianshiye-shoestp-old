package com.xinlianshiye.shoestp.plat.dao.pm.imp;

import java.sql.SQLException;
import java.util.List;

import com.xinlianshiye.shoestp.plat.dao.pm.IPMTemplateDao;

import irille.Entity.pm.PMTemplate;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.svr.DbPool;

public class PMTemplateDaoImp implements IPMTemplateDao{
	
	
	@Override
	public List<PMTemplate> list(){
		SQL sql = new SQL();
		sql.SELECT(PMTemplate.class).FROM(PMTemplate.class);
		return Query.sql(sql).queryList(PMTemplate.class);
	}

	@Override
	public void add(List<PMTemplate> addBeans) {
		for(int i=0;i<addBeans.size();i++) {
			PMTemplate bean = addBeans.get(i);
			String sql = " INSERT INTO " + PMTemplate.TB.getCodeSqlTb() + " SET " + PMTemplate.T.RCVR_TYPE.getFld().getCodeSqlField() + " = " + bean.getRcvrType() + ","
					+ PMTemplate.T.TYPE.getFld().getCodeSqlField() + " = " + bean.getType() + ","
					+ PMTemplate.T.TITLE.getFld().getCodeSqlField() + " = '" + bean.getTitle() + "',"
					+ PMTemplate.T.EMAIL_STATUS.getFld().getCodeSqlField() + " = " + bean.getEmailStatus() + ","
					+ PMTemplate.T.PM_STATUS.getFld().getCodeSqlField() + " = " + bean.getEmailStatus() + ","
					+ PMTemplate.T.ROW_VERSION.getFld().getCodeSqlField() + " = 0 ";
			BeanBase.executeUpdate(sql);
		}
		
		
		try {
			DbPool.getInstance().getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upd(List<PMTemplate> editBeans) {
		for(int i=0;i<editBeans.size();i++) {
			PMTemplate bean = editBeans.get(i);
			String sql = " UPDATE " + PMTemplate.TB.getCodeSqlTb() + " SET " + PMTemplate.T.RCVR_TYPE.getFld().getCodeSqlField() + " = " + bean.getRcvrType() + ","
					+ PMTemplate.T.TYPE.getFld().getCodeSqlField() + " = " + bean.getType() + ","
					+ PMTemplate.T.EMAIL_STATUS.getFld().getCodeSqlField() + " = " + bean.getEmailStatus() + ","
					+ PMTemplate.T.PM_STATUS.getFld().getCodeSqlField() + " = " + bean.getEmailStatus() + ","
					+ PMTemplate.T.ROW_VERSION.getFld().getCodeSqlField() + " = " + (bean.getRowVersion()+1)
					+ " WHERE " + PMTemplate.T.PKEY.getFld().getCodeSqlField() + " = " + bean.getPkey();
			BeanBase.executeUpdate(sql);
		}
		
		
		try {
			DbPool.getInstance().getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public PMTemplate load(Integer pkey) {
		return Query.SELECT(PMTemplate.class, pkey);
	}
}
