package irille.pub.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/9/26
 * Time: 13:36
 */
public class SEOUtils {
    public static String getPdtProductTitle(int pkey, String json) {
        if (json != null && json.length() > 0) {
            if (json.indexOf("{") == 0 && json.lastIndexOf("}") == json.length() || json.lastIndexOf("}") == json.length() + 1 || json.lastIndexOf("}") == json.length() - 1) {
                JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                return getPdtProductTitle(pkey, jsonObject.get("en").getAsString());
            } else {
                json.trim();
                json = json.replace(" ", "-").replace("\\", "").replace("/", "").toLowerCase();
                double pk = Math.log10(pkey) + 1;
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < Math.min((150 - pk), json.length()); i++) {
                    stringBuffer.append(json.charAt(i));
                }
                stringBuffer.append("_p");
                stringBuffer.append(pkey);
                stringBuffer.append(".html");
                return stringBuffer.toString();
            }
        }
        return null;
    }

    public static String firstUpperCase(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() < 1)
            return s;
        s = s.toLowerCase();
        String[] strings = s.split(" ");
        StringBuffer stringBuffer = new StringBuffer();
        for (String string : strings) {
            if (string.length() < 1) {
                break;
            }
            if (stringBuffer.length() > 0) {
                stringBuffer.append(" ");
            }
            stringBuffer.append(Character.toUpperCase(string.charAt(0)));
            stringBuffer.append(string.substring(1, string.length()));

        }
        return stringBuffer.toString();
    }

}
