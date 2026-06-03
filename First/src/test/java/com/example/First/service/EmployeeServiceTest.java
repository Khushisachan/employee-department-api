package com.example.First.service;

import com.example.First.dto.DepartmentDTO;
import com.example.First.dto.EmployeeDTO;
import com.example.First.entity.Department;
import com.example.First.entity.Employee;
import com.example.First.exception.ResourceNotFoundException;
import com.example.First.repositry.DepartmentRepositry;
import com.example.First.repositry.EmployeeRepositry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class EmployeeServiceTest {

    @Mock
    private EmployeeRepositry employeeRepositry;

    @Mock
    private DepartmentRepositry departmentRepository;


    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

//    1. saveEmp()
    @Test
    void saveEmployeeTest() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Khushi");
        employeeDTO.setDepartmentId(1L);

        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        Employee employee = new Employee();
        employee.setName("Khushi");
        employee.setDepartment(department);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        when(employeeRepositry.save(any(Employee.class))).thenReturn(employee);
        Employee saved = employeeService.saveEmp(employeeDTO);
        assertEquals("Khushi", saved.getName());

    }

//        2. getAllEmp()
    @Test
    void getAllEmployeeTest(){
            List<Employee> employees = List.of(new Employee(),new Employee());
            when(employeeRepositry.findAll()).thenReturn(employees);
            List<Employee> result = employeeService.getAllEmp();

            assertEquals(2,result.size());
        }

//        3. getEmpById()
    @Test
    void getEmployeeByIdTest() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Khushi");

        when(employeeRepositry.findById(1L)).thenReturn(Optional.of(employee));
        Employee result = employeeService.getEmpById(1L);

        assertEquals("Khushi", result.getName());
    }


//    4. getEmpById Not Found
    @Test
    void employeeNotFoundTest(){
        when(employeeRepositry.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,() -> employeeService.getEmpById(1L));
    }

//    5. updateEmp()
    @Test
    void updateEmployeeTest() {

        // Existing employee
        Employee existing = new Employee();
        existing.setId(1L);
        existing.setName("Old Name");

        // Existing department
        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        // Update request
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("New Name");
        dto.setDepartmentId(1L);

        // Mock employee lookup
        when(employeeRepositry.findById(1L)).thenReturn(Optional.of(existing));
        // Mock department lookup
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        // Mock save
        when(employeeRepositry.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));
        // Call service
        Employee updated = employeeService.updateEmp(dto, 1L);

        // Assertions
        assertEquals("New Name", updated.getName());
        assertEquals("IT", updated.getDepartment().getName());
    }

//        6. deleteEmp()
        @Test
        void deleteEmployeeTest() {
            Employee employee = new Employee();
            employee.setId(1L);

            when(employeeRepositry.findById(1L)).thenReturn(Optional.of(employee));
            employeeService.deleteEmp(1L);

            verify(employeeRepositry)
                    .delete(employee);
    }
}
