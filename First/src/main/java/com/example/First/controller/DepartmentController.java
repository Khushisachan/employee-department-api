package com.example.First.controller;

import com.example.First.entity.Department;
import com.example.First.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department saveDept(Department d){
        return departmentService.saveDept(d);
    }

    @GetMapping
    public List<Department> getAllDept(){
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id){
        return departmentService.getById(id);
    }

    @PutMapping("/{id}")
    public Department getById(@RequestBody Department d, @PathVariable Long id){
        return departmentService.updateBYId(d,id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        departmentService.deleteById(id);
        return "sucessfully deleted";
    }
}
