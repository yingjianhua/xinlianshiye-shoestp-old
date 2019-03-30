package fy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSize;
import irille.shop.pdt.PdtSpec;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.junit.Test;

public class newFY {
  private static String path = "C:\\Users\\admin\\Desktop\\新建文本文档.txt";
  private static String[] language = {
    "ja", "de", "fr", "es", "ru", "pt", "zh_CN", "zh_TW", "hu", "ro"
  };
  private static String sEncoding = "UTF-8";
  private static String tEncoding = "ISO-8859-1";
  @Test
  public void test() {
    for (String Language : language) {
      newFY.readFile(path, Language);
    }
  }

  public static void main(String[] args) throws Exception {
	  PdtProduct.TB.getCode();
	  PdtSize.TB.getCode();
	  PdtSpec.TB.getCode();
    for (String Language : language) {
      newFY.readFile(path, Language);
    }
  }
  /**
   * @param filePath
   * @param language
   */
  public static void readFile(String filePath, String language) {
    String value = null; // 截取=后面的字符串
    try {
      String encoding = sEncoding;
      File file = new File(filePath);
      if (file.isFile() && file.exists()) { // 判断文件是否存在
        InputStreamReader read =
            new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        BufferedWriter bufferedWritertxt =
            new BufferedWriter(
                new FileWriterWithEncoding(
                    "C:\\Users\\admin\\Desktop\\新建文本文档" + language + ".txt", tEncoding));
        String lineTxt = null; // 读取出来的所有字符串
        while ((lineTxt = bufferedReader.readLine()) != null) {
          if (lineTxt.indexOf("=") != -1) {
            lineTxt.substring(lineTxt.indexOf("="));
            value = lineTxt.substring(lineTxt.indexOf("=") + 1);
            String FYH = translateUtil.translate(value, language).getText();
            System.out.println("翻译后>>>>>>>>>>>>" + lineTxt.replace(value, FYH));
            bufferedWritertxt.write(new String(lineTxt.replace(value, FYH).getBytes(), tEncoding));
            bufferedWritertxt.write("\r\n");
          }
        }
        read.close();
        bufferedWritertxt.flush();
        bufferedWritertxt.close();
        bufferedReader.close();
      } else {
        System.out.println("找不到指定的文件");
      }
    } catch (Exception e) {
      System.out.println("读取文件内容出错");
      e.printStackTrace();
    }
  }
}
