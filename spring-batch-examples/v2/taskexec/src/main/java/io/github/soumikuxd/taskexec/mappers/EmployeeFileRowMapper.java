package io.github.soumikuxd.taskexec.mappers;

import io.github.soumikuxd.taskexec.models.Employee;
import jakarta.annotation.Nonnull;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EmployeeFileRowMapper implements FieldSetMapper<Employee> {
    @Override
    @Nonnull
    public Employee mapFieldSet(FieldSet fieldSet) throws BindException {
        Employee employee = new Employee();
        employee.setEmployeeId(fieldSet.readString("employeeId"));
        employee.setFirstName(fieldSet.readString("firstName"));
        employee.setLastName(fieldSet.readString("lastName"));
        employee.setEmail(fieldSet.readString("email"));
        employee.setAge(fieldSet.readInt("age"));
        return employee;
    }
}