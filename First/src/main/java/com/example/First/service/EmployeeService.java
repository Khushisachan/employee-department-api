package com.example.First.service;

import com.example.First.entity.Employee;
import com.example.First.entity.EmployeeNew;
import com.example.First.repositry.EmployeeNewRepository;
import com.example.First.repositry.EmployeeRepositry;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepositry employeeRepositry;

    @Autowired
    private EmployeeNewRepository employeeNewRepository;

    public Employee saveEmp(Employee e){
        return employeeRepositry.save(e);
    }

    public List<Employee> getAllEmp(){
        return employeeRepositry.findAll();
    }

    public Employee getEmpById(Long id) {
        Employee user = employeeRepositry.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found with id: " + id));
        return user;
    }

    public Employee updateEmp(Employee e, Long id) {
        Employee existing = getEmpById(id);
        existing.setName(e.getName());
        return employeeRepositry.save(existing);
    }

    public void deleteEmp(Long id){
        employeeRepositry.deleteById(id);
    }

    public void createEmployeeNew(String name) {
        EmployeeNew employeeNew = new EmployeeNew();
        employeeNew.setName(name);
        employeeNewRepository.save(employeeNew);
    }

    public Object getAllNew() {
        return employeeNewRepository.findAll();
    }
}
