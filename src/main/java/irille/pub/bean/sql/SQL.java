package irille.pub.bean.sql;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import irille.pub.bean.BeanBase;
import irille.pub.bean.BeanMain;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.IEnumOpt;
import irille.shop.plt.PltTrantslate;


/**
 * from mybatis version 3.4.6
 *
 * @author yingjianhua
 */
public class SQL {

    private MybatisSQL mybatisSQL;

    public SQL() {
        mybatisSQL = new MybatisSQL();
    }

    public <T extends BeanMain<?, ?>> SQL DELETE_FROM(Class<T> beanClass) {
        return mybatisSQL.DELETE_FROM(beanClass);
    }

    public <T extends BeanMain<?, ?>> SQL UPDATE(Class<T> beanClass) {
        return mybatisSQL.UPDATE(beanClass);
    }

    public <T extends BeanMain<?, ?>> SQL SET(IEnumFld fld, Serializable param) {
        mybatisSQL.SET(fld);
        if (param instanceof IEnumOpt) {
            IEnumOpt opt = (IEnumOpt) param;
            mybatisSQL.PARAM(opt.getLine().getKey());
        } else {
            mybatisSQL.PARAM(param);
        }
        return mybatisSQL.getSelf();
    }

    public <T extends BeanMain<?, ?>> SQL SELECT(Class<T> beanClass) {
        return mybatisSQL.SELECT(beanClass);
    }

    public SQL SELECT(String... columns) {
        return mybatisSQL.SELECT(columns);
    }

    public SQL SELECT(IEnumFld fld, String alias) {
        return mybatisSQL.SELECT(fld, alias);
    }

    public SQL SELECT(IEnumFld... flds) {
        return mybatisSQL.SELECT(flds);
    }

    public SQL SELECT(SQL sql, String alias) {
        if (sql == null) return this;
        mybatisSQL.params(sql.PARAMS());
        return mybatisSQL.SELECT("(" + sql.toString() + ") as " + alias);
    }

    public <T extends BeanMain<?, ?>> SQL FROM(Class<T> beanClass) {
        return mybatisSQL.FROM(beanClass);
    }
    
    public <T extends BeanMain<?, ?>> SQL FROM(SQL sql, String alias) {
    	mybatisSQL.FROM(sql, alias);
    	return mybatisSQL.params(sql.PARAMS());
    }
    public <T extends BeanMain<?, ?>> SQL FROM(SQL sql, Class<T> beanAlias) {
    	mybatisSQL.FROM(sql, beanAlias);
    	return mybatisSQL.params(sql.PARAMS());
    }

    public <T extends BeanMain<?, ?>> SQL LEFT_JOIN(Class<T> beanClass, IEnumFld fld1, IEnumFld fld2) {
        return mybatisSQL.LEFT_OUTER_JOIN(beanClass, fld1, fld2);
    }
    
    public <T extends BeanMain<?, ?>> SQL LEFT_JOIN(SQL sql, Class<T> beanAlias, IEnumFld fld1, IEnumFld fld2) {
    	return mybatisSQL.LEFT_OUTER_JOIN(sql, beanAlias, fld1, fld2);
    }

    public <T extends BeanMain<?, ?>> SQL LEFT_JOIN(String join) {
        return mybatisSQL.LEFT_OUTER_JOIN(join);
    }

    public <T extends BeanMain<?, ?>> SQL WHERE(IEnumFld fld, String conditions) {
        return mybatisSQL.WHERE(fld, conditions);
    }

    public <T extends BeanMain<?, ?>> SQL WHERE(IEnumFld fld, String conditions, IEnumFld fld2) {
        return mybatisSQL.WHERE(fld, conditions, fld2);
    }

    public <T extends BeanMain<?, ?>> SQL WHERE(IEnumFld fld, String conditions, Serializable... params) {
        mybatisSQL.WHERE(fld, conditions);
        mybatisSQL.params(params);
        return mybatisSQL.getSelf();
    }

    public <T extends BeanMain<?, ?>> SQL WHERE(String conditions, Serializable... params) {
        mybatisSQL.WHERE(conditions);
        mybatisSQL.params(params);
        return mybatisSQL.getSelf();
    }

