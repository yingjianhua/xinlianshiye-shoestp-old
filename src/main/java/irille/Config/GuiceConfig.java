package irille.Config;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Singleton;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.struts2.Struts2GuicePluginModule;

import irille.Aops.CacheAopsInterceptor;
import irille.Aops.Caches;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/5 Time: 16:18 */
public class GuiceConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return Guice.createInjector(
        new Struts2GuicePluginModule(),
        new ServletModule() {
          @Override
          protected void configureServlets() {
            // Struts 2 setup
            binder()
                .bindInterceptor(
                    Matchers.any(),
                    Matchers.annotatedWith(Caches.class),
                    new CacheAopsInterceptor());
            bind(ObjectMapper.class).toInstance(mapper);
            bind(Mailer.class).toInstance(GuiceConfig.build());
            bind(StrutsPrepareAndExecuteFilter.class).in(Singleton.class);
            filter("/*").through(StrutsPrepareAndExecuteFilter.class);
          }
        });
  }

  private static Mailer build() {
    Properties properties = new Properties();
    try {
      properties.load(GuiceConfig.class.getClassLoader().getResourceAsStream("email.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Mailer mailer =
        MailerBuilder.withSMTPServer(
                properties.getProperty("mail.smtp.host"),
                Integer.parseInt(properties.getProperty("mail.smtp.port")),
                properties.getProperty("mail.sender"),
                properties.getProperty("mail.smtp.password"))
            .withTransportStrategy(TransportStrategy.SMTPS)
            .buildMailer();
    return mailer;
  }
}
