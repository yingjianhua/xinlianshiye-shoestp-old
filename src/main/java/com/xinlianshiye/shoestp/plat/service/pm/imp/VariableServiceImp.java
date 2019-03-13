package com.xinlianshiye.shoestp.plat.service.pm.imp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xinlianshiye.shoestp.plat.service.pm.IVariableService;
import com.xinlianshiye.shoestp.plat.view.pm.VariableView;

import irille.Config.Attribute;
import irille.Config.Variable;
import irille.Entity.RFQ.JSON.ConsultMessage;
import irille.Entity.pm.PM.OTempType;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumOpt;
import irille.pub.util.Scanner;
import irille.pub.util.TranslateLanguage.translateUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

@Slf4j
public class VariableServiceImp implements IVariableService {

  private static final String action_root_package = "irille";

  private static final Map<Integer, Map<String, VariableView>> map;

  static {
    map = initVariable();
  }

  // 变量有中文名称/变量值/所属操作
  public static void main(String[] args) throws JSONException {
    String s = "{\"content\":\"22222\"}";
    JSONObject json = new JSONObject(s);
    System.out.println(json);
  }

  // 根据模板类型获取对应变量
  @Override
  public List<String> loadByTempType(Integer type) {
    Map<String, VariableView> variables = map.get(type);
    List<String> keys = new ArrayList<>();
    if (variables != null && variables.size() > 0) {
      for (String key : variables.keySet()) {
        keys.add(key);
      }
      return keys;
    }
    return null;
  }

  private static Map<Integer, Map<String, VariableView>> initVariable() {
    List<Class<?>> clss =
        Scanner.findClassByAnnotationSkipInitialize(Variable.class, action_root_package);
    Map<Integer, Map<String, VariableView>> map = new HashMap<>();
    for (Class<?> cls : clss) {
      Map<Integer, Map<String, VariableView>> initMap = initMap(cls);
      for (Integer key : initMap.keySet()) {
        if (map.containsKey(key)) {
          map.get(key).putAll(initMap.get(key));
        } else {
          map.put(key, initMap.get(key));
        }
      }
    }
    return map;
  }

  private static Map<Integer, Map<String, VariableView>> initMap(Class<?> cls) {
    Map<Integer, Map<String, VariableView>> map = new HashMap<>();
    Variable valiable = cls.getAnnotation(Variable.class);
    OTempType[] tempTypes = valiable.group();
    Attribute[] attributes = valiable.attributes();

    for (OTempType temp : tempTypes) {
      Map<String, VariableView> attrMap = new HashMap<>();
      for (Attribute attr : attributes) {
        VariableView view = new VariableView();
        view.setField(attr.field());
        view.setClazz(valiable.clazz());
        view.setEnumType(valiable.enumType());
        view.setFieldType(attr.type());
        attrMap.put(attr.name(), view);
      }
      map.put(Integer.valueOf(temp.getLine().getKey()), attrMap);
    }
    return map;
  }

  /** 获取实体类中枚举字段中对应的实体类字段 */
  public static Field getDeclaredField(Class<?> clazz, Object obj) {
    try {
      Method fldMethod = obj.getClass().getMethod("getFld");
      Object fld = fldMethod.invoke(obj);
      Method codeMethod = fld.getClass().getMethod("getCode");
      Object code = codeMethod.invoke(fld);
      return clazz.getDeclaredField("_" + String.valueOf(code));
    } catch (NoSuchMethodException
        | SecurityException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException
        | NoSuchFieldException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** 根据枚举名称获取对应的枚举对象 */
  public static Object fromValue(Class<?> enumType, Object object) {
    try {
      Method method = enumType.getMethod("name");
      for (Object o : enumType.getEnumConstants()) {
        if (Objects.equals(object, method.invoke(o))) {
          return o;
        }
      }
    } catch (IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException
        | NoSuchMethodException
        | SecurityException e) {
      e.printStackTrace();
    }
    throw new IllegalArgumentException(
        "No enum value " + object + " of " + enumType.getCanonicalName());
  }

  /** 根据枚举值获取枚举名称 */
  public static <T extends IEnumOpt> String fromValue1(Class<T> enumType, Object value) {
    try {
      Method method = enumType.getMethod("values");
      if (value == null) {
        return null;
      }
      T[] values = (T[]) method.invoke(null);
      for (T x : values) {
        if (Integer.valueOf(String.valueOf(value)).equals(Integer.valueOf(x.getLine().getKey()))) {
          return x.getLine().getName();
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("can't convertToEntityAttribute" + e.getMessage());
    }
    throw new RuntimeException("unknown dbData " + value);
  }

  // 模板填充  格式{$XXX}
  @Override
  public String render(Map<String, VariableView> variableMap, String template, Object... objects) {
    String reg = "\\{\\$[\\S]*?\\}{1}";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Pattern pattern = Pattern.compile(reg);
    Matcher matcher = pattern.matcher(template);

    while (matcher.find()) {
      String str = matcher.group();
      String key = str.substring(str.indexOf("$") + 1, str.lastIndexOf("}"));
      VariableView variable = variableMap.get(key);
      Object object = null;

      if (key.equals("now") || key.equals("当前时刻")) {
        object = sdf.format(new Date());
        template =
            template.replaceAll(
                str.replace("{", "\\{").replace("$", "\\$").replace("}", "\\}"),
                String.valueOf(object));
        continue;
      }

      if (variable == null) {
        log.info("非法参数【" + key + "】");
        throw new WebMessageException(ReturnCode.service_wrong_data, "非法参数【" + key + "】");
      }
      Class clazz = variable.getClazz();
      Field field = getDeclaredField(clazz, fromValue(variable.getEnumType(), variable.getField()));
      if (null == field) {
        continue;
      }
      for (Object obj : objects) {
        try {
          field.setAccessible(true);
          if (null == field.get(obj)) {
            object = String.valueOf("");
          } else if (variable.getFieldType() == ConsultMessage.class) {
            JSONObject json = new JSONObject(String.valueOf(field.get(obj)));
            if (json.has("imageUrl")) {
              object = "<img src='' alt=''/>";
              if (json.has("alt")) {
                object += " alt='" + json.getString("alt") + "'";
              } else {
                object += " />";
              }
            } else if (json.has("content")) {
              object = json.getString("content");
            } else if (json.has("url")) {
              object = "<a target='_blank' href=" + json.getString("url") + "></a>";
            }
          } else if (variable.getFieldType() == Date.class) {
            object = sdf.format((Date) field.get(obj));
          } else if (variable.getFieldType() == FldLanguage.class) {
            JSONObject json = new JSONObject(String.valueOf(field.get(obj)));
            object = translateUtil.getLanguage(field.get(obj), FldLanguage.Language.en);
          } else if (variable.getFieldType().isEnum()) {
            object = fromValue1(variable.getFieldType(), field.get(obj));
          } else {
            object = field.get(obj);
          }
        } catch (IllegalArgumentException
            | IllegalAccessException
            | SecurityException
            | JSONException e) {
          continue;
        }
      }
      template =
          template.replaceAll(
              str.replace("{", "\\{").replace("$", "\\$").replace("}", "\\}"),
              String.valueOf(object));
    }

    return template;
  }

  @Override
  public Map<String, VariableView> getMap(OTempType tempType) {
    return map.get(Integer.valueOf(tempType.getLine().getKey()));
  }
}
