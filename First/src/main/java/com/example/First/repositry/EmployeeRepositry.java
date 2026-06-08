package com.example.First.repositry;

import com.example.First.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import static javax.swing.text.html.HTML.Tag.SELECT;

public interface EmployeeRepositry extends JpaRepository<Employee,Long> {

    void deleteAllById(Long id);


    @Query("""
           SELECT e
           FROM Employee e
           WHERE e.name LIKE %:name%
           """)
    Page<Employee> findByName(String name, Pageable pageable);
}
