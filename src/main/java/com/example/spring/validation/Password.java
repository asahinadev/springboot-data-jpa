package com.example.spring.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;

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
@Repeatable(Password.List.class)
@Documented
@Constraint(validatedBy = {})
@Pattern(regexp = "^[\\p{Graph}]+$")
@Size
public @interface Password {

	@OverridesAttribute(constraint = Pattern.class)
	String message() default "{com.example.spring.validation.Password.message}";

	@OverridesAttribute(constraint = Size.class)
	int min() default 16;

	@OverridesAttribute(constraint = Size.class)
	int max() default 50;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Password[] value();
	}

}
