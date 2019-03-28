package myUtilsTest;

import java.util.regex.Pattern;

import irille.pub.validate.Regular;
import org.junit.Assert;
import org.junit.Test;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/3/28 Time: 16:40 */
public class RegularTest {
  @Test
  public void testname() {
    Pattern pattern = Pattern.compile(Regular.REGULAR_NAME);
    Assert.assertFalse(pattern.matcher("你好?").find());
    Assert.assertTrue(pattern.matcher("你好?很高").find());
    Assert.assertTrue(pattern.matcher("你好?   哈啊").find());
    Assert.assertFalse(pattern.matcher("?你好").find());
    Assert.assertTrue(pattern.matcher("العَرَبِيَّة").find());
    Assert.assertTrue(pattern.matcher("español").find());
  }

  @Test
  public void testcompanyname() {
    Pattern pattern = Pattern.compile(Regular.REGULAR_COMPANY);
    Assert.assertFalse(pattern.matcher("你好?").find());
    Assert.assertTrue(pattern.matcher("你好?很高").find());
    Assert.assertTrue(pattern.matcher("你好?   哈啊").find());
    Assert.assertFalse(pattern.matcher("?你好").find());
    Assert.assertTrue(pattern.matcher("العَرَبِيَّة").find());
    Assert.assertTrue(pattern.matcher("español").find());
  }
}
