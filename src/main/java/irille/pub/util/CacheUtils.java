package irille.pub.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtSize;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/10/6
 * Time: 10:23
 */
public class CacheUtils {


    public static LoadingCache<Integer, JsonObject> colorCache = CacheBuilder.newBuilder().expireAfterAccess(12, TimeUnit.HOURS).maximumSize(1000).build(new CacheLoader<Integer, JsonObject>() {
        @Override
        public JsonObject load(Integer s) throws Exception {
            return new JsonParser().parse(PdtColor.get(PdtColor.class, s).getName()).getAsJsonObject();
        }
    });

    public static LoadingCache<Integer, JsonObject> sizeCache = CacheBuilder.newBuilder().expireAfterAccess(12, TimeUnit.HOURS).maximumSize(1000).build(new CacheLoader<Integer, JsonObject>() {
        @Override
        public JsonObject load(Integer s) throws Exception {
            return new JsonParser().parse(PdtSize.get(PdtSize.class, s).getName()).getAsJsonObject();
        }
    });
}
