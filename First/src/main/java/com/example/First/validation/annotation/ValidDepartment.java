package com.example.First.validation.annotation;

import com.example.First.validation.validator.DepartmentExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentExistsValidator.class)
public @interface ValidDepartment {

    String message() default "Department does not exist";

    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
