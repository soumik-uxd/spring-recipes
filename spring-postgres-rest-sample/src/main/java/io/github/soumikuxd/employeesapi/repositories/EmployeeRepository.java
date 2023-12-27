package io.github.soumikuxd.employeesapi.repositories;

import io.github.soumikuxd.employeesapi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