    public <T extends BeanMain<?, ?>> SQL WHERE(boolean b, IEnumFld fld, String conditions, Serializable... params) {
        if (b) {
            mybatisSQL.WHERE(fld, conditions);
            mybatisSQL.params(params);
        }
        return mybatisSQL.getSelf();
    }

    public static void main(String[] args) {
        PltTrantslate.TB.getCode();
        Class beanClass = PltTrantslate.class;
        if (BeanMain.class.isAssignableFrom(beanClass)) {
            for (Class c : beanClass.getDeclaredClasses()) {
                if (c.getSimpleName().equals("T")) {
                    Object[] aa = c.getEnumConstants();
                    for (Object a : aa) {
                        Enum b = (Enum) a;
                        if (b.name().equals("")) {
                            IEnumFld d = (IEnumFld) a;
                            d.getFld().getCode();
                        }
                        System.out.println(b.name());

                        System.out.println(a.getClass());
                    }
                }
            }
        }
    }
    /**
     * 产生in 语句
     * @param num 问号数量
     * @return
     * <p>如参数为 2 则返回 in (?,?)
     * <p>如参数为10 则返回 in (?,?,?,?,?,?,?,?,?,?)
     */
    public static String getInSql(Integer num) {
		String[] s = new String[num];
		for (int i = 0; i < num; i++) {
			s[i] = "?";
		}
		return "in (" + StringUtils.join(s, ",") + ")";
    }
    
    public static Serializable[] getInParams(List<Serializable> params) {
    	params.size();
    	return null;
    }

    //多条件赛选 参数1 返回的dto 参数2 对应的实体类
    public <T extends BeanMain<?, ?>> SQL Mconditions(List<MconditionsView> list, Class<T> beanClass) {
        //存放实体类字段名称
        Object[] ObEnums = null;
        //循环实体类中字段
        Class bean = beanClass;
        if (BeanMain.class.isAssignableFrom(bean)) {
            for (Class classmethod : bean.getDeclaredClasses()) {
                if (classmethod.getSimpleName().equals("T")) {
                    ObEnums = classmethod.getEnumConstants();
                }
            }
        }

        for (MconditionsView mv : list) {
            for (Object field : ObEnums) {
                System.out.println(field);
                Enum fieldZ = (Enum) field;
                if (fieldZ.name().equalsIgnoreCase(mv.getMode())) {
                    IEnumFld fld = (IEnumFld) field;
                    if (mv.getIsdate() == 0) {
                        if (mv.getCondition() == 1) {
                            WHERE(fld, " >? ", mv.getContent());
                        }
                        if (mv.getCondition() == 2) {
                            WHERE(fld, " <? ", mv.getContent());
                        }
                        if (mv.getCondition() == 3) {
                            WHERE(fld, " <>? ", mv.getContent());
                        }
                        if (mv.getCondition() == 4) {
                            WHERE(fld, " =? ", mv.getContent());
                        }
                        if (mv.getCondition() == 5) {
                            WHERE(fld, " like '%" + mv.getContent() + "%' ");
                        }
                        if (mv.getCondition() == 6) {
                            WHERE(fld, " not like '%" + mv.getContent() + "%' ");
                        }
                    } else {
                        if (mv.getCondition() == 1) {
                            WHERE(fld, " >? ", new Date(Long.parseLong(mv.getContent())));
                        }
                        if (mv.getCondition() == 2) {
                            WHERE(fld, " <? ", new Date(Long.parseLong(mv.getContent())));
                        }
                        if (mv.getCondition() == 3) {
                            WHERE(fld, " <>? ", new Date(Long.parseLong(mv.getContent())));
                        }
                        if (mv.getCondition() == 4) {
                            WHERE(fld, " =? ", new Date(Long.parseLong(mv.getContent())));
                        }
                    }
                }
            }
        }
        return mybatisSQL.getSelf();
    }

    public <T extends BeanMain<?, ?>> SQL orWhere(IEnumFld fld, String conditions, Serializable... params) {
        return mybatisSQL.or().WHERE(fld, conditions, params);
    }

    public <T extends BeanMain<?, ?>> SQL or() {
        return mybatisSQL.or();
    }

    public <T extends BeanMain<?, ?>> SQL OR() {
        return mybatisSQL.OR();
    }

