package io.github.soumikuxd.springbatchexamples.repositories;

import io.github.soumikuxd.springbatchexamples.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
