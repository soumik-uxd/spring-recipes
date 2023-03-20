package io.github.soumikuxd.pagerhazelcastdemo.repositories;

import io.github.soumikuxd.pagerhazelcastdemo.models.ResponseRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DbRepository extends JpaRepository<ResponseRow, String> {
    @Query(value = "select c.first_name, c.last_name from public.customer c order by c.customer_id asc limit 40", nativeQuery = true)
    List<ResponseRow> getAll();
}
