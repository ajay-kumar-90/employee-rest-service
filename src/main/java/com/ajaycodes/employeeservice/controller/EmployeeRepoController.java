package com.ajaycodes.employeeservice.controller;

import com.ajaycodes.employeeservice.exception.EmployeeNotFoundException;
import com.ajaycodes.employeeservice.model.Department;
import com.ajaycodes.employeeservice.model.Employee;
import com.ajaycodes.employeeservice.repository.DepartmentRepository;
import com.ajaycodes.employeeservice.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeRepoController {

    private static final String EMPLOYEE_NOT_FOUND = "Employee not found";
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeRepoController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/jpa/employee")
    List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/jpa/employees/{empId}")
    EntityModel<Employee> getEmployeeById(@PathVariable int empId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(empId);
        Employee employee = employeeOptional.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        EntityModel<Employee> employeeEntityModel = EntityModel.of(employee);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll())
                .withRel("all-employees");
        employeeEntityModel.add(link);
        return employeeEntityModel;
    }

    @PostMapping("/jpa/employees/user")
    ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{emploeeId}")
                .buildAndExpand(savedEmployee.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/jpa/addDepartment/{empId}")
    ResponseEntity<Object> saveDepartment(@PathVariable int empId, @Valid @RequestBody Department department) {
        Optional<Employee> employeeOptional = employeeRepository.findById(empId);
        Employee employee = employeeOptional.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        department.setEmp(employee);
        departmentRepository.save(department);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{empId}")
                .buildAndExpand(employee.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("jpa/employee/delete/{empId}")
    void deleteEmployee(@PathVariable int empId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(empId);
        Employee employee = employeeOptional.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
        employeeRepository.delete(employee);
    }

}
