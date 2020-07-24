package com.example.spring.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

/**
 * The annotated {@code CharSequence} must match the specified regular
 * expression.
 * The regular expression follows the Java regular expression conventions
 * see {@link java.util.regex.Pattern}.
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 *
 * @author Emmanuel Bernard
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(Username.List.class)
@Documented
@Constraint(validatedBy = {})
@Pattern(regexp = "^[\\p{Alnum}]+$")
@Length
public @interface Username {

	@OverridesAttribute(constraint = Pattern.class)
	String message() default "{com.example.spring.validation.Code.message}";

	@OverridesAttribute(constraint = Length.class)
	int min() default 2;

	@OverridesAttribute(constraint = Length.class)
	int max() default 50;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Username[] value();
	}

}
