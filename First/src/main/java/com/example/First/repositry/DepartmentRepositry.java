package com.example.First.repositry;

import com.example.First.entity.Department;
import com.example.First.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface DepartmentRepositry extends JpaRepository<Department,Long> {

    @Query("""
    SELECT d FROM Department  d WHERE d.name LIKE %:name%
""")
    public Page<Department> findByDepartmentName(String name, Pageable pageable);
}
