package irille.pub.util.file;

import java.io.*;

public class Test4I18n {

  public static void main(String[] args) throws IOException {
    String path = "E:/eclipse-workspace/shoestp/src/shoestp_zh_TW.properties";
    File file = new File(path);
    BufferedReader in = null;
    PrintWriter out = null;
    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
      out = new PrintWriter(new File("D:/shoestp_zh_TW.properties"));
      String buffer = null;
      while ((buffer = in.readLine()) != null) {
        if (buffer.trim().equals("") || buffer.startsWith("#")) continue;
        if (buffer.indexOf("=") == -1) continue;
        String str =
            "String "
                + buffer.substring(0, buffer.indexOf("=") + 1).replaceAll("\\.", "_")
                + "\""
                + buffer.substring(buffer.indexOf("=") + 1)
                + "\";";
        System.out.println(str);
        out.println(str);
      }
      out.flush();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      in.close();
      out.close();
    }
  }
}
