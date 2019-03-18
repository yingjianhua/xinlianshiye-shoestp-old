package irille.pub.util;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import irille.pub.util.file.Finder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Scanner {
  private static Map<Class<?>, List<Class<?>>> TYPE_ANNOTATION_MAPS = new HashMap<>();

  public static Map<String, Class<?>> CLASS_ENUM = new HashMap<>();

  public static void find() {
    Scanner.class.getResource("/").getPath();
    new Finder().find("", ".class").deal(dd -> {});
  }

  public static <T extends Annotation> List<Method> findMethodByAnnotation(
      Class<T> annotationClass, Class<?> clazz) {
    log.debug("scanning annotation {} in {}", annotationClass.getName(), clazz.getName());
    return Stream.of(clazz.getMethods())
        .filter(
            method -> {
              if (method.getAnnotation(annotationClass) != null) {
                log.debug("finded {}", method);
                return true;
              } else {
                return false;
              }
            })
        .collect(Collectors.toList());
  }

  /**
   * 从rootPackage包进行搜索带有annotationClass注解的类进入静态块
   *
   * <p>eg. <code>findClassByAnnotation(Controller.class, "com.irille.omt.action")</code>
   *
   * @param annotationClass 注解类
   * @param rootPackage 起始包位置
   * @return
   */
  public static <T extends Annotation> List<Class<?>> findClassByAnnotation(
      Class<T> annotationClass, String rootPackage) {
    return findClassByAnnotation(annotationClass, rootPackage, true, true);
  }

  /**
   * 从rootPackage包进行搜索带有annotationClass注解的类不进入静态块
   *
   * <p>eg. <code>findClassByAnnotation(Controller.class, "com.irille.omt.action")</code>
   *
   * @param annotationClass 注解类
   * @param rootPackage 起始包位置
   * @return
   */
  public static <T extends Annotation> List<Class<?>> findClassByAnnotationSkipInitialize(
      Class<T> annotationClass, String rootPackage) {
    return findClassByAnnotation(annotationClass, rootPackage, false, true);
  }

  /**
   * 从从项目根目录开始搜索带有annotationClass注解的类
   *
   * <p>eg. <code>findClassByAnnotation(Controller.class)</code>
   *
   * @param annotationClass 注解类
   * @return
   */
  public static <T extends Annotation> List<Class<?>> findClassByAnnotation(
      Class<T> annotationClass) {
    return findClassByAnnotation(annotationClass, null, true, true);
  }

  public static <T extends Annotation> List<Class<?>> findClassByAnnotation(
      Class<T> annotationClass, String rootPackage, boolean initialize, boolean noCache) {
    log.debug("scanning annotation {} in {}", annotationClass.getName(), rootPackage);
    if (noCache || TYPE_ANNOTATION_MAPS.containsKey(annotationClass)) {
      String classPath = new File(Scanner.class.getResource("/").getFile()).getAbsolutePath();
      String rootPath =
          classPath
              + File.separator
              + (rootPackage == null ? "" : rootPackage.replaceAll("\\.", "\\\\"));

      List<Class<?>> classes =
          new Finder()
              .find(rootPath, "\\.class$").stream()
                  .map(fileName -> toClassName(classPath + File.separator, fileName))
                  .map(
                      className -> {
                        try {
                          if (!initialize) {
                            Class<?> cla =
                                Class.forName(className, false, Scanner.class.getClassLoader());
                            if (className.indexOf("$") != -1
                                && className
                                    .substring(className.lastIndexOf("$") + 1)
                                    .equals("T")) {
                              String pClassName =
                                  className.substring(0, className.lastIndexOf("$"));
                              CLASS_ENUM.put(pClassName, cla);
                            }

                            return cla;
                          } else {
                            return Class.forName(className);
                          }
                        } catch (ClassNotFoundException e) {
                          return null;
                        }
                      })
                  .filter(
                      clazz -> {
                        if (clazz != null && clazz.getAnnotation(annotationClass) != null) {
                          log.debug("finded {}", clazz.getName());
                          return true;
                        } else {
                          return false;
                        }
                      })
                  .collect(Collectors.toList());
      TYPE_ANNOTATION_MAPS.put(annotationClass, classes);
    }
    return TYPE_ANNOTATION_MAPS.get(annotationClass);
  }

  public static String toClassName(String classPath, String fileName) {
    if (fileName.indexOf(classPath) != -1 && fileName.indexOf(".class") != -1) {
      return fileName
          .substring(classPath.length(), fileName.indexOf(".class"))
          .replaceAll("\\\\", ".");
    } else {
      return null;
    }
  }
}
