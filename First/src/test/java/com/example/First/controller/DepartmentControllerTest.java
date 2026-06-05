package com.example.First.controller;

import com.example.First.dto.DepartmentDTO;
import com.example.First.entity.Department;
import com.example.First.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    //1. Save Department
    @Test
    void saveDepartmentTest() throws Exception{
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("HR");

        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        when(departmentService.saveDept(any(DepartmentDTO.class))).thenReturn(department);

        mockMvc.perform(post("/departments").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk());
    }

    //2. Get all departments
    // 2. Get All Departments
    @Test
    void getAllDepartmentsTest() throws Exception {

        Department d1 = new Department();
        d1.setId(1L);
        d1.setName("IT");

        Department d2 = new Department();
        d2.setId(2L);
        d2.setName("HR");

        when(departmentService.getAll())
                .thenReturn(List.of(d1, d2));

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("IT"))
                .andExpect(jsonPath("$[1].name").value("HR"));
    }

    // 3. Get Department By Id
    @Test
    void getDepartmentByIdTest() throws Exception {

        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        when(departmentService.getById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("IT"));
    }

    // 4. Update Department
    @Test
    void updateDepartmentTest() throws Exception {

        DepartmentDTO dto = new DepartmentDTO();
        dto.setName("Updated Department");

        Department department = new Department();
        department.setId(1L);
        department.setName("Updated Department");

        when(departmentService.updateById(
                any(DepartmentDTO.class),
                eq(1L)))
                .thenReturn(department);

        mockMvc.perform(put("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Updated Department"));
    }

    // 5. Delete Department
    @Test
    void deleteDepartmentTest() throws Exception {

        doNothing().when(departmentService)
                .deleteById(1L);

        mockMvc.perform(delete("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("Successfully Deleted"));
    }
}
