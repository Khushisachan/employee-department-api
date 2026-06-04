package com.example.First.service;

import com.example.First.dto.DepartmentDTO;
import com.example.First.entity.Department;
import com.example.First.exception.ResourceNotFoundException;
import com.example.First.repositry.DepartmentRepositry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @Mock
    private DepartmentRepositry departmentRepositry;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // 1. saveDept()
    @Test
    void saveDepartmentTest() {

        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("IT");

        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        when(departmentRepositry.save(any(Department.class)))
                .thenReturn(department);

        Department saved = departmentService.saveDept(dto);

        assertEquals("IT", saved.getName());
    }

    // 2. getAll()
    @Test
    void getAllDepartmentTest() {

        List<Department> departments = List.of(
                new Department(),
                new Department()
        );

        when(departmentRepositry.findAll())
                .thenReturn(departments);

        List<Department> result =
                departmentService.getAll();

        assertEquals(2, result.size());
    }

    // 3. getById()
    @Test
    void getDepartmentByIdTest() {

        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        when(departmentRepositry.findById(1L))
                .thenReturn(Optional.of(department));

        Department result =
                departmentService.getById(1L);

        assertEquals("IT", result.getName());
    }

    // 4. Department Not Found
    @Test
    void departmentNotFoundTest() {

        when(departmentRepositry.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> departmentService.getById(1L)
        );
    }

    // 5. updateById()
    @Test
    void updateDepartmentTest() {

        Department existing = new Department();
        existing.setId(1L);
        existing.setName("HR");

        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("IT");

        when(departmentRepositry.findById(1L))
                .thenReturn(Optional.of(existing));

        when(departmentRepositry.save(any(Department.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Department updated =
                departmentService.updateById(dto, 1L);

        assertEquals("IT", updated.getName());
    }

    // 6. deleteById()
    @Test
    void deleteDepartmentTest() {

        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        when(departmentRepositry.findById(1L))
                .thenReturn(Optional.of(department));

        departmentService.deleteById(1L);

        verify(departmentRepositry)
                .delete(department);
    }
}