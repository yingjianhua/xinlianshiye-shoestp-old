package irille.Aops;

import java.util.StringJoiner;

import irille.pub.util.CacheUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.struts2.ServletActionContext;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/9 Time: 10:06 */
public class CacheAopsInterceptor implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    StringJoiner buffer = new StringJoiner("_");
    for (Object argument : methodInvocation.getArguments()) {
      if (argument != null) buffer.add(String.valueOf(argument));
    }
    if (buffer.length() > 0) {
      buffer.add(
          String.valueOf(
              (methodInvocation.getClass().toString()
                  + methodInvocation.getMethod().getName()
                  + methodInvocation.getMethod().getParameterTypes())));
    }
    buffer.add(ServletActionContext.getRequest().getSession().getId());
    buffer.add(methodInvocation.getMethod().getReturnType().getSimpleName());
    Object result =
        CacheUtils.cache.get(
            buffer.toString(),
            o -> {
              try {
                return methodInvocation.proceed();
              } catch (Throwable throwable) {
                throwable.printStackTrace();
              }
              return null;
            });
    if (result == null) return methodInvocation.proceed();
    return result;
  }
}
