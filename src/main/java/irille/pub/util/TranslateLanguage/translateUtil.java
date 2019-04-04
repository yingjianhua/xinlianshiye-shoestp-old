package irille.pub.util.TranslateLanguage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import irille.pub.bean.Bean;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.tb.IEnumFld;
import irille.pub.util.AppConfig;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSize;
import irille.shop.pdt.PdtSpec;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltTrantslate;
import irille.shop.plt.PltTrantslateDAO;
import irille.shop.usr.UsrSupplier;
import org.apache.commons.lang3.StringEscapeUtils;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/8/13 Time: 9:45 */
public class translateUtil {
  private static final String appKey = "AIzaSyCPbc3yNYQgVc56qbUuAY_Yap-uDMkDkvc";
  private static Translate translate = null;
  public static boolean useCache = true;
  private static JsonParser jsonParser;
  private static ListeningExecutorService service;
  private static PltTrantslateDAO.Select select = new PltTrantslateDAO.Select();
  private static PltTrantslateDAO.Ins ins = new PltTrantslateDAO.Ins();
  private static final Logger logger = LoggerFactory.getLogger(translateUtil.class);
  private static LoadingCache<String, List<String>> classCache =
      CacheBuilder.newBuilder()
          .maximumSize(2000)
          .expireAfterWrite(1, TimeUnit.HOURS)
          .build(
              new CacheLoader<String, List<String>>() {
                @Override
                public List<String> load(String s) throws Exception {
                  try {
                    Class c = Class.forName(s);
                    if (Bean.class.isAssignableFrom(c)) {
                      for (Class<?> aClass : c.getClasses()) {
                        if (IEnumFld.class.isAssignableFrom(aClass)) {
                          return getMultiFld(aClass);
                        }
                      }
                    }
                  } catch (ClassNotFoundException e) {
                  }
                  return Lists.newArrayList();
                }
              });
  private static LoadingCache<TranslateBean, TranslateBean> tranlateCache =
      CacheBuilder.newBuilder()
          .maximumSize(10 * 1000 * 20)
          .expireAfterWrite(1, TimeUnit.HOURS)
          .removalListener(
              (RemovalListener<TranslateBean, TranslateBean>)
                  removalNotification ->
                      logger.debug(
                          String.format("Guava Cache Remove:", removalNotification.getKey())))
          .build(
              new CacheLoader<TranslateBean, TranslateBean>() {
                @Override
                public TranslateBean load(TranslateBean s) throws Exception {
                  if (s.isCache() && s.getText() != null && useCache) {
                    logger.debug("use cache");
                    TranslateBean translateBean =
                        select.getTransLatesByHashCode(s.getText(), s.getTargetLanguage());
                    if (translateBean != null) return translateBean;
                    if (s.getSourceLanguage() != null && s.getSourceLanguage().length() > 0) {
                      translateBean = translate(s);
                    } else {
                      translateBean = translate(s.getText(), s.getTargetLanguage());
                    }
                    PltTrantslate pubTrantslate = new PltTrantslate();
                    if (translateBean != null) {
                      pubTrantslate.setHashcode(String.valueOf(s.getText().hashCode()));
                      pubTrantslate.setSourceText(s.getText());
                      pubTrantslate.setTarget(s.getTargetLanguage());
                      pubTrantslate.setTargetText(translateBean.getText());
                      ins.setB(pubTrantslate).commit();
                    }
                    return translateBean;
                  } else {
                    if (s.getSourceLanguage() != null && s.getSourceLanguage().length() > 0) {
                      return translate(s);
                    } else {
                      return translate(s.getText(), s.getTargetLanguage());
                    }
                  }
                }
              });
  private static Cache<String, Method> methodCache =
      CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(1, TimeUnit.HOURS).build();

  private static Map<String, List<String>> globalNoCacheFilter = new HashMap<>();
  private static Map<String, List<String>> globalFilter = new HashMap<>();

