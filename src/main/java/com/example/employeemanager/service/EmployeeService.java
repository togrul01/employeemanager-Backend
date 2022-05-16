package com.example.employeemanager.service;

import com.example.employeemanager.exception.UserNotFoundException;
import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return repository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return repository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return repository.findEmployeeById(id).
                orElseThrow(() -> new UserNotFoundException(
                        "User by id " + id + "was not found"
                ));
    }


    public void deleteEmployee(Long id) {
        repository.deleteEmployeeById(id);
    }
}
