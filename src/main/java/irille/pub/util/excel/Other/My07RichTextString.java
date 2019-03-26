package irille.pub.util.excel.Other;

import java.awt.*;

import irille.pub.util.excel.Entity.AbsRichTextString;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

/** Created by IntelliJ IDEA. User: Administrator Date: 2018/7/3 Time: 11:20 */
public class My07RichTextString extends AbsRichTextString {

  private String string;
  private Color color;
  private String fontName;

  private My07RichTextString() {}

  public static My07RichTextString build(String string, String fontname, Color color) {
    My07RichTextString richTextString = new My07RichTextString();
    richTextString.string = string;
    richTextString.color = color;
    richTextString.fontName = fontname;
    return richTextString;
  }

  public static My07RichTextString build(String string, Color color) {
    My07RichTextString richTextString = new My07RichTextString();
    richTextString.string = string;
    richTextString.color = color;
    return richTextString;
  }

  @Override
  public RichTextString get(Workbook workbook) {
    RichTextString rt = new XSSFRichTextString(string);
    XSSFFont font1 = (XSSFFont) workbook.createFont();
    XSSFColor xlscolor = new XSSFColor(color);
    if (fontName != null) {
      font1.setFontName(fontName);
    }
    font1.setColor(xlscolor);
    rt.applyFont(0, 10, font1);
    return rt;
  }
}