  static {
    System.setProperty("GOOGLE_API_KEY", appKey);
    jsonParser = new JsonParser();
    //        service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
    addFilterToGlobalFilter(PdtProduct.class, PdtProduct.T.DESCRIPTION);
    addFilterToGlobalFilter(PdtProduct.class, PdtProduct.T.DESCRIBE_MODULE_1);
    addFilterToGlobalFilter(PdtProduct.class, PdtProduct.T.DESCRIBE_MODULE_2);
    addFilterToGlobalFilter(PdtProduct.class, PdtProduct.T.DESCRIBE_MODULE_3);
    addFilterToGlobalFilter(UsrSupplier.class, UsrSupplier.T.HOME_PAGE_DIY);
    addFilterToGlobalFilter(UsrSupplier.class, UsrSupplier.T.PRODUCT_PAGE_DIY);
    addFilterToGlobalFilter(UsrSupplier.class, UsrSupplier.T.CONTACT_PAGE_DIY);
    addFilterToGlobalFilter(PdtSize.class, PdtSize.T.NAME);
    addFilterToGlobalFilter(PdtSpec.class, PdtSpec.T.KEY_NAME);
  }

  /**
   * @Description:使用 缓存 添加过滤字段 到全局过滤器
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:08
   */
  public static void addFilterToGlobalNoCacheFilter(Class c, IEnumFld... enumFlds) {
    globalNoCacheFilter.put(
        c.getName(),
        Arrays.asList(enumFlds).stream()
            .map(
                enumFld -> {
                  return enumFld.getFld().getCode();
                })
            .collect(Collectors.toList()));
  }

  /**
   * @Description: 不翻译字段 全局的
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:09
   */
  public static void addFilterToGlobalFilter(Class c, IEnumFld... enumFlds) {
    List<String> list = globalFilter.get(c.getName());
    if (list == null) {
      globalFilter.put(
          c.getName(),
          Arrays.asList(enumFlds).stream()
              .map(
                  enumFld -> {
                    return enumFld.getFld().getCode();
                  })
              .collect(Collectors.toList()));
    } else {
      list.addAll(
          Arrays.asList(enumFlds).stream()
              .map(
                  enumFld -> {
                    return enumFld.getFld().getCode();
                  })
              .collect(Collectors.toList()));
    }
  }

  /**
   * @Description: 初始化谷歌翻译Api
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:10
   */
  private static void init() {
    if (translate == null) {
      TranslateOptions translateOptions = null;
      try {
        translateOptions = TranslateOptions.getDefaultInstance();
        translate = translateOptions.getService();
      } catch (Exception e) {

      }
    }
  }

  /**
   * @Description: 翻译多语言字段 目标语言到多语言
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:11
   */
  public static String getMultiLanguageTrans(String value, boolean forceTrans) {
    return getMultiLanguageTrans(value, null, null);
  }

  /**
   * @Description: 翻译单语言
   *
   * @params sourceText 要翻译的内容 targetLanguage 目标的语言
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:14
   */
  public static TranslateBean translate(String sourceText, String targetLanguage) {
    TranslateBean translateBean = new TranslateBean();
    translateBean.setText(sourceText);
    translateBean.setTargetLanguage(targetLanguage);
    return translate(translateBean);
  }

