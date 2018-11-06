package irille.pub.util.excel.Other;

import irille.pub.util.excel.Entity.AbsCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/7/3
 * Time: 10:34
 */
public class DateCellStyle extends AbsCellStyle {


    public DateCellStyle(String formatString) {
        super(formatString);
    }

    public static DateCellStyle build() {
        return new DateCellStyle("yyyy-MM-dd  hh:mm:ss");
    }

    public static DateCellStyle build(String s) {
        return new DateCellStyle(s);
    }

    @Override
    public CellStyle get(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(formatString));
        return cellStyle;
    }
}
