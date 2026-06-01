package com.example.First.service;

import com.example.First.entity.Department;
import com.example.First.repositry.DepartmentRepositry;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    public DepartmentRepositry departmentRepositry;

    public Department saveDept(Department d){
        return departmentRepositry.save(d);
    }

    public List<Department> getAll(){
        return departmentRepositry.findAll();
    }

    public Department getById(Long id){
        return departmentRepositry.getById(id);
    }

    public Department updateBYId(Department d,Long id){
        Department existing =  departmentRepositry.getById(id);
        existing.setName(d.getName());
        return departmentRepositry.save(existing);
    }

    public void deleteById(Long id){
        departmentRepositry.deleteById(id);
    }
}
