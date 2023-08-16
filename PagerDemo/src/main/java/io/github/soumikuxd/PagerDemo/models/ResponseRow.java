package io.github.soumikuxd.PagerDemo.models;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "public")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ResponseRow {
    @Id
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseRow that = (ResponseRow) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
