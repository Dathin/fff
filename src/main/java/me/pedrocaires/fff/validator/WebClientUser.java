package me.pedrocaires.fff.validator;

import me.pedrocaires.fff.endpoint.user.model.IsWebClientRequest;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

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
@Constraint(validatedBy = WebClientUser.WebClientUserValidator.class)
@Documented
public @interface WebClientUser {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	class WebClientUserValidator implements ConstraintValidator<WebClientUser, IsWebClientRequest> {

		private static final EmailValidator emailValidator = new EmailValidator();

		public boolean isValid(IsWebClientRequest isWebClientRequest, ConstraintValidatorContext constraintContext) {
			if (isWebClientRequest.isWebClient()
					&& !emailValidator.isValid(isWebClientRequest.getIdentifier(), constraintContext)) {
				constraintContext.buildConstraintViolationWithTemplate("Must be valid e-mail")
						.addPropertyNode("identifier").addConstraintViolation().disableDefaultConstraintViolation();
				return false;
			}
			return true;
		}

	}

}
