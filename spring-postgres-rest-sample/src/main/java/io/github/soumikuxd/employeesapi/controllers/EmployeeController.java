package io.github.soumikuxd.employeesapi.controllers;

import io.github.soumikuxd.employeesapi.exceptions.ResourceNotFoundException;
import io.github.soumikuxd.employeesapi.models.Employee;
import io.github.soumikuxd.employeesapi.repositories.EmployeeRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/all")
    @ApiOperation(value = "Get a list of all employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok().body(this.employeeRepository.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get employee details by id", response = Employee.class)
    public ResponseEntity<Employee> getEmployeeById(
        @ApiParam(value = "Id of the employee")
        @PathVariable(value = "id") long employeeId
    ) throws ResourceNotFoundException {
        Employee employee = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add the details of a new employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(this.employeeRepository.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update the details of an existing employee")
    public ResponseEntity<Employee> updateEmployee(
        @ApiParam(value = "Id of the employee")
        @PathVariable(value = "id") long employeeId,
        @Validated @RequestBody Employee updatedEmployee
    ) throws ResourceNotFoundException {
        Employee employee = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        return ResponseEntity.ok(this.employeeRepository.save(employee));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove the details of an employee")
    public Map<String, Boolean> removeEmployee(
        @ApiParam(value = "Id of the employee")
        @PathVariable(value = "id") long employeeId
    ) throws ResourceNotFoundException {
        Employee employee = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + employeeId));
        this.employeeRepository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
