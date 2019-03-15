package com.xinlianshiye.shoestp.common.errcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import irille.pub.tb.FldLanguage;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/3/13 Time: 16:02 */
public class MessageBuild {
  private static ConcurrentHashMap<String, HashMap<Integer, String>> hashMap;

  static {
    hashMap = new ConcurrentHashMap();
    for (FldLanguage.Language value : FldLanguage.Language.values()) {
      try {
        if (Files.exists(
            Paths.get(
                new URI(
                    MessageBuild.class.getResource("/")
                        + "errorCode/code_"
                        + value.name()
                        + ".ini")))) {
          try (BufferedReader bufferedReader =
              new BufferedReader(
                  new InputStreamReader(
                      MessageBuild.class
                          .getResource("/errorCode/code_" + value.name() + ".ini")
                          .openStream(),
                      "UTF-8")); ) {
            Pattern pattern = Pattern.compile("(\\d{1,})=(.*[\\r\\n]?)");
            while (bufferedReader.ready()) {
              String s = bufferedReader.readLine();
              Matcher matcher = pattern.matcher(s);
              if (matcher.find()) {
                String message = matcher.group(2);
                Integer i = Integer.parseInt(matcher.group(1));
                HashMap<Integer, String> map = hashMap.get(value.name());
                if (map == null) {
                  map = new HashMap<>();
                }
                MessageView body = null;
                if (body != null) {
                  System.err.println(
                      String.format("%d 该值存在重复,已存在的%s\r\n 现在存入值 %d,$s", i, body, i, message));
                } else {
                  System.out.println(String.format("正在处理值\r\n%d=%s", i, message));
                  map.put(i, message);
                  hashMap.put(value.name(), map);
                }
              }
            }
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    }
  }

  public static MessageView build(int code, FldLanguage.Language language) {
    HashMap<Integer, String> map = hashMap.get(language.name());
    String body = null;
    if (map != null) body = map.get(code);
    if (body == null || hashMap.get(FldLanguage.Language.en.name()) == null) {
      body = hashMap.get(FldLanguage.Language.en.name()).get(code);
    }
    if (body == null) {
      return new MessageView(code);
    }
    return new MessageView(code, body);
  }
}