package com.example.First.service;

import com.example.First.dto.EmployeeDTO;
import com.example.First.entity.Department;
import com.example.First.entity.Employee;
import com.example.First.entity.EmployeeNew;
import com.example.First.exception.ResourceNotFoundException;
import com.example.First.repositry.DepartmentRepositry;
import com.example.First.repositry.EmployeeNewRepository;
import com.example.First.repositry.EmployeeRepositry;
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

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepositry employeeRepositry;

    @Mock
    private EmployeeNewRepository employeeNewRepository;

    @Mock
    private DepartmentRepositry departmentRepositry;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEmployeeTest() {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Khushi");
        dto.setDepartmentId(1L);

        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        Employee employee = new Employee();
        employee.setName("Khushi");
        employee.setDepartment(department);

        when(departmentRepositry.findById(1L))
                .thenReturn(Optional.of(department));

        when(employeeRepositry.save(any(Employee.class)))
                .thenReturn(employee);

        Employee saved = employeeService.saveEmp(dto);

        assertEquals("Khushi", saved.getName());
        assertEquals("IT", saved.getDepartment().getName());
    }

    @Test
    void saveEmployeeDepartmentNotFoundTest() {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Khushi");
        dto.setDepartmentId(1L);

        when(departmentRepositry.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeService.saveEmp(dto)
        );
    }

    @Test
    void getAllEmployeeTest() {

        List<Employee> employees = List.of(
                new Employee(),
                new Employee()
        );

        when(employeeRepositry.findAll())
                .thenReturn(employees);

        List<Employee> result =
                employeeService.getAllEmp();

        assertEquals(2, result.size());
    }

    @Test
    void getEmployeeByIdTest() {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Khushi");

        when(employeeRepositry.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result =
                employeeService.getEmpById(1L);

        assertEquals("Khushi", result.getName());
    }

    @Test
    void employeeNotFoundTest() {

        when(employeeRepositry.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeService.getEmpById(1L)
        );
    }

    @Test
    void updateEmployeeTest() {

        Employee existing = new Employee();
        existing.setId(1L);
        existing.setName("Old Name");

        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("New Name");
        dto.setDepartmentId(1L);

        when(employeeRepositry.findById(1L))
                .thenReturn(Optional.of(existing));

        when(departmentRepositry.findById(1L))
                .thenReturn(Optional.of(department));

        when(employeeRepositry.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Employee updated =
                employeeService.updateEmp(dto, 1L);

        assertEquals("New Name", updated.getName());
        assertEquals("IT", updated.getDepartment().getName());
    }

    @Test
    void deleteEmployeeTest() {

        Employee employee = new Employee();
        employee.setId(1L);

        when(employeeRepositry.findById(1L))
                .thenReturn(Optional.of(employee));

        employeeService.deleteEmp(1L);

        verify(employeeRepositry)
                .delete(employee);
    }

    @Test
    void createEmployeeNewTest() {

        employeeService.createEmployeeNew("Khushi");

        verify(employeeNewRepository)
                .save(any(EmployeeNew.class));
    }

    @Test
    void getAllNewTest() {

        List<EmployeeNew> employees = List.of(
                new EmployeeNew(),
                new EmployeeNew()
        );

        when(employeeNewRepository.findAll())
                .thenReturn(employees);

        List<EmployeeNew> result =
                employeeService.getAllNew();

        assertEquals(2, result.size());
    }
}
