package irille.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.struts2.Struts2GuicePluginModule;
import irille.Aops.Caches;
import irille.Aops.CacheAopsInterceptor;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import javax.inject.Singleton;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/5
 * Time: 16:18
 */
public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new Struts2GuicePluginModule(),
                new ServletModule() {
                    @Override
                    protected void configureServlets() {
                        // Struts 2 setup
                        binder().bindInterceptor(Matchers.any(), Matchers.annotatedWith(Caches.class), new CacheAopsInterceptor());
                        bind(StrutsPrepareAndExecuteFilter.class).in(Singleton.class);
                        filter("/*").through(StrutsPrepareAndExecuteFilter.class);
                    }
                });
    }
}
