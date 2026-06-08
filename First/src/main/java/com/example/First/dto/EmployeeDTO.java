package com.example.First.dto;

import com.example.First.validation.annotation.ValidDepartment;
import com.example.First.validation.annotation.ValidName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDTO {

    @NotBlank(message = "Employee name is required")
    @ValidName
    private String name;

    @NotNull(message = "Department id is required")
    @ValidDepartment
    private Long departmentId;
}
