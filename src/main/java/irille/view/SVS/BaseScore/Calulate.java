package irille.view.SVS.BaseScore;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface Calulate {

	Class<? extends SVSHandler<?>>[] handleClass() default { };
}
