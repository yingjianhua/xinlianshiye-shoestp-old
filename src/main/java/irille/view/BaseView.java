package irille.view;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;

public interface BaseView {

  public static final Validator validator =
      Validation.buildDefaultValidatorFactory().getValidator();

  default void validThrow(Class<?>... groups) {
    Set<ConstraintViolation<BaseView>> set = validator.validate(this, groups);
    if (!set.isEmpty()) {
      ConstraintViolation<BaseView> violation = set.iterator().next();
      throw new WebMessageException(ReturnCode.valid_unknow, violation.getMessage());
    }
  }

  default Set<ConstraintViolation<BaseView>> valid(Class<?>... groups) {
    Set<ConstraintViolation<BaseView>> set = validator.validate(this, groups);
    return set;
  }
}
