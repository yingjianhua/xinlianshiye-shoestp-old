package irille.pub.util.FormaterSql;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/10
 * Time: 16:12
 */
public class FormaterSqlFldBean {
    private String fldName;
    private String alias;
    private String fullName;
    private boolean isMultiLanguageFld;

    public String getFldName() {
        return fldName;
    }

    public void setFldName(String fldName) {
        this.fldName = fldName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormaterSqlFldBean that = (FormaterSqlFldBean) o;
        return isMultiLanguageFld == that.isMultiLanguageFld &&
                Objects.equals(fldName, that.fldName) &&
                Objects.equals(alias, that.alias) &&
                Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fldName, alias, fullName, isMultiLanguageFld);
    }

    public boolean isMultiLanguageFld() {

        return isMultiLanguageFld;
    }

    public FormaterSqlFldBean setMultiLanguageFld(boolean multiLanguageFld) {
        isMultiLanguageFld = multiLanguageFld;
        return this;
    }

}
