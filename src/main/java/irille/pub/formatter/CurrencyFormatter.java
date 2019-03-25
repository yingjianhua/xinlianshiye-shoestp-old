package irille.pub.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import irille.shop.plt.PltErate;

/**
 * 对BigDecimal表示的金额根据不同的货币进行格式化
 *
 * @author yingjianhua
 */
public class CurrencyFormatter {

  private static final Map<String, NumberFormat> map = new LinkedHashMap<>();

  static {
    map.put("default", new DecimalFormat(",##0.00"));
    // -----------------------------------千位分隔符为",",小数分隔符为"."------------------------
    map.put("EUR", new DecimalFormat(",##0.00"));
    map.put("GBP", new DecimalFormat(",##0.00"));
    map.put("CAD", new DecimalFormat(",##0.00"));
    map.put("AUD", new DecimalFormat(",##0.00"));
    map.put("CHF", new DecimalFormat(",##0.00"));
    map.put("HKD", new DecimalFormat(",##0.00"));
    map.put("ILS", new DecimalFormat(",##0.00"));
    map.put("MXN", new DecimalFormat(",##0.00"));
    // -----------------------------------千位分隔符为" ",小数分隔符为","------------------------
    // 俄罗斯-卢布
    map.put(
        "RUB",
        new DecimalFormat(
            ",##0.00",
            new DecimalFormatSymbols() {
              {
                setGroupingSeparator(' ');
                setDecimalSeparator(',');
              }
            }));
    // -----------------------------------千位分隔符为".",小数分隔符为","------------------------
    // 巴西-巴西雷亚尔
    map.put(
        "BRL",
        new DecimalFormat(
            ",##0.00",
            new DecimalFormatSymbols() {
              {
                setGroupingSeparator('.');
                setDecimalSeparator(',');
              }
            }));
    // 以下货币最小单位是是元,小数点后是没有内容的
    // -----------------------------------千位分隔符为"."--------------------------------------
    // 智利-比索
    map.put(
        "CLP",
        new DecimalFormat(
            ",##0",
            new DecimalFormatSymbols() {
              {
                setGroupingSeparator('.');
              }
            }));
    // 挪威-克朗
    map.put(
        "NOK",
        new DecimalFormat(
            ",##0",
            new DecimalFormatSymbols() {
              {
                setGroupingSeparator('.');
              }
            }));
    // 丹麦-克朗
    map.put(
        "DKK",
        new DecimalFormat(
            ",##0",
            new DecimalFormatSymbols() {
              {
                setGroupingSeparator('.');
              }
            }));
    // -----------------------------------千位分隔符为","--------------------------------------
    // 日元
    map.put("JPY", new DecimalFormat(",##0"));
    // 瑞典-克朗
    map.put("SEK", new DecimalFormat(",##0"));
    // 韩元
    map.put("KRW", new DecimalFormat(",##0"));
  }

  private static String format(BigDecimal price, String currency, String symbol, String shortName) {
    NumberFormat nf = map.containsKey(currency) ? map.get(currency) : map.get("default");
    return shortName + " " + symbol + nf.format(price.doubleValue());
  }

  public static String format(BigDecimal price, PltErate currency) {
    return format(price, currency.getCurName(), "", "");
  }

  public static String format(
      BigDecimal price, PltErate currency, boolean symbol, boolean shortName) {
    return format(
        price,
        currency.getCurName(),
        symbol ? currency.getSymbol() : "",
        shortName ? currency.getCurName() : "");
  }

  public static void main(String[] args) {
    System.out.println(format(BigDecimal.ZERO, "EUR", "$", "EUR"));
    System.out.println(format(BigDecimal.valueOf(61321315.1231553), "EUR", "$", "EUR"));
  }
}
