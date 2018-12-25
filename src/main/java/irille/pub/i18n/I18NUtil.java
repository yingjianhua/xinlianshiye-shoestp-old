package irille.pub.i18n;

import irille.Filter.svr.SessionMsg;
import irille.shop.plt.PltErate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class I18NUtil {

	public static LocalDateTime trans(LocalDateTime time, ZoneId origin, ZoneId target) {
		return ZoneTrans.trans(time, origin, target);
	}

	public static String format(BigDecimal price, PltErate currency, boolean symbol, boolean shortName) {
		return CurrencyFormatter.format(price, currency, symbol, shortName);
	}

	public static String format(BigDecimal price, PltErate currency) {
		return CurrencyFormatter.format(price, currency);
	}


	private static class ZoneTrans {

		private static LocalDateTime trans(LocalDateTime time, ZoneId origin, ZoneId target) {
			Integer difference = ZonedDateTime.of(time, origin).getOffset().compareTo(ZonedDateTime.of(time, target).getOffset());
			return time.plusSeconds(difference);
		}
		@SuppressWarnings("unused")
		private static void test() {
			LocalDate date = LocalDate.of(2018, 8, 30);
			LocalTime time = LocalTime.of(18, 31);
			LocalDateTime datetime = LocalDateTime.of(date, time);
			System.out.println(datetime);
			System.out.println(trans(datetime, ZoneId.of("GMT+8"), ZoneId.of("GMT+7")));
		}
	}
	public static String getBundle(String key) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		SessionMsg sessionmsg = (SessionMsg)session.getAttribute(SessionMsg.session_key);
		ResourceBundle bundle =ResourceBundle.getBundle("shoestp", new Locale(sessionmsg.getLang().name()));
		return bundle.containsKey(key)?bundle.getString(key):"";
	}

	/**
	 * 对BigDecimal表示的金额根据不同的货币进行格式化</p>
	 *
	 * @author yingjianhua
	 *
	 */
	private static class CurrencyFormatter {

		private static final Map<String, NumberFormat> map = new LinkedHashMap<>();
		static {
			map.put("default", new DecimalFormat(",##0.00"));
	//-----------------------------------千位分隔符为",",小数分隔符为"."------------------------
			map.put("EUR", new DecimalFormat(",##0.00"));
			map.put("GBP", new DecimalFormat(",##0.00"));
			map.put("CAD", new DecimalFormat(",##0.00"));
			map.put("AUD", new DecimalFormat(",##0.00"));
			map.put("CHF", new DecimalFormat(",##0.00"));
			map.put("HKD", new DecimalFormat(",##0.00"));
			map.put("ILS", new DecimalFormat(",##0.00"));
			map.put("MXN",  new DecimalFormat(",##0.00"));
	//-----------------------------------千位分隔符为" ",小数分隔符为","------------------------
			//俄罗斯-卢布
			map.put("RUB", new DecimalFormat(",##0.00", new DecimalFormatSymbols(){{setGroupingSeparator(' ');setDecimalSeparator(',');}}));
	//-----------------------------------千位分隔符为".",小数分隔符为","------------------------
			//巴西-巴西雷亚尔
			map.put("BRL", new DecimalFormat(",##0.00", new DecimalFormatSymbols(){{setGroupingSeparator('.');setDecimalSeparator(',');}}));
	//以下货币最小单位是是元,小数点后是没有内容的
	//-----------------------------------千位分隔符为"."--------------------------------------
			//智利-比索
			map.put("CLP", new DecimalFormat(",##0", new DecimalFormatSymbols(){{setGroupingSeparator('.');}}));
			//挪威-克朗
			map.put("NOK", new DecimalFormat(",##0", new DecimalFormatSymbols(){{setGroupingSeparator('.');}}));
			//丹麦-克朗
			map.put("DKK", new DecimalFormat(",##0", new DecimalFormatSymbols(){{setGroupingSeparator('.');}}));
	//-----------------------------------千位分隔符为","--------------------------------------
			//日元
			map.put("JPY", new DecimalFormat(",##0"));
			//瑞典-克朗
			map.put("SEK", new DecimalFormat(",##0"));
			//韩元
			map.put("KRW", new DecimalFormat(",##0"));
		}
		private static String format(BigDecimal price, String currency, String symbol, String shortName) {
			NumberFormat nf = map.containsKey(currency)?map.get(currency):map.get("default");
			return shortName+" "+symbol+nf.format(price.doubleValue());
		}
		private static String format(BigDecimal price, PltErate currency) {
			return format(price, currency.getCurName(), "", "");
		}
		private static String format(BigDecimal price, PltErate currency, boolean symbol, boolean shortName) {
			return format(price, currency.getCurName(), symbol?currency.getSymbol():"", shortName?currency.getCurName():"");
		}
		@SuppressWarnings("unused")
		private static void test() {
			System.out.println(format(BigDecimal.ZERO, "EUR", "$", "EUR"));
			System.out.println(format(BigDecimal.valueOf(61321315.1231553), "EUR", "$", "EUR"));
		}
	}

}
