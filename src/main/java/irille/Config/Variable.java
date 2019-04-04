package irille.Config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import irille.Entity.pm.PM.OTempType;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface Variable {
  Attribute[] attributes();

  OTempType[] group();

  Class clazz();

  Class enumType();
}
