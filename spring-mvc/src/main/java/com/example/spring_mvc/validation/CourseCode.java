package com.example.spring_mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// the class that will contain implementation of this annotation (validation logic code)
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
// means we can apply this annotation to methods and fields
@Target({ElementType.METHOD, ElementType.FIELD})
// process this annotation at runtime
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
    // define course code field
    public String value() default "MO";

    // define error message field
    public String message() default "must start with MO";

    // define default groups
    public Class<?>[] groups() default {};

    // define default payloads
    public Class<? extends Payload>[] payload() default {};
}
