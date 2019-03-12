package irille.pub.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import irille.pub.bean.Bean;
import irille.pub.svr.DbPool;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.shop.pdt.PdtProduct;

public class BatchUtils {

	private static IEnumFld castEnum(Object obj) {
	    return (IEnumFld)obj;
	  }

	public static <T extends Bean> void batchIns(Class<T> beanClass,List<T> objs) {
		Class t = null;
		try {
			t = Class.forName(beanClass.getName() + "$T");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Object[] objarr = t.getEnumConstants();
		List<IEnumFld> flds = new ArrayList<>();
		for (Object object : objarr) {
			flds.add(castEnum(object));
		}
		batchIns(beanClass,flds,objs);
	}

	public static <T extends Bean> void batchIns(Class<T> beanClass,List<IEnumFld> flds,List<T> objs) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO ");
		sql.append( Tb.getTBByBean(beanClass).getCodeSqlTb() );
		sql.append(" SET ");
		Stack stack = new Stack();
		for(int i=0;i<flds.size();i++) {
			sql.append(flds.get(i).getFld().getCodeSqlField());
			sql.append(" = ? ");
			if(i != flds.size()-1) {
				sql.append(",");
			}
			stack.push(flds.get(i));
		}

		batch(sql.toString(),stack,objs);
	}

	public static <T extends Bean> void batchDel(Class<T> beanClass,List<IEnumFld> flds,List<T> objs) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM ");
		sql.append( Tb.getTBByBean(beanClass).getCodeSqlTb() );
		sql.append(" WHERE ");
		Stack stack = new Stack();
		for(int i=0;i<flds.size();i++) {
			sql.append(flds.get(i).getFld().getCodeSqlField());
			sql.append(" = ? ");
			if(i != flds.size()-1) {
				sql.append(",");
			}
			stack.push(flds.get(i));
		}

		batch(sql.toString(),stack,objs);
	}

	public static <T extends Bean> void batchUpd(Class<T> beanClass,List<IEnumFld> flds,List<IEnumFld> whereFlds,List<T> objs) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE ");
		sql.append( Tb.getTBByBean(beanClass).getCodeSqlTb() );
		sql.append(" SET ");
		Stack stack = new Stack();
		for(int i=0;i<flds.size();i++) {
			sql.append(flds.get(i).getFld().getCodeSqlField());
			sql.append(" = ? ");
			if(i != flds.size()-1) {
				sql.append(",");
			}
			stack.push(flds.get(i));
		}

		if(null != whereFlds && whereFlds.size() > 0) {
			sql.append(" WHERE ");
			for(int i=0;i<whereFlds.size();i++) {

				sql.append(whereFlds.get(i).getFld().getCodeSqlField());
				sql.append(" =? ");
				if(i != whereFlds.size()-1) {
					sql.append(",");
				}
				stack.push(whereFlds.get(i));
			}
		}

		batch(sql.toString(),stack,objs);
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
//		Stack<Integer> stack = new Stack();
//		stack.push(1);
//		stack.push(1);
//		for(Integer i:stack) {
//			System.out.println(i);
//		}
		PdtProduct.TB.getClass();
		System.out.println((IEnumFld)PdtProduct.T.PKEY);
		Class t = Class.forName(PdtProduct.class.getName() + "$T");
		Object[] flds = t.getEnumConstants();
		for (Object object : flds) {
			System.out.println(castEnum(object).getFld().getCodeSqlField());
		}
	}

	public static <T extends Bean> void batch(String sql,Stack<IEnumFld> flds,List<T> objs) {
		PreparedStatement pt = null;
		Connection connection = null;
			try {
				connection = DbPool.getInstance().getConn();
				pt = connection.prepareStatement(sql);
				connection.setAutoCommit(false);
				int nums = 0;
				for(T obj:objs) {
					int i = 1;
					for(IEnumFld f:flds) {
						try {
							Field field = obj.getClass().getDeclaredField("_" + f.getFld().getCode());
							field.setAccessible(true);
							pt.setObject(i, field.get(obj));
							field.get(obj);

							i++;
							nums++;
							if(nums%1000 == 0) {
								pt.executeBatch();
								pt.clearBatch();
							}
						} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					}
						pt.addBatch();
				}
				pt.executeBatch();
				connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				if(pt != null) {
					DbPool.close(pt);
				}
			}

	}
}
