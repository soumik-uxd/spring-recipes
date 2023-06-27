package io.github.soumikuxd.csv2db.processors;

import io.github.soumikuxd.csv2db.models.Employee;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
    @Override
    public Employee process(@Nonnull Employee employee) throws Exception {
        log.info("Employee: {}", employee);
        return employee;
    }
}