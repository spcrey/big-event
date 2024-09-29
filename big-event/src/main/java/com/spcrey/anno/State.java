package com.spcrey.anno;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.spcrey.validation.StateValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = { StateValidation.class })
 
public @interface State {
	
    String message() default "state value must be in published and draft";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
