package com.example.First.repositry;

import com.example.First.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface DepartmentRepositry extends JpaRepository<Department,Long> {
}
