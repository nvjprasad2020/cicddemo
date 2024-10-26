package com.example.demo.controller;

import com.example.demo.data.EmployeesDB;
import com.example.demo.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeesDB employeesDB;

    private List<Employee> mockEmployeeList;

    @BeforeEach
    void setUp() {
        Employee emp1 = new Employee(1, "John Doe", "Software Engineer", 60000);
        Employee emp2 = new Employee(2, "Jane Doe", "Manager", 80000);
        mockEmployeeList = Arrays.asList(emp1, emp2);
        when(employeesDB.getAllEmployees()).thenReturn(mockEmployeeList);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(mockEmployeeList.size()))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        int employeeId = 1;
        mockMvc.perform(get("/api/v1/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testForNotFoundException() throws Exception {
        int employeeId = 1;
        mockMvc.perform(get("/api/v1/employees/name/{name}", "jp")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
