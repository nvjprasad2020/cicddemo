package com.example.demo.data;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeesDB {
    private List<Employee> employeeList = new ArrayList<>();

    public EmployeesDB() {
        employeeList.add(new Employee(1, "JP viswanath", "Software Engineer", 75000.0f));
        employeeList.add(new Employee(2, "Siva Raj", "Project Manager", 85000.0f));
        employeeList.add(new Employee(3, "Kiran Kumar", "DevOps Engineer", 70000.0f));
        employeeList.add(new Employee(4, "Pavan Sankar", "Business Analyst", 65000.0f));
        employeeList.add(new Employee(5, "Viswa Prasad", "QA Engineer", 60000.0f));
    }

    public  List<Employee> getAllEmployees() {
        return employeeList;
    }
}
