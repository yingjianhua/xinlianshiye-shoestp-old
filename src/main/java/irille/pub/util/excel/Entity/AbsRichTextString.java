package irille.pub.util.excel.Entity;

import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;

/** Created by IntelliJ IDEA. User: Administrator Date: 2018/7/3 Time: 11:15 */
public abstract class AbsRichTextString {

  public abstract RichTextString get(Workbook workbook);
}
