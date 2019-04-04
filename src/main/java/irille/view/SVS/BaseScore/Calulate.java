package irille.view.SVS.BaseScore;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface Calulate {

  Class<? extends SVSHandler<?>>[] handleClass() default {};
}
