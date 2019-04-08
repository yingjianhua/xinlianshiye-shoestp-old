package fy;

import javax.script.ScriptException;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/9/25
 * Time: 18:44
 */
public class tranlatesTest {
        public static void main(String[] args) throws FileNotFoundException, ScriptException {
        try {
        	String[] language = { "en", "ar", "fr", "ru", "es" };
        	for (String string : language) {
        		getJsonOject("E:/JAVA/workspace/wzdl/src/main/webapp/js/lang_zh_CN.js", string);
			}
            //getJsonOject("E:/JAVA/workspace/wzdl/src/main/webapp/js/zh_CN.js", "ar");
        } catch (IOException e) {
            e.printStackTrace();
        }
        	//System.out.println(StringEscapeUtils.unescapeHtml("&lt;&lt;Previous page"));
    }
//    public static void main(String[] args) throws FileNotFoundException, ScriptException {
////        try {
////            getJsonOject("D:\\Project\\shoestp\\WebContent\\home\\static\\js\\lang\\zh_CN.js", "en");
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
//        Object result = engine.eval(new FileReader("D:/Project/shoestp/WebContent/home/static/js/lang/zh_CN.js"));
//        engine.eval("var MyJavaClass = Java.type('tranlatesTest');");
////        engine.eval("for (var key in lang_obj ){" +
////                "if (typeof lang_obj[key]!=='object'){MyJavaClass.hello(lang_obj[key]).length}" +
////                "}");
//        engine.eval("MyJavaClass.hello(lang_obj)");
////        engine.eval("print(MyJavaClass.hello())");
//
//    }
//
//    public static String hello(ScriptObjectMirror s) {
//        for (String ownKey : s.getOwnKeys(true)) {
//            System.out.println(s.get(ownKey).getClass());
//        }
//        return "Java";
//    }


    public static String getJsonOject(String path, String targetLang) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(path).toFile()));
        System.out.println(Paths.get(Paths.get(path).toFile().getParent() + "\\lang_" + targetLang + ".js"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get(Paths.get(path).toFile().getParent() + "\\lang_" + targetLang + ".js").toFile()));
        StringBuffer stringBuffer = new StringBuffer();
        while (bufferedReader.ready()) {
            String s = bufferedReader.readLine();
            String temp = s;
            if (s.indexOf(":") != -1 && s.lastIndexOf("\"") > s.indexOf(":")) {
                System.out.println(s);
                s = s.substring(s.indexOf("\"", s.indexOf(":")) + 1, s.lastIndexOf("\""));
//                String target = translateUtil.translate(s, targetLang).getText();
//                target = StringEscapeUtils.unescapeHtml(target);
//                target = target.substring(0, 1).toUpperCase() + target.substring(1);
//                bufferedWriter.write(temp.replace(s, target.replace("\"", "'")));
                bufferedWriter.write("\r\n");
            } else {
                bufferedWriter.write(s);
                bufferedWriter.write("\r\n");
            }
        }
        System.out.println("==================");
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();

        return stringBuffer.toString();

    }

}
