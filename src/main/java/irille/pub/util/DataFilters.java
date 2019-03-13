package irille.pub.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据过滤器 将一些格式的数据转换成相应格式或者过滤值 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/9/25 Time:
 * 10:13
 */
public class DataFilters {

  public static List ImageUrl_NoHost_List(String s) {
    if (s == null) return null;
    String[] strings = s.split(",");
    return ImageUrl_NoHost_List(strings);
  }

  public static String ImageUrl_NoHost_String(String s) {
    if (s == null) return null;
    String[] strings = s.split(",");
    if (strings.length > 0) {
      s = strings[0];
    }
    try {
      return new URL(s).getPath();
    } catch (MalformedURLException e) {
      //            e.printStackTrace();
    }
    return s;
  }

  public static List<String> ImageUrl_NoHost_List(String[] pics) {
    if (pics.length > 0) {
      List result = new ArrayList();
      for (String string : pics) {
        if (string.length() < 1) continue;
        try {
          result.add(new URL(string).getPath());
        } catch (MalformedURLException e) {
          result.add(string);
          //                    e.printStackTrace();
        }
      }
      return result;
    }
    return null;
  }
}
