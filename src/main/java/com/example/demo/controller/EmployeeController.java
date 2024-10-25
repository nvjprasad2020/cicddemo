package com.example.demo.controller;

import com.example.demo.data.EmployeesDB;
import com.example.demo.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")

public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("Getting all employees ");
        return ResponseEntity.ok(EmployeesDB.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getAllEmployees(@PathVariable Integer id) {
        logger.info("Getting given employee ");
        return ResponseEntity.ok(EmployeesDB.getAllEmployees().get(id - 1));
    }

}
