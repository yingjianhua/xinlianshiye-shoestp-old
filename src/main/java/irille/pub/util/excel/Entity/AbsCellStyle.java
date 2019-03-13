package irille.pub.util.excel.Entity;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/** Created by IntelliJ IDEA. User: Administrator Date: 2018/7/3 Time: 9:43 */
public abstract class AbsCellStyle {

  protected String formatString;

  public AbsCellStyle(String formatString) {
    this.formatString = formatString;
  }

  public abstract CellStyle get(Workbook workbook);
}
