package com.ajaycodes.employeeservice.dao;

import com.ajaycodes.employeeservice.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class EmployeeDao {

    private static final List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(1, "Nancy", "nancy@mail.com"));
        employees.add(new Employee(2, "Daniel", "daniel@mail.com"));
        employees.add(new Employee(3, "Scott", "scott@mail.com"));
    }

    public List<Employee> getAllEmployee() {
        return employees;
    }

    public Employee getEmployeeById(int empId) {
        return employees.stream().filter(employee -> employee.getEmployeeId() == empId).findFirst().orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        employee.setEmployeeId(employees.size()+1);
        employees.add(employee);
        return employee;
    }

    public Employee deleteEmployee(int empId) {
        Iterator<Employee> employeeIterator = employees.iterator();
        while (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            if (employee.getEmployeeId() == empId) {
                employeeIterator.remove();
                return employee;
            }
        }
        return null;
    }
}
