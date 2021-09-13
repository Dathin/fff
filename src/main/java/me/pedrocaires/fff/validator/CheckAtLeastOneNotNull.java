package me.pedrocaires.fff.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckAtLeastOneNotNull.CheckAtLeastOneNotNullValidator.class)
@Documented
public @interface CheckAtLeastOneNotNull {

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] fieldNames();

	class CheckAtLeastOneNotNullValidator implements ConstraintValidator<CheckAtLeastOneNotNull, Object> {

		private String[] fieldNames;

		public void initialize(CheckAtLeastOneNotNull constraintAnnotation) {
			this.fieldNames = constraintAnnotation.fieldNames();
		}

		public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
			var beanWrapper = new BeanWrapperImpl(object);
			var hasNotNull = false;

			for (var fieldName : fieldNames) {
				var fieldValue = beanWrapper.getPropertyValue(fieldName);

				if (fieldValue != null) {
					hasNotNull = true;
					break;
				}
				constraintContext
						.buildConstraintViolationWithTemplate(constraintContext.getDefaultConstraintMessageTemplate())
						.addPropertyNode(fieldName).addConstraintViolation().disableDefaultConstraintViolation();
			}

			return hasNotNull;
		}

	}

}