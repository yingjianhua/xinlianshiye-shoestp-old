package irille.pub;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import irille.pub.verify.AnimatedGifEncoder;
import org.apache.commons.lang3.RandomStringUtils;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/6 Time: 19:28 */
public class verifyImgServlet extends HttpServlet {

  public static final String RANDOM_LOGIN_KEY = "RANDOM_LOGIN_KEY";

  public void init() throws ServletException {
    System.setProperty("java.awt.headless", "true");
  }

  public static String getRandomLoginKey(HttpServletRequest req) {
    return (String) req.getSession().getAttribute("RANDOM_LOGIN_KEY");
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    boolean gif_enabled = support(req, "image/gif") || !support(req, "image/png");
    HttpSession ssn = req.getSession(true);
    String randomString = random();
    ssn.setAttribute("RANDOM_LOGIN_KEY", randomString);
    res.setContentType(gif_enabled ? "image/gif" : "image/png");
    res.setHeader("Pragma", "No-cache");
    res.setHeader("Cache-Control", "no-cache");
    res.setDateHeader("Expires", 0L);
    render(
        randomString,
        gif_enabled,
        req.getParameter("fontcolor"),
        req.getParameter("background"),
        res.getOutputStream());
  }

  protected static String random() {
    return RandomStringUtils.randomNumeric(4);
  }

  protected static void render(
      String num, boolean gif, String fontcolor, String background, OutputStream out)
      throws IOException {
    if (num.getBytes().length > 4) {
      throw new IllegalArgumentException("The length of param num cannot exceed 4.");
    } else {
      int width = 50;
      int height = 18;
      BufferedImage bi = new BufferedImage(width, height, 1);
      Graphics2D g = (Graphics2D) bi.getGraphics();
      //            g.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER)));
      g.setColor(getColor(background, Color.white));
      g.fillRect(0, 0, width, height);
      Font mFont = new Font("Tahoma", 3, 16);
      g.setFont(mFont);
      g.setColor(getColor(fontcolor, Color.BLACK));
      g.drawString(num, 2, 15);
      if (gif) {
        AnimatedGifEncoder e = new AnimatedGifEncoder();
        //                e.setTransparent(Color.WHITE);
        e.start(out);
        e.setDelay(0);
        e.addFrame(bi);
        e.finish();
      } else {
        ImageIO.write(bi, "png", out);
      }
    }
  }

  private static Color getColor(String color, Color defaultColor) {
    Color setColor = null;
    if (color != null && color.length() > 0) {
      if (color.indexOf("#") == -1) {
        setColor = Color.decode("#" + color);
      } else {
        setColor = Color.decode(color);
      }

      return setColor;
    } else {
      return defaultColor;
    }
  }

  public static String getHeader(HttpServletRequest req, String name) {
    String value = req.getHeader(name);
    if (value != null) {
      return value;
    } else {
      Enumeration names = req.getHeaderNames();

      String n;
      do {
        if (!names.hasMoreElements()) {
          return null;
        }

        n = (String) names.nextElement();
      } while (!n.equalsIgnoreCase(name));

      return req.getHeader(n);
    }
  }

  public static boolean support(HttpServletRequest req, String contentType) {
    String accept = getHeader(req, "accept");
    if (accept != null) {
      accept = accept.toLowerCase();
      return accept.indexOf(contentType.toLowerCase()) != -1;
    } else {
      return false;
    }
  }
}
