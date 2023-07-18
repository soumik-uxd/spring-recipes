package io.github.soumikuxd.agesummary.repositories;

import io.github.soumikuxd.agesummary.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
