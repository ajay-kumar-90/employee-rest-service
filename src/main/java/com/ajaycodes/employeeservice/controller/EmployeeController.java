package com.ajaycodes.employeeservice.controller;

import com.ajaycodes.employeeservice.dao.EmployeeDao;
import com.ajaycodes.employeeservice.exception.EmployeeNotFoundException;
import com.ajaycodes.employeeservice.model.Employee;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
    public List<Employee> getAll() {
        return employeeDao.getAllEmployee();
    }

    @GetMapping(value = "/employees/{empId}", produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
    public EntityModel<Employee> getEmployeeById(@PathVariable int empId) {
        Employee employee = employeeDao.getEmployeeById(empId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not Found");
        }
        EntityModel<Employee> employeeModel = EntityModel.of(employee);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll())
                .withRel("all-employees");
        employeeModel.add(link);
        return employeeModel;
    }

    @PostMapping(value = "/employees/user", consumes = {MediaType.APPLICATION_JSON_VALUE ,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeDao.saveEmployee(employee);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{employeeId}")
                .buildAndExpand(savedEmployee.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/employees/delete/{empId}", consumes = {MediaType.APPLICATION_JSON_VALUE ,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
    void deleteEmployee(@PathVariable int empId) {
        Employee employee = employeeDao.deleteEmployee(empId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not Found");
        }
    }

}
