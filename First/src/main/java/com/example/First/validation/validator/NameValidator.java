package com.example.First.validation.validator;

import com.example.First.repositry.EmployeeRepositry;
import com.example.First.validation.annotation.ValidName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NameValidator implements ConstraintValidator<ValidName, String> {
    private EmployeeRepositry employeeRepositry;
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        return value != null
                && value.matches("^[A-Za-z ]+$");
    }
}
