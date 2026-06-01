package com.example.First.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Table(name = "Departments")
public class Department {

    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employee;
}
