package com.ajaycodes.employeeservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;
    @Email
    @Size(max = 45)
    @Column(name = "email", length = 45)
    private String email;

    @OneToMany(mappedBy = "emp", orphanRemoval = true)
    private Set<Department> allDepartments = new LinkedHashSet<>();

    public Set<Department> getAllDepartments() {
        return allDepartments;
    }

    public void setAllDepartments(Set<Department> allDepartments) {
        this.allDepartments = allDepartments;
    }

    public Employee(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
