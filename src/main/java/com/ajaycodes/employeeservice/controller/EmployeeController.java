package com.ajaycodes.employeeservice.controller;

import com.ajaycodes.employeeservice.dao.EmployeeDao;
import com.ajaycodes.employeeservice.exception.EmployeeNotFoundException;
import com.ajaycodes.employeeservice.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return employeeDao.getAllEmployee();
    }

    @GetMapping("/employees/{empId}")
    public Employee getEmployeeById(@PathVariable int empId) {
        Employee employee = employeeDao.getEmployeeById(empId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not Found");
        }
        return employee;

    }

    @PostMapping("/employees/user")
    ResponseEntity<Object> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeDao.saveEmployee(employee);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{employeeId}")
                .buildAndExpand(savedEmployee.getEmployeeId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/employees/delete/{empId}")
    void deleteEmployee(@PathVariable int empId) {
        Employee employee = employeeDao.deleteEmployee(empId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not Found");
        }
    }

}
