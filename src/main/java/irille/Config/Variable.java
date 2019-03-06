package irille.Config;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import irille.Entity.pm.PM.OTempType;

@Target({ElementType.TYPE}) 
@Retention(RUNTIME)
public @interface Variable {
	Attribute[] attributes();
	OTempType[] group();
	Class clazz();
	Class enumType();
}

