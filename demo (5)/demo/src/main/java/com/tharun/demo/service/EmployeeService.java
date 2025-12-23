package com.tharun.demo.service;

import com.tharun.demo.model.Employee;
import com.tharun.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // GET ALL
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // GET BY ID
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // CREATE
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    // UPDATE
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setSalary(updatedEmployee.getSalary());

        return repository.save(employee);
    }

    // DELETE
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
