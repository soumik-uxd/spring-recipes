package io.github.soumikuxd.dbcleaner.repository;

import io.github.soumikuxd.dbcleaner.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
