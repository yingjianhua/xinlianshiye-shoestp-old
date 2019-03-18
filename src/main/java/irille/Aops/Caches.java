package irille.Aops;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/9 Time: 10:12 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Caches {}
