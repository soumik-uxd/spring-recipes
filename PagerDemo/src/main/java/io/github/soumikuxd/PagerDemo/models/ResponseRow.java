package io.github.soumikuxd.PagerDemo.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "responses", schema = "survey")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ResponseRow {
    @Id
    private String tag;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResponseRow that = (ResponseRow) o;
        return tag != null && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