  /**
   * @param
   * @return @Description: sourceText 要翻译的内容 Locale 目标语言的Locale信息
   * @date 2018/10/7 13:14
   * @author lijie@shoestp.cn
   */
  public static JsonObject translate(String sourceText, FldLanguage.Language... list) {
    JsonObject jsonObject = new JsonObject();
    for (Language lang : list) {
      TranslateBean translateBean = new TranslateBean();
      translateBean.setText(sourceText);
      translateBean.setTargetLanguage(lang.name());
      try {
        translateBean = tranlateCache.get(translateBean);
        jsonObject.addProperty(lang.name(), translateBean.getText());
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    return jsonObject;
  }

  /**
   * @param
   * @return @Description: sourceText 要翻译的内容 Locale 目标语言的Locale信息
   * @date 2018/10/7 13:14
   * @author lijie@shoestp.cn
   */
  public static JsonObject translate(String sourceText, Locale... list) {
    JsonObject jsonObject = new JsonObject();
    for (Locale locale : list) {
      TranslateBean translateBean = new TranslateBean();
      translateBean.setText(sourceText);
      translateBean.setTargetLanguage(locale.getLanguage());
      try {
        translateBean = tranlateCache.get(translateBean);
        jsonObject.addProperty(locale.getLanguage(), translateBean.getText());
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    return jsonObject;
  }

  /**
   * @Description: 翻译语言 使用缓存
   *
   * @params sourceText 要翻译的内容 targetLanguage 目标的语言
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:14
   */
  public static TranslateBean translateUseCachee(String sourceText, String targetLanguage) {
    TranslateBean translateBean = new TranslateBean();
    translateBean.setText(sourceText);
    translateBean.setTargetLanguage(targetLanguage);
    try {
      return tranlateCache.get(translateBean);
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * @return irille.pub.util.TranslateLanguage.TranslateBean @Description:
   * @date 2018/9/25 16:01
   * @author lijie@shoestp.cn
   */
  public static TranslateBean translate(TranslateBean params) {
    init();
    try {
      TranslateBean result = new TranslateBean();
      if (params.getText() == null || params.getText().length() < 1) {
        result = new TranslateBean();
        result.setText("");
        result.setSourceLanguage(params.getSourceLanguage());
        result.setTargetLanguage(params.getTargetLanguage());
        return result;
      }
      Translation translation =
          translate.translate(
              params.getText(),
              Translate.TranslateOption.targetLanguage(params.getTargetLanguage()),
              Translate.TranslateOption.sourceLanguage(params.getSourceLanguage()));
      //
      result.setText(translation.getTranslatedText());
      result.setSourceLanguage(translation.getSourceLanguage());
      result.setTargetLanguage(params.getTargetLanguage());
      try {
        Thread.sleep(
            ((result.getText().length() * FldLanguage.Language.values().length) / 10000) * 2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * @Description: 多语言获取目标样的 {"en"}
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:18
   */
  public static String getLanguage(Object o, FldLanguage.Language language) {
    if (o != null && o instanceof String) {
      String sourece = String.valueOf(o);
      if (sourece.length() > 0) {
        if (sourece.charAt(0) == '{' && sourece.charAt(sourece.length() - 1) == '}') {
          JsonObject jsonObject1 = (JsonObject) new JsonParser().parse(String.valueOf(sourece));
          if (jsonObject1.has(language.toString())) {
            if (jsonObject1.get(language.toString()).getAsString().replace(" ", "").length() < 1) {
              return jsonObject1.get(FldLanguage.Language.en.toString()).getAsString();
            } else {
              return jsonObject1.get(language.toString()).getAsString();
            }

          } else {
            if (jsonObject1.has(FldLanguage.Language.en.toString())) {
              return jsonObject1.get(FldLanguage.Language.en.toString()).getAsString();
            } else {
              if (jsonObject1.entrySet().iterator().hasNext())
                return jsonObject1
                    .get(jsonObject1.entrySet().iterator().next().getKey())
                    .getAsString();
            }
          }
        }
      }
      return String.valueOf(o);
    }
    return null;
  }

  /**
   * @Description: 自动翻译
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:18
   */
  public static <T> T autoTranslate(T obj) {
    return newAutoTranslate(obj, null);
  }

  /**
   * @Description: 自动翻译
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:19
   */
  public static <T> T autoTranslate(T obj, boolean forceTrans) {
    TranslateFilter translateFilter = null;
    if (forceTrans) {
      translateFilter = new TranslateFilter();
      translateFilter.setMode(1);
      translateFilter.setLanguageList(Arrays.asList(FldLanguage.Language.values()));
    }
    return newAutoTranslate(obj, translateFilter);
  }

  /**
   * @Description: 自动翻译 根据平台设置的语言
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:19
   */
  public static <T> T autoTranslateByManageLanguage(T obj, boolean forceTrans) {
    TranslateFilter translateFilter = null;
    if (forceTrans) {
      translateFilter = new TranslateFilter();
      translateFilter.setMode(1);
      translateFilter.setLanguageList(Arrays.asList(FldLanguage.Language.values()));
      translateFilter.setBaseLanguage(PltConfigDAO.manageLanguage());
    }
    return newAutoTranslate(obj, translateFilter);
  }

  /**
   * @Description: 多语言字段 转化成 目标语言
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/25 15:19
   */
  public static <T> T getAutoTranslate(T obj, FldLanguage.Language fldLanguage) {
    if (obj instanceof List) {
      return (T) getAutoTranslateList((List) obj, fldLanguage);
    }
    if (obj instanceof Map) {
      return (T) getAutoTranslateMap((Map) obj, fldLanguage);
    }
    try {
      String className = obj.getClass().getName();
      Class aClass = obj.getClass();
      for (String fldName : classCache.get(className)) {
        Method getMethod = methodCache.getIfPresent(className + "get" + fldName);
        if (getMethod == null) {
          getMethod = aClass.getMethod(getMethodString("get", fldName), null);
          methodCache.put(className + "get" + fldName, getMethod);
        }
        Object getValueObjet = null;
        getValueObjet = getMethod.invoke(obj, null);
        if (getValueObjet == null) continue;
        String getValue = String.valueOf(getValueObjet);
        if (getValue != null) {
          Method setMethod = methodCache.getIfPresent(className + "set" + fldName);
          if (setMethod == null) {
            setMethod = aClass.getMethod(getMethodString("set", fldName), String.class);
            methodCache.put(className + "set" + fldName, setMethod);
          }
          String value = getLanguage(getValue, fldLanguage);
          if (value.length() == 0) {
            logger.info("空字符串");
            logger.info(obj.toString());
          }
          setMethod.invoke(obj, value);
        }
      }
      return obj;
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static List getAutoTranslateList(List list, FldLanguage.Language fldLanguage) {
    Iterator iterator = list.iterator();
    List result = new ArrayList();
    while (iterator.hasNext()) {
      Object o = iterator.next();
      if (o == null) continue;
      if (o instanceof Map) {
        result.add(getAutoTranslateMap((Map) o, fldLanguage));
      } else {
        if (o instanceof List) {
          result.add(getAutoTranslateList((List) o, fldLanguage));
        } else {
          result.add(getAutoTranslate(o, fldLanguage));
        }
      }
    }
    return result;
  }

  public static Map getAutoTranslateMap(Map map, FldLanguage.Language fldLanguage) {
    Iterator iterator = map.entrySet().iterator();
    Map.Entry entry;
    while (iterator.hasNext()) {
      entry = (Map.Entry) iterator.next();
      if (entry.getValue() == null) continue;
      if (entry.getValue() instanceof List) {
        map.put(
            getAutoTranslate(entry.getKey(), fldLanguage),
            getAutoTranslateList((List) entry.getValue(), fldLanguage));
      } else {
        if (entry.getValue() instanceof Map) {
          map.put(
              getAutoTranslate(entry.getKey(), fldLanguage),
              getAutoTranslateMap((Map) entry.getValue(), fldLanguage));

        } else {
          map.put(
              getAutoTranslate(entry.getKey(), fldLanguage),
              getAutoTranslate(entry.getValue(), fldLanguage));
        }
      }
    }
    return map;
  }

  private static String getMethodString(String type, String o) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer
        .append(type)
        .append(Character.toUpperCase(o.charAt(0)))
        .append(o.length() > 1 ? o.substring(1) : "");
    return stringBuffer.toString();
  }

  /**
   * @Description: 获取多语言字段
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/13 17:34
   */
  public static List<String> getMultiFld(Class aClass)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    List<String> list = new ArrayList<>();
    if (IEnumFld.class.isAssignableFrom(aClass)) {
      IEnumFld o =
          (IEnumFld) aClass.getDeclaredMethod("valueOf", String.class).invoke(aClass, "PKEY");
      for (Fld fld : o.getFld().getTb().getFlds()) {
        if (fld.getClass() == FldLanguage.class) {
          list.add(fld.getCode());
        }
      }
    }
    return list;
  }

  /**
   * @Description: 异步 自动翻译并插入数据库 (存在数据库锁问题,无法获取数据库锁导致失败
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/20 18:30
   */
  public static void autoTranslateSaveOrUpdate(ListenableFuture future, FutureCallback callback) {
    Futures.addCallback(future, callback, service);
  }

  /**
   * @Description: 异步 自动翻译并插入数据库
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/20 18:30
   */
  public static void autoTranslateSaveOrUpdate(
      Bean obj, TranslateFilter translateFilter, FutureCallback callback) {
    ListenableFuture<Bean> future =
        service.submit(
            () -> {
              return newAutoTranslate(obj, translateFilter);
            });
    autoTranslateSaveOrUpdate(future, callback);
  }

  public static void runListTask(
      List<ListenableFuture<Bean>> future, FutureCallback<List<Bean>> callback) {
    Futures.addCallback(Futures.allAsList(future), callback, service);
  }

  /**
   * @return
   * @params null @Description:
   * @date 2018/9/25 15:37
   * @author lijie@shoestp.cn
   */
  public static ListenableFuture<Bean> getListenableFuture(Callable<Bean> callable) {
    return service.submit(callable);
  }

  /**
   * @Description: 新的翻译类
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/6 14:08
   */
  public static <T> T newAutoTranslate(T obj, TranslateFilter translateFilter) {
    init();
    try {
      String className = obj.getClass().getName();
      Class aClass = obj.getClass();
      for (String fldName : classCache.get(className)) {
        if (translateFilter != null
            && translateFilter.getFld() != null
            && translateFilter.getFld().contains(fldName) == (translateFilter.getMode() == 0)) {
          continue;
        }
        boolean Iscontinue = false;
        Method getMethod = methodCache.getIfPresent(className + "get" + fldName);
        if (getMethod == null) {
          getMethod = aClass.getMethod(getMethodString("get", fldName), null);
          methodCache.put(className + "get" + fldName, getMethod);
        }
        if (globalFilter.get(className) != null && globalFilter.get(className).contains(fldName)) {
          logger.info(String.format("全局过滤器 字段:%s 不翻译", fldName));
          Iscontinue = true;
        }
        Object getValueObjet = getMethod.invoke(obj, null);
        if (getValueObjet == null) continue;
        String getValue = String.valueOf(getValueObjet);
        if (getValue != null) {
          if (translateFilter != null && translateFilter.getCacheFilter() == null) {
            translateFilter.setCacheFilter(globalNoCacheFilter.get(className));
          }
          String setValue = null;
          if (Iscontinue) {
            setValue = getValue;
          } else {
            setValue = getMultiLanguageTrans(getValue, translateFilter, fldName);
          }
          Method setMethod = methodCache.getIfPresent(className + "set" + fldName);
          if (setMethod == null) {
            setMethod = aClass.getMethod(getMethodString("set", fldName), String.class);
            methodCache.put(className + "set" + fldName, setMethod);
          }
          setMethod.invoke(obj, setValue);
        }
      }
    } catch (ExecutionException
        | NoSuchMethodException
        | IllegalAccessException
        | InvocationTargetException e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * @Description: 获取基准字段
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/6 13:23
   */
  private static String getBaseValue(String value, FldLanguage.Language baseLangauge) {
    if (value == null || value.length() < 1) {
      return null;
    }
    JsonObject ex = null;
    if (value.charAt(0) == '{' && value.charAt(value.length() - 1) == '}') {
      ex = jsonParser.parse(value).getAsJsonObject();
    }
    String baseValue = value;
    List<FldLanguage.Language> languages =
        new ArrayList<>(
            Arrays.asList(
                FldLanguage.Language.en, FldLanguage.Language.zh_CN, FldLanguage.Language.zh_TW));
    if (baseLangauge != null) languages.add(0, baseLangauge);
    for (FldLanguage.Language language : languages) {
      if (ex == null) break;
      if (ex.get(language.toString()) != null
          && ex.get(language.toString()).getAsString().length() > 0) {
        Object o = ex.get(language.toString());
        if (o != null) {
          baseValue = ex.get(language.toString()).getAsString();
          break;
        }
      } else {
        baseValue = "";
      }
    }

    return StringEscapeUtils.unescapeHtml4(baseValue);
  }

  public static JsonObject valuetoMultilanguageJson(String s) {
    JsonObject jsonObject = new JsonObject();
    for (FldLanguage.Language value : FldLanguage.Language.values()) {
      jsonObject.addProperty(value.name(), s);
    }
    return jsonObject;
  }

  /**
   * @Description: 新的翻译
   *
   * @author lijie@shoestp.cn
   * @date 2018/9/6 14:19
   */
  private static String getMultiLanguageTrans(
      String getValue, TranslateFilter translateFilter, String fldName) {
    JsonObject jsonObject = new JsonObject();
    for (FldLanguage.Language language : FldLanguage.Language.values()) {
      if (translateFilter != null) {
        switch (translateFilter.getMode()) {
            //          黑名单模式   在LanguageList里的不翻译
          case 0:
          case 2:
            if (translateFilter.getLanguageList() != null
                && translateFilter.getLanguageList().contains(language)) {
              jsonObject.addProperty(language.toString(), getBaseValue(getValue, language));
              continue;
            }
            break;
            // 白名单模式   在LanguageList里的均翻译
          case 1:
            if (translateFilter.getLanguageList() != null
                && !translateFilter.getLanguageList().contains(language)) {
              jsonObject.addProperty(language.toString(), getBaseValue(getValue, language));
              continue;
            }
            break;
        }
        if (language.equals(translateFilter.getBaseLanguage())) {
          jsonObject.addProperty(language.toString(), getBaseValue(getValue, language));
          continue;
        }
      }
      TranslateBean translateBean = new TranslateBean();
      if (translateFilter != null && translateFilter.getCacheFilter() != null) {
        translateBean.setCache(!translateFilter.getCacheFilter().contains(fldName));
      }
      if (translateFilter != null && translateFilter.getSourceLanguage() != null) {
        translateBean.setSourceLanguage(translateFilter.getSourceLanguage());
      }
      translateBean.setText(
          getBaseValue(
              getValue, translateFilter == null ? null : translateFilter.getBaseLanguage()));
      if (translateBean.getText().length() < 1) {
        jsonObject.addProperty(language.name(), translateBean.getText());
        continue;
      }
      translateBean.setTargetLanguage(language.toString());
      try {
        translateBean = tranlateCache.get(translateBean);
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
      if (translateFilter != null && translateFilter.getSourceLanguage() != null) {
        if (translateBean.getSourceLanguage() != null
            && translateBean.getSourceLanguage().length() > 0) {
          translateFilter.setSourceLanguage(translateBean.getSourceLanguage().replace("-", "_"));
        }
      }
      if (AppConfig.dev) {
        logger.info(
            String.format(
                "翻译:%s  源语言:%s   目标语言:%s   翻译后: %s\r\n",
                getBaseValue(
                    getValue, translateFilter == null ? null : translateFilter.getBaseLanguage()),
                translateBean.getSourceLanguage(),
                translateBean.getTargetLanguage(),
                translateBean.getText()));
      }
      jsonObject.addProperty(language.name(), translateBean.getText());
    }
    return jsonObject.toString();
  }

  /**
   * * 仅适用于 多语言是单个字段的
   *
   * @param dbJsonString
   * @param saveJson
   * @param baseLanguage
   * @return
   */
  public static TranslateFilter buildFilter(
      String dbJsonString, String saveJson, Language baseLanguage) {
    TranslateFilter translateFilter = new TranslateFilter();
    translateFilter.setBaseLanguage(baseLanguage);
    translateFilter.setLanguageList(new ArrayList<>());
    // 转化为Json
    JsonObject dbJson = new JsonParser().parse(dbJsonString).getAsJsonObject();
    // 前端传回来的值
    JsonObject jsonObject = new JsonParser().parse(saveJson).getAsJsonObject();
    // 判断基准字段是否发生修改
    if (dbJson.has(baseLanguage.name())
        && !dbJson
            .get(baseLanguage.name())
            .getAsString()
            .equals(jsonObject.get(baseLanguage.name()).getAsString())) {
      // 基准发生修改 用黑名单模式,修改的字段不翻译
      translateFilter.setMode(0);
      jsonObject
          .entrySet()
          .forEach(
              stringJsonElementEntry -> {
                // 如果不相等  添加到清单
                if (dbJson.has(stringJsonElementEntry.getKey())) {
                  String dbString = dbJson.get(stringJsonElementEntry.getKey()).getAsString();
                  if (!dbString
                      .trim()
                      .equals(stringJsonElementEntry.getValue().getAsString().trim())) {
                    translateFilter
                        .getLanguageList()
                        .add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
                  }
                }
              });
    } else {
      translateFilter.setMode(1);
      jsonObject
          .entrySet()
          .forEach(
              stringJsonElementEntry -> {
                // 如果相等  添加到清单
                if (dbJson.has(stringJsonElementEntry.getKey())) {
                  String dbString =
                      dbJson.get(stringJsonElementEntry.getKey()).getAsString().trim();
                  if (dbString.equals(stringJsonElementEntry.getValue().getAsString().trim())) {
                    translateFilter
                        .getLanguageList()
                        .add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
                  }
                }
              });
    }

    return translateFilter;
  }

  public static TranslateFilter buildFilter(String saveJson, Language baseLanguage) {
    TranslateFilter translateFilter = new TranslateFilter();
    translateFilter.setBaseLanguage(baseLanguage);
    translateFilter.setLanguageList(new ArrayList<>());
    // 前端传回来的值
    if (saveJson != null) {
      JsonObject jsonObject = new JsonParser().parse(saveJson).getAsJsonObject();
      translateFilter.setMode(2);
      jsonObject
          .entrySet()
          .forEach(
              stringJsonElementEntry -> {
                // 寻找为空的字段,按照基准字段翻译补全
                if (stringJsonElementEntry.getValue().getAsString() != null
                    && stringJsonElementEntry.getValue().getAsString().length() > 0) {
                  translateFilter
                      .getLanguageList()
                      .add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
                }
              });
    }
    return translateFilter;
  }

  /**
   * * 升格成多语言JSON
   *
   * @param saveJson
   * @return
   */
  public static String toSaveJson(String saveJson, FldLanguage.Language language) {
    JsonObject jsonObject = null;
    JsonObject result = new JsonObject();
    if (saveJson != null && saveJson.length() > 0) {
      if (saveJson.indexOf("{") == 0 && saveJson.indexOf("}") == saveJson.length() - 1) {
        jsonObject = new JsonParser().parse(saveJson).getAsJsonObject();
      }
      for (Language value : Language.values()) {
        String values = "";
        if (jsonObject != null && jsonObject.has(value.name())) {
          values = jsonObject.get(value.name()).getAsString();
        } else {
          if (value.equals(language)) {
            values = saveJson;
          }
        }
        result.addProperty(value.name(), values);
      }
    } else {
      for (Language value : Language.values()) {
        result.addProperty(value.name(), "");
      }
    }
    return result.toString();
  }
}
