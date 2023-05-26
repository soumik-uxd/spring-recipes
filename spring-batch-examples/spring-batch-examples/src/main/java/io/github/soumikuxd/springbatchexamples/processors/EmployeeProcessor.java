package io.github.soumikuxd.springbatchexamples.processors;

import io.github.soumikuxd.springbatchexamples.models.Employee;
import jakarta.annotation.Nonnull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
    @Override
    public Employee process(@Nonnull Employee employee) throws Exception {
        return employee;
    }
}
