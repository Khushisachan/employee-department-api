package com.example.First.service;

import com.example.First.dto.DepartmentDTO;
import com.example.First.entity.Department;
import com.example.First.exception.ResourceNotFoundException;
import com.example.First.repositry.DepartmentRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepositry departmentRepositry;

    public Department saveDept(DepartmentDTO dto) {
        Department department = new Department();
        department.setName(dto.getName());
        return departmentRepositry.save(department);
    }

    public List<Department> getAll() {
        return departmentRepositry.findAll();
    }

    public Department getById(Long id) {
        return departmentRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    public Department updateById(DepartmentDTO dto, Long id) {
        Department existing = getById(id);
        existing.setName(dto.getName());
        return departmentRepositry.save(existing);
    }

    public void deleteById(Long id) {
        Department department = getById(id);
        departmentRepositry.delete(department);
    }
}