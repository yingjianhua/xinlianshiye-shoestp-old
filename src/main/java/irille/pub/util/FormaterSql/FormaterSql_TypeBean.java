package irille.pub.util.FormaterSql;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/7/16
 * Time: 15:18
 */
public enum FormaterSql_TypeBean {
    LeftJoin(" left join "),
    DESC("DESC"),
    ASC("ASC"),

    UPDATE("UPDATE "),
    SELECT("SELECT ");

    private String string;

    FormaterSql_TypeBean(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
