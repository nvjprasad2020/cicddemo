package com.example.demo.data;

import com.example.demo.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeesDB {
    private static List<Employee> employeeList = new ArrayList<>();

    public static List<Employee> getAllEmployees() {
         // Add 5 Employee records to the list
        employeeList.add(new Employee(1, "John Doe", "Software Engineer", 75000.0f));
        employeeList.add(new Employee(2, "Jane Smith", "Project Manager", 85000.0f));
        employeeList.add(new Employee(3, "Michael Brown", "DevOps Engineer", 70000.0f));
        employeeList.add(new Employee(4, "Sarah Johnson", "Business Analyst", 65000.0f));
        employeeList.add(new Employee(5, "Chris Evans", "QA Engineer", 60000.0f));
        return employeeList;
    }
}
