package com.example.First.dto;
import com.example.First.validation.annotation.ValidName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class        DepartmentDTO {

    @NotBlank(message = "Department name is required")
    // validator
    @ValidName
    private String name;
}