package io.github.soumikuxd.PagerDemo.repositories;

import io.github.soumikuxd.PagerDemo.models.ResponseRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DbRepository extends JpaRepository<ResponseRow, String> {
    @Query(value = "select r.tag, r.date from survey.responses r limit 40", nativeQuery = true)
    List<ResponseRow> getAll();
}
