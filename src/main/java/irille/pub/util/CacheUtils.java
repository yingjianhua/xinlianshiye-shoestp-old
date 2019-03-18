package irille.pub.util;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtSize;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/10/6 Time: 10:23 */
public class CacheUtils {

  public static LoadingCache<Integer, JsonObject> colorCache =
      CacheBuilder.newBuilder()
          .expireAfterAccess(12, TimeUnit.HOURS)
          .maximumSize(1000)
          .build(
              new CacheLoader<Integer, JsonObject>() {
                @Override
                public JsonObject load(Integer s) throws Exception {
                  return new JsonParser()
                      .parse(PdtColor.get(PdtColor.class, s).getName())
                      .getAsJsonObject();
                }
              });

  public static LoadingCache<Integer, JsonObject> sizeCache =
      CacheBuilder.newBuilder()
          .expireAfterAccess(12, TimeUnit.HOURS)
          .maximumSize(1000)
          .build(
              new CacheLoader<Integer, JsonObject>() {
                @Override
                public JsonObject load(Integer s) throws Exception {
                  return new JsonParser()
                      .parse(PdtSize.get(PdtSize.class, s).getName())
                      .getAsJsonObject();
                }
              });

  /**
   * @Description: 目前给单页做缓存
   *
   * @date 2018/11/8 14:34
   * @author lijie@shoestp.cn
   */
  public static Cache cache = Caffeine.newBuilder().expireAfterAccess(15, TimeUnit.MINUTES).build();
  /**
   * @Description: PK大赛 联合采购男鞋女鞋儿童鞋分类 缓存数据
   *
   * @date 2018/11/27 18:57
   * @anthor wilson zhang
   */
  public static Cache groupshopcache =
      Caffeine.newBuilder().expireAfterAccess(15, TimeUnit.MINUTES).build();
  /**
   * @Description: 邮箱注册失效
   *
   * @author chen
   */
  public static Cache mailValid =
      Caffeine.newBuilder().expireAfterAccess(24, TimeUnit.HOURS).build();

  /**
   * @Description: 邮箱重置密码失效
   *
   * @author chen
   */
  public static Cache pwdValid =
      Caffeine.newBuilder().expireAfterAccess(15, TimeUnit.MINUTES).build();
  /**
   * @Description: 60S间隔发送邮箱
   *
   * @author chen
   */
  public static Cache sendEm = Caffeine.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES).build();
}
