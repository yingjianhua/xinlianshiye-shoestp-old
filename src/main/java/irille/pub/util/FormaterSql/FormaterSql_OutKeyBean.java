package irille.pub.util.FormaterSql;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/7/16
 * Time: 15:18
 */
public class FormaterSql_OutKeyBean {
    private String table;
    private String fld;
    private String outKey;
    private FormaterSql_TypeBean type;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getFld() {
        return fld;
    }

    public void setFld(String fld) {
        this.fld = fld;
    }

    public String getOutKey() {
        return outKey;
    }

    public void setOutKey(String outKey) {
        this.outKey = outKey;
    }

    public FormaterSql_TypeBean getType() {
        return type;
    }

    public void setType(FormaterSql_TypeBean type) {
        this.type = type;
    }
}
