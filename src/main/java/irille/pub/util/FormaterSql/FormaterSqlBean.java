package irille.pub.util.FormaterSql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 示例用
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/7/16
 * Time: 11:13
 */
public class FormaterSqlBean {

    private List<FormaterSqlFldBean> fldlist;
    private String tableName;
    private List<FormaterSql_OutKeyBean> on;
    private StringBuffer where;
    private FormaterSql_TypeBean order;
    private Set<String> orderFld;
    private long offset;
    private long showItem;

    public FormaterSqlBean() {
        fldlist = new ArrayList<>();
        on = new ArrayList<>();
        where = new StringBuffer();
        orderFld = new HashSet<>();
    }

    public StringBuffer getWhere() {
        return where;
    }

    public void setWhere(StringBuffer where) {
        this.where = where;
    }

    public List<FormaterSqlFldBean> getFldlist() {
        return fldlist;
    }

    public void setFldlist(List<FormaterSqlFldBean> fldlist) {
        this.fldlist = fldlist;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<FormaterSql_OutKeyBean> getOn() {
        return on;
    }

    public void setOn(List<FormaterSql_OutKeyBean> on) {
        this.on = on;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getShowItem() {
        return showItem;
    }

    public void setShowItem(long showItem) {
        this.showItem = showItem;
    }

    public FormaterSql_TypeBean getOrder() {
        return order;
    }

    public void setOrder(FormaterSql_TypeBean order) {
        this.order = order;
    }


    public Set<String> getOrderFld() {
        return orderFld;
    }

    public void setOrderFld(Set<String> orderFld) {
        this.orderFld = orderFld;
    }

}
