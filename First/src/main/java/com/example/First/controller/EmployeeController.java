package com.example.First.controller;

import com.example.First.dto.EmployeeDTO;
import com.example.First.entity.Employee;
import com.example.First.entity.EmployeeNew;
import com.example.First.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee saveEmployee(@Valid @RequestBody EmployeeDTO dto) {
        return employeeService.saveEmp(dto);
    }

    @GetMapping
    public List<Employee> getAllEmp() {
        return employeeService.getAllEmp();
    }

    @GetMapping("/{id}")
    public Employee getEmpById(@PathVariable Long id) {
        return employeeService.getEmpById(id);
    }

    @PutMapping("/{id}")
    public Employee updateById(@Valid @RequestBody EmployeeDTO dto, @PathVariable Long id) {
        return employeeService.updateEmp(dto, id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        employeeService.deleteEmp(id);
        return "Successfully Deleted";
    }

    @PostMapping("/new")
    public ResponseEntity<String> createNewEmp(@Valid @RequestBody EmployeeDTO dto) {
        employeeService.createEmployeeNew(dto.getName());
        return ResponseEntity.ok("Created Successfully");
    }

    @GetMapping("/new")
    public ResponseEntity<List<EmployeeNew>> getAllNew() {
        return ResponseEntity.ok(employeeService.getAllNew());
    }

    // pagination
    @GetMapping("/employees")
    public ResponseEntity<Page<Employee>> getEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(employeeService.getEmployee(page, size));
    }

    @GetMapping("/employees/search")
    public ResponseEntity<Page<Employee>> searchEmployee(@RequestParam String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(employeeService.searchEmployee(name, page, size));
    }


}