    public <T extends BeanMain<?, ?>> SQL AND() {
        return mybatisSQL.AND();
    }

    public <T extends BeanMain<?, ?>> SQL ORDER_BY(IEnumFld fld, String type) {
        return mybatisSQL.ORDER_BY(fld, type);
    }

    public <T extends BeanMain<?, ?>> SQL GROUP_BY(IEnumFld fld) {
        return mybatisSQL.GROUP_BY(fld);
    }

    public <T extends BeanMain<?, ?>> SQL HAVING(IEnumFld fld, String condition, Serializable... params) {
    	mybatisSQL.HAVING(fld, condition);
        mybatisSQL.params(params);
        return mybatisSQL.getSelf();
    }
    
    public <T extends BeanMain<?, ?>> SQL HAVING(String condition) {
        return mybatisSQL.HAVING(condition);
    }

    public <T extends BeanMain<?, ?>> SQL LIMIT(int start, int limit) {
        return mybatisSQL.LIMIT(start, limit);
    }

    public <T extends BeanMain<?, ?>> SQL LOCK(boolean lock) {
        return mybatisSQL.LOCK(lock);
    }

    public <T extends BeanMain<?, ?>> SQL PARAM(Serializable param) {
        return mybatisSQL.PARAM(param);
    }

    public List<Serializable> PARAMS() {
        return mybatisSQL.PARAMS();
    }

    public String JSON_EXTRACT(IEnumFld fld, String key) {
        //return mybatisSQL.columnLabelWithAlias(fld)+"->"+key+" as "+mybatisSQL.columnLabel(fld);//老版本mysql不支持的语法
        return "JSON_EXTRACT(" + mybatisSQL.columnLabelWithAlias(fld) + ", \"$." + key + "\") as " + mybatisSQL.columnLabel(fld);
    }

    public String JSON_UNQUOTE(IEnumFld fld, String key) {
        return "JSON_UNQUOTE(JSON_EXTRACT(" + mybatisSQL.columnLabelWithAlias(fld) + ", \"$." + key + "\")) as " + mybatisSQL.columnLabel(fld);
    }


    @Override
    public String toString() {
        return mybatisSQL.toString();
    }


    class MybatisSQL extends AbstractSQL<SQL> {

        private int start = 0;
        private int limit = 0;
        private boolean lock = false;

        @Override
        public SQL getSelf() {
            return SQL.this;
        }

        public <T extends BeanMain<?, ?>> SQL SELECT(Class<T> beanClass) {
            return super.SELECT(alias(beanClass) + ".*");
        }

        public SQL SELECT(String... columns) {
            return super.SELECT(columns);
        }

        public SQL SELECT(IEnumFld... flds) {
            String[] columns = new String[flds.length];
            for (int i = 0; i < columns.length; i++) {
                columns[i] = columnLabelWithAlias(flds[i]);
            }
            return super.SELECT(columns);
        }

        public SQL SELECT(IEnumFld fld, String alias) {
            return super.SELECT(columnLabelWithAlias(fld) + " as " + alias);
        }

        public <T extends BeanMain<?, ?>> SQL FROM(Class<T> beanClass) {
            return super.FROM(tableNameWithAlias(beanClass));
        }
        
        public <T extends BeanMain<?, ?>> SQL FROM(SQL sql, Class<T> beanAlias) {
        	return super.FROM("(" + sql + ") as "+alias(beanAlias));
        }
        public <T extends BeanMain<?, ?>> SQL FROM(SQL sql, String alias) {
        	return super.FROM("(" + sql + ") as " + alias);
        }

        public <T extends BeanMain<?, ?>> SQL LEFT_OUTER_JOIN(Class<T> beanClass, IEnumFld fld1, IEnumFld fld2) {
            return super.LEFT_OUTER_JOIN(tableNameWithAlias(beanClass) + " ON " + columnLabelWithAlias(fld1) + "=" + columnLabelWithAlias(fld2));
        }
        
        public <T extends BeanMain<?, ?>> SQL LEFT_OUTER_JOIN(SQL sql, Class<T> beanAlias, IEnumFld fld1, IEnumFld fld2) {
        	return super.LEFT_OUTER_JOIN("(" + sql + ") " + alias(beanAlias) + " ON " + columnLabelWithAlias(fld1) + "=" + columnLabelWithAlias(fld2));
        }

