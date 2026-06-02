package com.example.First.service;

import com.example.First.dto.EmployeeDTO;
import com.example.First.entity.Department;
import com.example.First.entity.Employee;
import com.example.First.entity.EmployeeNew;
import com.example.First.exception.ResourceNotFoundException;
import com.example.First.repositry.DepartmentRepositry;
import com.example.First.repositry.EmployeeNewRepository;
import com.example.First.repositry.EmployeeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepositry employeeRepositry;

    @Autowired
    private EmployeeNewRepository employeeNewRepository;

    @Autowired
    private DepartmentRepositry departmentRepositry;

    public Employee saveEmp(EmployeeDTO dto) {
      Department department = departmentRepositry.findById(dto.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dto.getDepartmentId()));
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDepartment(department);
        return employeeRepositry.save(employee);
    }

    public List<Employee> getAllEmp() {
        return employeeRepositry.findAll();
    }

    public Employee getEmpById(Long id) {
        return employeeRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public Employee updateEmp(EmployeeDTO dto, Long id) {
        Employee existing = getEmpById(id);
        Department department = departmentRepositry.findById(dto.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dto.getDepartmentId()));
        existing.setName(dto.getName());
        existing.setDepartment(department);
        return employeeRepositry.save(existing);
    }

    public void deleteEmp(Long id) {
        Employee employee = getEmpById(id);
        employeeRepositry.delete(employee);
    }

    public void createEmployeeNew(String name) {
        EmployeeNew employeeNew = new EmployeeNew();
        employeeNew.setName(name);
        employeeNewRepository.save(employeeNew);
    }

    public List<EmployeeNew> getAllNew() {
     return employeeNewRepository.findAll();
    }
}