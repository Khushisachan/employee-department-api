package com.example.First.repositry;

import com.example.First.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositry extends JpaRepository<Employee,Long> {

    void deleteAllById(Long id);
}