        public <T extends BeanMain<?, ?>> SQL WHERE(IEnumFld fld, String conditions, IEnumFld fld2) {
            if (isSelect())
                return super.WHERE(columnLabelWithAlias(fld) + " " + conditions + " " + columnLabelWithAlias(fld2));
            else
                return super.WHERE(columnLabel(fld) + " " + conditions + " " + columnLabel(fld2));
        }

        public <T extends BeanMain<?, ?>> SQL WHERE(IEnumFld fld, String conditions) {
            if (isSelect())
                return super.WHERE(columnLabelWithAlias(fld) + " " + conditions);
            else
                return super.WHERE(columnLabel(fld) + " " + conditions);
        }
        
        public <T extends BeanMain<?, ?>> SQL HAVING(IEnumFld fld, String conditions) {
        	if (isSelect())
        		return super.HAVING(columnLabelWithAlias(fld) + " " + conditions);
        	else
        		return super.HAVING(columnLabel(fld) + " " + conditions);
        }

        public <T extends BeanMain<?, ?>> SQL ORDER_BY(IEnumFld fld, String type) {
            return super.ORDER_BY(columnLabelWithAlias(fld) + " " + type);
        }

        public <T extends BeanMain<?, ?>> SQL GROUP_BY(IEnumFld fld) {
            return super.GROUP_BY(columnLabelWithAlias(fld));
        }

        public <T extends BeanMain<?, ?>> SQL DELETE_FROM(Class<T> beanClass) {
            return super.DELETE_FROM(tableName(beanClass));
        }

        public <T extends BeanMain<?, ?>> SQL UPDATE(Class<T> beanClass) {
            return super.UPDATE(tableName(beanClass));
        }

        public SQL SET(IEnumFld fld) {
            return super.SET(columnLabel(fld) + "=?");
        }

        public <T extends BeanMain<?, ?>> SQL LIMIT(int start, int limit) {
            this.start = start;
            this.limit = limit;
            return getSelf();
        }

        public <T extends BeanMain<?, ?>> SQL LOCK(boolean lock) {
            this.lock = lock;
            return getSelf();
        }
        
        public <T extends BeanMain<?, ?>> SQL params(List<Serializable> params) {
        	for (Serializable param : params) {
                if (param instanceof IEnumOpt) {
                    IEnumOpt opt = (IEnumOpt) param;
                    this.PARAM(opt.getLine().getKey());
                } else {
                    this.PARAM(param);
                }
            }
        	return getSelf();
        }
        
        public <T extends BeanMain<?, ?>> SQL params(Serializable... params) {
        	for (Serializable param : params) {
        		if (param instanceof IEnumOpt) {
        			IEnumOpt opt = (IEnumOpt) param;
        			this.PARAM(opt.getLine().getKey());
        		} else {
        			this.PARAM(param);
        		}
        	}
        	return getSelf();
        }

        @Override
        public String toString() {
            String sql = super.toString();
            if (isSelect() && limit > 0)
                sql += " LIMIT " + start + "," + limit;
            if (lock)
                sql += " FOR UPDATE";
            return sql;
        }

        private <T extends BeanMain<?, ?>> String alias(Class<T> beanClass) {
            return beanClass.getSimpleName();
        }

        private <T extends BeanMain<?, ?>> String tableName(Class<T> beanClass) {
            return BeanBase.tb(beanClass).getCodeSqlTb();
        }

        private <T extends BeanMain<?, ?>> String tableNameWithAlias(Class<T> beanClass) {
            return tableName(beanClass) + " " + alias(beanClass);
        }

        private <T extends BeanMain<?, ?>> String alias(IEnumFld fld) {
            Class<?> a = ((Enum<?>) fld).getDeclaringClass().getDeclaringClass();
            return a.getSimpleName();
        }

        private String columnLabel(IEnumFld fld) {
            return fld.name().toLowerCase();
        }

        private String columnLabelWithAlias(IEnumFld fld) {
            return alias(fld) + "." + columnLabel(fld);
        }
    }
}
