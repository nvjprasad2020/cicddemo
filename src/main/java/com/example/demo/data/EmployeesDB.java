package com.example.demo.data;

import com.example.demo.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeesDB {
    private static List<Employee> employeeList = new ArrayList<>();

    static {
        employeeList.add(new Employee(1, "jp viswanath", "Software Engineer", 75000.0f));
        employeeList.add(new Employee(2, "siva raj", "Project Manager", 85000.0f));
        employeeList.add(new Employee(3, "kiran kumar", "DevOps Engineer", 70000.0f));
        employeeList.add(new Employee(4, "pavan sankar", "Business Analyst", 65000.0f));
        employeeList.add(new Employee(5, "viswaprasad", "QA Engineer", 60000.0f));
    }

    public static List<Employee> getAllEmployees() {
        return employeeList;
    }
}
