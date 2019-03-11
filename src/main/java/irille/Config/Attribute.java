package irille.Config;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({}) 
@Retention(RUNTIME)
public @interface Attribute {
	String name();
	String field();
	Class type();
}
