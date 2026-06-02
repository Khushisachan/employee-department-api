package com.example.First.controller;

import com.example.First.dto.DepartmentDTO;
import com.example.First.entity.Department;
import com.example.First.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department saveDept(@Valid @RequestBody DepartmentDTO dto) {
        return departmentService.saveDept(dto);
    }

    @GetMapping
    public List<Department> getAllDept() {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @PutMapping("/{id}")
    public Department updateById(@Valid @RequestBody DepartmentDTO dto, @PathVariable Long id) {
        return departmentService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        departmentService.deleteById(id);
        return "Successfully Deleted";
    }
}