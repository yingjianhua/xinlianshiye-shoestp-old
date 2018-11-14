package irille.Aops;

import irille.pub.util.CacheUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/9
 * Time: 10:06
 */
public class CacheAopsInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        StringBuffer buffer = new StringBuffer();
        for (Object argument : methodInvocation.getArguments()) {
            if (argument != null)
                buffer.append(argument);
        }
        if (buffer.length() > 0) {
            buffer.insert(0, String.valueOf((methodInvocation.getThis().toString() + methodInvocation.getMethod().getName()).hashCode())).append("_");
        }
        Object result = CacheUtils.cache.get(buffer.toString(), o -> {
            try {
                return methodInvocation.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
        if (result == null)
            return methodInvocation.proceed();
        return result;
    }
}
