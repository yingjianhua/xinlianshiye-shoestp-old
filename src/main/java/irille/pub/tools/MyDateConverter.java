package irille.pub.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import com.opensymphony.xwork2.XWorkException;

import org.apache.struts2.util.StrutsTypeConverter;

public class MyDateConverter extends StrutsTypeConverter {

  @Override
  public Object convertFromString(Map context, String[] values, Class toClass) {
    Date result = null;

    if (values != null && values.length > 0 && values[0] != null && values[0].length() > 0) {
      String sa = values[0];
      Locale locale = getLocale(context);

      DateFormat df = null;
      if (java.util.Date.class == toClass) {
        Date check;
        DateFormat[] dfs = getDateFormats(locale);
        for (DateFormat df1 : dfs) {
          try {
            check = df1.parse(sa);
            df = df1;
            if (check != null) {
              break;
            }
          } catch (ParseException ignore) {
          }
        }
      }
      // final fallback for dates without time
      if (df == null) {
        df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
      }
      try {
        df.setLenient(false); // let's use strict parsing (XW-341)
        result = df.parse(sa);
      } catch (ParseException e) {
        throw new XWorkException("Could not parse date", e);
      }
    }
    return result;
  }

  @Override
  public String convertToString(Map context, Object o) {
    return null;
  }

  private DateFormat[] getDateFormats(Locale locale) {
    DateFormat dt1 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, locale);
    DateFormat dt2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, locale);
    DateFormat dt3 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);

    DateFormat d1 = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    DateFormat d2 = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
    DateFormat d3 = DateFormat.getDateInstance(DateFormat.LONG, locale);

    DateFormat rfc3339 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    DateFormat rfc3339dateOnly = new SimpleDateFormat("yyyy-MM-dd");

    return new DateFormat[] {dt1, dt2, dt3, rfc3339, d1, d2, d3, rfc3339dateOnly};
  }
}
