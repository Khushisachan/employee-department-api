package com.example.First.controller;

import com.example.First.entity.Employee;
import com.example.First.entity.EmployeeNew;
import com.example.First.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee e){
        return employeeService.saveEmp(e);
    }

    @GetMapping
    public List<Employee>getAllEmp(){
        return employeeService.getAllEmp();
    }

    @GetMapping("/{id}")
    public Employee getEmpById(@PathVariable Long id){
        return employeeService.getEmpById(id);
    }

    @PutMapping("/{id}")
    public Employee updateById(@RequestBody Employee e, @PathVariable Long id){
       return employeeService.updateEmp(e,id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        employeeService.deleteEmp(id);
        return "successfully deleted";
    }

    @PostMapping("/new")
    public ResponseEntity<?> createNewEmp(@RequestBody String name) {
        employeeService.createEmployeeNew(name);
        return ResponseEntity.ok("created successfully");
    }

    @GetMapping("/new")
    public ResponseEntity<?> getAllNew() {
        return ResponseEntity.ok(employeeService.getAllNew());
    }

}
