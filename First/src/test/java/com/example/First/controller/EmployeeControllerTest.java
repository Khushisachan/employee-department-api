package com.example.First.controller;

import com.example.First.dto.DepartmentDTO;
import com.example.First.dto.EmployeeDTO;
import com.example.First.entity.Department;
import com.example.First.entity.Employee;
import com.example.First.entity.EmployeeNew;
import com.example.First.repositry.DepartmentRepositry;
import com.example.First.service.DepartmentService;
import com.example.First.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DepartmentRepositry departmentRepositry;

    @BeforeEach
    void setup() {
        when(departmentRepositry.existsById(1L))
                .thenReturn(true);
    }


    // 1. POST /employees
    @Test
    void saveEmployeeTest() throws Exception {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Khushi");
        dto.setDepartmentId(1L);

        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Khushi");
        employee.setDepartment(department);

        when(employeeService.saveEmp(any(EmployeeDTO.class)))
                .thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Khushi"));

    }

        // 2. GET /employees
        @Test
        void getAllEmployeesTest() throws Exception {

            Employee employee1 = new Employee();
            employee1.setId(1L);
            employee1.setName("khushi");

            Employee employee2 = new Employee();
            employee2.setId(1L);
            employee2.setName("sachan");

            when(employeeService.getAllEmp()).thenReturn(List.of(employee1,employee2));

            mockMvc.perform(get("/employees")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2));
    }

        // 3. GET /employees/{id}
    @Test
    void getEmployeeByIdTest() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("khushi");

        when(employeeService.getEmpById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("khushi"));
    }

        // 4. PUT /employees/{id}
        @Test
        void updateEmployeeTest() throws Exception {

            EmployeeDTO dto = new EmployeeDTO();
            dto.setName("khushi renu sachan");
            dto.setDepartmentId(1L);

            Department department = new Department();
            department.setId(1L);
            department.setName("HR");

            Employee employee = new Employee();
            employee.setId(1L);
            employee.setName("khushi renu sachan");
            employee.setDepartment(department);

            when(employeeService.updateEmp(any(EmployeeDTO.class), eq(1L))).thenReturn(employee);

            mockMvc.perform(put("/employees/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("khushi renu sachan"));

        }
        // 5. DELETE /employees/{id}
            @Test
            void deleteEmployeeTest() throws Exception{
                doNothing().when(employeeService).deleteEmp(1L);
                mockMvc.perform(delete("/employees/1")).andExpect(status().isOk()).andExpect(content().string("Successfully Deleted"));
    }

    // 6. POST /employees/new
    @Test
    void createEmployeeNewTest() throws Exception{
      EmployeeDTO dto = new EmployeeDTO();
      dto.setName("khushi");
      dto.setDepartmentId(1L);
      doNothing().when(employeeService).createEmployeeNew("khushi");

        mockMvc.perform(post("/employees/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("Created Successfully"));
    }

    // 7. GET /employees/new
    @Test
    void getAllEmployeeNewTest() throws Exception{
        EmployeeNew e1 = new EmployeeNew();
        e1.setId(1L);
        e1.setName("khushi");

        EmployeeNew e2 = new EmployeeNew();
        e2.setName("sachan");
        e2.setId(2L);

        when(employeeService.getAllNew()).thenReturn(List.of(e1,e2));

        mockMvc.perform(get("/employees/new")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2));
    }
}