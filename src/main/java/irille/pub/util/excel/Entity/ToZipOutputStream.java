package irille.pub.util.excel.Entity;

import java.io.ByteArrayOutputStream;

import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/10 Time: 15:05 */
@Data
public class ToZipOutputStream {
  private ByteArrayOutputStream outputStream;
  private String name;
  //    后缀名  不带 点(.)
  private String suffix;

  public static ToZipOutputStream from(
      String name, String suffix, ByteArrayOutputStream byteArrayOutputStream) {
    ToZipOutputStream toZipOutputStream = new ToZipOutputStream();
    toZipOutputStream.setName(name);
    toZipOutputStream.setSuffix(suffix);
    toZipOutputStream.setOutputStream(byteArrayOutputStream);
    return toZipOutputStream;
  }
}
