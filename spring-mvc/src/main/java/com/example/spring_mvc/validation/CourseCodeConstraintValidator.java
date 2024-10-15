package com.example.spring_mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
    private String coursePrefix;

    @Override
    public void initialize(CourseCode courseCode) {
        // fill attribute with value that developer assigned in annotation
        coursePrefix = courseCode.value();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
       // `code`: hold form data entered by user.
       // `ConstraintValidatorContext`: helper class for additional error messages.

        boolean result;

        if (code != null)
            result = code.startsWith(coursePrefix);
        else
            result = true;

        return result;

    }
}
