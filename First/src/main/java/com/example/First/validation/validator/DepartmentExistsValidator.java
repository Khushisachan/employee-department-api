package com.example.First.validation.validator;

import com.example.First.repositry.DepartmentRepositry;
import com.example.First.validation.annotation.ValidDepartment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentExistsValidator implements ConstraintValidator<ValidDepartment,Long> {
    private final DepartmentRepositry departmentRepositry;

    @Override
    public boolean isValid(Long departmentId , ConstraintValidatorContext context){
        if(departmentId == null){
            return true;
        }

        return departmentRepositry.existsById(departmentId);
    }
}
