package irille.pub.util.excel.Units;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.pow;

public class ExcelUnits {

  public static int[] StringToCellXy(String xy) {
    Pattern pattern = Pattern.compile("([a-zA-Z]{1,})([0-9]{1,})");
    Matcher matcher = pattern.matcher(xy.toLowerCase());
    try {
      if (!matcher.matches()) {
        throw new Exception("无效坐标字符串");
      }
      int sum = 0;

      String s = matcher.group(1);
      String s2 = matcher.group(2);
      for (int i = s.length(); i > 0; i--) {
        sum = sum + (s.charAt(s.length() - i) - 'a' + 1) * ((int) pow(26, (i - 1)));
      }
      return new int[] {sum - 1, Integer.parseInt(s2) - 1};
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String getSuffix(String s) {
    return s.substring(s.lastIndexOf(".") + 1, s.length());
  }
}
